package Objects;

import java.util.ArrayList;
import java.util.List;

public class Cup {
    private String name;
    private Integer noOfTeams;
    private ArrayList<Team> teams;

    public Cup(String name, Integer noOfTeams) {
        this.name = name;
        this.noOfTeams = noOfTeams;
        this.teams = new ArrayList<>();
    }

    public void addTeams(List<Team> teams){
        this.teams.addAll(teams);
    }

    public String getName() {
        return name;
    }

    public Integer getNoOfTeams() {
        return teams.size();
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public ArrayList<Player> getAllPlayers(){
        ArrayList<Player> players = new ArrayList<>();
        for (Team team : teams){
            players.addAll(team.getAllPlayers());
        }
        return players;
    }

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

}
