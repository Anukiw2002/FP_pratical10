import scala.io.StdIn._

object LetterCounter {

  def main(args: Array[String]): Unit = {
    print("Enter a list of words separated by commas (e.g., apple,banana,cherry,date):")
    val input = readLine()

    val words = input.split(",").map(_.trim).toList

    val totalLetterCount = countLetterOccurrences(words)

    println(s"Total count of letter occurrences: $totalLetterCount")
  }

  def countLetterOccurrences(words: List[String]): Int = {
    val wordLengths = words.map(word => word.length)

    val totalLetters = wordLengths.reduce((length1, length2) => length1 + length2)

    totalLetters
  }
}
