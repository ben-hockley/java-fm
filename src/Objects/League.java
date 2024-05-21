package Objects;

import data.Data;
import events.Game;
import main.fixtureGen;

import java.util.ArrayList;

public class League {
    private final Integer tier;

    private final ArrayList<Team> teams;

    private ArrayList<ArrayList<Game>> fixtures;

    public League(String leagueName, String leagueLogo, Integer tier, Nation nation) {
        this.tier = tier;

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

    public Integer getNumberOfTeams(){
        return teams.size();
    }

    public ArrayList<ArrayList<Game>> generateFixtures() {
        fixtures = fixtureGen.generateFixtureSchedule(teams);

        int weekNumber = 1;

        for (ArrayList<Game> week : fixtures) {
            for (Game game : week){
                //System.out.println(game.getHomeTeam().getTeamName() + " vs " + game.getAwayTeam().getTeamName() + " on " + game.getDayOfMonth() + "/" + game.getMonth());

                game.getHomeTeam().addFixture(game);
                game.getAwayTeam().addFixture(game);
            }
            weekNumber++;
        }
        return fixtures;
    }
}
