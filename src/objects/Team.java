package objects;

import data.Data;
import events.Game;

import java.awt.Color;
import java.util.ArrayList;

public class Team {
    /**
     * The type of the team, either "Club" or "International".
     */
    private final String type;
    /**
     * The full name of the team. (e.g. Manchester United)
     */
    private final String name;
    /**
     * The shortened version of the team name (e.g. Man Utd)
     * Used for displaying the team in smaller spaces in the UI.
     */
    private final String shortName;
    /**
     * The file name of the league logo (.png) in the teamImages folder.
     */
    private final String logo;

    /**
     * The league the team is currently in, may change if the team is promoted
     * or relegated.
     */
    private League league;

    /**
     * The number of points the team has in their league this season.
     * Primary factor in determining league standings.
     * Each win adds 3 points, each draw adds 1 point.
     */
    private Integer leaguePoints;
    /**
     * The number of matches the team has played in their league this season.
     */
    private Integer leagueMatchesPlayed;
    /**
     * The number of wins the team has in their league this season.
     */
    private Integer leagueWins;
    /**
     * The number of draws the team has in their league this season.
     */
    private Integer leagueDraws;
    /**
     * The number of losses the team has in their league this season.
     */
    private Integer leagueLosses;

    /**
     * The number of points the team has in the champions league group stage
     * this season. The top 2 teams in the group stage advance to the knockout
     * round.
     */
    private Integer cupPoints;
    /**
     * The number of matches the team has played in the cup this season.
     */
    private Integer cupMatchesPlayed;
    /**
     * The number of matches the team has played in THIS STAGE of the champions
     * league this season.
     * This resets back to 0 at the end of each stage.
     */
    private Integer cupGames;
    /**
     * The number of wins the team has in THIS STAGE of the champions league
     * this season.
     */
    private Integer cupWins;
    /**
     * The number of draws the team has in THIS STAGE of the champions league
     * this season.
     */
    private Integer cupDraws;
    /**
     * The number of losses the team has in THIS STAGE of the champions league
     * this season.
     */
    private Integer cupLosses;
    /**
     * The number of goals the team has scored in THIS STAGE of the champions
     * league this season, used to determine winners in knockout rounds by
     * aggregate score across two legs.
     */
    private Integer cupGoalsScored;
    /**
     * Whether the team is advancing to the next round of the cup.
     */
    private Boolean advancingToNextRound;
    /**
     * ArrayList containing all the teams in the team's champions league group.
     * The team must play each of these twice (one home and one away) in the
     * group stage.
     */
    private ArrayList<Team> championsLeagueGroupStage;
    /**
     * The letter of the team's champions league group. (e.g. A, B, C, D, E, F)
     */
    private Character championsLeagueGroupLetter = 'A';

    /**
     * List of Integers representing the formation of the team. (length = 3)
     * formation[0] = number of defenders.
     * formation[1] = number of midfielders.
     * formation[2] = number of forwards.
     */
    private final Integer[] formation;
    /**
     * List of Player's in the team's starting 11. (length = 11)
     * The starting 11 is determined by the best players of each position
     * in the team.
     * startingEleven[0] = the starting goalkeeper.
     * Rest of startingEleven = the starting defenders, midfielders, forwards.
     */
    private Player[] startingEleven;
    /**
     * ArrayList of the player's substitutes (length 5-12).
     */
    private ArrayList<Player> substitutes;
    /**
     * ArrayList containing all the team's scheduled fixtures for the season.
     * New fixtures are added if the team makes the next stage of a cup.
     */
    private ArrayList<Game> fixtures;
    /**
     * A colour used to represent the team, usually the team's primary colour,
     * but may be the team's secondary colour if the primary color is too light
     * to display white text on. (RGB / HEX)
     */
    private final Color color;
    /**
     * The team's starting transfer budget, which the team's transfer budget
     * is set to at the beginning of each new season.
     */
    private final Integer initialTransferBudget;
    /**
     * The team's current transfer budget.
     * This increases by half the player's value when a player is sold.
     * This decreases by the player's value when a player is bought.
     * This is set to the team's initialTransferBudget
     * at the start of each season.
     */
    private Integer transferBudget;

    /**
     * ArrayList containing all the players in the team.
     * This includes the starting 11 (length 11) and substitutes (length 5-12).
     * Total length of players = 16-23.
     */
    private final ArrayList<Player> players;

