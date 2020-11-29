package ControllerTests

import java.io.File
import java.nio.file.Files

import Controllers.ASCIIArtGenerator
import org.scalatest.FunSuite

import scala.io.Source.fromFile

class ASCIIArtGeneratorTest extends FunSuite {
  val ASCIIArtGenerator = new ASCIIArtGenerator

  test("Image to ASCII art without filters test") {
    // Image file with one pixel only and RGB of 199, 47, 47
    val singlePixelImageFile = new File(
      getClass.getResource("/single_pixel_image_199_47_47.png").getFile)

    // Create temporary file for saving
    val tmpFile =
      Files.createTempFile("ASCII_art_generator_test_", ".txt").toFile

    // 0.3*199 + 0.59*47 + 0.11*47 = 92.6 which translates to '*'
    val expected = "*"

    // Create input arguments
    val inputArgs = Seq(
      "--image",
      singlePixelImageFile.getAbsolutePath,
      "--output-file",
      tmpFile.getAbsolutePath)

    ASCIIArtGenerator.run(inputArgs)

    val res = fromFile(tmpFile.getAbsolutePath)

    // Check result
    assert(res.getLines().next() == expected)

    res.close()
  }
}
