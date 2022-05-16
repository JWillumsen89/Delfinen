package Finance;

import Members.Member;

import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class MembersFee {
  Member member = new Member();

  private int junior;
  private int senior;
  private int newAge;
  private double seniorGold;
//  double[] fees = new double[5];
  int[] fees = {1000, 1600, 25, 500};


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



  /*
  double[] fees() { 1000

    fees[0] = 1000; //junior
    fees[1] = 1600; //senior
    fees[3] = 25; //seniorGold discount
    fees[4] = 500; // passivtMember
    return fees;
  }

   */


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



  public  void ChangememebersFees() {
    Scanner sc = new Scanner(System.in);
    CashierMenu chanhiem = new CashierMenu();
    System.out.println("---------------Change  Delfin Svømmeklub medlems fees------------------------\n");
    System.out.print("Junior:............" + fees[0] + ",- kr. Type 1\n");
    System.out.print("Senior:........... " + fees[1] + ",- kr. Type 2\n");
    System.out.print("Senior Gold:...... " + fees[2] + " % Type 3\n");
    System.out.print("Passiv Membership: " + fees[3] + ",- kr. Type 4\n");
    int feeToChange = sc.nextInt();
    System.out.println("What is the new membbers fee for this category?");
    int newMemberFee = sc.nextInt();
    for (int i = 0;i < fees.length;i++){
    fees[i] = feeToChange;
      if (fees[i]== feeToChange){
      fees[i]=newMemberFee;
      }
    }
    System.out.println("you have now change the fee from"+ feeToChange+" to " + newMemberFee);
    chanhiem.cashierMenuUi();
    }

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

  public  String printMenmbersFees() {
    for (int i = 0; i < fees.length; i++) {
      System.out.println(i);
    }
    return null;
  }

}