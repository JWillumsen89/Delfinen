package delfin;

import colors.*;
import competitors.TrainingScore;
import filehandler.DatabaseException;
import filehandler.FileHandler;
import finance.MembersFee;
import members.Member;
import ui.UserInterface;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Controller {

  private String name;
  private String email;
  private String butterfly = "-";
  private String breastStroke = "-";
  private String backCrawl = "-";
  private String crawl = "-";
  private final String team = "-";
  private final String coach = "-";
  private String dateToString;

  private int phoneNumber;
  private double paymentCategory;

  private char active;
  private char answerCom;
  private char competitorOrRegular;
  private char paidOrNot = 'N';

  private LocalDate age;

  boolean programRunning = true;
  boolean running = true;
  boolean intError;
  boolean correctInput = false;
  final boolean added = false;

  final Scanner input = new Scanner(System.in);

  //File handler
  final FileHandler fileHandler = new FileHandler();
  private Integer memberId = fileHandler.loadMemberID();

  final UserInterface ui = new UserInterface();
  final MembersFee memberFee = new MembersFee();
  final Member member = new Member(name, age, phoneNumber, email, memberId, active, paidOrNot, paymentCategory, butterfly, crawl, backCrawl, breastStroke, team, coach, added, competitorOrRegular);

  private ArrayList<Member> members = new ArrayList<>();
  private final ArrayList<Member> searchedForMembers = new ArrayList<>();
  private ArrayList<TrainingScore> trainingScores = new ArrayList<>();

  //CHAIRMAN
  public void addMember() {
    System.out.println("\nCreate new member\n-----------------");
    typeName();
    typeDOBException();
    typeEmailError();
    typePhoneNumber();
    addMemberID();
    typeMemberStatus();
    scannerBugFix();
    if (active == 'A') {
      typeCompetitionOrNot();
      scannerBugFix();
      if (answerCom == 'C') {
        pickDiscipline();
      }
    }
    saveRegularMember();
  }

  public void addMemberID() {
    //Adds memberId
    memberId = fileHandler.loadMemberID() + 1;
  }

  public String formatAge(String birthday) {
    age = LocalDate.parse(birthday);
    String dateToString;
    dateToString = age.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
    System.out.println("My age" + dateToString);

    return dateToString;
  }

  public void changeActiveOrPassive(Member member) {
    typeMemberStatus();
    scannerBugFix();
    if (member != null) {
      member.setActiveOrPassive(active);
      System.out.println(member);
      save();
    } else {
      saveRegularMember();
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
      saveRegularMember();
    }
  }

  public void changeName(Member member) {
    typeName();
    if (member != null) {
      member.setName(name);
      System.out.println(member);
      save();
    } else {
      saveRegularMember();
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
      saveRegularMember();
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
      case "5" -> exit();
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
        saveRegularMember();
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

  public void competitorsList() {
    //TODO TITLES
    for (Member competitor : members)
      if (competitor.getCompetitorOrRegular() == 'C') {
        System.out.printf("%04d %-30s %-35s %-9s %-5s %-9s %-5s\n", competitor.getMemberID(), competitor.getName(), competitor.getAge(),
            competitor.getButterfly(), competitor.getCrawl(), competitor.getBackCrawl(), competitor.getBreastStroke());
      }
  }

  public void teamsList() {
    //TODO TITLES
    printButterflySeniorTeam();
    printButterflyJuniorTeam();
    printCrawlSeniorTeam();
    printCrawlJuniorTeam();
    printSeniorBackCrawlTeam();
    printJuniorBackCrawlTeam();
    printSeniorBreaststrokeTeam();
    printJuniorBreaststrokeTeam();
  }

  public void printButterflySeniorTeam() {
    System.out.println("------------------List of senior butterfly swimmers:------------------");
    for (Member competitor : members) {
      if (!competitor.getButterfly().equals("-") && competitor.getTeams().equals("Senior")) {
        System.out.printf("%04d %-30s %-4s %-8s\n", competitor.getMemberID(), competitor.getName(), competitor.getAge(), competitor.getCoach());
      }
    }
  }

  public void printButterflyJuniorTeam() {
    System.out.println("------------------List of junior butterfly swimmers:------------------");
    for (Member competitor : members) {
      if (!competitor.getButterfly().equals("-") && competitor.getTeams().equals("Junior")) {
        System.out.printf("%04d %-30s %-4s %-8s\n", competitor.getMemberID(), competitor.getName(), competitor.getAge(), competitor.getCoach());
      }
    }
  }

  public void printCrawlSeniorTeam() {
    System.out.println("------------------List of senior crawl swimmers-----------------------");
    for (Member competitor : members) {
      if (!competitor.getCrawl().equals("-") && competitor.getTeams().equals("Senior")) {
        System.out.printf("%04d %-30s %-4s %-8s\n", competitor.getMemberID(), competitor.getName(), competitor.getAge(), competitor.getCoach());
      }
    }
  }

  public void printCrawlJuniorTeam() {
    System.out.println("------------------List of junior crawl swimmers-----------------------");
    for (Member competitor : members) {
      if (!competitor.getCrawl().equals("-") && competitor.getTeams().equals("Junior")) {
        System.out.printf("%04d %-30s %-4s %-8s\n", competitor.getMemberID(), competitor.getName(), competitor.getAge(), competitor.getCoach());
      }
    }
  }

  public void printSeniorBackCrawlTeam() {
    System.out.println("------------------List of senior back crawl swimmers:-----------------");
    for (Member competitor : members) {
      if (!competitor.getBackCrawl().equals("-") && competitor.getTeams().equals("Senior")) {
        System.out.printf("%04d %-30s %-4s %-8s\n", competitor.getMemberID(), competitor.getName(), competitor.getAge(), competitor.getCoach());
      }
    }
  }

  public void printJuniorBackCrawlTeam() {
    System.out.println("------------------List of junior back crawl swimmers:-----------------");
    for (Member competitor : members) {
      if (!competitor.getBackCrawl().equals("-") && competitor.getTeams().equals("Junior")) {
        System.out.printf("%04d %-30s %-4s %-8s\n", competitor.getMemberID(), competitor.getName(), competitor.getAge(), competitor.getCoach());
      }
    }
  }

  public void printSeniorBreaststrokeTeam() {
    System.out.println("------------------List of senior breaststroke swimmers:---------------");
    for (Member competitor : members) {
      if (!competitor.getBreastStroke().equals("-") && competitor.getTeams().equals("Senior")) {
        System.out.printf("%04d %-30s %-4s %-8s\n", competitor.getMemberID(), competitor.getName(), competitor.getAge(), competitor.getCoach());
      }
    }
  }

  public void printJuniorBreaststrokeTeam() {
    System.out.println("------------------List of junior breaststroke swimmers:---------------");
    for (Member competitor : members) {
      if (!competitor.getBreastStroke().equals("-") && competitor.getTeams().equals("Junior")) {
        System.out.printf("%04d %-30s %-4s %-8s\n", competitor.getMemberID(), competitor.getName(), competitor.getAge(), competitor.getCoach());
      }
    }
  }

  public void paymentCategory(double resultAge) {
    paymentCategory = memberFee.paymentCategoryCalculator(resultAge);
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

  public void saveRegularMember() {

    paidOrNot = 'N';
    System.out.println("\nMember information:");
    System.out.println(FontColors.CYAN + "\nName: " + name + "\nDate of birth: " + age + "\nEmail: " + email + "\nPhone number: "
        + phoneNumber + "\nmember ID: " + memberId + "\nActive or passive: " + active + FontColors.RESET);
    if (answerCom == 'C') {
      activeDiscipline();
    }
    System.out.print("\n\nAre the information correct? Yes[Y], edit[E] or discard[D]: ");
    String decision = input.nextLine().toUpperCase(Locale.ROOT);
    switch (decision) {
      case "Y" -> {
        createNewMember(name, age, phoneNumber, email, memberId, active, paidOrNot, paymentCategory, butterfly, crawl, backCrawl, breastStroke, team, coach, added, competitorOrRegular);
        save();
        System.out.println(FontColors.BLUE + "\nMEMBER HAS BEEN SAVED!!\n" + FontColors.RESET);
      }
      case "E" -> editMember(null);
      case "D" -> System.out.println(FontColors.RED + "\nDISCARDED - Nothing have been saved\n" + FontColors.RESET);
    }
  }

  public void activeDiscipline() {
    System.out.println("Active discipline: ");
    if (!butterfly.equals("-"))
      System.out.println("Butterfly");
    if (!crawl.equals("-"))
      System.out.println("Crawl");
    if (!backCrawl.equals("-"))
      System.out.println("Back crawl");
    if (!breastStroke.equals("-"))
      System.out.println("Breaststroke");
  }

  public void searchMember() {

    System.out.print("\nSEARCH MEMBER - Type [0] to return to menu or type member name/part of name: ");
    String memberName = input.nextLine().toLowerCase(Locale.ROOT);

    if (memberName.equals("0")) {
      chairman();
    } else {
      ArrayList<Member> members = findMemberByName(memberName);
      if (members.size() != 0) {
        ui.printMemberList();
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

  public void typeDOB() {

    System.out.print("Enter date of birth in YYYY-MM-DD format: ");
    age = LocalDate.parse(input.nextLine());
    LocalDate temp = LocalDate.parse(age.toString());
    double resultAge = member.calculateAge(temp);
    paymentCategory(resultAge);
    correctInput = true;
  }

  public void typeDOBException() {
    while (!correctInput) {
      try {
        typeDOB();
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
    while (!name.matches("^[a-zA-ZæøåÆØÅ ]*$")) {
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

  public void typeCompetitionOrNot() {
    char comp = 'C';
    char regular = 'R';
    boolean answer = false;

    do {
      System.out.print("Competition swimmer[C] or regular[R]: ");
      answerCom = input.next().toUpperCase(Locale.ROOT).charAt(0);
      if (answerCom == comp) {
        competitorOrRegular = 'C';
        answer = true;
      } else if (answerCom == regular) {
        competitorOrRegular = 'R';
        answer = true;
      } else {
        wrongInput();
      }
    }
    while (!answer);
  }

  public void pickDiscipline() {
    running = true;
    butterfly = "-";
    crawl = "-";
    backCrawl = "-";
    breastStroke = "-";

    while (running) {
      ui.printDisciplineMenu();
      String decision = input.nextLine();
      switch (decision) {
        case "1" -> butterfly = "Butterfly";
        case "2" -> crawl = "Crawl";
        case "3" -> backCrawl = "BackCrawl";
        case "4" -> breastStroke = "Breaststroke";
        case "5" -> running = false;
        default -> wrongInput();
      }
    }
  }

  //TODO: ADD their category and constructor
  public void createNewMember(String name, LocalDate age, int phoneNumber, String email, Integer memberID, char activeOrPassive,
                              char paidOrNot, double paymentCategory, String butterfly, String crawl, String backCrawl,
                              String breastStroke, String team, String coach, Boolean added, char competitorOrRegular) {
    members.add(new Member(name, member.getAge(), phoneNumber, email, memberID, activeOrPassive, paidOrNot, paymentCategory, butterfly, crawl, backCrawl, breastStroke, team, coach, added, competitorOrRegular));
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
      case "5" -> searchMemberCashier();
      case "6" -> exit();
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

  public void missingPayments() {
    ArrayList<Member> arrears = new ArrayList<>();
    for (Member member : members) {
      if (member.getPaidOrNot() == 'N') {
        arrears.add(member);
        System.out.println(member);
      }
    }
    System.out.println("\nThe number of members that have arrears is: " + arrears.size() + "\n");
  }

  public void seeAllPayments() {
    double totalPayment = 0.0;
    for (Member member : members) {
      totalPayment += member.getPaymentCategory();
      System.out.println(member);
    }
    System.out.println("\n The total income from members this season: " + totalPayment + " dkk\n");
  }

  public void typePaymentStatus() {
    char paid = 'P';
    char not = 'N';
    boolean answer = false;

    do {
      lineSpace();
      System.out.print("Paid[P] or Not[N]: ");
      paidOrNot = input.next().toUpperCase(Locale.ROOT).charAt(0);
      if (paidOrNot == paid) {
        answer = true;
      } else if (paidOrNot == not) {
        answer = true;
      } else {
        wrongInput();
      }
    }
    while (!answer);
  }

  public void changePaymentStatus(Member member) {
    typePaymentStatus();
    scannerBugFix();
    if (member != null) {
      member.setPaidOrNot(paidOrNot);
      lineSpace();
      System.out.println(member);
      lineSpace();
      save();
    } else {
      saveRegularMember();
    }
    paidOrNot = 'N';
  }

  public void editStatus(Member member) {
    ui.printCashierEditStatusMenu();
    String choice = input.nextLine();
    switch (choice) {
      case "0" -> cashier();
      case "1" -> changePaymentStatus(member);
      case "2" -> searchMemberCashier();
      default -> {
        wrongInput();
        editMember(member);
      }
    }
  }

  public void searchMemberCashier() {

    System.out.print("\nSEARCH MEMBER - Type [0] to return to menu or type member name/part of name: ");
    String memberName = input.nextLine().toLowerCase(Locale.ROOT);
    lineSpace();
    if (memberName.equals("0")) {
      cashier();
    } else {
      ArrayList<Member> members = findMemberByName(memberName);
      if (members.size() != 0) {
        ui.printMemberList();
        for (Member member : members) {
          System.out.println(member);
        }
        System.out.print("\nType [0] to return to cashier menu or select a member by, ID number, that you want to edit: ");
        int memberID = input.nextInt();
        scannerBugFix();
        System.out.println();
        if (memberID == 0) {
          System.out.println("\nNo changes have been made.");
        } else {
          Member member1 = pickAMember(memberID);
          editStatus(member1);
        }
      } else {
        System.out.println(FontColors.RED + "The member could not be found" + FontColors.RESET);
        searchMemberCashier();
      }
    }
  }

  //COACH

  public void coaches() {
    ui.printCoachMenu();
    String choice = input.nextLine();
    switch (choice) {
      case "1" -> competitorsList();
      case "2" -> {
        addToTeams();
        teamsList();
      }
      case "3" -> System.out.println("Coach Menu 3");
      case "4" -> System.out.println("Coach Menu 4");
      case "5" -> exit();
      case "0" -> start();
      default -> {
        wrongInput();
        coaches();
      }
    }
  }

  public void addToTeams() {
    for (Member competitor : members) {
      if (!competitor.isAdded() && competitor.getCompetitorOrRegular() == 'C') {
        if (competitor.getAge() < 18) {
          competitor.setTeams("Junior");
          competitor.setCoach("Coach 1");
          competitor.setAdded(true);
        } else if (competitor.getAge() >= 18) {
          competitor.setTeams("Senior");
          competitor.setCoach("Coach 2");
          competitor.setAdded(true);
        }
      }
    }

   /* for (int i = 0; i < manageTeams.getTeams().length ; i++ ){
      manageTeams.getTeams()[i].getCompetitors().clear();
    }


    for (Member competitor : competitors) {
      competitors.add(competitor);
    }

    for (Competitors competitor : competitors) {

      for (int i = 0; i < competitor.getDisciplines().size(); i++) {
        Discipline discipline = competitor.getDisciplines().get(i);
        LocalDate dateOfBirth = LocalDate.ofEpochDay(competitor.getAge());
        int age = Period.between(dateOfBirth, LocalDate.now()).getYears();

        if (discipline == Discipline.Crawl) {

          if (age >= 18) {
            manageTeams.getTeams()[2].getCompetitors().add(competitor);
          } else {
            manageTeams.getTeams()[6].getCompetitors().add(competitor);
          }

        } else if (discipline == Discipline.BackCrawl) {

          if (age >= 18) {
            manageTeams.getTeams()[0].getCompetitors().add(competitor);
          } else {
            manageTeams.getTeams()[4].getCompetitors().add(competitor);
          }

        } else if (discipline == Discipline.Butterfly) {

          if (age >= 18) {
            manageTeams.getTeams()[3].getCompetitors().add(competitor);
          } else {
            manageTeams.getTeams()[7].getCompetitors().add(competitor);
          }

        } else if (discipline == Discipline.Breaststroke) {

          if (age >= 18) {
            manageTeams.getTeams()[1].getCompetitors().add(competitor);
          } else {
            manageTeams.getTeams()[5].getCompetitors().add(competitor);
          }

        }
      }
    }

    */


  }

  public void addTrainingResult() {

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
    programRunning = false;
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

  public void lineSpace(){
    System.out.println();
  }

}