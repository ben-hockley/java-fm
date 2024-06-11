package objects;

import data.Data;
import events.Game;

import java.awt.*;
import java.util.ArrayList;

public class Team {
    //Team details, these will not change.
    private final String teamType;
    private final String teamName;
    private final String shortName;
    private final String teamLogo;

    //Team properties that can change.
    private League league;


    //League stats.
    private Integer leaguePoints;
    private Integer leagueMatchesPlayed;
    private Integer leagueWins;
    private Integer leagueDraws;
    private Integer leagueLosses;

    //Cup stats
    private Integer cupPoints;
    private Integer cupMatchesPlayed;
    private Integer cupGames;
    private Integer cupWins;
    private Integer cupDraws;
    private Integer cupLosses;
    private Integer cupGoalsScored;
    private Boolean advancingToNextRound;

    private ArrayList<Team> championsLeagueGroupStage; //list of other teams in the team's champions league group.

    private Integer championsLeagueGroupNumber; //the group number of the team's champions league group.

    private Character championsLeagueGroupLetter; //the group letter of the team's champions league group.



    private final Integer[] formation; //e.g. 4-4-2 would be [4,4,2], should always be 3 numbers.

    private Player[] startingEleven;

    private ArrayList<Player> substitutes;

    private ArrayList<Game> fixtures;

    private final Color teamColor;

    private final Integer initialTransferBudget;
    private Integer transferBudget;


    public ArrayList<Player> players;

    //constructor to create club teams.
    public Team(String teamName,String shortName ,String teamLogo, League league, Integer[] formation, Color teamColor, Integer transferBudget_millions) {
        this.teamType = "Club";

        this.teamName = teamName;
        this.shortName = shortName;
        this.teamLogo = teamLogo;
        this.teamColor = teamColor;

        this.league = league;
        this.formation = formation;

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

        this.initialTransferBudget = transferBudget_millions * 1000000;
        this.transferBudget = transferBudget_millions * 1000000;

        league.addTeam(this); //after creating a team, add them to their league.
    }


