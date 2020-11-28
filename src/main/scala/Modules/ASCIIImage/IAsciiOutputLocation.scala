package Modules.ASCIIImage

trait IAsciiOutputLocation {
  def output(image: AsciiImage)

  def close()
}
