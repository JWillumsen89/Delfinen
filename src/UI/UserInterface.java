package UI;


import Delfin.Main;
import Filehandler.DatabaseException;
import Finance.CashierMenu;
import Finance.MembersFee;
import Members.Member;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.Locale;
import java.util.Scanner;

public class UserInterface {
  CashierMenu goToMenueCashier = new CashierMenu();

  private String name;
  private String email;
  private Integer memberID;
  //   private LocalDate dateOfBirth;

  private LocalDate age;
  private int phoneNumber;

  private char active;
  private char paidOrNot;

  private boolean fileSaved = false;


  MembersFee memberFee = new MembersFee();
  Scanner input = new Scanner(System.in);

  private final Main app;

  public UserInterface(Main app) {
    this.app = app;
  }


  public void start() {
    System.out.println("Welcome to DELFIN SVØMMEKLUB");

    while (true) {
      switch (mainMenu()) {
        case 0 -> exit();
        case 1 -> chairman();
        case 2 -> goToMenueCashier.cashierMenuUi();
        case 3 -> coaches();
      }
    }
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
    int choice = input.nextInt();
    while (choice < 0 || choice > 3) {
      System.out.println("Only values 0-3 allowed");
      choice = input.nextInt();
    }
    return choice;
  }

  //TODO MOVE TO SEPERATE CLASS
  public void chairman() {

    System.out.println("""
        Chairman menu
        ---------
        1) Add new member
        2) Remove member
        3) Member list
        4) Save member
                        
        0) Return to main
        """);
    Scanner input = new Scanner(System.in);
    int choice = input.nextInt();
    while (choice < 0 || choice > 4) {
      System.out.println("Only values 0-4 allowed");
      choice = input.nextInt();
      input.nextLine(); //Scannerbug
    }

    switch (choice) {
      case 0 -> {
        if (!fileSaved) {
          System.out.println("You haven't saved your members list, are you still sure you want to return to main menu and loose any new data? yes[Y] or no[N]: ");
          char decision = input.next().toUpperCase(Locale.ROOT).charAt(0);
          switch (decision) {
            case 'Y' -> start();
            case 'N' -> chairman();
            default -> System.out.println("Invalid char");
          }
        }
      }
      case 1 -> addMember();
      case 2 -> removeMember();
      case 3 -> memberList();
      case 4 -> save();

    }
  }

  public void addMember() {
    System.out.println("Create new member\n-----------------");
    Scanner input = new Scanner(System.in);
    System.out.print("Name: ");
    name = input.nextLine();

    //------------------ageCalulatorUsed in Payments-------------------------------
    System.out.print("Enter date of birth in YYYY-MM-DD format: ");
    age = LocalDate.parse(input.nextLine());
    LocalDate temp = LocalDate.parse(age.toString());
    memberFee.calculateAge(temp);
    int result = (int) memberFee.paymentCategoryCalculator();
    System.out.println(result); //TODO: skal slettes, kun til test(linjen)
    System.out.println(memberFee.getNewAge());


//----------------------------slut----------------------

    //input.nextLine();
    System.out.print("Email: ");
    email = input.nextLine();
    System.out.print("Phonenumber: ");
    phoneNumber = input.nextInt();
    input.nextLine(); // ScannerBug fix
    //TODO Autogenerat ID Number!
    System.out.print("memberID: ");
    memberID = input.nextInt();

    char active1 = 'A';
    char active2 = 'P';
    boolean answer = false;

    do {
      System.out.print("Active: [A] or Passive: [P]");
      active = input.next().toUpperCase(Locale.ROOT).charAt(0);
      if (active == active1) {
        System.out.println("Active member");
        answer = true;
      } else if (active == active2) {
        System.out.println("Passive member");
        answer = true;
      } else {
        System.out.println("Invalid char");
      }
    }
    while (!answer);


    input.nextLine();//Scanner bug fix
    saveMember();

  }

