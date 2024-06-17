package JFrames.championsLeaguePopups;

import JFrames.UI;
import objects.Team;
import objects.Player;

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

        JLabel roundOf16Label = getRoundOf16Label();
        add(roundOf16Label, BorderLayout.NORTH);

        JPanel advancingTeamsPanel = getAdvancingTeamsPanel(quarterFinalTeams);
        add(advancingTeamsPanel, BorderLayout.WEST);

        JPanel topScorersPanel = new JPanel();
        topScorersPanel.setPreferredSize(new Dimension(200, 600));
        topScorersPanel.setLayout(new GridLayout(9, 1));
        topScorersPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        JLabel topScorersTitleLabel = getFixtureLabel("Top Scorers:", Font.BOLD, 18, SwingConstants.LEFT);
        topScorersPanel.add(topScorersTitleLabel);

        ArrayList<Player> topScorers = Data.world.getCupByName("UEFA Champions League").getPlayersByGoalsScored();

        for (int i = 0; i < 8; i++) {
            JLabel topScorerLabel = new JLabel();
            topScorerLabel.setText(topScorers.get(i).getPlayerName() + " - " + topScorers.get(i).getCupGoals() + " Goals");
            topScorerLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            topScorerLabel.setHorizontalAlignment(SwingConstants.LEFT);
            topScorerLabel.setVerticalAlignment(SwingConstants.CENTER);
            topScorersTitleLabel.setOpaque(true);
            topScorersPanel.add(topScorerLabel);
        }
        add(topScorersPanel, BorderLayout.EAST);

        JPanel nextRoundsFixturesPanel = new JPanel();
        nextRoundsFixturesPanel.setLayout(new GridLayout(5, 1));
        nextRoundsFixturesPanel.setForeground(Color.WHITE);
        nextRoundsFixturesPanel.setBackground(new Color(14,32,80));

        JLabel nextRoundsFixturesTitleLabel = getNextRoundsFixturesTitleLabel();
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
            JLabel fixtureLabel = getFixtureLabel(homeTeam.getShortName() + " vs " + awayTeam.getShortName(), Font.PLAIN, 12, SwingConstants.CENTER);
            nextRoundsFixturesPanel.add(fixtureLabel);
        }

        //add quarter-final fixtures to back-end to simulate in the background
        mainMenu.addRoundOfChampionsLeagueFixtures(quarterFinalHomeFixtures); //leg 1
        mainMenu.addRoundOfChampionsLeagueFixtures(quarterFinalAwayFixtures); //leg 2


        add(nextRoundsFixturesPanel, BorderLayout.CENTER);
        setVisible(true);

        for (Team team : Data.world.getCupByName("UEFA Champions League").getTeams()){
            team.resetCupRoundStats();
        }
    }

    private static JLabel getFixtureLabel(String homeTeam, int plain, int size, int center) {
        JLabel fixtureLabel = new JLabel(homeTeam);
        fixtureLabel.setFont(new Font("Arial", plain, size));
        fixtureLabel.setHorizontalAlignment(center);
        fixtureLabel.setVerticalAlignment(SwingConstants.CENTER);
        fixtureLabel.setBackground(new Color(14, 32, 80));
        fixtureLabel.setForeground(Color.WHITE);
        fixtureLabel.setOpaque(true);
        return fixtureLabel;
    }

    private static JLabel getNextRoundsFixturesTitleLabel() {
        return getFixtureLabel("Quarter Final Fixtures:", Font.BOLD, 12, SwingConstants.CENTER);
    }

    private static JLabel getRoundOf16Label() {
        JLabel roundOf16Label = new JLabel("Round of 16 Summary");
        roundOf16Label.setHorizontalAlignment(SwingConstants.CENTER);
        roundOf16Label.setVerticalAlignment(SwingConstants.CENTER);
        roundOf16Label.setBackground(new Color(14,32,80)); // champions league color
        roundOf16Label.setForeground(Color.WHITE);
        roundOf16Label.setFont(new Font("Arial", Font.BOLD, 24));
        roundOf16Label.setOpaque(true);
        roundOf16Label.setPreferredSize(new Dimension(800, 100));
        return roundOf16Label;
    }

    private static JPanel getAdvancingTeamsPanel(ArrayList<Team> quarterFinalTeams) {
        JPanel advancingTeamsPanel = new JPanel();
        advancingTeamsPanel.setPreferredSize(new Dimension(200, 600));
        advancingTeamsPanel.setLayout(new GridLayout(9, 1));
        advancingTeamsPanel.setForeground(Color.WHITE);
        advancingTeamsPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        JLabel advancingTeamsLabel = getFixtureLabel("Teams Advancing to Quarter Finals:", Font.BOLD, 12, SwingConstants.LEFT);
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
        return advancingTeamsPanel;
    }
}
