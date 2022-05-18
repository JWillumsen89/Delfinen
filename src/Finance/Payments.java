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

    for (Member member : app.getMembers()) {
      if (member.getPaidOrNot() == 'N') {
        restance.add(member);
        System.out.println(member);
      }
    }
  }


  void sessonTotal() {
    ArrayList<Member> sessonPayments = new ArrayList<>();
    for (Member member : app.getMembers()) {
      if (member.getAge() == membersFee.paymentCategoryCalculator()) ;
      {
        int result = (int) membersFee.getCategory();
        sessonPayments.add(member);
        System.out.println(result);
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
