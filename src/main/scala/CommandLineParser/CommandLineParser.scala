package CommandLineParser

import scala.collection.mutable

class CommandLineParser(
  private val _noArgCommands: Commands[() => Unit],
  private val _oneArgCommands: Commands[String => Unit]) {

  private def _executeNoArgCommand(name: String): Unit =
    _noArgCommands.getCommand(name)()

  private def _executeOneArgCommand(
    name: String,
    queue: mutable.Queue[String]): Unit =
    try {
      val arg: String = queue.dequeue()
      _oneArgCommands.getCommand(name)(arg)
    } catch {
      case _: NoSuchElementException =>
        throw new IllegalArgumentException(
          s"Invalid number of arguments entered: $name!")
    }

  def parse(input: Seq[String]): Unit = {
    val queue = mutable.Queue[String](input: _*)

    while (queue.nonEmpty) {
      val front = queue.dequeue()

      if (_noArgCommands.commandExists(front))
        _executeNoArgCommand(front)
      else if (_oneArgCommands.commandExists(front))
        _executeOneArgCommand(front, queue)
      else
        throw new IllegalArgumentException(s"Invalid command entered: $front!")
    }
  }
}
