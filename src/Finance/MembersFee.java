package Finance;

import Members.Member;

public class MembersFee {
  //Member member = new Member();

  private int junior;
  private int senior;
  private int newAge;
  private double seniorGold;
  //  double[] fees = new double[5];
  public int[] fees = {1000, 1600, 25, 500};
  double category;


  //---------------------------constructor-----------------------------------

  /*
  public MembersFee(boolean activPassivMemebership, int juniorFee, int seniorFee, double seniorGoldFee, double category) {
    //activMebership = aPmemebership;
    junior = juniorFee;
    senior = seniorFee;
    seniorGold = seniorGoldFee;


  }

   */

  public MembersFee() {

  }

  //-------------------------------------getter----------------------------

  public int getJunior() {
    return junior;
  }

  public double getCategory() {
    return category;
  }

  public int getSenior() {
    return senior;
  }

  public double getSeniorGold() {
    return seniorGold;
  }

  public double paymentCategoryCalculator(double result) {

     Member member = new Member();


    if (result < 18) {
      category = fees[0];
    }
    if (result>= 18) {
      category = fees[1];
    }
    if (result >= 60) {
      category = fees[1] * 0.75;

    } else if (member.getActiveOrPassive()== 'P'){
      category = fees[3];
    }
    return category;
  }

}
