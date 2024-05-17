package JPanels;

import Objects.Player;
import Objects.Team;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

//import static JPanels.HomeDefaultDisplay.manageTeamButton;

public class HomeGameDisplay extends JPanel {
    public HomeGameDisplay(Team homeTeam, Team awayTeam, ArrayList<Team> leagueStandings) {
        this.removeAll();
        this.setPreferredSize(new Dimension(1000,350));
        this.setBackground(Color.GREEN);
        this.setLayout(new BorderLayout());

        System.out.println(homeTeam.getTeamName() + " vs " + awayTeam.getTeamName());

        Team opponent = null;

        if (homeTeam.getTeamName().equals("Arsenal")) {
            opponent = awayTeam;
        } else if (awayTeam.getTeamName().equals("Arsenal")) {
            opponent = homeTeam;
        }

        //Opponents Lineup (WEST)
        JPanel opponentsLineup = new JPanel(new GridLayout(13,1));
        opponentsLineup.setPreferredSize(new Dimension(300, 300));
        opponentsLineup.setBackground(Color.WHITE);
        opponentsLineup.setForeground(Color.BLACK);
        opponentsLineup.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        opponentsLineup.add(new JLabel("Opponent: " + opponent.getTeamName()));
        opponentsLineup.add(new JLabel("Formation: " + opponent.getFormationInText()));

        Player[] opponentStarting11 = opponent.bestStartingEleven();

        for (Player player : opponentStarting11) {
            opponentsLineup.add(new JLabel(player.getPlayerName() + " - " + player.getPosition() + " - " + player.getRating()));
        }


        opponentsLineup.setOpaque(true);
        this.add(opponentsLineup,BorderLayout.EAST);

        //Game Title (NORTH)

        JLabel northLabel = getNorthLabel(homeTeam, awayTeam);
        this.add(northLabel, BorderLayout.NORTH);

        //League Standings (WEST) -- copied from HomeDefaultDisplay, clean up this code later.
        JLabel westLabel = new JLabel();
        westLabel.setPreferredSize(new Dimension(150, 300));
        westLabel.setBackground(Color.YELLOW);
        westLabel.setForeground(Color.WHITE);
        westLabel.setLayout(new GridLayout(leagueStandings.size() + 1, 1));

        JLabel leagueStandingsTitle = new JLabel("League Standings");
        leagueStandingsTitle.setHorizontalAlignment(SwingConstants.CENTER);
        leagueStandingsTitle.setVerticalAlignment(SwingConstants.CENTER);

        westLabel.add(leagueStandingsTitle);
        for (Team leagueStanding : leagueStandings) {
            JLabel team = new JLabel(leagueStanding.getTeamName() + " - " + leagueStanding.getPoints());
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
        JPanel centerPanel = new JPanel(new GridLayout(2, 1));
        centerPanel.setPreferredSize(new Dimension(750,300));
        centerPanel.setBackground(Color.BLUE);
        /*
        JButton bigManageTeamButton = new JButton("Manage Team");
        bigManageTeamButton.setRolloverEnabled(false);
        bigManageTeamButton.addActionListener(e -> {
            JFrame manageTeamFrame = new JFrame("Manage Team");
            manageTeamFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            manageTeamFrame.add(new ManageTeam());
            manageTeamFrame.pack();
            manageTeamFrame.setVisible(true);
        });

        JButton kickOffButton = new JButton("Kick Off");
        kickOffButton.setRolloverEnabled(false);
        kickOffButton.addActionListener(e -> {
            System.out.println("Kick Off");
        });

        centerPanel.add(bigManageTeamButton);
        centerPanel.add(kickOffButton);
         */
        this.add(centerPanel, BorderLayout.CENTER);
    }

    private static JLabel getNorthLabel(Team homeTeam, Team awayTeam) {
        JLabel northLabel = new JLabel(homeTeam.getTeamName() + " vs " + awayTeam.getTeamName());
        northLabel.setHorizontalAlignment(SwingConstants.CENTER);
        northLabel.setVerticalAlignment(SwingConstants.CENTER);
        northLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        northLabel.setPreferredSize(new Dimension(1000,50));
        northLabel.setForeground(Color.WHITE);
        northLabel.setBackground(Color.BLUE);
        northLabel.setOpaque(true);
        return northLabel;
    }
}
