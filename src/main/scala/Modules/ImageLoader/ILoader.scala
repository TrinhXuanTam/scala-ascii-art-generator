package Modules.ImageLoader

import java.awt.image.BufferedImage
import java.io.File

trait ILoader {
  def load(file: File): BufferedImage
}
