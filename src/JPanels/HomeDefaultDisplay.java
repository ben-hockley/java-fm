package JPanels;

import javax.swing.*;
import java.awt.*;

public class HomeDefaultDisplay extends JPanel {
    public HomeDefaultDisplay(String teamName, String[] leagueStandings) {
        this.setPreferredSize(new Dimension(1000,350));
        this.setLayout(new BorderLayout());

        //Title of the team (NORTH)
        JLabel northLabel = new JLabel(teamName);
        northLabel.setHorizontalAlignment(SwingConstants.CENTER);
        northLabel.setVerticalAlignment(SwingConstants.CENTER);
        northLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        northLabel.setPreferredSize(new Dimension(1000,50));
        northLabel.setForeground(Color.WHITE);
        northLabel.setBackground(Color.BLUE);
        northLabel.setOpaque(true);
        this.add(northLabel, BorderLayout.NORTH);

        //League Standings (WEST)
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

        //Game Options (CENTER)
        JPanel centerPanel = new JPanel();
        centerPanel.setPreferredSize(new Dimension(800,300));
        centerPanel.setBackground(Color.BLUE);
        centerPanel.setLayout(new GridLayout(2, 2));

        Button manageTeamButton = new Button("Manage Team");
        Button viewScheduleButton = new Button("View Schedule");
        Button transferMarketButton = new Button("Transfer Market");
        Button settingsButton = new Button("Settings");

        manageTeamButton.addActionListener(e -> {
            JFrame manageTeamFrame = new JFrame("Manage Team");
            manageTeamFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            manageTeamFrame.setSize(500, 500);
            manageTeamFrame.setVisible(true);
        });

        viewScheduleButton.addActionListener(e -> {
            JFrame viewScheduleFrame = new JFrame("View Schedule");
            viewScheduleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            viewScheduleFrame.setSize(500, 500);
            viewScheduleFrame.setVisible(true);
        });

        transferMarketButton.addActionListener(e -> {
            JFrame transferMarketFrame = new JFrame("Transfer Market");
            transferMarketFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            transferMarketFrame.setSize(500, 500);
            transferMarketFrame.setVisible(true);
        });

        settingsButton.addActionListener(e -> {
            JFrame settingsFrame = new JFrame("Settings");
            settingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            settingsFrame.setSize(500, 500);
            settingsFrame.setVisible(true);
        });

        centerPanel.add(manageTeamButton);
        centerPanel.add(viewScheduleButton);
        centerPanel.add(transferMarketButton);
        centerPanel.add(settingsButton);

        centerPanel.setOpaque(true);

        this.add(centerPanel, BorderLayout.CENTER);
    }
}
