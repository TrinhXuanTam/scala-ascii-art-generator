package Core.AsciiOutputLocationTests

import java.nio.file.Files

import Core.OutputLocations.FileOutput
import Modules.AsciiImage.{AsciiImage, GrayscaleGrid}
import org.scalatest.FunSuite

import scala.io.Source.fromFile

class FileOutputTest extends FunSuite {
  private val _image = new AsciiImage(new GrayscaleGrid(Array(Array(1))))

  test("Write test") {
    val tmpFilePath = Files.createTempFile("ASCII_tmp_file-", ".txt")
    val fileOutputLocation = FileOutput(tmpFilePath.toString)

    fileOutputLocation.output(_image)
    val src = fromFile(tmpFilePath.toString)
    val _lines = src.getLines()
    assert(_lines.hasNext)
    assert(_lines.next() == "@")

    fileOutputLocation.close()
  }
}