    //constructor to create national teams.
    public Team(Nation nationalTeam){
        this.teamType = "International";

        this.teamName = nationalTeam.getNationName();
        this.shortName = nationalTeam.getNationName();
        this.teamLogo = nationalTeam.getNationFlag();
        this.teamColor = Color.BLUE;

        this.league = Data.international.getLeagueByTier(1); //world cup
        this.formation = new Integer[]{4,3,3};

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

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public String getTeamName() {
        return teamName;
    }

    public String getShortName() {
        return shortName;
    }

    public String getTeamLogo() {
        return teamLogo;
    }

    //searches for players by position and returns the top n players.
    //this function could be used to pick the starting lineup for an CPU controlled team.
    //e.g. the CPU could play 4-4-2 and would call getPlayersByPosition("DEF", 4) to get the top 4 defenders.
    //I could advance this function by adding more parameters to players and eliminating injured/suspended players.
    public ArrayList<Player> getPlayersByPosition(String position, Integer numberOfPlayers){
        ArrayList<Player> playersInPosition = new ArrayList<>();
        for (Player player : players) {
            if (player.getPosition().equals(position)) {
                playersInPosition.add(player);
            }
        }

        while (playersInPosition.size() > numberOfPlayers) {
            Integer lowestRating = 100;
            Player lowestRatedPlayer = null;
            for (Player player : playersInPosition) {

                if (player.getRating() < lowestRating) {
                    lowestRating = player.getRating();
                    lowestRatedPlayer = player;
                }
            }
            playersInPosition.remove(lowestRatedPlayer);
        }

        return playersInPosition;
    }

    public void setDefaultStartingElevenandSubs() {
        startingEleven = bestStartingEleven();

        substitutes = bestSubs();
    }

    //gets the best starting 11 for the team.
    //finds the best combination of goalkeepers, defenders, midfielders and forwards based on the formation.
    //sorts players by player rating and puts the highest rated players in the starting 11.
    //this function could be used to pick the starting lineup for an CPU controlled team.
    private Player[] bestStartingEleven(){
        Player[] startingEleven = new Player[11];

        Integer numberGoalkeepers = 1;
        Integer numberDefenders = formation[0];
        Integer numberMidfielders = formation[1];
        Integer numberForwards = formation[2];

        ArrayList<Player> goalkeepers = getPlayersByPosition("GK", numberGoalkeepers);
        ArrayList<Player> defenders = getPlayersByPosition("DEF", numberDefenders);
        ArrayList<Player> midfielders = getPlayersByPosition("MID", numberMidfielders);
        ArrayList<Player> forwards = getPlayersByPosition("FWD", numberForwards);

        startingEleven[0] = goalkeepers.get(0);

        int playerIndex = 1;
        for (Player player : defenders) {
            startingEleven[playerIndex] = player;
            playerIndex++;
        }
        for (Player player : midfielders) {
            startingEleven[playerIndex] = player;
            playerIndex++;
        }
        for (Player player : forwards) {
            startingEleven[playerIndex] = player;
            playerIndex++;
        }

        return startingEleven;
    }

    private ArrayList<Player> bestSubs() {

        ArrayList<Player> subs = new ArrayList<>(players);

        for (Player player : getStartingEleven()) {
            subs.remove(player);
        }
        return subs;
    }

    public String getFormationInText() {
        return formation[0] + "-" + formation[1] + "-" + formation[2];
    }

    public League getLeague(){
        return league;
    }

    public Integer getLeagueMatchesPlayed() {
        return leagueMatchesPlayed;
    }


    public Integer getLeagueWins() {
        return leagueWins;
    }

    public Integer getLeagueDraws() {
        return leagueDraws;
    }

    public Integer getLeagueLosses() {
        return leagueLosses;
    }

    public Integer getLeaguePoints() {
        return leaguePoints;
    }

    public void addLeagueWin() {
        this.leagueMatchesPlayed += 1;
        this.leagueWins += 1;
        this.leaguePoints += 3;
    }

    public void addLeagueDraw() {
        this.leagueMatchesPlayed += 1;
        this.leagueDraws += 1;
        this.leaguePoints += 1;
    }

    public void addLeagueLoss() {
        this.leagueMatchesPlayed += 1;
        this.leagueLosses += 1;
    }

    public void addCupWin(Integer goalsScored) {
        this.cupMatchesPlayed += 1;

        this.cupGames += 1;
        this.cupWins += 1;
        this.cupPoints += 3;

        this.cupGoalsScored += goalsScored;
    }

    public void addCupDraw(Integer goalsScored) {
        this.cupMatchesPlayed += 1;

        this.cupGames += 1;
        this.cupDraws += 1;
        this.cupPoints += 1;

        this.cupGoalsScored += goalsScored;
    }

    public void addCupLoss(Integer goalsScored) {
        this.cupMatchesPlayed += 1;

        this.cupGames += 1;
        this.cupLosses += 1;

        this.cupGoalsScored += goalsScored;
    }
    public ArrayList<Player> getAllPlayers() {
        return players;
    }

    public Integer getRating() {
        Player[] startingEleven = getStartingEleven();
        Integer teamRating = -700;
        for (Player player : startingEleven) {
            teamRating += player.getRating();
        }
        return teamRating;
    }

    public Player[] getStartingEleven() {
        return startingEleven;
    }

    public ArrayList<Player> getTopGoalscorers(){
        ArrayList<Player> topGoalscorers = new ArrayList<>(players);
        topGoalscorers.sort((o1, o2) -> o2.getLeagueGoals().compareTo(o1.getLeagueGoals()));
        return topGoalscorers;
    }

    public ArrayList<Player> getSubstitutes() {
        return substitutes;
    }

    public void makeSubstitution(Player playerOut, Player playerIn) {
        //replace playerOut with playerIn in the starting 11.
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

    public Player getPlayerByName(String playerName) {
        for (Player player : players) {
            if (player.getPlayerName().equals(playerName)) {
                return player;
            }
        }
        return null;
    }

    public void addFixture(Game game) {
        this.fixtures.add(game);
    }

    public ArrayList<Game> getFixtures() {
        return fixtures;
    }

    public Color getTeamColor() {
        return teamColor;
    }

    public void removePlayer(Player player) {
        this.players.remove(player);
    }

    public Integer getNumberOfPlayers() {
        return players.size();
    }

    public Integer getNumberOfGoalkeepers() {
        Integer numberOfGoalkeepers = 0;
        for (Player player : players) {
            if (player.getPosition().equals("GK")) {
                numberOfGoalkeepers++;
            }
        }
        return numberOfGoalkeepers;
    }

    public Integer getNumberOfDefenders() {
        Integer numberOfDefenders = 0;
        for (Player player : players) {
            if (player.getPosition().equals("DEF")) {
                numberOfDefenders++;
            }
        }
        return numberOfDefenders;
    }

    public Integer getNumberOfMidfielders() {
        Integer numberOfMidfielders = 0;
        for (Player player : players) {
            if (player.getPosition().equals("MID")) {
                numberOfMidfielders++;
            }
        }
        return numberOfMidfielders;
    }

    public Integer getNumberOfForwards() {
        Integer numberOfForwards = 0;
        for (Player player : players) {
            if (player.getPosition().equals("FWD")) {
                numberOfForwards++;
            }
        }
        return numberOfForwards;
    }


    //can be used for promotion, relegation.
    public void setNewLeague(League newLeague){
        league.removeTeam(this);
        this.league = newLeague;
        newLeague.addTeam(this);
    }

    public void resetLeagueStats() {
        this.leagueMatchesPlayed = 0;
        this.leagueWins = 0;
        this.leagueDraws = 0;
        this.leagueLosses = 0;
        this.leaguePoints = 0;
    }
    public void resetCupStats() {
        this.cupMatchesPlayed = 0;

        this.cupGames = 0;
        this.cupWins = 0;
        this.cupDraws = 0;
        this.cupLosses = 0;
        this.cupPoints = 0;
    }

    public void resetCupRoundStats() {
        this.cupGames = 0;
        this.cupWins = 0;
        this.cupDraws = 0;
        this.cupLosses = 0;
        this.cupPoints = 0;

        this.cupGoalsScored = 0;
    }

    public Integer getTransferBudget() {
        return transferBudget;
    }

    public void reduceTransferBudget(Integer amount) {
        this.transferBudget -= amount;
    }

    public void increaseTransferBudget(Integer amount){
        this.transferBudget += amount;
    }

    public String getTeamType() {
        return teamType;
    }

    public void addCupMatchPlayed() {
        this.cupMatchesPlayed += 1;
    }

    public Integer getCupMatchesPlayed(){
        return cupMatchesPlayed;
    }

    public Integer getCupGames(){ return cupGames; }
    public Integer getCupWins() {
        return cupWins;
    }
    public Integer getCupDraws() {
        return cupDraws;
    }
    public Integer getCupLosses() {
        return cupLosses;
    }
    public Integer getCupPoints() {
        return cupPoints;
    }

    public void resetTransferBudget() {
        this.transferBudget = initialTransferBudget;
    }

    public void setCupMatchesPlayed(Integer cupMatchesPlayed) {
        this.cupMatchesPlayed = cupMatchesPlayed;
    }

    public void setChampionsLeagueGroup(ArrayList<Team> teams){
        championsLeagueGroupStage.addAll(teams);
    }

    public void setChampionsLeagueGroupNumber(Integer groupNumber){
        this.championsLeagueGroupNumber = groupNumber;

        if (groupNumber == 1){
            this.championsLeagueGroupLetter = 'A';
        } else if (groupNumber == 2){
            this.championsLeagueGroupLetter = 'B';
        } else if (groupNumber == 3){
            this.championsLeagueGroupLetter = 'C';
        } else if (groupNumber == 4){
            this.championsLeagueGroupLetter = 'D';
        } else if (groupNumber == 5){
            this.championsLeagueGroupLetter = 'E';
        } else if (groupNumber == 6){
            this.championsLeagueGroupLetter = 'F';
        } else if (groupNumber == 7){
            this.championsLeagueGroupLetter = 'G';
        } else if (groupNumber == 8){
            this.championsLeagueGroupLetter = 'H';
        }
    }

    public Character getChampionsLeagueGroupLetter(){
        return championsLeagueGroupLetter;
    }

    public ArrayList<Team> getChampionsLeagueGroupStandings(){
        ArrayList<Team> standings = new ArrayList<>(championsLeagueGroupStage);
        standings.sort((o1, o2) -> o2.getCupPoints().compareTo(o1.getCupPoints()));
        return standings;
    }

    public void resetChampionsLeagueGroupStage(){
        this.championsLeagueGroupStage = new ArrayList<>();
        this.championsLeagueGroupNumber = null;
        this.championsLeagueGroupLetter = null;
    }
    public Integer getCupGoalsScored(){
        return cupGoalsScored;
    }

    public void setAdvancingToNextRound(Boolean advancingToNextRound){
        this.advancingToNextRound = advancingToNextRound;
    }

    public Boolean isAdvancingToNextRound(){
        return advancingToNextRound;
    }

    public void wipeFixtures(){
        this.fixtures = new ArrayList<>();
    }

    public Player getLowestRatedStarterAtPosition(String position){
        Player lowestRatedPlayer = null;
        for (Player player : startingEleven) {
            if (player.getPosition().equals(position)) {
                if (lowestRatedPlayer == null || player.getRating() < lowestRatedPlayer.getRating()) {
                    lowestRatedPlayer = player;
                }
            }
        }
        return lowestRatedPlayer;
    }
    public Integer getDefensiveRating(){
        int defensiveRating = 0;
        for (Player player : startingEleven) {
            if (player.getPosition().equals("GK") || player.getPosition().equals("DEF")) {
                defensiveRating += (player.getRating()-60);
            }
        }
        defensiveRating /= 10;
        return defensiveRating;
    }

    public Integer getOffensiveRating() {
        int offensiveRating = 0;
        for (Player player : startingEleven) {
            if (player.getPosition().equals("MID") || player.getPosition().equals("FWD")) {
                offensiveRating += (player.getRating() - 60);
            }
        }
        offensiveRating /= 10;
        return offensiveRating;
    }

    public void relegate(){

        league.removeTeam(this);
        this.league = league.getCountry().getLeagueByTier(league.getTier() + 1);
        league.addTeam(this);
    }

    public void promote(){

        league.removeTeam(this);
        this.league = league.getCountry().getLeagueByTier(1);
        league.addTeam(this);
    }


    /**
     * Function to generate a 2D ArrayList of 3 random substitutions for the team to make in the match.
     * @return a 2D arrayList, where the 1st inner arrayList contains the players to be subbed off, and the 2nd inner arrayList contains the players to be subbed in.
     * ------------------------------------------------
     * The two inner arrayLists are parralel arrays:
     * e.g. 1st sub = .get(0).get(0) subbed off, .get(1).get(0) subbed in.
     * 2nd sub = .get(0).get(1) subbed off, .get(1).get(1) subbed in.
     * 3rd sub = .get(0).get(2) subbed off, .get(1).get(2) subbed in.
     */
    public ArrayList<ArrayList<Player>> getSubstitutions() {
        ArrayList<ArrayList<Player>> substitutions = new ArrayList<>();

        //don't let the number of players subbed in any position exceed the number of players of that position on the bench.
        int noOfDefendersSubbed = 0;
        int noOfMidfieldersSubbed = 0;
        int noOfForwardsSubbed = 0;

        int noOfDefendersOnBench = getNumberOfDefenders() - formation[0];
        int noOfMidfieldersOnBench = getNumberOfMidfielders() - formation[1];
        int noOfForwardsOnBench = getNumberOfForwards() - formation[2];

        ArrayList<Player> playersOut = new ArrayList<>();
        ArrayList<Player> playersIn = new ArrayList<>();
        for (int i=0; i<3; i++){

            //Part 1: get player to be subbed off.
            Player playerOut = null;

            //regenerate player to be subbed off until a valid option is found.
            while (
                    playerOut == null
                    || playersOut.contains(playerOut)
                    || playerOut.getPosition().equals("DEF") && noOfDefendersSubbed >= noOfDefendersOnBench
                    || playerOut.getPosition().equals("MID") && noOfMidfieldersSubbed >= noOfMidfieldersOnBench
                    || playerOut.getPosition().equals("FWD") && noOfForwardsSubbed >= noOfForwardsOnBench
            ) {
                playerOut = startingEleven[(int) (Math.random() * 10 + 1)]; //get any player in the starting 11 except the GK.
            }

            switch (playerOut.getPosition()) {
                case "DEF" -> noOfDefendersSubbed++;
                case "MID" -> noOfMidfieldersSubbed++;
                case "FWD" -> noOfForwardsSubbed++;
            }

            playersOut.add(playerOut);

            //Part 2: get player to be subbed in.
            Player playerIn = null;

            //regenerate player to replace subbed off player found in part 1 until a valid option is found.
            while (
                    playerIn == null
                    || playersIn.contains(playerIn)
                    || !playerIn.getPosition().equals(playerOut.getPosition())
            ) {
                playerIn = substitutes.get((int) (Math.random() * substitutes.size()));
            }

            playersIn.add(playerIn);
        }

        substitutions.add(playersOut); // .get(0) will be the players to be subbed off.
        substitutions.add(playersIn); // .get(1) will be the players to be subbed in.


        // .get(0) and .get(1) will be parallel arrays.
        // e.g. 1st sub = .get(0).get(0) subbed off, .get(1).get(0) subbed in.
        // 2nd sub = .get(0).get(1) subbed off, .get(1).get(1) subbed in.
        // 3rd sub = .get(0).get(2) subbed off, .get(1).get(2) subbed in.

        return substitutions;
    }
}
