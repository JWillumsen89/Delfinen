package Managers;

import Competitors.Competition;
import Competitors.Discipline;
import Delfin.Main;
import Members.Member;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
/*
public class Coach {

    //private ArrayList<> ;
    Main app = new Main();
    Scanner input = new Scanner(System.in);
    private String s = "Enter the member ID of the swimmer";
    String menu = """
            menu
            ---------
            1) Register results from competition
            2) See top 5 list
                            
            0) Return to main
            """;

    public void coaches(Scanner input) {
      System.out.println("Which coach are you? Junior [S] or senior [S]");
        char answer = input.next().toUpperCase(Locale.ROOT).charAt(0);
       if(answer == 'J'){
         juniorCoach();

       }
       else if(answer == 'S'){
           seniorCoach();

       }else{
           System.out.println("Sorry try again");
           coaches(input);
       }
    }
    //-------------------------- Junior --------------------------
    public void juniorCoach(){
        System.out.println("Junior coach " +menu);
        int choice = input.nextInt();
        while (choice < 0 || choice > 3) {
            System.out.println("Only values 0-2 allowed");
            choice = input.nextInt();
            input.nextLine(); //Scannerbug
        }

        switch (choice) {
            case 0 -> coaches(input);
            case 1 -> registerFromCompetition(input);
            case 2 -> juniorTopFive(input);
        }
    }

    //-------------------------- Senior --------------------------
    public void seniorCoach(){
        System.out.println("Senior coach " + menu);
        int choice = input.nextInt();
        while (choice < 0 || choice > 3) {
            System.out.println("Only values 0-2 allowed");
            choice = input.nextInt();
            input.nextLine(); //Scannerbug
        }
        switch (choice) {
            case 0 -> coaches(input);
            case 1 -> registerFromCompetition(input);
            case 2 -> seniorTopFive(input);
        }
    }

   //--------------------------  case 1 --------------------------
    //TODO muligvis fjernes?
    public void registerFromCompetition(Scanner input){
        System.out.println("Would you like to register a new result or change an already existing result? New: [N] or Old: [O]:");
        char decision = input.next().toUpperCase(Locale.ROOT).charAt(0);
        switch (decision) {
            case 'N' -> aNewCompetitionResult();
            case 'O' -> changeACompetitionScore();
        }
    }

    public void aNewCompetitionResult(){
        System.out.println("Whose results would you like to add to? Enter their numberID");

        System.out.println("---------------------MEMBER LIST---------------------");
        for (Member member : app.getAllMembers()) {
            System.out.println(member);
        }
        System.out.println("\nThe number of members in the list: " + app.getMemberCount() + "\n");


    }

    public void changeACompetitionScore(){

    }

    //--------------------------  case 2 --------------------------
    //Junior
    public void juniorTopFive(Scanner input){
        System.out.println("Which junior category do you want to choose from? Butterfly [1], Crawl[2], Backcrawl[3] or Breaststroke[4]");
        int numberDecision = input.nextInt();
        if (numberDecision == 1){
            System.out.println("Butterfly");

        }
        else if (numberDecision == 2){
            System.out.println("Crawl\n");

        }
        else if (numberDecision == 3){
            System.out.println("Backcrawl\n");


        }
        else if (numberDecision == 4){
            System.out.println("Breaststroke\n");


        }
    }

    //Senior
    public void seniorTopFive(Scanner input){
        System.out.println("Which senior category do you want to choose from? Butterfly [1], Crawl[2], Backcrawl[3] or Breaststroke[4]");
        int numberDecision = input.nextInt();
        if (numberDecision == 1){

        }
        else if (numberDecision == 2){

        }
        else if (numberDecision == 3){

        }
        else if (numberDecision == 4){

        }
    }
}


 */