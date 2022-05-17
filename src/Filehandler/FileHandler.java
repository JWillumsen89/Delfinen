package Filehandler;

import Members.Member;
import UI.UserInterface;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class FileHandler {

  private String fileName = "MemberBase.csv";

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

    }
    return members;
  }

  public Member readMember(String line) {

    Scanner input = new Scanner(line).useDelimiter(";").useLocale(Locale.ENGLISH);


    //TODO MEMBER INPUT
    String name = input.next();
    int age = input.nextInt();
    int phoneNumber = input.nextInt();
    String email = input.next();
    Integer memberID = input.nextInt();
    char activeOrPassive = input.next().charAt(0);
    char paidOrNot = input.next().charAt(0);
    //double memberCategory = input.nextDouble();
    /*boolean active = input.hasNext();
    boolean paid = input.hasNext();

     */

    Member member = new Member(name, age, phoneNumber, email, memberID, activeOrPassive, paidOrNot);

    return member;
  }

  public void saveMembersToFile(ArrayList<Member> members) {
    try {
      PrintStream out = new PrintStream(fileName);
// TODO Make headers for CSV
      out.println("name;age;phoneNumber;email;memberId;active(A)OrPassive(P);paid(P)OrNot(N)");
      for (Member member : members) {
        writeMember(out, member);
      }
      out.close();
    } catch (FileNotFoundException exception) {
      DatabaseException dbex = new DatabaseException();
      throw dbex;
    }
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
    out.print("\n");


  }
}
