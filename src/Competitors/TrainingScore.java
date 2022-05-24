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
    public String toString() {
        return "Date: " + DATE + "Time: " + TIME.toMillis() + "MS: " + "Discipline" + DISCIPLIN;
    }

    @Override
    public int compareTo(TrainingScore other) {
        if (this.getTIME().toMillis() == 0 && other.getTIME().toMillis() == 0) {
            return 0;
        } else if (this.getTIME().toMillis() == 0) {
            return 1;
        } else if (other.getTIME().toMillis() == 0) {
            return -1;
        }
        return (this.getTIME().compareTo(other.getTIME()));
    }


}

