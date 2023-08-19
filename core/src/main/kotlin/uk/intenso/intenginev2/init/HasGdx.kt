package uk.intenso.intenginev2.init

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.Viewport

interface HasGdx {

    fun stage():Stage

    fun viewport():Viewport

    fun batch():Batch

    fun spritebatch():SpriteBatch
}
