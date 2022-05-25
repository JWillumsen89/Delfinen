package members;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Member {
  //private LocalDate dateOfBirth; //TODO: Skal evt bruges hvis der skal stå fødselsdag og ikke alder i print
  private String email;
  private String name;
  private String coach;
  private String teams;
  private String butterfly, breastStroke, backCrawl, crawl;
  private int age;
  private int phoneNumber;
  private Integer memberID;
  private double paymentCategory;
  private char activeOrPassive;

  private char paidOrNot;

  private char competitorOrRegular;
  private boolean added = false;
  LocalDate curDate = LocalDate.now();

  public void setPaidOrNot(char paidOrNot) {
    this.paidOrNot = paidOrNot;
  }


  //Constructor normal member
  public Member(String name, int age, int phoneNumber, String email, Integer memberID, char activeOrPassive, char paidOrNot,
                double paymentCategory, String butterfly, String crawl, String backCrawl,
                String breastStroke, String coach, String teams, boolean added, char competitorOrRegular) {
    this.name = name;
    this.age = age;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.memberID = memberID;
    this.activeOrPassive = activeOrPassive;
    this.paidOrNot = paidOrNot;
    this.paymentCategory = paymentCategory;
    this.butterfly = butterfly;
    this.breastStroke = breastStroke;
    this.backCrawl = backCrawl;
    this.crawl = crawl;
    this.coach = coach;
    this.teams = teams;
    this.added = added;
    this.competitorOrRegular = competitorOrRegular;

  }

  public Member(String name, LocalDate age, int phoneNumber, String email, Integer memberId, char active, char paidOrNot, double paymentCategory, String butterfly, String crawl, String backCrawl, String breastStroke, String team, String coach, boolean added, char competitorOrRegular) {

  }

  public int calculateAge(LocalDate newAge) {
    if (newAge != null) {
      age = Period.between(newAge, curDate).getYears();
      return age;
    } else {
      return 0;
    }
  }

/*
  public String formatAge(String birthday) {
    curDate = LocalDate.parse(birthday);
    String dateToString = curDate.toString();
    dateToString = curDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
    System.out.println("My age" + dateToString);

    return dateToString;
  }
  public String convertDateToString (String birthday) {

    curDate = LocalDate.parse(birthday);


    String dateToString = curDate.toString();

    // Return the result
    return (dateToString);
  }

 */

  //-----------------Setter-----------------
  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPhoneNumber(Integer phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public void setActiveOrPassive(char activeOrPassive) {
    this.activeOrPassive = activeOrPassive;
  }

  public void setCoach(String coach) {
    this.coach = coach;
  }

  public void setTeams(String teams) {
    this.teams = teams;
  }

  public void setAdded(boolean added) {
    this.added = added;
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

  public String getButterfly() {
    return butterfly;
  }

  public String getBreastStroke() {
    return breastStroke;
  }

  public String getBackCrawl() {
    return backCrawl;
  }

  public String getCrawl() {
    return crawl;
  }

  public String getCoach() {
    return coach;
  }

  public String getTeams() {
    return teams;
  }

  public char getCompetitorOrRegular() {
    return competitorOrRegular;
  }

  public boolean isAdded() {
    return added;
  }

  @Override
  public String toString() {

    String active = "ACTIVE";
    String paid = "PAID";
    if (activeOrPassive == 'A' && paidOrNot == 'P') {
      return
          String.format("%04d %-30s %-35s %-10d %-16d %-8s %-9s %-10.2f %s", memberID,
              name, email, age, phoneNumber, active, paid, paymentCategory, "dkk");
    }
    String notPaid = "NOT PAID";
    if (activeOrPassive == 'A' && paidOrNot == 'N') {
      return
          String.format("%04d %-30s %-35s %-10d %-16d %-8s %-9s %-10.2f %s", memberID,
              name, email, age, phoneNumber, active, notPaid, paymentCategory, "dkk");
    }
    String passive = "PASSIVE";
    if (activeOrPassive == 'P' && paidOrNot == 'P') {
      return
          String.format("%04d %-30s %-35s %-10d %-16d %-8s %-9s %-10.2f %s", memberID,
              name, email, age, phoneNumber, passive, paid, paymentCategory, "dkk");
    }

    if (activeOrPassive == 'P' && paidOrNot == 'N') {
      return
          String.format("%04d %-30s %-35s %-10d %-16d %-8s %-9s %-10.2f %s", memberID,
              name, email, age, phoneNumber, passive, notPaid, paymentCategory, "dkk");
    }
    return
        String.format("%04d %-30s %-35s %-10d %-16d %-8s %-9s %-10.2f %s", memberID,
            name, email, age, phoneNumber, active, paid, paymentCategory, "dkk");
  }
}
