package Filters

import scala.collection.mutable.ArrayBuffer

class ASCIIFilterBuilder {
  private val _filters: ArrayBuffer[IASCIIFilter] = new ArrayBuffer[IASCIIFilter]()

  def addFilter(filter: IASCIIFilter): ASCIIFilterBuilder = {
    _filters.append(filter)
    this
  }

  def build(): ASCIIFilters = new ASCIIFilters(_filters.toSeq)
}
