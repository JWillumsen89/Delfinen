package Delfin;

import Filehandler.FileHandler;
import Members.Member;
import UI.UserInterface;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {

  private ArrayList<Member> members = new ArrayList<>();

  //private ArrayList<Member> members = new ArrayList<>();


  public void start() {

    UserInterface ui = new UserInterface(this);

    ui.start();
  }

  public Main() {
    members = new ArrayList<>();
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

  //TODO: ændret "age" fra int til LocalDate, og ændre "gae" til age.getyear()


  public void createNewMember(String name, LocalDate age, int phoneNumber, String email, String memberID, char activeOrPassive, char paidOrNot) {
    Member member = new Member(name, age.getYear(), phoneNumber, email, memberID, activeOrPassive, paidOrNot);
    members.add(member);
    //System.out.println(member);
  }

  public boolean removeMember(String memberId) {
    Member member = findMemberById(memberId);
    if (member == null) {
      return false;
    } else {
      members.remove(member);
      return true;
    }
  }

  public Member findMemberById(String memberId) {
    for (Member member : members) {
      if (member.getMemberID().equalsIgnoreCase(memberId)) {
        return member;
      }
    }
    return null;
  }

  public Iterable<Member> getAllMembers() {
    return members;
  }

  public int getMemberCount() {
    return members.size();
  }
}
