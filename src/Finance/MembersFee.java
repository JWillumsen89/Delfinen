package Finance;

import Members.Member;

import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class MembersFee extends Member {
  //Member member = new Member();

  private int junior;
  private int senior;
  private int newAge;
  private double seniorGold;
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

  //-------------------------------------gette----------------------------

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

    //TODO: evt swichcase?

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



}