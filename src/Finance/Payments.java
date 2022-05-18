package Finance;

import Delfin.Main;
import Members.Member;

import java.util.ArrayList;

public class Payments {

  Member members = new Member();
  private final Main app;

  public Payments(Main app) {
    this.app = app;
  }

  public void allMissingPayments() {
    ArrayList<Member> restance = new ArrayList<Member>();

    for (Member member : app.getMembers()) {
      if (member.getPaidOrNot()=='N') {
        restance.add(member);
        System.out.println(member); }
    }
  }
/*
  @Override
  public String toString() {
    return "Payments{" + "members=" + members + ", app=" + app +
        '}';
  }

 */
/*
  public void allMissingPayments() {
  }

 */
}
