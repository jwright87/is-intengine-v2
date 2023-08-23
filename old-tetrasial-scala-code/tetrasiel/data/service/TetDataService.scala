package com.intensostudios.tetrasiel.data.service

import argonaut.Argonaut._
import com.badlogic.gdx.Gdx

/**
  * Created by jwright on 24/07/16.
  */
class TetDataService {


  def load(): Unit = {

    val tileJson:String = Gdx.files.internal("json/tiles.json").readString()
    val tileList = tileJson.decodeOption[List[String]].get
  }

}
