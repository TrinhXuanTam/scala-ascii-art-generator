package modules.ImageLoader.Loaders

import java.awt.image.BufferedImage
import java.io.File

import modules.ImageLoader.Image

trait ILoader {
  def load(file: File): BufferedImage
}
