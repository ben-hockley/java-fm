package JFrames;

import Objects.Player;
import Objects.Team;
import data.Data;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class endOfSeasonSummary extends JFrame {
    public endOfSeasonSummary(Team userTeam) {
        setTitle("End of Season Summary");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        //END OF SEASON BACK-END FUNCTIONS.

        ArrayList<Player> retiringPlayers = new ArrayList<>();

        //age all players by one year
        for (Player player : userTeam.getLeague().getAllPlayers()){
            player.increaseAge(1);

            //if player is 35 or above, 50% chance the player retires
            if (player.getAge() >= 35){
                int retirementChance = (int) Math.round(Math.random());

                if (retirementChance == 1){
                    System.out.println(player.getPlayerName() + " retires, aged " + player.getAge());
                    retiringPlayers.add(player);
                    player.retirePlayer();
                }
            }
        }


        // CONTENTS OF JFRAME
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("End of Season Summary");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setBackground(Color.BLUE);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setOpaque(true);

        titleLabel.setPreferredSize(new Dimension(800, 50));
        this.add(titleLabel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6, 2));
        mainPanel.setBackground(Color.GRAY);




        // Champions
        Team LeagueChampions = userTeam.getLeague().getStandings().get(0);
        Player TopGoalscorer = userTeam.getLeague().getTopGoalscorers().get(0);

        JLabel championsLabel = new JLabel("Champions:" + LeagueChampions.getTeamName());
        championsLabel.setFont(new Font("Arial", Font.BOLD, 18));
        championsLabel.setHorizontalAlignment(SwingConstants.LEFT);
        championsLabel.setVerticalAlignment(SwingConstants.CENTER);
        championsLabel.setBackground(LeagueChampions.getTeamColor());
        championsLabel.setForeground(Color.WHITE);
        championsLabel.setOpaque(true);
        mainPanel.add(championsLabel);

        JLabel topGoalScorerLabel = new JLabel("Top Goal Scorer: " + TopGoalscorer.getPlayerName() + ", " + TopGoalscorer.getGoals() + " goals");
        topGoalScorerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        topGoalScorerLabel.setHorizontalAlignment(SwingConstants.LEFT);
        topGoalScorerLabel.setVerticalAlignment(SwingConstants.CENTER);
        topGoalScorerLabel.setBackground(TopGoalscorer.getTeam().getTeamColor());
        topGoalScorerLabel.setForeground(Color.WHITE);
        topGoalScorerLabel.setOpaque(true);
        mainPanel.add(topGoalScorerLabel);



        this.add(mainPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }
}
