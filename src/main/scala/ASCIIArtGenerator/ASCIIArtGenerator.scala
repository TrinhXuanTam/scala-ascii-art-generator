package ASCIIArtGenerator

import java.awt.image.BufferedImage

import CommandLineParser.{CommandLineParser, CommandLineParserBuilder, OneArgumentCommand}
import ImageLoader._
import Filters._

class ASCIIArtGenerator(private val _args: Seq[String]) {
  private var _image: Option[Image] = None

  def _registerCommands(): CommandLineParser =
    new CommandLineParserBuilder()
      .registerCommand("--path", new OneArgumentCommand((path: String) => _image = new ImageLoader().loadFromPath(path)))
      .build()

  def run(): Unit =
    try {
      val cmdParser = _registerCommands()
      cmdParser.parse(_args)
    } catch {
      case e: Exception => println(e.getMessage)
    }
}
