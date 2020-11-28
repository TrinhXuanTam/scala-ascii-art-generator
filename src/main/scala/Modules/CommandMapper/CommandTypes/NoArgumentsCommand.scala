package Modules.CommandMapper.CommandTypes

// Command with no arguments
class NoArgumentsCommand(val func: () => Unit) extends ICommand {
  override def execute(commandVisitor: CommandVisitor): Unit =
    commandVisitor.visitNoArgumentsCommand(this)
}
