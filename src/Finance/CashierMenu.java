package Finance;

import UI.UserInterface;

import java.util.Scanner;

public class CashierMenu {


  public void cashierMenuUi() {
    MembersFee memebrsfees = new MembersFee();
    System.out.println("""
        Cashier Menu
        ---------
        1) See Missing payments
        2) Change Members fees
        3) See all payments
        4) See members fees
        5) Exit program
                                
        0) Return to main
        """);
    Scanner input = new Scanner(System.in);
    int choice = input.nextInt();
    while (choice < 0 || choice > 4) {
      System.out.println("Only values 0-4 allowed");
      choice = input.nextInt();
    }

    switch (choice) {
      //case 1 -> missingpyments();
      case 2 -> memebrsfees.ChangememebersFees();
      //case 3 -> seeAllPayments();
      case 4 -> memebrsfees.printMenmbersFees();
      case 5 -> exit();
     // case 0 -> returnToMain();


    }
  }

  public void exit() {
    System.out.println("\u001b[1;31mPROGRAM SHUTTING DOWN\u001b[m");
    System.exit(0);
  }

}
