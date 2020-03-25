package com.example.fortune.impl

import com.example.fortune.Fortune
import com.linkedin.restli.server.annotations.RestLiCollection
import com.linkedin.restli.server.resources.CollectionResourceTemplate

@RestLiCollection(name="fortunes", namespace = "com.example.fortune")
class FortunesResource extends CollectionResourceTemplate[java.lang.Long, Fortune] {

  val db = Map(
    1L → "Enjoy life!",
    2L → "Today is your lucky day.",
    3L → "There's no time like the present."
  )

  /**
   * A sample scala get.
   */
  override def get(key: java.lang.Long): Fortune = {
    val message = db.get(key.longValue()) match {
      case Some(m) ⇒ m
      case None ⇒ s"Your luck has run out. No fortune for id = $key"
    }
    new Fortune()
      .setFortune(s"You got this from a Scala Resource: ${message}!")
  }
}