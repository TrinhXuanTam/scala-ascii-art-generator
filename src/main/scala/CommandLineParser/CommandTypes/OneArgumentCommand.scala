package CommandLineParser.CommandTypes

import CommandLineParser.CommandVisitor

class OneArgumentCommand(val func: String => Unit) extends ICommand {
  override def execute(commandVisitor: CommandVisitor): Unit = commandVisitor.visitOneArgumentCommand(this)
}
