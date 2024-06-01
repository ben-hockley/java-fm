package Objects;

import data.Data;
import events.Game;

import java.awt.*;
import java.util.ArrayList;

public class Team {
    //Team details, these will not change.
    private final String teamName;
    private final String shortName;
    private final String teamLogo;

    //Team properties that can change.
    private League league;
    private Integer points;
    private Integer matchesPlayed;
    private Integer wins;
    private Integer draws;
    private Integer losses;
    private final Integer[] formation; //e.g. 4-4-2 would be [4,4,2], should always be 3 numbers.

    private Player[] startingEleven;

    private ArrayList<Player> substitutes;

    private final ArrayList<Game> fixtures;

    private final Color teamColor;

    private Integer transferBudget;


    public ArrayList<Player> players;

    //constructor to create club teams.
    public Team(String teamName,String shortName ,String teamLogo, League league, Integer[] formation, Color teamColor, Integer transferBudget_millions) {
        this.teamName = teamName;
        this.shortName = shortName;
        this.teamLogo = teamLogo;
        this.teamColor = teamColor;

        this.league = league;
        this.formation = formation;

        this.players = new ArrayList<>();

        this.fixtures = new ArrayList<>();

        this.matchesPlayed = 0;
        this.wins = 0;
        this.draws = 0;
        this.losses = 0;
        this.points = 0;

        this.transferBudget = transferBudget_millions * 1000000;

        league.addTeam(this); //after creating a team, add them to their league.
    }


    //constructor to create national teams.
    public Team(Nation nationalTeam){
        this.teamName = nationalTeam.getNationName();
        this.shortName = nationalTeam.getNationName();
        this.teamLogo = nationalTeam.getNationFlag();
        this.teamColor = Color.BLUE;

        this.league = Data.england.getLeagueByTier(1);
        this.formation = new Integer[]{4,3,3};

        this.players = nationalTeam.getBestSquad();

        this.fixtures = new ArrayList<>();

        this.matchesPlayed = 0;
        this.wins = 0;
        this.draws = 0;
        this.losses = 0;
        this.points = 0;

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

    public Integer getMatchesPlayed() {
        return matchesPlayed;
    }

    public Integer getWins() {
        return wins;
    }

    public Integer getDraws() {
        return draws;
    }

    public Integer getLosses() {
        return losses;
    }

    public Integer getPoints() {
        return points;
    }

    public void addWin() {
        this.matchesPlayed += 1;
        this.wins += 1;
        this.points += 3;
    }

    public void addDraw() {
        this.matchesPlayed += 1;
        this.draws += 1;
        this.points += 1;
    }

    public void addLoss() {
        this.matchesPlayed += 1;
        this.losses += 1;
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
        topGoalscorers.sort((o1, o2) -> o2.getGoals().compareTo(o1.getGoals()));
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

    public void resetStats() {
        this.matchesPlayed = 0;
        this.wins = 0;
        this.draws = 0;
        this.losses = 0;
        this.points = 0;
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
}
