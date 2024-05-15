package Objects;

import java.util.ArrayList;

public class Team {
    //Team details, these will not change.
    private String teamName;
    private String shortName;
    private String teamLogo;

    //Team properties that can change.
    private League league;
    private Integer points;
    private Integer[] formation; //e.g. 4-4-2 would be [4,4,2], should always be 3 numbers.


    public ArrayList<Player> players;

    public Team(String teamName,String shortName ,String teamLogo, League league, Integer[] formation) {
        this.teamName = teamName;
        this.shortName = shortName;
        this.teamLogo = teamLogo;

        this.league = league;
        this.points = 0;
        this.formation = formation;

        this.players = new ArrayList<Player>();

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
        ArrayList<Player> playersInPosition = new ArrayList<Player>();
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

    //gets the best starting 11 for the team.
    //finds the best combination of goalkeepers, defenders, midfielders and forwards based on the formation.
    //sorts players by player rating and puts the highest rated players in the starting 11.
    //this function could be used to pick the starting lineup for an CPU controlled team.
    public Player[] bestStartingEleven(){
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

        Integer playerIndex = 1;
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

    public String getFormationInText() {
        return formation[0] + "-" + formation[1] + "-" + formation[2];
    }

    //gets the best subs for the team.
}
