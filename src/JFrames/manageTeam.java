package JFrames;

import Objects.Player;
import Objects.Team;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class manageTeam extends JFrame {

    public manageTeam(Team userTeam) {
        this.setSize(500,800);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Manage Team");
        this.setLayout(new GridLayout(1,2));

        JPanel startingElevenPanel = new JPanel();

        startingElevenPanel.setLayout(new GridLayout(13,1));
        startingElevenPanel.add(new JLabel("Starting 11:"));

        ButtonGroup startingEleven = new ButtonGroup();

        for (Player player : userTeam.getStartingEleven()) {
            JRadioButton playerButton = new JRadioButton(player.getPlayerName());

            if (Objects.equals(player.getPosition(), "GK")){
                playerButton.setBackground(Color.ORANGE);
            } else if (Objects.equals(player.getPosition(), "DEF")){
                playerButton.setBackground(Color.YELLOW);
            } else if (Objects.equals(player.getPosition(), "MID")){
                playerButton.setBackground(Color.GREEN);
            } else if (Objects.equals(player.getPosition(), "FWD")){
                playerButton.setBackground(Color.CYAN);
            }

            playerButton.setActionCommand(player.getPlayerName());
            startingEleven.add(playerButton);
            startingElevenPanel.add(playerButton);
        }

        startingElevenPanel.setOpaque(true);

        JPanel benchPanel = new JPanel();
        benchPanel.setLayout(new GridLayout(13,1));
        benchPanel.add(new JLabel("Substitutes:"));

        ButtonGroup substitutes = new ButtonGroup();

        for (Player player : userTeam.getSubstitutes()) {
            JRadioButton playerButton = new JRadioButton(player.getPlayerName());

            if (Objects.equals(player.getPosition(), "GK")){
                playerButton.setBackground(Color.ORANGE);
            } else if (Objects.equals(player.getPosition(), "DEF")){
                playerButton.setBackground(Color.YELLOW);
            } else if (Objects.equals(player.getPosition(), "MID")){
                playerButton.setBackground(Color.GREEN);
            } else if (Objects.equals(player.getPosition(), "FWD")){
                playerButton.setBackground(Color.CYAN);
            }

            playerButton.setActionCommand(player.getPlayerName());
            substitutes.add(playerButton);
            benchPanel.add(playerButton);
        }

        Button substitutePlayer = new Button("Substitute Player");
        substitutePlayer.setBackground(Color.BLUE);
        substitutePlayer.setForeground(Color.WHITE);
        substitutePlayer.addActionListener(e -> {
            try {
                if (userTeam.getPlayerByName(startingEleven.getSelection().getActionCommand()).getPosition().equals(userTeam.getPlayerByName(substitutes.getSelection().getActionCommand()).getPosition())){
                    userTeam.makeSubstitution(userTeam.getPlayerByName(startingEleven.getSelection().getActionCommand()), userTeam.getPlayerByName(substitutes.getSelection().getActionCommand()));
                    this.dispose();

                    new manageTeam(userTeam);
                } else {
                    throw new IllegalArgumentException("Players must be the same position to make a substitution.");
                }
            } catch (IllegalArgumentException exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage());
            }

        });

        startingElevenPanel.add(substitutePlayer);

        this.add(startingElevenPanel);
        this.add(benchPanel);

        this.setVisible(true);
    }
}
