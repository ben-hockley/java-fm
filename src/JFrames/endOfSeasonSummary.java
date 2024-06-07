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

        //promotion and relegation (english league)
        Data.england.getLeagueByTier(1).getStandings().get(19).relegate();
        Data.england.getLeagueByTier(1).getStandings().get(18).relegate();
        Data.england.getLeagueByTier(1).getStandings().get(17).relegate();

        Data.england.getLeagueByTier(2).getStandings().get(0).promote();
        Data.england.getLeagueByTier(2).getStandings().get(0).promote();
        Data.england.getLeagueByTier(2).getStandings().get(0).promote();

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

        JLabel titleLabel = getLeagueChampionsLabel("End of Season Summary", Font.BOLD, 24, SwingConstants.CENTER, Color.BLUE);

        titleLabel.setPreferredSize(new Dimension(800, 50));
        this.add(titleLabel, BorderLayout.NORTH);

        JPanel retiringPlayersPanel = new JPanel();
        retiringPlayersPanel.setLayout(new BoxLayout(retiringPlayersPanel, BoxLayout.Y_AXIS));
        retiringPlayersPanel.setBackground(Color.GRAY);
        retiringPlayersPanel.setForeground(Color.BLUE);

        JLabel retiringPlayersTitleLabel = getLeagueChampionsLabel("Retiring Players:", Font.PLAIN, 18, SwingConstants.LEFT, Color.BLUE);
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
        for (Team team : Data.world.getAllTeams()){
            team.resetLeagueStats();
            team.resetCupStats();
        }

        for (Player player : Data.world.getAllPlayers()){
            player.resetLeagueStats();
            player.resetCupStats();
        }
    }

    private static JPanel getMainPanel(Team userTeam) {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6, 1));
        mainPanel.setBackground(Color.GRAY);


        // League
        String leagueName = userTeam.getLeague().getName();
        Team LeagueChampions = userTeam.getLeague().getStandings().get(0);
        Player TopGoalscorer = userTeam.getLeague().getTopGoalscorers().get(0);

        JLabel leagueChampionsLabel = getLeagueChampionsLabel(leagueName + " Champions:" + LeagueChampions.getTeamName(), Font.BOLD, 18, SwingConstants.LEFT, LeagueChampions.getTeamColor());
        mainPanel.add(leagueChampionsLabel);

        JLabel leagueTopGoalScorerLabel = getTopGoalScorerLabel(TopGoalscorer, "League");
        mainPanel.add(leagueTopGoalScorerLabel);

        JLabel championsLeagueWinner = getLeagueChampionsLabel("Champions League Winner: " + Data.world.getCupByName("UEFA Champions League").getChampion().getTeamName(), Font.BOLD, 18, SwingConstants.LEFT, Data.world.getCupByName("UEFA Champions League").getChampion().getTeamColor());
        mainPanel.add(championsLeagueWinner);

        JLabel topGoalScorerChampionsLeague = getTopGoalScorerLabel(Data.world.getCupByName("UEFA Champions League").getTopGoalscorer().get(0), "Cup");
        mainPanel.add(topGoalScorerChampionsLeague);

        JLabel ballonDorWinner = getBallonDorWinner();
        mainPanel.add(ballonDorWinner);

        JLabel goldenBoyWinner = getGoldenBoyWinner();
        mainPanel.add(goldenBoyWinner);

        return mainPanel;
    }

    private static JLabel getLeagueChampionsLabel(String leagueName, int bold, int size, int left, Color LeagueChampions) {
        JLabel leagueChampionsLabel = new JLabel(leagueName);
        leagueChampionsLabel.setFont(new Font("Arial", bold, size));
        leagueChampionsLabel.setHorizontalAlignment(left);
        leagueChampionsLabel.setVerticalAlignment(SwingConstants.CENTER);
        leagueChampionsLabel.setBackground(LeagueChampions);
        leagueChampionsLabel.setForeground(Color.WHITE);
        leagueChampionsLabel.setOpaque(true);
        return leagueChampionsLabel;
    }

    private static JLabel getGoldenBoyWinner() {
        Player bestYoungPlayerInWorld = Data.world.getHighestRatedPlayerUnder21();
        JLabel goldenBoyWinner = new JLabel("Golden Boy: " + bestYoungPlayerInWorld.getPlayerName() + ", " + bestYoungPlayerInWorld.getTeam().getTeamName() + "/" + bestYoungPlayerInWorld.getNationality().getNationName());
        goldenBoyWinner.setHorizontalAlignment(SwingConstants.CENTER);
        goldenBoyWinner.setVerticalAlignment(SwingConstants.CENTER);
        goldenBoyWinner.setBackground(Color.BLACK);
        goldenBoyWinner.setForeground(new Color(255, 215, 0)); //Gold
        goldenBoyWinner.setFont(new Font("Arial", Font.BOLD, 18));
        goldenBoyWinner.setOpaque(true);
        return goldenBoyWinner;
    }

    private static JLabel getBallonDorWinner() {
        Player bestPlayerInWorld = Data.world.getHighestRatedPlayer();
        JLabel ballonDorWinner = new JLabel("Ballon d'Or: " + bestPlayerInWorld.getPlayerName() + ", " + bestPlayerInWorld.getTeam().getTeamName() + "/" + bestPlayerInWorld.getNationality().getNationName());
        ballonDorWinner.setHorizontalAlignment(SwingConstants.CENTER);
        ballonDorWinner.setVerticalAlignment(SwingConstants.CENTER);
        ballonDorWinner.setBackground(new Color(255,215,0)); //Gold
        ballonDorWinner.setForeground(Color.BLACK);
        ballonDorWinner.setFont(new Font("Arial", Font.BOLD, 18));
        ballonDorWinner.setOpaque(true);
        return ballonDorWinner;
    }

    private static JLabel getTopGoalScorerLabel(Player TopGoalscorer, String tournamentType) {


        JLabel topGoalScorerLabel = getGoalScorerLabel(TopGoalscorer, tournamentType);
        topGoalScorerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        topGoalScorerLabel.setHorizontalAlignment(SwingConstants.LEFT);
        topGoalScorerLabel.setVerticalAlignment(SwingConstants.CENTER);
        topGoalScorerLabel.setBackground(TopGoalscorer.getTeam().getTeamColor());
        topGoalScorerLabel.setForeground(Color.WHITE);
        topGoalScorerLabel.setOpaque(true);
        return topGoalScorerLabel;
    }

    private static JLabel getGoalScorerLabel(Player TopGoalscorer, String tournamentType) {
        JLabel topGoalScorerLabel;

        if (tournamentType.equals("League")){
            topGoalScorerLabel = new JLabel("Top Goal Scorer: " + TopGoalscorer.getPlayerName() + ", " + TopGoalscorer.getLeagueGoals() + " goals");
        } else if (tournamentType.equals("Cup")){
            topGoalScorerLabel = new JLabel("Top Goal Scorer: " + TopGoalscorer.getPlayerName() + ", " + TopGoalscorer.getCupGoals() + " goals");
        } else {
            topGoalScorerLabel = new JLabel("Unknown tournament type");
        }
        return topGoalScorerLabel;
    }

    private static JLabel getRetiringPlayerLabel(Player player) {
        return getLeagueChampionsLabel(player.getPlayerName() + ", " + player.getAge() + ", " + player.getPosition() + ", " + player.getTeam().getShortName(), Font.PLAIN, 12, SwingConstants.LEFT, player.getTeam().getTeamColor());
    }
}
