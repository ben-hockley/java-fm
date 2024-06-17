package JFrames;

import objects.Player;
import objects.Team;
import data.Data;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class endOfSeasonSummary extends JFrame {

    Team championshipWinners;
    Team championsShipRunnersUp;
    Team championshipPlayoffWinners;

    Team premierLeague20th;
    Team premierLeague19th;
    Team premierLeague18th;
    public endOfSeasonSummary(Team userTeam) {
        setTitle("End of Season Summary");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        //END OF SEASON BACK-END FUNCTIONS.

        //promotion and relegation (english league)


        //promote three random teams from the championship to the premier league.
        ArrayList<Team> randomisedChampionshipStandings = Data.england.getLeagueByTier(2).shuffleStandings();

        championshipWinners = randomisedChampionshipStandings.get(0);
        championsShipRunnersUp = randomisedChampionshipStandings.get(1);
        championshipPlayoffWinners = randomisedChampionshipStandings.get(2);

        championshipWinners.promote();
        championsShipRunnersUp.promote();
        championshipPlayoffWinners.promote();

        //relegate bottom 3 teams from premier league to the championship
        premierLeague20th = Data.england.getLeagueByTier(1).getStandings().get(19);
        premierLeague19th = Data.england.getLeagueByTier(1).getStandings().get(18);
        premierLeague18th = Data.england.getLeagueByTier(1).getStandings().get(17);

        premierLeague18th.relegate();
        premierLeague19th.relegate();
        premierLeague20th.relegate();

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
            if (player.getRating() < 70){
                player.retirePlayer(); //retire player if they are not good enough anymore
                retiringPlayers.add(player);
            } else if (player.getAge() >= 35){
                int retirementChance = (int) Math.round(Math.random());

                if (retirementChance == 1){
                    //removes the player and replaces them with a regen
                    player.retirePlayer();
                    retiringPlayers.add(player);
                }
            }
        }
        //reset every club and national team's starting 11 and subs to account
        //for retiring players.

        //reset every team's transfer budget to their initial transfer budget.
        for (Team team : Data.world.getAllTeams()){
            team.resetTransferBudget();
        }


        // CONTENTS OF JFRAME
        setLayout(new BorderLayout());

        JLabel titleLabel = getHeadingLabel("End of Season Summary", Font.BOLD, 24, SwingConstants.CENTER, Color.BLUE);
        titleLabel.setIcon(new ImageIcon(new ImageIcon("teamImages/" + userTeam.getTeamLogo()).getImage().getScaledInstance(55,72, Image.SCALE_SMOOTH)));

        titleLabel.setPreferredSize(new Dimension(800, 50));
        this.add(titleLabel, BorderLayout.NORTH);

        JPanel retiringPlayersPanel = new JPanel();
        retiringPlayersPanel.setLayout(new BoxLayout(retiringPlayersPanel, BoxLayout.Y_AXIS));
        retiringPlayersPanel.setBackground(Color.GRAY);
        retiringPlayersPanel.setForeground(Color.BLUE);

        JLabel retiringPlayersTitleLabel = getHeadingLabel("Retiring Players:", Font.PLAIN, 18, SwingConstants.LEFT, Color.BLUE);
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
            player.resetSeasonStats();
        }

        for (Team team : Data.world.getAllTeams()) {
            team.setDefaultStartingElevenandSubs();
        }

        if (userTeam.getTeamType().equals("International")){
            for (Player nationalPlayer : userTeam.getLeague().getAllPlayers()){
                nationalPlayer.resetSeasonStats();
            }

            for (Team nationalTeam : userTeam.getLeague().getAllTeams()){
                nationalTeam.resetLeagueStats();
                nationalTeam.wipeFixtures();
            }
        }
        //set default starting 11 and subs for all international teams.
        for (Team team : Data.international.getLeagueByTier(1).getAllTeams()) {

            team.updateBestSquad();
            team.setDefaultStartingElevenandSubs();
        }
    }

    private JPanel getMainPanel(Team userTeam) {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(8, 1));
        mainPanel.setBackground(Color.GRAY);

        // League
        String leagueName = userTeam.getLeague().getName();
        Team LeagueChampions = userTeam.getLeague().getStandings().get(0);
        Team ChampionsLeagueWinner = Data.world.getCupByName("UEFA Champions League").getChampion();
        Player LeagueTopGoalscorer = userTeam.getLeague().getTopGoalscorers().get(0);
        Player ChampionsLeagueTopGoalscorer = Data.world.getCupByName("UEFA Champions League").getTopGoalscorer().get(0);

        JLabel leagueChampionsLabel = getHeadingLabel(leagueName + " Champions:" + LeagueChampions.getTeamName(), Font.BOLD, 18, SwingConstants.LEFT, LeagueChampions.getTeamColor());
        leagueChampionsLabel.setIcon(new ImageIcon(new ImageIcon("teamImages/" + LeagueChampions.getTeamLogo()).getImage().getScaledInstance(55,72, Image.SCALE_SMOOTH)));
        leagueChampionsLabel.setHorizontalTextPosition(SwingConstants.LEFT);
        leagueChampionsLabel.setIconTextGap(50);

        JLabel leagueTopGoalScorerLabel = getFormattedTopGoalScorerLabel(LeagueTopGoalscorer, "League");


        JLabel leagueRelegatedTeamsLabel = new JLabel();
        JLabel teamsPromotedToLeagueLabel = new JLabel();
        if (userTeam.getTeamType().equals("Club")){
            leagueRelegatedTeamsLabel = getRelegatedTeamsLabel();
            leagueRelegatedTeamsLabel.setIcon(new ImageIcon(new ImageIcon("teamImages/" + Data.england.getLeagueByTier(2).getLeagueLogo()).getImage().getScaledInstance(55,72, Image.SCALE_SMOOTH)));
            teamsPromotedToLeagueLabel = getHeadingLabel("Teams Promoted to " + leagueName + ": " + championshipWinners.getTeamName() + ", " + championsShipRunnersUp.getTeamName() + ", " + championshipPlayoffWinners.getTeamName(), Font.BOLD, 12, SwingConstants.LEFT, Color.GREEN);
            teamsPromotedToLeagueLabel.setIcon(new ImageIcon(new ImageIcon("teamImages/" + Data.england.getLeagueByTier(1).getLeagueLogo()).getImage().getScaledInstance(55,72, Image.SCALE_SMOOTH)));
        }

        JLabel championsLeagueWinnerLabel = getHeadingLabel("Champions League Winner: " + ChampionsLeagueWinner.getTeamName(), Font.BOLD, 18, SwingConstants.LEFT, ChampionsLeagueWinner.getTeamColor());
        championsLeagueWinnerLabel.setIcon(new ImageIcon(new ImageIcon("teamImages/" + ChampionsLeagueWinner.getTeamLogo()).getImage().getScaledInstance(55,72, Image.SCALE_SMOOTH)));
        championsLeagueWinnerLabel.setHorizontalTextPosition(SwingConstants.LEFT);
        championsLeagueWinnerLabel.setIconTextGap(50);

        JLabel topGoalScorerChampionsLeague = getFormattedTopGoalScorerLabel(ChampionsLeagueTopGoalscorer, "Cup");
        JLabel ballonDorWinnerLabel = getBallonDorWinnerLabel();
        JLabel goldenBoyWinnerLabel = getGoldenBoyWinnerLabel();

        mainPanel.add(leagueChampionsLabel);
        mainPanel.add(leagueTopGoalScorerLabel);

        if (userTeam.getTeamType().equals("Club")){
            mainPanel.add(leagueRelegatedTeamsLabel);
            mainPanel.add(teamsPromotedToLeagueLabel);
            mainPanel.add(championsLeagueWinnerLabel);
            mainPanel.add(topGoalScorerChampionsLeague);
        }
        mainPanel.add(ballonDorWinnerLabel);
        mainPanel.add(goldenBoyWinnerLabel);

        return mainPanel;
    }

    private JLabel getHeadingLabel(String leagueName, int bold, int size, int left, Color LeagueChampions) {
        JLabel newJLabel = new JLabel(leagueName);
        newJLabel.setFont(new Font("Arial", bold, size));
        newJLabel.setHorizontalAlignment(left);
        newJLabel.setVerticalAlignment(SwingConstants.CENTER);
        newJLabel.setBackground(LeagueChampions);
        newJLabel.setForeground(Color.WHITE);
        newJLabel.setOpaque(true);
        return newJLabel;
    }

    private JLabel getGoldenBoyWinnerLabel() {
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

    private JLabel getBallonDorWinnerLabel() {
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

    private JLabel getFormattedTopGoalScorerLabel(Player TopGoalscorer, String tournamentType) {


        JLabel topGoalScorerLabel = getTopGoalScorerLabel(TopGoalscorer, tournamentType);
        topGoalScorerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        topGoalScorerLabel.setIcon(new ImageIcon(new ImageIcon("teamImages/" + TopGoalscorer.getTeam().getTeamLogo()).getImage().getScaledInstance(55,72, Image.SCALE_SMOOTH)));
        topGoalScorerLabel.setHorizontalTextPosition(SwingConstants.LEFT);
        topGoalScorerLabel.setIconTextGap(50);
        topGoalScorerLabel.setHorizontalAlignment(SwingConstants.LEFT);
        topGoalScorerLabel.setVerticalAlignment(SwingConstants.CENTER);
        topGoalScorerLabel.setBackground(TopGoalscorer.getTeam().getTeamColor());
        topGoalScorerLabel.setForeground(Color.WHITE);
        topGoalScorerLabel.setOpaque(true);
        return topGoalScorerLabel;
    }

    private JLabel getTopGoalScorerLabel(Player TopGoalscorer, String tournamentType) {
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

    private JLabel getRelegatedTeamsLabel() {
        return getHeadingLabel("Premier League Relegated Teams: " + premierLeague20th.getTeamName() + ", " + premierLeague19th.getTeamName() + ", " + premierLeague18th.getTeamName(), Font.BOLD, 12, SwingConstants.LEFT, Color.RED);
    }

    private JLabel getRetiringPlayerLabel(Player player) {
        return getHeadingLabel(player.getPlayerName() + ", " + player.getAge() + ", " + player.getPosition() + ", " + player.getTeam().getShortName(), Font.PLAIN, 12, SwingConstants.LEFT, player.getTeam().getTeamColor());
    }
}
