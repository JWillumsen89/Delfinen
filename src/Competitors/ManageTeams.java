package Competitors;

import Filehandler.FileHandler;
import Members.Member;

import java.util.ArrayList;

public class ManageTeams {
    private FileHandler fileHandler = new FileHandler();
    private ArrayList<Member> members = fileHandler.loadMembersFromFile();
    private Teams[] teams;
    private ArrayList<Discipline> disciplines = new ArrayList<>();

    public ManageTeams() {

        teams = new Teams[8];
//-----------------------------------JUNIOR CHOACH--------------------------------------
        teams[0] = new Teams("Choach1 ", "JUNIOR ", Discipline.BACKCRAWL);
        teams[1] = new Teams("Choach1 ", "JUNIOR ", Discipline.BREASTSTROKE);
        teams[2] = new Teams("Choach1 ", "JUNIOR ", Discipline.CRAWL);
        teams[3] = new Teams("Choach2 ", "JUNIOR ", Discipline.BUTTERFLY);
//-----------------------------------SENIOR CHOACH--------------------------------------
        teams[4] = new Teams("Choach2 ", "SENIOR ", Discipline.BACKCRAWL);
        teams[5] = new Teams("Choach2 ", "SENIOR ", Discipline.BREASTSTROKE);
        teams[6] = new Teams("Choach2 ", "SENIOR ", Discipline.CRAWL);
        teams[7] = new Teams("Choach1 ", "JUNIOR ", Discipline.BUTTERFLY);

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
