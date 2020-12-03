package Modules.AsciiImage

// ASCII filter interface
abstract class IAsciiFilter {
  def transform(image: AsciiImage): AsciiImage
}
