package CommandLineParser.CommandTypes

import CommandLineParser.CommandVisitor

trait ICommand {
  def execute(commandVisitor: CommandVisitor)
}
