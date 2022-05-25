package finance;



public class MembersFee {

  public final int[] fees = {1000, 1600, 25, 500};
  double category;

  //---------------------------constructor-----------------------------------
  public MembersFee() {

  }

  //-------------------------------------getter----------------------------

  public double paymentCategoryCalculator(double result) {

    if (result < 18) {
      category = fees[0];
    }
    if (result>= 18) {
      category = fees[1];
    }
    if (result >= 60) {
      category = fees[1] * 0.75;

    }
    return category;
  }

}