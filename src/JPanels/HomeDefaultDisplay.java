package JPanels;

import Objects.Team;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HomeDefaultDisplay extends JPanel {
    public HomeDefaultDisplay(String teamName, ArrayList<Team> leagueStandings) {
        this.setPreferredSize(new Dimension(1000,350));
        this.setLayout(new BorderLayout());
        this.removeAll();

        //Title of the team (NORTH)
        this.add(teamTitle(teamName), BorderLayout.NORTH);
        this.add(leagueTable(leagueStandings), BorderLayout.WEST);

        //Game Options (CENTER)
        JLabel centerLabel = new JLabel();
        centerLabel.setPreferredSize(new Dimension(750,300));
        centerLabel.setBackground(Color.BLUE);
        centerLabel.setLayout(new GridLayout(2, 2));

        this.add(centerLabel, BorderLayout.EAST);
    }

    private JLabel teamTitle(String teamName){
        JLabel teamTitle = new JLabel(teamName);
        return getTitleBanner(teamTitle);
    }

    static JLabel getTitleBanner(JLabel teamTitle) {
        teamTitle.setHorizontalAlignment(SwingConstants.CENTER);
        teamTitle.setVerticalAlignment(SwingConstants.CENTER);
        teamTitle.setFont(new Font("Arial", Font.PLAIN, 20));
        teamTitle.setPreferredSize(new Dimension(1000,50));
        teamTitle.setForeground(Color.WHITE);
        teamTitle.setBackground(Color.BLUE);
        teamTitle.setOpaque(true);
        return teamTitle;
    }


    //League Standings (WEST)
    static JScrollPane leagueTable(ArrayList<Team> leagueStandings){
        JLabel westLabel = new JLabel();
        westLabel.setPreferredSize(new Dimension(350, 300));
        westLabel.setBackground(Color.YELLOW);
        westLabel.setForeground(Color.WHITE);

        westLabel.setLayout(new GridLayout(1, 2));

        JLabel teamNames = new JLabel();
        JLabel teamStats = new JLabel();

        teamNames.setLayout(new GridLayout(leagueStandings.size() + 1, 1));

        JLabel tableTitle = new JLabel("League Standings");
        tableTitle.setHorizontalAlignment(SwingConstants.CENTER);
        tableTitle.setVerticalAlignment(SwingConstants.CENTER);
        tableTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        tableTitle.setOpaque(true);
        tableTitle.setBackground(Color.BLUE);
        tableTitle.setForeground(Color.WHITE);
        teamNames.add(tableTitle);

        int tablePosition = 1;
        for (Team leagueStanding : leagueStandings) {
            JLabel team = new JLabel(tablePosition + "." + leagueStanding.getShortName());
            team.setHorizontalAlignment(SwingConstants.CENTER);
            team.setVerticalAlignment(SwingConstants.CENTER);
            team.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            if (tablePosition <= 4) {
                team.setBackground(Color.BLUE);
            } else if (tablePosition <= 6) {
                team.setBackground(Color.GREEN);
            } else if (tablePosition <= 17) {
                team.setBackground(Color.ORANGE);
            } else {
                team.setBackground(Color.RED);
            }
            team.setOpaque(true);
            team.setForeground(Color.WHITE);
            teamNames.add(team);
            tablePosition++;
        }

        teamStats.setLayout(new GridLayout(leagueStandings.size() + 1, 5));

        teamStats.add(new JLabel("MP"));
        teamStats.add(new JLabel("W"));
        teamStats.add(new JLabel("D"));
        teamStats.add(new JLabel("L"));
        teamStats.add(new JLabel("PTS"));

        for (Team leagueStanding : leagueStandings) {
            teamStats.add(new JLabel(String.valueOf(leagueStanding.getMatchesPlayed())));
            teamStats.add(new JLabel(String.valueOf(leagueStanding.getWins())));
            teamStats.add(new JLabel(String.valueOf(leagueStanding.getDraws())));
            teamStats.add(new JLabel(String.valueOf(leagueStanding.getLosses())));
            teamStats.add(new JLabel(String.valueOf(leagueStanding.getPoints())));
        }

        westLabel.add(teamNames);
        westLabel.add(teamStats);

        westLabel.setOpaque(true);
        JScrollPane westScrollPane = new JScrollPane(westLabel);
        westScrollPane.setPreferredSize(new Dimension(400, 300));
        westScrollPane.setFocusable(false);
        return westScrollPane;
    }
}
