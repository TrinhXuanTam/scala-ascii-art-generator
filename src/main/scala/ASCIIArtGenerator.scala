package scala

import CommandLineParser.CommandLineParser


class ASCIIArtGenerator() {
  private val cmdParser: CommandLineParser = new CommandLineParser()

  def generate(_args: Seq[String]): Unit = {
    cmdParser.parse(_args)
  }
}
