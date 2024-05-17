package JPanels;

import javax.swing.*;
import java.awt.*;

public class HomeDefaultDisplay extends JPanel {
    public HomeDefaultDisplay(String teamName, String[] leagueStandings) {
        this.setPreferredSize(new Dimension(1000,350));
        this.setLayout(new BorderLayout());
        this.removeAll();

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
        for (String leagueStanding : leagueStandings) {
            JLabel team = new JLabel(leagueStanding);
            team.setHorizontalAlignment(SwingConstants.CENTER);
            team.setVerticalAlignment(SwingConstants.CENTER);
            team.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            westLabel.add(team);
        }

        westLabel.setOpaque(true);
        JScrollPane westScrollPane = new JScrollPane(westLabel);
        westScrollPane.setPreferredSize(new Dimension(200, 300));
        westScrollPane.setFocusable(false);
        this.add(westScrollPane, BorderLayout.WEST);

        //Game Options (CENTER)
        JLabel centerLabel = new JLabel();
        centerLabel.setPreferredSize(new Dimension(750,300));
        centerLabel.setBackground(Color.BLUE);
        centerLabel.setLayout(new GridLayout(2, 2));

        /*
        JButton manageTeamButton = new JButton("Manage Team");
        manageTeamButton.setRolloverEnabled(false);

        JButton viewScheduleButton = new JButton("View Schedule");
        viewScheduleButton.setRolloverEnabled(false);


        JButton transferMarketButton = new JButton("Transfer Market");
        transferMarketButton.setRolloverEnabled(false);

        JButton settingsButton = new JButton("Settings");
        settingsButton.setRolloverEnabled(false);



         */
        //setRolloverEnabled is used to disable the button from showing over current home display when hovered over.
/*
        manageTeamButton.addActionListener(e -> {
            JFrame manageTeamFrame = new ManageTeam();
        });

        viewScheduleButton.addActionListener(e -> {
            JFrame viewScheduleFrame = new ViewSchedule();
        });

        transferMarketButton.addActionListener(e -> {
            JFrame transferMarketFrame = new TransferMarket();
        });

        settingsButton.addActionListener(e -> {
            JFrame settingsFrame = new Settings();
        });


        centerLabel.add(manageTeamButton);
        centerLabel.add(viewScheduleButton);
        centerLabel.add(transferMarketButton);
        centerLabel.add(settingsButton);

 */

        this.add(centerLabel, BorderLayout.EAST);
    }
}
