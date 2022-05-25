package competitors;

import java.util.Date;

public class Competitor {

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
          "MemberID: " + memberID +
              "\nName: " + name +
              "\nDiscipline: " + discipline +
              "\nTime: " + time;
    } else {
      return
          "MemberID: " + memberID +
              "\nName: " + name +
              "\nDiscipline: " + discipline +
              "\nTime: " + time +
              "\nCompetition: " + competitionLocation +
              "\nRanking: " + competitionResult;
    }
  }
}
