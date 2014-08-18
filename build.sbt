organization := "com.gambaeng"

name := "scala-getoptions"

description := """Scala option parser based on Perl's Getopt::Long"""

scalaVersion := "2.11.0"

crossScalaVersions := Seq("2.11.0")

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.11" % "2.1.7" % "test",
  "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  "org.slf4j" % "slf4j-api" % "1.7.7",
  "ch.qos.logback" % "logback-classic" % "1.1.2"
)

version := "0.2.0"

licenses := Seq("MIT License" -> url("http://www.opensource.org/licenses/mit-license.php"))

homepage := Some(url("https://github.com/DavidGamba/scala-getoptions"))

scmInfo := Some(
  ScmInfo(
    browseUrl = url("http://github.com/DavidGamba/scala-getoptions"),
    connection = "scm:git:git@github.com:DavidGamba/scala-getoptions.git"
  )
)

publishMavenStyle := true

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
  <developers>
    <developer>
      <id>DavidGamba</id>
      <name>David Gamba</name>
      <url>http://gambaeng.com</url>
    </developer>
  </developers>
)
