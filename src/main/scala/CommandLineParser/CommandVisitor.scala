package CommandLineParser

import scala.collection.mutable

class CommandVisitor(commands: Commands, args: Seq[String]) {
  private val _args = mutable.Queue[String](args: _*)

  def executeCommands() : Unit = {
    while (_args.nonEmpty)
      commands.getCommand(_args.dequeue()).execute(this)
  }

  def visitNoArgumentsCommand(command: NoArgumentsCommand): Unit = command.func()

  def visitOneArgumentCommand(command: OneArgumentCommand): Unit = command.func(_args.dequeue())
}
