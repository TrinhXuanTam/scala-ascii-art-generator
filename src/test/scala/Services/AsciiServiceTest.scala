package Services

import Modules.AsciiImage.{AsciiImage, GrayscaleGrid, IAsciiFilter}
import org.mockito.ArgumentMatchers.any
import org.mockito.MockitoSugar
import org.scalatest.FunSuite

class AsciiServiceTest extends FunSuite with MockitoSugar {
  test("Filters test") {
    // Mock filters
    val filter1Mock = mock[IAsciiFilter]
    val filter2Mock = mock[IAsciiFilter]
    val src = new AsciiImage(new GrayscaleGrid(Array(Array(0))))

    when(filter1Mock.transform(any()))
      .thenReturn(new AsciiImage(new GrayscaleGrid(Array(Array(11)))))
    when(filter2Mock.transform(any()))
      .thenReturn(new AsciiImage(new GrayscaleGrid(Array(Array(33)))))

    val filters1 = Seq(filter1Mock)
    val filters2 = Seq(filter1Mock, filter2Mock)
    val asciiService = new AsciiService
    val res1 = asciiService.applyFilters(filters1, src)
    val res2 = asciiService.applyFilters(filters2, src)

    // One filter test
    assert(res1.width == 1)
    assert(res1.height == 1)
    assert(res1.getGrayScaleData(0)(0) == 11)

    // Filter chaining test
    assert(res2.width == 1)
    assert(res2.height == 1)
    assert(res2.getGrayScaleData(0)(0) == 33)
  }
}