    /**
     * Constructor to create a new club team.
     * @param teamLongName the team's full name (e.g. Manchester United)
     * @param teamShortName the team's shortened name (e.g. Man Utd)
     * @param teamLogo file name of the team's logo in the teamImages folder.
     * @param currentLeague the current league in which the team plays.
     * @param primaryFormation the default formation of the team.
     * @param teamColor a color to represent the team.
     * @param transferBudgetMillions the teams transfer budget in millions.
     */
    public Team(final String teamLongName, final String teamShortName,
                final String teamLogo, final League currentLeague,
                final Integer[] primaryFormation, final Color teamColor,
                final Integer transferBudgetMillions) {
        this.type = "Club";

        this.name = teamLongName;
        this.shortName = teamShortName;
        this.logo = teamLogo;
        this.color = teamColor;

        this.league = currentLeague;
        this.formation = primaryFormation;

        this.players = new ArrayList<>();

        this.fixtures = new ArrayList<>();

        //League stats
        this.leagueMatchesPlayed = 0;
        this.leagueWins = 0;
        this.leagueDraws = 0;
        this.leagueLosses = 0;
        this.leaguePoints = 0;

        //Cup stats
        this.cupMatchesPlayed = 0;
        this.advancingToNextRound = false;

        //Cup ROUND stats
        this.cupGames = 0;
        this.cupWins = 0;
        this.cupDraws = 0;
        this.cupLosses = 0;
        this.cupPoints = 0;
        this.cupGoalsScored = 0;

        this.championsLeagueGroupStage = new ArrayList<>();

        this.initialTransferBudget = transferBudgetMillions * 1000000;
        this.transferBudget = transferBudgetMillions * 1000000;

        league.addTeam(this); //after creating a team, add them to their league.
    }


    /**
     * Constructor to create a new international team.
     * @param nationalTeam the nation which the team represents.
     */
    public Team(final Nation nationalTeam) {
        this.type = "International";

        this.name = nationalTeam.getNationName();
        this.shortName = nationalTeam.getNationName();
        this.logo = nationalTeam.getNationFlag();
        this.color = Color.BLUE;

        this.league = Data.international.getLeagueByTier(1); //world cup

        final int numberOfDefenders = 4;
        final int numberOfMidfielders = 3;
        final int numberOfForwards = 3;

        this.formation = new Integer[]{
                numberOfDefenders, numberOfMidfielders, numberOfForwards};

        this.players = nationalTeam.getBestSquad();

        this.fixtures = new ArrayList<>();

        //League stats
        this.leagueMatchesPlayed = 0;
        this.leagueWins = 0;
        this.leagueDraws = 0;
        this.leagueLosses = 0;
        this.leaguePoints = 0;

        //Cup stats
        this.cupMatchesPlayed = 0;
        this.advancingToNextRound = false;

        //Cup ROUND stats
        this.cupGames = 0;
        this.cupWins = 0;
        this.cupDraws = 0;
        this.cupLosses = 0;
        this.cupPoints = 0;
        this.cupGoalsScored = 0;

        this.initialTransferBudget = 0;
        this.transferBudget = 0;

        Data.international.getLeagueByTier(1).addTeam(this);
    }

    /**
     * Adds a player to the team's list of players.
     * Used when a new player is created with this team as their team,
     * or when a player transfers to this team.
     * @param player the player to add to the team.
     */
    protected void addPlayer(final Player player) {
        this.players.add(player);
    }

    /**
     * Getter method to get the team's full name. (e.g. Manchester United)
     * @return the team's long/full name.
     */
    public String getTeamName() {
        return name;
    }

    /**
     * Getter method to get the team's shortened name (e.g. Man Utd)
     * @return the team's shortened name.
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Getter method to get the team logo's file name in the teamImages folder.
     * @return the team's logo file name.
     */
    public String getTeamLogo() {
        return logo;
    }


    private ArrayList<Player> getPlayersByPosition(
            final String position, final Integer numberOfPlayers) {
        ArrayList<Player> playersInPosition = new ArrayList<>();
        for (Player player : players) {
            if (player.getPosition().equals(position)) {
                playersInPosition.add(player);
            }
        }

        Nation.getBestPlayers(numberOfPlayers, playersInPosition);

        return playersInPosition;
    }

