import CommandLineParser.CommandLineParser
import org.scalatest.FunSuite


class CommandLineParserTest extends FunSuite {
  test("Command registration test") {
    def noArgFunction(): Unit = {
      println("This is a no arg function!")
    }

    def oneArgFunction(arg: String): Unit = {
      println(arg)
    }

    val cmdParser: CommandLineParser = new CommandLineParser

    cmdParser.registerNoArgCommand("--command", noArgFunction)
    cmdParser.registerOneArgCommand("--command2", oneArgFunction)

    assert(cmdParser.commandExists("--command"))
    assert(cmdParser.commandExists("--command2"))

    cmdParser.removeCommand("--command")
    cmdParser.removeCommand("--command2")

    assert(!cmdParser.commandExists("--command"))
    assert(!cmdParser.commandExists("--command2"))

    cmdParser.registerNoArgCommand("--command", noArgFunction)
    cmdParser.registerOneArgCommand("--command2", oneArgFunction)

    assert(cmdParser.commandExists("--command"))
    assert(cmdParser.commandExists("--command2"))
  }

  test("Command call test") {
    var noArgFunctionResult: Boolean = false
    var oneArgFunctionResult: String = "fail"

    def noArgFunction(): Unit = {
      noArgFunctionResult = true
    }

    def oneArgFunction(arg: String): Unit = {
      oneArgFunctionResult = arg
    }

    val cmdParser: CommandLineParser = new CommandLineParser
    val args: List[String] = List("--command", "--command2", "success")
    cmdParser.registerNoArgCommand("--command", noArgFunction)
    cmdParser.registerOneArgCommand("--command2", oneArgFunction)
    cmdParser.parse(args)

    assert(noArgFunctionResult)
    assert(oneArgFunctionResult == "success")
  }
}
