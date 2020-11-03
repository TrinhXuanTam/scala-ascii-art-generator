package CommandLineParser

import CommandLineParser.CommandTypes.ICommand

import scala.collection.mutable

class Commands {
  protected val _commands: mutable.HashMap[String, ICommand] = new mutable.HashMap()

  def commandExists(name: String): Boolean = _commands.isDefinedAt(name)

  def register(name: String, command: ICommand): Unit = {
    require(!commandExists(name), s"This command already exists: $name!")
    _commands.addOne(name, command)
  }

  def removeCommand(name: String): Unit = _commands.remove(name)

  def getCommand(name: String): ICommand = {
    require(commandExists(name), s"This command is not registered: $name!")
    _commands(name)
  }
}
