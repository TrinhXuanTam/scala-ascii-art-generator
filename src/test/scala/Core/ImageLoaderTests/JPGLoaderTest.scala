package Core.ImageLoaderTests

import java.io.File

import Core.ImageLoaders.JPGLoader
import Core.Utils.TestUtils
import javax.imageio.ImageIO
import org.scalatest.FunSuite

class JPGLoaderTest extends FunSuite {
  private val _file = new File(getClass.getResource("/john_doe.jpg").getFile)

  test("File load test") {
    val gifLoader = new JPGLoader
    assert(TestUtils.compareImages(gifLoader.load(_file), ImageIO.read(_file)))
  }
}
