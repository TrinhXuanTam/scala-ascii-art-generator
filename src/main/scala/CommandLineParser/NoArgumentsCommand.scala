package CommandLineParser

class NoArgumentsCommand(val func: () => Unit) extends ICommand {
  override def execute(commandVisitor: CommandVisitor): Unit = commandVisitor.visitNoArgumentsCommand(this)
}
