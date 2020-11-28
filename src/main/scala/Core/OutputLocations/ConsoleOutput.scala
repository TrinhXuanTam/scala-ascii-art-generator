package Core.OutputLocations

import Modules.ASCIIImage.{AsciiImage, IAsciiOutputLocation}

case class ConsoleOutput() extends IAsciiOutputLocation {
  override def output(image: AsciiImage): Unit = {
    for (y <- 0 until image.height) {
      for (x <- 0 until image.width)
        System.out.write(image.getArt(x)(y))
      System.out.write('\n')
    }
    System.out.flush()
  }

  override def close(): Unit = {}
}
