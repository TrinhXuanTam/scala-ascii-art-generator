package CommandLineParser

class CommandLineParserBuilder {
  private val _noArgCommands: Commands[() => Unit] = new Commands()
  private val _oneArgCommands: Commands[String => Unit] = new Commands()

  private def _checkIfCommandExists(name: String): Unit =
    if (commandExists(name))
      throw new IllegalArgumentException(s"This command already exists: $name!")

  def commandExists(name: String): Boolean =
    _noArgCommands.commandExists(name) || _oneArgCommands.commandExists(name)

  def removeCommand(name: String): CommandLineParserBuilder = {
    _noArgCommands.removeCommand(name)
    _oneArgCommands.removeCommand(name)
    this
  }

  def registerNoArgCommand(
    name: String,
    func: () => Unit): CommandLineParserBuilder = {
    _checkIfCommandExists(name)
    _noArgCommands.register(name, func)
    this
  }

  def registerOneArgCommand(
    name: String,
    func: String => Unit): CommandLineParserBuilder = {
    _checkIfCommandExists(name)
    _oneArgCommands.register(name, func)
    this
  }

  def build(): CommandLineParser =
    new CommandLineParser(_noArgCommands, _oneArgCommands)
}
