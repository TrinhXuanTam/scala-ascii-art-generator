package Modules.AsciiFilterTests

import Core.Filters.InvertAsciiFilter
import Modules.AsciiImage.{AsciiImage, GrayscaleGrid}
import org.scalatest.FunSuite

class InvertAsciiFilterTest extends FunSuite {
  private val _image = new AsciiImage(
    new GrayscaleGrid(Array(Array(1, 100), Array(150, 200))))

  test("Invert test") {
    val invertFilter = new InvertAsciiFilter
    val res = invertFilter.transform(_image).getGrayScaleData

    assert(res(0)(0) == 254)
    assert(res(0)(1) == 155)
    assert(res(1)(0) == 105)
    assert(res(1)(1) == 55)
  }
}
