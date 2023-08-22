package uk.intenso.intenginev2.views

import com.badlogic.gdx.scenes.scene2d.Stage
import com.github.czyzby.lml.annotation.LmlAction
import com.github.czyzby.lml.parser.LmlView
import com.kotcrab.vis.ui.widget.Menu

class StartMenuView(val stage: Stage) : LmlView {
    override fun getStage(): Stage {
        return stage
    }

    override fun getViewId(): String {
        return StartMenuView::class.java.simpleName + "_VIEW"
    }

    @LmlAction("chooseFirst")
    fun menuPress(menu: Menu) {
        println("First Menu Pressed")
    }

    @LmlAction("submenuPress")
    fun chooseSubMenu() {
        println("SubmenuPress")
    }

    @LmlAction("chooseSecond")
    fun chooseSecond() {
        println("chooseSecond")

    }

    @LmlAction("chooseThird")
    fun chooseThird() {
        println("Choose Third")
    }
}
