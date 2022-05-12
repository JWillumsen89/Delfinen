package Competitors;

import java.time.Duration;

public class Competition {

    private final String CompetitonName;
    private final int CompetitorPlacement;
    private final Duration CompetitorTime;

    public Competition(String competionName, int competionPlacement, Duration competitorTime) {
        CompetitonName = competionName;
        CompetitorPlacement = competionPlacement;
        CompetitorTime = competitorTime;

        //------------------------Getter-------------------------
    }
    public String getCompetitonName() {
        return CompetitonName;
    }

    public int getCompetitorPlacement() {
        return CompetitorPlacement;
    }

    public Duration getCompetitorTime() {
        return CompetitorTime;
    }

    public String toString() {
        return "Competition Name: "
                + CompetitonName
                + "Placement: "
                + CompetitorPlacement
                + "Time: "
                + CompetitorTime;
    }
}
