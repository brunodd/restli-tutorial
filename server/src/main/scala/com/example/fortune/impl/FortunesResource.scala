package com.example.fortune.impl

import com.example.fortune.Fortune
import com.linkedin.restli.server.annotations.RestLiCollection
import com.linkedin.restli.server.resources.CollectionResourceTemplate

@RestLiCollection(name="fortunes", namespace = "com.example.fortune")
class FortunesResource extends CollectionResourceTemplate[java.lang.Long, Fortune] {

  /**
   * A sample scala get.
   */
  override def get(key: java.lang.Long): Fortune = {
    val message = "hello world"

    new Fortune()
      .setFortune(s"You got this from a Scala Resource: ${message}!")
  }
}