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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;

import static JPanels.HomeDefaultDisplay.getTitleBanner;

public class HomeGameDisplay extends JPanel {
    public HomeGameDisplay(Team homeTeam, Team awayTeam, Team userTeam, dateTime clock, UI ui, ArrayList<Game> week1allGames) {
        this.removeAll();
        this.setPreferredSize(new Dimension(1000,350));
        this.setBackground(Color.GREEN);
        this.setLayout(new BorderLayout());

        for (Game game : week1allGames){
            if (game.getHomeTeam() == userTeam || game.getAwayTeam() == userTeam) {
                //don't add result.
            } else {

                //add win to favourite, loss to underdog, draw if evenly matched.
                //will replace with better system. (as underdogs will sometimes win)

                Integer homeRating = game.getHomeTeam().getRating();
                Integer awayRating = game.getAwayTeam().getRating();
                //home advantage
                homeRating += 40;

                double homeRandomValue = Math.random();
                double awayRandomValue = Math.random();

                int homeGoals = (int)Math.round(homeRating * homeRandomValue / 75);
                int awayGoals = (int)Math.round(awayRating * awayRandomValue / 75);

                if (homeGoals > awayGoals) {
                    //home win
                    game.getHomeTeam().addWin();
                    game.getAwayTeam().addLoss();
                } else if (awayGoals > homeGoals) {
                    //away win
                    game.getHomeTeam().addLoss();
                    game.getAwayTeam().addWin();
                } else {
                    //draw
                    game.getHomeTeam().addDraw();
                    game.getAwayTeam().addDraw();
                }


                //game.getHomeTeam().addWin();
                //game.getAwayTeam().addLoss();
            }
        }








        //end of new code

        ArrayList<Team> leagueStandings = userTeam.getLeague().getStandings();

        Team opponent;
        if (homeTeam == userTeam) opponent = awayTeam ; else opponent = homeTeam;

        this.add(gameTitle(homeTeam, awayTeam), BorderLayout.NORTH); //Game Title (NORTH)
        this.add(opponentsLineup(opponent),BorderLayout.EAST); //Opponents Lineup (EAST)
        this.add(HomeDefaultDisplay.leagueTable(leagueStandings), BorderLayout.WEST); //League Standings (WEST)

        //Game Options (CENTER)
        JPanel centerPanel = new JPanel(new GridLayout(2,1));
        centerPanel.setPreferredSize(new Dimension(750,300));
        centerPanel.setBackground(Color.BLUE);

        JLabel playGameButton = getPlayGameButton(userTeam, clock, ui, homeTeam, awayTeam);
        JLabel manageTeamButton = getManageTeamButton(userTeam);

        centerPanel.add(playGameButton);
        centerPanel.add(manageTeamButton);

        this.add(centerPanel, BorderLayout.CENTER);
    }

    private JLabel getManageTeamButton(Team userTeam) {
        JLabel manageTeamButton = new JLabel("Manage Team");

        manageTeamButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                manageTeamButton.removeMouseListener(this);

                JFrame manageTeam = new manageTeam(userTeam);

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

    private JLabel getPlayGameButton(Team userTeam, dateTime clock, UI ui, Team homeTeam, Team awayTeam) {
        JLabel playGameButton = new JLabel("Play Game");
        playGameButton.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                playGameButton.removeMouseListener(this);


                JFrame gameSimulator = new gameSimulator(homeTeam, awayTeam);

                //add points for none user teams
                Integer numberOfDraws = ((int)Math.floor(Math.random() * 4))*2;
                Integer numberOfWins = (userTeam.getLeague().getNumberOfTeams() - 2 - numberOfDraws)/2;
                //number of losses same as number of wins.

                ArrayList<Team> allTeams = userTeam.getLeague().getAllTeams();
                Collections.shuffle(allTeams);
                allTeams.remove(homeTeam);
                allTeams.remove(awayTeam);

                /*
                for (int i = 0; i < numberOfDraws; i++) {
                    allTeams.get(i).addDraw();
                }
                for (int i = numberOfDraws; i < numberOfDraws + numberOfWins; i++) {
                    allTeams.get(i).addWin();
                }
                for (int i = numberOfDraws + numberOfWins; i < numberOfDraws + numberOfWins*2; i++) {
                    allTeams.get(i).addLoss();
                }

                 */
                allTeams.add(homeTeam);
                allTeams.add(awayTeam);

                clock.progressDate();
                ui.updateCalendar(clock.getDateNumber(), userTeam);
                ui.revalidate();
            }
        });
        playGameButton.setHorizontalAlignment(SwingConstants.CENTER);
        playGameButton.setVerticalAlignment(SwingConstants.CENTER);
        playGameButton.setFont(new Font("Arial", Font.PLAIN, 20));
        playGameButton.setForeground(Color.WHITE);
        playGameButton.setBackground(Color.BLUE);
        playGameButton.setOpaque(true);
        playGameButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        return playGameButton;
    }

    //Game Title (NORTH)
    private JLabel gameTitle(Team homeTeam, Team awayTeam) {
        JLabel northLabel = new JLabel(homeTeam.getTeamName() + " vs " + awayTeam.getTeamName());
        return getTitleBanner(northLabel);
    }

    //Opponents Lineup (EAST)
    private JLabel opponentsLineup(Team opponent) {
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
