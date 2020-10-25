package CommandLineParser

import scala.collection.mutable


class CommandLineParser {
  private val _noArgCommands: Commands[() => Unit] = new Commands()
  private val _oneArgCommands: Commands[String => Unit] = new Commands()

  private def _checkIfCommandExists(name: String): Unit = {
    if (commandExists(name)) throw new IllegalArgumentException("This command already exists!")
  }

  private def _executeNoArgCommand(name: String): Unit = _noArgCommands.getCommand(name)()

  private def _executeOneArgCommand(name: String, queue: mutable.Queue[String]): Unit = {
    try {
      val arg: String = queue.dequeue()
      _oneArgCommands.getCommand(name)(arg)
    } catch {
      case _: NoSuchElementException => throw new IllegalArgumentException("Invalid number of arguments entered!")
    }
  }

  def commandExists(name: String): Boolean = _noArgCommands.commandExists(name) || _oneArgCommands.commandExists(name)

  def removeCommand(name: String): CommandLineParser = {
    _noArgCommands.removeCommand(name)
    _oneArgCommands.removeCommand(name)
    this
  }

  def registerNoArgCommand(name: String, func: () => Unit): CommandLineParser = {
    _checkIfCommandExists(name)
    _noArgCommands.register(name, func)
    this
  }

  def registerOneArgCommand(name: String, func: String => Unit): CommandLineParser = {
    _checkIfCommandExists(name)
    _oneArgCommands.register(name, func)
    this
  }

  def parse(input: Seq[String]): Unit = {
    val queue = mutable.Queue[String](input: _*)

    while (queue.nonEmpty) {
      val front = queue.dequeue()

      if (_noArgCommands.commandExists(front)) {
        _executeNoArgCommand(front)
      } else if (_oneArgCommands.commandExists(front)) {
        _executeOneArgCommand(front, queue)
      } else {
        throw new IllegalArgumentException("Invalid command entered!")
      }
    }
  }
}
