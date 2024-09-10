import scala.io.StdIn.readLine

class Rational(n: Int, d: Int) {
  require(d != 0, "Denominator cannot be zero")

  private val gcdVal = gcd(n.abs, d.abs)
  val numerator: Int = n / gcdVal
  val denominator: Int = d / gcdVal

  def neg: Rational = new Rational(-numerator, denominator)

  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

  override def toString: String = s"$numerator/$denominator"
}

object Main {
  def main(args: Array[String]): Unit = {
    print("Enter the numerator:")
    val numerator = readLine().toInt

    print("Enter the denominator:")
    val denominator = readLine().toInt

    val x = new Rational(numerator, denominator)
    println(s"Rational number: $x")

    println(s"Negation of the rational number: ${x.neg}")
  }
}
