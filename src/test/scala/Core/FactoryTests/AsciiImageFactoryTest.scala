package Core.FactoryTests

import java.awt.Color
import java.awt.image.BufferedImage

import Core.Factories.AsciiImageFactory
import Models.Image
import org.scalatest.FunSuite

class AsciiImageFactoryTest extends FunSuite {
  test("RGB to grayscale conversion test") {
    // Create a dummy image
    val image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB)
    val red = 100
    val green = 150
    val blue = 200
    val color = new Color(red, green, blue)
    image.setRGB(0, 0, color.getRGB)

    // Calculate the expected grayscale value
    val expected = ((0.3 * red) + (0.59 * green) + (0.11 * blue)).toInt

    // Compare results
    val grayscale = AsciiImageFactory.fromImage(new Image(image))
    assert(grayscale.width == 1)
    assert(grayscale.height == 1)
    assert(grayscale.getGrayScaleData(0)(0) == expected)
  }
}
