package JFrames;

import Objects.Player;
import Objects.Team;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class gameSimulator extends JFrame {

    public gameSimulator(Team homeTeam, Team awayTeam) {
        this.setSize(500,800);
        JPanel pitch = new JPanel(new BorderLayout());
        pitch.setBackground(new Color(40, 140, 40));
        pitch.setOpaque(true);
        this.add(pitch);

        Integer homeRating = homeTeam.getRating();
        Integer awayRating = awayTeam.getRating();
        //home advantage
        homeRating += 250;

        int homeGoals = (int)(homeRating * Math.random() / 200);
        int awayGoals = (int)(awayRating * Math.random() / 200);

        if (homeGoals > awayGoals) {
            //home win
            homeTeam.addWin();
            awayTeam.addLoss();
        } else if (awayGoals > homeGoals) {
            //away win
            homeTeam.addLoss();
            awayTeam.addWin();
        } else {
            //draw
            homeTeam.addDraw();
            awayTeam.addDraw();
        }

        ArrayList<String> homeGoalscorers = new ArrayList<>();
        ArrayList<String> awayGoalscorers = new ArrayList<>();

        for (int i = 0; i < homeGoals; i++) {
            Player scorer = homeTeam.getStartingEleven()[(int)Math.floor(Math.random() * 10) + 1];
            homeGoalscorers.add(scorer.getPlayerName() + "  '" + (int)(Math.random() * 90));
        }

        for (String goal : homeGoalscorers) {
            System.out.println(goal);
        }
        System.out.println();

        for (int i = 0; i < awayGoals; i++) {
            Player scorer = awayTeam.getStartingEleven()[(int)Math.floor(Math.random() * 10) + 1];
            awayGoalscorers.add(scorer.getPlayerName() + "  '" + (int)(Math.random() * 90));
        }

        for (String goal : awayGoalscorers) {
            System.out.println(goal);
        }


        JLabel score = new JLabel(homeTeam.getTeamName() + " " + homeGoals + " - " + awayGoals + " " + awayTeam.getTeamName());
        score.setFont(new Font("Arial", Font.PLAIN, 20));
        score.setHorizontalAlignment(SwingConstants.CENTER);
        score.setVerticalAlignment(SwingConstants.CENTER);
        score.setBackground(Color.WHITE);
        score.setForeground(Color.BLACK);
        score.setOpaque(true);
        pitch.add(score, BorderLayout.NORTH);


        JLabel homeTeamLabel = new JLabel();
        homeTeamLabel.setLayout(new GridLayout(2,1));
        homeTeamLabel.setPreferredSize(new Dimension(230, 500));

        JLabel homeGoalscorersLabel = new JLabel();
        homeGoalscorersLabel.setLayout(new GridLayout(homeGoals, 1));
        homeGoalscorersLabel.setBackground(Color.WHITE);
        homeGoalscorersLabel.setForeground(Color.BLACK);
        homeGoalscorersLabel.setOpaque(true);

        for (String goal : homeGoalscorers) {
            JLabel goalLabel = new JLabel(goal);
            goalLabel.setHorizontalAlignment(SwingConstants.CENTER);
            goalLabel.setVerticalAlignment(SwingConstants.CENTER);
            goalLabel.setForeground(Color.BLACK);
            homeGoalscorersLabel.add(goalLabel);
        }

        JLabel homeLineupLabel = new JLabel();
        homeLineupLabel.setLayout(new GridLayout(20, 1));
        homeLineupLabel.setBackground(Color.WHITE);
        homeLineupLabel.setForeground(Color.BLACK);
        homeLineupLabel.setOpaque(true);
        homeLineupLabel.add(new JLabel("Formation: " + homeTeam.getFormationInText()));
        Player[] homeStarting11 = homeTeam.getStartingEleven();
        for (Player player : homeStarting11) {
            homeLineupLabel.add(new JLabel(player.getPlayerName() + " - " + player.getPosition() + " - " + player.getRating()));
        }
        Player[] homeSubs = homeTeam.getSubstitutes();
        homeLineupLabel.add(new JLabel("Substitutes:"));
        for (Player player : homeSubs) {
            homeLineupLabel.add(new JLabel(player.getPlayerName() + " - " + player.getPosition() + " - " + player.getRating()));
        }

        homeTeamLabel.add(homeLineupLabel);
        homeTeamLabel.add(homeGoalscorersLabel);

        pitch.add(homeTeamLabel, BorderLayout.WEST);


        JLabel awayTeamLabel = new JLabel();
        awayTeamLabel.setLayout(new GridLayout(2,1));
        awayTeamLabel.setPreferredSize(new Dimension(230, 500));

        JLabel awayGoalscorersLabel = new JLabel();
        awayGoalscorersLabel.setLayout(new GridLayout(awayGoals, 1));
        awayGoalscorersLabel.setPreferredSize(new Dimension(200, awayGoals*100));
        awayGoalscorersLabel.setBackground(Color.WHITE);
        awayGoalscorersLabel.setForeground(Color.BLACK);
        awayGoalscorersLabel.setOpaque(true);

        for (String goal : awayGoalscorers) {
            JLabel goalLabel = new JLabel(goal);
            goalLabel.setHorizontalAlignment(SwingConstants.CENTER);
            goalLabel.setVerticalAlignment(SwingConstants.CENTER);
            awayGoalscorersLabel.add(goalLabel);
        }

        JLabel awayLineupLabel = new JLabel();
        awayLineupLabel.setLayout(new GridLayout(20, 1));
        awayLineupLabel.setBackground(Color.WHITE);
        awayLineupLabel.setForeground(Color.BLACK);
        awayLineupLabel.setOpaque(true);
        awayLineupLabel.add(new JLabel("Formation: " + awayTeam.getFormationInText()));
        Player[] awayStarting11 = awayTeam.getStartingEleven();
        for (Player player : awayStarting11) {
            awayLineupLabel.add(new JLabel(player.getPlayerName() + " - " + player.getPosition() + " - " + player.getRating()));
        }
        Player[] awaySubs = awayTeam.getSubstitutes();
        awayLineupLabel.add(new JLabel("Substitutes:"));
        for (Player player : awaySubs) {
            awayLineupLabel.add(new JLabel(player.getPlayerName() + " - " + player.getPosition() + " - " + player.getRating()));
        }

        awayTeamLabel.add(awayLineupLabel);
        awayTeamLabel.add(awayGoalscorersLabel);

        pitch.add(awayTeamLabel, BorderLayout.EAST);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
}
