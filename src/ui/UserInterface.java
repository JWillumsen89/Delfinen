package ui;

import finance.MembersFee;

import java.util.Scanner;

public class UserInterface {

  private String choice;
  private final MembersFee membersFee = new MembersFee();
  private final Scanner input = new Scanner(System.in);

  public void displayWelcomeMessage() {
    System.out.println("\nWelcome to DELFIN SVØMMEKLUB\n");

  }

  public String mainMenu() {
    System.out.println("""
        Main menu
        ---------
        1) Chairman
        2) Cashier
        3) Coaches
                        
        0) Close program
        """);
    System.out.print("Pick a menu: ");
    choice = input.nextLine();

    return choice;
  }

  public void printCashierMenu() {
    System.out.println("""
        
        Cashier Menu
        ---------
        1) List of all in arrears
        2) Change membership fees
        3) Total income for this season
        4) See our membership fees
        5) Change payment status
        6) Close program
                                
        0) Return to main
        """);

    System.out.print("Pick a menu: ");
  }

  public void printCashierEditStatusMenu() {
    System.out.println("""
        
        EDIT STATUS MENU:
        
        1) Edit member payment status
        2) Search for another member
                  
        0) Return to Cashier menu
        """);
    System.out.print("What do you want to do?: ");
  }

  public void printChairmanMenu() {

    System.out.println("""
                
        Chairman menu
        ---------
        1) Add new member
        2) Remove member
        3) Member list
        4) Search for member
        5) Close program
                        
        0) Return to main
        """);

    System.out.print("Pick a menu: ");

  }

  public void printChoiceEditMember() {

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

  public void printChoseToChangeFees() {
    //CashierMenu cashierMenu = new CashierMenu();
    System.out.println("---------------Change Delfin Svømmeklub membership fees------------------------\n");
    System.out.print("Junior:............" + membersFee.fees[0] + ",- kr. Type 1\n");
    System.out.print("Senior:........... " + membersFee.fees[1] + ",- kr. Type 2\n");
    System.out.print("Senior Gold:...... " + membersFee.fees[2] + " % Type 3\n");
    System.out.print("Passive Membership: " + membersFee.fees[3] + ",- kr. Type 4\n");

    System.out.println("What is the new members fee for this category?");
  }

  public void printCoachMenu() {
    System.out.println("""
        Coach Menu
        ---------
        1) List of all competitor swimmers
        2) Teams
        3) Training results
        4) Top 5 results
        5) Add results
        6) Close program
                                
        0) Return to main
        """);

    System.out.print("Pick a menu: ");
  }

  public void printDisciplineMenu() {
    System.out.println("""
        1) Butterfly
        2) Crawl
        3) Back crawl
        4) Breaststroke
        
        5) Done adding disciplines
        """);
    System.out.print("Pick discipline: ");
  }

  public void printMemberList() {
    System.out.println("-------------------------------------------------------MEMBER LIST-------------------------------------------------------------------");
    System.out.printf("%-4s %-30s %-35s %-10s %-16s %-8s %-9s %-5s\n", "ID", "Name", "Email", "AGE", "Phone Number", "Status", "Paid", "Membership Fee");
    System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");

  }

  public void printMembersFees() {
    System.out.println("-----------Current members fee pr.season-------------\n");
    System.out.println("Junior: " + membersFee.fees[0] + ",- kr. ");
    System.out.println("Senior: " + membersFee.fees[1] + ",- kr. ");
    System.out.println("Senior Gold: " + membersFee.fees[2] + " % Discount off Senior fee");
    System.out.println("Passive Membership: " + membersFee.fees[3] + ",- kr. \n\n");

  }

  public void printRemoveMember() {
    System.out.print("\nWhich member do you want to remove with the member ID or type[0] to return to chairman menu: ");

  }

  public void printSearchMenu() {

    System.out.println("""
                
        1) Remove member
        2) Edit member
        3) Search for another member
                  
        0) Return to Chairman menu
        """);
    System.out.print("What do you want to do?: ");

  }

  public String readChairmanUi() {
    choice = input.nextLine();

    return choice;
  }
}