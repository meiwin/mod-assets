import sbt._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "mod-assets"
    val appVersion      = "1.0.0"

    val appDependencies = Seq(
      // Add your project dependencies here,
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      // Add your own project settings here      
    )

}
