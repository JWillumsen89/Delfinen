package Competitors;

import java.time.Duration;

public class Competition {

    private final String CompetitorName;
    private final int CompetitorPlacement;
    private final Duration CompetitorTime;

    public Competition(String competitionName, int competitionPlacement, Duration competitorTime) {
        CompetitorName = competitionName;
        CompetitorPlacement = competitionPlacement;
        CompetitorTime = competitorTime;

        //------------------------Getter-------------------------
    }

    public String getCompetitorName() {
        return CompetitorName;
    }

    public int getCompetitorPlacement() {
        return CompetitorPlacement;
    }

    public Duration getCompetitorTime() {
        return CompetitorTime;
    }

    public String toString() {
        return "Competition Name: "
                + CompetitorName
                + "Placement: "
                + CompetitorPlacement
                + "Time: "
                + CompetitorTime;
    }
}
