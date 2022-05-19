package Finance;

import Delfin.Main;
import Members.Member;
import UI.UserInterface;

import java.util.ArrayList;
import java.util.Scanner;

//import static java.lang.Shutdown.exit;

public class CashierMenu {

  //UserInterface udserInterface = new UserInterface(null);
  Payments payments = new Payments(null);
  MembersFee memebrsfees = new MembersFee();

  //UserInterface backToMainMenu = new UserInterface();

  public void cashierMenuUi() {

    System.out.println("""
        \nCashier Menu
        ---------
        1) Change Members fees
        2) See members fees
        3) MemberList of missing payments
        4) Grand total for this sesson
        6) Exit program
                                
        0) Return to main
        """);
    Scanner input = new Scanner(System.in);
    int choice = input.nextInt();
    while (choice < 0 || choice > 6) {
      System.out.println("Only values 0-6 allowed");
      choice = input.nextInt();
      input.nextLine(); //Scannerbug
    }

    switch (choice) {
      case 1 -> memebrsfees.ChangememebersFees();
      case 2 -> memebrsfees.printMenmbersFees();
      case 3 -> payments.allMissingPayments();
      case 4 -> payments.sessonTotal();
      case 6 -> exit();
    // case 0 -> udserInterface.mainMenu();


    }
  }

  public void exit () {
      System.out.println("\u001b[1;31mPROGRAM SHUTTING DOWN\u001b[m");
      System.exit(0);
    }
  }

