package Objects;

import java.util.ArrayList;

public class League {
    private String leagueName;
    private String leagueLogo;
    private Integer tier;
    private Nation nation;

    private ArrayList<Team> teams;

    public League(String leagueName, String leagueLogo, Integer tier, Nation nation) {
        this.leagueName = leagueName;
        this.leagueLogo = leagueLogo;
        this.tier = tier;
        this.nation = nation;

        this.teams = new ArrayList<Team>();

        nation.addLeague(this);
    };

    public void addTeam(Team team) {
        this.teams.add(team);
    }
}