    /**
     * Sets the team's starting 11 as the best players in the team that fit the
     * team's formation. Sets the substitutes as the remaining players in the
     * squad.
     */
    public void setDefaultStartingElevenandSubs() {
        startingEleven = bestStartingEleven();

        substitutes = bestSubs();
    }

    /**
     * Generates the teams best starting eleven and returns it as an array.
     * @return array of the players in the team's best starting 11. (length=11)
     */
    private Player[] bestStartingEleven() {
        final int startingLineupSize = 11;

        Player[] starting11 = new Player[startingLineupSize];

        Integer numberGoalkeepers = 1;
        Integer numberDefenders = formation[0];
        Integer numberMidfielders = formation[1];
        Integer numberForwards = formation[2];

        ArrayList<Player> goalkeepers =
                getPlayersByPosition("GK", numberGoalkeepers);
        ArrayList<Player> defenders =
                getPlayersByPosition("DEF", numberDefenders);
        ArrayList<Player> midfielders =
                getPlayersByPosition("MID", numberMidfielders);
        ArrayList<Player> forwards =
                getPlayersByPosition("FWD", numberForwards);

        starting11[0] = goalkeepers.get(0);

        int playerIndex = 1;
        for (Player player : defenders) {
            starting11[playerIndex] = player;
            playerIndex++;
        }
        for (Player player : midfielders) {
            starting11[playerIndex] = player;
            playerIndex++;
        }
        for (Player player : forwards) {
            starting11[playerIndex] = player;
            playerIndex++;
        }

        return starting11;
    }

    private ArrayList<Player> bestSubs() {

        ArrayList<Player> subs = new ArrayList<>(players);

        for (Player player : getStartingEleven()) {
            subs.remove(player);
        }
        return subs;
    }
    /**
     * converts the team's formation from an array to a string and returns it.
     * @return the team's formation as a string. (e.g. 4-3-3)
     */
    public String getFormationInText() {
        return formation[0] + "-" + formation[1] + "-" + formation[2];
    }

    /**
     * Getter method to get the team's league.
     * @return the team the league is currently playing in.
     */
    public League getLeague() {
        return league;
    }

    /**
     * Getter method to get the number of league matches played by the team
     * this season.
     * @return the number of league matches played by the team this season.
     */
    public Integer getLeagueMatchesPlayed() {
        return leagueMatchesPlayed;
    }


    /**
     * Getter method to get the number of league games won by the team
     * this season.
     * @return the number of league wins the team has this season.
     */
    public Integer getLeagueWins() {
        return leagueWins;
    }

    /**
     * Getter method to get the number of league games drawn by the team
     * this season.
     * @return the number of league draws the team has this season.
     */
    public Integer getLeagueDraws() {
        return leagueDraws;
    }

    /**
     * Getter method to get the number of league games lost by the team
     * this season.
     * @return the number of league losses the team has this season.
     */
    public Integer getLeagueLosses() {
        return leagueLosses;
    }

    /**
     * Getter method to get the number of league points of the team this season.
     * @return the number of league points the team has this season.
     */
    public Integer getLeaguePoints() {
        return leaguePoints;
    }

    /**
     * Function to apply when the team wins a league match.
     * Increases the number of league matches played by 1.
     * Increases the number of league wins by 1.
     * Increases the number of league points by 3.
     */
    public void addLeagueWin() {
        final int pointsForWin = 3;

        this.leagueMatchesPlayed += 1;
        this.leagueWins += 1;
        this.leaguePoints += pointsForWin;
    }

    /**
     * Function to apply when the team draws a league match.
     * Increases the number of league matches played by 1.
     * Increases the number of league draws by 1.
     * Increases the number of league points by 1.
     */
    public void addLeagueDraw() {
        this.leagueMatchesPlayed += 1;
        this.leagueDraws += 1;
        this.leaguePoints += 1;
    }

    /**
     * Function to apply when the team loses a league match.
     * Increases the number of league matches played by 1.
     * Increases the number of league losses by 1.
     */
    public void addLeagueLoss() {
        this.leagueMatchesPlayed += 1;
        this.leagueLosses += 1;
    }

