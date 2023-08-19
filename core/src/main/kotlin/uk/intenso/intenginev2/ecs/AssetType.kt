package uk.intenso.intenginev2.ecs;

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont


enum class AssetType(val dir: String, val clazz: Class<*>) {
    FONT("fonts", BitmapFont::class.java),
    PNG("images", Texture::class.java)
    ;

    fun clazz() = clazz

    fun <T> descriptor(name: String,assetType:AssetType):T {
        val T = assetType.clazz
        return  AssetDescriptor("${assetType.dir}/$name", assetType.clazz) as T

    }
}
