package CommandLineParser

class OneArgumentCommand(val func: String => Unit) extends ICommand {
  override def execute(commandVisitor: CommandVisitor): Unit = commandVisitor.visitOneArgumentCommand(this)
}
