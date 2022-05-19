package Delfin;

import Filehandler.FileHandler;
import Finance.MembersFee;
import Members.Member;
import UI.UserInterface;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {

  private Integer memberId;
  private ArrayList<Member> members = new ArrayList<>();
  private ArrayList<Member> searchedForMembers = new ArrayList<>();
  ArrayList<Member> restance = new ArrayList<>();

  //MAIN!!!
  public static void main(String[] args) {

    Main main = new Main();
    main.loadDatabase();
    main.start();

  }





  public ArrayList<Member> getMembers() {
    return members;
  }



  public void setMemberId(Integer memberId) {
    this.memberId = memberId;
  }



  MembersFee memberFee = new MembersFee();

  public void start() {

    UserInterface ui = new UserInterface();

    ui.start();  //TODO:her crsher den!!
  }

  public void loadDatabase() {
    FileHandler fileHandler = new FileHandler();
    members = fileHandler.loadMembersFromFile();
    fileHandler.loadMemberID();
  }

  public void saveDatabase() {
    FileHandler fileHandler = new FileHandler();
    fileHandler.saveMembersToFile(members);
    //memberId = fileHandler.getDataValue();
    fileHandler.saveMemberID(memberId);
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

  public Member searchMember(String name) {
    Member member = findMemberByName(name);
    if (member == null) {
      return null;
    } else {
      return member;
    }
  }

  public Member findMemberByName(String name) {
    for (Member member : members) {
      if (member.getName().equalsIgnoreCase(name)) {
        return member;
      }
    }
    return null;
  }

  //TODO Make search more flexible
  public ArrayList<Member> findMemberByName2(String name) {
    searchedForMembers.clear();
    for (Member member : members) {
      if (member.getName().equalsIgnoreCase(name)) {
        searchedForMembers.add(member);
      }
    }
    return searchedForMembers;
  }

  public ArrayList<Member> findMemberByNotPaid() {
    restance.clear();
    for (Member member : members) {
      if (member.getPaidOrNot() == 'N') {
        restance.add(member);
      }
    }
    return restance;
  }

  public Iterable<Member> getAllMembers() {
    return members;
  }

  public int getMemberCount() {
    return members.size();
  }






}
