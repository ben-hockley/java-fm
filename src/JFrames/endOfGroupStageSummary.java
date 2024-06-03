package JFrames;

import Objects.Cup;
import Objects.Team;
import data.Data;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class endOfGroupStageSummary extends JFrame {

    public endOfGroupStageSummary(Team userTeam){
        setTitle("UCL: End of Group Stage Summary");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        //END OF GROUP STAGE BACK-END FUNCTIONS.

        for (Team team : Data.world.getCupByName("UEFA Champions League").getTeams()){
            team.resetCupStats();
        }


        //get arrayList of all the teams that finished 1st or 2nd in their group, so qualify for the knockout stage.
        ArrayList<Team> teamsAdvancingToKnockouts = new ArrayList<>();
        for (Team team : Data.world.getCupByName("UEFA Champions League").getTeams()){
            if (team.getChampionsLeagueGroupStandings().indexOf(team) < 2){
                //advance team to knockout if they finished first or second in their group.
                teamsAdvancingToKnockouts.add(team);
            }
        }

        //sorts teams by group position (Group winners first).
        teamsAdvancingToKnockouts.sort((team1, team2) -> {
            int team1GroupPosition = team1.getChampionsLeagueGroupStandings().indexOf(team1);
            int team2GroupPosition = team2.getChampionsLeagueGroupStandings().indexOf(team2);

            if (team1GroupPosition < team2GroupPosition){
                return -1;
            } else if (team1GroupPosition > team2GroupPosition){
                return 1;
            } else {
                return 0;
            }
        });

        JLabel titleLabel = new JLabel();
        titleLabel.setPreferredSize(new Dimension(800, 100));
        titleLabel.setText("UCL: End of Group Stage Summary");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBackground(new Color(14,32,80));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        titleLabel.setOpaque(true);
        add(titleLabel, BorderLayout.NORTH);

        JPanel teamsAdvancingPanel = new JPanel();
        teamsAdvancingPanel.setPreferredSize(new Dimension(300,600));
        teamsAdvancingPanel.setLayout(new BoxLayout(teamsAdvancingPanel, BoxLayout.Y_AXIS));
        teamsAdvancingPanel.setBackground(new Color(14,32,80));
        teamsAdvancingPanel.setForeground(Color.WHITE);
        teamsAdvancingPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        JLabel teamsAdvancingTitleLabel = new JLabel();
        teamsAdvancingTitleLabel.setText("Teams Advancing to Knockouts:");
        teamsAdvancingTitleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        teamsAdvancingTitleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        teamsAdvancingTitleLabel.setVerticalAlignment(SwingConstants.CENTER);
        teamsAdvancingTitleLabel.setBackground(new Color(14,32,80));
        teamsAdvancingTitleLabel.setForeground(Color.WHITE);
        teamsAdvancingTitleLabel.setOpaque(true);
        teamsAdvancingPanel.add(teamsAdvancingTitleLabel);

        for (Team team : teamsAdvancingToKnockouts){
            JLabel teamLabel = new JLabel();
            int groupPosition = team.getChampionsLeagueGroupStandings().indexOf(team) + 1;

            teamLabel.setText(team.getShortName() + " (Group "+ team.getChampionsLeagueGroupLetter() + (groupPosition == 1 ? " Winner)" : " Runner-Up)"));
            teamLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            teamLabel.setHorizontalAlignment(SwingConstants.LEFT);
            teamLabel.setVerticalAlignment(SwingConstants.CENTER);
            teamLabel.setBackground(team.getTeamColor());
            teamLabel.setForeground(Color.WHITE);
            teamLabel.setOpaque(true);
            teamsAdvancingPanel.add(teamLabel);
        }

        JScrollPane scrollableTeamsAdvancingPanel = new JScrollPane(teamsAdvancingPanel);
        scrollableTeamsAdvancingPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollableTeamsAdvancingPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(scrollableTeamsAdvancingPanel, BorderLayout.WEST);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6, 1));
        mainPanel.setBackground(new Color(14,32,80));

        JLabel topScorerLabel = new JLabel();
        Cup activeCup = Data.world.getCupByName("UEFA Champions League");

        StringBuilder topGoalscorersList = new StringBuilder();

        if (activeCup.getTopGoalscorer().size() == 1){
            topGoalscorersList.append("Top Goalscorer: ");
        } else {
            topGoalscorersList.append("Top Goalscorers: ");
        }

        for (int i = 0; i < activeCup.getTopGoalscorer().size(); i++){
            topGoalscorersList.append(activeCup.getTopGoalscorer().get(i).getPlayerName()).append(", ").append(activeCup.getTopGoalscorer().get(i).getTeam().getShortName()).append(" ; ");
        }



        topScorerLabel.setText(topGoalscorersList + " (" + activeCup.getTopGoalscorer().get(0).getCupGoals() + " goals)");
        topScorerLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        topScorerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topScorerLabel.setVerticalAlignment(SwingConstants.CENTER);
        topScorerLabel.setBackground(new Color(14,32,80));
        topScorerLabel.setForeground(Color.WHITE);
        topScorerLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        topScorerLabel.setOpaque(true);

        JScrollPane scrollableTopScorerLabel = new JScrollPane(topScorerLabel);
        scrollableTopScorerLabel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollableTopScorerLabel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        mainPanel.add(scrollableTopScorerLabel);
        //add mainPanel to the JFrame
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }
}
