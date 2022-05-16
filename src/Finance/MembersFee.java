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
public double getCategory(){
    return category;
}

  public int getSenior() {
    return senior;
  }

  public double getSeniorGold() {
    return seniorGold;
  }

  public int getNewAge(){
    return newAge;
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


  public  Object printMenmbersFees() {
    //for (int i = 0; i < fees.length; i++) {
    System.out.println("-----------Curent members fee pr.season-------------\n");
    System.out.println("Junior: "+fees [0]+",- kr. ");
    System.out.println("Senior: "+fees [1]+",- kr. ");
    System.out.println("Senior Gold: "+fees [2]+" % Discount off Senior fee");
    System.out.println("Passiv Membership: "+fees [3]+",- kr. \n\n");
    return null;
  }

}