package Delfin;

import Filehandler.FileHandler;
import Finance.MembersFee;
import Members.Member;
import UI.UserInterface;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {

  private ArrayList<Member> members = new ArrayList<>();

  //private ArrayList<Member> members = new ArrayList<>();
  MembersFee memberFee = new MembersFee();

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

  //TODO: tilføj deres kategori. og konstruktør


  public void createNewMember(String name, LocalDate age, int phoneNumber, String email, Integer memberID, char activeOrPassive, char paidOrNot) {
    Member member = new Member(name, memberFee.getNewAge(), phoneNumber, email, memberID, activeOrPassive, paidOrNot);
    members.add(member);
    //System.out.println(member);
  }

  public boolean removeMember(Integer memberId) {
    Member member = findMemberById(memberId);
    if (member == null) {
      return false;
    } else {
      members.remove(member);
      return true;
    }
  }

  public Member findMemberById(Integer memberId) {
    for (Member member : members) {
      if (member.getMemberID() == memberId) {
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
