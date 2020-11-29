package Modules.AsciiFilterTests

import Core.Filters.FlipAsciiFilter
import Modules.AsciiImage.{AsciiImage, GrayscaleGrid}
import org.scalatest.FunSuite

class FlipAsciiFilterTest extends FunSuite {
  private val _image = new AsciiImage(
    new GrayscaleGrid(Array(Array(1, 100), Array(150, 200))))

  test("X axis test") {
    val flipFilter = new FlipAsciiFilter("x")
    val flipFilter2 = new FlipAsciiFilter("X")

    val res = flipFilter.transform(_image).getGrayScaleData
    val res2 = flipFilter2.transform(_image).getGrayScaleData

    assert(res(0)(0) == 150)
    assert(res(0)(1) == 200)
    assert(res(1)(0) == 1)
    assert(res(1)(1) == 100)

    assert(res2(0)(0) == 150)
    assert(res2(0)(1) == 200)
    assert(res2(1)(0) == 1)
    assert(res2(1)(1) == 100)
  }

  test("Y axis test") {
    val flipFilter = new FlipAsciiFilter("y")
    val flipFilter2 = new FlipAsciiFilter("Y")
    val res = flipFilter.transform(_image).getGrayScaleData
    val res2 = flipFilter2.transform(_image).getGrayScaleData

    assert(res(0)(0) == 100)
    assert(res(0)(1) == 1)
    assert(res(1)(0) == 200)
    assert(res(1)(1) == 150)

    assert(res2(0)(0) == 100)
    assert(res2(0)(1) == 1)
    assert(res2(1)(0) == 200)
    assert(res2(1)(1) == 150)
  }

  test("Invalid input test") {
    assertThrows[IllegalArgumentException](new FlipAsciiFilter("A"))
  }
}
