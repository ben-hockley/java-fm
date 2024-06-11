package objects;

import data.Data;
import events.Game;
import main.fixtureGen;

import java.util.ArrayList;
import java.util.Collections;

public class League {
    /**
     * The tier of the league in the nation it is based in.
     * (e.g. Premier League is tier 1 in England, and championship is tier 2)
     * Used for promotion/relegation.
     */
    private final Integer tier;
    /**
     * ArrayList of all teams in the league, teams are added to the league
     * when created or promoted, and removed when relegated.
     */
    private final ArrayList<Team> teams;
    /**
     * The name of the league.
     */
    private final String name;
    /**
     * The Nation in which the league is based.
     */
    private final Nation nation;
    /**
     * File name of a .png image of the league logo, from teamImages folder.
     */
    private final String logo;

    /**
     * Constructor for the league class. Used to create a new league object.
     * @param leagueName the name of the league.
     * @param leagueLogo file name of a .png image of the league logo.
     * @param leagueTier tier in the football pyramid of the league.
     * @param country country in which the team plays.
     */
    public League(final String leagueName, final String leagueLogo,
                  final Integer leagueTier, final Nation country) {
        this.tier = leagueTier;
        this.nation = country;
        this.name = leagueName;
        this.logo = leagueLogo;

        this.teams = new ArrayList<>(); // Initialize an empty list of teams.

        try {
            //search nation make sure league of same tier doesn't already exist.
            for (League league : nation.getAllLeagues()) {
                if (league.getTier().equals(tier)) {
                    throw new IllegalArgumentException();
                }
            }
            // If not, add the league to the nation.
            nation.addLeague(this);
        } catch (IllegalArgumentException e) {

            System.out.println("Error: League of tier " + tier
                    + " already exists in " + nation.getNationName());
        }
    }

    /**
     * Used to add newly created teams ,
     * or teams that have been relegated/ promoted to a new league.
     * @param team the team to be added to the league.
     */
    public void addTeam(final Team team) {
        teams.add(team);
    }

    /**
     * Getter Function to return an arrayList of all the teams in the league.
     * @return arrayList of all the teams in the league.
     */
    public ArrayList<Team> getAllTeams() {
        return teams;
    }

    /**
     * Getter function to return which tier of football the league is at.
     * (e.g. Premier league == 1, Championship == 2)
     * @return Integer representing the tier of the league.
     */
    public Integer getTier() {
        return tier;
    }

    /**
     * Getter function to return the team object of a team with a given name.
     * Searches through the list of teams and compares the teams name to the
     * name given as args.
     * @param teamName the name of the team to be returned.
     * @return the team object with the given name.
     */
    public Team getTeamByName(final String teamName) {
        for (Team team : teams) {
            if (team.getTeamName().equals(teamName)) {
                return team;
            }
        }
        return null;
    }

    /**
     * Useful for league tables and determing teams qualified for cups like UCL.
     * @return a list of all the teams in the league sorted by league points.
     */
    public ArrayList<Team> getStandings() {
        ArrayList<Team> standings = getAllTeams();
        standings.sort((team1, team2) ->
                team2.getLeaguePoints().compareTo(team1.getLeaguePoints()));

        return standings;
    }

    /**
     * Used for fetching the name of the league to be used in the UI.
     * @return the name of the league.
     */
    public String getName() {
        return name;
    }

    /**
     * Used to generate the fixtures for the league.
     * Adds the fixtures to each team's fixture list, so cpu games are
     * simulated and cpu teams can also get points when playing each other.
     * @return 2D arraylist of games, each inner arraylist
     * represents a week of games.
     */
    public ArrayList<ArrayList<Game>> generateLeagueFixtures() {
        ArrayList<ArrayList<Game>> fixtures
                = fixtureGen.generateLeagueFixtureSchedule(teams, Data.listOfLeagueDates, "League");

        for (ArrayList<Game> week : fixtures) {
            for (Game game : week) {
                game.getHomeTeam().addFixture(game);
                game.getAwayTeam().addFixture(game);
            }
        }
        return fixtures;
    }

    /**
     * complies all players on every team in the league to get a long list of players.
     * @return arraylist of all players in the league.
     */
    public ArrayList<Player> getAllPlayers() {
        ArrayList<Player> allPlayers = new ArrayList<>();
        for (Team team : teams) {
            allPlayers.addAll(team.getAllPlayers());
        }
        return allPlayers;
    }

    /**
     * Gets all players in the league and sotys them by league goals scored.
     * Useful for getting top goalscorers in the league.
     * @return list of all league players sorted by league goals scored.
     */
    public ArrayList<Player> getTopGoalscorers() {
        ArrayList<Player> topGoalscorers = getAllPlayers();
        topGoalscorers.sort((o1, o2) -> o2.getLeagueGoals().compareTo(o1.getLeagueGoals()));
        topGoalscorers.sort((o1, o2) -> o2.getLeagueGoals().compareTo(o1.getLeagueGoals()));
        return topGoalscorers;
    }

    /**
     * Searches list of all players in the league for a specific player (playername).
     * Returns the player if found, otherwise returns null.
     * @param playerName String name of the missing player.
     * @return player object or null if player not found.
     */
    public Player getPlayerByName(final String playerName) {
        for (Player player : getAllPlayers()) {
            if (player.getPlayerName().equals(playerName)) {
                return player;
            }
        }
        return null;
    }

    /**
     * Removes a team from the league.
     * Useful for promotion/relegation/ deleting a team.
     * @param team the team to be removed.
     */
    public void removeTeam(final Team team) {
        this.teams.remove(team);
    }

    /**
     * Number of games in a standard round-robin league season, where each
     * team plays every other team twice, once at home and once away.
     * @return number of club games in a season.
     */
    public Integer getNumberOfGamesInSeason() {
        return (teams.size() - 1) * 2;
    }

    /**
     * Getter function to return the nation in which the league is based.
     * @return the nation in which the league is based. (e.g. England)
     */
    public Nation getCountry() {
        return this.nation;
    }

    /**
     * Shuffles a list of all the teams in the league and returns it.
     * This is useful for randomizing promotions/ relegations/
     * cup draws etc.
     * In leagues where the user is not involved
     * @return randomly shuffled arrayList of all teams in the league.
     */
    public ArrayList<Team> shuffleStandings() {
        ArrayList<Team> standings = getStandings();
        Collections.shuffle(standings);

        return standings;
    }

    /**
     * Gets png image off intenet of the league logo.
     * @return file name of the league logo.
     */
    public String getLeagueLogo() {
        return logo;
    }


}
