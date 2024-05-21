package JFrames;

import Objects.Player;
import Objects.Team;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class gameSimulator extends JFrame {

    public gameSimulator(Team homeTeam, Team awayTeam) {
        this.setSize(500,800);
        this.setTitle(homeTeam.getTeamName() + " vs " + awayTeam.getTeamName());
        JPanel pitch = new JPanel(new BorderLayout());
        pitch.setBackground(new Color(40, 140, 40));
        pitch.setOpaque(true);
        this.add(pitch);

        Integer homeRating = homeTeam.getRating();
        Integer awayRating = awayTeam.getRating();
        //home advantage
        homeRating += 40;

        double homeRandomValue = Math.random();
        double awayRandomValue = Math.random();

        int homeGoals = (int)Math.round(homeRating * homeRandomValue / 75);
        int awayGoals = (int)Math.round(awayRating * awayRandomValue / 75);

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
            Player scorer;

            double scorerGenerator = Math.random();
            // 60% chance the scorer is a forward (20% chance for each forward)
            if (scorerGenerator >= 0.8) {
                scorer = homeTeam.getStartingEleven()[10]; //FWD 1
            } else if (scorerGenerator >= 0.6) {
                scorer = homeTeam.getStartingEleven()[9]; //FWD 2
            } else if (scorerGenerator >= 0.4) {
                scorer = homeTeam.getStartingEleven()[8]; //FWD 3
            } else if (scorerGenerator >= 0.3) {
                scorer = homeTeam.getStartingEleven()[7]; //MID 1
            } else if (scorerGenerator >= 0.2) {
                scorer = homeTeam.getStartingEleven()[6]; //MID 2
            } else if (scorerGenerator >= 0.1) {
                scorer = homeTeam.getStartingEleven()[5]; //MID 3
            } else {
                int randomIntBetween1And4 = (int)Math.floor(Math.random()*4) + 1;
                scorer = homeTeam.getStartingEleven()[randomIntBetween1And4]; //DEFENDER
            }
            // 30% chance the scorer is a midfielder (10% chance for each midfielder)

            // 10% chance the scorer is a defender (2.5% chance for each defender)
            homeGoalscorers.add(scorer.getPlayerName() + "  '" + (int)(Math.random() * 98 + 1));
        }

        for (int i = 0; i < awayGoals; i++) {
            Player scorer;

            double scorerGenerator = Math.random();
            // 60% chance the scorer is a forward (20% chance for each forward)
            if (scorerGenerator >= 0.8) {
                scorer = awayTeam.getStartingEleven()[10]; //FWD 1
            } else if (scorerGenerator >= 0.6) {
                scorer = awayTeam.getStartingEleven()[9]; //FWD 2
            } else if (scorerGenerator >= 0.4) {
                scorer = awayTeam.getStartingEleven()[8]; //FWD 3
            } else if (scorerGenerator >= 0.3) {
                scorer = awayTeam.getStartingEleven()[7]; //MID 1
            } else if (scorerGenerator >= 0.2) {
                scorer = awayTeam.getStartingEleven()[6]; //MID 2
            } else if (scorerGenerator >= 0.1) {
                scorer = awayTeam.getStartingEleven()[5]; //MID 3
            } else {
                scorer = awayTeam.getStartingEleven()[(int)Math.floor(Math.random())*4 + 1]; //DEFENDER
            }
            // 30% chance the scorer is a midfielder (10% chance for each midfielder)

            // 10% chance the scorer is a defender (2.5% chance for each defender)
            awayGoalscorers.add(scorer.getPlayerName() + "  '" + (int)(Math.random() * 99));
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
        homeTeamLabel.setLayout(new GridLayout(1,1));
        homeTeamLabel.setPreferredSize(new Dimension(230, 500));

        JLabel homeGoalscorersLabel = new JLabel();
        homeGoalscorersLabel.setLayout(new GridLayout(homeGoals, 1));
        homeGoalscorersLabel.setBackground(Color.WHITE);
        homeGoalscorersLabel.setForeground(Color.BLACK);
        homeGoalscorersLabel.setOpaque(true);

        homeGoalscorers.sort((a, b) -> {
            String[] aSplit = a.split("'");
            String[] bSplit = b.split("'");
            return Integer.parseInt(aSplit[1]) - Integer.parseInt(bSplit[1]);
        });

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

        pitch.add(homeTeamLabel, BorderLayout.WEST);


        JLabel awayTeamLabel = new JLabel();
        awayTeamLabel.setLayout(new GridLayout(1,1));
        awayTeamLabel.setPreferredSize(new Dimension(230, 500));

        JLabel awayGoalscorersLabel = new JLabel();
        awayGoalscorersLabel.setLayout(new GridLayout(awayGoals, 1));
        awayGoalscorersLabel.setPreferredSize(new Dimension(200, awayGoals*100));
        awayGoalscorersLabel.setBackground(Color.WHITE);
        awayGoalscorersLabel.setForeground(Color.BLACK);
        awayGoalscorersLabel.setOpaque(true);

        awayGoalscorers.sort((a, b) -> {
            String[] aSplit = a.split("'");
            String[] bSplit = b.split("'");
            return Integer.parseInt(aSplit[1]) - Integer.parseInt(bSplit[1]);
        });

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

        pitch.add(awayTeamLabel, BorderLayout.EAST);

        JLabel goalScorersLabel = new JLabel();
        goalScorersLabel.setLayout(new GridLayout(1,2));
        goalScorersLabel.setPreferredSize(new Dimension(500, 100));
        goalScorersLabel.add(homeGoalscorersLabel);
        goalScorersLabel.add(awayGoalscorersLabel);

        goalScorersLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pitch.add(goalScorersLabel, BorderLayout.SOUTH);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
}
