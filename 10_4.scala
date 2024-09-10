object BankApp {
  case class Account(id: String, balance: Double)

  type Bank = List[Account]

  def negativeBalances(accounts: Bank): List[Account] = {
    accounts.filter(account => account.balance < 0)
  }

  def sumOfBalances(accounts: Bank): Double = {
    accounts.map(_.balance).sum
  }

  def applyInterest(accounts: Bank): Bank = {
    accounts.map(account => {
      val interestRate = if (account.balance > 0) 0.05 else 0.1
      val newBalance = account.balance + account.balance * interestRate
      account.copy(balance = newBalance)
    })
  }

  def main(args: Array[String]): Unit = {
    print("Enter the number of accounts: ")
    val numAccounts = scala.io.StdIn.readInt()

    val accounts: Bank = (1 to numAccounts).map { i =>
      print(s"Enter the ID for account $i: ")
      val id = scala.io.StdIn.readLine()
      print(s"Enter the balance for account $i: ")
      val balance = scala.io.StdIn.readDouble()
      Account(id, balance)
    }.toList

    val negativeAccounts = negativeBalances(accounts)
    print("\nAccounts with negative balances: ")
    negativeAccounts.foreach(account => println(s"Account ID: ${account.id}, Balance: ${account.balance}"))

    val totalBalance = sumOfBalances(accounts)
    println(s"\nTotal balance of all accounts: $$${"%.2f".format(totalBalance)}")

    val updatedAccounts = applyInterest(accounts)
    println("\nFinal balances after applying interest:")
    updatedAccounts.foreach(account => println(s"Account ID: ${account.id}, Final Balance: ${"%.2f".format(account.balance)}"))
  }
}
