package ASCIIArtGenerator

import java.awt.image.BufferedImage

import CommandLineParser.CommandLineParser
import ImageLoader._
import Filters._

class ASCIIArtGenerator(private val _args: Seq[String]) {
  private val _filter: Filter = new Filter
  private var _image: Option[Image] = None
  private val _cmdParser: CommandLineParser = new CommandLineParser

  def _registerCommands(): Unit = {
    _cmdParser.registerOneArgCommand("--path", (path: String) => _image = new ImageLoader().loadFromPath(path))
  }

  def run(): Unit = {
    _registerCommands()
    _cmdParser.parse(_args)

    if (_image.isEmpty) {
      println("Failed to load the image, aborting...")
      return
    }
  }
}
