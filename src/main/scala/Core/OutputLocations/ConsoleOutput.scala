package Core.OutputLocations

import Modules.AsciiImage.{AsciiImage, IAsciiOutputLocation}

case class ConsoleOutput() extends IAsciiOutputLocation {

  // Print ASCII art to console
  override def output(image: AsciiImage): Unit = {
    val asciiArt = image.getArt

    for (y <- 0 until image.height) {
      for (x <- 0 until image.width)
        System.out.write(asciiArt(x)(y))
      System.out.write('\n')
    }

    System.out.flush()
  }

  // Do nothing in order to leave the terminal open
  override def close(): Unit = {}
}
