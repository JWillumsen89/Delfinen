package delfin;

public class Main {

  public static void main(String[] args) {

    Controller controller = new Controller();
    controller.loadDatabase();
    controller.start();
  }
}
