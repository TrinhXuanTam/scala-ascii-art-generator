package Main

import ASCIIArtGenerator.ASCIIArtGenerator

object Main extends App {
  val ASCIIArtGenerator: ASCIIArtGenerator = new ASCIIArtGenerator(this.args)
  ASCIIArtGenerator.run()
}