package objects;

import data.Data;

import java.util.ArrayList;
public class Cup {
    /**
     * String name contains the name of the cup, is set in the constructor ,
     * and retrieved using the getter function getName().
     */
    private final String name;
    /**
     * ArrayList teams holds all the teams that partake in the cup,
     * for example in the champions league, the teams are set to the top
     * placing clubs in the best european leagues at the start of every season,
     * using the updateChampionsLeague() function.
     */
    private ArrayList<Team> teams;
    /**
     * variable Team 'champion' holds the last winner of the cup.
     * This is set to the cup final winner if the user's team is involved,
     * or randomized out of all remaining teams if the user is eliminated
     * prior to the final or not involved in the competition.
     */
    private Team champion;

    /**
     * Constructor for creating a new cup competetion, establishes a new cup
     * with an empty arrayList of teams with an initial
     * capacity set to the numberOfTeams in the competition.
     * @param cupName the name of the competition (e.g. UEFA Champions League)
     * @param numberOfTeams the number of teams in the cup (e.g. 32)
     */
    public Cup(final String cupName, final int numberOfTeams) {
        this.name = cupName;
        this.teams = new ArrayList<>(numberOfTeams);

        this.champion = null;
    }

    /**
     * Getter function to fetch the name of the cup.
     * @return the name of the cup (e.g. UEFA Champions League)
     */
    public String getName() {
        return name;
    }

    /**
     * Getter function to fetch an arrayList with all the teams in the cup.
     * To be used after the ArrayList teams has been updated,
     * and is not empty like after the cup object is created via constructor.
     * @return an ArrayList containing all the teams in the competition.
     */
    public ArrayList<Team> getTeams() {
        return teams;
    }

    /**
     * Compiles all players on all teams in the cup, useful for updating,
     * resetting and querying player cup stats for leaderboards. (e.g. Goals)
     * Contains all the players eligible for the cup this year ,
     * (on a member team), including those who have not played or whose team
     * has been eliminated already.
     * @return a long arrayList containing all the players in the cup.
     */
    private ArrayList<Player> getAllPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        for (Team team : teams) {
            players.addAll(team.getAllPlayers());
        }
        return players;
    }

    /**
     * Sorts get getAllPlayers ArrayList to get list of players by goals
     * scored, useful for Top Goalscorer leaderboard.
     * @return ArrayList containing all Players in order of goals scored.
     */
    public ArrayList<Player> getPlayersByGoalsScored() {
        ArrayList<Player> players = getAllPlayers();
        players.sort((player1, player2) ->
                Integer.compare(player2.getCupGoals(), player1.getCupGoals()));
        return players;
    }

    /**
     * Getter function to retrieve all the Top Goalscorers for info JFrames.
     * For stats displayed in temporary information JFrames.
     * @return an ArrayList of variable length (min = 1), containing all the
     * player/s tied for top goalscorer.
     */
    public ArrayList<Player> getTopGoalscorer() {
        ArrayList<Player> topGoalscorers = new ArrayList<>();
        Integer maxGoals = 0;
        for (Player player : getAllPlayers()) {
            if (player.getCupGoals() > maxGoals) {
                topGoalscorers.clear();
                topGoalscorers.add(player);
                maxGoals = player.getCupGoals();
            } else if (player.getCupGoals().equals(maxGoals)) {
                topGoalscorers.add(player);
            }
        }
        return topGoalscorers;
    }

    /**
     * Sets the champion of the cup to the winner of the cup final.
     * @param cupWinner the winner of the cup final.
     */
    public void setChampion(final Team cupWinner) {
        this.champion = cupWinner;
    }

    /**
     * Getter method to fetch the last winner of the cup.
     * @return the cup champion (Team).
     */
    public Team getChampion() {
        if (this.champion != null) {
            return champion;
        } else {
            ArrayList<Team> possibleChampions = new ArrayList<>();
            for (Team team : teams) {
                if (team.isAdvancingToNextRound()) {
                    possibleChampions.add(team);
                }
            }

            int randomChampionIndex =
                    (int) (Math.random() * possibleChampions.size());

            return possibleChampions.get(randomChampionIndex);
        }
    }

    /**
     * Function called at the end of each season to update next year's
     * champions league teams based on league standings for any user leadgue,
     * and randomly generated standings for any other eligible European
     * Leagues in the game.
     */
    public void updateChampionsLeagueTeams() {
        //clear list of teams.
        this.teams = new ArrayList<>();

        // number of teams from each country that qualify :
        // (32 Total)
        // Change these to reconfigure the champions league.
        final int englishTeams = 5;

        final int frenchTeams = 4;
        final int italianTeams = 4;
        final int spanishTeams = 4;
        final int germanTeams = 4;

        final int portugueseTeams = 2;
        final int netherlandsTeams = 2;
        final int turkishTeams = 2;
        final int scottishTeams = 2;

        final int belgianTeams = 1;
        final int austrianTeams = 1;
        final int greekTeams = 1;

        //add 5 English Teams, 4 French teams etc...
        for (int i = 0; i < englishTeams; i++) {
            teams.add(Data.england.getLeagueByTier(1).getStandings().get(i));
        }
        for (int i = 0; i < frenchTeams; i++) {
            teams.add(Data.france.getLeagueByTier(1).getStandings().get(i));
        }
        for (int i = 0; i < italianTeams; i++) {
            teams.add(Data.italy.getLeagueByTier(1).getStandings().get(i));
        }
        for (int i = 0; i < spanishTeams; i++) {
            teams.add(Data.spain.getLeagueByTier(1).getStandings().get(i));
        }
        for (int i = 0; i < germanTeams; i++) {
            teams.add(Data.germany.getLeagueByTier(1).getStandings().get(i));
        }

        for (int i = 0; i < portugueseTeams; i++) {
            teams.add(Data.portugal.getLeagueByTier(1).getStandings().get(i));
        }
        for (int i = 0; i < netherlandsTeams; i++) {
            teams.add(
                    Data.netherlands.getLeagueByTier(1).getStandings().get(i));
        }
        for (int i = 0; i < turkishTeams; i++) {
            teams.add(Data.turkey.getLeagueByTier(1).getStandings().get(i));
        }
        for (int i = 0; i < scottishTeams; i++) {
            teams.add(Data.scotland.getLeagueByTier(1).getStandings().get(i));
        }
        for (int i = 0; i < austrianTeams; i++) {
            teams.add(Data.austria.getLeagueByTier(1).getStandings().get(i));
        }
        for (int i = 0; i < belgianTeams; i++) {
            teams.add(Data.belgium.getLeagueByTier(1).getStandings().get(i));
        }
        for (int i = 0; i < greekTeams; i++) {
            teams.add(Data.greece.getLeagueByTier(1).getStandings().get(i));
        }
    }


}
