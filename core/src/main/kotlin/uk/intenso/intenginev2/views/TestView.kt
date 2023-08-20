package uk.intenso.intenginev2.views

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Action
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.github.czyzby.lml.annotation.*
import com.github.czyzby.lml.parser.LmlParser
import com.github.czyzby.lml.parser.LmlView
import com.github.czyzby.lml.parser.impl.AbstractLmlView
import com.github.czyzby.lml.util.Lml
import ktx.log.Logger
import uk.intenso.intenginev2.lmlpatch.widgets.ButtonManager
import java.util.*

class TestView : AbstractLmlView(Stage(ScreenViewport())),LmlView {

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

    /* templates/examples/checkBox.lml */
    /** @param button will have its text changed.
     */
    @LmlAction("switch")
    fun switch(button: TextButton) {
        if (button.isChecked) {
            button.setText(button.text.toString().uppercase(Locale.getDefault()))
        } else {
            button.setText(button.text.toString().lowercase(Locale.getDefault()))
        }
    }
    /* Reflected methods, available in LML views: */ /* templates/main.lml */
    fun fadeIn(): Action {
        // Used by main window just after view show.
        return Actions.sequence(Actions.alpha(0f), Actions.fadeIn(0.5f, Interpolation.fade))
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
