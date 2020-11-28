package Core.OutputLocations

import java.io.{File, FileOutputStream}

import Modules.ASCIIImage.{AsciiImage, IAsciiOutputLocation}

case class FileOutput(private val _path: String) extends IAsciiOutputLocation {
  private val _file = new File(_path)
  private val _oStream = new FileOutputStream(_file, false)

  // Save ASCII art to file
  override def output(image: AsciiImage): Unit = {
    // Create file if it doesn't exist
    _file.createNewFile()

    val asciiArt = image.getArt

    for (y <- 0 until image.height) {
      for (x <- 0 until image.width)
        _oStream.write(asciiArt(x)(y))
      _oStream.write('\n')
    }
    _oStream.flush()
  }

  // Close the file
  override def close(): Unit = _oStream.close()
}
