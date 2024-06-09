package main;

import JFrames.endOfSeasonSummary;
import JFrames.endOfSemiFinalsSummary;
import data.Data;
import JFrames.UI;
import Objects.Team;
import javax.swing.JFrame;
import java.util.ArrayList;

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

        Team userTeam = Data.england.getLeagueByTier(1).getTeamByName("Manchester City");

        JFrame userUI = new UI(userTeam);
        userUI.setTitle("Football Manager 2024");
    }
}

//this software was created by Benjamin Hockley, 2024.
