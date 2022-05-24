package competitors;

import filehandler.FileHandler;
import members.Member;

import java.util.ArrayList;

public class ManageTeams {
    private final FileHandler fileHandler = new FileHandler();
    private final ArrayList<Member> members = fileHandler.loadMembersFromFile();
    final Teams[] teams;

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

      //  addToTeam();

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




   /* public void addToTeam(Competitors competitors) {
      //  ArrayList<Competitors> competitors = new ArrayList<>();

        for (int i = 0; i < teams.length; i++) {
            teams[i].getCompetitors().clear();
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
                        teams[2].getCompetitors().add(competitor);
                    } else {
                        teams[6].getCompetitors().add(competitor);
                    }

                } else if (discipline == Discipline.BackCrawl) {

                    if (age >= 18) {
                        teams[0].getCompetitors().add(competitor);
                    } else {
                        teams[4].getCompetitors().add(competitor);
                    }

                } else if (discipline == Discipline.Butterfly) {

                    if (age >= 18) {
                        teams[3].getCompetitors().add(competitor);
                    } else {
                        teams[7].getCompetitors().add(competitor);
                    }

                } else if (discipline == Discipline.Breaststroke) {

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

            if (discipline == Discipline.Crawl) {
                if (age >= 18) {
                    teams[2].getCompetitors().remove(competitor);
                }
                teams[6].getCompetitors().remove(competitor);
            } else if (discipline == Discipline.BackCrawl) {
                if (age >= 18) {
                    teams[0].getCompetitors().remove(competitor);
                }
                teams[4].getCompetitors().remove(competitor);
            } else if (discipline == Discipline.Butterfly) {
                if (age >= 18) {
                    teams[3].getCompetitors().remove(competitor);
                }
                teams[7].getCompetitors().remove(competitor);
            } else if (discipline == Discipline.Breaststroke) {
                if (age >= 18) {
                    teams[1].getCompetitors().remove(competitor);
                }
                teams[5].getCompetitors().remove(competitor);
            }
        }
    }

    */
}

