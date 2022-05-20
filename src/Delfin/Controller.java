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

  FileHandler fileHandler = new FileHandler();
  private Integer memberID = fileHandler.loadMemberID();

  //   private LocalDate dateOfBirth;
  private LocalDate age;
  private int phoneNumber;

  private char active;
  private char paidOrNot;
  //MembersFee memebrsfees = new MembersFee(); //blir brugt?
  MembersFee memberFee = new MembersFee(); //blir brugt?
  Scanner input = new Scanner(System.in);
Member member = new Member();

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
        case 2 -> cashier();
        case 3 -> coaches();
      }
    }
  }

  public void chairman() {
    ui.printChairmanMenu();
    int choice = ui.readChairmanUi();
    switch (choice) {
      case 0 -> start();
      case 1 -> addMember();
      case 2 -> removeMember();
      case 3 -> memberList();
      case 4 -> searchMember();
    }
  }

  public void cashier(){
    ui.printCschierMenu();
    Scanner input = new Scanner(System.in);
    int choice = input.nextInt();
    while (choice < 0 || choice > 5) {
      System.out.println("Only values 0-4 allowed");
      choice = input.nextInt();
    }

    switch (choice) {
      //case 1 -> missingpyments();
      case 2 -> changeMemberFees();
      //case 3 -> seeAllPayments();
      case 4 -> ui.printMenmbersFees();
      case 5 -> exit();
      case 0 -> start();


    }
  }

  public void changeMemberFees(){
    ui.printChoseToChangeFees();
    Scanner sc = new Scanner(System.in);
    int feeToChange = sc.nextInt();
    int newMemberFee = sc.nextInt();
    for (int i = 0;i < memberFee.fees.length;i++){
      memberFee.fees[i] = feeToChange;
      if (memberFee .fees[i]== feeToChange){
        memberFee.fees[i]=newMemberFee;
      }
    }
    System.out.println("you have now change the fee from"+ feeToChange+" to " + newMemberFee);
    cashier();
  }


  public void memberList() {
    ui.printMemberList();
    for (Member member : getAllMembers()) {
      System.out.println(member);
    }
    System.out.println("\nThe number of members in the list: " + getMemberCount() + "\n");

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
      searchMemberMenu(member);
    } else {
      System.out.println("The member could not be found");

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

  public void searchMemberMenu(Member member) {
    ui.printSearchMenu();
    Scanner input = new Scanner(System.in);
    int choice = input.nextInt();
    input.nextLine(); //Scanner bug fix
    while (choice < 0 || choice > 3) {
      System.out.println("Only values 0-3 allowed");
      choice = input.nextInt();
      input.nextLine(); //Scannerbug
    }

    switch (choice) {
      case 0 -> ui.printChairmanMenu();
      case 1 -> removeMember();
      case 2 -> editMember(member);
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
    member.calculateAge(temp);
    int paymentCategory = (int) memberFee.paymentCategoryCalculator();
    System.out.println(paymentCategory); //TODO: skal slettes, kun til test(linjen)
    System.out.println(member.getAge());


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
    ui.printChoiceEditMember();
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
      case "EXIT" -> ui.printChairmanMenu();
      default -> {
        System.out.println("Invalid decision");

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
    Member newMember = new Member (name,  member.getAge(), phoneNumber, email, memberID, activeOrPassive, paidOrNot);
    members.add(newMember);
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


