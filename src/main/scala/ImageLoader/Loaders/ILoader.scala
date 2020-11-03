package ImageLoader.Loaders

import java.awt.image.BufferedImage
import java.io.File

import Image.Image

trait ILoader {
  def load(file: File): BufferedImage
}
