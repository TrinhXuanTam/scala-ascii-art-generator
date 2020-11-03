package ASCIIArtGenerator

import java.io.{OutputStream, PrintStream}

import ASCIIImage.{ASCIIImage, ASCIIImageFactory, ASCIIImageSaver}
import CommandLineParser._
import Filters.{ASCIIFilterBuilder, RotateASCIIFilter}
import _root_.CommandLineParser.CommandTypes._
import ImageLoader.{Image, _}

import scala.collection.mutable

class ASCIIArtGenerator(private val _args: Seq[String]) {
  private var _image: Option[Image] = None
  private val _asciiFilterBuilder = new ASCIIFilterBuilder
  private val _outputBuffers = new mutable.HashSet[OutputStream]

  private def _registerCommands(): CommandLineParser =
    new CommandLineParserBuilder()
      .registerCommand("--path", new OneArgumentCommand((path: String) => _image = Some(new Image(new PathImageLoader().load(path)))))
      .registerCommand("--uri", new OneArgumentCommand((uri: String) => _image = Some(new Image(new URIImageLoader().load(uri)))))
      .registerCommand("--rotate", new OneArgumentCommand((degrees: String) => _asciiFilterBuilder.addFilter(new RotateASCIIFilter(degrees))))
      .registerCommand("--output-console", new NoArgumentsCommand(() => _outputBuffers.add(System.out)))
      .build()

  private def _closeStreams(): Unit = {
    for(outputStream <- _outputBuffers)
      if(outputStream != System.out)
        outputStream.close()
  }

  def run(): Unit ={
    _registerCommands().parse(_args)
    require(_image.nonEmpty, "Failed to load the image!")
    val asciiImage = _asciiFilterBuilder.build().transform(ASCIIImageFactory.fromImage(_image.get))
    new ASCIIImageSaver().save(asciiImage, _outputBuffers.toSeq)
    _closeStreams()
  }
}
