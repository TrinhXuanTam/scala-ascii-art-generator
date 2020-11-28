package Modules.CommandMapper

import Modules.CommandMapper.CommandTypes.{CommandVisitor, ICommand}

// String to callback function mapper
class CommandMapper {
  private val _commands: CommandCollection = new CommandCollection

  // Execute commands for a given input
  def executeCommands(input: Seq[String]): Unit =
    new CommandVisitor(input).visit(_commands)

  // Check if a given command is registered
  def commandExists(key: String): Boolean = _commands.commandExists(key)

  // Remove a given command
  def removeCommand(key: String): CommandMapper = {
    _commands.removeCommand(key)
    this
  }

  // Bind command to a string key
  def registerCommand(key: String, command: ICommand): CommandMapper = {
    require(!commandExists(key), s"This command already exists: $key!")
    _commands.register(key, command)
    this
  }

}
