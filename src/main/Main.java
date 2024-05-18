package main;

import data.Data;
import JFrames.UI;
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
            System.out.println(team.getTeamName());

            team.setDefaultStartingElevenandSubs();
        }
        //User Interface
        Team userTeam = Data.england.getLeagueByTier(1).getTeamByName("Arsenal");

        JFrame userUI = new UI(userTeam);
        userUI.setTitle("Football Manager 2024");

        //testing substitution
        userTeam.makeSubstitution(userTeam.getStartingEleven()[0], userTeam.getSubstitutes()[0]);
        System.out.println(userTeam.getStartingEleven()[0].getPlayerName());
        System.out.println(userTeam.getSubstitutes()[0].getPlayerName());
    }
}

//this software was created by Benjamin Hockley, 2024.
