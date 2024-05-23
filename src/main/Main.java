package main;

import data.Data;
import jframes.UI;
import Objects.Team;

import javax.swing.JFrame;

public class Main {
    /**
     * Main method, starting point of the application.
     */
    public static void main(final String[] args) {

        //have multiple leagues in separate functions ,
        //so the player can choose which leagues to load.
        Data.setPremierLeague();

        for (Team team : Data.england.getLeagueByTier(1).getAllTeams()) {
            team.setDefaultStartingElevenandSubs();
        }
        //User Interface
        Team userTeam = Data.england.getLeagueByTier(1).getTeamByName("Manchester United");

        JFrame userUI = new UI(userTeam);
        userUI.setTitle("Football Manager 2024");
    }
}

//this software was created by Benjamin Hockley, 2024.
