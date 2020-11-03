package CommandLineParser

import CommandLineParser.CommandTypes.ICommand

class CommandLineParserBuilder {
  private val _commands: Commands = new Commands()

  private def _checkIfCommandExists(name: String): Unit = if (commandExists(name)) throw new IllegalArgumentException(s"This command already exists: $name!")

  def commandExists(name: String): Boolean = _commands.commandExists(name)

  def removeCommand(name: String): CommandLineParserBuilder = {
    _commands.removeCommand(name)
    this
  }

  def registerCommand(name: String, command: ICommand): CommandLineParserBuilder = {
    _checkIfCommandExists(name)
    _commands.register(name, command)
    this
  }

  def build(): CommandLineParser = new CommandLineParser(_commands)
}
