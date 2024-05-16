import Data.Data;
import JFrames.UI;
import Objects.Player;
import Objects.Team;

import javax.swing.*;
import java.util.ArrayList;

public class Main {
    public static Team userTeam;
    public static void main(String[] args) {
        //Data testing, will be deleted later
        Data.setData();

        Player[] englandPlayers = Data.england.bestStartingEleven();


        for (Player player : englandPlayers) {
            System.out.println(player.getPlayerName() + " " + player.getRating() + " " + player.getTeam().getTeamName());
        }


        //end of data testing

        //User Interface
        userTeam = Data.england.getLeagueByTier(1).getTeamByName("Arsenal");
        JFrame userUI = new UI(userTeam);
    }
}