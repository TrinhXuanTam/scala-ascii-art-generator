package modules.CommandMapper

import modules.CommandMapper.CommandTypes.{CommandVisitor, ICommand}

// String to callback function mapper
class CommandMapper {
  private val _commands: CommandCollection = new CommandCollection

  def executeCommands(input: Seq[String]): Unit = new CommandVisitor(input).visit(_commands)

  def commandExists(key: String): Boolean = _commands.commandExists(key)

  def removeCommand(key: String): CommandMapper = {
    _commands.removeCommand(key)
    this
  }

  def registerCommand(key: String, command: ICommand): CommandMapper = {
    require(!commandExists(key), s"This command already exists: $key!")
    _commands.register(key, command)
    this
  }

}
