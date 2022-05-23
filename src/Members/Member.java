package Members;

import java.time.LocalDate;
import java.time.Period;

public class Member {
  //private LocalDate dateOfBirth; //TODO: Skal evt bruges hvis der skal stå fødselsdag og ikke alder i print
  private String email;
  private String name;
  private int age;
  private int phoneNumber;
  private char activeOrPassive;
  private char paidOrNot;
  private Integer memberID;
  private double paymentCategory;

  //Constructors
  public Member(String name, int age, int phoneNumber, String email, Integer memberID, char activeOrPassive, char paidOrNot, double paymentCategory) {
    this.name = name;
    this.age = age;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.memberID = memberID;
    this.activeOrPassive = activeOrPassive;
    this.paidOrNot = paidOrNot;
    this.paymentCategory = paymentCategory;

  }

  public Member() {

  }

  public int calculateAge(LocalDate newAge) {
    LocalDate curDate = LocalDate.now(); // nu bruger lokal tid og dato med now.
    if (newAge != null) {
// gem fødselsdag under member som attribute

      age = Period.between(newAge, curDate).getYears();
      return age;
    } else {
      return 0;
    }
  }

  //-----------------Setter-----------------

  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  public void setPaymentCategory(double paymentCategory){
    this.paymentCategory = paymentCategory;
  }

  public void setPhoneNumber(Integer phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public void setActiveOrPassive(char activeOrPassive) {
    this.activeOrPassive = activeOrPassive;
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

  public Integer getMemberID() {
    return memberID;
  }

  public char getActiveOrPassive() {
    return activeOrPassive;
  }

  public char getPaidOrNot() {
    return paidOrNot;
  }

  public double getPaymentCategory() {
    return paymentCategory;
  }

  @Override
  public String toString() {

    String active = "ACTIVE";
    String paid = "PAID";
    if (activeOrPassive == 'A' && paidOrNot == 'P') {
      return
          String.format("%04d %-30s %-35s %-10d %-16d %-8s %-9s %-10.2f %s", memberID, name, email, age, phoneNumber, active, paid, paymentCategory, "dkk");
    }
    String notPaid = "NOT PAID";
    if (activeOrPassive == 'A' && paidOrNot == 'N') {
      return
          String.format("%04d %-30s %-35s %-10d %-16d %-8s %-9s %-10.2f %s", memberID, name, email, age, phoneNumber, active, notPaid, paymentCategory, "dkk");
    }
    String passive = "PASSIVE";
    if (activeOrPassive == 'P' && paidOrNot == 'P') {
      return
          String.format("%04d %-30s %-35s %-10d %-16d %-8s %-9s %-10.2f %s", memberID, name, email, age, phoneNumber, passive, paid, paymentCategory, "dkk");
    }

    if (activeOrPassive == 'P' && paidOrNot == 'N') {
      return
          String.format("%04d %-30s %-35s %-10d %-16d %-8s %-9s %-10.2f %s", memberID, name, email, age, phoneNumber, passive, notPaid, paymentCategory, "dkk");
    }
    return
        String.format("%04d %-30s %-35s %-10d %-16d %-8s %-9s %-10.2f %s", memberID, name, email, age, phoneNumber, active, paid, paymentCategory, "dkk");
  }
}
