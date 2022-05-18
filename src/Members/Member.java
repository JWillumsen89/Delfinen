package Members;

import com.sun.tools.javac.Main;

import java.util.ArrayList;
import java.util.Objects;

public class Member {

  private String name;
  private String email;
  private String active = "ACTIVE";
  private String passive = "PASSIVE";
  private String paid = "PAID";
  private String notPaid = "NOT PAID";
  //   private LocalDate dateOfBirth;

  private Integer memberID;
  private int age;
  private int phoneNumber;

  private char activeOrPassive;
  private char paidOrNot;
  private double memberCategory;

  //private ArrayList<Member> members;
  private ArrayList<Member> restance;

  //TODO: add double memberCategory
  public Member(String name, int age, int phoneNumber, String email, Integer memberID, char activeOrPassive, char paidOrNot) {
    this.name = name;
    this.age = age;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.memberID = memberID;
    this.activeOrPassive = activeOrPassive;
    this.paidOrNot = paidOrNot;
    this.memberCategory = memberCategory;


    }
    public Member(){

  }


  //-----------------Setter-----------------



  //-----------------Getter-----------------

  public String getNotPaid(){ //TODO: HUSK
    return notPaid;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }



  public int getPhoneNumber() {
    return phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public Integer getMemberID() {
    return memberID;
  }

  public char getActiveOrPassive() {
    return activeOrPassive;
  }

  public char getPaidOrNot() {
    return paidOrNot;
  }

  @Override
  public String toString() {

    if (activeOrPassive == 'A' && paidOrNot == 'P') {
      return
          String.format("%04d %-30s %-35s %-10d %-16d %-8s %-7s", memberID, name, email, age, phoneNumber, active, paid);
    }
    if (activeOrPassive == 'A' && paidOrNot == 'N') {
      return
          String.format("%04d %-30s %-35s %-10d %-16d %-8s %-7s", memberID, name, email, age, phoneNumber, active, notPaid);
    }
    if (activeOrPassive == 'P' && paidOrNot == 'P') {
      return
          String.format("%04d %-30s %-35s %-10d %-16d %-8s %-7s", memberID, name, email, age, phoneNumber, passive, paid);
    }

    if (activeOrPassive == 'P' && paidOrNot == 'N') {
      return
          String.format("%04d %-30s %-35s %-10d %-16d %-8s %-7s", memberID, name, email, age, phoneNumber, passive, notPaid);
    }
    return
        String.format("%04d %-30s %-35s %-10d %-16d %-8s %-7s", memberID, name, email, age, phoneNumber, active, paid);
  }










/*
  public ArrayList<Member> getRestance() {
    Main main = new Main;
    if (Objects.equals(paidOrNot, "n")){
      restance.add(getPaidOrNot());
      System.out.println(restance);
    }
    return restance;
  }

 */
}
