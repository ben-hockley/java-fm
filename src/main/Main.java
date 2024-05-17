package main;

import data.Data;
import JFrames.UI;
import Objects.Team;

import javax.swing.JFrame;

public class Main {
    /**
     *
     * @param args --
     *             Main class, running this starts the application.
     */
    public static void main(final String[] args) {

        //have multiple leagues in separate functions ,
        //so the player can choose which leagues to load.
        Data.setPremierLeague();


        //User Interface
        Team userTeam = Data.england.getLeagueByTier(1).getTeamByName(
                "Arsenal");

        JFrame userUI = new UI(userTeam);
        userUI.setTitle("Football Manager 2024");
    }
}

//this software was created by Benjamin Hockley, 2024.
