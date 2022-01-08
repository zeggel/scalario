name := "scalario"

version := "0.1"

scalaVersion := "2.13.7"

lazy val lwjglVersion = "3.3.0"
lazy val lwjglNatives = "natives-windows"
lazy val jomlVersion = "1.10.2"

libraryDependencies += "org.lwjgl" % "lwjgl" % lwjglVersion
libraryDependencies += "org.lwjgl" % "lwjgl-assimp" % lwjglVersion
libraryDependencies += "org.lwjgl" % "lwjgl-glfw" % lwjglVersion
libraryDependencies += "org.lwjgl" % "lwjgl-nfd" % lwjglVersion
libraryDependencies += "org.lwjgl" % "lwjgl-openal" % lwjglVersion
libraryDependencies += "org.lwjgl" % "lwjgl-opengl" % lwjglVersion
libraryDependencies += "org.lwjgl" % "lwjgl-stb" % lwjglVersion
libraryDependencies += "org.lwjgl" % "lwjgl" % lwjglVersion classifier lwjglNatives
libraryDependencies += "org.lwjgl" % "lwjgl-assimp" % lwjglVersion classifier lwjglNatives
libraryDependencies += "org.lwjgl" % "lwjgl-glfw" % lwjglVersion classifier lwjglNatives
libraryDependencies += "org.lwjgl" % "lwjgl-nfd" % lwjglVersion classifier lwjglNatives
libraryDependencies += "org.lwjgl" % "lwjgl-openal" % lwjglVersion classifier lwjglNatives
libraryDependencies += "org.lwjgl" % "lwjgl-opengl" % lwjglVersion classifier lwjglNatives
libraryDependencies += "org.lwjgl" % "lwjgl-stb" % lwjglVersion classifier lwjglNatives
libraryDependencies += "org.joml" % "joml" % jomlVersion
