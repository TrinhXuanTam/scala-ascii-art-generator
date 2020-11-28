package Modules.ASCIIImage

import scala.collection.mutable.ArrayBuffer

class AsciiFilters {
  private val _filters: ArrayBuffer[IAsciiFilter] =
    new ArrayBuffer[IAsciiFilter]()

  def addFilter(filter: IAsciiFilter): AsciiFilters = {
    _filters.append(filter)
    this
  }

  def transform(ASCIIImage: AsciiImage): AsciiImage = {
    var res = ASCIIImage
    for (filter <- _filters)
      res = filter.transform(res)
    res
  }
}
