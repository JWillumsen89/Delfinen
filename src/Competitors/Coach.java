package Competitors;


import UI.UserInterface;

import java.util.ArrayList;
import java.util.Scanner;

    public class Coach extends UserInterface {

        private Scanner sc = new Scanner(System.in);


        public void printHeader() {
            System.out.println("\n ----CoachMenu----");
        }

        public void printMenuOptions() {
            System.out.println("1: Top 5 Training list");
            System.out.println("2: Add new competition");
            System.out.println("3: Add new training session");
            System.out.println("4: Show training schedule");
            System.out.println("0: Return to main menu");
        }

        public int inputMemberID() {
            System.out.print("\ninsert member ID: ");
            String memberIDString = sc.nextLine();
            int number = 0;

            try {
                number = Integer.parseInt(memberIDString);
            } catch (NumberFormatException n) {
                System.err.println("Error need a number");
            }
            return number;
        }

        public void memberNotFound() {
            System.out.println("invalid member.");

        }

        private Integer inputInt(int min, int max) {
            int number = -1;
            while (number < min || number > max) {
                String memberIDString = sc.nextLine();
                while (memberIDString.length() > 2 || memberIDString.isEmpty()) {
                    System.out.print("Invalid try again: ");
                    memberIDString = sc.nextLine();
                }

                try {
                    number = Integer.parseInt(memberIDString);
                } catch (NumberFormatException n) {
                    System.out.print("I need a number: ");
                }
                if (number < min || number > max) {
                    System.out.print("Invalid try again: ");
                }
            }
            return number;
        }


        public void printMenuJrSr() {
            System.out.println("");
            System.out.println("1: Junior");
            System.out.println("2: Senior");
        }

        public void printMenuDisciplines() {
            System.out.println("");
            System.out.println("1: BreastStroke");
            System.out.println("2: Crawl");
            System.out.println("3: Backcrawl");
            System.out.println("4: Butterfly");
        }

        public void printTop5Chest(ArrayList<Competitors> allCompetitors) {
            System.out.println("");
            System.out.println("top 5 for BreastStroke");

        }

        public void printTop5Crawl(ArrayList<Competitors> allCompetitors) {
            System.out.println("");
            System.out.println("Top 5 for Crawl");
        }

        public void printTop5BackCrawl(ArrayList<Competitors> allCompetitors) {
            System.out.println("");
            System.out.println("Top 5 for Backcrawl");
        }

        public void printTop5Butterfly(ArrayList<Competitors> allCompetitors) {
            System.out.println("");
            System.out.println("Top 5 for Butterfly");
        }
    }

