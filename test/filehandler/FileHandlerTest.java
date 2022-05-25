package filehandler;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {

  @Test
  void loadMemberID() {
    FileHandler filehandler = new FileHandler();
    Integer result = filehandler.loadMemberID();
    assertEquals(0, result); //0 should be updated to number in IDNumber.txt

  }
}