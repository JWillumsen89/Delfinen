package Finance;

import Members.Member;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class MembersFee {
  Member member = new Member();
  //private boolean activMebership = false;
  private int junior;
  private int senior;
  private int newAge;
  private double seniorGold;
  double[] fees = new double[5];


  //---------------------------constructor-----------------------------------

  public MembersFee(boolean aPmemebership, int juniorFee, int seniorFee, double seniorGoldFee) {
    //activMebership = aPmemebership;
    junior = juniorFee;
    senior = seniorFee;
    seniorGold = seniorGoldFee;


  }

  public MembersFee() {

  }

  //-------------------------------------gette----------------------------

  public int getJunior() {
    return junior;
  }

  public int getSenior() {
    return senior;
  }

  public double getSeniorGold() {
    return seniorGold;
  }

  public double[] fees() {

    fees[0] = 1000; //junior
    fees[1] = 1600; //senior
    fees[3] = 25; //seniorGold discount
    fees[4] = 500; // passivtMember
    return fees;
  }


  public void paymentCategoryCalculator(double category) {

    //TODO: evt swichcase?

    if (newAge < 18) {
      category = fees()[0];
    }
    if (newAge >= 18) {
      category = fees()[1];
    }
    if (newAge >= 60) {
      category = fees()[1] * 0.75;

    } else {
      //category = member.isActive() ;
    }
  }


//TODO: hjælp

  public double ChangememebersFees(double newFee) {
    Scanner sc = new Scanner(System.in);
    System.out.println("---------------Change  Delfin Svømmeklub medlems fees------------------------\n");
    System.out.print("Junior:............" + fees[0] + ",- kr.\n");
    System.out.print("Senior:........... " + fees[1] + ",- kr.\n");
    System.out.print("Senior Gold:...... " + fees[2] + ",- kr.\n");
    System.out.print("Passiv Membership: " + fees[3] + ",- kr.\n");
    System.out.println("Which would you like to change?");



    return newFee;
  }



/*
    for (double i: fees())
      System.out.println(i + "\n ");


    sc.nextDouble();
  }

 */

  //----------------------------is used in members fee------------------------------

  public int calculateAge(LocalDate age) {
    LocalDate curDate = LocalDate.now(); // nu bruger lokal tid og dato med now.
    if ((age != null) && (curDate != null)) {

      newAge = Period.between(age, curDate).getYears();
      return newAge;
    } else {
      return 0;
    }
  }


}



//TODO: For aktive medlemmer er kontingentet for ungdomssvømmere (under 18 år) 1000 kr. årligt, for seniorsvømmere (18 år og over) 1600 kr. årligt. For medlemmer over 60 år gives der 25 % rabat af seniortaksten. For passivt medlemskab er taksten 500 kr. årligt.