package Delfin;

import Filehandler.FileHandler;
import Finance.MembersFee;
import Members.Member;
import UI.UserInterface;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {


  public static void main(String[] args) {

    Controller contoller = new Controller();
    contoller.loadDatabase();
    contoller.start();

  }
}
