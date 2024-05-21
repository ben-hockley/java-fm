package main;

import Objects.Team;
import data.Data;
import events.Game;

import java.util.ArrayList;
import java.util.List;

public class fixtureGen {

    public static ArrayList<ArrayList<Game>> generateFixtureSchedule(List<Team> teams) {
        int numberOfTeams = teams.size();

        ArrayList<ArrayList<Game>> schedule = new ArrayList<>();
        int totalRounds = (numberOfTeams - 1) * 2; //38 for a 20 team league
        int matchesPerRound = numberOfTeams / 2; //10 for a 20 team league

        for (int round = 0; round < totalRounds; round++) {
            ArrayList<Game> roundMatches = new ArrayList<>();

            for (int match = 0; match < matchesPerRound; match++) {
                int home = (round + match) % (numberOfTeams - 1);
                int away = (numberOfTeams - 1 - match + round) % (numberOfTeams - 1);

                if (match == 0) {
                    away = numberOfTeams - 1;
                }

                if (round % 2 == 0) {
                    roundMatches.add(new Game(teams.get(home), teams.get(away), Data.listOfDates[round]));
                } else {
                    roundMatches.add(new Game(teams.get(away), teams.get(home), Data.listOfDates[round]));
                }
            }

            schedule.add(roundMatches);
        }

        return schedule;
    }

    public static void generateFixtures() {

        Data.setPremierLeague();

        List<Team> teams = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            teams.add(Data.england.getLeagueByTier(1).getAllTeams().get(i));
        }

        ArrayList<ArrayList<Game>> schedule = generateFixtureSchedule(teams);

        for (int i = 0; i < schedule.size(); i++) {
            System.out.println("Round " + (i + 1) + ":");
            for (Game match : schedule.get(i)) {
                //System.out.println(match);

                System.out.println(match.getHomeTeam().getTeamName() + " vs " + match.getAwayTeam().getTeamName() + " on " + match.getDayOfMonth() + "/" + match.getMonth());
            }
            System.out.println();  // Blank line between rounds
        }
    }
}

