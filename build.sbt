libraryDependencies ++= Seq(
  "com.novocode" % "junit-interface" % "0.10-M2" % "test"
)

testOptions += Tests.Argument(TestFrameworks.JUnit, "-q", "-v")


