logLevel := Level.Warn

val InterplayVersion = sys.props.get("interplay.version").getOrElse("3.0.5")

addSbtPlugin("com.typesafe.play" % "interplay" % InterplayVersion)
addSbtPlugin("com.github.sbt" % "sbt-release" % "1.1.0")
addSbtPlugin("org.scalariform" % "sbt-scalariform" % "1.8.3")
addSbtPlugin("de.heikoseeberger" % "sbt-header" % "5.6.5")
