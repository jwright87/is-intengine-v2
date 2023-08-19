package uk.intenso.intenginev2.init

import com.badlogic.gdx.Application.LOG_DEBUG
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.Texture.TextureFilter.Linear
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.assets.disposeSafely
import ktx.assets.toInternalFile
import ktx.async.KtxAsync
import ktx.graphics.use
import ktx.log.Logger
import ktx.log.logger


/**
 * KtxGame is a bit more opinionated Game equivalent that not only delegates all game events to the current Screen
 * instance, but also ensures non-nullability of screens, manages screen clearing, and maintains screens collection,
 * which allows switching screens while knowing only their concrete class.
 */
class IeMain : KtxGame<KtxScreen>() {

    private val log = Logger(IeMain::class.java.name)

    override fun create() {
        KtxAsync.initiate()
        Gdx.app.setLogLevel(LOG_DEBUG);

logger<IeMain>().debug { "debug.." }
        log.info { "Info..." }
log.error { "Error..." }
        Thread.sleep(5_000)

    }
}

class FirstScreen : KtxScreen {
    private val image = Texture("logo.png".toInternalFile(), true).apply { setFilter(Linear, Linear) }
    private val batch = SpriteBatch()

    override fun render(delta: Float) {
        clearScreen(red = 0.7f, green = 0.7f, blue = 0.7f)
        batch.use {
            it.draw(image, 100f, 160f)
        }
    }

    override fun dispose() {
        image.disposeSafely()
        batch.disposeSafely()
    }
}
