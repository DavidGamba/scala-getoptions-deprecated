name := "scala-getoptions"

version := "0.1.0"

organization := "com.github.DavidGamba"

homepage := Some(url("https://github.com/DavidGamba/scala-getoptions"))

licenses := Seq("MIT License" -> url("http://www.opensource.org/licenses/mit-license.php"))

description := """Scala option parser based on Perl's Getopt::Long"""

scalaVersion := "2.11.0"

crossScalaVersions := Seq("2.11.0")

libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.7"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.2"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.1.7" % "test"
