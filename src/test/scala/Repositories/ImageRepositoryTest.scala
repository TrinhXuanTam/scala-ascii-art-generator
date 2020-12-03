package Repositories

import java.awt.Color
import java.io.File

import javax.imageio.ImageIO
import org.scalatest.FunSuite

class ImageRepositoryTest extends FunSuite {
  test("Load from path test") {
    val file = new File(getClass.getResource("/me.gif").getFile)
    val imageRepository = new ImageRepository
    val src = ImageIO.read(file)
    val res = imageRepository.fromPath(file.getAbsolutePath)

    // Must be same dimension
    assert(res.height == src.getHeight())
    assert(res.width == src.getWidth())

    // Check every RGB value
    for {
      x <- 0 until res.width
      y <- 0 until res.height
    } assert(res.getColorAt(x, y) == new Color(src.getRGB(x, y)))
  }

  test("Forbidden extension test") {
    val file = new File(getClass.getResource("/package_diagram.svg").getFile)
    val imageRepository = new ImageRepository
    assertThrows[IllegalArgumentException](imageRepository.fromPath(file.getAbsolutePath))
  }
}
