package CommandLineParser

import scala.collection.mutable

private class Commands[T] {
  protected val _commands: mutable.HashMap[String, T] = new mutable.HashMap()

  def commandExists(name: String): Boolean = _commands.isDefinedAt(name)

  def register(name: String, func: T): Unit = {
    if (commandExists(name)) throw new IllegalArgumentException("This command already exists!")
    _commands.addOne(name, func)
  }

  def removeCommand(name: String): Unit = {
    _commands.remove(name)
  }

  def getCommand(name: String): T = {
    if (!commandExists(name)) throw new IllegalArgumentException("This command is not registered!")
    _commands(name)
  }
}
