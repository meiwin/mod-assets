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

  lazy val assets: Option[{ def at(file: String): Call }] = {
    try {
      val routes = Class.forName("controllers.routes")
      val assetsField = routes.getDeclaredField("Assets")
      val assets = assetsField.get(routes).asInstanceOf[{ def at(file: String): Call }]
      assets.getClass.getMethod("at", classOf[java.lang.String])
      Option(assets)
    } catch {
      case e: Throwable => Option.empty
    }
  }

  lazy val assetsWithPath: Option[{ def at(path: String, file: String): Call }] = {
    try {
      val routes = Class.forName("controllers.routes")
      val assetsField = routes.getDeclaredField("Assets")
      val assets = assetsField.get(routes).asInstanceOf[{ def at(path: String, file: String): Call }]
      assets.getClass.getMethod("at", classOf[java.lang.String], classOf[String])
      Option(assets)
    } catch {
      case e: Throwable => Option.empty
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

  private[this] var counter = 0
  private[this] def url(baseUrl: String) = {
    counter = counter + 1
    if (CDNLength > 0) (if (CDNLength == 1) CDNs(0) else CDNs(counter % CDNLength)) + baseUrl
    else baseUrl
  }


  def at(file: String) = {

    val baseUrl = assets.map { o =>
      o.at(file).toString()
    }.getOrElse(throw new Exception("Please specify `path` parameter for `util.Assets.at(...)` method call."))
    url(baseUrl)

  }

  def at(path: String, file: String) = {

    val baseUrl =
      if (assetsWithPath.isDefined) {
        assetsWithPath.get.at(path, file).toString()
      } else {
        assets.get.at(file).toString()
      }
    url(baseUrl)

  }

}
