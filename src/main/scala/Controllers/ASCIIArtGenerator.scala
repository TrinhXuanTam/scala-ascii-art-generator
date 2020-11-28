package Controllers

import Core.Factories.AsciiImageFactory
import Core.Filters.{BrightnessASCIIFilter, FlipASCIIFilter, InvertASCIIFilter, RotateASCIIFilter, ScaleASCIIFilter}
import Core.OutputLocations.{ConsoleOutput, FileOutput}
import Models.Image
import Modules.ASCIIImage.{IAsciiFilter, IAsciiOutputLocation}
import Services.{AsciiService, ImageService, InputService, OutputService}

// Main controller
class ASCIIArtGenerator {
  val _outputService = new OutputService
  val _asciiService = new AsciiService

  // Transform an image into ASCII art
  def run(args: Seq[String]): Unit = {

    // Init variables
    var outputLocations: Set[IAsciiOutputLocation] = Set[IAsciiOutputLocation]()
    var filters: List[IAsciiFilter] = List[IAsciiFilter]()
    var image: Option[Image] = None
    val inputService = new InputService

    // Bind callback functions to objects
    inputService.mapStringToCallback("--invert", () => filters = filters.appended(new InvertASCIIFilter()))
                .mapStringToCallback("--path", (path: String) => image = Some(new ImageService().loadFromPath(path)))
                .mapStringToCallback("--flip", (direction: String) => filters = filters.appended(new FlipASCIIFilter(direction)))
                .mapStringToCallback("--rotate", (degrees: String) => filters = filters.appended(new RotateASCIIFilter(degrees)))
                .mapStringToCallback("--scale", (scaleFactor: String) => filters = filters.appended(new ScaleASCIIFilter(scaleFactor)))
                .mapStringToCallback("--brightness", (brightness: String) => filters = filters.appended(new BrightnessASCIIFilter(brightness)))
                .mapStringToCallback("--output-file", (path: String) => outputLocations = outputLocations + FileOutput(path))
                .mapStringToCallback("--output-console", () => outputLocations = outputLocations + ConsoleOutput())
                .processInput(args)

    require(image.nonEmpty, "Failed to load the image!")

    //Apply filters
    val src = AsciiImageFactory.fromImage(image.get)
    val res = _asciiService.applyFilters(filters, src)

    // Output the final ascii image
    _outputService.output(res, outputLocations.toSeq)
  }
}
