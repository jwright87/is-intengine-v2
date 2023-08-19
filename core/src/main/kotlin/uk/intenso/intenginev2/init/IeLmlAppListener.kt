package uk.intenso.intenginev2.init

import com.badlogic.gdx.Gdx
import com.github.czyzby.kiwi.util.common.Exceptions
import com.github.czyzby.lml.parser.LmlParser
import com.github.czyzby.lml.parser.LmlView
import com.github.czyzby.lml.parser.impl.AbstractLmlView
import com.github.czyzby.lml.util.LmlApplicationListener
import uk.intenso.intenginev2.lmlpatch.GdxUiFactory
import uk.intenso.intenginev2.lmlpatch.lmlparser.GdxFixLmlParser
import uk.intenso.intenginev2.lmlpatch.views.TestView
import uk.intenso.intenginev2.lmlviews.TestView


/** An {@link ApplicationListener} implementation that manages a list of {@link AbstractLmlView LML views}. Forces the
 * user to prepare a {@link LmlParser} with {@link #createParser()} method. Ensures smooth view transitions. Adds
 * default actions with {@link #addDefaultActions()} method: "exit" closes the application after smooth screen hiding,
 * "close" is a no-op utility method for dialogs and "setView" changes the current view according to the actor's ID (the
 * ID has to match name of a class extending {@link AbstractLmlView}). Most of its settings are customizable - go
 * through protected methods API for more info.
 *
 * <p>
 * {@link AbstractLmlView} instances managed by this class are required to properly implement
 * {@link AbstractLmlView#getTemplateFile()}. Note that the views are likely to be accessed with reflection, so make
 * sure to include their classes in GWT reflection mechanism.
 *
 * <p>
 * What LibGDX {@link com.badlogic.gdx.Game Game} is to {@link com.badlogic.gdx.Screen Screen}, this class is the same
 * thing to {@link AbstractLmlView}. Except it adds much more functionalities.
 **/
class IeLmlAppListener: LmlApplicationListener() {

    private var view: AbstractLmlView? = null



    override fun createParser(): LmlParser {
        val parser: LmlParser = GdxUiFactory.fullExampleParser()
        GdxFixLmlParser(
            parser.data,
            parser.getSyntax()
        )
        createView(parser,"testview")

    }

    private fun createView(parser: LmlParser,lmlFileName:String) {
        try {
            val viewLmlPath = "views/$lmlFileName.lml")

            view = parser.createView(MainView.class, Gdx.files.internal(viewLmlPath))
        } catch (e: Throwable) {
            Exceptions.ignore(e)
            e.printStackTrace()
        }
        Gdx.input.inputProcessor = view.getStage()
    }
}
