package Core.ImageLoaderTests

import java.io.File

import Core.ImageLoaders.GIFLoader
import Core.Utils.TestUtils
import javax.imageio.ImageIO
import org.scalatest.FunSuite

class GIFLoaderTest extends FunSuite {
  private val _file = new File(getClass.getResource("/me.gif").getFile)

  test("File load test") {
    val gifLoader = new GIFLoader
    assert(TestUtils.compareImages(gifLoader.load(_file), ImageIO.read(_file)))
  }
}
