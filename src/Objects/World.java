package Objects;

import java.util.ArrayList;

public class World {
    public ArrayList<League> leagues;

    public World() {
        leagues = new ArrayList<>();
    }

    public void addLeague(League league) {
        leagues.add(league);
    }

    public ArrayList<Player> getAllPlayers() {
        ArrayList<Player> allPlayers = new ArrayList<>();
        for (League league : leagues) {
            allPlayers.addAll(league.getAllPlayers());
        }
        return allPlayers;
    }

    public ArrayList<Player> getPlayersByValue() {
        ArrayList<Player> allPlayers = getAllPlayers();
        allPlayers.sort((p1, p2) -> (int) (p2.getValue() - p1.getValue()));
        return allPlayers;
    }

    public Team getTeamByName(String teamName) {
        for (League league : leagues) {
            Team team = league.getTeamByName(teamName);
            if (team != null) {
                return team;
            }
        }
        return null;
    }

    public Player getPlayerByName(String playerName) {
        for (League league : leagues) {
            Player player = league.getPlayerByName(playerName);
            if (player != null) {
                return player;
            }
        }
        return null;
    }

    public Team getRandomTeam() {
        return leagues.get((int) (Math.random() * leagues.size())).getRandomTeam();
    }

    public ArrayList<Team> getAllTeams() {
        ArrayList<Team> allTeams = new ArrayList<>();
        for (League league : leagues) {
            allTeams.addAll(league.getAllTeams());
        }
        return allTeams;
    }
}
