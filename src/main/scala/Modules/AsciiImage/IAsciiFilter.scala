package Modules.AsciiImage

// ASCII filter interface
trait IAsciiFilter {
  def transform(image: AsciiImage): AsciiImage
}
