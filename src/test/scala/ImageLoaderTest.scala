import java.awt.image.BufferedImage
import java.io.File

import ImageLoader.{FileImageLoader, PathImageLoader, URIImageLoader}
import javax.imageio.ImageIO
import org.scalatest.FunSuite

class ImageLoaderTest extends FunSuite {
  private def _compareImages(A: BufferedImage, B: BufferedImage): Boolean = {
    if (A.getWidth() != B.getWidth() || A.getHeight() != B.getHeight()) {
      return false
    }

    for(x <- 0 until A.getWidth(); y <- 0 until A.getHeight()) {
      if (A.getRGB(x, y) != B.getRGB(x, y)) {
        return false
      }
    }

    true
  }

  private val _file = new File("./src/test/resources/john_doe.jpg")
  private val _file2 = new File("./src/test/resources/default_profile.png")
  private val _file3 = new File("./src/test/resources/me.gif")
  private val _file4 = new File("./src/test/resources/test.txt")

  private val _src = ImageIO.read(_file)
  private val _src2 = ImageIO.read(_file2)
  private val _src3 = ImageIO.read(_file3)

  test("File loader test") {
    assert(_compareImages(_src, new FileImageLoader().load(_file)))
    assert(_compareImages(_src2, new FileImageLoader().load(_file2)))
    assert(_compareImages(_src3, new FileImageLoader().load(_file3)))
    assertThrows[IllegalArgumentException](new FileImageLoader().load(_file4))
  }

  test("Load images from path test") {
    assert(_compareImages(_src, new PathImageLoader().load(_file.getCanonicalPath)))
    assert(_compareImages(_src2, new PathImageLoader().load(_file2.getCanonicalPath)))
    assert(_compareImages(_src3, new PathImageLoader().load(_file3.getCanonicalPath)))
    assertThrows[IllegalArgumentException](new PathImageLoader().load(_file4.getCanonicalPath))
  }

  test("Load images from uri test") {
    assert(_compareImages(_src, new URIImageLoader().load(_file.toURI.toString)))
    assert(_compareImages(_src2, new URIImageLoader().load(_file2.toURI.toString)))
    assert(_compareImages(_src3, new URIImageLoader().load(_file3.toURI.toString)))
    assertThrows[IllegalArgumentException](new URIImageLoader().load(_file4.toURI.toString))
  }
}
