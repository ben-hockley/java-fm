package JFrames;

import objects.Cup;
import objects.Team;
import data.Data;
import events.Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class endOfGroupStageSummary extends JFrame {

    public endOfGroupStageSummary(Team userTeam, UI mainMenu){
        setTitle("UCL: End of Group Stage Summary");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        //get arrayList of all the teams that finished 1st or 2nd in their group, so qualify for the knockout stage.
        ArrayList<Team> teamsAdvancingToKnockouts = new ArrayList<>();
        for (Team team : Data.world.getCupByName("UEFA Champions League").getTeams()){
            if (team.getChampionsLeagueGroupStandings().indexOf(team) < 2){
                //advance team to knockout if they finished first or second in their group.
                teamsAdvancingToKnockouts.add(team);
                team.setAdvancingToNextRound(true);
            } else {
                team.setAdvancingToNextRound(false);
            }
        }

        //sorts teams by group position (Group winners first).
        teamsAdvancingToKnockouts.sort((team1, team2) -> {
            int team1GroupPosition = team1.getChampionsLeagueGroupStandings().indexOf(team1);
            int team2GroupPosition = team2.getChampionsLeagueGroupStandings().indexOf(team2);

            return Integer.compare(team1GroupPosition, team2GroupPosition);
        });

        ArrayList<Game> roundOf16HomeFixtures = new ArrayList<>();
        ArrayList<Game> roundOf16AwayFixtures = new ArrayList<>();

        for (int i=0; i<8; i++){
            Team fixtureHomeTeam = teamsAdvancingToKnockouts.get(i);
            Team fixtureAwayTeam = teamsAdvancingToKnockouts.get(15 - i);

            Game homeFixture = new Game(fixtureHomeTeam, fixtureAwayTeam, Data.listOfCupDates[6], "Cup");
            Game awayFixture = new Game(fixtureAwayTeam, fixtureHomeTeam, Data.listOfCupDates[7], "Cup");

            //add all round of 16 fixtures to all games 2d array
            roundOf16HomeFixtures.add(homeFixture);
            roundOf16AwayFixtures.add(awayFixture);

            //if the fixture involves the user team, add it to the events array so that  it appears in the calendar.
            if (fixtureHomeTeam == userTeam || fixtureAwayTeam == userTeam){
                mainMenu.addUserGame(homeFixture);
                mainMenu.addUserGame(awayFixture);
            }
        }


        // adds non-user champions league fixtures to the fixture list so that
        // they will be simulated in the background simultaneoulsy to the user's games.
        mainMenu.addRoundOfChampionsLeagueFixtures(roundOf16HomeFixtures);
        mainMenu.addRoundOfChampionsLeagueFixtures(roundOf16AwayFixtures);


        JLabel titleLabel = getTitleLabel();
        add(titleLabel, BorderLayout.NORTH);

        JPanel teamsAdvancingPanel = new JPanel();
        teamsAdvancingPanel.setPreferredSize(new Dimension(300,600));
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
            roundOf16FixtureLabel.setVerticalAlignment(SwingConstants.CENTER);
            roundOf16FixtureLabel.setHorizontalAlignment(SwingConstants.CENTER);
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
