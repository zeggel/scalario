package jade

import org.lwjgl.glfw.GLFW._

object KeyListener {
  private var keyPressed: Map[Int, Boolean] = Map.empty

  def keyCallback(window: Long, key: Int, scancode: Int, action: Int, mods: Int): Unit = {
    if (action == GLFW_PRESS) {
      keyPressed += key -> true
    } else if (action == GLFW_RELEASE) {
      keyPressed += key -> false
    }
  }

  def isKeyPressed(keyCode: Int): Boolean = keyPressed.getOrElse(keyCode, false)
}
