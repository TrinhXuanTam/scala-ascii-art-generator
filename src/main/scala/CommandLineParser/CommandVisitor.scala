package CommandLineParser

import CommandLineParser.CommandTypes.{NoArgumentsCommand, OneArgumentCommand}

import scala.collection.mutable

class CommandVisitor(args: Seq[String]) {
  private val _args = mutable.Queue[String](args: _*)

  private def _pop(): String = {
    require(_args.nonEmpty, "Invalid number of arguments!")
    _args.dequeue()
  }

  def visit(commands: Commands) : Unit = {
    while (_args.nonEmpty)
      commands.getCommand(_pop()).execute(this)
  }

  def visitNoArgumentsCommand(command: NoArgumentsCommand): Unit = command.func()

  def visitOneArgumentCommand(command: OneArgumentCommand): Unit = command.func(_pop())
}
