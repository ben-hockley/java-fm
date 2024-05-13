package Objects;

import java.util.ArrayList;

public class Team {
    private String teamName;
    private String teamLogo;
    private League league;
    private Integer points;

    private ArrayList<Player> players;

    public Team(String teamName, String teamLogo, League league) {
        this.teamName = teamName;
        this.teamLogo = teamLogo;
        this.league = league;
        this.points = 0;

        this.players = new ArrayList<Player>();

        league.addTeam(this);
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }
}