    /**
     * Function to apply when the team wins a cup match.
     * Increases the number of cup matches played by 1.
     * Increases the number of cup games played this round by 1.
     * Increases the number of cup wins by 1.
     * Increases the number of cup points by 3.
     * Increases the number of cup goals scored this round by the number of
     * goals scored. (this is used to calculate aggregate results in two-legged
     * knockout rounds)
     * @param goalsScored the number of goals scored by the team in the match.
     */
    public void addCupWin(final Integer goalsScored) {
        final int pointsForWin = 3;

        this.cupMatchesPlayed += 1;

        this.cupGames += 1;
        this.cupWins += 1;
        this.cupPoints += pointsForWin;

        this.cupGoalsScored += goalsScored;
    }

    /**
     * Function to apply when the team draws a cup match.
     * Increases the number of cup matches played by 1.
     * Increases the number of cup games played this round by 1.
     * Increases the number of cup draws by 1.
     * Increases the number of cup points by 1.
     * Increases the number of cup goals scored this roundby the number of
     * goals scored. (this is used to calculate aggregate results in two-legged
     * knockout rounds)
     * @param goalsScored the number of goals scored by the team in the match.
     */
    public void addCupDraw(final Integer goalsScored) {
        this.cupMatchesPlayed += 1;

        this.cupGames += 1;
        this.cupDraws += 1;
        this.cupPoints += 1;

        this.cupGoalsScored += goalsScored;
    }

    /**
     * Function to apply when the team loses a cup match.
     * Increases the number of cup matches played by 1.
     * Increases the number of cup games played this round by 1.
     * Increases the number of cup losses by 1.
     * Increases the number of cup goals scored this round by the number of
     * goals scored. (this is used to calculate aggregate results in two-legged
     * knockout rounds)
     * @param goalsScored the number of goals scored by the team in the match.
     */
    public void addCupLoss(final Integer goalsScored) {
        this.cupMatchesPlayed += 1;

        this.cupGames += 1;
        this.cupLosses += 1;

        this.cupGoalsScored += goalsScored;
    }

    /**
     * Getter method to get all the team's players.
     * @return an ArrayList of all the team's players.
     */
    protected ArrayList<Player> getAllPlayers() {
        return players;
    }

    /**
     * Getter method to get the team's starting 11.
     * @return an array of the team's starting 11 players. (length=11)
     */
    public Player[] getStartingEleven() {
        return startingEleven;
    }

    /**
     * Sorts the team's players by goals scored and returns them as an
     * arrayList, sorted in descending order.
     * @return an arrayList of the team's players sorted by goals scored.(desc)
     */
    public ArrayList<Player> getTopGoalscorers() {
        ArrayList<Player> topGoalscorers = new ArrayList<>(players);
        topGoalscorers.sort((o1, o2) ->
                o2.getLeagueGoals().compareTo(o1.getLeagueGoals()));
        return topGoalscorers;
    }

    /**
     * Gets all the team's players who are not in the starting 11 and returns
     * them as an arrayList. (size = 5-12)
     * @return an arrayList of the team's substitutes.
     */
    public ArrayList<Player> getSubstitutes() {
        return substitutes;
    }

    /**
     * Function to change the starting lineup in the 'manage team' section.
     * Changes the lineup that will start in the next match.
     * @param playerOut the player to be replaced in the starting lineup.
     * @param playerIn the player to be added to the starting lineup.
     */
    public void makeSubstitution(final Player playerOut,
                                 final Player playerIn) {
        for (int i = 0; i < startingEleven.length; i++) {
            if (startingEleven[i] == playerOut) {
                startingEleven[i] = playerIn;
                break;
            }
        }
        //replace playerIn with playerOut in the substitutes.
        for (int i = 0; i < substitutes.size(); i++) {
            if (substitutes.get(i) == playerIn) {
                substitutes.set(i, playerOut);
                break;
            }
        }
    }

    /**
     * Searches the teams list of current players and returns the matching
     * player if there is one, otherwise returns null.
     * @param playerName the name to search.
     * @return player matching playerName if exists, otherwise null.
     */
    public Player getPlayerByName(final String playerName) {
        for (Player player : players) {
            if (player.getPlayerName().equals(playerName)) {
                return player;
            }
        }
        return null;
    }

    /**
     * Adds a new game to the team's list of fixtures, if this is a user team,
     * the game will appear in the calendar and be simulated in gameSimulator
     * on the date, otherwise the game will be simulated in the background
     * simultaneously as the user plays a game in the same gameweek.
     * @param game game to be added to the team's list of fixtures.
     */
    public void addFixture(final Game game) {
        this.fixtures.add(game);
    }

