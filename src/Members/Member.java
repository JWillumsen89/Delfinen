package Members;

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

  private ArrayList<Member> members;
  private ArrayList<Member> restance;

  public Member(String name, int age, int phoneNumber, String email, String memberID, char activeOrPassive, char paidOrNot) {
    this.name = name;
    this.age = age;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.memberID = memberID;
    this.activeOrPassive = activeOrPassive;
    this.paidOrNot = paidOrNot;

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
      return "Member\n" +
          "\nName: " + name +
          "\nEmail: " + email +
          "\nMemberID: " + memberID +
          "\nAge: " + age +
          "\nPhoneNumber: " + phoneNumber +
          "\nActive member" +
          "\nPaid Membership fee" +
          '\n';
    }
    if (activeOrPassive == 'A' && paidOrNot == 'N') {
      return "Member\n" +
          "\nName: " + name +
          "\nEmail: " + email +
          "\nMemberID: " + memberID +
          "\nAge: " + age +
          "\nPhoneNumber: " + phoneNumber +
          "\nActive member" +
          "\nPaid Membership fee" +
          '\n';
    }
    if (activeOrPassive == 'P' && paidOrNot == 'P') {
      return "Member\n" +
          "\nName: " + name +
          "\nEmail: " + email +
          "\nMemberID: " + memberID +
          "\nAge: " + age +
          "\nPhoneNumber: " + phoneNumber +
          "\nPassive member" +
          "\nPaid Membership fee" +
          '\n';
    }

    if (activeOrPassive == 'P' && paidOrNot == 'N') {
      return "Member\n" +
          "\nName: " + name +
          "\nEmail: " + email +
          "\nMemberID: " + memberID +
          "\nAge: " + age +
          "\nPhoneNumber: " + phoneNumber +
          "\nPassive member" +
          "\nNot Paid Membership fee" +
          '\n';
    }
    return "Member\n" +
        "\nName: " + name +
        "\nEmail: " + email +
        "\nMemberID: " + memberID +
        "\nAge: " + age +
        "\nPhoneNumber: " + phoneNumber +
        "\nActive(A) or Passive(P): " + activeOrPassive +
        "\nPaid(P) or Not(N):" + paidOrNot +
        '\n';


  }
}
