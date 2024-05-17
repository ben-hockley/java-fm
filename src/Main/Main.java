package Main;

import Data.Data;
import JFrames.UI;
import Objects.Team;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        Data.setPremierLeague(); //have multiple leagues in separate functions so the player can choose which leagues to load.


        //User Interface
        Team userTeam = Data.england.getLeagueByTier(1).getTeamByName("Arsenal");

        JFrame userUI = new UI(userTeam);
        userUI.setTitle("Football Manager 2024");
    }
}

//this software was created by Benjamin Hockley, 2024.