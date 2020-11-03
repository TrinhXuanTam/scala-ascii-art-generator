package ASCIIArtGenerator

import CommandLineParser.{CommandLineParser, CommandLineParserBuilder}
import _root_.CommandLineParser.CommandTypes.OneArgumentCommand
import Image.Image
import ImageLoader._


class ASCIIArtGenerator(private val _args: Seq[String]) {
  private var _image: Option[Image] = None

  def _registerCommands(): CommandLineParser =
    new CommandLineParserBuilder()
      .registerCommand("--path", new OneArgumentCommand((path: String) => _image = Some(new Image(new PathImageLoader().load(path)))))
      .registerCommand("--url", new OneArgumentCommand((uri: String) => _image = Some(new Image(new URIImageLoader().load(uri)))))
      .build()

  def run(): Unit ={
    _registerCommands().parse(_args)
  }
}
