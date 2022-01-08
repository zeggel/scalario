package jade

import org.lwjgl.Version
import org.lwjgl.glfw.Callbacks.glfwFreeCallbacks
import org.lwjgl.glfw.GLFW.{GLFW_FALSE, GLFW_MAXIMIZED, GLFW_RESIZABLE, GLFW_TRUE, GLFW_VISIBLE, glfwCreateWindow, glfwDefaultWindowHints, glfwDestroyWindow, glfwInit, glfwMakeContextCurrent, glfwPollEvents, glfwSetErrorCallback, glfwShowWindow, glfwSwapBuffers, glfwSwapInterval, glfwTerminate, glfwWindowHint, glfwWindowShouldClose}
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11.{GL_COLOR_BUFFER_BIT, glClear, glClearColor}

object Window {
  val width = 720
  val height = 480
  val title = "Mario"

  var glfwWindow: Option[java.lang.Long] = None

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
  }

  def loop(): Unit = {
    while (!glfwWindowShouldClose(glfwWindow.get)) {
      // Poll events
      glfwPollEvents()

      glClearColor(1.0f, 1.0f, 1.0f, 1.0f)
      glClear(GL_COLOR_BUFFER_BIT)

      glfwSwapBuffers(glfwWindow.get)
    }
  }

}
