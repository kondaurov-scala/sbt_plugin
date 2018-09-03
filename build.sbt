
lazy val plugin = (project in file("plugin"))
  .settings(
    version := "0.0.1-SNAPSHOT",
    name := "sbt-plugin",
    organization := "com.github.kondaurovdev",
    sbtPlugin := true,
    publishTo := {
      val v = publishTo.value
      if (isSnapshot.value)
        Some("Sonatype Nexus Repository Manager" at "https://oss.sonatype.org/content/repositories/snapshots/")
      else
        v
    },
    bintrayRepository := "maven",
    publishArtifact in (Compile, packageDoc) := !isSnapshot.value,
    publishArtifact in (Test, packageDoc) := false,
    licenses += ("MIT", url("http://opensource.org/licenses/MIT")),
    bintrayReleaseOnPublish := !isSnapshot.value,
    addSbtPlugin("org.foundweekends" % "sbt-bintray" % "0.5.1"),
    addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.5.1"),
    addSbtPlugin("org.scoverage" % "sbt-coveralls" % "1.2.6"),
    credentials ++= Seq(
      Credentials(Path.userHome / ".ivy2" / ".sonatype")
    ),
    publishMavenStyle := true,
    pomExtra :=
      <url>https://github.com/kondaurov-scala/sbt_plugin</url>
        <scm>
          <url>https://github.com/kondaurov-scala/sbt_plugin.git</url>
          <connection>https://github.com/kondaurov-scala/sbt_plugin.=</connection>
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