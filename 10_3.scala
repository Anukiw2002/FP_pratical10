import scala.io.StdIn._

case class Account(var balance: Double, accountNumber: Int) {
  def deposit(amount: Double): Unit = {
    if (amount > 0) {
      balance += amount
      println(f"Successfully deposited Rs.$amount%.2f to Account $accountNumber. New balance is Rs.$balance%.2f.")
    } else {
      println(s"Deposit amount must be greater than zero for Account $accountNumber.")
    }
  }

  def withdraw(amount: Double): Unit = {
    if (amount > 0 && amount <= balance) {
      balance -= amount
      println(f"Successfully withdrew Rs.$amount%.2f from Account $accountNumber. New balance is Rs.$balance%.2f.")
    } else if (amount > balance) {
      println(s"Insufficient balance in Account $accountNumber.")
    } else {
      println(s"Withdrawal amount must be greater than zero for Account $accountNumber.")
    }
  }

  def transfer(toAccount: Account, amount: Double): Unit = {
    if (amount > 0 && amount <= balance) {
      this.withdraw(amount) 
      toAccount.deposit(amount) 
      println(f"Successfully transferred Rs.$amount%.2f from Account $accountNumber to Account ${toAccount.accountNumber}.")
    } else if (amount > balance) {
      println(s"Insufficient balance in Account $accountNumber for transfer.")
    } else {
      println(s"Transfer amount must be greater than zero for Account $accountNumber.")
    }
  }
}

object BankApp {
  def main(args: Array[String]): Unit = {
    val account1 = Account(1000.0, 1)
    val account2 = Account(500.0, 2)
    
    var continue = true
    while (continue) {   
      println("\n1. Deposit")
      println("2. Withdraw")
      println("3. Transfer")
      println("4. Exit")
      print("Choose an option: ")

      val choice = readInt()

      choice match {
        case 1 => 
          print("Select account to deposit to (1 or 2) : ")
          val accountChoice = readInt()
          val selectedAccount = if (accountChoice == 1) account1 else account2
          print(s"Enter the amount to deposit to Account $accountChoice: Rs. ")
          val amount = readDouble()
          selectedAccount.deposit(amount)
          
        case 2 =>
          print("Select account to withdraw from (1 or 2): ")
          val accountChoice = readInt()
          val selectedAccount = if (accountChoice == 1) account1 else account2
          print(s"Enter the amount to withdraw from Account $accountChoice: Rs. ")
          val amount = readDouble()
          selectedAccount.withdraw(amount)
          
        case 3 =>
          print("Select the source account for transfer (1 or 2): ")
          val sourceAccountChoice = readInt()
          val sourceAccount = if (sourceAccountChoice == 1) account1 else account2
          val targetAccount = if (sourceAccountChoice == 1) account2 else account1
          
          print(s"Enter the amount to transfer from Account ${sourceAccount.accountNumber} to Account ${targetAccount.accountNumber}: Rs. ")
          val amount = readDouble()
          sourceAccount.transfer(targetAccount, amount)
          
        case 4 =>
          println("Exiting.....")
          continue = false
          
        case _ =>
          println("Invalid option. Please try again.")
      }
    }
  }
}
