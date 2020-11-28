import Controllers.ASCIIArtGenerator

object Main extends App {
  try new ASCIIArtGenerator().run(this.args)
  catch {
    case e: Exception => println(e.getMessage)
  }
}
