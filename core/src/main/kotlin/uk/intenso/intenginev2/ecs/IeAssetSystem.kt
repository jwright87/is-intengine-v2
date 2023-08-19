package uk.intenso.intenginev2.ecs

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.assets.AssetManager

object IeAssetSystem {


    private val mgr = AssetManager()

    fun load(assetPath: String?, assetClass: Class<*>?) {
        mgr.load(assetPath, assetClass) // fileName with extension, sameName will use to get from manager
        mgr.finishLoading() //or use update() inside render() method
    }

    operator fun <T> get(name: String?, assetClass: Class<T>?): T {
        return mgr.get(name, assetClass)
    }


    /**
     * //        FileHandleResolver resolver = new InternalFileHandleResolver();
     * //        manager.setLoader(.class, new FreeTypeFontGeneratorLoader(resolver));
     * //        manager.setLoader(BitmapFont.class, ".fnt", new FreetypeFontLoader(resolver));
     * //        FreetypeFontLoader.FreeTypeFontLoaderParameter parms = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
     * //        parms.fontFileName = path;    // path of .ttf file where that exist
     * //        parms.fontParameters.size = 10;
     * @param name
     * @return
     */
    fun <T> loadAndGet(assetType: String, name: String, assetClass: Class<T>): T {
        mgr.load(AssetDescriptor<T>("fonts/" + name, assetClass))
        mgr.finishLoading()
        return mgr.get(name)
    }

    fun load(name: String, clazz: Class<*>) {
//        val descriptor = asset.descriptor(name)
        mgr.load(name, clazz)
//        mgr.load(descriptor)
        mgr.finishLoading()
    }

    fun <T> get(name: String): T {
        return mgr.get<T>(name)
//        return mgr.get(asset.descriptor(name,asset)) as T
    }

    fun <T> loadGet(name: String, clazz: Class<*>): T {
        load(name, clazz)
        mgr.finishLoading()
        return get(name)
    }

}
