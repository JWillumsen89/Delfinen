package Delfin;

import Filehandler.FileHandler;
import Members.Member;

import java.util.ArrayList;

public class Main {

  private ArrayList<Member> members;

  public Main() {
    members = new ArrayList<>();
  }

  public static void main(String[] args) {
    new Program().start();
  }

  public void loadDatabase() {
    FileHandler fileHandler = new FileHandler();
    members = fileHandler.loadMembersFromFile();
  }

  public void saveDatabase() {
    FileHandler fileHandler = new FileHandler();
    fileHandler.saveMembersToFile(members);
  }

}
