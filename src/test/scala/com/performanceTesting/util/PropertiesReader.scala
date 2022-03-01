package com.performanceTesting.util

import com.typesafe.config.ConfigFactory

object PropertiesReader {
  def getProperty(name: String): String = {

    var env = System.getProperty("environment")

    if (env != null) {
      env = "-" + env
    }
    else {
      env = ""
    }

    val filepath = "config/"
    print("file path: "+ filepath)
    val config = ConfigFactory.load(filepath + "config" + env + ".properties")
    config.getString(name)
  }


}
