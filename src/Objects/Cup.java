package Objects;

import data.Data;

import java.util.ArrayList;
public class Cup {
    private final String name;
    private ArrayList<Team> teams;
    private Team champion;

    public Cup(String name, int numberOfTeams) {
        this.name = name;
        this.teams = new ArrayList<>(numberOfTeams);

        this.champion = null;
    }

    public String getName() {
        return name;
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

    public ArrayList<Player> getPlayersByGoalsScored(){
        ArrayList<Player> players = getAllPlayers();
        players.sort((player1, player2) -> Integer.compare(player2.getCupGoals(), player1.getCupGoals()));
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

    public void setChampion(Team cupWinner){
        this.champion = cupWinner;
    }

    public Team getChampion(){
        if (this.champion != null){
            return champion;
        } else {
            ArrayList<Team> possibleChampions = new ArrayList<>();
            for (Team team : teams){
                if (team.isAdvancingToNextRound()){
                    possibleChampions.add(team);
                }
            }

            int randomChampionIndex = (int) (Math.random() * possibleChampions.size());

            return possibleChampions.get(randomChampionIndex);
        }
    }

    //use this function at the end of the season to update the teams for next year's champions league.
    public void updateChampionsLeagueTeams(){
        this.teams = new ArrayList<>();

        for (int i=0; i<5; i++){
            teams.add(Data.england.getLeagueByTier(1).getStandings().get(i));
        }
        for (int i=0; i<4; i++){
            teams.add(Data.spain.getLeagueByTier(1).getStandings().get(i));
            teams.add(Data.italy.getLeagueByTier(1).getStandings().get(i));
            teams.add(Data.germany.getLeagueByTier(1).getStandings().get(i));
            teams.add(Data.france.getLeagueByTier(1).getStandings().get(i));
        }
        for (int i=0; i<2; i++){
            teams.add(Data.scotland.getLeagueByTier(1).getStandings().get(i));
            teams.add(Data.portugal.getLeagueByTier(1).getStandings().get(i));
            teams.add(Data.netherlands.getLeagueByTier(1).getStandings().get(i));
            teams.add(Data.turkey.getLeagueByTier(1).getStandings().get(i));
        }
        teams.add(Data.belgium.getLeagueByTier(1).getStandings().get(0));
        teams.add(Data.austria.getLeagueByTier(1).getStandings().get(0));
        teams.add(Data.greece.getLeagueByTier(1).getStandings().get(0));
    }


}
