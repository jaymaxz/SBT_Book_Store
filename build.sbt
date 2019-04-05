name := "SBT_Book_Store"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies +=  "org.scalaj" %% "scalaj-http" % "2.4.1"
libraryDependencies += "com.googlecode.json-simple" % "json-simple" % "1.1.1"
libraryDependencies += "com.google.code.gson" % "gson" % "2.2.2"
// https://mvnrepository.com/artifact/com.rabbitmq/amqp-client
libraryDependencies += "com.rabbitmq" % "amqp-client" % "5.0.0"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.5"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"