package Modules.ASCIIImage

// ASCII filter interface
trait IAsciiFilter {
  def transform(image: AsciiImage): AsciiImage
}
