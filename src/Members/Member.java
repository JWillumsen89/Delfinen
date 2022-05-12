package Members;

import java.util.ArrayList;

public class Member {

  private String name;
  private String email;
  private String memberID;
  //   private LocalDate dateOfBirth;

  private int age;
  private int phoneNumber;

  private boolean active;
  private boolean paid;

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

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }


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

    public boolean isActive() {
        return active;
    }

    public boolean isPaid() {
        return paid;
    }

    @Override
    public String toString() {
        return "Member\n" +
                "\nName: " + name +
                "\nEmail: " + email  +
                "\nMemberID: " + memberID +
                "\nAge: " + age +
                "\nPhoneNumber: " + phoneNumber +
                "\nActive(A) or Passive(P): " + activeOrPassive +
                "\nPaid(P) or Not(N): " + paidOrNot +
                '\n';
    }
}
