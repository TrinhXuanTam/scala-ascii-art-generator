package ASCIIArtGenerator

import java.awt.image.BufferedImage

import CommandLineParser.{CommandLineParser, CommandLineParserBuilder}
import ImageLoader._
import Filters._

class ASCIIArtGenerator(private val _args: Seq[String]) {
  private val _filter: Filter = new Filter
  private var _image: Option[Image] = None

  def _registerCommands(): CommandLineParser =
    new CommandLineParserBuilder()
      .registerOneArgCommand(
        "--path",
        (path: String) => _image = new ImageLoader().loadFromPath(path))
      .build()

  def run(): Unit =
    try {
      val cmdParser = _registerCommands()
      cmdParser.parse(_args)
    } catch {
      case e: Exception => println(e.getMessage)
    }
}
