package util

object Time {
  val timeStarted: Float = System.nanoTime()

  def time: Float = ((System.nanoTime() - timeStarted) * 1E-9).toFloat
}
