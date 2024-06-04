package JFrames;

import Objects.Team;
import Objects.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import data.Data;
import events.Game;

public class endOfRoundOf16Summary extends JFrame {
    public endOfRoundOf16Summary(ArrayList<Team> quarterFinalTeams, UI mainMenu, Team userTeam) {
        setTitle("UCL RO16 Summary");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());

        JLabel roundOf16Label = new JLabel("Round of 16 Summary");
        roundOf16Label.setHorizontalAlignment(SwingConstants.CENTER);
        roundOf16Label.setVerticalAlignment(SwingConstants.CENTER);
        roundOf16Label.setBackground(new Color(14,32,80)); // champions league color
        roundOf16Label.setForeground(Color.WHITE);
        roundOf16Label.setFont(new Font("Arial", Font.BOLD, 24));
        roundOf16Label.setOpaque(true);
        roundOf16Label.setPreferredSize(new Dimension(800, 100));
        add(roundOf16Label, BorderLayout.NORTH);

        JPanel advancingTeamsPanel = new JPanel();
        advancingTeamsPanel.setPreferredSize(new Dimension(200, 600));
        advancingTeamsPanel.setLayout(new GridLayout(9, 1));
        advancingTeamsPanel.setForeground(Color.WHITE);
        advancingTeamsPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        JLabel advancingTeamsLabel = new JLabel("Teams Advancing to Quarter Finals:");
        advancingTeamsLabel.setFont(new Font("Arial", Font.BOLD, 12));
        advancingTeamsLabel.setHorizontalAlignment(SwingConstants.LEFT);
        advancingTeamsLabel.setVerticalAlignment(SwingConstants.CENTER);
        advancingTeamsLabel.setBackground(new Color(14,32,80));
        advancingTeamsLabel.setForeground(Color.WHITE);
        advancingTeamsLabel.setOpaque(true);
        advancingTeamsPanel.add(advancingTeamsLabel);


        for (Team team : quarterFinalTeams) {
            JLabel teamLabel = new JLabel();
            teamLabel.setText(team.getShortName());
            teamLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            teamLabel.setHorizontalAlignment(SwingConstants.LEFT);
            teamLabel.setVerticalAlignment(SwingConstants.CENTER);
            teamLabel.setBackground(team.getTeamColor());
            teamLabel.setForeground(Color.WHITE);
            teamLabel.setOpaque(true);
            advancingTeamsPanel.add(teamLabel);
        }

        advancingTeamsPanel.setPreferredSize(new Dimension(200, 600));
        add(advancingTeamsPanel, BorderLayout.WEST);

        JPanel topScorersPanel = new JPanel();
        topScorersPanel.setPreferredSize(new Dimension(200, 600));
        topScorersPanel.setLayout(new GridLayout(9, 1));
        topScorersPanel.setForeground(Color.WHITE);
        topScorersPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        JLabel topScorersLabel = new JLabel("Top Scorers:");


        topScorersLabel.setFont(new Font("Arial", Font.BOLD, 18));
        topScorersLabel.setHorizontalAlignment(SwingConstants.LEFT);
        topScorersLabel.setVerticalAlignment(SwingConstants.CENTER);
        topScorersLabel.setBackground(new Color(14,32,80));
        topScorersLabel.setForeground(Color.WHITE);
        topScorersLabel.setOpaque(true);
        topScorersPanel.add(topScorersLabel);

        ArrayList<Player> topScorers = Data.world.getCupByName("UEFA Champions League").getPlayersByGoalsScored();

        for (int i = 0; i < 5; i++) {
            JLabel topScorerLabel = new JLabel();
            topScorerLabel.setText(topScorers.get(i).getPlayerName() + " - " + topScorers.get(i).getCupGoals() + " Goals");
            topScorerLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            topScorerLabel.setHorizontalAlignment(SwingConstants.LEFT);
            topScorerLabel.setVerticalAlignment(SwingConstants.CENTER);
            topScorerLabel.setBackground(new Color(14,32,80));
            topScorerLabel.setForeground(Color.WHITE);
            topScorersLabel.setOpaque(true);
            topScorersPanel.add(topScorerLabel);
        }
        add(topScorersPanel, BorderLayout.EAST);

        JPanel nextRoundsFixturesPanel = new JPanel();
        nextRoundsFixturesPanel.setLayout(new GridLayout(5, 1));
        nextRoundsFixturesPanel.setForeground(Color.WHITE);
        nextRoundsFixturesPanel.setBackground(new Color(14,32,80));

        JLabel nextRoundsFixturesTitleLabel = new JLabel("Quarter Final Fixtures:");
        nextRoundsFixturesTitleLabel.setFont(new Font("Arial", Font.BOLD, 12));
        nextRoundsFixturesTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nextRoundsFixturesTitleLabel.setVerticalAlignment(SwingConstants.CENTER);
        nextRoundsFixturesTitleLabel.setBackground(new Color(14,32,80));
        nextRoundsFixturesTitleLabel.setForeground(Color.WHITE);
        nextRoundsFixturesTitleLabel.setOpaque(true);
        nextRoundsFixturesPanel.add(nextRoundsFixturesTitleLabel);

        //BACK END, Adding fixtures to main menu so games are simulated.
        ArrayList<Team> quarterFinalTeamsCopy = new ArrayList<>(quarterFinalTeams);

        ArrayList<Game> quarterFinalHomeFixtures = new ArrayList<>(4);
        ArrayList<Game> quarterFinalAwayFixtures = new ArrayList<>(4);

        for (int i = 0; i < 4; i++) {
            Team homeTeam = quarterFinalTeamsCopy.remove((int) (Math.random() * quarterFinalTeamsCopy.size()));
            Team awayTeam = quarterFinalTeamsCopy.remove((int) (Math.random() * quarterFinalTeamsCopy.size()));

            Game homeLeg = new Game(homeTeam, awayTeam, Data.listOfCupDates[8], "Cup");
            Game awayLeg = new Game(awayTeam, homeTeam, Data.listOfCupDates[9], "Cup");

            quarterFinalHomeFixtures.add(homeLeg);
            quarterFinalAwayFixtures.add(awayLeg);

            if (homeTeam == userTeam || awayTeam == userTeam){
                mainMenu.addUserGame(homeLeg);
                mainMenu.addUserGame(awayLeg);
            }


            // BACK TO FORMATTING FRONT END
            JLabel fixtureLabel = new JLabel(homeTeam.getShortName() + " vs " + awayTeam.getShortName());
            fixtureLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            fixtureLabel.setHorizontalAlignment(SwingConstants.CENTER);
            fixtureLabel.setVerticalAlignment(SwingConstants.CENTER);
            fixtureLabel.setBackground(new Color(14,32,80));
            fixtureLabel.setForeground(Color.WHITE);
            fixtureLabel.setOpaque(true);
            nextRoundsFixturesPanel.add(fixtureLabel);
        }

        mainMenu.addRoundOfChampionsLeagueFixtures(quarterFinalHomeFixtures);
        mainMenu.addRoundOfChampionsLeagueFixtures(quarterFinalAwayFixtures);


        add(nextRoundsFixturesPanel, BorderLayout.CENTER);
        setVisible(true);
    }
}
