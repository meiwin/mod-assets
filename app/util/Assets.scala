package util

import play.api.Play
import play.api.mvc.Call
import play.api.Play.current
import scala.util.Random

/**
 * Created with IntelliJ IDEA.
 * User: meiwinfu
 * Date: 4/11/12
 * Time: 1:02 PM
 * To change this template use File | Settings | File Templates.
 */
object Assets {

  val CDNS_KEY = "cdns"

  lazy val assets = {
    try {
      val routes = Class.forName("controllers.routes")
      val assetsField = routes.getDeclaredField("Assets")
      val assets = assetsField.get(routes).asInstanceOf[{ def at(file: String): Call }]
      assets
    } catch {
      case e: Throwable => throw e
    }
  }

  lazy val CDNs: List[String] = {
    if (Play.configuration.keys.contains(CDNS_KEY)) {
      val l = Play.configuration.underlying.getStringList(CDNS_KEY)
      List(l.toArray(new Array[String](l.size())) : _*)
    }
    else List[String]()
  }

  lazy val CDNLength = CDNs.size

  def at(file: String) = {

    val baseUrl = assets.at(file).toString()
    val url =
      if (CDNLength > 0) CDNs(Random.nextInt(CDNLength)) + baseUrl
      else baseUrl
    url
  }

}
