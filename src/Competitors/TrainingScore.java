package Competitors;

import java.time.Duration;
import java.time.LocalDate;

public class TrainingScore implements Comparable<TrainingScore> {

    private final LocalDate DATE;
    private final Duration TIME;
    private final Discipline DISCIPLIN;


    public TrainingScore(LocalDate date, Duration time, Discipline disciplin) {
        DATE = date;
        TIME = time;
        DISCIPLIN = disciplin;
    }
    public LocalDate getDATE() {
        return DATE;
    }
    public Duration getTIME() {
        return TIME;
    }
    public Discipline getDISCIPLIN() {
        return DISCIPLIN;
    }

    @Override
    public int compareTo(TrainingScore o) {
        return 0;
    }
}
