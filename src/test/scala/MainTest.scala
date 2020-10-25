package MainTest

import Main.Foos
import org.scalatest.FunSuite


class MainTest extends FunSuite {
  test("Foo") {
    assert(Foos.Foo())
  }
}
