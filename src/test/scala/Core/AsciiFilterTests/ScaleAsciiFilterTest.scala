package Core.AsciiFilterTests

import Core.Filters.ScaleAsciiFilter
import Modules.AsciiImage.{AsciiImage, GrayscaleGrid}
import org.scalatest.FunSuite

class ScaleAsciiFilterTest extends FunSuite {
  private val _image = new AsciiImage(
    new GrayscaleGrid(Array(Array(1, 100), Array(150, 200))))

  test("Invalid input test") {
    assertThrows[IllegalArgumentException](new ScaleAsciiFilter("0"))
    assertThrows[IllegalArgumentException](new ScaleAsciiFilter("3"))
    assertThrows[IllegalArgumentException](new ScaleAsciiFilter("0.1"))
  }

  test("Scale factor 1 test") {
    val scaleFilter = new ScaleAsciiFilter("1")
    val src = _image.getGrayScaleData
    val res = scaleFilter.transform(_image).getGrayScaleData

    assert(res.width == src.width)
    assert(res.height == src.height)

    assert(res(0)(0) == src(0)(0))
    assert(res(0)(1) == src(0)(1))
    assert(res(1)(0) == src(1)(0))
    assert(res(1)(1) == src(1)(1))
  }

  test("Scale factor 0.25 test") {
    val scaleFilter = new ScaleAsciiFilter("0.25")
    val src = _image.getGrayScaleData
    val res = scaleFilter.transform(_image).getGrayScaleData
    val average = (src(0)(0) + src(0)(1) + src(1)(0) + src(1)(1)) / 4

    assert(res.width == 1)
    assert(res.height == 1)

    assert(res(0)(0) == average)
  }

  test("Scale factor 4 test") {
    val scaleFilter = new ScaleAsciiFilter("4")
    val res = scaleFilter.transform(_image).getGrayScaleData

    assert(res.width == 4)
    assert(res.height == 4)

    // Top right corner
    assert(res(0)(0) == 1)
    assert(res(0)(1) == 1)
    assert(res(1)(0) == 1)
    assert(res(1)(1) == 1)

    // Top left corner
    assert(res(2)(0) == 150)
    assert(res(2)(1) == 150)
    assert(res(3)(0) == 150)
    assert(res(3)(1) == 150)

    // Bottom right corner
    assert(res(2)(2) == 200)
    assert(res(3)(2) == 200)
    assert(res(2)(3) == 200)
    assert(res(3)(3) == 200)

    // Bottom left corner
    assert(res(0)(2) == 100)
    assert(res(1)(2) == 100)
    assert(res(0)(3) == 100)
    assert(res(1)(3) == 100)
  }
}
