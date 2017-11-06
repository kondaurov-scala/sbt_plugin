package com.github.kondaurovdev

import sbt.Keys._
import sbt.{Def, _}
import bintray.BintrayKeys._
import com.github.kondaurovdev.AkCommonPlugin.autoImport.{akCommonVcsPath, akCommonVcsType}

object AkCommonPlugin extends AutoPlugin {

  override def requires = plugins.JvmPlugin

  override def trigger: PluginTrigger = allRequirements

  object autoImport {

    lazy val akCommonVcsPath: SettingKey[String] = settingKey[String]("Vcs repo uri")
    lazy val akCommonVcsType: SettingKey[String] = settingKey[String]("Vcs type")

  }

  def publishSettings: Seq[Def.Setting[_]] = Seq(
    organization := "com.github.kondaurovdev",
    publishTo := {
      if (isSnapshot.value) {
        Some("Sonatype Nexus Repository Manager" at "https://oss.sonatype.org/content/repositories/snapshots/")
      } else {
        publishTo.value
      }
    },
    bintrayRepository := "maven",
    publishArtifact in (Compile, packageDoc) := !isSnapshot.value,
    publishArtifact in (Test, packageDoc) := false,
    bintrayReleaseOnPublish := !isSnapshot.value,
    licenses += ("MIT", url("http://opensource.org/licenses/MIT")),
    credentials ++= Seq(
      Credentials(Path.userHome / ".ivy2" / ".sonatype")
    ),
    publishMavenStyle := true,
    pomExtra :=
      <url>${akCommonVcsPath.value}</url>
        <scm>
          <url>${akCommonVcsPath.value}.${akCommonVcsType.value}</url>
          <connection>${akCommonVcsPath.value}.${akCommonVcsType.value}</connection>
          <tag>${version.value}</tag>
        </scm>
        <developers>
          <developer>
            <id>kondaurovdev</id>
            <name>Alexander Kondaurov</name>
            <email>kondaurov.dev@gmail.com</email>
          </developer>
        </developers>,
    pomIncludeRepository := { _ => false }
  )

  import autoImport._

  override lazy val projectSettings: Seq[Def.Setting[_]] = Seq(
    scalaVersion := "2.12.2",
    scalacOptions ++= Seq("-deprecation"),
    akCommonVcsType := "git",
    resolvers ++= Seq(
      Resolver.sonatypeRepo("snapshots"),
      Resolver.bintrayRepo("kondaurovdev", "maven")
    )
  ) ++ publishSettings

}
