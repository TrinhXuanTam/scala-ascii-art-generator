package Services

import Modules.ASCIIImage.{AsciiImage, IAsciiFilter}

class AsciiService {
  def applyFilters(
    filters: Seq[IAsciiFilter],
    image: AsciiImage): AsciiImage = {
    var res = image

    for (filter <- filters)
      res = filter.transform(res)

    res
  }
}
