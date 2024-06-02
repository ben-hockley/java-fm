package main;

import Objects.Team;
import data.Data;
import events.Game;

import java.util.ArrayList;
import java.util.Collections;
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
    public static ArrayList<ArrayList<Game>> generateLeagueFixtureSchedule(List<Team> teams, Integer[][] dates, String gameType) {
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
                    roundMatches.add(new Game(teams.get(home), teams.get(away), dates[round], gameType));
                } else {
                    roundMatches.add(new Game(teams.get(away), teams.get(home), dates[round], gameType));
                }
            }

            schedule.add(roundMatches);
        }

        return schedule;
    }


    public static ArrayList<ArrayList<Game>> generateChampionsLeagueFixtureSchedule(){
        ArrayList<Team> teams = Data.world.getCupByName("UEFA Champions League").getTeams();
        Collections.shuffle(teams);

        ArrayList<ArrayList<Team>> groups = new ArrayList<>();

        ArrayList<Team> group = new ArrayList<>();
        for (int i = 0; i < teams.size(); i++) {
            group.add(teams.get(i));
            if (group.size() == 4) {
                groups.add(group);
                group = new ArrayList<>();
            }
        }

        ArrayList<ArrayList<Game>> schedule = new ArrayList<>();

        ArrayList<Game> week1 = new ArrayList<>();
        ArrayList<Game> week2 = new ArrayList<>();
        ArrayList<Game> week3 = new ArrayList<>();
        ArrayList<Game> week4 = new ArrayList<>();
        ArrayList<Game> week5 = new ArrayList<>();
        ArrayList<Game> week6 = new ArrayList<>();

        for (ArrayList<Team> groupNo : groups){
            week1.addAll(generateLeagueFixtureSchedule(groupNo, Data.listOfCupDates, "Cup").get(0));
            week2.addAll(generateLeagueFixtureSchedule(groupNo, Data.listOfCupDates, "Cup").get(1));
            week3.addAll(generateLeagueFixtureSchedule(groupNo, Data.listOfCupDates, "Cup").get(2));
            week4.addAll(generateLeagueFixtureSchedule(groupNo, Data.listOfCupDates, "Cup").get(3));
            week5.addAll(generateLeagueFixtureSchedule(groupNo, Data.listOfCupDates, "Cup").get(4));
            week6.addAll(generateLeagueFixtureSchedule(groupNo, Data.listOfCupDates, "Cup").get(5));
        }

        schedule.add(week1);
        schedule.add(week2);
        schedule.add(week3);
        schedule.add(week4);
        schedule.add(week5);
        schedule.add(week6);

        return schedule;
    }
}

