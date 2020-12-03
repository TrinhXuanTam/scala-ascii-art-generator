package Services

import Modules.CommandMapper.CommandMapper
import Modules.CommandMapper.CommandTypes.{
  NoArgumentsCommand,
  OneArgumentCommand
}

// Maps commands from CMD to callback functions
class InputService {
  private val _cmdParser = new CommandMapper

  // map no argument functions
  def mapStringToCallback(key: String, callback: () => Unit): InputService = {
    _cmdParser.registerCommand(key, new NoArgumentsCommand(callback))
    this
  }

  // map one argument functions
  def mapStringToCallback(
    key: String,
    callback: String => Unit): InputService = {
    _cmdParser.registerCommand(key, new OneArgumentCommand(callback))
    this
  }

  // execute callbacks on given input
  def processInput(args: Seq[String]): InputService = {
    _cmdParser.executeCommands(args)
    this
  }
}
