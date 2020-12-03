package Core.AsciiFilterTests

import Core.Filters.RotateAsciiFilter
import Modules.AsciiImage.{AsciiImage, GrayscaleGrid}
import org.scalatest.FunSuite

class RotateAsciiFilterTest extends FunSuite {
  private val _image = new AsciiImage(
    new GrayscaleGrid(Array(Array(1, 100), Array(150, 200))))

  test("Invalid input test") {
    assertThrows[IllegalArgumentException](new RotateAsciiFilter("91"))
    assertThrows[IllegalArgumentException](new RotateAsciiFilter("1"))
    assertThrows[IllegalArgumentException](new RotateAsciiFilter("150"))
    assertThrows[IllegalArgumentException](new RotateAsciiFilter("250"))
    assertThrows[IllegalArgumentException](new RotateAsciiFilter("invalid"))
  }

  test("Clockwise rotation test") {
    val rotateFilter = new RotateAsciiFilter("90")
    val res = rotateFilter.transform(_image).getGrayScaleData

    assert(res(0)(0) == 100)
    assert(res(0)(1) == 200)
    assert(res(1)(0) == 1)
    assert(res(1)(1) == 150)
  }

  test("Counterclockwise rotation test") {
    val rotateFilter = new RotateAsciiFilter("-90")

    val res = rotateFilter.transform(_image).getGrayScaleData

    assert(res(0)(0) == 150)
    assert(res(0)(1) == 1)
    assert(res(1)(0) == 200)
    assert(res(1)(1) == 100)
  }
}
