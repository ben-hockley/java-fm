package JFrames;

import Objects.Player;
import Objects.Team;
import events.Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class gameSimulator extends JFrame {

    /**
     * Constructor for the game simulator JFrame.
     * @param simulatedGame the game to be simulated.
     * @param userTeam the team the user is managing in the simulated game.
     */
    public gameSimulator(Game simulatedGame, Team userTeam) {

        Team homeTeam = simulatedGame.getHomeTeam();
        Team awayTeam = simulatedGame.getAwayTeam();


        this.setSize(500,800);
        this.setTitle("Match report: " + homeTeam.getTeamName() + " vs " + awayTeam.getTeamName());
        JPanel pitch = new JPanel(new BorderLayout());
        pitch.setBackground(new Color(40, 140, 40));
        pitch.setOpaque(true);
        this.add(pitch);

        Integer homeRating = homeTeam.getRating();
        Integer awayRating = awayTeam.getRating();

        //home advantage
        homeRating += 40;

        double homeRandomValue = Math.random();
        double awayRandomValue = Math.random();

        int homeGoals = (int)Math.round(homeRating * homeRandomValue / 75);
        int awayGoals = (int)Math.round(awayRating * awayRandomValue / 75);


        if (simulatedGame.getGameType().equals("League")) {
            //add results to teams' league stats for the league table.
            if (homeGoals > awayGoals) {
                //home win
                homeTeam.addLeagueWin();
                awayTeam.addLeagueLoss();
            } else if (awayGoals > homeGoals) {
                //away win
                homeTeam.addLeagueLoss();
                awayTeam.addLeagueWin();
            } else {
                //draw
                homeTeam.addLeagueDraw();
                awayTeam.addLeagueDraw();
            }
        } else if (simulatedGame.getGameType().equals("Cup")) {
            //add results to teams' cup stats for the cup table.
            if (homeGoals > awayGoals) {
                //home win
                homeTeam.addCupWin();
                awayTeam.addCupLoss();
            } else if (awayGoals > homeGoals) {
                //away win
                homeTeam.addCupLoss();
                awayTeam.addCupWin();
            } else {
                //draw
                homeTeam.addCupDraw();
                awayTeam.addCupDraw();
            }
        }


        ArrayList<String> homeGoalscorers = new ArrayList<>();
        ArrayList<String> awayGoalscorers = new ArrayList<>();

        for (int i = 0; i < homeGoals; i++) {
            Player scorer;

            double scorerGenerator = Math.random();
            // 60% chance the scorer is a forward (20% chance for each forward)
            if (scorerGenerator >= 0.8) {
                scorer = homeTeam.getStartingEleven()[10]; //FWD 1
            } else if (scorerGenerator >= 0.6) {
                scorer = homeTeam.getStartingEleven()[9]; //FWD 2
            } else if (scorerGenerator >= 0.4) {
                scorer = homeTeam.getStartingEleven()[8]; //FWD 3
            } else if (scorerGenerator >= 0.3) {
                // 30% chance the scorer is a midfielder (10% chance for each midfielder)
                scorer = homeTeam.getStartingEleven()[7]; //MID 1
            } else if (scorerGenerator >= 0.2) {
                scorer = homeTeam.getStartingEleven()[6]; //MID 2
            } else if (scorerGenerator >= 0.1) {
                scorer = homeTeam.getStartingEleven()[5]; //MID 3
            } else {
                // 10% chance the scorer is a defender (2.5% chance for each defender)
                int randomIntBetween1And4 = (int)Math.floor(Math.random()*4) + 1;
                scorer = homeTeam.getStartingEleven()[randomIntBetween1And4]; //DEFENDER
            }

            //add a goal to the goalscorer's league goals tally
            if (simulatedGame.getGameType().equals("League")) {
                scorer.addLeagueGoal();
            } else if (simulatedGame.getGameType().equals("Cup")) {
                scorer.addCupGoal();
            }

            //add scorer and random minute to the list of goalscorers printed on the match report.
            homeGoalscorers.add(scorer.getPlayerName() + "  '" + (int)(Math.random() * 98 + 1));
        }

        for (int i = 0; i < awayGoals; i++) {
            Player scorer;

            double scorerGenerator = Math.random();
            // 60% chance the scorer is a forward (20% chance for each forward)
            if (scorerGenerator >= 0.8) {
                scorer = awayTeam.getStartingEleven()[10]; //FWD 1
            } else if (scorerGenerator >= 0.6) {
                scorer = awayTeam.getStartingEleven()[9]; //FWD 2
            } else if (scorerGenerator >= 0.4) {
                scorer = awayTeam.getStartingEleven()[8]; //FWD 3
            } else if (scorerGenerator >= 0.3) {
                // 30% chance the scorer is a midfielder (10% chance for each midfielder)
                scorer = awayTeam.getStartingEleven()[7]; //MID 1
            } else if (scorerGenerator >= 0.2) {
                scorer = awayTeam.getStartingEleven()[6]; //MID 2
            } else if (scorerGenerator >= 0.1) {
                scorer = awayTeam.getStartingEleven()[5]; //MID 3
            } else {
                // 10% chance the scorer is a defender (2.5% chance for each defender)
                scorer = awayTeam.getStartingEleven()[(int)Math.floor(Math.random())*4 + 1]; //DEFENDER
            }

            //add a goal to the goalscorer's league goals tally
            if (simulatedGame.getGameType().equals("League")) {
                scorer.addLeagueGoal();
            } else if (simulatedGame.getGameType().equals("Cup")) {
                scorer.addCupGoal();
            }

            //add scorer and random minute to the list of goalscorers printed on the match report.
            awayGoalscorers.add(scorer.getPlayerName() + "  '" + (int)(Math.random() * 99));
        }


        JLabel score = new JLabel(homeTeam.getTeamName() + " " + homeGoals + " - " + awayGoals + " " + awayTeam.getTeamName());
        score.setFont(new Font("Arial", Font.PLAIN, 20));
        score.setHorizontalAlignment(SwingConstants.CENTER);
        score.setVerticalAlignment(SwingConstants.CENTER);
        score.setBackground(Color.WHITE);
        score.setForeground(Color.BLACK);
        score.setOpaque(true);
        pitch.add(score, BorderLayout.NORTH);


        JLabel homeTeamLabel = new JLabel();
        homeTeamLabel.setLayout(new GridLayout(1,1));
        homeTeamLabel.setPreferredSize(new Dimension(230, 500));

        JLabel homeGoalscorersLabel = new JLabel();
        homeGoalscorersLabel.setLayout(new GridLayout(homeGoals, 1));
        homeGoalscorersLabel.setBackground(Color.WHITE);
        homeGoalscorersLabel.setForeground(Color.BLACK);
        homeGoalscorersLabel.setOpaque(true);

        homeGoalscorers.sort((a, b) -> {
            String[] aSplit = a.split("'");
            String[] bSplit = b.split("'");
            return Integer.parseInt(aSplit[1]) - Integer.parseInt(bSplit[1]);
        });

        for (String goal : homeGoalscorers) {
            JLabel goalLabel = new JLabel(goal);
            goalLabel.setHorizontalAlignment(SwingConstants.CENTER);
            goalLabel.setVerticalAlignment(SwingConstants.CENTER);
            goalLabel.setForeground(Color.BLACK);
            homeGoalscorersLabel.add(goalLabel);
        }

        JLabel homeLineupLabel = new JLabel();
        homeLineupLabel.setLayout(new GridLayout(25, 1)); // set up the layout so there is space for all players and the formation and substitutes labels.
        homeLineupLabel.setBackground(Color.WHITE);
        homeLineupLabel.setForeground(Color.BLACK);
        homeLineupLabel.setOpaque(true);
        homeLineupLabel.add(new JLabel("Formation: " + homeTeam.getFormationInText()));
        Player[] homeStarting11 = homeTeam.getStartingEleven();
        for (Player player : homeStarting11) {

            //add a league appearance to the player's stats
            if (simulatedGame.getGameType().equals("League")) {
                player.addLeagueAppearance();
            } else if (simulatedGame.getGameType().equals("Cup")) {
                player.addCupAppearance();
            }

            //add the players name to the list of players printed on the starting 11 on the match report.
            homeLineupLabel.add(new JLabel(player.getPlayerName() + " - " + player.getPosition() + " - " + player.getRating()));
        }
        ArrayList<Player> homeSubs = homeTeam.getSubstitutes();
        homeLineupLabel.add(new JLabel("Substitutes:"));
        for (Player player : homeSubs) {

            //add the players name to the list of players printed on the substitutes on the match report.
            homeLineupLabel.add(new JLabel(player.getPlayerName() + " - " + player.getPosition() + " - " + player.getRating()));
        }

        homeTeamLabel.add(homeLineupLabel);

        pitch.add(homeTeamLabel, BorderLayout.WEST);


        JLabel awayTeamLabel = new JLabel();
        awayTeamLabel.setLayout(new GridLayout(1,1));
        awayTeamLabel.setPreferredSize(new Dimension(230, 500));

        JLabel awayGoalscorersLabel = new JLabel();
        awayGoalscorersLabel.setLayout(new GridLayout(awayGoals, 1));
        awayGoalscorersLabel.setPreferredSize(new Dimension(200, awayGoals*100));
        awayGoalscorersLabel.setBackground(Color.WHITE);
        awayGoalscorersLabel.setForeground(Color.BLACK);
        awayGoalscorersLabel.setOpaque(true);

        awayGoalscorers.sort((a, b) -> {
            String[] aSplit = a.split("'");
            String[] bSplit = b.split("'");
            return Integer.parseInt(aSplit[1]) - Integer.parseInt(bSplit[1]);
        });

        for (String goal : awayGoalscorers) {
            JLabel goalLabel = new JLabel(goal);
            goalLabel.setHorizontalAlignment(SwingConstants.CENTER);
            goalLabel.setVerticalAlignment(SwingConstants.CENTER);
            awayGoalscorersLabel.add(goalLabel);
        }

        JLabel awayLineupLabel = new JLabel();
        awayLineupLabel.setLayout(new GridLayout(25, 1)); // set up the layout so there is space for all players and the formation and substitutes labels.
        awayLineupLabel.setBackground(Color.WHITE);
        awayLineupLabel.setForeground(Color.BLACK);
        awayLineupLabel.setOpaque(true);
        awayLineupLabel.add(new JLabel("Formation: " + awayTeam.getFormationInText()));
        Player[] awayStarting11 = awayTeam.getStartingEleven();
        for (Player player : awayStarting11) {

            //add a league appearance to the player's stats.
            if (simulatedGame.getGameType().equals("League")) {
                player.addLeagueAppearance();
            } else if (simulatedGame.getGameType().equals("Cup")) {
                player.addCupAppearance();
            }

            //add the players name to the list of players printed on the starting 11 on the match report.
            awayLineupLabel.add(new JLabel(player.getPlayerName() + " - " + player.getPosition() + " - " + player.getRating()));
        }
        ArrayList<Player> awaySubs = awayTeam.getSubstitutes();
        awayLineupLabel.add(new JLabel("Substitutes:"));
        for (Player player : awaySubs) {

            //add the players name to the list of players printed on the substitutes on the match report.
            awayLineupLabel.add(new JLabel(player.getPlayerName() + " - " + player.getPosition() + " - " + player.getRating()));
        }

        awayTeamLabel.add(awayLineupLabel);

        pitch.add(awayTeamLabel, BorderLayout.EAST);

        JLabel goalScorersLabel = new JLabel();
        goalScorersLabel.setLayout(new GridLayout(1,2));
        goalScorersLabel.setPreferredSize(new Dimension(500, 100));
        goalScorersLabel.add(homeGoalscorersLabel);
        goalScorersLabel.add(awayGoalscorersLabel);

        goalScorersLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pitch.add(goalScorersLabel, BorderLayout.SOUTH);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        Integer numberOfGamesInChampionsLeagueGroupStage = 6;

        if (userTeam.getCupMatchesPlayed().equals(numberOfGamesInChampionsLeagueGroupStage) && simulatedGame.getGameType().equals("Cup")) {
            //End of group stage, load group stage summary JFrame, and trigger end of group stage events. (generation of UCL knockout rounds)
            new endOfGroupStageSummary(userTeam);
        }

        Integer numberOfLeagueGamesInSeason = userTeam.getLeague().getNumberOfGamesInSeason();


        //last game of the season must always be a league match, so end of season events are triggered.
        if (userTeam.getLeagueMatchesPlayed().equals(numberOfLeagueGamesInSeason) && simulatedGame.getGameType().equals("League")) {
            //End of season, load season summary JFrame, and trigger end of season events.
            new endOfSeasonSummary(userTeam);
        }
    }
}
