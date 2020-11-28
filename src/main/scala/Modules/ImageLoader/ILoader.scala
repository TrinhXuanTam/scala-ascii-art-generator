package Modules.ImageLoader

import java.awt.image.BufferedImage
import java.io.File

// File loader interface
trait ILoader {
  def load(file: File): BufferedImage
}
