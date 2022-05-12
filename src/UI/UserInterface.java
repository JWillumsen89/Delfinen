package UI;


import Delfin.Main;
import Filehandler.DatabaseException;
import Members.Member;


import java.util.Scanner;

public class UserInterface {

  private boolean fileSaved = false;

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
        case 2 -> cashier();
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
        case 0 -> start();
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
    String name = input.nextLine();
    // TODO change to date of birth
    System.out.print("Age: ");
    int age = input.nextInt();
    input.nextLine();
    System.out.print("Email: ");
    String email = input.nextLine();
    System.out.print("Phonenumber: ");
    int phoneNumber = input.nextInt();
    input.nextLine(); // ScannerBug fix
    //TODO Autogenerat ID Number!
    System.out.print("memberID: ");
    String memberID = input.nextLine();
    System.out.print("Active: true/false ");
    boolean active = input.nextBoolean();
    boolean paid = false;

    app.createNewMember(name, age, phoneNumber, email, memberID, active, paid);

    chairman();

  }

  public void removeMember() {
    Scanner sc = new Scanner (System.in);
    System.out.println("Remove member\n");
    System.out.println("\nWhich member do you want to remove with the member ID");
    String memberID = sc.nextLine();

    //TODO System.out.println("Are you sure tha"); are you sure?

    boolean success = app.removeMember(memberID);

      if(success){
        System.out.println("The member has been removed");
      }
      else{
        System.out.println("The member could not be found and can't be deleted");
      }

  }


  public void memberList() {
    System.out.println("Member list");
      for(Member member : app.getAllMembers()){
        System.out.println(member);
      }
    System.out.println("The number of members in the list: "+app.getMemberCount());
  }

  //TODO MOVE TO SEPERATE CLASS
  public void cashier() {
    System.out.println("""
        Cashier menu
        ---------
        1) Add new member
        2) Remove member
        3) Member list
        3) Save member
                        
        0) Return to main
        """);



  }

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

  }

  public void exit() {
    System.out.println("\u001b[1;31mPROGRAM SHUTTING DOWN\u001b[m");
    System.exit(0);
  }
}
