package UI;

import Delfin.Main;

import java.util.Scanner;

public class Chairman{

  UserInterface ui;
  Main app;

  //TODO MOVE TO SEPERATE CLASS
  public void chairman() {

    System.out.println("""
        Main menu
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
    }

    switch (choice) {
      case 0 -> ui.start();
      case 1 -> addMember();
      case 2 -> removeMember();
      case 3 -> memberList();
      case 4 -> ui.save();

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

  }

  public void memberList() {

  }
}
