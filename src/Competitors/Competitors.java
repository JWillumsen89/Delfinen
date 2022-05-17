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
                       String memberID,
                       char activeOrPassive,
                       char paidOrNot,
                       ArrayList<Discipline>disciplines,
                       ArrayList<TrainingScore>trainingScores,
                       ArrayList<Competition>competitions) {

        super(name, age, phoneNumber, email, memberID, activeOrPassive, paidOrNot);
    }
}
