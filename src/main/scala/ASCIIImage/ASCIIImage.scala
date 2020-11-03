package ASCIIImage

trait ASCIIImage {
  val _data: Array[Array[Int]]

  val width: Int

  val height: Int

  def getData: Array[Array[Int]] = _data.map(_.clone())
}