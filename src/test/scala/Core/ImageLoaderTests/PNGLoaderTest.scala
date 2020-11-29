package Core.ImageLoaderTests

import java.io.File

import Core.ImageLoaders.PNGLoader
import Core.Utils.TestUtils
import javax.imageio.ImageIO
import org.scalatest.FunSuite

class PNGLoaderTest extends FunSuite {
  private val _file = new File(getClass.getResource("/planet.png").getFile)

  test("File load test") {
    val gifLoader = new PNGLoader
    assert(TestUtils.compareImages(gifLoader.load(_file), ImageIO.read(_file)))
  }
}
