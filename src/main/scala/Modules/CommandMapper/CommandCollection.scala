package Modules.CommandMapper

import Modules.CommandMapper.CommandTypes.ICommand

// Collection of commands
class CommandCollection {
  protected var _commands: Map[String, ICommand] = Map()

  // Check if command exists
  def commandExists(key: String): Boolean = _commands.isDefinedAt(key)

  // Insert a new string & command pair into the hash map
  def register(key: String, command: ICommand): Unit = {
    require(!commandExists(key), s"This command already exists: $key!")
    _commands = _commands + (key -> command)
  }

  // Remove a command from the hashmap
  def removeCommand(key: String): Unit = _commands = _commands - key

  // Retrieve a command from the hashmap
  def getCommand(key: String): ICommand = {
    require(commandExists(key), s"This command is not registered: $key!")
    _commands(key)
  }
}
