package modules.CommandMapper.CommandTypes

// Command interface
trait ICommand {
  def execute(commandVisitor: CommandVisitor)
}
