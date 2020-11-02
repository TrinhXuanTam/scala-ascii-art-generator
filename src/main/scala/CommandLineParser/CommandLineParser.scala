package CommandLineParser

class CommandLineParser(
  private val _commands: Commands
) {

  def parse(input: Seq[String]): Unit =
    new CommandVisitor(_commands, input).executeCommands()
}
