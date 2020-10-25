package Main

object Main extends App {
  val ASCIIArtGenerator : ASCIIArtGenerator = new ASCIIArtGenerator(this.args)
  ASCIIArtGenerator.generate()
}

object Foos {
  def Foo() : Boolean = true
}