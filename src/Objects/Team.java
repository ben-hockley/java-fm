package Objects;

import events.Game;

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
    private Integer[] formation; //e.g. 4-4-2 would be [4,4,2], should always be 3 numbers.

    private Player[] startingEleven;

    private Player[] substitutes;


    public ArrayList<Player> players;

    public Team(String teamName,String shortName ,String teamLogo, League league, Integer[] formation) {
        this.teamName = teamName;
        this.shortName = shortName;
        this.teamLogo = teamLogo;

        this.league = league;
        this.formation = formation;

        this.players = new ArrayList<>();

        this.matchesPlayed = 0;
        this.wins = 0;
        this.draws = 0;
        this.losses = 0;
        this.points = 0;

        league.addTeam(this); //after creating a team, add them to their league.
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


    //searches a player by index.
    public Player getPlayerByIndex(Integer index){
        return players.get(index);
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

        Player[] bestSubsList = new Player[7];

        for (int i=0; i<7; i++){
            bestSubsList[i] = bestSubs().get(i);
        }

        substitutes = bestSubsList;
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
        ArrayList<Player> subs = new ArrayList<>();

        for (Player player : players) {
            subs.add(player);
        }

        for (Player player : getStartingEleven()) {
            subs.remove(player);
        }
        while (subs.size() > 7) {
            Integer lowestRating = 100;
            Player lowestRatedPlayer = null;
            for (Player player : subs) {
                if (player.getRating() < lowestRating) {
                    lowestRating = player.getRating();
                    lowestRatedPlayer = player;
                }
            }
            subs.remove(lowestRatedPlayer);
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
    public void removePoints(Integer points) {
        this.points -= points;
    }

    public ArrayList<Player> getAllPlayers() {
        return players;
    }

    //this function is used to progress the date by 1 week, used for generating dates for fixtures.
    public Integer[] getDateOneWeekLater(Integer[] date) {

        int monthLength = 31;
        if (date[1] == 2) {
            monthLength = 28;
        } else if (date[1] == 4 || date[1] == 6 || date[1] == 9 || date[1] == 11) {
            monthLength = 30;
        }

        date[0] += 7;
        if (date[0] > monthLength) {
            date[0] -= monthLength;
            date[1] += 1;
        }
        if (date[1] > 12) {
            date[1] = 1;
        }
        return date;
    }

    public ArrayList<Game> generateFixtures() {
        //create new arraylist to store fixtures.
        ArrayList<Game> fixtures = new ArrayList<>();

        //Get all other teams in the league.
        ArrayList<Team> teams = league.getAllTeams();
        teams.remove(this);

        //starting date is based on the PL's starting date (Saturday 6th August)
        Integer[] date = new Integer[]{6, 8};

        Integer[][] listOfDates = {
                {6, 8},
                {13, 8},
                {20, 8},
                {27, 8},
                {3, 9},
                {10, 9},
                {17, 9},
                {24, 9},
                {1, 10},
                {8, 10},
                {15, 10},
                {22, 10},
                {29, 10},
                {5, 11},
                {12, 11},
                {19, 11},
                {26, 11},
                {3, 12},
                {10, 12},
                {17, 12},
                {24, 12},
                {31, 12},
                {7, 1},
                {14, 1},
                {21, 1},
                {28, 1},
                {4, 2},
                {11, 2},
                {18, 2},
                {25, 2},
                {4, 3},
                {11, 3},
                {18, 3},
                {25, 3},
                {1, 4},
                {8, 4},
                {15, 4},
                {22, 4},
                {29, 4},
                {6, 5},
                {13, 5},
                {20, 5},
                {27, 5}
        };
        //has 5 extra dates, could apply champions league/europa league here.
        //Or could use these dates for cup games.
        //Or could use these dates for international breaks.


        //Games alternate between home and away fixtures, set the first fixture to home.
        boolean home = true;
        Game game;
        int n = 0;
        //loop through all teams in the league (first round of fixtures)
        for (Team team : teams) {
            //create a new game object with the current team and the team in the loop.

            if (home){
                game = new Game(this, team, listOfDates[n]);
            } else {
                game = new Game(team, this, listOfDates[n]);
            }

            //add the game to the fixtures list.
            fixtures.add(game);
            //if the game was a home game, set the next game to be an away game.
            if (home) { home = false; } else { home = true; }

            n++;
        }

        //loop through all teams in the league (second round of fixtures)
        for (Team team : teams) {
            //create a new game object with the current team and the team in the loop.

            if (home){
                game = new Game(this, team, listOfDates[n]);
            } else {
                game = new Game(team, this, listOfDates[n]);
            }
            //add the game to the fixtures list.
            fixtures.add(game);
            //if the game was a home game, set the next game to be an away game.
            if (home) { home = false; } else { home = true; }
            n++;
        }

        //add userTeam back to the array so it is included in the table.
        teams.add(this);
        return fixtures;
    }

    public Integer getRating() {
        Player[] startingEleven = getStartingEleven();
        Integer teamRating = -400;
        for (Player player : startingEleven) {
            teamRating += player.getRating();
            System.out.println(player.getPlayerName() + " - " + player.getRating());
        }
        return teamRating;
    }

    public Player[] getStartingEleven() {
        return startingEleven;
    }

    public Player[] getSubstitutes() {
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
        for (int i = 0; i < substitutes.length; i++) {
            if (substitutes[i] == playerIn) {
                substitutes[i] = playerOut;
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
}
