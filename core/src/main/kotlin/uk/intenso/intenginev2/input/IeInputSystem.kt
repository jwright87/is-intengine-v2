package uk.intenso.intenginev2.input

import ktx.app.KtxInputAdapter

/**
 * KtxInputAdapter is an interface extending InputProcessor. Provides no-op implementations of all methods, without being a class like com.badlogic.gdx.InputAdapter.
 */
class IeInputSystem : KtxInputAdapter {

    override fun keyDown(keycode: Int): Boolean {
        return super.keyDown(keycode)
    }

    override fun keyTyped(character: Char): Boolean {
        return super.keyTyped(character)
    }

    override fun keyUp(keycode: Int): Boolean {
        return super.keyUp(keycode)
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        return super.touchDown(screenX, screenY, pointer, button)
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        return super.touchUp(screenX, screenY, pointer, button)
    }
}
