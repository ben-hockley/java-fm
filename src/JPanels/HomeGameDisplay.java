package JPanels;

import Objects.Player;
import Objects.Team;
import Objects.dateTime;
import JFrames.UI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Callable;

import static JPanels.HomeDefaultDisplay.getTitleBanner;

public class HomeGameDisplay extends JPanel {
    public HomeGameDisplay(Team homeTeam, Team awayTeam, Team userTeam, dateTime clock, UI ui) {
        this.removeAll();
        this.setPreferredSize(new Dimension(1000,350));
        this.setBackground(Color.GREEN);
        this.setLayout(new BorderLayout());

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
        JLabel manageTeamButton = getManageTeamButton();

        centerPanel.add(playGameButton);
        centerPanel.add(manageTeamButton);

        this.add(centerPanel, BorderLayout.CENTER);
    }

    private JLabel getManageTeamButton() {
        JLabel manageTeamButton = new JLabel("Manage Team");

        manageTeamButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Manage Team!");
                //need to add functionality to manage team.
                manageTeamButton.removeMouseListener(this);
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
        playGameButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Game played!");
                playGameButton.removeMouseListener(this);


                JFrame gameSimulator = new JFrame();
                gameSimulator.setSize(500,500);
                JPanel pitch = new JPanel(new BorderLayout());
                pitch.setBackground(new Color(40, 140, 40));
                pitch.setOpaque(true);
                gameSimulator.add(pitch);


                //game logic is very simple and entierly random at this point,
                //will be replaced with a more complex system in the future.
                int homeGoals = (int)(Math.floor((Math.random() * 5)));
                int awayGoals = (int)(Math.floor((Math.random() * 5)));

                if (homeGoals > awayGoals) {
                    //home win
                    homeTeam.addPoints(3);
                } else if (awayGoals > homeGoals) {
                    //away win
                    awayTeam.addPoints(3);
                } else {
                    //draw
                    homeTeam.addPoints(1);
                    awayTeam.addPoints(1);
                }

                //add points for none user teams
                Integer numberOfDraws = ((int)Math.floor(Math.random() * 4))*2;
                Integer numberOfWins = (userTeam.getLeague().getNumberOfTeams() - 2 - numberOfDraws)/2;
                //number of losses same as number of wins.

                ArrayList<Team> allTeams = userTeam.getLeague().getAllTeams();
                Collections.shuffle(allTeams);
                allTeams.remove(homeTeam);
                allTeams.remove(awayTeam);

                for (int i = 0; i < numberOfDraws; i++) {
                    allTeams.get(i).addPoints(1);
                }
                for (int i = numberOfDraws; i < numberOfDraws + numberOfWins; i++) {
                    allTeams.get(i).addPoints(3);
                }



                JLabel score = new JLabel(homeTeam.getTeamName() + " " + homeGoals + " - " + awayGoals + " " + awayTeam.getTeamName());
                score.setFont(new Font("Arial", Font.PLAIN, 20));
                score.setHorizontalAlignment(SwingConstants.CENTER);
                score.setVerticalAlignment(SwingConstants.CENTER);
                score.setBackground(Color.WHITE);
                score.setForeground(Color.BLACK);
                score.setOpaque(true);
                pitch.add(score, BorderLayout.NORTH);

                gameSimulator.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                gameSimulator.setVisible(true);

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

        Player[] opponentStarting11 = opponent.bestStartingEleven();
        for (Player player : opponentStarting11) {
            opponentsLineup.add(new JLabel(player.getPlayerName() + " - " + player.getPosition() + " - " + player.getRating()));
        }

        opponentsLineup.setOpaque(true);
        return opponentsLineup;
    }
}
