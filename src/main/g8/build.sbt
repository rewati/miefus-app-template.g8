organization := "com.ytel"

name := "$name$"

version := "$version$"

scalaVersion := "$scala_version$"

val repoResolvers = new {
  val maven = "Maven Repository" at "http://mvnrepository.com/artifact"
  val mavenCental = "Maven Central" at "https://repo1.maven.org/maven2"
  val ytelInternal = "Ytel internal" at "http://artifactsrv.ytel.com:8080/repository/internal/"
  val ytelSnapShot = "Ytel internal" at "http://artifactsrv.ytel.com:8080/repository/snapshots/"
  val common = Seq(maven,mavenCental,ytelInternal,ytelSnapShot)
}

resolvers ++= repoResolvers.common
libraryDependencies += "com.ytel" %% "miefus" % "$miefus_version$"

assemblyMergeStrategy in assembly := {
  case "reference.conf" => MergeStrategy.concat
  case x if x.contains(".properties") => MergeStrategy.last
  case PathList("org.slf4j", "impl", "versions.properties", xs @ _*) => MergeStrategy.first
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

mainClass in assembly := Some("com.ytel.$name$.Application")
