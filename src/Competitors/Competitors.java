package Competitors;

import Members.Member;

import java.util.ArrayList;

public class Competitors extends Member {

    private ArrayList<Discipline>disciplines = new ArrayList<>();
    private ArrayList<TrainingScore>trainingScores = new ArrayList<>();
    private ArrayList<Competition>competitions = new ArrayList<>();

    public Competitors(String name,
                       int age,
                       int phoneNumber,
                       String email,
                       int memberID,
                       char activeOrPassive,
                       char paidOrNot,
                       String seniority,
                        int result,
                        ArrayList<Discipline>disciplines,
                       ArrayList<TrainingScore>trainingScores,
                       ArrayList<Competition>competitions) {

        super(name, age, phoneNumber, email, memberID, activeOrPassive, paidOrNot);
        setCompetitions(competitions);
        setDisciplines(disciplines);
        setTrainingScores(trainingScores);
    }

    public Competitors(String name,
                       int age,
                       int phoneNumber,
                       String email,
                       int memberID,
                       char activeOrPassive,
                       char paidOrNot,
                       ArrayList<Discipline> disciplines) {
        super(name, age, phoneNumber, email, memberID, activeOrPassive, paidOrNot);
        setDisciplines(disciplines);
    }
// ----------------------------------SETTER--------------------------------------------

    public void setDisciplines(ArrayList<Discipline> disciplines) {
        this.disciplines = disciplines;
    }
    public void setTrainingScores(ArrayList<TrainingScore> trainingScores) {
        this.trainingScores = trainingScores;
    }
    public void setCompetitions(ArrayList<Competition> competitions) {
        this.competitions = competitions;
    }

    // ------------------------------GETTER---------------------------------------------

    public ArrayList<Discipline> getDisciplines() {
        return disciplines;
    }
    public  ArrayList<TrainingScore> getTrainingScores() {
        return trainingScores;
    }
    public  ArrayList<Competition> getCompetitions() {
        return competitions;
    }

    public String getName() {
        return super.getName();
    }

    public int getAge() {
        return super.getAge();
    }

    public String toString() {
        return super.toString() +
                "Training Scores: " +
                trainingScores +
                "Disciplines: " +
                disciplines +
                "Competitions: " +
                competitions;

    }

}
