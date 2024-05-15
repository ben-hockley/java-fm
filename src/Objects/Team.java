package Objects;

import java.util.ArrayList;

public class Team {
    private String teamName;
    private String shortName;
    private String teamLogo;
    private League league;
    private Integer points;

    public ArrayList<Player> players;

    public Team(String teamName,String shortName ,String teamLogo, League league) {
        this.teamName = teamName;
        this.shortName = shortName;

        this.teamLogo = teamLogo;
        this.league = league;
        this.points = 0;

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
    //e.g the CPU could play 4-4-2 and would call getPlayersByPosition("DEF", 4) to get the top 4 defenders.
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
}
