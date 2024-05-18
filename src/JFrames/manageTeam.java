package JFrames;

import Objects.Player;
import Objects.Team;

import javax.swing.*;
import java.awt.*;

public class manageTeam extends JFrame {

    public manageTeam(Team userTeam) {
        this.setSize(500,800);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new GridLayout(1,2));

        JPanel startingElevenPanel = new JPanel();
        startingElevenPanel.setBackground(new java.awt.Color(40, 140, 40));
        startingElevenPanel.setForeground(new java.awt.Color(255, 255, 255));

        startingElevenPanel.setLayout(new GridLayout(13,1));
        startingElevenPanel.add(new JLabel("Starting 11:"));

        ButtonGroup startingEleven = new ButtonGroup();

        for (Player player : userTeam.getStartingEleven()) {
            JRadioButton playerButton = new JRadioButton(player.getPlayerName());
            playerButton.setActionCommand(player.getPlayerName());
            startingEleven.add(playerButton);
            startingElevenPanel.add(playerButton);
        }

        Button printSelected = new Button("Substitute Player");
        printSelected.setBackground(Color.BLUE);
        printSelected.setForeground(Color.WHITE);
        printSelected.addActionListener(e -> {
            System.out.println("Selected Player: " + startingEleven.getSelection().getActionCommand());
        });

        startingElevenPanel.add(printSelected);

        startingElevenPanel.setOpaque(true);
        this.add(startingElevenPanel);

        JPanel benchPanel = new JPanel();
        this.add(benchPanel);

        this.setVisible(true);
    }
}
