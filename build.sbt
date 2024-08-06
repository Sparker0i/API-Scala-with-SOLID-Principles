ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.3"

lazy val root = (project in file("."))
  .settings(
    name := "API-Scala-with-SOLIDPrinciples",
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.client4" %% "core" % "4.0.0-M16",
      "com.lihaoyi" %% "upickle" % "3.3.1",
      "org.scalatest" %% "scalatest" % "3.2.19" % Test,
      "org.scalatest" %% "scalatest-funsuite" % "3.2.19" % Test,
    )
  )
