package Competitors;

import Filehandler.FileHandler;
import Members.Member;

import java.util.ArrayList;

public class ManageTeams {
    private final FileHandler fileHandler = new FileHandler();
    private final ArrayList<Member> members = fileHandler.loadMembersFromFile();
    private final Teams[] teams;
    private final ArrayList<Discipline> disciplines = new ArrayList<>();

    public ManageTeams() {

        teams = new Teams[8];
//-----------------------------------JUNIOR COACH--------------------------------------
        teams[0] = new Teams("Coach1 ", "JUNIOR ", Discipline.BackCrawl);
        teams[1] = new Teams("Coach1 ", "JUNIOR ", Discipline.Breaststroke);
        teams[2] = new Teams("Coach1 ", "JUNIOR ", Discipline.Crawl);
        teams[3] = new Teams("Coach2 ", "JUNIOR ", Discipline.Butterfly);
//-----------------------------------SENIOR COACH--------------------------------------
        teams[4] = new Teams("Coach2 ", "SENIOR ", Discipline.BackCrawl);
        teams[5] = new Teams("Coach2 ", "SENIOR ", Discipline.Breaststroke);
        teams[6] = new Teams("Coach2 ", "SENIOR ", Discipline.Crawl);
        teams[7] = new Teams("Coach1 ", "JUNIOR ", Discipline.Butterfly);

        addToTeam();

    }

    //-------------------------------------GETTER--------------------------------------------
    public ArrayList<Member> getMembers() {
        return members;

    }

    public FileHandler getFileHandler() {
        return fileHandler;
    }

    public Teams[] getTeams() {
        return teams;
    }

  /*  public String getDisciplines() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < disciplines.length; i++) {
            stringBuilder.append(((i) + 1)).append(". ").append(disciplines.get(i)).append("\n");
        }
        return stringBuilder.toString();

    }

   */

    public void addToTeam() {
        ArrayList<Competitors> competitors = new ArrayList<>();
    }
}
