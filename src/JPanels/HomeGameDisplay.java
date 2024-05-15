package JPanels;

import Objects.Team;

import javax.swing.*;
import java.awt.*;

public class HomeGameDisplay extends JPanel {
    public HomeGameDisplay(Team homeTeam, Team awayTeam) {
        this.removeAll();
        this.setPreferredSize(new Dimension(1000,350));
        this.setBackground(Color.GREEN);

        System.out.println(homeTeam.getTeamName() + " vs " + awayTeam.getTeamName());
    }
}
