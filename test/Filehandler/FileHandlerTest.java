package test.Filehandler;

import Filehandler.FileHandler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {

  @Test
  void loadMemberID() {
    FileHandler fileHandler = new FileHandler();
    Integer result = fileHandler.loadMemberID();
    assertEquals(5,result);
  }
}