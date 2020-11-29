package Controllers

import Core.Factories.AsciiImageFactory
import Core.Filters.{
  BrightnessASCIIFilter,
  FlipASCIIFilter,
  InvertASCIIFilter,
  RotateASCIIFilter,
  ScaleASCIIFilter
}
import Core.OutputLocations.{ConsoleOutput, FileOutput}
import Models.Image
import Modules.AsciiImage.{IAsciiFilter, IAsciiOutputLocation}
import Services.{AsciiService, ImageService, InputService}

// Main controller
class ASCIIArtGenerator {
  val _asciiService = new AsciiService

  // Transform an image into ASCII art
  def run(args: Seq[String]): Unit = {

    // Init variables
    var image: Option[Image] = None
    var filters: List[IAsciiFilter] = List[IAsciiFilter]()
    var outputLocations: Set[IAsciiOutputLocation] = Set[IAsciiOutputLocation]()
    val inputService = new InputService

    // Bind callback functions to objects
    inputService
      .mapStringToCallback(
        "--invert",
        () => filters = filters.appended(new InvertASCIIFilter()))
      .mapStringToCallback(
        "--image",
        (path: String) => image = Some(new ImageService().loadFromPath(path)))
      .mapStringToCallback(
        "--flip",
        (direction: String) =>
          filters = filters.appended(new FlipASCIIFilter(direction)))
      .mapStringToCallback(
        "--rotate",
        (degrees: String) =>
          filters = filters.appended(new RotateASCIIFilter(degrees)))
      .mapStringToCallback(
        "--scale",
        (scaleFactor: String) =>
          filters = filters.appended(new ScaleASCIIFilter(scaleFactor)))
      .mapStringToCallback(
        "--brightness",
        (brightness: String) =>
          filters = filters.appended(new BrightnessASCIIFilter(brightness)))
      .mapStringToCallback(
        "--output-file",
        (path: String) => outputLocations = outputLocations + FileOutput(path))
      .mapStringToCallback(
        "--output-console",
        () => outputLocations = outputLocations + ConsoleOutput())

    // Process input from command line
    inputService.processInput(args)

    // Throw exception if no file was given or failed to load
    require(image.nonEmpty, "Failed to load the image!")

    //Apply filters
    val src = AsciiImageFactory.fromImage(image.get)
    val res = _asciiService.applyFilters(filters, src)

    // Output the final ascii image
    _asciiService.outputImage(res, outputLocations.toSeq)
  }
}
