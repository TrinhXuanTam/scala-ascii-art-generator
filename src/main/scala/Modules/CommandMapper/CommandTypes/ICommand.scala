package Modules.CommandMapper.CommandTypes

// Command interface
trait ICommand {
  def execute(commandVisitor: CommandVisitor)
}
