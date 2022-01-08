package jade

import java.awt.event.KeyEvent

class LevelEditorScene extends Scene {

  private var changingScene = false
  private var timeToChangeScene = 2.0f

  println("Inside level editor scene")

  override def update(dt: Float): Unit = {
    if (!changingScene && KeyListener.isKeyPressed(KeyEvent.VK_SPACE)) {
      changingScene = true
    }

    if (changingScene && timeToChangeScene > 0) {
      timeToChangeScene -= dt
      Window.r -= dt * 5.0f
      Window.g -= dt * 5.0f
      Window.b -= dt * 5.0f
    } else if (changingScene) {
      Window.changeScene(1)
    }
  }
}
