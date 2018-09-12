package com.github.kondaurovdev.plugin

import sbt.{AutoPlugin, PluginTrigger}
import sbtprotoc.ProtocPlugin
import sbtprotoc.ProtocPlugin.autoImport.PB

import sbt.Keys._
import sbt.{Def, _}

object SbtScalapbCompilerPlugin extends AutoPlugin {

  override def requires = ProtocPlugin

  override def trigger: PluginTrigger = allRequirements

  override def projectSettings: Seq[Def.Setting[_]] = super.projectSettings ++ Seq(

    PB.targets in Compile := Seq(
      scalapb.gen(flatPackage = true) -> (sourceManaged in Compile).value
    )

  )
}
