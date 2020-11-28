package Services

import Modules.ASCIIImage.{AsciiImage, IAsciiOutputLocation}

class OutputService {

  def output(
    image: AsciiImage,
    outputLocations: Seq[IAsciiOutputLocation]): Unit =
    for (location <- outputLocations) {
      location.output(image)
      location.close()
    }
}
