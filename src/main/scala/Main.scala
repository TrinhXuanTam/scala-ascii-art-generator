import ASCIIArtGenerator.ASCIIArtGenerator

object Main extends App {
  try {
    new ASCIIArtGenerator(this.args).run()
  } catch {
    case e: Exception => println(e.getMessage)
  }
}
