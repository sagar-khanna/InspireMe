name := "InspireMe"

version := "1.0-SNAPSHOT"

resolvers ++= Seq(
  "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository",
  "central" at "http://artifactory-eu1.travelsupermarket.com/artifactory/all-releases",
  "snapshots" at "http://artifactory-eu1.travelsupermarket.com/artifactory/all-snapshot",
  "features" at "http://artifactory-eu1.travelsupermarket.com/artifactory/all-features",
  "release-candidates" at "http://artifactory-eu1.travelsupermarket.com/artifactory/all-release-candidates"
)

resolvers += Resolver.url("Objectify Play Repository", url("http://schaloner.github.io/releases/"))(Resolver.ivyStylePatterns)


libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
    "com.clever-age"          % "play2-elasticsearch"       % "0.8.1"
)     

play.Project.playJavaSettings
