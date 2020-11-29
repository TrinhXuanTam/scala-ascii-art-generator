package Modules.AsciiImageTests

import Modules.AsciiImage.GrayscaleGrid
import org.scalatest.FunSuite

class GrayscaleGridTest extends FunSuite {
  private val _data = Array(Array(1, 2), Array(3, 4), Array(5, 6))

  test("Getter test") {
    val grayscaleGrid = new GrayscaleGrid(_data)
    assert(grayscaleGrid.width == 3)
    assert(grayscaleGrid.height == 2)
  }

  test("Invalid input data tests") {
    // Illegal height
    assertThrows[IllegalArgumentException](new GrayscaleGrid(Array(Array())))

    // Illegal width
    assertThrows[IllegalArgumentException](new GrayscaleGrid(Array()))

    // Invalid dimensions
    assertThrows[IllegalArgumentException](
      new GrayscaleGrid(Array(Array(1), Array(2, 3))))
  }

  test("Index operator test") {
    val grayscaleGrid = new GrayscaleGrid(_data)
    assert(grayscaleGrid(0)(0) == 1)
    assert(grayscaleGrid(0)(1) == 2)
    assert(grayscaleGrid(1)(0) == 3)
    assert(grayscaleGrid(1)(1) == 4)
    assert(grayscaleGrid(2)(0) == 5)
    assert(grayscaleGrid(2)(1) == 6)
  }

  test("Raw data immutability test") {
    val grayscaleGrid = new GrayscaleGrid(_data)
    val rawData = grayscaleGrid.getRawData
    rawData(0)(0) = 99

    assert(grayscaleGrid(0)(0) == 1 && rawData(0)(0) == 99)
  }

  test("Empty grid test") {
    val grayscaleGrid = new GrayscaleGrid(1, 2)
    assert(grayscaleGrid.width == 1)
    assert(grayscaleGrid.height == 2)
  }
}
