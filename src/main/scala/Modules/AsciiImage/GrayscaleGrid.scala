package Modules.AsciiImage

// 2D collection of grayscale values
class GrayscaleGrid(private val _data: Array[Array[Int]]) {
  def this(width: Int, height: Int) =
    this(Array.ofDim[Int](width, height))

  // The grid must have at least 1 row and 1 column and must be same size
  require(
    !_data.isEmpty && !_data(0).isEmpty && _data.forall(el =>
      el.length == _data(0).length),
    "Invalid image dimension!")

  val width: Int = _data.length

  val height: Int = _data(0).length

  // Index operator
  def apply(x: Int): Array[Int] = _data(x)

  // Get a copy of the raw data
  def getRawData: Array[Array[Int]] = _data.map(_.clone())
}
