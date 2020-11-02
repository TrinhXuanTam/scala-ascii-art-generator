import CommandLineParser.{CommandLineParser, CommandLineParserBuilder, NoArgumentsCommand, OneArgumentCommand}
import org.scalatest.FunSuite

class CommandLineParserTest extends FunSuite {
  test("Command registration test") {
    val noArgCommand = new NoArgumentsCommand(() => {})
    val oneArgCommand = new OneArgumentCommand(_ => {})

    val cmdParserBuilder: CommandLineParserBuilder =
      new CommandLineParserBuilder()

    cmdParserBuilder
      .registerCommand("--command", noArgCommand)
      .registerCommand("--command2", oneArgCommand)

    assert(cmdParserBuilder.commandExists("--command"))
    assert(cmdParserBuilder.commandExists("--command2"))

    cmdParserBuilder.removeCommand("--command")
    cmdParserBuilder.removeCommand("--command2")

    assert(!cmdParserBuilder.commandExists("--command"))
    assert(!cmdParserBuilder.commandExists("--command2"))

    cmdParserBuilder.registerCommand("--command", noArgCommand)
    cmdParserBuilder.registerCommand("--command2", oneArgCommand)

    assert(cmdParserBuilder.commandExists("--command"))
    assert(cmdParserBuilder.commandExists("--command2"))
  }

  test("Command call test") {
    var noArgFunctionResult: Boolean = false
    var oneArgFunctionResult: String = "fail"
    val args: List[String] = List("--command", "--command2", "success")
    val args2: List[String] = List("--command2", "another success")
    val cmdParserBuilder: CommandLineParserBuilder = new CommandLineParserBuilder()

    val noArgCommand: NoArgumentsCommand = new NoArgumentsCommand(() => noArgFunctionResult = true)
    val oneArgumentCommand: OneArgumentCommand = new OneArgumentCommand((arg: String) => oneArgFunctionResult = arg)

    cmdParserBuilder.registerCommand("--command", noArgCommand)
    cmdParserBuilder.registerCommand("--command2", oneArgumentCommand)
    val cmdParser = cmdParserBuilder.build()

    cmdParser.parse(args)

    assert(noArgFunctionResult)
    assert(oneArgFunctionResult == "success")

    cmdParser.parse(args2)
    assert(oneArgFunctionResult == "another success")
  }

//  test("Invalid command test") {
//    val cmdParser = new CommandLineParserBuilder().build()
//    val args: List[String] = List("--command")
//
//    assertThrows(cmdParser.parse(args))
//  }
}