  public void saveMember() {
    char paidOrNot = 'N';
    System.out.println("\nMember information:");
    System.out.println("\nName: " + name + "\nDate of birth: " + age + "\nEmail: " + email + "\nPhone number: "
        + phoneNumber + "\nmember ID: " + "\nActive or passive: " + active);
    System.out.print("\n\nAre the information correct? Yes[Y], edit[E] or discard[D]: ");
    String decision = input.nextLine().toUpperCase(Locale.ROOT);
    switch (decision) {
      case "Y" -> {
        app.createNewMember(name, age, phoneNumber, email, memberID, active, paidOrNot);
        app.setMemberId(memberID);
        System.out.println("\nMEMBER HAS BEEN SAVED!!\n");
        chairman();
      }
      case "E" -> {
        editMember();
      }
      case "D" -> {
        System.out.println("\nDISCARDED - Nothing have been saved\n");
        chairman();
      }
    }
  }

  public void editMember() {
    System.out.println("""
        NAME            [N]
        DATE OF BIRTH   [D]
        EMAIL           [E]
        PHONE NUMBER    [P]
        MEMBER STATUS   [M]
        EXIT            [EXIT]
        """);
    System.out.print("What do you want to edit: ");
    String decision = input.nextLine().toUpperCase(Locale.ROOT);
    switch (decision) {
      case "N" -> {
        System.out.println("Change name: ");
        name = input.nextLine();
        saveMember();
      }
      case "D" -> {
        System.out.println("Change date of birth: ");
        age = LocalDate.parse(String.valueOf(input.nextInt()));
        input.nextLine(); //Scanner bug fix
        saveMember();
      }
      case "E" -> {
        System.out.println("Change email: ");
        email = input.nextLine();
        saveMember();
      }
      case "P" -> {
        System.out.println("Change phone number: ");
        phoneNumber = input.nextInt();
        input.nextLine(); //Scanner bug fix
        saveMember();
      }
      case "M" -> {
        System.out.println("Change member status to Active: [A] or Passive: [P]: ");
        active = input.next().toUpperCase(Locale.ROOT).charAt(0);
        input.nextLine(); //Scanner bug fix
        saveMember();
      }
      case "EXIT" -> chairman();
      default -> {
        System.out.println("Invalid decision");
        editMember();
      }
    }
  }

  public void removeMember() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Remove member\n");
    System.out.println("\nWhich member do you want to remove with the member ID");
    Integer memberID = sc.nextInt();

    //TODO System.out.println("Are you sure tha"); are you sure?

    boolean success = app.removeMember(memberID);

    if (success) {
      System.out.println("The member has been removed");
    } else {
      System.out.println("The member could not be found and can't be deleted");
    }

    chairman();
  }


  public void memberList() {
    System.out.println("-----------------------------------------------MEMBER LIST-----------------------------------------------------------");
    System.out.printf("%-4s %-30s %-35s %-10s %-16s %-8s %-7s\n", "ID", "Name", "Email", "DOT", "Phone Number", "Status", "Paid");
    System.out.println("---------------------------------------------------------------------------------------------------------------------");
    for (Member member : app.getAllMembers()) {
      System.out.println(member);
    }
    System.out.println("\nThe number of members in the list: " + app.getMemberCount() + "\n");

    chairman();
  }
/*
  //TODO MOVE TO SEPERATE CLASS
  public void cashier() {

  }

 */

  //TODO MOVE TO SEPERATE CLASS
  public void coaches() {

  }

  public void save() {


    try {
      System.out.println("Saving the database ...");
      app.saveDatabase();
      System.out.println("Saving database completed succesfully");
      System.out.println("You can now exit the application");
      fileSaved = true;
    } catch (DatabaseException exception) {
      System.out.println("\u001b[1;31m ERROR: Could not save file\u001b[m");
    }
    chairman();
  }

  public void exitMenu() {
    Scanner input = new Scanner(System.in);
    if (!fileSaved) {
      System.out.println("You haven't saved your members list, are you still sure you want to exit? yes[Y] or no[N]: ");
      char decision = input.next().toUpperCase(Locale.ROOT).charAt(0);
      switch (decision) {
        case 'Y' -> exit();
        case 'N' -> mainMenu();
      }
    } else
      exit();
  }

  public void exit() {
    System.out.println("\u001b[1;31mPROGRAM SHUTTING DOWN\u001b[m");
    System.exit(0);
  }


}



