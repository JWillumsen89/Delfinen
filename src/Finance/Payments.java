package Finance;

import Delfin.Main;
import Members.Member;

import java.util.ArrayList;

public class Payments {
  MembersFee membersFee = new MembersFee();
  Member members = new Member();
  private final Main app;

  public Payments(Main app) {
    this.app = app;
  }

  public void allMissingPayments() {
    ArrayList<Member> restance = new ArrayList<>();

    System.out.println("-----------------------------------------------ALL MISSING PAYMENTS-----------------------------------------------------------");
    System.out.printf("%-4s %-30s %-35s %-10s %-16s %-8s %-7s\n", "ID", "Name", "Email", "DOT", "Phone Number", "Status", "Paid");
    System.out.println("------------------------------------------------------------------------------------------------------------------------------");

    for (Member member : app.getMembers()) {
      if (member.getPaidOrNot() == 'N') {
        restance.add(member);
        System.out.println(member);
        System.out.println("\nThe number of members in the list: " + app.getMemberCount() + "\n");
      }
    }
  }


  void sessonTotal() {
    ArrayList<Member> sessonPayments = new ArrayList<>();

    System.out.println("-----------------------------------------------ALL MISSING PAYMENTS-----------------------------------------------------------");
    System.out.printf(" %-30s%-10s\n", "Total", "DOT");
    System.out.println("------------------------------------------------------------------------------------------------------------------------------");

    for (Member member : app.getMembers()) {
      if (member.getAge() == membersFee.paymentCategoryCalculator()) ;
      {
        int result = (int) membersFee.getCategory();
        sessonPayments.add(member);
        System.out.println(result);
        System.out.println("\nThe number of members in the list: " + app.getMemberCount() + "\n");
      }
    }
  }





/*
  @Override
  public String toString() {
    return "Payments{" + "members=" + members + ", app=" + app +
        '}';
  }

 */
}