    /**
     * Returns an arrayList of all the team's fixtures currently scheduled.
     * @return arrayList of all games upcoming for the team.
     */
    public ArrayList<Game> getFixtures() {
        return fixtures;
    }

    /**
     * Getter method to get the team's primary colour.
     * @return the team's primary colour.
     */
    public Color getTeamColor() {
        return color;
    }

    /**
     * Removes a player from their team, and does not assign a new team.
     * This is used to retire players.
     * @param player player to be removed from team (e.g. retired)
     */
    protected void removePlayer(final Player player) {
        this.players.remove(player);
    }

    /**
     * Returns the number of players in the team.
     * Is used to query team size to make sure team does not exceed max size,
     * or go below minimum size.
     * @return size of the team's player list.
     */
    public Integer getNumberOfPlayers() {
        return players.size();
    }

    /**
     * Returns the number of goalkeepers in the teams player list.
     * Is used to make sure the team doesn't have too many or too few
     * goalkeepers before completing a transfer.
     * @return number of "GK" players in the team's list of current players.
     */
    public Integer getNumberOfGoalkeepers() {
        Integer numberOfGoalkeepers = 0;
        for (Player player : players) {
            if (player.getPosition().equals("GK")) {
                numberOfGoalkeepers++;
            }
        }
        return numberOfGoalkeepers;
    }

    /**
     * Returns the number of defenders in the teams player list.
     * Is used to make sure the team doesn't have too many or too few
     * defenders before completing a transfer.
     * @return number of "DEF" players in the team's list of current players.
     */
    public Integer getNumberOfDefenders() {
        Integer numberOfDefenders = 0;
        for (Player player : players) {
            if (player.getPosition().equals("DEF")) {
                numberOfDefenders++;
            }
        }
        return numberOfDefenders;
    }

    /**
     * Returns the number of midfielders in the teams player list.
     * Is used to make sure the team doesn't have too many or too few
     * midfielders before completing a transfer.
     * @return number of "MID" players in the team's list of current players.
     */
    public Integer getNumberOfMidfielders() {
        Integer numberOfMidfielders = 0;
        for (Player player : players) {
            if (player.getPosition().equals("MID")) {
                numberOfMidfielders++;
            }
        }
        return numberOfMidfielders;
    }

    /**
     * Returns the number of forwards in the teams player list.
     * Is used to make sure the team doesn't have too many or too few
     * forwards before completing a transfer.
     * @return number of "FWD" players in the team's list of current players.
     */
    public Integer getNumberOfForwards() {
        Integer numberOfForwards = 0;
        for (Player player : players) {
            if (player.getPosition().equals("FWD")) {
                numberOfForwards++;
            }
        }
        return numberOfForwards;
    }

    /**
     * Resets all the team's league stats to 0.
     * Used to reset stats at the end of the season.
     */
    public void resetLeagueStats() {
        this.leagueMatchesPlayed = 0;
        this.leagueWins = 0;
        this.leagueDraws = 0;
        this.leagueLosses = 0;
        this.leaguePoints = 0;
    }

    /**
     * Resets all the team's cup stats to 0.
     * Used to reset stats at the end of the season.
     */
    public void resetCupStats() {
        this.cupMatchesPlayed = 0;

        this.cupGames = 0;
        this.cupWins = 0;
        this.cupDraws = 0;
        this.cupLosses = 0;
        this.cupPoints = 0;
    }

    /**
     * Resets round-specific cup stats at the end of each round of the cup.
     * (e.g. Champions League group stage, Champions League knockout round 1)
     * Round-specific stats must be reset so aggregate scoring on two-legged
     * knockout rounds can be tracked accurately to determine which team
     * progresses to the next round.
     */
    public void resetCupRoundStats() {
        this.cupGames = 0;
        this.cupWins = 0;
        this.cupDraws = 0;
        this.cupLosses = 0;
        this.cupPoints = 0;

        this.cupGoalsScored = 0;
    }

    /**
     * Getter method for the team's current transfer budget.
     * @return the team's current transfer budget.
     * Used to display transfer budget in transfer market UI.
     * Also used to query transfers to make sure the team has enough funds,
     * before the deal goes ahead.
     * If the team has insufficient funds, the transfer will not go through.
     */
    public Integer getTransferBudget() {
        return transferBudget;
    }

