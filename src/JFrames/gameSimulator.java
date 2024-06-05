package JFrames;

import Objects.Player;
import Objects.Team;
import data.Data;
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
    public gameSimulator(Game simulatedGame, Team userTeam, UI mainMenu) {

        Team homeTeam = simulatedGame.getHomeTeam();
        Team awayTeam = simulatedGame.getAwayTeam();

        this.setSize(500,800);
        this.setTitle("Match report: " + homeTeam.getTeamName() + " vs " + awayTeam.getTeamName());
        JPanel pitch = new JPanel(new BorderLayout());
        pitch.setBackground(new Color(40, 140, 40));
        pitch.setOpaque(true);
        this.add(pitch);

        Integer homeOffensiveRating = homeTeam.getOffensiveRating();
        Integer homeDefensiveRating = homeTeam.getDefensiveRating();

        Integer awayOffensiveRating = awayTeam.getOffensiveRating();
        Integer awayDefensiveRating = awayTeam.getDefensiveRating();

        int homeRating = Math.max(homeOffensiveRating - (awayDefensiveRating / 2), 1);
        int awayRating = Math.max(awayOffensiveRating - (homeDefensiveRating / 2), 1);

        //home advantage
        homeRating += 2;

        double homeRandomValue = Math.random() / 3;
        double awayRandomValue = Math.random() / 3;

        int homeGoals = (int)Math.round(homeRating * homeRandomValue);
        int awayGoals = (int)Math.round(awayRating * awayRandomValue);

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
                homeTeam.addCupWin(homeGoals);
                awayTeam.addCupLoss(awayGoals);
            } else if (awayGoals > homeGoals) {
                //away win
                homeTeam.addCupLoss(homeGoals);
                awayTeam.addCupWin(awayGoals);
            } else {
                //draw
                homeTeam.addCupDraw(homeGoals);
                awayTeam.addCupDraw(awayGoals);
            }
        }


        ArrayList<String> homeGoalscorers = new ArrayList<>();
        ArrayList<String> awayGoalscorers = new ArrayList<>();

        for (int i = 0; i < homeGoals; i++) {
            Player scorer;

            double scorerGenerator = Math.random();
            // 60% chance the scorer is a forward (20% chance for each forward)
            if (scorerGenerator >= 0.4) {
                //FWD scores
                int FWD1Rating = homeTeam.getStartingEleven()[10].getRating() - 60;
                int FWD2Rating = homeTeam.getStartingEleven()[9].getRating() - 60;
                int FWD3Rating = homeTeam.getStartingEleven()[8].getRating() - 60;
                int combinedFWD = FWD1Rating + FWD2Rating + FWD3Rating;

                double FWD1Chance = (double)FWD1Rating / (double)combinedFWD;
                double FWD2Chance = (double)FWD2Rating / (double)combinedFWD;

                double randomFWD = Math.random();

                if (randomFWD < FWD1Chance) {
                    scorer = homeTeam.getStartingEleven()[10]; //FWD 1
                } else if (randomFWD < FWD1Chance + FWD2Chance) {
                    scorer = homeTeam.getStartingEleven()[9]; //FWD 2
                } else {
                    scorer = homeTeam.getStartingEleven()[8]; //FWD 3
                }

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
            if (scorerGenerator >= 0.4) {
                //FWD scores
                int FWD1Rating = awayTeam.getStartingEleven()[10].getRating() - 60;
                int FWD2Rating = awayTeam.getStartingEleven()[9].getRating() - 60;
                int FWD3Rating = awayTeam.getStartingEleven()[8].getRating() - 60;
                int combinedFWD = FWD1Rating + FWD2Rating + FWD3Rating;

                double FWD1Chance = (double) FWD1Rating / (double) combinedFWD;
                double FWD2Chance = (double) FWD2Rating / (double) combinedFWD;

                double randomFWD = Math.random();

                if (randomFWD < FWD1Chance) {
                    scorer = awayTeam.getStartingEleven()[10]; //FWD 1
                } else if (randomFWD < FWD1Chance + FWD2Chance) {
                    scorer = awayTeam.getStartingEleven()[9]; //FWD 2
                } else {
                    scorer = awayTeam.getStartingEleven()[8]; //FWD 3
                }

            } else if (scorerGenerator >= 0.3) {
                // 30% chance the scorer is a midfielder (10% chance for each midfielder)
                scorer = awayTeam.getStartingEleven()[7]; //MID 1
            } else if (scorerGenerator >= 0.2) {
                scorer = awayTeam.getStartingEleven()[6]; //MID 2
            } else if (scorerGenerator >= 0.1) {
                scorer = awayTeam.getStartingEleven()[5]; //MID 3
            } else {
                // 10% chance the scorer is a defender (2.5% chance for each defender)
                int randomIntBetween1And4 = (int) Math.floor(Math.random() * 4) + 1;
                scorer = awayTeam.getStartingEleven()[randomIntBetween1And4]; //DEFENDER
            }

            //add a goal to the goalscorer's league goals tally
            if (simulatedGame.getGameType().equals("League")) {
                scorer.addLeagueGoal();
            } else if (simulatedGame.getGameType().equals("Cup")) {
                scorer.addCupGoal();
            }

            //add scorer and random minute to the list of goalscorers printed on the match report.
            awayGoalscorers.add(scorer.getPlayerName() + "  '" + (int)(Math.random() * 98 + 1));
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
            homeTeamLabel.setLayout(new GridLayout(1, 1));
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
            awayTeamLabel.setLayout(new GridLayout(1, 1));
            awayTeamLabel.setPreferredSize(new Dimension(230, 500));

            JLabel awayGoalscorersLabel = new JLabel();
            awayGoalscorersLabel.setLayout(new GridLayout(awayGoals, 1));
            awayGoalscorersLabel.setPreferredSize(new Dimension(200, awayGoals * 100));
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
            goalScorersLabel.setLayout(new GridLayout(1, 2));
            goalScorersLabel.setPreferredSize(new Dimension(500, 100));
            goalScorersLabel.add(homeGoalscorersLabel);
            goalScorersLabel.add(awayGoalscorersLabel);

            goalScorersLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            pitch.add(goalScorersLabel, BorderLayout.SOUTH);

            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.setVisible(true);

            int numberOfGamesInChampionsLeagueGroupStage = 6;

            //at the end of the champions league group stage.
            if (userTeam.getCupMatchesPlayed().equals(numberOfGamesInChampionsLeagueGroupStage) && simulatedGame.getGameType().equals("Cup")) {
                //End of group stage, load group stage summary JFrame, and trigger end of group stage events. (generation of UCL knockout rounds)
                new endOfGroupStageSummary(userTeam, mainMenu);
            }

            //after the champions league Round of 16, Quarter Finals, Semi Finals.
            if (
                    (userTeam.getCupMatchesPlayed().equals(numberOfGamesInChampionsLeagueGroupStage + 2) && simulatedGame.getGameType().equals("Cup"))
                            || (userTeam.getCupMatchesPlayed().equals(numberOfGamesInChampionsLeagueGroupStage + 4) && simulatedGame.getGameType().equals("Cup"))
                            || (userTeam.getCupMatchesPlayed().equals(numberOfGamesInChampionsLeagueGroupStage + 6) && simulatedGame.getGameType().equals("Cup"))
            ) {

                //get goals scored for both teams across the two legs of fixtures.
                Integer homeGoalsScored = simulatedGame.getHomeTeam().getCupGoalsScored();
                Integer awayGoalsScored = simulatedGame.getAwayTeam().getCupGoalsScored();

                if (homeGoalsScored > awayGoalsScored) {
                    //home team wins on aggregate
                    homeTeam.setAdvancingToNextRound(true);
                    awayTeam.setAdvancingToNextRound(false);

                } else if (awayGoalsScored > homeGoalsScored) {
                    //away team wins on aggregate
                    awayTeam.setAdvancingToNextRound(true);
                    homeTeam.setAdvancingToNextRound(false);

                } else {
                    //draw on aggregate, so simulate a penalty shootout to decide the winner.
                    //simulate penalty shootout (50/ 50 chance of winning for each team)
                    int randomNumber = (int) (Math.round(Math.random()));

                    if (randomNumber == 1) {
                        homeTeam.setAdvancingToNextRound(true);
                        awayTeam.setAdvancingToNextRound(false);
                    } else {
                        awayTeam.setAdvancingToNextRound(true);
                        homeTeam.setAdvancingToNextRound(false);
                    }
                }


                Team opponent;
                if (homeTeam.equals(userTeam)){
                    opponent = awayTeam;
                } else {
                    opponent = homeTeam;
                }

                if (userTeam.getCupMatchesPlayed().equals(numberOfGamesInChampionsLeagueGroupStage + 2)) {
                    ArrayList<Team> quarterFinalTeams = new ArrayList<>(8);
                    for (Team team : Data.world.getCupByName("UEFA Champions League").getTeams()) {
                        if (team.isAdvancingToNextRound()) {
                            quarterFinalTeams.add(team);
                        }
                    }

                    if (userTeam.isAdvancingToNextRound()){
                        JOptionPane.showMessageDialog(mainMenu, userTeam.getTeamName() + " advance to the quarter finals of the UEFA Champions League, after beating " + opponent.getTeamName() + " " + userTeam.getCupGoalsScored() + "-" + opponent.getCupGoalsScored() + " on aggregate");
                    } else {
                        JOptionPane.showMessageDialog(mainMenu, userTeam.getTeamName() + " are eliminated from the UEFA Champions League, after losing to " + opponent.getTeamName() + " " + opponent.getCupGoalsScored() + "-" + userTeam.getCupGoalsScored() + " on aggregate");
                    }
                    new endOfRoundOf16Summary(quarterFinalTeams, mainMenu, userTeam);
                } else if (userTeam.getCupMatchesPlayed().equals(numberOfGamesInChampionsLeagueGroupStage + 4)) {
                    ArrayList<Team> semiFinalTeams = new ArrayList<>(4);
                    for (Team team : Data.world.getCupByName("UEFA Champions League").getTeams()) {
                        if (team.isAdvancingToNextRound()) {
                            semiFinalTeams.add(team);
                        }
                    }

                    if (userTeam.isAdvancingToNextRound()){
                        JOptionPane.showMessageDialog(mainMenu, userTeam.getTeamName() + " advance to the semi-finals of the UEFA Champions League, after beating " + opponent.getTeamName() + " " + userTeam.getCupGoalsScored() + "-" + opponent.getCupGoalsScored() + " on aggregate");
                    } else {
                        JOptionPane.showMessageDialog(mainMenu, userTeam.getTeamName() + " are eliminated from the UEFA Champions League, after losing to " + opponent.getTeamName() + " " + opponent.getCupGoalsScored() + "-" + userTeam.getCupGoalsScored() + " on aggregate");
                    }
                    new endOfQuarterFinalSummary(semiFinalTeams, mainMenu, userTeam);
                } else if (userTeam.getCupMatchesPlayed().equals(numberOfGamesInChampionsLeagueGroupStage + 6)) {
                    ArrayList<Team> finalTeams = new ArrayList<>(2);
                    for (Team team : Data.world.getCupByName("UEFA Champions League").getTeams()) {
                        if (team.isAdvancingToNextRound()) {
                            finalTeams.add(team);
                        }
                    }

                    if (userTeam.isAdvancingToNextRound()){
                        Team finalOpponent;
                        if (finalTeams.get(0).equals(userTeam)) {
                            finalOpponent = finalTeams.get(1);
                        } else {
                            finalOpponent = finalTeams.get(0);
                        }

                        JOptionPane.showMessageDialog(mainMenu, userTeam.getTeamName() + " advance to the final of the UEFA Champions League, after beating " + opponent.getTeamName() + " " + userTeam.getCupGoalsScored() + "-" + opponent.getCupGoalsScored() + " on aggregate. They will face " + finalOpponent.getTeamName() + " in the final.");
                    } else {
                        JOptionPane.showMessageDialog(mainMenu, userTeam.getTeamName() + " are eliminated from the UEFA Champions League, after losing to " + opponent.getTeamName() + " " + opponent.getCupGoalsScored() + "-" + userTeam.getCupGoalsScored() + " on aggregate. " + finalTeams.get(0).getTeamName() + " will face " + finalTeams.get(1).getTeamName() + " in the final.");
                    }
                    new endOfSemiFinalsSummary(finalTeams, mainMenu, userTeam);
                }
            }

            //champions league final
            if (userTeam.getCupMatchesPlayed().equals(numberOfGamesInChampionsLeagueGroupStage + 7) && simulatedGame.getGameType().equals("Cup")) {
                if (homeGoals > awayGoals) {
                    Data.world.getCupByName("UEFA Champions League").setChampion(homeTeam);

                    JOptionPane.showMessageDialog(mainMenu, homeTeam.getTeamName() + " win the UEFA Champions League, beating " + awayTeam.getTeamName() + " " + homeGoals + " - " + awayGoals + " in the final.");
                } else if (awayGoals > homeGoals) {
                    Data.world.getCupByName("UEFA Champions League").setChampion(awayTeam);

                    JOptionPane.showMessageDialog(mainMenu, awayTeam.getTeamName() + " win the UEFA Champions League, beating " + homeTeam.getTeamName() + " " + awayGoals + " - " + homeGoals + " in the final.");
                } else {
                    int randomNumber = (int) (Math.round(Math.random()));
                    if (randomNumber == 1) {
                        Data.world.getCupByName("UEFA Champions League").setChampion(homeTeam);

                        JOptionPane.showMessageDialog(mainMenu, homeTeam.getTeamName() + " win the UEFA Champions League, beating " + awayTeam.getTeamName() + " on penalties in the final.");
                    } else {
                        Data.world.getCupByName("UEFA Champions League").setChampion(awayTeam);

                        JOptionPane.showMessageDialog(mainMenu, awayTeam.getTeamName() + " win the UEFA Champions League, beating " + homeTeam.getTeamName() + " on penalties in the final.");
                    }
                }
            }


            Integer numberOfLeagueGamesInSeason = userTeam.getLeague().getNumberOfGamesInSeason();


            //last game of the season must always be a league match, so end of season events are triggered.
            if (userTeam.getLeagueMatchesPlayed().equals(numberOfLeagueGamesInSeason) && simulatedGame.getGameType().equals("League")) {
                //End of season, load season summary JFrame, and trigger end of season events.
                new endOfSeasonSummary(userTeam);
                mainMenu.regenerateFixtures(userTeam);
                Data.world.wipeRecentTransfersList();
            }
        }
    }

