package CommandLineParser.CommandTypes

import CommandLineParser.CommandVisitor

class NoArgumentsCommand(val func: () => Unit) extends ICommand {
  override def execute(commandVisitor: CommandVisitor): Unit = commandVisitor.visitNoArgumentsCommand(this)
}
