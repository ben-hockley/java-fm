package events;
import objects.Team;

public class Game extends Event {
    /**
     * Defines what type of game it is (e.g "League", "Cup", "Friendly").
     * This is used for things such as stat tracking
     * and league standings in the GUI.
     */
    private final String type;

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
     * @param gameHomeTeam The home team in the game.
     * @param gameAwayTeam The away team in the game.
     * @param date     The date of the game.
     * @param gameType The type of game (e.g "League", "Cup", "Friendly").
     */
    public Game(final Team gameHomeTeam, final Team gameAwayTeam,
                final Integer[] date, final String gameType) {
        this.homeTeam = gameHomeTeam;
        this.awayTeam = gameAwayTeam;
        this.date = date;
        this.type = gameType;
    }

    /**
     * @return The home team in the game.
     */
    public Team getHomeTeam() {
        return homeTeam;
    }
    /**
     * @return The away team in the game.
     */
    public Team getAwayTeam() {
        return awayTeam;
    }
    /**
     * @return The type of game (e.g "League", "Cup", "Friendly").
     */
    public String getGameType() {
        return type;
    }

}