    /**
     * Reduces the team's transfer budget by the int amount passed as args.
     * Used to reduce the team's budget after a player is bought.
     * @param amount amount to reduce the team's transfer budget by.
     */
    public void reduceTransferBudget(final Integer amount) {
        this.transferBudget -= amount;
    }

    /**
     * Increases the team's transfer budget by the int amount passed as args.
     * Used to increase the team's budget after a player is sold.
     * @param amount amount to increase the team's transfer budget by.
     */
    public void increaseTransferBudget(final Integer amount) {
        this.transferBudget += amount;
    }

    /**
     * Getter method for the team's type.
     * (either "Club" or "International")
     * @return the team's type. ("Club" or "International")
     */
    public String getTeamType() {
        return type;
    }

    /**
     * Getter method for the total number of Champions league matches played
     * this season.
     * Used to determine which round of the champions league the team is in.
     * @return the total number of champions league matches played this season.
     */
    public Integer getCupMatchesPlayed() {
        return cupMatchesPlayed;
    }

    /**
     * Getter method to return the number of cup games played in THIS ROUND
     * or the champions league. Used to determine whether a round should end.
     * @return the number of cup games played in THIS ROUND.
     */
    public Integer getCupGames() {
        return cupGames;
    }

    /**
     * Getter method to return the number of cup wins in THIS ROUND.
     * @return the number of cup wins in THIS ROUND.
     */
    public Integer getCupWins() {
        return cupWins;
    }

    /**
     * Getter method to return the number of cup draws in THIS ROUND.
     * @return the number of cup draws in THIS ROUND.
     */
    public Integer getCupDraws() {
        return cupDraws;
    }

    /**
     * Getter method to return the number of cup losses in THIS ROUND.
     * @return the number of cup losses in THIS ROUND.
     */
    public Integer getCupLosses() {
        return cupLosses;
    }

    /**
     * Getter method for the number of points scored in THIS ROUND of the cup.
     * Used to determine group stage standings.
     * @return number of points scored in THIS ROUND of the cup.
     */
    public Integer getCupPoints() {
        return cupPoints;
    }

    /**
     * Sets the team's transfer budget to their initial transfer budget.
     * Used at the end of the season.
     */
    public void resetTransferBudget() {
        this.transferBudget = initialTransferBudget;
    }

    /**
     * Adds all team's in the team's champions league group to an arraylist.
     * So they can be sorted into a group stage table.
     * @param teams all the teams in the team's champions league group.
     */
    public void setChampionsLeagueGroup(final ArrayList<Team> teams) {
        championsLeagueGroupStage.addAll(teams);
    }

    /**
     * Sets the letter of the team's champions league group.
     * @param groupNumber the number of the group the team is in.
     *                    1 - > 'A'
     *                    2 - > 'B'
     *                    3 - > 'C'
     *                    4 - > 'D'
     *                    5 - > 'E'
     *                    6 - > 'F'
     *                    7 - > 'G'
     *                    8 - > 'H'
     */
    public void setChampionsLeagueGroupLetter(final Integer groupNumber) {
        switch (groupNumber) {
            case 1 -> championsLeagueGroupLetter = 'A';
            case 2 -> championsLeagueGroupLetter = 'B';
            case 3 -> championsLeagueGroupLetter = 'C';
            case 4 -> championsLeagueGroupLetter = 'D';
            case 5 -> championsLeagueGroupLetter = 'E';
            case 6 -> championsLeagueGroupLetter = 'F';
            case 7 -> championsLeagueGroupLetter = 'G';
            case 8 -> championsLeagueGroupLetter = 'H';
            default -> championsLeagueGroupLetter = null;
        }
    }

    /**
     * Getter method to get the letter of the team's champions league group.
     * @return the letter of the champions league group (e.g. A, B, C, D, E, F)
     */
    public Character getChampionsLeagueGroupLetter() {
        return championsLeagueGroupLetter;
    }

    /**
     * Gets all the teams in the team's champions league group and sorts them
     * by points, to determine who advances to the knockout rounds.
     * @return an arrayList of the teams in the team's champions league group,
     * sorted by points.
     */
    public ArrayList<Team> getChampionsLeagueGroupStandings() {
        ArrayList<Team> standings = new ArrayList<>(championsLeagueGroupStage);
        standings.sort((o1, o2) ->
                o2.getCupPoints().compareTo(o1.getCupPoints()));
        return standings;
    }

