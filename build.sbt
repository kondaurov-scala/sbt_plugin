
organization in ThisBuild := "com.github.kondaurovdev"

val vcsUri = "github.com/kondaurov-scala/sbt_plugin.git"

val commonSettings = Seq(
  publishTo := {
    val v = publishTo.value
    if (isSnapshot.value)
      Some("Sonatype Nexus Repository Manager" at "https://oss.sonatype.org/content/repositories/snapshots/")
    else
      v
  },
  bintrayRepository := "maven",
  licenses += ("MIT", url("http://opensource.org/licenses/MIT")),
  bintrayVcsUrl := Some(s"https://$vcsUri"),
  bintrayReleaseOnPublish := !isSnapshot.value,
  publishArtifact in (Compile, packageDoc) := !isSnapshot.value,
  publishArtifact in (Test, packageDoc) := false,
  credentials ++= Seq(
    Credentials(Path.userHome / ".ivy2" / ".sonatype")
  ),
  publishMavenStyle := true,
  pomExtra :=
    <url>https://{vcsUri}</url>
    <scm>
      <url>https://{vcsUri}</url>
      <connection>scv:git:git@{vcsUri}</connection>
      <tag>{version.value}</tag>
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

lazy val publish_plugin = (project in file("publish_plugin"))
  .settings(
    version := "0.0.1",
    name := "publish_plugin",
    sbtPlugin := true,
    addSbtPlugin("org.foundweekends" % "sbt-bintray" % "0.5.1"),
    addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.5.1"),
    addSbtPlugin("org.scoverage" % "sbt-coveralls" % "1.2.6"),
  )
  .settings(commonSettings: _*)

lazy val scalapb_plugin = (project in file("scalapb_plugin"))
  .settings(commonSettings: _*)
  .settings(
    version := "0.0.1",
    name := "scalapb_plugin",
    sbtPlugin := true,
    addSbtPlugin("com.thesamet" % "sbt-protoc" % "0.99.18"),
    libraryDependencies ++= Seq(
      "com.thesamet.scalapb" %% "compilerplugin" % "0.8.0-RC1"
    )
  )

lazy val packager_plugin = (project in file("packager_plugin"))
  .settings(commonSettings: _*)
  .settings(
    version := "0.0.1",
    name := "packager_plugin",
    sbtPlugin := true,
    addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.3.6")
  )

publish := {}
publishLocal := {}
