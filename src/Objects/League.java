package Objects;

import Data.Data;

import java.util.ArrayList;

public class League {
    private final String leagueName;
    private final String leagueLogo;
    private final Integer tier;
    private final Nation nation;

    private ArrayList<Team> teams;

    public League(String leagueName, String leagueLogo, Integer tier, Nation nation) {
        this.leagueName = leagueName;
        this.leagueLogo = leagueLogo;
        this.tier = tier;
        this.nation = nation;

        this.teams = new ArrayList<Team>(); // Initialize an empty list of teams.

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
    public void addTeam(Team team) {
        this.teams.add(team);
    }

    public ArrayList<Team> getAllTeams() {
        return teams;
    }

    public Integer getTier() {
        return tier;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public String getLeagueLogo() {
        return leagueLogo;
    }

    public Nation getNation() {
        return nation;
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


    //could be used for relegation/promotion
    public void removeTeam(Team team) {
        teams.remove(team);
    }
}
