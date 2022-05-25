package filehandler;

import competitors.Competitor;
import members.Member;
import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class FileHandler {

  private final String fileNameMemberBase = "res/MemberBase.csv";
  private final String fileNameCompetitorList = "res/CompetitorList.csv";
  private final String fileNameID = "res/IDNumber.txt";

  //Members list
  public ArrayList<Member> loadMembersFromFile() {

    ArrayList<Member> members = new ArrayList<>();

    try {
      Scanner fileScanner = new Scanner(new File(fileNameMemberBase));
      fileScanner.nextLine();
      while (fileScanner.hasNextLine()) {
        String line = fileScanner.nextLine();
        Member member = readMember(line);
        members.add(member);
      }
    } catch (FileNotFoundException exception) {
      System.out.println("\u001b[1;31mMEMBERLIST DID NOT GET LOADED\u001b[m");
    }
    return members;
  }

  public void saveMembersToFile(ArrayList<Member> members) {
    try {
      PrintStream out = new PrintStream(fileNameMemberBase);
      out.println("name;age;phoneNumber;email;memberId;active(A)OrPassive(P);paid(P)OrNot(N);PaymentCategory;butterfly;crawl;backCrawl;breaststroke;team;coach;added;competitorOrRegular");
      for (Member member : members) {
        writeMember(out, member);
      }
      out.close();
    } catch (FileNotFoundException exception) {
      throw new DatabaseException();
    }
  }

  public Member readMember(String line) {

    Scanner input = new Scanner(line).useDelimiter(";").useLocale(Locale.ENGLISH);
    String name = input.next();
    int age = input.nextInt();
    int phoneNumber = input.nextInt();
    String email = input.next();
    Integer memberID = input.nextInt();
    char activeOrPassive = input.next().charAt(0);
    char paidOrNot = input.next().charAt(0);
    double paymentCategory = input.nextDouble();
    String butterfly = input.next();
    String crawl = input.next();
    String backCrawl = input.next();
    String breastStroke = input.next();
    String team = input.next();
    String coach = input.next();
    boolean added = input.nextBoolean();
    char competitorOrRegular = input.next().charAt(0);

    return new Member(name, age, phoneNumber, email, memberID, activeOrPassive, paidOrNot, paymentCategory,
        butterfly, crawl, backCrawl, breastStroke, team, coach, added, competitorOrRegular);
  }

  public void writeMember(PrintStream out, Member member) {

    out.print(member.getName());
    out.print(";");
    out.print(member.getAge());
    out.print(";");
    out.print(member.getPhoneNumber());
    out.print(";");
    out.print(member.getEmail());
    out.print(";");
    out.print(member.getMemberID());
    out.print(";");
    out.print(member.getActiveOrPassive());
    out.print(";");
    out.print(member.getPaidOrNot());
    out.print(";");
    out.print(member.getPaymentCategory());
    out.print(";");
    out.print(member.getButterfly());
    out.print(";");
    out.print(member.getCrawl());
    out.print(";");
    out.print(member.getBackCrawl());
    out.print(";");
    out.print(member.getBreastStroke());
    out.print(";");
    out.print(member.getTeams());
    out.print(";");
    out.print(member.getCoach());
    out.print(";");
    out.print(member.isAdded());
    out.print(";");
    out.print(member.getCompetitorOrRegular());
    out.print("\n");
  }

  //Competitor result list
  public ArrayList<Competitor> loadCompetitorListFromFile() {

    ArrayList<Competitor> competitors = new ArrayList<>();

    try {
      Scanner fileScanner = new Scanner(new File(fileNameCompetitorList));
      fileScanner.nextLine();
      while (fileScanner.hasNextLine()) {
        String line = fileScanner.nextLine();
        Competitor competitor = readCompetitor(line);
        competitors.add(competitor);
      }
    } catch (FileNotFoundException exception) {
      System.out.println("\u001b[1;31mCOMPETITOR LIST DID NOT GET LOADED\u001b[m");
    }
    return competitors;
  }

  public void saveCompetitorToFile(ArrayList<Competitor> competitors) {
    try {
      PrintStream out = new PrintStream(fileNameCompetitorList);
      out.println("name;memberId;discipline;resultTypeCompetitionTraining" +
          ";time;competitionLocation;competitionResult;combinedMilliseconds");
      for (Competitor competitor : competitors) {
        writeCompetitor(out, competitor);
      }
      out.close();
    } catch (FileNotFoundException exception) {
      throw new DatabaseException();
    }
  }

  public Competitor readCompetitor(String line) {

    Scanner input = new Scanner(line).useDelimiter(";").useLocale(Locale.ENGLISH);
    String name = input.next();
    Integer memberID = input.nextInt();
    String discipline = input.next();
    char resultTypeCompetitionTraining = input.next().charAt(0);
    String time = input.next();
    String competitionLocation = input.next();
    int competitionResult = input.nextInt();
    int combinedMilliseconds = input.nextInt();

    return new Competitor(name, memberID, discipline,
        resultTypeCompetitionTraining, time, competitionLocation, competitionResult, combinedMilliseconds);
  }

  public void writeCompetitor(PrintStream out, Competitor competitor) {

    out.print(competitor.getName());
    out.print(";");
    out.print(competitor.getMemberID());
    out.print(";");
    out.print(competitor.getDiscipline());
    out.print(";");
    out.print(competitor.getTrainingOrCompetition());
    out.print(";");
    out.print(competitor.getTime());
    out.print(";");
    out.print(competitor.getCompetitionLocation());
    out.print(";");
    out.print(competitor.getCompetitionResult());
    out.print(";");
    out.print(competitor.getMilliseconds());
    out.print("\n");
  }

  //MemberId
  public void saveMemberID(Integer id) {
    try {
      FileWriter myWriter = new FileWriter(fileNameID);
      myWriter.write(String.valueOf(id));
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public Integer loadMemberID() {
    String data = null;
    try {
      File myObj = new File(fileNameID);
      Scanner myReader = new Scanner(myObj);
      data = myReader.nextLine();
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    assert data != null;
    return Integer.parseInt(data);
  }
}