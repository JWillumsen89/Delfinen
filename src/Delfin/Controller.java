package Delfin;

import Colors.FontColors;
import Filehandler.DatabaseException;
import Filehandler.FileHandler;
import Finance.MembersFee;
import Members.Member;
import UI.UserInterface;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Controller {

  private String name;
  private String email;


  private int phoneNumber;
  private double paymentCategory;

  private char active;

  private LocalDate age;

  boolean programRunning = true;
  boolean intError;
  boolean correctInput = false;

  Scanner input = new Scanner(System.in);

  //File handler
  FileHandler fileHandler = new FileHandler();
  private Integer memberId = fileHandler.loadMemberID();

  UserInterface ui = new UserInterface();
  MembersFee memberFee = new MembersFee();
  Member member = new Member();


  private ArrayList<Member> members = new ArrayList<>();
  private final ArrayList<Member> searchedForMembers = new ArrayList<>();


  //CHAIRMAN
  public void addMember() {
    System.out.println("\nCreate new member\n-----------------");
    typeName();
    typeDOTException();
    typeEmailError();
    typePhoneNumber();
    addMemberID();
    typeMemberStatus();
    scannerBugFix();
    saveMember();
  }

  public void addMemberID() {
    //Adds memberId
    memberId = fileHandler.loadMemberID() + 1;
  }

  public void changeActiveOrPassive(Member member) {
    typeMemberStatus();
    scannerBugFix();
    if (member != null) {
      member.setActiveOrPassive(active);
      System.out.println(member);
      save();
    } else {
      saveMember();
    }

  }

  public void changeAge() {

  }

  public void changeEmail(Member member) {
    typeEmail();
    if (member != null) {
      member.setEmail(email);
      System.out.println(member);
      save();
    } else {
      saveMember();
    }
  }

  public void changeName(Member member) {
    typeName();
    if (member != null) {
      member.setName(name);
      System.out.println(member);
      save();
    } else {
      saveMember();
    }
  }

  public void changePhoneNumber(Member member) {
    typePhoneNumber();
    scannerBugFix();
    if (member != null) {
    member.setPhoneNumber(phoneNumber);
      System.out.println(member);
      save();
    } else {
      saveMember();
    }
  }

  public void chairman() {
    ui.printChairmanMenu();
    String choice = ui.readChairmanUi();
    switch (choice) {
      case "0" -> start();
      case "1" -> addMember();
      case "2" -> removeMember();
      case "3" -> memberList();
      case "4" -> searchMember();
      default -> {
        wrongInput();
        chairman();
      }
    }
  }

  public void editMember(Member member) {
    ui.printChoiceEditMember();
    String decision = input.nextLine().toUpperCase(Locale.ROOT);
    switch (decision) {
      case "N" -> changeName(member);
      case "D" -> {
        System.out.println("Change date of birth: ");
        age = LocalDate.parse(String.valueOf(input.nextInt()));
        scannerBugFix();
        saveMember();
      }
      case "E" -> changeEmail(member);
      case "P" -> changePhoneNumber(member);
      case "M" -> changeActiveOrPassive(member);
      case "EXIT" -> chairman();
      default -> {
        wrongInput();
        editMember(member);
      }
    }
  }

  public void memberList() {
    ui.printMemberList();
    for (Member member : getAllMembers()) {
      System.out.println(member);
    }
    System.out.println("\nThe number of members in the list: " + getMemberCount() + "\n");

  }

  public void paymentCategory(double resultAge) {
    paymentCategory = memberFee.paymentCategoryCalculator(resultAge);
    System.out.println(paymentCategory); //TODO: NEEDS TO BE DELETED, TEST LINE
  }

  public Member pickAMember(int memberID) {
    Member member = findMemberById(memberID);
    if (member != null) {
      System.out.println(member);
      return member;
    } else {
      System.out.println("The member could not be found");
    }
    return null;
  }

  public void saveMember() {

    char paidOrNot = 'N';
    System.out.println("\nMember information:");
    System.out.println(FontColors.CYAN + "\nName: " + name + "\nDate of birth: " + age + "\nEmail: " + email + "\nPhone number: "
        + phoneNumber + "\nmember ID: " + memberId + "\nActive or passive: " + active + FontColors.RESET);
    System.out.print("\n\nAre the information correct? Yes[Y], edit[E] or discard[D]: ");
    String decision = input.nextLine().toUpperCase(Locale.ROOT);
    switch (decision) {
      case "Y" -> {
        createNewMember(name, age, phoneNumber, email, memberId, active, paidOrNot, paymentCategory);
        save();
        System.out.println(FontColors.BLUE + "\nMEMBER HAS BEEN SAVED!!\n" + FontColors.RESET);

      }
      case "E" -> editMember(null);
      case "D" -> System.out.println(FontColors.RED + "\nDISCARDED - Nothing have been saved\n" + FontColors.RESET);
    }
  }

  public void searchMember() {

    System.out.print("\nSEARCH MEMBER - Type [0] to return to menu or type member name/part of name: ");
    String memberName = input.nextLine().toLowerCase(Locale.ROOT);

    if (memberName.equals("0")) {
      chairman();
    } else {
      ArrayList<Member> members = findMemberByName(memberName);
      if (members.size() != 0) {
        for (Member member : members) {
          System.out.println(member);
        }
        System.out.print("\nType [0] to return to chairman menu or select a member by, ID number, that you want to edit: ");
        int memberID = input.nextInt();
        scannerBugFix();
        System.out.println();
        if (memberID == 0) {
          System.out.println("\nNo changes have been made.");
        } else {
          Member member1 = pickAMember(memberID);
          searchMemberMenu(member1);
        }
      } else {
        System.out.println(FontColors.RED + "The member could not be found" + FontColors.RESET);
        searchMember();
      }
    }
  }

  public void searchMemberMenu(Member member) {
    ui.printSearchMenu();
    String choice = input.nextLine();
    switch (choice) {
      case "0" -> chairman();
      case "1" -> removeMember();
      case "2" -> editMember(member);
      case "3" -> searchMember();
      default -> {
        wrongInput();
        searchMemberMenu(member);
      }
    }
  }

  public void typeDOT() {

    System.out.print("Enter date of birth in YYYY-MM-DD format: ");
    age = LocalDate.parse(input.nextLine());
    LocalDate temp = LocalDate.parse(age.toString());
    double resultAge = member.calculateAge(temp);
    paymentCategory(resultAge);
    System.out.println(member.getAge());
    correctInput = true;
  }

  public void typeDOTException() {
    while (!correctInput) {
      try {
        typeDOT();
      } catch (Exception e) {
        wrongInput();
      }
    }
  }

  public void typeEmail() {

    System.out.print("Email: ");
    email = input.nextLine().toUpperCase();
  }

  public void typeEmailError() {

    String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";

    typeEmail();
    while (!email.matches(regex)) {
      wrongInput();
      typeEmail();
    }
  }

  public void typeMemberStatus() {
    char active1 = 'A';
    char active2 = 'P';
    boolean answer = false;

    do {
      System.out.print("Active[A] or Passive[P]: ");
      active = input.next().toUpperCase(Locale.ROOT).charAt(0);
      if (active == active1) {
        answer = true;
      } else if (active == active2) {
        answer = true;
      } else {
        wrongInput();
      }
    }
    while (!answer);
  }

  public void typeName() {
    System.out.print("Enter Name: ");
    name = input.nextLine().toUpperCase();
    while (!name.matches("^[a-zA-Z ]*$")) {
      wrongInput();
      System.out.print("Please enter a valid name!: ");
      name = input.nextLine().toUpperCase();
    }
  }

  public void typePhoneNumber() {
    phoneNumber = 0;
    intError = true;
    System.out.print("Phone number: ");
    while (intError) {
      if (input.hasNextInt()) {
        phoneNumber = input.nextInt();
      } else {
        wrongInput();
        System.out.print("Please enter a valid phone number: ");
        input.next();
        continue;
      }
      intError = false;
    }
  }

  //TODO: ADD their category and constructor
  public void createNewMember(String name, LocalDate age, int phoneNumber, String email, Integer memberID, char activeOrPassive, char paidOrNot, double paymentCategory) {
    Member newMember = new Member(name, member.getAge(), phoneNumber, email, memberID, activeOrPassive, paidOrNot, paymentCategory);
    members.add(newMember);
  }

  public void removeMember() {
    ui.printRemoveMember();
    memberId = 0;
    intError = true;
    while (intError) {
      if (input.hasNextInt()) {
        memberId = input.nextInt();
      } else {
        wrongInput();
        ui.printRemoveMember();
        input.next();
        continue;
      }
      intError = false;
    }
    scannerBugFix();

    Member member = findMemberById(memberId);
    if (memberId == 0)
      System.out.println("EXITING REMOVE MENU");
    else if (member == null) {
      System.out.println("The member could not be found and can't be deleted");
    } else {
      System.out.println("\n" + member);
      System.out.print("\nAre you sure you want to delete this member? yes [Y] or no[N]: ");
      String decision = input.nextLine();
      if (decision.equalsIgnoreCase("Y")) {
        members.remove(member);
        System.out.println("\u001b[1;31mTHE MEMBER HAVE BEEN DELETED\u001b[m");
        save();
      } else
        System.out.println("\nYOU DIDNT DELETE THE MEMBER");
    }
  }

  //CASHIER

  public void cashier() {
    ui.printCashierMenu();
    String choice = input.nextLine();
    switch (choice) {
      case "1" -> missingPayments();
      case "2" -> changeMemberFees();
      case "3" -> seeAllPayments();
      case "4" -> ui.printMembersFees();
      case "5" -> exit();
      case "0" -> start();
      default -> {
        wrongInput();
        cashier();
      }

    }
  }

  public void changeMemberFees() {
    ui.printChoseToChangeFees();
    int feeToChange = input.nextInt();
    int newMemberFee = input.nextInt();
    for (int i = 0; i < memberFee.fees.length; i++) {
      memberFee.fees[i] = feeToChange;
      if (memberFee.fees[i] == feeToChange) {
        memberFee.fees[i] = newMemberFee;
      }
    }
    System.out.println("you have now change the fee from" + feeToChange + " to " + newMemberFee);
    cashier();
  }

  //COACH

  public void coaches() {

  }

  //FILE HANDLER

  public void loadDatabase() {
    FileHandler fileHandler = new FileHandler();
    members = fileHandler.loadMembersFromFile();
    memberId = fileHandler.loadMemberID();
  }

  public void save() {

    try {
      System.out.println(FontColors.BLUE + "Saving the database ...");
      saveDatabase();
      System.out.println("Saving database completed successfully");
      System.out.println("You can now exit the application" + FontColors.RESET);
    } catch (DatabaseException exception) {
      System.out.println(FontColors.RED + "ERROR: Could not save file" + FontColors.RESET);
    }
  }

  public void saveDatabase() {
    FileHandler fileHandler = new FileHandler();
    fileHandler.saveMembersToFile(members);
    fileHandler.saveMemberID(memberId);
  }

  //SYSTEM HANDLER

  public void exit() {
    System.out.println("\u001b[1;31mPROGRAM SHUTTING DOWN\u001b[m");
    System.exit(0);
  }

  public Member findMemberById(Integer memberId) {
    for (Member member : members) {
      if (member.getMemberID().equals(memberId)) {
        return member;
      }
    }
    return null;
  }

  public ArrayList<Member> findMemberByName(String name) {
    searchedForMembers.clear();
    for (Member member : members) {
      if (member.getName().toLowerCase().contains(name)) {
        searchedForMembers.add(member);
      }
    }
    return searchedForMembers;
  }

  public Iterable<Member> getAllMembers() {
    return members;
  }

  public int getMemberCount() {
    return members.size();
  }

  public void wrongInput() {
    String wrongInput = FontColors.RED + "WRONG INPUT! - PLEASE TRY AGAIN" + FontColors.RESET;
    System.out.println(wrongInput);
  }

  public void scannerBugFix() {
    input.nextLine();
  }

  public void start() {

    ui.displayWelcomeMessage();
    String choice = ui.mainMenu();
    while (programRunning) {
      switch (choice) {
        case "0" -> exit();
        case "1" -> chairman();
        case "2" -> cashier();
        case "3" -> coaches();
        default -> {
          wrongInput();
          start();
        }
      }
    }
  }

  public void missingPayments() {
    ArrayList<Member> restance = new ArrayList<>();
    for (Member member : members) {
      if (member.getPaidOrNot() == 'N' & member.getActiveOrPassive() == 'A') {
        restance.add(member);
        System.out.println(member.toString());
      }
    }
    System.out.println("\nThe number of members that have restance is: " + restance.size() + "\n");
  }

  public void seeAllPayments() {
    double totalPayment = 0.0;
    for (Member member : members) {
      if (member.getPaidOrNot() == 'P' & member.getActiveOrPassive() == 'A') {
        totalPayment += member.getPaymentCategory();
        System.out.println(member.toString());
      }
    }
    System.out.println("\n The total income from activ members this sesson is: " + totalPayment + " dkk\n");
  }

}
