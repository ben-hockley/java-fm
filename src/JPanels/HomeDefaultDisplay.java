package JPanels;

import javax.swing.*;
import java.awt.*;

public class HomeDefaultDisplay extends JPanel {
    public HomeDefaultDisplay(String teamName, String[] leagueStandings) {
        this.setPreferredSize(new Dimension(1000,350));
        this.setLayout(new BorderLayout());

        //Title of the team
        JLabel northLabel = new JLabel(teamName);
        northLabel.setHorizontalAlignment(SwingConstants.CENTER);
        northLabel.setVerticalAlignment(SwingConstants.CENTER);
        northLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        northLabel.setPreferredSize(new Dimension(1000,50));
        northLabel.setForeground(Color.WHITE);
        northLabel.setBackground(Color.BLUE);
        northLabel.setOpaque(true);
        this.add(northLabel, BorderLayout.NORTH);

        //League Standings
        JLabel westLabel = new JLabel();
        westLabel.setPreferredSize(new Dimension(150, 300));
        westLabel.setBackground(Color.YELLOW);
        westLabel.setForeground(Color.WHITE);
        westLabel.setLayout(new GridLayout(leagueStandings.length + 1, 1));

        JLabel leagueStandingsTitle = new JLabel("League Standings");
        leagueStandingsTitle.setHorizontalAlignment(SwingConstants.CENTER);
        leagueStandingsTitle.setVerticalAlignment(SwingConstants.CENTER);

        westLabel.add(leagueStandingsTitle);
        for (int i = 0; i < leagueStandings.length; i++) {
            JLabel team = new JLabel(leagueStandings[i]);
            team.setHorizontalAlignment(SwingConstants.CENTER);
            team.setVerticalAlignment(SwingConstants.CENTER);
            team.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            westLabel.add(team);
        }

        westLabel.setOpaque(true);
        JScrollPane westScrollPane = new JScrollPane(westLabel);
        westScrollPane.setPreferredSize(new Dimension(200, 300));
        this.add(westScrollPane, BorderLayout.WEST);
    }
}
