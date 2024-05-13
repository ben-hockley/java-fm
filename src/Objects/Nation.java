package Objects;

import java.util.ArrayList;

public class Nation {
    private String nationName;
    private String nationFlag;
    private Integer worldRanking;
    private String Continent;

    private ArrayList<Player> players;
    private ArrayList<League> leagues;

    public Nation(String nationName, String nationFlag, Integer worldRanking, String Continent) {
        this.nationName = nationName;
        this.nationFlag = nationFlag;
        this.worldRanking = worldRanking;
        this.Continent = Continent;

        this.players = new ArrayList<Player>();
        this.leagues = new ArrayList<League>();
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void addLeague(League league) {
        this.leagues.add(league);
    }
}
