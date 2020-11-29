package Modules.CommandMapperTests

import Modules.CommandMapper.CommandCollection
import Modules.CommandMapper.CommandTypes.ICommand
import org.mockito.MockitoSugar
import org.scalatest.FunSuite

class CommandCollectionTest extends FunSuite with MockitoSugar {
  private val _commandMock = mock[ICommand]

  test("Registration test") {
    val commandCollection = new CommandCollection

    commandCollection.register("--command", _commandMock)

    // Command already exists
    assertThrows[IllegalArgumentException](
      commandCollection.register("--command", _commandMock))

    // Check if command is found
    assert(commandCollection.commandExists("--command"))

    // Command should not be returned after delete
    commandCollection.removeCommand("--command")
    assert(!commandCollection.commandExists("--command"))
  }

  test("Get command test") {
    val commandCollection = new CommandCollection

    commandCollection.register("--command", _commandMock)
    assert(commandCollection.getCommand("--command") == _commandMock)
    assertThrows[IllegalArgumentException](
      commandCollection.getCommand("--nonExistent"))
  }
}
