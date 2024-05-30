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

        //ArrayList to store retiring players, so they can be displayed in the JFrame.
        ArrayList<Player> retiringPlayers = new ArrayList<>();

        //age all players by one year
        for (Player player : userTeam.getLeague().getAllPlayers()){
            player.increaseAge(1);

            //increase rating for young players
            if (player.getAge() <= 24){
                player.increaseRating((int) (Math.random() * 4));
            }

            //decrease rating for old players
            if (player.getAge() >= 30){
                player.decreaseRating((int) (Math.random() * 4));
            }

            //if player is 35 or above, 50% chance the player retires
            if (player.getAge() >= 35){
                int retirementChance = (int) Math.round(Math.random());

                if (retirementChance == 1){
                    //removes the player and replaces them with a regen
                    player.retirePlayer();

                    retiringPlayers.add(player);
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

        JPanel retiringPlayersPanel = new JPanel();
        retiringPlayersPanel.setLayout(new BoxLayout(retiringPlayersPanel, BoxLayout.Y_AXIS));
        retiringPlayersPanel.setBackground(Color.GRAY);
        retiringPlayersPanel.setForeground(Color.BLUE);

        JLabel retiringPlayersTitleLabel = new JLabel("Retiring Players:");
        retiringPlayersTitleLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        retiringPlayersTitleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        retiringPlayersTitleLabel.setVerticalAlignment(SwingConstants.CENTER);
        retiringPlayersTitleLabel.setBackground(Color.BLUE);
        retiringPlayersTitleLabel.setForeground(Color.WHITE);
        retiringPlayersTitleLabel.setOpaque(true);
        retiringPlayersPanel.add(retiringPlayersTitleLabel);

        for (Player player : retiringPlayers){
            JLabel retiringPlayerLabel = new JLabel(player.getPlayerName() + ", " + player.getAge() + ", " + player.getPosition() + ", " + player.getTeam().getShortName());
            retiringPlayerLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            retiringPlayerLabel.setHorizontalAlignment(SwingConstants.LEFT);
            retiringPlayerLabel.setVerticalAlignment(SwingConstants.CENTER);
            retiringPlayerLabel.setBackground(player.getTeam().getTeamColor());
            retiringPlayerLabel.setForeground(Color.WHITE);
            retiringPlayerLabel.setOpaque(true);
            retiringPlayersPanel.add(retiringPlayerLabel);
        }

        JScrollPane retiringPlayersScrollPane = new JScrollPane(retiringPlayersPanel);
        retiringPlayersScrollPane.setPreferredSize(new Dimension(200, 800));
        this.add(retiringPlayersScrollPane, BorderLayout.WEST);

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

        // MORE BACK-END FUNCTIONS, executed after the JFrame is displayed so last seasons stats are displayed before stats are reset.
        for (Team team : userTeam.getLeague().getAllTeams()){
            team.resetStats();
        }

        for (Player player : userTeam.getLeague().getAllPlayers()){
            player.resetStats();
        }
    }
}
