import Controllers.ASCIIArtGenerator

object Main extends App {
  try new ASCIIArtGenerator().run(this.args)
  catch {
    // Print error messages
    case e: Exception => println(e.getMessage)
  }
}
