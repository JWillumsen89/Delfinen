package UI;


import java.util.Scanner;

public class UserInterface {

  public void displayWelcomeMsg() {
    System.out.println("Welcome to DELFIN SVØMMEKLUB");

  }

  public int mainMenu() {
    System.out.println("""
        Main menu
        ---------
        1) Chairman
        2) Cashier
        3) Coaches
                        
        0) Exit application
        """);
    Scanner input = new Scanner(System.in);
    System.out.print("Pick a menu: ");
    int choice = input.nextInt();
    while (choice < 0 || choice > 3) {
      System.out.println("Only values 0-3 allowed");
      choice = input.nextInt();
    }
    return choice;
  }

  public void printChairmanMenu() {

    System.out.println("""
                
        Chairman menu
        ---------
        1) Add new member
        2) Remove member
        3) Member list
        4) Search for member
                        
        0) Return to main
        """);


  }

  public int readChairmanUi() {
    Scanner input = new Scanner(System.in);
    int choice = input.nextInt();
    while (choice < 0 || choice > 4) {
      System.out.println("Only values 0-4 allowed");
      System.out.print("Pick an option: ");
      choice = input.nextInt();
      input.nextLine(); //Scanner bug fix
    }
    return choice;
  }

  public void printChoiceEditMember() {
    Scanner input = new Scanner(System.in);
    input.nextLine(); //Scanner bug fix
    System.out.println("""
        NAME            [N]
        DATE OF BIRTH   [D]
        EMAIL           [E]
        PHONE NUMBER    [P]
        MEMBER STATUS   [M]
        EXIT            [EXIT]
        """);
    System.out.print("What do you want to edit: ");

  }

  public void printRemoveMember() {
    System.out.print("\nWhich member do you want to remove with the member ID: ");

  }

  public void printSearchMenu() {

    System.out.println("""
        1) Remove member
        2) Edit member
        3) Search for another member
                  
        0) Return to Chairman menu""");
    System.out.print("What do you want to do?: ");

  }

  public void printMemberList() {
    System.out.println("-----------------------------------------------MEMBER LIST-----------------------------------------------------------");
    System.out.printf("%-4s %-30s %-35s %-10s %-16s %-8s %-7s\n", "ID", "Name", "Email", "DOT", "Phone Number", "Status", "Paid");
    System.out.println("---------------------------------------------------------------------------------------------------------------------");

  }


}