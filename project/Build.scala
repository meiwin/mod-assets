import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "mod-assets"
    val appVersion      = "1.0.0"

    val appDependencies = Seq(
      // Add your project dependencies here,
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      // Add your own project settings here
      organization := "blockthirty"
    , publishTo <<= version { (v: String) =>
        if (v.trim.endsWith("SNAPSHOT"))
          Some(Resolver.file("file",  new File(Path.userHome.absolutePath+"/Projects/Maven2/m2repo/snapshots")))
        else
          Some(Resolver.file("file",  new File(Path.userHome.absolutePath+"/Projects/Maven2/m2repo/releases")))
      }
    )
}
