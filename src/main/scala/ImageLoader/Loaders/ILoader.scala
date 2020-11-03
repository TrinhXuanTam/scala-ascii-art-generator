package ImageLoader.Loaders

import java.awt.image.BufferedImage
import java.io.File

import ImageLoader.Image

trait ILoader {
  def load(file: File): BufferedImage
}
