package jade

import org.lwjgl.glfw.GLFW._

object MouseListener {
  private var scrollXd = 0.0
  private var scrollYd = 0.0
  private var xPos = 0.0
  private var yPos = 0.0
  private var lastX = 0.0
  private var lastY = 0.0
  var isDragging = false
  private var mouseButtonPressed: Map[Int, Boolean] = Map.empty

  def mousePosCallback(window: Long, xpos: Double, ypos: Double): Unit = {
    lastX = xPos
    lastY = yPos
    xPos = xpos
    yPos = ypos
    isDragging = mouseButtonPressed.values.fold(false)(_ || _)
  }

  def mouseButtonCallback(window: Long, button: Int, action: Int, mods: Int): Unit = {
    if (action == GLFW_PRESS) {
      if (button < 3) {
        mouseButtonPressed += button -> true
      }
    } else if (action == GLFW_RELEASE) {
      mouseButtonPressed += button -> false
      isDragging = false
    }
  }

  def mouseScrollCallback(window: Long, xOffset: Double, yOffset: Double): Unit = {
    scrollXd = xOffset
    scrollYd = yOffset
  }

  def endFrame() = {
    scrollXd = 0;
    scrollYd = 0
    lastX = xPos
    lastY = yPos
  }

  def x: Float = xPos.toFloat
  def y: Float = yPos.toFloat
  def dx: Float = (lastX - xPos).toFloat
  def dy: Float = (lastY - yPos).toFloat
  def scrollX: Float = scrollXd.toFloat
  def scrollY: Float = scrollYd.toFloat
  def mouseButtonDown(button: Int): Boolean = mouseButtonPressed.getOrElse(button, false)
}
