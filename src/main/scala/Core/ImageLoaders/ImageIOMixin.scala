package Core.ImageLoaders

import java.awt.image.BufferedImage
import java.io.File

import Modules.ImageLoader.ILoader
import javax.imageio.ImageIO

// ILoader interface implementation with ImageIO from JDK
trait ImageIOMixin extends ILoader {
  def load(file: File): BufferedImage = ImageIO.read(file)
}
