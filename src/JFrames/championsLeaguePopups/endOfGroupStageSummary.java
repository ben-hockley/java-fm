package JFrames.championsLeaguePopups;

import JFrames.UI;
import objects.Cup;
import objects.Team;
import data.Data;
import events.Game;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
public class endOfGroupStageSummary extends JFrame {

    /**
     * This class is a JFrame that displays the end of group stage summary
     * for the UEFA Champions League.
     * It displays the teams that have advanced to the knockout stage, and
     * provides a list of the round of 16 fixtures.
     * It also lists the top goalscorers so far in the competition.
     * @param userTeam the team the user is playing as.
     * @param mainMenu the main UI JFrame, where the game runs.
     */
    public endOfGroupStageSummary(final Team userTeam, final UI mainMenu) {
        setTitle("UCL: End of Group Stage Summary");
        Cup championsLeague = Data.world.getCupByName("UEFA Champions League");
        final int popupWidth = 800;
        final int popupHeight = 600;

        final int noTeams = 16;
        final int noTies = noTeams / 2;
        final int groupStageGames = 6;

        final Integer[] leg1Date = Data.listOfCupDates[groupStageGames];
        final Integer[] leg2Date = Data.listOfCupDates[groupStageGames + 1];

        setSize(popupWidth, popupHeight);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        ArrayList<Team> teamsAdvancingToKnockouts = new ArrayList<>();
        for (Team team : championsLeague.getTeams()) {
            if (team.getChampionsLeagueGroupStandings().indexOf(team) < 2) {
                teamsAdvancingToKnockouts.add(team);
                team.setAdvancingToNextRound(true);
            } else {
                team.setAdvancingToNextRound(false);
            }
        }

        //sorts teams by group position (Group winners first).
        teamsAdvancingToKnockouts.sort((team1, team2) -> {
            int team1GroupPosition =
                    team1.getChampionsLeagueGroupStandings().indexOf(team1);
            int team2GroupPosition =
                    team2.getChampionsLeagueGroupStandings().indexOf(team2);

            return Integer.compare(team1GroupPosition, team2GroupPosition);
        });

        ArrayList<Game> roundOf16HomeFixtures = new ArrayList<>();
        ArrayList<Game> roundOf16AwayFixtures = new ArrayList<>();

        for (int i = 0; i < noTies; i++) {
            Team teamA = teamsAdvancingToKnockouts.get(i);
            Team teamB = teamsAdvancingToKnockouts.get(noTeams - 1 - i);

            Game leg1 = new Game(teamA, teamB, leg1Date, "Cup");
            Game leg2 = new Game(teamB, teamA, leg2Date, "Cup");

            //add all round of 16 fixtures to all games 2d array
            roundOf16HomeFixtures.add(leg1);
            roundOf16AwayFixtures.add(leg2);

            //Add user games to events (calendar).
            if (teamA == userTeam || teamB == userTeam) {
                mainMenu.addUserGame(leg1);
                mainMenu.addUserGame(leg2);
            }
        }


        // Non-user games added to list of games be simulated in background.
        mainMenu.addRoundOfChampionsLeagueFixtures(roundOf16HomeFixtures);
        mainMenu.addRoundOfChampionsLeagueFixtures(roundOf16AwayFixtures);


        JLabel titleLabel = getTitleLabel();
        add(titleLabel, BorderLayout.NORTH);

        JPanel teamsAdvancingPanel = new JPanel();
        teamsAdvancingPanel.setPreferredSize(new Dimension(300, popupHeight));
        teamsAdvancingPanel.setLayout(new BoxLayout(teamsAdvancingPanel, BoxLayout.Y_AXIS));
        teamsAdvancingPanel.setBackground(new Color(14,32,80));
        teamsAdvancingPanel.setForeground(Color.WHITE);
        teamsAdvancingPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        JLabel teamsAdvancingTitleLabel = getTeamsAdvancingTitleLabel();
        teamsAdvancingPanel.add(teamsAdvancingTitleLabel);

        for (Team team : teamsAdvancingToKnockouts){
            JLabel teamLabel = new JLabel();
            int groupPosition = team.getChampionsLeagueGroupStandings().indexOf(team) + 1;

            teamLabel.setText(team.getShortName() + " (Group "+ team.getChampionsLeagueGroupLetter() + (groupPosition == 1 ? " Winner)" : " Runner-Up)"));
            teamLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            teamLabel.setHorizontalAlignment(SwingConstants.LEFT);
            teamLabel.setVerticalAlignment(SwingConstants.CENTER);
            teamLabel.setBackground(team.getTeamColor());
            teamLabel.setForeground(Color.WHITE);
            teamLabel.setOpaque(true);
            teamsAdvancingPanel.add(teamLabel);
        }

        JScrollPane scrollableTeamsAdvancingPanel = new JScrollPane(teamsAdvancingPanel);
        scrollableTeamsAdvancingPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollableTeamsAdvancingPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(scrollableTeamsAdvancingPanel, BorderLayout.WEST);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(10, 1));
        mainPanel.setBackground(new Color(14,32,80));

