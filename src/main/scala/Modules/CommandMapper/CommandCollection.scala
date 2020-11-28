package Modules.CommandMapper

import Modules.CommandMapper.CommandTypes.ICommand

import scala.collection.mutable

// Collection of commands
class CommandCollection {
  protected val _commands: mutable.HashMap[String, ICommand] =
    new mutable.HashMap()

  // Check if command exists
  def commandExists(key: String): Boolean = _commands.isDefinedAt(key)

  // Insert a new string & command pair into the hash map
  def register(key: String, command: ICommand): Unit = {
    require(!commandExists(key), s"This command already exists: $key!")
    _commands.addOne(key, command)
  }

  // Remove a command from the hashmap
  def removeCommand(key: String): Unit = _commands.remove(key)

  // Retrieve a command from the hashmap
  def getCommand(key: String): ICommand = {
    require(commandExists(key), s"This command is not registered: $key!")
    _commands(key)
  }
}
