@file:JvmName("TeaVMLauncher")

package uk.intenso.intenginev2.teavm

import com.github.xpenatan.gdx.backends.teavm.TeaApplicationConfiguration
import com.github.xpenatan.gdx.backends.teavm.TeaApplication
import uk.intenso.intenginev2.init.IeMain

/** Launches the TeaVM/HTML application. */
fun main() {
    val config = TeaApplicationConfiguration("canvas").apply {
        width = 640
        height = 480
    }
    TeaApplication(IeMain(), config)
}
