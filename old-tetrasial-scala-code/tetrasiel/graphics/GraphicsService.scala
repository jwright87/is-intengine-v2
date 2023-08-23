package com.intensostudios.tetrasiel.graphics

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.{TextureAtlas, TextureRegion}
import com.intensostudios.core.exceptions.ISResourceException
import com.intensostudios.tetrasiel.global.TetDefs.SpriteSheet

import scala.collection.mutable

/**
  * Created by jwright on 24/07/16.
  */
class GraphicsService {

  lazy val manager = new AssetManager

  lazy val tiles = new mutable.LongMap[TextureRegion]

  lazy val spriteSheets = new mutable.HashMap[String, SpriteSheet]

  lazy val textures = new mutable.HashMap[String, TextureRegion]


  def load(packNames: Seq[String]): Unit = {
    try {
      packNames.foreach(atlas => {
        manager.load(s"packs/$atlas.pack", classOf[TextureAtlas])
      })
    } catch {
      case e: Exception => throw new ISResourceException("Unable to load Texture Pack", e)
    }
  }

  def prepareTiles(packName: String, tileNames: Map[Int, String]): Unit = {
    try {
      val atlas = manager.get[TextureAtlas](s"packs/$packName.pack")

      tileNames.foreach(t => {
        tiles.put(t._1, findAndFlip(atlas, t._2))
      })
    } catch {
      case e: Exception => throw new ISResourceException("Unable to load Texture Pack", e)
    }
  }

  def prepareSprites(packName: String, sprites: Map[String, String]): Unit = {
    try {
      val atlas = manager.get[TextureAtlas](s"packs/$packName.pack")
//      spriteSheets.put()
    } catch {
      case e: Exception => throw new ISResourceException("Unable to load Texture Pack", e)
    }
  }

  private def findAndFlipSheet(atlas: TextureAtlas, name: String, xFrames: Int, yFrames: Int): SpriteSheet = {
    val region = atlas.findRegion(name)
    val split = region.split(region.packedWidth / xFrames, region.packedHeight / yFrames)
    split.foreach(s => s.foreach(_.flip(false, true)))
    split
  }

  /**
    * Finds the region and flips it so y0 is top right.
    *
    * @param atlas
    * @param name
    * @return
    */
  private def findAndFlip(atlas: TextureAtlas, name: String): TextureRegion = {
    val region = atlas.findRegion(name)
    if (region == null) throw new ISResourceException(s"Can't find region: $name")
    region.flip(false, true)
    region
  }

}
