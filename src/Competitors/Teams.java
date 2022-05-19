package Competitors;

import java.util.ArrayList;

public class Teams {

    private String coachName;
    private ArrayList<Competitors> competitors;
    private String teamName;
    private Discipline discipline;

    public Teams(String coachName, String teamName, Discipline discipline) {

    }
// -------------------------------------SETTER-------------------------------------
    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    public void setDiscipline(Discipline discipline){
        this.discipline = discipline;
    }

// --------------------------------------GETTER-------------------------------------

    public ArrayList<Competitors> getCompetitors() {
        return competitors;
    }

    public String getTeamName() {
        return teamName;
    }

    public Discipline getDiscipline() {
        return discipline;
    }
    public String getStringOfCompetitors() {
        StringBuilder stringBuilder = new StringBuilder();
        if (competitors != null) {
            for (Competitors competitors : competitors) {
                stringBuilder.append(competitors.getName());
            }
        } else {
            stringBuilder.append("No competitors available!");
        }
        return stringBuilder.toString();
    }

    public String toString() {
        return "Team: " +
                teamName
                + "Coach: "
                + coachName
                + "Competitors: "
                + getStringOfCompetitors();
    }

}
