package jade

import org.lwjgl.Version
import org.lwjgl.glfw.Callbacks.glfwFreeCallbacks
import org.lwjgl.glfw.GLFW.{GLFW_FALSE, GLFW_KEY_SPACE, GLFW_MAXIMIZED, GLFW_RESIZABLE, GLFW_TRUE, GLFW_VISIBLE, glfwCreateWindow, glfwDefaultWindowHints, glfwDestroyWindow, glfwInit, glfwMakeContextCurrent, glfwPollEvents, glfwSetCursorPosCallback, glfwSetErrorCallback, glfwSetKeyCallback, glfwSetMouseButtonCallback, glfwSetScrollCallback, glfwShowWindow, glfwSwapBuffers, glfwSwapInterval, glfwTerminate, glfwWindowHint, glfwWindowShouldClose}
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11.{GL_COLOR_BUFFER_BIT, glClear, glClearColor}
import util.Time

object Window {
  val width = 720
  val height = 480
  val title = "Mario"

  var glfwWindow: Option[java.lang.Long] = None

  var currentScene: Scene = new LevelEditorScene()

  var r: Float = 1
  var g: Float = 1
  var b: Float = 1
  private var a: Float = 1
  private var fadeToBlack = false

  def changeScene(newScene: Int): Unit = newScene match {
    case 0 => currentScene = new LevelEditorScene()
    case 1 => currentScene = new LevelScene()
    case _ => assert(assertion = false, s"Unknown scene '$newScene'")
  }

  def run(): Unit = {
    println(s"Hello LWJGL ${Version.getVersion}!")

    init()
    loop()

    // Free the memory
    glfwFreeCallbacks(glfwWindow.get)
    glfwDestroyWindow(glfwWindow.get)

    // Terminate GLFW and the free the error callback
    glfwTerminate()
    glfwSetErrorCallback(null).free()
  }

  def init(): Unit = {
    // Setup an error callback
    GLFWErrorCallback.createPrint(System.err).set

    // Initialize GLFW
    if (!glfwInit()) {
      throw new IllegalStateException("Unable to initialize GLFW.")
    }

    // Configure GLFW
    glfwDefaultWindowHints()
    glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)
    glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE)
    glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE)

    // Create the window
    glfwWindow = Option(glfwCreateWindow(width, height, title, null.asInstanceOf[java.lang.Long], null.asInstanceOf[java.lang.Long]))
    if (glfwWindow.isEmpty) {
      throw new IllegalStateException("Failed to create the GLFW window.")
    }

    glfwSetCursorPosCallback(glfwWindow.get, MouseListener.mousePosCallback)
    glfwSetMouseButtonCallback(glfwWindow.get, MouseListener.mouseButtonCallback)
    glfwSetScrollCallback(glfwWindow.get, MouseListener.mouseScrollCallback)
    glfwSetKeyCallback(glfwWindow.get, KeyListener.keyCallback)

    // Make the OpenGL context current
    glfwMakeContextCurrent(glfwWindow.get)
    // Enable v-sync
    glfwSwapInterval(1)

    // Make the window visible
    glfwShowWindow(glfwWindow.get)

    // This line is critical for LWJGL's interoperation with GLFW's
    // OpenGL context, or any context that is managed externally.
    // LWJGL detects the context that is current in the current thread,
    // creates the GLCapabilities instance and makes the OpenGL
    // bindings available for use.
    GL.createCapabilities()

    changeScene(0)
  }

  def loop(): Unit = {
    var beginTime = Time.time
    var endTime = Time.time
    var dt = -1.0f

    while (!glfwWindowShouldClose(glfwWindow.get)) {
      // Poll events
      glfwPollEvents()

      glClearColor(r, g, b, a)
      glClear(GL_COLOR_BUFFER_BIT)

      if (dt >= 0) {
        currentScene.update(dt)
      }

      glfwSwapBuffers(glfwWindow.get)

      endTime = Time.time
      dt = endTime - beginTime
      beginTime = endTime
    }
  }

}
