package Filehandler;

import Members.Member;


import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class FileHandler {

  private final String fileName = "res/MemberBase.csv";
  private final String fileNameID = "res/IDNumber.txt";

  public ArrayList<Member> loadMembersFromFile() {

    ArrayList<Member> members = new ArrayList<>();

    try {
      Scanner fileScanner = new Scanner(new File(fileName));
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
      PrintStream out = new PrintStream(fileName);
      out.println("name;age;phoneNumber;email;memberId;active(A)OrPassive(P);paid(P)OrNot(N);PaymentCategory");
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

    return new Member(name, age, phoneNumber, email, memberID, activeOrPassive, paidOrNot, paymentCategory);
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
    out.print("\n");
  }

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