package Main;

import Data.Data;
import Events.Game;
import JFrames.UI;
import Objects.Player;
import Objects.Team;

import javax.swing.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Data.setPremierLeague(); //have multiple leagues in separate functions so the player can choose which leagues to load.


        //User Interface
        Team userTeam = Data.england.getLeagueByTier(1).getTeamByName("Arsenal");
        JFrame userUI = new UI(userTeam);

        ArrayList<Game> fixtures = userTeam.generateFixtures();

        for (Game game : fixtures) {
            System.out.println(game.homeTeam.getTeamName() + " vs " + game.awayTeam.getTeamName() + " on " + game.getDate()[0] + "/" + game.getDate()[1] + "\n");
        }




    }
}

//this software was created by Benjamin Hockley, 2024.