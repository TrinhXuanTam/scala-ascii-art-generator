package modules.Filters

import modules.ASCIIImage.ASCIIImage

trait IASCIIFilter {
  def transform(image: ASCIIImage): ASCIIImage
}