    /**
     * Reset the team's champions league group at the end of the season, in
     * case the team is in the champions league again and needs to generate
     * a new group.
     */
    public void resetChampionsLeagueGroupStage() {
        this.championsLeagueGroupStage = new ArrayList<>();
        this.championsLeagueGroupLetter = null;
    }

    /**
     * Getter method for number of goals scored by the team in THIS ROUND of
     * the cup (e.g. champions league).
     * Useful for determining aggregate scores in two-legged knockout rounds,
     * or tiebreakers if teams are equal on points at the end of the group
     * stage.
     * @return the number of goals scored by the team in THIS ROUND of the cup.
     */
    public Integer getCupGoalsScored() {
        return cupGoalsScored;
    }

    /**
     * Sets the teams progression status to 'true' or 'false' depending on
     * whether they are advancing to the next cup round.
     * Group stage = top 2 teams progress.
     * Knockout rounds = winner of the two-legged tie progresses.
     * Advancing teams are filtered through and used to generate the next
     * round of fixtures in the cup (e.g. Champions League).
     * @param isAdvancing 'true' if the team is advancing to the next
     *                             round, 'false' if the team is eliminated.
     */
    public void setAdvancingToNextRound(final Boolean isAdvancing) {
        this.advancingToNextRound = isAdvancing;
    }

    /**
     * Getter method for the team's progression status in the cup.
     * @return true if the team is advancing to the next round, false if not.
     */
    public Boolean isAdvancingToNextRound() {
        return advancingToNextRound;
    }

    /**
     * Clears the team's entire fixture list, to make room for new fixtures
     * at the start of a new season.
     */
    public void wipeFixtures() {
        this.fixtures = new ArrayList<>();
    }

    /**
     * Returns the team's weakest starter in the specified position.
     * Is used to determine whether a listed player would be a realistic
     * transfer target for the team.
     * Stops CPU teams from signing players significantly below their level.
     * @param position position to query.
     * @return lowest rated starter at position passed as args.
     */
    protected Player getLowestRatedStarterAtPosition(final String position) {
        Player lowestRatedPlayer = null;
        for (Player player : startingEleven) {
            if (player.getPosition().equals(position)) {
                if (lowestRatedPlayer == null
                        || player.getRating() < lowestRatedPlayer.getRating()) {
                    lowestRatedPlayer = player;
                }
            }
        }
        return lowestRatedPlayer;
    }

    /**
     * Calculates and returns the teams defensive rating, based upon the
     * average rating or the team's current defenders and goalkeeper.
     * A higher defensive rating reduces the opposition's chance of scoring.
     * @return the team's defensive rating (higher is better).
     */
    public Integer getDefensiveRating() {
        final int minimumPlayerRating = 60;
        final int numberOfDefensivePlayers = 1 + formation[0];

        int defensiveRating = 0;
        for (Player player : startingEleven) {
            if (player.getPosition().equals("GK")
                    || player.getPosition().equals("DEF")) {
                defensiveRating += (player.getRating() - minimumPlayerRating);
            }
        }
        defensiveRating /= (numberOfDefensivePlayers * 2);
        return defensiveRating;
    }

    /**
     * Calculates and returns the teams offensive rating, based upon the
     * average rating of the team's current midfielders and forwards.
     * A higher offensive rating increases the team's chance of scoring.
     * @return the team's offensive rating (higher is better).
     */
    public Integer getOffensiveRating() {
        final int minimumPlayerRating = 60;
        final int numberOfOffensivePlayers = formation[1] + formation[2];

        int offensiveRating = 0;
        for (Player player : startingEleven) {
            if (player.getPosition().equals("MID")
                    || player.getPosition().equals("FWD")) {
                offensiveRating += (player.getRating() - minimumPlayerRating);
            }
        }
        offensiveRating /= (numberOfOffensivePlayers * 2);
        return offensiveRating;
    }

    /**
     * Relegates the team to the next league down.
     * Removes the team from their current league and adds them to the league
     * at the next tier down in the football pyramid in the same country.
     * Used when a team is relegated at the end of the season.
     * (e.g. Premier League -> Championship)
     */
    public void relegate() {

        league.removeTeam(this);
        this.league = league.getCountry().getLeagueByTier(league.getTier() + 1);
        league.addTeam(this);
    }

