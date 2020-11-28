package Modules.ASCIIImage

class GrayscaleGrid(private val _data: Array[Array[Int]]) {
  def this(width: Int, height: Int) =
    this(Array.ofDim[Int](width, height))

  require(_data.length > 0, "Invalid image dimension!")

  val width: Int = _data.length

  val height: Int = _data(0).length

  def apply(x: Int): Array[Int] = _data(x)

  def getRawData: Array[Array[Int]] = _data.map(_.clone())
}
