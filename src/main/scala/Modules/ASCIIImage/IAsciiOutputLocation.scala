package Modules.ASCIIImage

// ASCII output location interface
trait IAsciiOutputLocation {
  def output(image: AsciiImage)

  def close()
}
