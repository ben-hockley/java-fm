package JFrames;

import Objects.Player;
import Objects.Team;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class gameSimulator extends JFrame {

    public gameSimulator(Team homeTeam, Team awayTeam) {
        this.setSize(500,500);
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
            Player scorer = homeTeam.bestStartingEleven()[(int)Math.floor(Math.random() * 10) + 1];
            homeGoalscorers.add(scorer.getPlayerName() + "  '" + (int)(Math.random() * 90));
        }

        for (String goal : homeGoalscorers) {
            System.out.println(goal);
        }
        System.out.println();

        for (int i = 0; i < awayGoals; i++) {
            Player scorer = awayTeam.bestStartingEleven()[(int)Math.floor(Math.random() * 10) + 1];
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

        JLabel homeGoalscorersLabel = new JLabel();
        homeGoalscorersLabel.setLayout(new GridLayout(homeGoals, 1));
        homeGoalscorersLabel.setPreferredSize(new Dimension(200, homeGoals*100));
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

        pitch.add(homeGoalscorersLabel, BorderLayout.WEST);

        JLabel awayGoalscorersLabel = new JLabel();
        awayGoalscorersLabel.setLayout(new GridLayout(awayGoals, 1));
        awayGoalscorersLabel.setPreferredSize(new Dimension(200, awayGoals*100));
        awayGoalscorersLabel.setBackground(Color.WHITE);
        awayGoalscorersLabel.setForeground(Color.BLACK);
        awayGoalscorersLabel.setOpaque(true);
        pitch.add(awayGoalscorersLabel, BorderLayout.EAST);

        for (String goal : awayGoalscorers) {
            JLabel goalLabel = new JLabel(goal);
            goalLabel.setHorizontalAlignment(SwingConstants.CENTER);
            goalLabel.setVerticalAlignment(SwingConstants.CENTER);
            awayGoalscorersLabel.add(goalLabel);
        }

        pitch.add(awayGoalscorersLabel, BorderLayout.EAST);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
}
