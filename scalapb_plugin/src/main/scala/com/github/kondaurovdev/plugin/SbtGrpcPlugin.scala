package com.github.kondaurovdev.plugin

import sbt.{AutoPlugin, PluginTrigger}
import sbt.Keys._
import sbt.{Def, _}

object SbtGrpcPlugin extends AutoPlugin {

  override def requires = SbtScalapbCompilerPlugin

  override def trigger: PluginTrigger = allRequirements

  override def projectSettings: Seq[Def.Setting[_]] = super.projectSettings ++ Seq(

    libraryDependencies ++= Seq(
      "io.grpc" % "grpc-netty" % scalapb.compiler.Version.grpcJavaVersion,
      "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion
    )

  )

}