    /**
     * Promotes the team to the next league up.
     * Removes the team from their current league and adds them to the league
     * at the next tier up in the football pyramid in the same country.
     * Used when a team is promoted at the end of the season.
     * (e.g. Championship -> Premier League)
     */
    public void promote() {

        league.removeTeam(this);
        this.league = league.getCountry().getLeagueByTier(1);
        league.addTeam(this);
    }


    /**
     * Function to generate a 2D ArrayList of 3 random substitutions for the
     * team to make in the match.
     * @return a 2D arrayList,
     * where the 1st inner arrayList contains the players to be subbed off,
     * and the 2nd inner arrayList contains the players to be subbed in.
     * ------------------------------------------------
     * The two inner arrayLists are parralel arrays:
     * e.g. 1st sub = .get(0).get(0) subbed off, .get(1).get(0) subbed in.
     * 2nd sub = .get(0).get(1) subbed off, .get(1).get(1) subbed in.
     * 3rd sub = .get(0).get(2) subbed off, .get(1).get(2) subbed in.
     */
    public ArrayList<ArrayList<Player>> getSubstitutions() {
        ArrayList<ArrayList<Player>> substitutions = new ArrayList<>();

        // don't let the number of players subbed in any position exceed the
        // number of players of that position on the bench.
        int noOfDefendersSubbed = 0;
        int noOfMidfieldersSubbed = 0;
        int noOfForwardsSubbed = 0;

        int noOfDefendersOnBench = getNumberOfDefenders() - formation[0];
        int noOfMidfieldersOnBench = getNumberOfMidfielders() - formation[1];
        int noOfForwardsOnBench = getNumberOfForwards() - formation[2];

        ArrayList<Player> playersOut = new ArrayList<>();
        ArrayList<Player> playersIn = new ArrayList<>();

        final int starting11Size = 11;
        final int numberOfSubGks = this.getNumberOfGoalkeepers() - 1;
        final int nonGkSubs = substitutes.size() - numberOfSubGks;
        final int maxSubsAllowed = 3;
        final int subsPossible = Math.min(maxSubsAllowed, nonGkSubs);


        for (int i = 0; i < subsPossible; i++) {

            //Part 1: get player to be subbed off.
            Player playerOut = null;

            //regenerate player to be subbed off until a valid option is found.
            while (
                    playerOut == null
                    || playersOut.contains(playerOut)
                    || playerOut.getPosition().equals("DEF")
                            && noOfDefendersSubbed >= noOfDefendersOnBench
                    || playerOut.getPosition().equals("MID")
                            && noOfMidfieldersSubbed >= noOfMidfieldersOnBench
                    || playerOut.getPosition().equals("FWD")
                            && noOfForwardsSubbed >= noOfForwardsOnBench
            ) {
                playerOut = startingEleven[
                        (int) (Math.random() * (starting11Size - 1) + 1)];
            }

            switch (playerOut.getPosition()) {
                case "DEF" -> noOfDefendersSubbed++;
                case "MID" -> noOfMidfieldersSubbed++;
                case "FWD" -> noOfForwardsSubbed++;
                default -> throw new IllegalStateException("Unexpected value: "
                        + playerOut.getPosition());
            }

            playersOut.add(playerOut);

            //Part 2: get player to be subbed in.
            Player playerIn = null;

            while (
                    playerIn == null
                    || playersIn.contains(playerIn)
                    || !playerIn.getPosition().equals(playerOut.getPosition())
            ) {
                playerIn = substitutes.get((int) (Math.random()
                        * substitutes.size()));
            }

            playersIn.add(playerIn);
        }

        substitutions.add(playersOut);
        substitutions.add(playersIn);


        // .get(0) and .get(1) will be parallel arrays.
        // e.g. 1st sub = .get(0).get(0) subbed off, .get(1).get(0) subbed in.
        // 2nd sub = .get(0).get(1) subbed off, .get(1).get(1) subbed in.
        // 3rd sub = .get(0).get(2) subbed off, .get(1).get(2) subbed in.

        return substitutions;
    }

    /**
     * Getter method to get the team's formation.
     * @return the team's formation as an array.
     */
    public Integer[] getFormation() {
        return formation;
    }
}
