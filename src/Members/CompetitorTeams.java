package Members;

public class CompetitorTeams extends Member {

    public String getCoach() {
        return coach;
    }

    public String getTeams() {
        return teams;
    }

    private String coach;
    private String teams;


    public CompetitorTeams(String name, int age, Integer memberID,
                  String butterfly, String crawl, String backCrawl, String breastStroke, String coach, String teams) {
        super(name, age, memberID, butterfly, crawl, backCrawl, breastStroke);
        this.coach = coach;
        this.teams = teams;




    }

    @Override
    public String toString() {
        return "CompetitorTeams {" +
                "coach; " + coach + '\'' +
                " teams; " + teams + '\'' +
                '}';
    }
}
