package JFrames;

import data.Data;
import objects.League;
import objects.Nation;
import objects.Team;

import javax.swing.*;
import java.awt.*;

public class menu extends JFrame {
    public menu() {
        this.setSize(1000, 500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel titleMenu = getTitleMenu();
        this.add(titleMenu);

        this.setVisible(true);
        this.setTitle("Setup Menu");
    }

    private JPanel getTitleMenu() {
        JPanel titleMenu = new JPanel(new BorderLayout());

        JLabel title = new JLabel("Football Manager 2024");
        title.setForeground(java.awt.Color.WHITE);
        title.setBackground(Color.RED);
        title.setOpaque(true);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setPreferredSize(new Dimension(1000, 100));
        titleMenu.add(title, BorderLayout.NORTH);

        JLabel startButtonContainer = new JLabel();
        JLabel startButton = new JLabel("Start");
        startButton.setForeground(Color.BLUE);
        startButton.setBackground(Color.WHITE);
        startButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        startButton.setOpaque(true);
        startButton.setHorizontalAlignment(SwingConstants.CENTER);
        startButton.setVerticalAlignment(SwingConstants.CENTER);
        startButton.setFont(new Font("Arial", Font.BOLD, 15));
        System.out.println(startButtonContainer.getHeight());
        int halfWidth = this.getWidth() / 2;
        int halfHeight = this.getHeight() / 2;
        startButton.setBounds(halfWidth - 50, halfHeight - 25, 100, 50);
        startButtonContainer.add(startButton);
        titleMenu.add(startButtonContainer, BorderLayout.CENTER);

        startButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                titleMenu.setVisible(false);

                JPanel selectCountryMenu = getSelectCountryMenu();
                SwingUtilities.getWindowAncestor(titleMenu).add(selectCountryMenu);
            }
        });

        return titleMenu;
    }

    private JPanel getSelectCountryMenu() {
        JPanel selectCountryMenu = new JPanel(new BorderLayout());

        JLabel title = new JLabel("Select Country");
        title.setForeground(java.awt.Color.WHITE);
        title.setBackground(Color.RED);
        title.setOpaque(true);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setPreferredSize(new Dimension(1000, 100));
        selectCountryMenu.add(title, BorderLayout.NORTH);

        final int numberOfCountries = 2;
        int numberOfRows = numberOfCountries / 3;
        int numberOfColumns = Math.min(3, numberOfCountries);

        JPanel countryList = new JPanel(new GridLayout(numberOfRows, numberOfColumns, 10, 10));
        countryList.setPreferredSize(new Dimension(1000, 400));

        JLabel internationalLabel = getNationLabel(Data.international, selectCountryMenu);
        JLabel englandLabel = getNationLabel(Data.england, selectCountryMenu);

        countryList.add(englandLabel);
        countryList.add(internationalLabel);

        selectCountryMenu.add(countryList, BorderLayout.CENTER);

        return selectCountryMenu;
    }

    private JPanel selectLeagueMenu(Nation nationSelected) {
        final int numberOfLeagues = nationSelected.getNumberOfLeagues();

        JPanel selectLeagueMenu = new JPanel(new BorderLayout());

        String nationName = nationSelected.getNationName();
        JLabel title = new JLabel(nationName + ": Select League");
        title.setForeground(java.awt.Color.WHITE);
        title.setBackground(Color.RED);
        title.setOpaque(true);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setPreferredSize(new Dimension(1000, 100));
        selectLeagueMenu.add(title, BorderLayout.NORTH);

        JLabel leagueList = new JLabel();
        leagueList.setLayout(new GridLayout(numberOfLeagues, 1, 10, 10));
        leagueList.setPreferredSize(new Dimension(1000, 400));

        for (League league : nationSelected.getAllLeagues()) {
            JLabel leagueLabel = new JLabel(league.getName());
            leagueLabel.setForeground(Color.BLUE);
            leagueLabel.setBackground(Color.WHITE);
            leagueLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            leagueLabel.setOpaque(true);
            leagueLabel.setHorizontalAlignment(SwingConstants.CENTER);
            leagueLabel.setVerticalAlignment(SwingConstants.CENTER);
            leagueLabel.setFont(new Font("Arial", Font.BOLD, 15));
            leagueList.add(leagueLabel);

            leagueLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    selectLeagueMenu.setVisible(false);
                    SwingUtilities.getWindowAncestor(selectLeagueMenu).add(getSelectTeamMenu(league));
                }
            });
        }
        selectLeagueMenu.add(leagueList, BorderLayout.CENTER);

        return selectLeagueMenu;
    }

    private JPanel getSelectTeamMenu(League leagueSelected) {
        final int numberOfTeams = leagueSelected.getNumberOfTeams();

        JPanel selectTeamMenu = new JPanel(new BorderLayout());

        String leagueName = leagueSelected.getName();
        JLabel title = new JLabel(leagueName + ": Select Team");
        title.setForeground(java.awt.Color.WHITE);
        title.setBackground(Color.RED);
        title.setOpaque(true);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setPreferredSize(new Dimension(1000, 100));
        selectTeamMenu.add(title, BorderLayout.NORTH);

        JLabel teamList = new JLabel();

        int numberOfRows = numberOfTeams / 5;
        int numberOfColumns = Math.min(5, numberOfTeams);

        teamList.setLayout(new GridLayout(numberOfRows, numberOfColumns, 10, 10));
        teamList.setPreferredSize(new Dimension(1000, 400));

        for (Team team : leagueSelected.getAllTeams()) {
            JLabel teamLabel = new JLabel(team.getTeamName());
            teamLabel.setForeground(Color.BLUE);
            teamLabel.setBackground(Color.WHITE);
            teamLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            teamLabel.setOpaque(true);
            teamLabel.setHorizontalAlignment(SwingConstants.CENTER);
            teamLabel.setVerticalAlignment(SwingConstants.CENTER);
            teamLabel.setFont(new Font("Arial", Font.BOLD, 15));
            teamLabel.setIcon(new ImageIcon(new ImageIcon("teamImages/" + team.getTeamLogo()).getImage().getScaledInstance(55,72, Image.SCALE_SMOOTH)));
            teamList.add(teamLabel);

            teamLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    selectTeamMenu.setVisible(false);

                    JFrame userUI = new UI(team);
                    userUI.setTitle("Football Manager 2024");
                    dispose();
                }
            });
        }
        selectTeamMenu.add(teamList, BorderLayout.CENTER);

        return selectTeamMenu;
    }

    private JLabel getNationLabel(Nation nationOption, JPanel parentPanel){
        JLabel nationLabel = new JLabel(nationOption.getNationName());
        nationLabel.setForeground(Color.BLUE);
        nationLabel.setBackground(Color.WHITE);
        nationLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        nationLabel.setOpaque(true);
        nationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nationLabel.setVerticalAlignment(SwingConstants.CENTER);
        nationLabel.setFont(new Font("Arial", Font.BOLD, 15));
        nationLabel.setIcon(new ImageIcon(new ImageIcon("teamImages/" + nationOption.getNationFlag()).getImage().getScaledInstance(200,120, Image.SCALE_SMOOTH)));
        nationLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        nationLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        nationLabel.setIconTextGap(10);

        nationLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {

                parentPanel.setVisible(false);
                SwingUtilities.getWindowAncestor(parentPanel).add(selectLeagueMenu(nationOption));
            }
        });

        return nationLabel;
    }

}
