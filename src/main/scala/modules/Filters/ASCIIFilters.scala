package modules.Filters

import modules.ASCIIImage.ASCIIImage

class ASCIIFilters(private val _filters: Seq[IASCIIFilter]) {
  def transform(ASCIIImage: ASCIIImage): ASCIIImage = {
    var res = ASCIIImage
    for(filter <- _filters)
      res = filter.transform(res)
    res
  }
}
