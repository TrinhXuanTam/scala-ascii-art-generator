package Modules.CommandMapperTests

import Modules.CommandMapper.CommandCollection
import Modules.CommandMapper.CommandTypes.{
  CommandVisitor,
  NoArgumentsCommand,
  OneArgumentCommand
}
import org.mockito.MockitoSugar
import org.scalatest.FunSuite

class CommandVisitorTest extends FunSuite with MockitoSugar {
  private val _commandCollectionMock = mock[CommandCollection]

  test("Command execution test") {
    val args = Seq("--noArgs", "--oneArg", "success")
    val commandVisitor = new CommandVisitor(args)
    var oneArgSuccess = "fail"
    var noArgSuccess = false

    when(_commandCollectionMock.getCommand("--noArgs"))
      .thenReturn(new NoArgumentsCommand(() => noArgSuccess = true))

    when(_commandCollectionMock.getCommand("--oneArg"))
      .thenReturn(new OneArgumentCommand((arg: String) => oneArgSuccess = arg))

    commandVisitor.visit(_commandCollectionMock)

    assert(noArgSuccess)
    assert(oneArgSuccess == "success")
  }

  test("Invalid number of arguments test") {
    val args = Seq("--oneArg")

    when(_commandCollectionMock.getCommand("--oneArg"))
      .thenReturn(new OneArgumentCommand((_: String) => {}))

    val commandVisitor = new CommandVisitor(args)

    assertThrows[IllegalArgumentException](
      commandVisitor.visit(_commandCollectionMock))
  }
}
