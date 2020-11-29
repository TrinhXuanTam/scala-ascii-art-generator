package Modules.AsciiImageTests

import java.util

import Modules.AsciiImage.{AsciiImage, GrayscaleGrid}
import org.mockito.MockitoSugar
import org.scalatest.FunSuite

class AsciiImageTest extends FunSuite with MockitoSugar {
  private val _grayscaleGridMock = mock[GrayscaleGrid]

  // Test all grayscale values
  test("Grayscale to ASCII art test") {
    when(_grayscaleGridMock.getRawData)
      .thenReturn(Array(Array(1, 26, 51, 76, 101, 126, 151, 176, 201, 226)))

    val asciiImage = new AsciiImage(_grayscaleGridMock)

    assert(
      asciiImage.getArt(0) sameElements Array('@', '%', '#', '*', '+', '=', '-',
        ':', '.', ' '))
  }
}
