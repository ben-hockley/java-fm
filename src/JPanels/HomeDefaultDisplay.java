package JPanels;

import JFrames.UI;
import JFrames.manageTeam;
import JFrames.transferMarket;
import Objects.Team;
import Objects.dateTime;
import data.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class HomeDefaultDisplay extends JPanel {
    public HomeDefaultDisplay(ArrayList<Team> leagueStandings, Team userTeam, dateTime clock, UI parentUI) {
        this.setPreferredSize(new Dimension(1000,350));
        this.setLayout(new BorderLayout());
        this.removeAll();

        //Title of the team (NORTH)
        this.add(teamTitle(userTeam), BorderLayout.NORTH);

        //add League Table of the LEAGUE of the usert team (WEST).
        this.add(leagueTable(leagueStandings, "League", userTeam), BorderLayout.WEST);

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

        //these key listeners act as a substitute for the key listeners on the progress date button as the progress date button is not focused when the homeDefaultDisplay first loads.

        BlueLabel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_T) {

                    if (userTeam.getTeamType().equals("International")){
                        JOptionPane.showMessageDialog(null, "You are managing a national Team, so you cannot buy and sell players.");
                    } else {
                        if (clock.getMonthNumber().equals(1) || clock.getMonthNumber().equals(6) || clock.getMonthNumber().equals(7) || clock.getMonthNumber().equals(8)){
                            new transferMarket(userTeam);
                        } else {
                            JOptionPane.showMessageDialog(null, "Transfer window shut, you can buy and sell players in January, June, July and August.");
                        }
                    }
                }
            }
        });



        BlueLabel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_P) {
                    if (parentUI.progressDateButton.isEnabled()){
                        clock.progressDate();
                        parentUI.updateCalendar(clock.getDateNumber(), userTeam);

                        //dispose of all frames except the main frame.
                        if (Frame.getFrames().length > 1){

                            for (int i=1; i< Frame.getFrames().length; i++){
                                Frame.getFrames()[i].dispose();
                            }
                        }
                    }
                }
            }
        });
        BlueLabel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_M) {
                    new manageTeam(userTeam);
                }
            }
        });

        BlueLabel.setOpaque(true);

        JLabel RedLabel = new JLabel();
        RedLabel.setBackground(Color.RED);
        RedLabel.setLayout(new BoxLayout(RedLabel, BoxLayout.Y_AXIS));

        JLabel recentTransfersTitle = new JLabel("Recent Transfers");
        recentTransfersTitle.setHorizontalAlignment(SwingConstants.CENTER);
        recentTransfersTitle.setVerticalAlignment(SwingConstants.CENTER);
        recentTransfersTitle.setFont(new Font("Arial", Font.PLAIN, 20));
        recentTransfersTitle.setForeground(Color.WHITE);
        RedLabel.add(recentTransfersTitle);

        for (String transfer : Data.world.getRecentTransfers()){
            JLabel transferLabel = new JLabel(transfer);
            transferLabel.setHorizontalAlignment(SwingConstants.CENTER);
            transferLabel.setVerticalAlignment(SwingConstants.CENTER);
            transferLabel.setFont(new Font("Arial", Font.PLAIN, 10));
            transferLabel.setForeground(Color.WHITE);
            RedLabel.add(transferLabel);
        }


        RedLabel.setOpaque(true);

        JScrollPane redScrollPane= new JScrollPane(RedLabel);
        redScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        redScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);


        centerLabel.add(teamTopGoalscorers);
        centerLabel.add(leagueTopGoalscorers);
        centerLabel.add(BlueLabel);
        centerLabel.add(redScrollPane);

        this.add(centerLabel, BorderLayout.CENTER);
    }

    private JLabel teamTitle(Team userTeam){
        JLabel teamTitle = new JLabel(userTeam.getTeamName());
        teamTitle.setBackground(userTeam.getTeamColor());

        return teamTitle;
    }


    //League Standings (WEST)
    //static variable , so it can be shown in the homeGameDisplay aswell.
    static JScrollPane leagueTable(ArrayList<Team> leagueStandings, String tournamentType, Team userTeam){
        JLabel westLabel = new JLabel();
        westLabel.setPreferredSize(new Dimension(250, 300));
        westLabel.setBackground(Color.YELLOW);
        westLabel.setForeground(Color.WHITE);

        westLabel.setLayout(new GridLayout(1, 2));

        JLabel teamNames = new JLabel();
        JLabel teamStats = new JLabel();

        teamNames.setLayout(new GridLayout(leagueStandings.size() + 1, 1));

        JLabel tableTitle = getTableTitle(leagueStandings, tournamentType, userTeam);
        teamNames.add(tableTitle);

        int tablePosition = 1;
        for (Team leagueStanding : leagueStandings) {

            JLabel team = null;
            if (tournamentType.equals("League")){
                team = getTeamPositionInLeague(leagueStanding, tablePosition);
            } else if (tournamentType.equals("Cup")) {
                team = getTeamPositionInChampionsLeagueGroupStage(leagueStanding, tablePosition);
            }
            teamNames.add(team);
            tablePosition++;
        }

        teamStats.setLayout(new GridLayout(leagueStandings.size() + 1, 5));

        teamStats.add(new JLabel("MP"));
        teamStats.add(new JLabel("W"));
        teamStats.add(new JLabel("D"));
        teamStats.add(new JLabel("L"));
        teamStats.add(new JLabel("PTS"));

        if (tournamentType.equals("League")){

            for (Team leagueStanding : leagueStandings) {
                teamStats.add(new JLabel(String.valueOf(leagueStanding.getLeagueMatchesPlayed())));
                teamStats.add(new JLabel(String.valueOf(leagueStanding.getLeagueWins())));
                teamStats.add(new JLabel(String.valueOf(leagueStanding.getLeagueDraws())));
                teamStats.add(new JLabel(String.valueOf(leagueStanding.getLeagueLosses())));
                teamStats.add(new JLabel(String.valueOf(leagueStanding.getLeaguePoints())));
            }
        } else if (tournamentType.equals("Cup")){
            //add group stage stats
            if (userTeam.getCupMatchesPlayed() < 6){
                for (Team leagueStanding : leagueStandings) {
                    teamStats.add(new JLabel(String.valueOf(leagueStanding.getCupGames())));
                    teamStats.add(new JLabel(String.valueOf(leagueStanding.getCupWins())));
                    teamStats.add(new JLabel(String.valueOf(leagueStanding.getCupDraws())));
                    teamStats.add(new JLabel(String.valueOf(leagueStanding.getCupLosses())));
                    teamStats.add(new JLabel(String.valueOf(leagueStanding.getCupPoints())));
                }
            } else if (userTeam.getCupMatchesPlayed() < 12) {
                //for round of 16, quarter-finals and semi-final fixtures (2 legs)

                //replace standard league stats with aggregate score.
                teamStats.removeAll();

                teamStats.setLayout(new GridLayout(leagueStandings.size() + 1, 2));

                //displays legs played in the tie and the current aggregate score.
                teamStats.add(new JLabel("MP"));
                teamStats.add(new JLabel("Agg. Score"));

                for (Team knockoutStageTeam : leagueStandings){

                    //displays the aggregate score, since these are 'round stats' so reset at the end of each knockout stage.
                    teamStats.add(new JLabel(String.valueOf(knockoutStageTeam.getCupGames())));
                    teamStats.add(new JLabel(String.valueOf(knockoutStageTeam.getCupGoalsScored())));
                }
            } else {
                //for the final or otherwise, no need for stats.
                teamStats.removeAll();
            }
        }


        westLabel.add(teamNames);
        westLabel.add(teamStats);

        westLabel.setOpaque(true);
        JScrollPane westScrollPane = new JScrollPane(westLabel);
        westScrollPane.setPreferredSize(new Dimension(300, 300));
        westScrollPane.setFocusable(false);
        return westScrollPane;
    }

    private static JLabel getTableTitle(ArrayList<Team> leagueStandings, String tournamentType, Team userTeam) {
        JLabel tableTitle = new JLabel();

        if (tournamentType.equals("League")){
            tableTitle.setText("League Standings");
            tableTitle.setBackground(new Color(61, 25, 91)); // Premier League Color
        } else if (tournamentType.equals("Cup")){

            if (userTeam.getCupMatchesPlayed() < 6){
                tableTitle.setText("UCL Group " + leagueStandings.get(0).getChampionsLeagueGroupLetter() + " Standings");
            } else if (userTeam.getCupMatchesPlayed() < 8){
                tableTitle.setText("UCL Round of 16");
            } else if (userTeam.getCupMatchesPlayed() < 10){
                tableTitle.setText("UCL Quarter Finals");
            } else if (userTeam.getCupMatchesPlayed() < 12){
                tableTitle.setText("UCL Semi Finals");
            } else if (userTeam.getCupMatchesPlayed() == 12){
                tableTitle.setText("UCL FINAL");
            }

            tableTitle.setBackground(new Color(14, 32, 80)); // Champions League Color
        } else {
            tableTitle.setBackground(Color.BLACK);
        }
        tableTitle.setHorizontalAlignment(SwingConstants.CENTER);
        tableTitle.setVerticalAlignment(SwingConstants.CENTER);
        tableTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        tableTitle.setOpaque(true);
        tableTitle.setForeground(Color.WHITE);
        return tableTitle;
    }

    private static JLabel getTeamPositionInLeague(Team leagueStanding, int tablePosition) {
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
        return team;
    }

    private static JLabel getTeamPositionInChampionsLeagueGroupStage(Team leagueStanding, int tablePosition) {
        JLabel team = new JLabel(tablePosition + "." + leagueStanding.getShortName());
        team.setHorizontalAlignment(SwingConstants.CENTER);
        team.setVerticalAlignment(SwingConstants.CENTER);
        team.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        if (tablePosition <= 2) {
            team.setBackground(Color.GREEN);
        } else if (tablePosition == 3) {
            team.setBackground(Color.ORANGE);
        } else {
            team.setBackground(Color.RED);
        }
        team.setOpaque(true);
        team.setForeground(Color.WHITE);
        return team;
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

            JLabel appearances = new JLabel(String.valueOf(userTeam.getTopGoalscorers().get(i).getLeagueAppearances()));
            appearances.setHorizontalAlignment(SwingConstants.CENTER);
            appearances.setVerticalAlignment(SwingConstants.CENTER);
            appearances.setBorder(BorderFactory.createLineBorder(Color.WHITE));
            appearances.setFont(new Font("Arial", Font.PLAIN, 10));
            appearances.setForeground(Color.WHITE);

            JLabel goals = new JLabel(String.valueOf(userTeam.getTopGoalscorers().get(i).getLeagueGoals()));
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
            JLabel team = getTeam(userTeam, i);

            JLabel appearances = new JLabel(String.valueOf(userTeam.getLeague().getTopGoalscorers().get(i).getLeagueAppearances()));
            appearances.setHorizontalAlignment(SwingConstants.CENTER);
            appearances.setVerticalAlignment(SwingConstants.CENTER);
            appearances.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            appearances.setFont(new Font("Arial", Font.PLAIN, 10));

            JLabel goals = new JLabel(String.valueOf(userTeam.getLeague().getTopGoalscorers().get(i).getLeagueGoals()));
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

    private static JLabel getTeam(Team userTeam, int i) {
        JLabel team;

        if (userTeam.getTeamType().equals("International")){
            team = new JLabel(userTeam.getLeague().getTopGoalscorers().get(i).getNationality().getNationName());
            team.setBackground(userTeam.getLeague().getTopGoalscorers().get(i).getNationality().getNationColor());
        } else {
            team = new JLabel(userTeam.getLeague().getTopGoalscorers().get(i).getTeam().getShortName());
            team.setBackground(userTeam.getLeague().getTopGoalscorers().get(i).getTeam().getTeamColor());
        }

        team.setHorizontalAlignment(SwingConstants.CENTER);
        team.setVerticalAlignment(SwingConstants.CENTER);

        team.setForeground(Color.WHITE);
        team.setOpaque(true);
        team.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        team.setFont(new Font("Arial", Font.PLAIN, 10));
        return team;
    }
}