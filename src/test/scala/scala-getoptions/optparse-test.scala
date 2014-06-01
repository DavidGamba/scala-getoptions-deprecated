import org.scalatest._
import com.gambaeng.utils.OptionParser

class OptionParserTest extends FlatSpec with Matchers {

  "An OptionParser" should "take an empty map and pass along all non option entries" in {
    val args = Array[String]("hello", "world")

    val (options, remaining) = OptionParser.getOptions(args, Map())
    remaining should equal ( Array("hello", "world") )
    options shouldBe empty
  }

  it should "warn on unknown option entries and ignore them" in {
    val args = Array[String]("hello", "world", "--unknown")

    val (options, remaining) = OptionParser.getOptions(args, Map())
    remaining should equal ( Array("hello", "world") )
    options shouldBe empty
  }

  it should "match flags" in {
    val args = Array[String]("hello", "--help", "-h", "--version", "world")

    val (options, remaining) = OptionParser.getOptions(args,
      Map(
        "--help"          -> 'man,
        "-h"              -> 'help,
        "--version"       -> 'version
      ))
    remaining should equal ( Array("hello", "world") )
    options should not be empty
    options should equal ( Map( 'man -> true,
                                'help -> true,
                                'version -> true ))
  }

  it should "match strings" in {
    val args = Array[String]("hello", "--help=mystring", "-h", "another_string", "--version", "version", "world")

    val (options, remaining) = OptionParser.getOptions(args,
      Map(
        "--help=s"          -> 'man,
        "-h=s"              -> 'help,
        "--version=s"       -> 'version
      ))
    remaining should equal ( Array("hello", "world") )
    options should not be empty
    options should equal ( Map( 'man -> "mystring",
                                'help -> "another_string",
                                'version -> "version" ))
  }

  it should "match integers" in {
    val args = Array[String]("hello", "--help=1", "-h", "3", "--version", "2", "world")

    val (options, remaining) = OptionParser.getOptions(args,
      Map(
        "--help=i"          -> 'man,
        "-h=i"              -> 'help,
        "--version=i"       -> 'version
      ))
    remaining should equal ( Array("hello", "world") )
    options should not be empty
    options should equal ( Map( 'man -> 1,
                                'help -> 3,
                                'version -> 2 ))
  }

  it should "match double" in {
    val args = Array[String]("hello", "--help=1.5", "-h", "3.2", "--version", "2.7543", "world")

    val (options, remaining) = OptionParser.getOptions(args,
      Map(
        "--help=f"          -> 'man,
        "-h=f"              -> 'help,
        "--version=f"       -> 'version
      ))
    remaining should equal ( Array("hello", "world") )
    options should not be empty
    options should equal ( Map( 'man -> 1.5,
                                'help -> 3.2,
                                'version -> 2.7543 ))
  }

  it should "match all previous things at the same time" in {
    val args = Array[String]("hello", "world")

    val (options, remaining) = OptionParser.getOptions(args,
      Map(
        "--help"          -> 'man,
        "-h"              -> 'help,
        "--version"       -> 'version,
        "-c|--case"       -> 'case,
        "-f"              -> 'type_file,
        "-d"              -> 'type_dir,
        "--hidden"        -> 'hidden,
        "--vcs"           -> 'vcs,
        "--full|fullpath" -> 'fullpath,
        "--type=s"        -> 'type,
        "--color=s"       -> 'color,
        "--int=i"         -> 'int,
        "--function"      -> println("function")
      ))
    remaining should equal ( Array("hello", "world") )
  }
}
