package JFrames;

import Objects.Player;
import Objects.Team;
import data.Data;
import events.Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class endOfSemiFinalsSummary extends JFrame {

    public endOfSemiFinalsSummary(ArrayList<Team> finalTeams, UI mainMenu, Team userTeam){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("UCL Semi Final Summary");
        setSize(800, 600);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Semi Final Summary");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setBackground(new Color(14, 32, 80));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setOpaque(true);

        add(titleLabel, BorderLayout.NORTH);

        JPanel topScorersPanel = new JPanel();
        topScorersPanel.setPreferredSize(new Dimension(200, 600));
        topScorersPanel.setLayout(new GridLayout(9, 1));

        JLabel topScorersTitleLabel = new JLabel("Top Scorers:");
        topScorersTitleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topScorersTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topScorersTitleLabel.setVerticalAlignment(SwingConstants.CENTER);
        topScorersTitleLabel.setBackground(new Color(14, 32, 80));
        topScorersTitleLabel.setForeground(Color.WHITE);
        topScorersTitleLabel.setOpaque(true);

        topScorersPanel.add(topScorersTitleLabel);
        ArrayList<Player> topGoalscorers = Data.world.getCupByName("UEFA Champions League").getPlayersByGoalsScored();

        for (int i=0; i<8; i++){
            Player player = topGoalscorers.get(i);
            JLabel playerLabel = new JLabel(player.getPlayerName() + " - " + player.getCupGoals() + " Goals");
            playerLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            playerLabel.setForeground(Color.WHITE);
            playerLabel.setBackground(new Color(14, 32, 80));
            playerLabel.setOpaque(true);

            topScorersPanel.add(playerLabel);
        }

        add(topScorersPanel,BorderLayout.EAST);

        JPanel mainPanelChampionsLeagueFinalTeams = new JPanel(new GridLayout(1, 2));

        Team homeTeam = finalTeams.get(0);
        Team awayTeam = finalTeams.get(1);


        JLabel homeTeamLabel = new JLabel(homeTeam.getTeamName());
        homeTeamLabel.setVerticalAlignment(SwingConstants.CENTER);
        homeTeamLabel.setHorizontalAlignment(SwingConstants.CENTER);
        homeTeamLabel.setBackground(homeTeam.getTeamColor());
        homeTeamLabel.setForeground(Color.WHITE);
        homeTeamLabel.setIcon(new ImageIcon(new ImageIcon("teamImages/" + homeTeam.getTeamLogo()).getImage().getScaledInstance(110,144, Image.SCALE_SMOOTH)));
        homeTeamLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        homeTeamLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        homeTeamLabel.setFont(new Font("Arial", Font.BOLD, 24));
        homeTeamLabel.setOpaque(true);

        JLabel awayTeamLabel = new JLabel(awayTeam.getTeamName());
        awayTeamLabel.setVerticalAlignment(SwingConstants.CENTER);
        awayTeamLabel.setHorizontalAlignment(SwingConstants.CENTER);
        awayTeamLabel.setBackground(awayTeam.getTeamColor());
        awayTeamLabel.setForeground(Color.WHITE);
        awayTeamLabel.setIcon(new ImageIcon(new ImageIcon("teamImages/" + awayTeam.getTeamLogo()).getImage().getScaledInstance(110,144, Image.SCALE_SMOOTH)));
        awayTeamLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        awayTeamLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        awayTeamLabel.setFont(new Font("Arial", Font.BOLD, 24));
        awayTeamLabel.setOpaque(true);

        mainPanelChampionsLeagueFinalTeams.add(homeTeamLabel);
        mainPanelChampionsLeagueFinalTeams.add(awayTeamLabel);

        add(mainPanelChampionsLeagueFinalTeams, BorderLayout.CENTER);

        setVisible(true);

        Game championsLeagueFinal = new Game(homeTeam, awayTeam, Data.listOfCupDates[12], "Cup");

        ArrayList<Game> championsLeagueFinalList = new ArrayList<>(1);
        championsLeagueFinalList.add(championsLeagueFinal);

        mainMenu.addRoundOfChampionsLeagueFixtures(championsLeagueFinalList);

        if (homeTeam.equals(userTeam) || awayTeam.equals(userTeam)){
            mainMenu.addUserGame(championsLeagueFinal);
        }
    }
}
