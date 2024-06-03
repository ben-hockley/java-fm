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
        for (Player player : Data.world.getAllPlayers()){
            player.increaseAge(1);

            //increase rating for young players
            if (player.getAge() <= 25){
                player.increaseRating((int) (Math.random() * 4));
                //player rating increases by random int between 0 and 3.
                if (player.getRating() > 99){
                    player.setRating(99);
                    //if player rating is greater than 99, set it to 99, the maximum rating.
                }
            }

            //decrease rating for old players
            if (player.getAge() >= 31){
                player.decreaseRating((int) (Math.random() * 4));
                //player rating decreases by random int between 0 and 3
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

        //reset every team's transfer budget to their initial transfer budget.
        for (Team team : Data.world.getAllTeams()){
            team.resetTransferBudget();
            team.setCupMatchesPlayed(0);
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
            JLabel retiringPlayerLabel = getRetiringPlayerLabel(player);
            retiringPlayersPanel.add(retiringPlayerLabel);
        }

        JScrollPane retiringPlayersScrollPane = new JScrollPane(retiringPlayersPanel);
        retiringPlayersScrollPane.setPreferredSize(new Dimension(200, 800));
        this.add(retiringPlayersScrollPane, BorderLayout.WEST);

        JPanel mainPanel = getMainPanel(userTeam);


        this.add(mainPanel, BorderLayout.CENTER);
        this.setVisible(true);

        // MORE BACK-END FUNCTIONS, executed after the JFrame is displayed so last seasons stats are displayed before stats are reset.
        for (Team team : userTeam.getLeague().getAllTeams()){
            team.resetLeagueStats();
        }

        for (Player player : userTeam.getLeague().getAllPlayers()){
            player.resetStats();
        }
    }

    private static JPanel getMainPanel(Team userTeam) {
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

        JLabel topGoalScorerLabel = getTopGoalScorerLabel(TopGoalscorer);
        mainPanel.add(topGoalScorerLabel);
        return mainPanel;
    }

    private static JLabel getTopGoalScorerLabel(Player TopGoalscorer) {
        JLabel topGoalScorerLabel = new JLabel("Top Goal Scorer: " + TopGoalscorer.getPlayerName() + ", " + TopGoalscorer.getLeagueGoals() + " goals");
        topGoalScorerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        topGoalScorerLabel.setHorizontalAlignment(SwingConstants.LEFT);
        topGoalScorerLabel.setVerticalAlignment(SwingConstants.CENTER);
        topGoalScorerLabel.setBackground(TopGoalscorer.getTeam().getTeamColor());
        topGoalScorerLabel.setForeground(Color.WHITE);
        topGoalScorerLabel.setOpaque(true);
        return topGoalScorerLabel;
    }

    private static JLabel getRetiringPlayerLabel(Player player) {
        JLabel retiringPlayerLabel = new JLabel(player.getPlayerName() + ", " + player.getAge() + ", " + player.getPosition() + ", " + player.getTeam().getShortName());
        retiringPlayerLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        retiringPlayerLabel.setHorizontalAlignment(SwingConstants.LEFT);
        retiringPlayerLabel.setVerticalAlignment(SwingConstants.CENTER);
        retiringPlayerLabel.setBackground(player.getTeam().getTeamColor());
        retiringPlayerLabel.setForeground(Color.WHITE);
        retiringPlayerLabel.setOpaque(true);
        return retiringPlayerLabel;
    }
}
