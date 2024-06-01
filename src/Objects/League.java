package Objects;

import events.Game;
import main.fixtureGen;

import java.util.ArrayList;

public class League {
    private final Integer tier;

    private final ArrayList<Team> teams;
    private final String name;

    public League(String leagueName, String leagueLogo, Integer tier, Nation nation) {
        this.tier = tier;
        this.name = leagueName;

        this.teams = new ArrayList<>(); // Initialize an empty list of teams.

        try {
            //search nation make sure league of same tier doesn't already exist.
            for (League league : nation.getAllLeagues()) {
                if (league.getTier().equals(tier)) {
                    throw new IllegalArgumentException();
                }
            }
            // If not, add the league to the nation.
            nation.addLeague(this);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: League of tier " + tier + " already exists in " + nation.getNationName());
        }
    }

    //could be used for relegation/promotion
    public void addTeam(Team team) { teams.add(team); }

    public ArrayList<Team> getAllTeams() {
        return teams;
    }

    public Integer getTier() {
        return tier;
    }

    public Team getTeamByName(String teamName) {
        for (Team team : teams) {
            if (team.getTeamName().equals(teamName)) {
                return team;
            }
        }
        return null;
    }

    public ArrayList<Team> getStandings() {
        ArrayList<Team> standings = getAllTeams();
        standings.sort((team1, team2) -> team2.getPoints().compareTo(team1.getPoints()));

        return standings;
    }

    public String getName() {
        return name;
    }

    public ArrayList<ArrayList<Game>> generateFixtures() {
        ArrayList<ArrayList<Game>> fixtures = fixtureGen.generateFixtureSchedule(teams);

        for (ArrayList<Game> week : fixtures) {
            for (Game game : week){
                //System.out.println(game.getHomeTeam().getTeamName() + " vs " + game.getAwayTeam().getTeamName() + " on " + game.getDayOfMonth() + "/" + game.getMonth());

                game.getHomeTeam().addFixture(game);
                game.getAwayTeam().addFixture(game);
            }
        }
        return fixtures;
    }

    public ArrayList<Player> getAllPlayers(){
        ArrayList<Player> allPlayers = new ArrayList<>();
        for (Team team : teams) {
            allPlayers.addAll(team.getAllPlayers());
        }
        return allPlayers;
    }
    public ArrayList<Player> getTopGoalscorers(){
        ArrayList<Player> topGoalscorers = getAllPlayers();
        topGoalscorers.sort((o1, o2) -> o2.getGoals().compareTo(o1.getGoals()));
        return topGoalscorers;
    }
    public Player getPlayerByName(String playerName){
        for (Player player : getAllPlayers()) {
            if (player.getPlayerName().equals(playerName)){
                return player;
            }
        }
        return null;
    }
    public Team getRandomTeam(){
        return teams.get((int) (Math.random() * teams.size()));
    }

    public void removeTeam(Team team){
        this.teams.remove(team);
    }
}
