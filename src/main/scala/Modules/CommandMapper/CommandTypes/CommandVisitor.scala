package Modules.CommandMapper.CommandTypes

import Modules.CommandMapper.CommandCollection

// Command visitor to pass arguments into commands
class CommandVisitor(args: Seq[String]) {
  private var _args = args.toList

  private def _pop(): String = {
    require(_args.nonEmpty, "Invalid number of arguments!")
    val head = _args.head
    _args = _args.tail
    head
  }

  // Iterate through commands and execute every command
  def visit(commands: CommandCollection): Unit =
    while (_args.nonEmpty) commands.getCommand(_pop()).execute(this)

  def visitNoArgumentsCommand(command: NoArgumentsCommand): Unit =
    command.func()

  def visitOneArgumentCommand(command: OneArgumentCommand): Unit =
    command.func(_pop())
}
