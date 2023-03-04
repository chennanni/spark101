import java.nio.file.Paths

/**
 * get the project path
 */
object GetProjectPath {
  def main(args: Array[String]): Unit = {
    println("1. my project path is: " + Paths.get(".").toAbsolutePath)
    println("2. my project path is: " + System.getProperty("user.dir"))
  }
}