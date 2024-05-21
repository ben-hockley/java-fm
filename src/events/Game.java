package events;
import Objects.Team;

public class Game extends Event {
    /**
     * The home team in the game.
     */
    private final Team homeTeam;
    /**
     * The away team in the game.
     */
    private final Team awayTeam;

    /**
     * Constructor for the Game class.
     *
     * @param homeTeam The home team in the game.
     * @param awayTeam The away team in the game.
     * @param date     The date of the game.
     */
    public Game(final Team homeTeam, final Team awayTeam, final Integer[] date) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.date = date;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }


}
