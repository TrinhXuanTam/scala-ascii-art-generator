package Repositories

import Modules.ASCIIImage.{AsciiImage, IAsciiOutputLocation}

class AsciiRepository {

  // Save the ASCII image and close the saving location
  def saveToOutputLocation(
    image: AsciiImage,
    outputLocations: Seq[IAsciiOutputLocation]): Unit =
    for (location <- outputLocations) {
      location.output(image)
      location.close()
    }
}
