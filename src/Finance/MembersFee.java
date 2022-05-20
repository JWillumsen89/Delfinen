package Finance;

import java.time.LocalDate;

public class MembersFee {
  //Member member = new Member();

  private int junior;
  private int senior;
  private int newAge;
  private double seniorGold;
  private String seniority;
  //  double[] fees = new double[5];
  public int[] fees = {1000, 1600, 25, 500};
  double category;


  //---------------------------constructor-----------------------------------

  public MembersFee(boolean aPmemebership, int juniorFee, int seniorFee, double seniorGoldFee, double category) {
    //activMebership = aPmemebership;
    junior = juniorFee;
    senior = seniorFee;
    seniorGold = seniorGoldFee;
    category = category;


  }

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

  public double paymentCategoryCalculator() {

    //TODO: evt switch case?

    if (newAge < 18) {
      category = fees[0];

    }
    if (newAge >= 18) {
      category = fees[1];
    }
    if (newAge >= 60) {
      category = fees[1] * 0.75;

    } else {
      //category = member.isActive() ;
    }
    return category;
  }

  public String seniorityCategory() {

    if(newAge < 18){
      seniority = "Junior";
    }
    else if (newAge >=60) {
      seniority = "Senior gold";
    }
    else if (newAge >= 18) {
      seniority = "Senior";
    }

    return seniority;
  }

}

