package uk.intenso.intenginev2.views

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.github.czyzby.lml.annotation.LmlActor
import com.github.czyzby.lml.annotation.LmlAfter
import com.github.czyzby.lml.annotation.LmlBefore
import com.github.czyzby.lml.annotation.LmlInject
import com.github.czyzby.lml.parser.LmlData
import com.github.czyzby.lml.parser.LmlParser
import com.github.czyzby.lml.parser.impl.AbstractLmlView
import com.github.czyzby.lml.util.Lml
import ktx.log.Logger
import uk.intenso.intenginev2.lmlpatch.widgets.ButtonManager
import com.github.czyzby.lml.parser.LmlView

class TestView : AbstractLmlView,LmlView {

    constructor(stage: Stage, lmlData: LmlData) : super(Stage(ScreenViewport())) {
    }

    private val log = Logger(TestView::class.java.name)

    @LmlActor("testTable")
    private val resultTable: Table? = null

    @LmlActor("testLabel")
    private val testLabel: Label? = null

    @LmlInject
    private val buttonManager: ButtonManager? = null

    @LmlInject
    private var parser: LmlParser? = null

    fun parseTemplate() {
        val template: String = ""//TODO() templateInput.getText()
        parser!!.data.addActionContainer(viewId, this) // Making our methods available in templates.
        try {
            val actors = parser!!.parseTemplate(template)
            resultTable!!.clear()
            for (actor in actors) {
                resultTable!!.add(actor).row()
            }
        } catch (exception: java.lang.Exception) {
            onParsingError(exception)
        }
    }

    private fun onParsingError(exception: Exception) {
        // Printing the message without stack trace - we don't want to completely flood the console and its usually not
        // relevant anyway. Change to '(...), "Unable to parse LML template:", exception);' for stacks.
        Gdx.app.error(Lml.LOGGER_TAG, "Unable to parse LML template: $exception")
        resultTable!!.clear()
        resultTable!!.add("Error occurred. Sorry.")
        parser!!.fillStage(stage, Gdx.files.internal("templates/dialogs/error.lml"))
    }

    @LmlBefore
    fun lmlBefore(parser: LmlParser) {
        log.info { "LML Before..." }
        this.parser = parser
    }

    @LmlAfter
    fun lmlAfter() {
        log.info { "LML After..." }
        // This method will be invoked after main.lml is parsed and MainView's fields are fully injected and processed.
        Gdx.app.log(Lml.LOGGER_TAG, "Parsed main.lml with: $parser");
    }

    override fun getViewId(): String {
        return this.javaClass.simpleName + "View"
    }
}
