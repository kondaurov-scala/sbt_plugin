package com.github.kondaurovdev.plugin

import sbt.Keys._
import sbt.{Def, _}

object AkCommonPlugin extends AutoPlugin {

  override def requires = plugins.JvmPlugin

  override def trigger: PluginTrigger = allRequirements

  object autoImport {

    lazy val akCommonVcsPath: SettingKey[String] = settingKey[String]("Vcs repo uri")
    lazy val akCommonVcsType: SettingKey[String] = settingKey[String]("Vcs type")

  }

  import autoImport._

  override lazy val projectSettings: Seq[Def.Setting[_]] = Seq(
    scalaVersion := "2.12.2",
    scalacOptions ++= Seq("-deprecation"),
    akCommonVcsType := "git",
    resolvers ++= Seq(
      Resolver.sonatypeRepo("snapshots"),
      Resolver.bintrayRepo("kondaurovdev", "maven")
    )
  ) ++ CommonSettings.publishSettings

}
