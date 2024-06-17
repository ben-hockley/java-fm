package objects;

import data.regenNames;

import java.awt.Color;
import java.util.ArrayList;

public class Nation {

    /**
     * The name of the nation.
     */
    private final String name;
    /**
     * .png image of the Nations's flag.
     */
    private final String flag;
    /**
     * The nation's FIFA world ranking.
     */
    private final Integer worldRanking;
    /**
     * The continent the nation is located in.
     */
    private final String continent;
    /**
     * All The players that are from this nation.
     * Players are added to their club and nation via constructor when created.
     */
    private ArrayList<Player> players;
    /**
     * All the leagues that are in this nation.
     * Leagues are added to their nation via constructor when created.
     * Teams can be promoted and relegated between leagues at different tiers.
     */
    private final ArrayList<League> leagues;
    /**
     * The main color of the nation's football shirt
     * (e.g. Brazil = Yellow, Germany = White, etc.)
     */
    private final Color nationColor;

    /**
     * Constructor for the Nation class.
     * @param nationName The name of the nation.
     * @param nationFlag The .png image of the nation's flag.
     * @param worldRank The nation's FIFA world ranking.
     * @param continentConfederation The continent the nation PLAYS in.
     *                               (e.g. Australia -> Asia)
     */
    public Nation(final String nationName, final String nationFlag,
                  final int worldRank, final String continentConfederation) {
        this.name = nationName;
        this.flag = nationFlag;
        this.worldRanking = worldRank;
        this.continent = continentConfederation;

        this.players = new ArrayList<>();
        this.leagues = new ArrayList<>();

        //change this later
        this.nationColor = Color.BLUE;
    }

    /**
     * Method used in constructor of player object to add itself to its
     * nation's list of players.
     * @param player The player to be added to the nation.
     */
    protected void addPlayer(final Player player) {
        this.players.add(player);
    }

    /**
     * Method used in the league constructor to add itself
     * to its nation's list of leagues.
     * @param league The league to be added to the nation.
     */
    protected void addLeague(final League league) {
        this.leagues.add(league);
    }

    /**
     * Getter for the nation's name to be used in the UI etc.
     * @return The name of the nation.
     */
    public String getNationName() {
        return name;
    }

    private ArrayList<Player> getPlayersByPosition(
                    final String position, final Integer numberOfPlayers) {
        ArrayList<Player> playersInPosition = new ArrayList<>();
        for (Player player : players) {
            if (player.getPosition().equals(position)) {
                playersInPosition.add(player);
            }
        }

        // if more than the desired amount of players in the position,
        // remove the lowest rated players
        // until the desired amount of players is reached.
        getBestPlayers(numberOfPlayers, playersInPosition);

        //if nation does not have enough players in the position, generate some.
        while (playersInPosition.size() < numberOfPlayers) {

            //Create a filler player with no club to fill the national team.
            String fillerFName = regenNames.listOfFirstNames[
                    (int) (Math.random() * regenNames.listOfFirstNames.length)];
            String fillerLName = regenNames.listOfLastNames[
                    (int) (Math.random() * regenNames.listOfLastNames.length)];
            final int fillerRating = 50 + (int) (Math.random() * 15);
            final int fillerAge = 18 + (int) (Math.random() * 20);

            Player fillerPlayer = new Player(fillerFName, fillerLName, position,
                    null, this, fillerRating, fillerAge);

            playersInPosition.add(fillerPlayer);
        }

        return playersInPosition;
    }

    protected static void getBestPlayers(final Integer playerLimit,
                                       final ArrayList<Player> players) {
        final int gameMaxRating = 99;
        while (players.size() > playerLimit) {
            Integer lowestRating = gameMaxRating;
            Player lowestRatedPlayer = null;
            for (Player player : players) {

                if (player.getRating() < lowestRating) {
                    lowestRating = player.getRating();
                    lowestRatedPlayer = player;
                }
            }
            players.remove(lowestRatedPlayer);
        }
    }

    /**
     * Getter function to return an arrayList of all the nation's leagues.
     * @return arrayList of all the nation's leagues.
     */
    public ArrayList<League> getAllLeagues() {
        return leagues;
    }

    /**
     * Getter function to return the league of a specific tier in the nation.
     * @param tier The tier of the league to be returned.
     * @return The Nation's league at specified tier in its football pyramid.
     */
    public League getLeagueByTier(final Integer tier) {
        for (League league : leagues) {
            if (league.getTier().equals(tier)) {
                return league;
            }
        }
        return null;
    }

    /**
     * Method to remove a player from the nation, used when a player retires.
     * Could also be used to change an uncapped players nationality,
     * if they are dual national and switch allegiance. (e.g. Diego Costa)
     * @param player The player to be removed from the nation.
     */
    protected void removePlayer(final Player player) {
        this.players.remove(player);
    }

    /**
     * Method to get the best squad of players from the nation, to determine
     * the national team.
     * @return The best squad of players from the nation.
     */
    protected ArrayList<Player> getBestSquad() {
        //Formation of the national team.
        final Integer[] formation = {4, 3, 3};

        ArrayList<Player> bestSquad = new ArrayList<>();
        ArrayList<Player> goalkeepers = getPlayersByPosition("GK", 1);
        ArrayList<Player> defenders = getPlayersByPosition("DEF", formation[0]);
        ArrayList<Player> midfield = getPlayersByPosition("MID", formation[1]);
        ArrayList<Player> forwards = getPlayersByPosition("FWD", formation[2]);

        bestSquad.addAll(goalkeepers);
        bestSquad.addAll(defenders);
        bestSquad.addAll(midfield);
        bestSquad.addAll(forwards);

        ArrayList<Player> subs = new ArrayList<>(players);
        subs.removeAll(bestSquad);

        final int maxSubs = 12;

        getBestPlayers(maxSubs, subs);

        bestSquad.addAll(subs);

        return bestSquad;
    }

    /**
     * Getter function to return the file location
     * of the nation's flag .png image.
     * @return The nation's flag image.
     */
    public String getNationFlag() {
        return flag;
    }

    /**
     * Getter function to return the nation's Color.
     * @return The nation's Color. (Rgb / hex)
     */
    public Color getNationColor() {
        return nationColor;
    }

    /**
     * Gets the length of the nation's leagues arraylist, to determine how many
     * leagues it has.
     * @return the number of leagues in the Nation.
     */
    public Integer getNumberOfLeagues(){
        return leagues.size();
    }
}
