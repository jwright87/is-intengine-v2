package uk.intenso.intenginev2.ecs

import com.badlogic.gdx.graphics.Texture

object IeInitSystem {

    fun initialLoad() {
        val T:Class<*> = AssetType.PNG.clazz()
        IeAssetSystem.loadGet("images/kotlin-logo.png", Texture.class) as
    }
}
