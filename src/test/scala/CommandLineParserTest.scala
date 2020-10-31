import CommandLineParser.{CommandLineParser, CommandLineParserBuilder}
import org.scalatest.FunSuite

class CommandLineParserTest extends FunSuite {
  test("Command registration test") {
    def noArgFunction(): Unit =
      println("This is a no arg function!")

    def oneArgFunction(arg: String): Unit =
      println(arg)

    val cmdParserBuilder: CommandLineParserBuilder =
      new CommandLineParserBuilder()

    cmdParserBuilder.registerNoArgCommand("--command", noArgFunction)
    cmdParserBuilder.registerOneArgCommand("--command2", oneArgFunction)

    assert(cmdParserBuilder.commandExists("--command"))
    assert(cmdParserBuilder.commandExists("--command2"))

    cmdParserBuilder.removeCommand("--command")
    cmdParserBuilder.removeCommand("--command2")

    assert(!cmdParserBuilder.commandExists("--command"))
    assert(!cmdParserBuilder.commandExists("--command2"))

    cmdParserBuilder.registerNoArgCommand("--command", noArgFunction)
    cmdParserBuilder.registerOneArgCommand("--command2", oneArgFunction)

    assert(cmdParserBuilder.commandExists("--command"))
    assert(cmdParserBuilder.commandExists("--command2"))
  }

  test("Command call test") {
    var noArgFunctionResult: Boolean = false
    var oneArgFunctionResult: String = "fail"

    def noArgFunction(): Unit =
      noArgFunctionResult = true

    def oneArgFunction(arg: String): Unit =
      oneArgFunctionResult = arg

    val cmdParserBuilder: CommandLineParserBuilder =
      new CommandLineParserBuilder()
    val args: List[String] = List("--command", "--command2", "success")
    val args2: List[String] = List("--command2", "another success")

    cmdParserBuilder.registerNoArgCommand("--command", noArgFunction)
    cmdParserBuilder.registerOneArgCommand("--command2", oneArgFunction)
    val cmdParser = cmdParserBuilder.build()

    cmdParser.parse(args)

    assert(noArgFunctionResult)
    assert(oneArgFunctionResult == "success")

    cmdParser.parse(args2)
    assert(oneArgFunctionResult == "another success")
  }
}
