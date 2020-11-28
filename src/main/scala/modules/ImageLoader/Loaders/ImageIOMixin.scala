package modules.ImageLoader.Loaders

import java.awt.image.BufferedImage
import java.io.File

import javax.imageio.ImageIO

trait ImageIOMixin extends ILoader {
  def load(file: File): BufferedImage = ImageIO.read(file)
}
