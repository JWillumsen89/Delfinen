package UI;

import java.util.Scanner;

public class UserInterface {

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
        Main menu
        ---------
        1) Add new member
        2) Remove member
                        
        0) Return to main
        """);
    Scanner input = new Scanner(System.in);
    int choice = input.nextInt();
    while (choice < 0 || choice > 2) {
      System.out.println("Only values 0-2 allowed");
      choice = input.nextInt();
    }
    while (true) {
      switch (choice) {
        case 0 -> start();
        case 1 -> addMember();
        case 2 -> removeMember();
      }
    }
  }

  public void addMember() {

  }

  public void removeMember() {

  }

  //TODO MOVE TO SEPERATE CLASS
  public void cashier() {

  }

  //TODO MOVE TO SEPERATE CLASS
  public void coaches() {

  }

  public void exit() {
    System.out.println("\u001b[1;31mPROGRAM SHUTTING DOWN\u001b[m");
    System.exit(0);
  }
}
