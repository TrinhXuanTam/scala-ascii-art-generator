package CommandLineParser

trait ICommand {
  def execute(commandVisitor: CommandVisitor)
}
