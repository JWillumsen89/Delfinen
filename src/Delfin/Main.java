package Delfin;

import Filehandler.FileHandler;
import Members.Member;
import UI.UserInterface;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {

  private ArrayList<Member> members = new ArrayList<>();



  public void start() {

    UserInterface ui = new UserInterface(this);

    ui.start();
  }


  public static void main(String[] args) {

    Main main = new Main();
    main.loadDatabase();
    main.start();

  }

  public void loadDatabase() {
    FileHandler fileHandler = new FileHandler();
    members = fileHandler.loadMembersFromFile();
  }

  public void saveDatabase() {
    FileHandler fileHandler = new FileHandler();
    fileHandler.saveMembersToFile(members);
  }

  public void createNewMember(String name, LocalDate age, int phoneNumber, String email, String memberID, boolean active, boolean paid) {
    Member member = new Member(); //var inden i meber(name, age, phoneNumber, email, memberID, active, paid)
    members.add(member);
    System.out.println(member);
  }

}
