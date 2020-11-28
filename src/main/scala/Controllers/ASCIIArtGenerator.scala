package Controllers

import Core.Factories.AsciiImageFactory
import Models.Image
import Services.{AsciiService, ImageService, InputService}

// Main controller
class ASCIIArtGenerator {

  // Transform an image into ASCII art
  def run(args: Seq[String]): Unit = {
    val inputService = new InputService
    var image: Option[Image] = None

    // Bind image loaders to image object
    inputService.mapStringToCallback(
      "--path",
      (path: String) => image = Some(new ImageService().loadFromPath(path)))

    inputService.processInput(args)

    require(image.nonEmpty, "Failed to load the image!")

    val asciiService = new AsciiService
    var asciiImage = AsciiImageFactory.fromImage(image.get)
  }
}
