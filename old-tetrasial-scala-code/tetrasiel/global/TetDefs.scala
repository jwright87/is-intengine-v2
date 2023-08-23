package com.intensostudios.tetrasiel.global

import com.badlogic.gdx.graphics.g2d.TextureRegion

/**
  * Created by jwright on 24/07/16.
  */
object TetDefs {

  type SpriteSheet = Array[Array[TextureRegion]]


  //Used for data service
  sealed trait EntityType
  case object TileType extends EntityType
}
