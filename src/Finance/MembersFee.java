package Finance;

import Members.Member;

import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class MembersFee {

  private boolean activMebership = false;
  private int junior;
  private int senior;
  private double seniorGold;




  //---------------------------constructor-----------------------------------

  public MembersFee(boolean aPmemebership, int juniorFee, int seniorFee, double seniorGoldFee){
    activMebership = aPmemebership;
    junior = juniorFee;
    senior = seniorFee;
    seniorGold = seniorGoldFee;


  }
  public MembersFee(){

  }

  //-------------------------------------gette----------------------------

  public int getJunior(){
    return junior;
  }
  public int getSenior(){
    return senior;
  }
  public double getSeniorGold(){
    return seniorGold;
  }

  public double[] fees(){
    double[] fees = new double[5];
    fees[0] = 1000; //junior
    fees[1] = 1600; //senior
    fees[3]= 25; //seniorGold discount
    fees[4] = 500; // passivtMember
    return fees;
    }

public void paymentCategoryCalculator(double category){
  Member memeberAge = new Member();
  //TODO: evt swichcase?

  if (memeberAge.getAge() < 18) {
     category = fees()[0];
  }if (memeberAge.getAge() >=18){
    category = fees()[1];
    } if  (memeberAge.getAge() >=60 ){
    category = fees()[1] *0.75;
  }else {
    category = memeberAge.isActive(fees([4]);
  }
  }




//TODO: hjælp

      public double ChangememebersFees(){
    Scanner sc = new Scanner(System.in);
    System.out.println("---------------Change memebrs fees------------------------\n");
    System.out.print("List:\n" + fees([0]) +"");
    sc.nextDouble();
  }

  //----------------------------is used in members fee------------------------------

  public int calculateAge(LocalDate age) {
    LocalDate curDate = LocalDate.now(); // nu bruger lokal tid og dato med now.
    if ((age != null) && (curDate != null)) {

      return Period.between(age, curDate).getYears();
    } else {
      return 0;
    }
  }


}



//TODO: For aktive medlemmer er kontingentet for ungdomssvømmere (under 18 år) 1000 kr. årligt, for seniorsvømmere (18 år og over) 1600 kr. årligt. For medlemmer over 60 år gives der 25 % rabat af seniortaksten. For passivt medlemskab er taksten 500 kr. årligt.