package finance;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MembersFeeTest {

  @Test
  void paymentCategoryCalculator() {
    MembersFee test = new MembersFee(); // test class
    Double result = test.paymentCategoryCalculator(20); // Argument for unit test is age
    assertEquals(1600,result); // expected for age above 20, should be 1600. Equals to "result"
  }
}