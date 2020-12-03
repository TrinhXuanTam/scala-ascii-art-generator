package Repositories

import Modules.AsciiImage.{AsciiImage, IAsciiOutputLocation}
import org.mockito.ArgumentMatchers.any
import org.mockito.MockitoSugar
import org.scalatest.FunSuite

class AsciiRepositoryTest extends FunSuite with MockitoSugar {
  test("Save to output test") {
    val image = mock[AsciiImage]
    var success = false
    var success2 = false
    var closed = false
    var closed2 = false

    // Create IOutputLocation mock
    class OutputLocationMock extends IAsciiOutputLocation {
      override def output(image: AsciiImage): Unit = success = true

      override def close(): Unit = closed = true
    }

    class OutputLocationMock2 extends IAsciiOutputLocation {
      override def output(image: AsciiImage): Unit = success2 = true

      override def close(): Unit = closed2 = true
    }

    new AsciiRepository().saveToOutputLocation(
      image,
      Seq(new OutputLocationMock, new OutputLocationMock2))

    // Single output location test
    assert(success)
    assert(closed)

    // Output location chain test
    assert(success2)
    assert(closed2)
  }
}
