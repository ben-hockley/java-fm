package Main;

import Data.Data;
import Events.Event;
import Events.Game;
import JFrames.UI;
import Objects.League;
import Objects.Player;
import Objects.Team;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Data.setPremierLeague(); //have multiple leagues in separate functions so the player can choose which leagues to load.


        //User Interface
        Team userTeam = Data.england.getLeagueByTier(1).getTeamByName("Arsenal");
        JFrame userUI = new UI(userTeam);
    }
}