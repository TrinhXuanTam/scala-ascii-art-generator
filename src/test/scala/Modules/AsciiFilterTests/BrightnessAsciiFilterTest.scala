package Modules.AsciiFilterTests

import Core.Filters.BrightnessAsciiFilter
import Modules.AsciiImage.{AsciiImage, GrayscaleGrid}
import org.mockito.MockitoSugar
import org.scalatest.FunSuite

class BrightnessAsciiFilterTest extends FunSuite {
  private val _image = new AsciiImage(
    new GrayscaleGrid(Array(Array(1, 100), Array(150, 200))))

  test("Positive brightness test") {
    val filter = new BrightnessAsciiFilter("100")
    val res = filter.transform(_image).getGrayScaleData
    assert(res(0)(0) == 101)
    assert(res(0)(1) == 200)
    assert(res(1)(0) == 250)
    assert(res(1)(1) == 255)
  }

  test("Negative brightness test") {
    val filter = new BrightnessAsciiFilter("-50")
    val res = filter.transform(_image).getGrayScaleData
    assert(res(0)(0) == 0)
    assert(res(0)(1) == 50)
    assert(res(1)(0) == 100)
    assert(res(1)(1) == 150)
  }

  test("Zero brightness test") {
    val filter = new BrightnessAsciiFilter("0")
    val src = _image.getGrayScaleData
    val res = filter.transform(_image).getGrayScaleData
    assert(res(0)(0) == src(0)(0))
    assert(res(0)(1) == src(0)(1))
    assert(res(1)(0) == src(1)(0))
    assert(res(1)(1) == src(1)(1))
  }
}
