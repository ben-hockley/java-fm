package main;

import JFrames.endOfSeasonSummary;
import Objects.Player;
import Objects.World;
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
        Data.setChampionsLeague();
        Data.setNationsLeague();

        //set default starting 11 and subs for all club teams.
        for (Team team : Data.world.getAllTeams()) {
            team.setDefaultStartingElevenandSubs();
        }

        //set default starting 11 and subs for all international teams.
        for (Team team : Data.international.getLeagueByTier(1).getAllTeams()) {
            team.setDefaultStartingElevenandSubs();
        }
        //User Interface

        //testing international teams
        Team userTeam = Data.world.getTeamByName("Manchester City");

        JFrame userUI = new UI(userTeam);
        userUI.setTitle("Football Manager 2024");
    }
}

//this software was created by Benjamin Hockley, 2024.
