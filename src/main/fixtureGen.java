package main;

import Objects.Team;
import data.Data;
import events.Game;

import java.util.ArrayList;
import java.util.List;

public class fixtureGen {

    /**
     * Generates a double round-robin fixture schedule for a league.
     *
     * @param teams The teams in the league.
     *
     * @return 2d arrayList of games representing the fixture schedule,
     * with each inner arrayList representing a round of fixtures.
     */
    public static ArrayList<ArrayList<Game>> generateLeagueFixtureSchedule(List<Team> teams) {
        int numberOfTeams = teams.size();

        ArrayList<ArrayList<Game>> schedule = new ArrayList<>();
        int totalRounds = (numberOfTeams - 1) * 2; //38 for a 20 team league
        int matchesPerRound = numberOfTeams / 2; //10 for a 20 team league

        if (numberOfTeams > 20){
            totalRounds /= 2;
        }

        for (int round = 0; round < totalRounds; round++) {
            ArrayList<Game> roundMatches = new ArrayList<>();

            for (int match = 0; match < matchesPerRound; match++) {
                int home = (round + match) % (numberOfTeams - 1);
                int away = (numberOfTeams - 1 - match + round) % (numberOfTeams - 1);

                if (match == 0) {
                    away = numberOfTeams - 1;
                }

                if (round % 2 == 0) {
                    roundMatches.add(new Game(teams.get(home), teams.get(away), Data.listOfDates[round], "League"));
                } else {
                    roundMatches.add(new Game(teams.get(away), teams.get(home), Data.listOfDates[round], "League"));
                }
            }

            schedule.add(roundMatches);
        }

        return schedule;
    }
}

