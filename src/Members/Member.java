package Members;

import java.time.LocalDate;
import java.util.ArrayList;

public class Member {

  private String name;
  private String email;
  private String memberID;
  //   private LocalDate dateOfBirth;

  private int age;
  private int phoneNumber;

  private char activeOrPassive;
  private char paidOrNot;
  private double memberCategory;

  private ArrayList<Member> members;
  private ArrayList<Member> restance;

  //TODO: add double memberCategory
  public Member(String name, int age, int phoneNumber, String email, String memberID, char activeOrPassive, char paidOrNot) {
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

  public String getMemberID() {
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
          "Name: " + name +
          "; Email: " + email +
          "; MemberID: " + memberID +
          "; Age: " + age +
          "; PhoneNumber: " + phoneNumber +
          "; Active member" +
          "; Paid Membership fee";
    }
    if (activeOrPassive == 'A'&& paidOrNot == 'N') {
      return
          "Name: " + name +
          "; Email: " + email +
          "; MemberID: " + memberID +
          "; Age: " + age +
          "; PhoneNumber: " + phoneNumber +
          "; Active member" +
          "; Not Paid Membership fee";
    }
    if (activeOrPassive == 'P' && paidOrNot == 'P') {
      return
          "Name: " + name +
          "; Email: " + email +
          "; MemberID: " + memberID +
          "; Age: " + age +
          "; PhoneNumber: " + phoneNumber +
          "; Passive member" +
          "; Paid Membership fee";
    }

    if (activeOrPassive == 'P' && paidOrNot == 'N') {
      return
          "Name: " + name +
          "; Email: " + email +
          "; MemberID: " + memberID +
          "; Age: " + age +
          "; PhoneNumber: " + phoneNumber +
          "; Passive member" +
          "; Not Paid Membership fee";
    }
    return
        "Name: " + name +
        "; Email: " + email +
        "; MemberID: " + memberID +
        "; Age: " + age +
        "; PhoneNumber: " + phoneNumber +
        "; Active(A) or Passive(P): " + activeOrPassive +
        "; Paid(P) or Not(N): test" + paidOrNot;


  }
}
