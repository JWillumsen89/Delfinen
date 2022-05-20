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

        for (int i = 0; i < teams.length; i++) {
            teams[i].getCompetitors().clear();
        }


        for (Member member : members) {
            if (member instanceof Competitors) {
                competitors.add((Competitors) member);
            }
        }

        for (Competitors competitor : competitors) {

            for (int i = 0; i < competitor.getDisciplines().size(); i++) {
                Discipline discipline = competitor.getDisciplines().get(i);
                LocalDate dateOfBirth = LocalDate.ofEpochDay(competitor.getAge());
                int age = Period.between(dateOfBirth, LocalDate.now()).getYears();

                if (discipline == Discipline.CRAWL) {

                    if (age >= 18) {
                        teams[2].getCompetitors().add(competitor);
                    } else {
                        teams[6].getCompetitors().add(competitor);
                    }

                } else if (discipline == Discipline.BACKCRAWL) {

                    if (age >= 18) {
                        teams[0].getCompetitors().add(competitor);
                    } else {
                        teams[4].getCompetitors().add(competitor);
                    }

                } else if (discipline == Discipline.BUTTERFLY) {

                    if (age >= 18) {
                        teams[3].getCompetitors().add(competitor);
                    } else {
                        teams[7].getCompetitors().add(competitor);
                    }

                } else if (discipline == Discipline.BREASTSTROKE) {

                    if (age >= 18) {
                        teams[1].getCompetitors().add(competitor);
                    } else {
                        teams[5].getCompetitors().add(competitor);
                    }

                }
            }
        }
    }


    public void removeFromTeam(Competitors competitor, Discipline discipline) {
        for (int i = 0; i < competitor.getDisciplines().size(); i++) {
            LocalDate dateOfBirth = LocalDate.ofEpochDay(competitor.getAge());
            int age = Period.between(dateOfBirth, LocalDate.now()).getYears();

            if (discipline == Discipline.CRAWL) {
                if (age >= 18) {
                    teams[2].getCompetitors().remove(competitor);
                }
                teams[6].getCompetitors().remove(competitor);
            } else if (discipline == Discipline.BACKCRAWL) {
                if (age >= 18) {
                    teams[0].getCompetitors().remove(competitor);
                }
                teams[4].getCompetitors().remove(competitor);
            } else if (discipline == Discipline.BUTTERFLY) {
                if (age >= 18) {
                    teams[3].getCompetitors().remove(competitor);
                }
                teams[7].getCompetitors().remove(competitor);
            } else if (discipline == Discipline.BREASTSTROKE) {
                if (age >= 18) {
                    teams[1].getCompetitors().remove(competitor);
                }
                teams[5].getCompetitors().remove(competitor);
            }
        }
    }
}

