package CommandLineParser

import java.util.NoSuchElementException

import scala.collection.mutable

class CommandLineParser {
  private val _noArgCommands: mutable.HashMap[String, () => Unit] = new mutable.HashMap()
  private val _oneArgCommands: mutable.HashMap[String, String => Unit] = new mutable.HashMap()

  def _checkIfCommandExists(name: String): Unit = {
    if (commandExists(name)) throw new IllegalArgumentException("This command already exists!")
  }

  def commandExists(name: String): Boolean = _noArgCommands.isDefinedAt(name) || _oneArgCommands.isDefinedAt(name)

  def removeCommand(name: String): Unit = {
    if (!commandExists(name)) throw new IllegalArgumentException("This command is not registered!")

    _noArgCommands.remove(name)
    _oneArgCommands.remove(name)
  }

  def registerNoArgCommand(name: String, func: () => Unit): Unit = {
    _checkIfCommandExists(name)
    _noArgCommands.addOne(name, func)
  }

  def registerOneArgCommand(name: String, func: String => Unit): Unit = {
    _checkIfCommandExists(name)
    _oneArgCommands.addOne(name, func)
  }

  def parse(input: Seq[String]): Unit = {
    val queue = mutable.Queue[String](input: _*)

    while (queue.nonEmpty) {
      val front = queue.dequeue()

      if (_noArgCommands.isDefinedAt(front))
        _noArgCommands(front)()
      else if (_oneArgCommands.isDefinedAt(front)) {
        val arg: String = queue.dequeue()
        _oneArgCommands(front)(arg)
      } else {
        throw new IllegalArgumentException("Invalid command entered!")
      }
    }
  }
}
