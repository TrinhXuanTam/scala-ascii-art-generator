package Modules.FileLoaderTests

import java.io.File
import java.nio.file.Files

import Core.Utils.TestUtils
import Modules.ImageLoader.{FileImageLoader, ILoader}
import javax.imageio.ImageIO
import org.mockito.MockitoSugar
import org.scalatest.FunSuite

class FileImageLoaderTest extends FunSuite with MockitoSugar {
  private val _loaderMock = mock[ILoader]

  test("File load test") {
    // Create temporary file
    val file = new File(getClass.getResource("/me.gif").getFile)

    // Mock file loader interface
    when(_loaderMock.load(file))
      .thenReturn(ImageIO.read(file))

    val fileImageLoader = new FileImageLoader(Map(".gif" -> _loaderMock))

    assert(
      TestUtils.compareImages(fileImageLoader.load(file), ImageIO.read(file)))
  }
}
