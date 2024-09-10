import scala.io.StdIn

class Rational(n: Int, d: Int) {
  require(d != 0, "Denominator cannot be zero")

  private val g = gcd(n.abs, d.abs) 
  val numerator: Int = n / g
  val denominator: Int = d / g

  def subtract(that: Rational): Rational = {
    new Rational(
      numerator * that.denominator - that.numerator * denominator,
      denominator * that.denominator
    )
  }

  private def gcd(a: Int, b: Int): Int = {
    if (b == 0) a else gcd(b, a % b)
  }

  override def toString: String = s"$numerator/$denominator"
}

object RationalSubtractionApp {
  def main(args: Array[String]): Unit = {
    def readRational(name: String): Rational = {
      print(s"Enter the numerator for $name:")
      val num = StdIn.readInt()
      print(s"Enter the denominator for $name:")
      val den = StdIn.readInt()
      new Rational(num, den)
    }

    val x = readRational("x")
    val y = readRational("y")
    val z = readRational("z")

    println(
      "Which variables would you like to subtract? " +
        "(Enter in the format 'x-y', 'y-x', 'z-y', etc.)"
    )
    val choice = StdIn.readLine().trim.toLowerCase

    val result = choice match {
      case "x-y" => x.subtract(y)
      case "y-x" => y.subtract(x)
      case "x-z" => x.subtract(z)
      case "z-x" => z.subtract(x)
      case "y-z" => y.subtract(z)
      case "z-y" => z.subtract(y)
      case _ =>
        println("Invalid choice. Please enter two variables in the format 'x-y', 'y-x', etc.")
        return
    }

    println(s"The result of $choice is $result")
  }
}
