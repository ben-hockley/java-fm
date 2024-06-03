package JPanels;

import JFrames.gameSimulator;
import JFrames.manageTeam;
import Objects.Player;
import Objects.Team;
import Objects.dateTime;
import JFrames.UI;
import events.Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HomeGameDisplay extends JPanel {
    private JLabel playGameButton;
    private JLabel manageTeamButton;
    public HomeGameDisplay(Game userGame, Team userTeam, dateTime clock, UI ui, ArrayList<Game> allGames) {
        this.removeAll();
        this.setPreferredSize(new Dimension(1000,350));
        this.setBackground(Color.GREEN);
        this.setLayout(new BorderLayout());


        //simulate NON-USER games.
        for (Game game : allGames){
            if (game.getHomeTeam() == userTeam || game.getAwayTeam() == userTeam) {
                //don't add result. (games involving the user team are simulated in the gameSimulator class)
            } else {

                //gets the ratings of the teams to determine who has a better chance of winning.
                Integer homeRating = game.getHomeTeam().getRating();
                Integer awayRating = game.getAwayTeam().getRating();

                //home advantage, increases the home team's chance of winning.
                homeRating += 40;

                //generate random values between 0 and 1 for each team to simulate form/other factors.
                double homeRandomValue = Math.random();
                double awayRandomValue = Math.random();

                //generate number of goals scored by each team.
                int homeGoals = (int)Math.round(homeRating * homeRandomValue / 75);
                int awayGoals = (int)Math.round(awayRating * awayRandomValue / 75);

                //compare number of goals scored by each team to determine result.
                if (game.getGameType().equals("League")){
                    if (homeGoals > awayGoals) {
                        //home win
                        game.getHomeTeam().addLeagueWin();
                        game.getAwayTeam().addLeagueLoss();
                    } else if (awayGoals > homeGoals) {
                        //away win
                        game.getHomeTeam().addLeagueLoss();
                        game.getAwayTeam().addLeagueWin();
                    } else {
                        //draw
                        game.getHomeTeam().addLeagueDraw();
                        game.getAwayTeam().addLeagueDraw();
                    }
                } else if (game.getGameType().equals("Cup")){
                    if (homeGoals > awayGoals) {
                        //home win
                        game.getHomeTeam().addCupWin();
                        game.getAwayTeam().addCupLoss();
                    } else if (awayGoals > homeGoals) {
                        //away win
                        game.getHomeTeam().addCupLoss();
                        game.getAwayTeam().addCupWin();
                    } else {
                        //draw
                        game.getHomeTeam().addCupDraw();
                        game.getAwayTeam().addCupDraw();
                    }
                }


                //add appearances to all players in the game.
                Player[] homeStartingEleven = game.getHomeTeam().getStartingEleven();

                if (game.getGameType().equals("League")){
                    for (Player player : homeStartingEleven) {
                        player.addLeagueAppearance();
                    }
                } else if (game.getGameType().equals("Cup")){
                    for (Player player : homeStartingEleven) {
                        player.addCupAppearance();
                    }
                }


                Player[] awayStartingEleven = game.getAwayTeam().getStartingEleven();

                if (game.getGameType().equals("League")){
                    for (Player player : awayStartingEleven) {
                        player.addLeagueAppearance();
                    }
                } else if (game.getGameType().equals("Cup")){
                    for (Player player : awayStartingEleven) {
                        player.addCupAppearance();
                    }
                }

                //add goals to the goalscorers.
                for (int i = 0; i < homeGoals; i++) {
                    Player scorer;

                    double scorerGenerator = Math.random();
                    // 60% chance the scorer is a forward (20% chance for each forward)
                    if (scorerGenerator >= 0.8) {
                        scorer = homeStartingEleven[10]; //FWD 1
                    } else if (scorerGenerator >= 0.6) {
                        scorer = homeStartingEleven[9]; //FWD 2
                    } else if (scorerGenerator >= 0.4) {
                        scorer = homeStartingEleven[8]; //FWD 3
                    } else if (scorerGenerator >= 0.3) {
                        // 30% chance the scorer is a midfielder (10% chance for each midfielder)
                        scorer = homeStartingEleven[7]; //MID 1
                    } else if (scorerGenerator >= 0.2) {
                        scorer = homeStartingEleven[6]; //MID 2
                    } else if (scorerGenerator >= 0.1) {
                        scorer = homeStartingEleven[5]; //MID 3
                    } else {
                        // 10% chance the scorer is a defender (2.5% chance for each defender)
                        int randomIntBetween1And4 = (int)Math.floor(Math.random()*4) + 1;
                        scorer = homeStartingEleven[randomIntBetween1And4]; //DEFENDER
                    }

                    //add a goal to the goalscorer's tally

                    if (game.getGameType().equals("League")){
                        scorer.addLeagueGoal();
                    } else if (game.getGameType().equals("Cup")){
                        scorer.addCupGoal();
                    }
                }

                for (int i = 0; i < awayGoals; i++) {
                    Player scorer;

                    double scorerGenerator = Math.random();
                    // 60% chance the scorer is a forward (20% chance for each forward)
                    if (scorerGenerator >= 0.8) {
                        scorer = awayStartingEleven[10]; //FWD 1
                    } else if (scorerGenerator >= 0.6) {
                        scorer = awayStartingEleven[9]; //FWD 2
                    } else if (scorerGenerator >= 0.4) {
                        scorer = awayStartingEleven[8]; //FWD 3
                    } else if (scorerGenerator >= 0.3) {
                        // 30% chance the scorer is a midfielder (10% chance for each midfielder)
                        scorer = awayStartingEleven[7]; //MID 1
                    } else if (scorerGenerator >= 0.2) {
                        scorer = awayStartingEleven[6]; //MID 2
                    } else if (scorerGenerator >= 0.1) {
                        scorer = awayStartingEleven[5]; //MID 3
                    } else {
                        // 10% chance the scorer is a defender (2.5% chance for each defender)
                        int randomIntBetween1And4 = (int)Math.floor(Math.random()*4) + 1;
                        scorer = awayStartingEleven[randomIntBetween1And4]; //DEFENDER
                    }

                    //add a goal to the goalscorer's tally
                    if (game.getGameType().equals("League")){
                        scorer.addLeagueGoal();
                    } else if (game.getGameType().equals("Cup")){
                        scorer.addCupGoal();
                    }
                }
            }
        }

        ArrayList<Team> leagueStandings = new ArrayList<>();
        if (userGame.getGameType().equals("League")){
            leagueStandings = userTeam.getLeague().getStandings();
        } else if (userGame.getGameType().equals("Cup")){
            leagueStandings = userTeam.getChampionsLeagueGroupStandings();
            //add the standings of the user team's champions league group to the panel.
        }


        //USER GAME.

        //add the game title to the panel. (NORTH)
        this.add(getGameTitle(userGame), BorderLayout.NORTH);


        //add the opponents lineup to the panel. (EAST)
        Team homeTeam = userGame.getHomeTeam();
        Team awayTeam = userGame.getAwayTeam();

        Team opponent;
        if (homeTeam == userTeam) opponent = awayTeam ; else opponent = homeTeam;

        this.add(getOpponentsLineup(opponent),BorderLayout.EAST);

        //add the league table to the panel. (WEST)

        if (userGame.getGameType().equals("League")){
            this.add(HomeDefaultDisplay.leagueTable(leagueStandings, "League"), BorderLayout.WEST);
        } else if (userGame.getGameType().equals("Cup")){
            this.add(HomeDefaultDisplay.leagueTable(leagueStandings, "Cup"), BorderLayout.WEST);
        }

        //Game Options (CENTER)
        JPanel centerPanel = new JPanel(new GridLayout(2,1));
        centerPanel.setPreferredSize(new Dimension(750,300));
        centerPanel.setBackground(Color.BLUE);

        JLabel playGameButton = getPlayGameButton(userTeam, clock, ui, userGame);
        JLabel manageTeamButton = getManageTeamButton(userTeam);

        centerPanel.add(playGameButton);
        centerPanel.add(manageTeamButton);

        this.add(centerPanel, BorderLayout.CENTER);
    }

    private JLabel getManageTeamButton(Team userTeam) {
        manageTeamButton = new JLabel("Manage Team");

        manageTeamButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {

                new manageTeam(userTeam);

            }
        });
        manageTeamButton.setHorizontalAlignment(SwingConstants.CENTER);
        manageTeamButton.setVerticalAlignment(SwingConstants.CENTER);
        manageTeamButton.setFont(new Font("Arial", Font.PLAIN, 20));
        manageTeamButton.setForeground(Color.WHITE);
        manageTeamButton.setBackground(Color.RED);
        manageTeamButton.setOpaque(true);
        manageTeamButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        return manageTeamButton;
    }

    private JLabel getPlayGameButton(Team userTeam, dateTime clock, UI ui, Game simulatedGame) {
        playGameButton = new JLabel("Play Game");
        playGameButton.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt) {

                //remove mouse listeners from buttons , so they can't be clicked on non-matchdays.
                playGameButton.removeMouseListener(this);
                manageTeamButton.removeMouseListener(manageTeamButton.getMouseListeners()[0]);

                //open a game simulator, to simulate the game and display the match report.
                new gameSimulator(simulatedGame, userTeam);

                clock.progressDate();
                ui.updateCalendar(clock.getDateNumber(), userTeam);
                ui.revalidate();
            }
        });
        playGameButton.setHorizontalAlignment(SwingConstants.CENTER);
        playGameButton.setVerticalAlignment(SwingConstants.CENTER);
        playGameButton.setFont(new Font("Arial", Font.PLAIN, 20));
        playGameButton.setForeground(Color.WHITE);

        if (simulatedGame.getGameType().equals("League")){
            playGameButton.setBackground(new Color(61, 25, 91)); // Premier league Color
        } else if (simulatedGame.getGameType().equals("Cup")){
            playGameButton.setBackground(new Color(14, 32, 80)); // Champions league Color
        }


        playGameButton.setOpaque(true);
        playGameButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        return playGameButton;
    }

    //Game Title (NORTH)
    private JLabel getGameTitle(Game game) {

        Team homeTeam = game.getHomeTeam();
        Team awayTeam = game.getAwayTeam();

        JLabel northLabel = new JLabel(homeTeam.getTeamName() + " vs " + awayTeam.getTeamName());
        northLabel.setVerticalAlignment(SwingConstants.CENTER);
        northLabel.setHorizontalAlignment(SwingConstants.CENTER);
        northLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        northLabel.setForeground(Color.WHITE);
        northLabel.setOpaque(true);

        if (game.getGameType().equals("League")){
            northLabel.setBackground(new Color(61, 25, 91)); // Premier league Color
        } else if (game.getGameType().equals("Cup")){
            northLabel.setBackground(new Color(14, 32, 80)); // Champions league Color
        }

        return northLabel;
    }

    //Opponents Lineup (EAST)
    private JLabel getOpponentsLineup(Team opponent) {
        JLabel opponentsLineup = new JLabel();
        opponentsLineup.setLayout(new GridLayout(13, 1));
        opponentsLineup.setPreferredSize(new Dimension(300, 300));
        opponentsLineup.setBackground(Color.WHITE);
        opponentsLineup.setForeground(Color.BLACK);
        opponentsLineup.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        opponentsLineup.add(new JLabel("Opponent: " + opponent.getTeamName()));
        opponentsLineup.add(new JLabel("Formation: " + opponent.getFormationInText()));

        Player[] opponentStarting11 = opponent.getStartingEleven();
        for (Player player : opponentStarting11) {
            opponentsLineup.add(new JLabel(player.getPlayerName() + " - " + player.getPosition() + " - " + player.getRating()));
        }

        opponentsLineup.setOpaque(true);
        return opponentsLineup;
    }
}
