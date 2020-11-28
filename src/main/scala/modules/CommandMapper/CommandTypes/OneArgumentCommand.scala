package modules.CommandMapper.CommandTypes

// Command with one argument
class OneArgumentCommand(val func: String => Unit) extends ICommand {
  override def execute(commandVisitor: CommandVisitor): Unit =
    commandVisitor.visitOneArgumentCommand(this)
}
