package Events;

import Objects.Team;

public class Game extends Event {
    public Team homeTeam;
    public Team awayTeam;

    public Game(Team homeTeam, Team awayTeam, Integer date, Integer month){
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.date = date;
        this.month = month;
    }

}
