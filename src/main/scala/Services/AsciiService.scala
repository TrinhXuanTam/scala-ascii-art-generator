package Services

import Modules.ASCIIImage.{AsciiImage, IAsciiFilter, IAsciiOutputLocation}
import Repositories.AsciiRepository

class AsciiService {
  private val asciiRepository = new AsciiRepository

  // Apply all filters on the given ASCII image return the result
  def applyFilters(
    filters: Seq[IAsciiFilter],
    image: AsciiImage): AsciiImage = {
    var res = image

    for (filter <- filters)
      res = filter.transform(res)

    res
  }

  // Output a given ASCII image to selected output locations
  def outputImage(
    image: AsciiImage,
    outputLocations: Seq[IAsciiOutputLocation]): Unit =
    asciiRepository.saveToOutputLocation(image, outputLocations)
}
