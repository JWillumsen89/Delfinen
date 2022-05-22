package Delfin;

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
    typeEmail();
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
    member.setActiveOrPassive(active);
    System.out.println(member);
  }

  public void changeAge() {

  }

  public void changeEmail(Member member) {
    typeEmail();
    member.setEmail(email);
    System.out.println(member);
  }

  public void changeName(Member member) {
    typeName();
    member.setName(name);
    System.out.println(member);
  }

  public void changePhoneNumber(Member member) {
    typePhoneNumber();
    member.setPhoneNumber(phoneNumber);
    System.out.println(member);
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
        System.out.println("Invalid decision");
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
    System.out.println("\nName: " + name + "\nDate of birth: " + age + "\nEmail: " + email + "\nPhone number: "
        + phoneNumber + "\nmember ID: " + memberId + "\nActive or passive: " + active);
    System.out.print("\n\nAre the information correct? Yes[Y], edit[E] or discard[D]: ");
    String decision = input.nextLine().toUpperCase(Locale.ROOT);
    switch (decision) {
      case "Y" -> {
        createNewMember(name, age, phoneNumber, email, memberId, active, paidOrNot, paymentCategory);
        save();
        System.out.println("\nMEMBER HAS BEEN SAVED!!\n");

      }
      case "E" -> editMember(null);
      case "D" -> System.out.println("\nDISCARDED - Nothing have been saved\n");
    }
  }

  public void searchMember() {

    System.out.print("\nSEARCH MEMBER - Type name or part of name: ");
    String memberName = input.nextLine().toLowerCase(Locale.ROOT);
    System.out.println(" ");

    ArrayList<Member> members = findMemberByName2(memberName);
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
      System.out.println("The member could not be found");
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


  }

  public void paymentCategory(double resultAge) {
    paymentCategory = memberFee.paymentCategoryCalculator(resultAge);
    System.out.println(paymentCategory); //TODO: NEEDS TO BE DELETED, TEST LINE
  }

  public void typeDOTException() {
    try {
      typeDOT();
    } catch (Exception e) {
      wrongInput();
      typeDOT();
    }
  }

  public void typeEmail() {
    try {
      System.out.print("Email: ");
      email = input.nextLine();
    } catch (Exception e) {
      wrongInput();
      System.out.print("Email: ");
      email = input.nextLine();
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
        System.out.println("Active member");
        answer = true;
      } else if (active == active2) {
        System.out.println("Passive member");
        answer = true;
      } else {
        System.out.println("Invalid character");
      }
    }
    while (!answer);
  }

  public void typeName() {
    System.out.print("Name: ");
    name = input.nextLine();
  }

  public void typePhoneNumber() {
    phoneNumber = 0;
    boolean bError = true;
    System.out.print("Phone number: ");
    while (bError) {
      if (input.hasNextInt()) {
        phoneNumber = input.nextInt();
      } else {
        wrongInput();
        System.out.print("Phone number: ");
        input.next();
        continue;
      }
      bError = false;
    }
  }


  //TODO: ADD their category and constructor
  public void createNewMember(String name, LocalDate age, int phoneNumber, String email, Integer memberID, char activeOrPassive, char paidOrNot, double paymentCategory) {
    Member newMember = new Member(name, member.getAge(), phoneNumber, email, memberID, activeOrPassive, paidOrNot, paymentCategory);
    members.add(newMember);
    //System.out.println(member);
  }

  public void removeMember() {
    ui.printRemoveMember();
    memberId = 0;
    boolean bError = true;
    while (bError) {
      if (input.hasNextInt()) {
        memberId = input.nextInt();
      } else {
        wrongInput();
        ui.printRemoveMember();
        input.next();
        continue;
      }
      bError = false;
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
      //case 1 -> missingPayments();
      case "2" -> changeMemberFees();
      //case 3 -> seeAllPayments();
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
      System.out.println("Saving the database ...");
      saveDatabase();
      System.out.println("Saving database completed successfully");
      System.out.println("You can now exit the application");
    } catch (DatabaseException exception) {
      System.out.println("\u001b[1;31m ERROR: Could not save file\u001b[m");
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

  public ArrayList<Member> findMemberByName2(String name) {
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
    String wrongInput = "\u001b[1;31mWRONG INPUT! - PLEASE TRY AGAIN\u001b[m";
    System.out.println(wrongInput);
  }

  public void scannerBugFix() {
    input.nextLine();
  }

  public void start() {

    ui.displayWelcomeMessage();
    String choice = ui.mainMenu();
    while (true) {
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
}
