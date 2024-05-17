package JPanels;

import Objects.Player;
import Objects.Team;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static JPanels.HomeDefaultDisplay.getTitleBanner;

public class HomeGameDisplay extends JPanel {
    public HomeGameDisplay(Team homeTeam, Team awayTeam, ArrayList<Team> leagueStandings, Team userTeam) {
        this.removeAll();
        this.setPreferredSize(new Dimension(1000,350));
        this.setBackground(Color.GREEN);
        this.setLayout(new BorderLayout());

        Team opponent;
        if (homeTeam == userTeam) opponent = awayTeam ; else opponent = homeTeam;

        this.add(gameTitle(homeTeam, awayTeam), BorderLayout.NORTH); //Game Title (NORTH)
        this.add(opponentsLineup(opponent),BorderLayout.EAST); //Opponents Lineup (EAST)
        this.add(HomeDefaultDisplay.leagueTable(leagueStandings), BorderLayout.WEST); //League Standings (WEST)

        //Game Options (CENTER)
        JPanel centerPanel = new JPanel(new GridLayout(2, 1));
        centerPanel.setPreferredSize(new Dimension(750,300));
        centerPanel.setBackground(Color.BLUE);

        this.add(centerPanel, BorderLayout.CENTER);
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
