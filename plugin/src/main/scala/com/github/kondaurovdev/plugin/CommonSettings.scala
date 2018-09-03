package com.github.kondaurovdev.plugin

import bintray.BintrayKeys.{bintrayReleaseOnPublish, bintrayRepository}
import sbt.Keys._
import sbt._

object CommonSettings {

  import AkCommonPlugin.autoImport._

  def publishSettings: Seq[Def.Setting[_]] = Seq(
    organization := "com.github.kondaurovdev",
    publishTo := {
      val v = publishTo.value
      if (isSnapshot.value) {
        Some("Sonatype Nexus Repository Manager" at "https://oss.sonatype.org/content/repositories/snapshots/")
      } else {
        v
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

}
