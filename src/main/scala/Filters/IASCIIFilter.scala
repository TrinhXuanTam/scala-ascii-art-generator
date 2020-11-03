package Filters

import ASCIIImage.ASCIIImage

trait IASCIIFilter {
  def transform(image: ASCIIImage): ASCIIImage
}