        JLabel topScorerLabel = new JLabel();
        Cup activeCup = Data.world.getCupByName("UEFA Champions League");

        StringBuilder topGoalscorersList = new StringBuilder();

        if (activeCup.getTopGoalscorer().size() == 1){
            topGoalscorersList.append("Top Goalscorer: ");
        } else {
            topGoalscorersList.append("Top Goalscorers: ");
        }

        for (int i = 0; i < activeCup.getTopGoalscorer().size(); i++){
            topGoalscorersList.append(activeCup.getTopGoalscorer().get(i).getPlayerName()).append(", ").append(activeCup.getTopGoalscorer().get(i).getTeam().getShortName()).append(" ; ");
        }



        topScorerLabel.setText(topGoalscorersList + " (" + activeCup.getTopGoalscorer().get(0).getCupGoals() + " goals)");
        topScorerLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        topScorerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topScorerLabel.setVerticalAlignment(SwingConstants.CENTER);
        topScorerLabel.setBackground(new Color(14,32,80));
        topScorerLabel.setForeground(Color.WHITE);
        topScorerLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        topScorerLabel.setOpaque(true);

        JScrollPane scrollableTopScorerLabel = new JScrollPane(topScorerLabel);
        scrollableTopScorerLabel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollableTopScorerLabel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        mainPanel.add(scrollableTopScorerLabel);

        JLabel roundOf16FixturesTitleLabel = new JLabel("Round of 16 Fixtures");
        roundOf16FixturesTitleLabel.setVerticalAlignment(SwingConstants.CENTER);
        roundOf16FixturesTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        roundOf16FixturesTitleLabel.setForeground(Color.WHITE);

        mainPanel.add(roundOf16FixturesTitleLabel);

        for (Game roundOf16Fixture : roundOf16HomeFixtures){

            Team homeTeam = roundOf16Fixture.getHomeTeam();
            Team awayTeam = roundOf16Fixture.getAwayTeam();

            JLabel roundOf16FixtureLabel = new JLabel(homeTeam.getTeamName() + " vs " + awayTeam.getTeamName());
            roundOf16FixtureLabel.setHorizontalAlignment(SwingConstants.CENTER);
            roundOf16FixtureLabel.setVerticalAlignment(SwingConstants.CENTER);
            roundOf16FixtureLabel.setForeground(Color.WHITE);

            mainPanel.add(roundOf16FixtureLabel);
        }

        //add mainPanel to the JFrame
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);

        if (!userTeam.isAdvancingToNextRound()){
            JOptionPane.showMessageDialog(mainMenu,"After finishing " + Data.world.convertToDateFormat(userTeam.getChampionsLeagueGroupStandings().indexOf(userTeam) + 1) + " place in Group " + userTeam.getChampionsLeagueGroupLetter() + ", " + userTeam.getTeamName() + " have been eliminated from the Champions League.");
        } else {
            JOptionPane.showMessageDialog(mainMenu, userTeam.getTeamName() + " finished " + Data.world.convertToDateFormat(userTeam.getChampionsLeagueGroupStandings().indexOf(userTeam) + 1) + " place in Group " + userTeam.getChampionsLeagueGroupLetter() + " so advance to the Champions League round of 16.");
        }



        //END OF GROUP STAGE BACK-END FUNCTIONS.
        for (Team championsLeagueTeam : Data.world.getCupByName("UEFA Champions League").getTeams()){

            //wipes cup games, wins, draws, losses, points but not matches played
            championsLeagueTeam.resetCupRoundStats();
        }
    }

    private static JLabel getTeamsAdvancingTitleLabel() {
        JLabel teamsAdvancingTitleLabel = new JLabel();
        teamsAdvancingTitleLabel.setText("Teams Advancing to Knockouts:");
        teamsAdvancingTitleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        teamsAdvancingTitleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        teamsAdvancingTitleLabel.setVerticalAlignment(SwingConstants.CENTER);
        teamsAdvancingTitleLabel.setBackground(new Color(14,32,80));
        teamsAdvancingTitleLabel.setForeground(Color.WHITE);
        teamsAdvancingTitleLabel.setOpaque(true);
        return teamsAdvancingTitleLabel;
    }

    private static JLabel getTitleLabel() {
        JLabel titleLabel = new JLabel();
        titleLabel.setPreferredSize(new Dimension(800, 100));
        titleLabel.setText("UCL: End of Group Stage Summary");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBackground(new Color(14,32,80));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        titleLabel.setOpaque(true);
        return titleLabel;
    }
}
