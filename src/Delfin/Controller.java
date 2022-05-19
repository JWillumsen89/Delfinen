package Delfin;

import Filehandler.DatabaseException;
import Filehandler.FileHandler;
import Finance.CashierMenu;
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

  FileHandler fileHandler = new FileHandler();
  private Integer memberID = fileHandler.loadMemberID();

  //   private LocalDate dateOfBirth;
  private LocalDate age;
  private int phoneNumber;

  private char active;
  private char paidOrNot;
  CashierMenu goToMenueCashier = new CashierMenu();
  MembersFee memberFee = new MembersFee();
  Scanner input = new Scanner(System.in);


  private Integer memberId;
  private ArrayList<Member> members = new ArrayList<>();
  private ArrayList<Member> searchedForMembers = new ArrayList<>();
  UserInterface ui = new UserInterface();

  public ArrayList<Member> getMembers() {
    return members;
  }

  public void setMemberId(Integer memberId) {
    this.memberId = memberId;
  }

  //MembersFee memberFee = new MembersFee();

  public void start() {

    ui.displayWelcomeMsg();
    int choice = ui.mainMenu();
    while (true) {
      switch (choice) {
        case 0 -> exit();
        case 1 -> chairman();
        case 2 -> goToMenueCashier.cashierMenuUi();
        case 3 -> coaches();
      }
    }
  }

  public void chairman() {
    ui.printChaimanMenu();
    int choice = ui.readChaimanUi();
    switch (choice) {
      case 0 -> start();
      case 1 -> addMember();
      case 2 -> removeMember();
      case 3 -> memberList();
      case 4 -> searchMember();
    }
  }

  public void memberList() {
    ui.printMemberList();
    for (Member member : getAllMembers()) {
      System.out.println(member);
    }
    System.out.println("\nThe number of members in the list: " + getMemberCount() + "\n");

    ui.printChaimanMenu();
  }

  public void searchMember() {

    Scanner sc = new Scanner(System.in);
    System.out.print("SEARCH MEMBER - Type name of member: ");
    String memberName = sc.nextLine();

    ArrayList<Member> members = findMemberByName2(memberName);
    if (members.size() != 0) {
      for (Member member : members) {
        System.out.println(member);
      }
      System.out.print("Select a member by ID number that you want to edit: ");
      int memberID = input.nextInt();
      Member member = pickAMember(memberID);
      searchMemberMenu();
    } else {
      System.out.println("The member could not be found");
      ui.printChaimanMenu();
    }
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

  public void searchMemberMenu() {
    ui.printScMenu();
    Scanner input = new Scanner(System.in);
    int choice = input.nextInt();
    input.nextLine(); //Scanner bug fix
    while (choice < 0 || choice > 3) {
      System.out.println("Only values 0-3 allowed");
      choice = input.nextInt();
      input.nextLine(); //Scannerbug
    }

    switch (choice) {
      case 0 -> ui.printChaimanMenu();
      case 1 -> removeMember();
      case 2 -> ui.printChoiceEditMember();
      case 3 -> searchMember();
    }
  }

  public void loadDatabase() {
    FileHandler fileHandler = new FileHandler();
    members = fileHandler.loadMembersFromFile();
    fileHandler.loadMemberID();
  }

  public void addMember() {
    System.out.println("Create new member\n-----------------");
    Scanner input = new Scanner(System.in);
    System.out.print("Name: ");
    name = input.nextLine();

    //------------------ageCalulatorUsed in Payments-------------------------------
    System.out.print("Enter date of birth in YYYY-MM-DD format: ");
    age = LocalDate.parse(input.nextLine());
    LocalDate temp = LocalDate.parse(age.toString());
    memberFee.calculateAge(temp);
    int result = (int) memberFee.paymentCategoryCalculator();
    System.out.println(result); //TODO: skal slettes, kun til test(linjen)
    System.out.println(memberFee.getNewAge());


//----------------------------slut----------------------

    System.out.print("Email: ");
    email = input.nextLine();
    System.out.print("Phonenumber: ");
    phoneNumber = input.nextInt();
    input.nextLine(); // ScannerBug fix

    memberID = fileHandler.loadMemberID() + 1;


    char active1 = 'A';
    char active2 = 'P';
    boolean answer = false;

    do {
      System.out.print("Active: [A] or Passive: [P]");
      active = input.next().toUpperCase(Locale.ROOT).charAt(0);
      if (active == active1) {
        System.out.println("Active member");
        answer = true;
      } else if (active == active2) {
        System.out.println("Passive member");
        answer = true;
      } else {
        System.out.println("Invalid char");
      }
    }
    while (!answer);


    input.nextLine();//Scanner bug fix
    saveMember();

  }

  public void saveDatabase() {
    FileHandler fileHandler = new FileHandler();
    fileHandler.saveMembersToFile(members);
    fileHandler.saveMemberID(memberId);
  }

  public void coaches() {

  }

  public void saveMember() {

    char paidOrNot = 'N';
    System.out.println("\nMember information:");
    System.out.println("\nName: " + name + "\nDate of birth: " + age + "\nEmail: " + email + "\nPhone number: "
        + phoneNumber + "\nmember ID: " + memberID + "\nActive or passive: " + active);
    System.out.print("\n\nAre the information correct? Yes[Y], edit[E] or discard[D]: ");
    String decision = input.nextLine().toUpperCase(Locale.ROOT);
    switch (decision) {
      case "Y" -> {
        createNewMember(name, age, phoneNumber, email, memberID, active, paidOrNot);
        setMemberId(memberID);
        save();
        System.out.println("\nMEMBER HAS BEEN SAVED!!\n");

      }
      case "E" -> {
        editMember(null);
      }
      case "D" -> {
        System.out.println("\nDISCARDED - Nothing have been saved\n");

      }
    }
  }

  public void editMember(Member member) {
    String decision = input.nextLine().toUpperCase(Locale.ROOT);
    switch (decision) {
      case "N" -> changeName(member);
      case "D" -> {
        System.out.println("Change date of birth: ");
        age = LocalDate.parse(String.valueOf(input.nextInt()));
        input.nextLine(); //Scanner bug fix
        saveMember();
      }
      case "E" -> {
        changeEmail(member);
      }
      case "P" -> {
        changePhoneNumber(member);
      }
      case "M" -> {
        changeActiveOrPassive(member);
      }
      case "EXIT" -> ui.printChaimanMenu();
      default -> {
        System.out.println("Invalid decision");
        ui.printChoiceEditMember();
      }
    }
    save();
  }

  public void changeName(Member member) {
    System.out.println("Change name: ");
    name = input.nextLine();
    if (member != null) {
      member.setName(name);
      System.out.println(member);
    } else {
      saveMember();
    }
  }

  public void changeAge() {

  }

  public void changeEmail(Member member) {
    System.out.println("Change email: ");
    email = input.nextLine();
    if (member != null) {
      member.setEmail(email);
      System.out.println(member);
    } else {
      saveMember();
    }
  }

  public void changePhoneNumber(Member member) {
    System.out.println("Change phone number: ");
    phoneNumber = input.nextInt();
    input.nextLine(); //Scanner bug fix
    if (member != null) {
      member.setPhoneNumber(phoneNumber);
      System.out.println(member);
    } else {
      saveMember();
    }
  }

  public void changeActiveOrPassive(Member member) {
    System.out.println("Change member status to Active: [A] or Passive: [P]: ");
    active = input.next().toUpperCase(Locale.ROOT).charAt(0);
    input.nextLine(); //Scanner bug fix
    if (member != null) {
      member.setActiveOrPassive(active);
      System.out.println(member);
    } else {
      saveMember();
    }
  }

  public void save() {

    try {
      System.out.println("Saving the database ...");
      saveDatabase();
      System.out.println("Saving database completed succesfully");
      System.out.println("You can now exit the application");
    } catch (DatabaseException exception) {
      System.out.println("\u001b[1;31m ERROR: Could not save file\u001b[m");
    }

  }


  //TODO: tilføj deres kategori. og konstruktør
  public void createNewMember(String name, LocalDate age, int phoneNumber, String email, Integer memberID, char activeOrPassive, char paidOrNot) {
    Member member = new Member(name, memberFee.getNewAge(), phoneNumber, email, memberID, activeOrPassive, paidOrNot);
    members.add(member);
    //System.out.println(member);
  }

  public void removeMember() {
    ui.printRemoveMember();
    Scanner sc = new Scanner(System.in);
    Integer memberId = sc.nextInt();

    //TODO System.out.println("Are you sure tha"); are you sure?

    Member member = findMemberById(memberId);
    if (member == null) {
      System.out.println("The member could not be found and can't be deleted");
    } else {
      members.remove(member);
      System.out.println("The member has been removed");
      save();
    }
    ui.printChaimanMenu();
  }


  public Member findMemberById(Integer memberId) {
    for (Member member : members) {
      if (member.getMemberID() == memberId) {
        return member;
      }
    }
    return null;
  }

  public Member searchMember(String name) {
    Member member = findMemberByName(name);
    if (member == null) {
      return null;
    } else {
      return member;
    }
  }

  public Member findMemberByName(String name) {
    for (Member member : members) {
      if (member.getName().equalsIgnoreCase(name)) {
        return member;
      }
    }
    return null;
  }

  //TODO Make search more flexible
  public ArrayList<Member> findMemberByName2(String name) {
    searchedForMembers.clear();
    for (Member member : members) {
      if (member.getName().equalsIgnoreCase(name)) {
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

  public void exit() {
    System.out.println("\u001b[1;31mPROGRAM SHUTTING DOWN\u001b[m");
    System.exit(0);
  }
}


