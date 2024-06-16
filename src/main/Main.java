package main;

import data.Data;
import JFrames.menu;
import objects.Team;

public class Main {
    /**
     * Main method, starting point of the application.
     */
    public static void main(final String[] args) {

        //have multiple leagues in separate functions ,
        //so the player can choose which leagues to load.
        Data.setPremierLeague();
        Data.setChampionsLeague();
        Data.setWorldCup();

        //set default starting 11 and subs for all club teams.
        for (Team team : Data.world.getAllTeams()) {
            team.setDefaultStartingElevenandSubs();
        }

        //set default starting 11 and subs for all international teams.
        for (Team team : Data.international.getLeagueByTier(1).getAllTeams()) {
            team.setDefaultStartingElevenandSubs();
        }

        new menu();
    }
}

//this software was created by Benjamin Hockley, 2024.
