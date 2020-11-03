import java.awt.image.BufferedImage
import java.io.File

import ImageLoader.{FileImageLoader, PathImageLoader}
import javax.imageio.ImageIO
import org.scalatest.FunSuite

class ImageLoaderTest extends FunSuite {
  private def _compareImages(A: BufferedImage, B: BufferedImage): Boolean = {
    if (A.getWidth() != B.getWidth() || A.getHeight() != B.getHeight()) {
      return false
    }

    for(y <- 0 until A.getHeight(); x <- 0 until A.getWidth()) {
      if (A.getRGB(x, y) != B.getRGB(x, y)) {
        return false
      }
    }

    true
  }

  test("File loader test") {
    val file = new File("./src/test/resources/john_doe.jpg")
    val src = ImageIO.read(file)
    val res = new FileImageLoader().load(file)
    assert(_compareImages(src, res))
  }

  test("Load images from path test") {
    val file = new File("./src/test/resources/john_doe.jpg")
    val file2 = new File("./src/test/resources/default_profile.png")
    val file3 = new File("./src/test/resources/me.gif")

    val src = ImageIO.read(file)
    val src2 = ImageIO.read(file2)
    val src3 = ImageIO.read(file3)

    assert(_compareImages(src, new PathImageLoader().load(file.getCanonicalPath)))
    assert(_compareImages(src2, new PathImageLoader().load(file2.getCanonicalPath)))
    assert(_compareImages(src3, new PathImageLoader().load(file3.getCanonicalPath)))
  }

  test("Load images from url test") {

  }
}
