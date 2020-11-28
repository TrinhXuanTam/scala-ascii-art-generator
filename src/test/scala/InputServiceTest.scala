import Services.InputService
import org.scalatest.FunSuite

class InputServiceTest extends FunSuite {
  private val _input = List("--zero", "--one", "true")

  test("Registration test") {
    var noArgRes = false
    var oneArgRes = "false"
    val inputService = new InputService

    inputService.mapStringToCallback("--zero", () => noArgRes = true)
    inputService.mapStringToCallback(
      "--one",
      (input: String) => oneArgRes = input)

    inputService.processInput(_input)

    assert(noArgRes)
    assert(oneArgRes == "true")
  }

  test("Unregistered callback test") {
    assertThrows[IllegalArgumentException](
      new InputService().processInput(_input))
  }
}
