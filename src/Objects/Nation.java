package Objects;

import java.util.ArrayList;

import static Objects.Team.getPlayers;

public class Nation {
    private final String nationName;
    private final String nationFlag;
    private Integer worldRanking;
    private final String Continent;

    private ArrayList<Player> players;
    private final ArrayList<League> leagues;

    public Nation(String nationName, String nationFlag, Integer worldRanking, String Continent) {
        this.nationName = nationName;
        this.nationFlag = nationFlag;
        this.worldRanking = worldRanking;
        this.Continent = Continent;

        this.players = new ArrayList<>();
        this.leagues = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void addLeague(League league) {
        this.leagues.add(league);
    }

    public String getNationName() {
        return nationName;
    }

    public ArrayList<Player> getPlayersByPosition(String position, Integer numberOfPlayers){
        return getPlayers(position, numberOfPlayers, players);
    }

    public Player[] bestStartingEleven(){
        Player[] startingEleven = new Player[11];

        //National team's default formation set to 4-3-3, can change this later.

        Integer numberGoalkeepers = 1;
        Integer numberDefenders = 4;
        Integer numberMidfielders = 3;
        Integer numberForwards = 3;

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

    public ArrayList<League> getAllLeagues() {
        return leagues;
    }


    //searches for a league by tier.
    // e.g: England.getLeagueByTier(1) would return the premier league.
    public League getLeagueByTier(Integer tier) {
        for (League league : leagues) {
            if (league.getTier().equals(tier)) {
                return league;
            }
        }
        return null;
    }
}
