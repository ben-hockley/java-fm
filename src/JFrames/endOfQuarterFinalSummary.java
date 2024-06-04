package JFrames;

import Objects.Player;
import Objects.Team;
import data.Data;
import events.Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class endOfQuarterFinalSummary extends JFrame {

    public endOfQuarterFinalSummary(ArrayList<Team> semiFinalTeams, UI mainMenu, Team userTeam) {

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("UCL Quarter Final Summary");
        setSize(800, 600);

        JLabel titleLabel = new JLabel("Quarter Final Summary");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setBackground(new Color(14, 32, 80));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setOpaque(true);
        titleLabel.setPreferredSize(new Dimension(800, 100));
        add(titleLabel, BorderLayout.NORTH);

        JPanel semiFinalTeamsPanel = new JPanel();
        semiFinalTeamsPanel.setPreferredSize(new Dimension(200, 600));
        semiFinalTeamsPanel.setLayout(new GridLayout(5, 1));

        JLabel semiFinalTeamsLabel = new JLabel("Semi Final Teams:");
        semiFinalTeamsLabel.setFont(new Font("Arial", Font.BOLD, 24));
        semiFinalTeamsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        semiFinalTeamsLabel.setVerticalAlignment(SwingConstants.CENTER);
        semiFinalTeamsLabel.setBackground(new Color(14, 32, 80));
        semiFinalTeamsLabel.setForeground(Color.WHITE);
        semiFinalTeamsLabel.setOpaque(true);
        semiFinalTeamsPanel.add(semiFinalTeamsLabel);

        for (Team team : semiFinalTeams) {
            JLabel teamLabel = new JLabel();
            teamLabel.setBackground(team.getTeamColor());
            teamLabel.setForeground(Color.WHITE);
            teamLabel.setHorizontalAlignment(SwingConstants.CENTER);
            teamLabel.setVerticalAlignment(SwingConstants.CENTER);
            teamLabel.setText(team.getShortName());
            teamLabel.setOpaque(true);
            semiFinalTeamsPanel.add(teamLabel);
        }

        add(semiFinalTeamsPanel, BorderLayout.WEST);

        JPanel topScorersPanel = new JPanel();
        topScorersPanel.setPreferredSize(new Dimension(200, 600));
        topScorersPanel.setLayout(new GridLayout(9, 1));

        JLabel topScorersTitleLabel = new JLabel("Top Scorers:");
        topScorersTitleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topScorersTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topScorersTitleLabel.setVerticalAlignment(SwingConstants.CENTER);
        topScorersTitleLabel.setBackground(new Color(14, 32, 80));
        topScorersTitleLabel.setForeground(Color.WHITE);
        topScorersTitleLabel.setOpaque(true);
        topScorersPanel.add(topScorersTitleLabel);

        ArrayList<Player> topScorers = Data.world.getCupByName("UEFA Champions League").getPlayersByGoalsScored();
        for (int i = 0; i < 8; i++) {
            Player scorer = topScorers.get(i);

            JLabel playerLabel = new JLabel();
            playerLabel.setBackground(Color.WHITE);
            playerLabel.setForeground(Color.BLACK);
            playerLabel.setHorizontalAlignment(SwingConstants.CENTER);
            playerLabel.setVerticalAlignment(SwingConstants.CENTER);
            playerLabel.setText(scorer.getPlayerName() + " - " + scorer.getCupGoals() + " Goals");
            playerLabel.setOpaque(true);
            topScorersPanel.add(playerLabel);
        }

        add(topScorersPanel, BorderLayout.EAST);

        JPanel semiFinalFixturesPanel = new JPanel();
        semiFinalFixturesPanel.setPreferredSize(new Dimension(400, 500));
        semiFinalFixturesPanel.setLayout(new GridLayout(3, 1));

        JLabel semiFinalFixturesTitleLabel = new JLabel("Semi Final Fixtures:");
        semiFinalFixturesTitleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        semiFinalFixturesTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        semiFinalFixturesTitleLabel.setVerticalAlignment(SwingConstants.CENTER);
        semiFinalFixturesTitleLabel.setBackground(new Color(14, 32, 80));
        semiFinalFixturesTitleLabel.setForeground(Color.WHITE);
        semiFinalFixturesTitleLabel.setOpaque(true);
        semiFinalFixturesPanel.add(semiFinalFixturesTitleLabel);

        ArrayList<Game> semiFinalHomeLegs = new ArrayList<>(2);
        ArrayList<Game> semiFinalAwayLegs = new ArrayList<>(2);

        for (int i = 0; i < 2; i++) {
            //BACK END
            Team homeTeam = semiFinalTeams.get(i);
            Team awayTeam = semiFinalTeams.get(3-i);

            Game homeLeg = new Game(homeTeam, awayTeam, Data.listOfCupDates[10], "Cup");
            Game awayLeg = new Game(awayTeam, homeTeam, Data.listOfCupDates[11], "Cup");

            semiFinalHomeLegs.add(homeLeg);
            semiFinalAwayLegs.add(awayLeg);

            if (awayTeam.equals(userTeam) || homeTeam.equals(userTeam)){
                mainMenu.addUserGame(homeLeg);
                mainMenu.addUserGame(awayLeg);
            }

            //FRONT END

            JLabel semiFinalFixtureLabel = new JLabel(homeTeam.getTeamName() + " vs " + awayTeam.getTeamName());
            semiFinalFixtureLabel.setHorizontalAlignment(SwingConstants.CENTER);
            semiFinalFixtureLabel.setVerticalAlignment(SwingConstants.CENTER);
            semiFinalFixtureLabel.setBackground(new Color(14, 32, 80));
            semiFinalFixtureLabel.setForeground(Color.WHITE);
            semiFinalFixtureLabel.setOpaque(true);
            semiFinalFixturesPanel.add(semiFinalFixtureLabel);
        }

        mainMenu.addRoundOfChampionsLeagueFixtures(semiFinalHomeLegs);
        mainMenu.addRoundOfChampionsLeagueFixtures(semiFinalAwayLegs);

        add(semiFinalFixturesPanel, BorderLayout.CENTER);

        setVisible(true);

        for (Team team : Data.world.getCupByName("UEFA Champions League").getTeams()){
            team.resetCupRoundStats();
        }
    }

}
