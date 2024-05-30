package JPanels;

import JFrames.manageTeam;
import JFrames.transferMarket;
import Objects.Team;
import Objects.dateTime;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class HomeDefaultDisplay extends JPanel {
    public HomeDefaultDisplay(ArrayList<Team> leagueStandings, Team userTeam, dateTime clock) {
        this.setPreferredSize(new Dimension(1000,350));
        this.setLayout(new BorderLayout());
        this.removeAll();

        //Title of the team (NORTH)
        this.add(teamTitle(userTeam), BorderLayout.NORTH);
        this.add(leagueTable(leagueStandings), BorderLayout.WEST);

        //Game Options (CENTER)
        JLabel centerLabel = new JLabel();
        centerLabel.setPreferredSize(new Dimension(650,600));
        centerLabel.setBackground(Color.BLUE);
        centerLabel.setLayout(new GridLayout(2, 2));

        JLabel teamTopGoalscorers = getTeamTopGoalscorers(userTeam);

        JLabel leagueTopGoalscorers = getLeagueTopGoalscorers(userTeam);

        JLabel BlueLabel = new JLabel();
        BlueLabel.setLayout(new GridLayout(5, 1));
        BlueLabel.setBackground(Color.BLUE);
        BlueLabel.setForeground(Color.WHITE);

        JLabel tranferMarketPrompt = new JLabel("Press 'T' to open Transfer Market");
        tranferMarketPrompt.setHorizontalAlignment(SwingConstants.CENTER);
        tranferMarketPrompt.setVerticalAlignment(SwingConstants.CENTER);
        tranferMarketPrompt.setFont(new Font("Arial", Font.PLAIN, 20));
        tranferMarketPrompt.setForeground(Color.WHITE);
        BlueLabel.add(tranferMarketPrompt);

        JLabel manageTeamPrompt = new JLabel("Press 'M' to manage your team");
        manageTeamPrompt.setHorizontalAlignment(SwingConstants.CENTER);
        manageTeamPrompt.setVerticalAlignment(SwingConstants.CENTER);
        manageTeamPrompt.setFont(new Font("Arial", Font.PLAIN, 20));
        manageTeamPrompt.setForeground(Color.WHITE);
        BlueLabel.add(manageTeamPrompt);
        BlueLabel.setFocusable(true);

        BlueLabel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_T) {

                    if (clock.getMonthNumber().equals(1) || clock.getMonthNumber().equals(6) || clock.getMonthNumber().equals(7) || clock.getMonthNumber().equals(8)){
                        JFrame transferMarket = new transferMarket(userTeam);
                    } else {
                        JOptionPane.showMessageDialog(null, "Transfer window shut, you can buy and sell players in January, June, July and August.");
                    }
                }
            }
        });
        BlueLabel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_M) {
                    JFrame manageTeam = new manageTeam(userTeam);
                }
            }
        });

        BlueLabel.setOpaque(true);

        JLabel RedLabel = new JLabel();
        RedLabel.setBackground(Color.RED);
        RedLabel.setOpaque(true);


        centerLabel.add(teamTopGoalscorers);
        centerLabel.add(leagueTopGoalscorers);
        centerLabel.add(BlueLabel);
        centerLabel.add(RedLabel);

        this.add(centerLabel, BorderLayout.CENTER);
    }

    private JLabel teamTitle(Team userTeam){
        JLabel teamTitle = new JLabel(userTeam.getTeamName());
        teamTitle.setBackground(userTeam.getTeamColor());

        return teamTitle;
    }


    //method to generate a title banner for the North of the home display.
    //static so it can be used in homeGameDisplay aswell.
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
    //static variable , so it can be shown in the homeGameDisplay aswell.
    static JScrollPane leagueTable(ArrayList<Team> leagueStandings){
        JLabel westLabel = new JLabel();
        westLabel.setPreferredSize(new Dimension(250, 300));
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
        westScrollPane.setPreferredSize(new Dimension(300, 300));
        westScrollPane.setFocusable(false);
        return westScrollPane;
    }

    private JLabel getTeamTopGoalscorers(Team userTeam){
        JLabel mainLable = new JLabel();
        mainLable.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Top Goalscorers : " + userTeam.getTeamName());
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        titleLabel.setPreferredSize(new Dimension(1000,25));

        JLabel topGoalScorersLabel = new JLabel();
        topGoalScorersLabel.setLayout(new GridLayout(1, 2));

        JLabel nameLabel = new JLabel();
        nameLabel.setLayout(new GridLayout(6, 1));
        nameLabel.setBackground(userTeam.getTeamColor());
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setOpaque(true);

        JLabel name = new JLabel("Name");
        name.setForeground(Color.WHITE);
        nameLabel.add(name);
        for (int i=0; i<5; i++) {
            JLabel player = new JLabel(userTeam.getTopGoalscorers().get(i).getPlayerName());
            player.setHorizontalAlignment(SwingConstants.CENTER);
            player.setVerticalAlignment(SwingConstants.CENTER);
            player.setBorder(BorderFactory.createLineBorder(Color.WHITE));
            player.setFont(new Font("Arial", Font.PLAIN, 10));
            player.setForeground(Color.WHITE);
            nameLabel.add(player);
        }


        JLabel teamStatsLabel = new JLabel();
        teamStatsLabel.setLayout(new GridLayout(6, 3));
        teamStatsLabel.setBackground(userTeam.getTeamColor());
        teamStatsLabel.setForeground(Color.WHITE);
        teamStatsLabel.setOpaque(true);

        JLabel pos = new JLabel("Pos");
        pos.setForeground(Color.WHITE);
        teamStatsLabel.add(pos);

        JLabel apps = new JLabel("Apps");
        apps.setForeground(Color.WHITE);
        teamStatsLabel.add(apps);

        JLabel goalsHeading = new JLabel("Goals");
        goalsHeading.setForeground(Color.WHITE);
        teamStatsLabel.add(goalsHeading);

        for (int i=0; i<5; i++) {
            JLabel position = new JLabel(userTeam.getTopGoalscorers().get(i).getPosition());
            position.setHorizontalAlignment(SwingConstants.CENTER);
            position.setVerticalAlignment(SwingConstants.CENTER);
            position.setBorder(BorderFactory.createLineBorder(Color.WHITE));
            position.setFont(new Font("Arial", Font.PLAIN, 10));
            position.setForeground(Color.WHITE);

            JLabel appearances = new JLabel(String.valueOf(userTeam.getTopGoalscorers().get(i).getAppearances()));
            appearances.setHorizontalAlignment(SwingConstants.CENTER);
            appearances.setVerticalAlignment(SwingConstants.CENTER);
            appearances.setBorder(BorderFactory.createLineBorder(Color.WHITE));
            appearances.setFont(new Font("Arial", Font.PLAIN, 10));
            appearances.setForeground(Color.WHITE);

            JLabel goals = new JLabel(String.valueOf(userTeam.getTopGoalscorers().get(i).getGoals()));
            goals.setHorizontalAlignment(SwingConstants.CENTER);
            goals.setVerticalAlignment(SwingConstants.CENTER);
            goals.setBorder(BorderFactory.createLineBorder(Color.WHITE));
            goals.setFont(new Font("Arial", Font.PLAIN, 10));
            goals.setForeground(Color.WHITE);

            teamStatsLabel.add(position);
            teamStatsLabel.add(appearances);
            teamStatsLabel.add(goals);
        }

        topGoalScorersLabel.add(nameLabel);
        topGoalScorersLabel.add(teamStatsLabel);

        mainLable.add(titleLabel, BorderLayout.NORTH);
        mainLable.add(topGoalScorersLabel, BorderLayout.CENTER);

        return mainLable;
    }

    private JLabel getLeagueTopGoalscorers(Team userTeam){
        JLabel mainLable = new JLabel();
        mainLable.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Top Goalscorers : " + userTeam.getLeague().getName());
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        titleLabel.setPreferredSize(new Dimension(1000,25));

        JLabel topGoalScorersLabel = new JLabel();
        topGoalScorersLabel.setLayout(new GridLayout(1, 2));

        JLabel nameLabel = new JLabel();
        nameLabel.setLayout(new GridLayout(6, 1));
        nameLabel.setBackground(Color.WHITE);
        nameLabel.setOpaque(true);

        nameLabel.add(new JLabel("Name"));
        for (int i=0; i<5; i++) {
            JLabel player = new JLabel(userTeam.getLeague().getTopGoalscorers().get(i).getPlayerName());
            player.setHorizontalAlignment(SwingConstants.CENTER);
            player.setVerticalAlignment(SwingConstants.CENTER);
            player.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            player.setFont(new Font("Arial", Font.PLAIN, 10));
            nameLabel.add(player);
        }

        JLabel teamStatsLabel = new JLabel();
        teamStatsLabel.setLayout(new GridLayout(6, 3));
        teamStatsLabel.setOpaque(true);

        teamStatsLabel.add(new JLabel("Team"));
        teamStatsLabel.add(new JLabel("Apps"));
        teamStatsLabel.add(new JLabel("Goals"));

        for (int i=0; i<5; i++) {
            JLabel team = new JLabel(userTeam.getLeague().getTopGoalscorers().get(i).getTeam().getShortName());
            team.setHorizontalAlignment(SwingConstants.CENTER);
            team.setVerticalAlignment(SwingConstants.CENTER);

            team.setBackground(userTeam.getLeague().getTopGoalscorers().get(i).getTeam().getTeamColor());
            team.setForeground(Color.WHITE);
            team.setOpaque(true);
            team.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            team.setFont(new Font("Arial", Font.PLAIN, 10));

            JLabel appearances = new JLabel(String.valueOf(userTeam.getLeague().getTopGoalscorers().get(i).getAppearances()));
            appearances.setHorizontalAlignment(SwingConstants.CENTER);
            appearances.setVerticalAlignment(SwingConstants.CENTER);
            appearances.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            appearances.setFont(new Font("Arial", Font.PLAIN, 10));

            JLabel goals = new JLabel(String.valueOf(userTeam.getLeague().getTopGoalscorers().get(i).getGoals()));
            goals.setHorizontalAlignment(SwingConstants.CENTER);
            goals.setVerticalAlignment(SwingConstants.CENTER);
            goals.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            goals.setFont(new Font("Arial", Font.PLAIN, 10));

            teamStatsLabel.add(team);
            teamStatsLabel.add(appearances);
            teamStatsLabel.add(goals);
        }

        topGoalScorersLabel.add(nameLabel);
        topGoalScorersLabel.add(teamStatsLabel);

        mainLable.add(titleLabel, BorderLayout.NORTH);
        mainLable.add(topGoalScorersLabel, BorderLayout.CENTER);

        return mainLable;
    }
}