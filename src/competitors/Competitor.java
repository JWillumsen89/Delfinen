package competitors;

import java.util.Date;

public class Competitor {

  public Integer getMemberID() {
    return memberID;
  }

  public String getName() {
    return name;
  }

  public String getDiscipline() {
    return discipline;
  }

  public String getCompetitionLocation() {
    return competitionLocation;
  }

  public String getTime() {
    return time;
  }

  public int getCompetitionResult() {
    return competitionResult;
  }

  public int getMilliseconds() {
    return milliseconds;
  }

  public char getTrainingOrCompetition() {
    return trainingOrCompetition;
  }

  private Integer memberID;
  private String name;
  private String discipline;
  private String competitionLocation;
  private String time;
  private int competitionResult;
  private int milliseconds;
  private char trainingOrCompetition;


  public Competitor(String name, Integer memberID, String discipline,
                    char trainingOrCompetition, String time, String competitionLocation, int competitionResult, int milliseconds) {
    this.name = name;
    this.memberID = memberID;
    this.discipline = discipline;
    this.trainingOrCompetition = trainingOrCompetition;
    this.time = time;
    this.competitionLocation = competitionLocation;
    this.competitionResult = competitionResult;
    this.milliseconds = milliseconds;

  }

  @Override
  public String toString() {

    if (trainingOrCompetition == 'T') {
      return
          String.format("%04d %-30s %-14s %-11s", memberID,
              name, discipline, time);
    } else {
      return
          String.format("%04d %-30s %-14s %-11s %-25s %-4d", memberID,
              name, discipline, time, competitionLocation, competitionResult);
    }
  }
}
