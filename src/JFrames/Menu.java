package JFrames;

import data.Data;
import objects.League;
import objects.Nation;
import objects.Team;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

public class Menu extends JFrame {

    /**
     * Default width of the menu JFrame.
     */
    private final int defaultWidth = 1000;
    /**
     * Default height of the menu JFrame.
     */
    private final int defaultHeight = 500;
    /**
     * Default height of the title JLabel in the menu JFrame.
     */
    private final int defaultTitleHeight = 100;
    /**
     * Distance between elements in the menu JFrame. (hgap and vgap)
     */
    private final int spacing = 10;
    /**
     * Constructor for the menu class.
     * This loads the 1st title menu that is displayed to the user when the
     * program is run.
     */
    public Menu() {
        this.setSize(defaultWidth, defaultHeight);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel titleMenu = getTitleMenu();
        this.add(titleMenu);

        this.setVisible(true);
        this.setTitle("Setup Menu");
    }

    /**
     * Method to create the title menu.
     * This is the first JPanel that is displayed to the user.
     * The method is triggered when the program is run, and when the user
     * presses the back button on the 'select country' menu.
     * (the 2nd menu that is displayed to the user).
     * @return JPanel of the title menu.
     */
    private JPanel getTitleMenu() {
        JPanel titleMenu = new JPanel(new BorderLayout());

        JLabel title = new JLabel("Football Manager 2024");
        formatTitle(title);
        title.setPreferredSize(new Dimension(defaultWidth, defaultTitleHeight));
        titleMenu.add(title, BorderLayout.NORTH);

        JLabel startButtonContainer = new JLabel();


        final int startButtonWidth = 100;
        final int startButtonHeight = 50;
        JLabel startButton = new JLabel("Start");
        formatClickableLabel(startButton);
        int halfWidth = this.getWidth() / 2;
        int halfHeight = this.getHeight() / 2;
        startButton.setBounds(
                halfWidth - startButtonWidth / 2,
                halfHeight - startButtonHeight / 2,
                startButtonWidth, startButtonHeight
        );
        startButtonContainer.add(startButton);
        titleMenu.add(startButtonContainer, BorderLayout.CENTER);

        startButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(final java.awt.event.MouseEvent evt) {
                titleMenu.setVisible(false);

                JPanel countryMenu = getSelectCountryMenu();
                SwingUtilities.getWindowAncestor(titleMenu).add(countryMenu);
            }
        });

        return titleMenu;
    }

    private void formatTitle(final JLabel title) {
        final int titleFontSize = 30;

        title.setForeground(Color.WHITE);
        title.setBackground(Color.RED);
        title.setOpaque(true);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, titleFontSize));
    }

    private void formatClickableLabel(final JLabel clickableLabel) {
        final int labelFontSize = 15;

        clickableLabel.setForeground(Color.BLUE);
        clickableLabel.setBackground(Color.WHITE);
        clickableLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        clickableLabel.setOpaque(true);
        clickableLabel.setHorizontalAlignment(SwingConstants.CENTER);
        clickableLabel.setVerticalAlignment(SwingConstants.CENTER);
        clickableLabel.setFont(new Font("Arial", Font.BOLD, labelFontSize));
    }

    private JPanel getSelectCountryMenu() {
        final int rowSize = 3;

        JPanel selectCountryMenu = new JPanel(new BorderLayout());

        JLabel titleLabel = getBackButtonTitle("Select Country",
                selectCountryMenu, getTitleMenu());
        selectCountryMenu.add(titleLabel, BorderLayout.NORTH);

        final int numberOfCountries = 6;
        int numberOfRows = numberOfCountries / rowSize;
        int numberOfColumns = Math.min(rowSize, numberOfCountries);

        JPanel countryList = new JPanel(new GridLayout(
                numberOfRows, numberOfColumns, spacing, spacing));
        countryList.setPreferredSize(new Dimension(
                defaultWidth, defaultHeight - defaultTitleHeight));

        JLabel internationalLabel = getNationLabel(
                Data.international, selectCountryMenu);

        JLabel englandLabel = getNationLabel(Data.england, selectCountryMenu);
        JLabel franceLabel = getNationLabel(Data.france, selectCountryMenu);
        JLabel germanyLabel = getNationLabel(Data.germany, selectCountryMenu);
        JLabel italyLabel = getNationLabel(Data.italy, selectCountryMenu);
        JLabel spainLabel = getNationLabel(Data.spain, selectCountryMenu);

        countryList.add(englandLabel);
        countryList.add(franceLabel);
        countryList.add(germanyLabel);
        countryList.add(italyLabel);
        countryList.add(spainLabel);
        countryList.add(internationalLabel);

        selectCountryMenu.add(countryList, BorderLayout.CENTER);

        return selectCountryMenu;
    }

    private JPanel selectLeagueMenu(final Nation nationSelected) {
        final int numberOfLeagues = nationSelected.getNumberOfLeagues();

        JPanel selectLeagueMenu = new JPanel(new BorderLayout());

        String nationName = nationSelected.getNationName();
        JLabel titleLabel = getBackButtonTitle("Select League - " + nationName,
                selectLeagueMenu, getSelectCountryMenu());

        selectLeagueMenu.add(titleLabel, BorderLayout.NORTH);

        JLabel leagueList = new JLabel();
        leagueList.setLayout(new GridLayout(
                numberOfLeagues, 1, spacing, spacing));
        leagueList.setPreferredSize(new Dimension(
                defaultWidth, defaultHeight - defaultTitleHeight));

        for (League league : nationSelected.getAllLeagues()) {
            JLabel leagueLabel = new JLabel(league.getName());
            formatClickableLabel(leagueLabel);
            leagueList.add(leagueLabel);

            leagueLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(final java.awt.event.MouseEvent evt) {
                    selectLeagueMenu.setVisible(false);
                    SwingUtilities.getWindowAncestor(selectLeagueMenu).add(
                            getSelectTeamMenu(league));
                }
            });
        }
        selectLeagueMenu.add(leagueList, BorderLayout.CENTER);

        return selectLeagueMenu;
    }

    private JPanel getSelectTeamMenu(final League leagueSelected) {
        final int numberOfTeams = leagueSelected.getNumberOfTeams();

        JPanel selectTeamMenu = new JPanel(new BorderLayout());

        String leagueName = leagueSelected.getName();
        JLabel titleLabel = getBackButtonTitle("Select Team - " + leagueName,
                selectTeamMenu, selectLeagueMenu(leagueSelected.getCountry()));


        selectTeamMenu.add(titleLabel, BorderLayout.NORTH);

        JLabel teamList = new JLabel();

        final int rowLength = 5;

        int numberOfRows = numberOfTeams / rowLength;
        int numberOfColumns = Math.min(rowLength, numberOfTeams);

        teamList.setLayout(new GridLayout(numberOfRows, numberOfColumns,
                spacing, spacing));
        teamList.setPreferredSize(new Dimension(defaultWidth,
                defaultHeight - defaultTitleHeight));

        final int teamLogoWidth = 55;
        final int teamLogoHeight = 72;

        for (Team team : leagueSelected.getAllTeams()) {
            JLabel teamLabel = new JLabel(team.getTeamName());
            formatClickableLabel(teamLabel);
            teamLabel.setIcon(new ImageIcon(new ImageIcon("teamImages/"
                    + team.getTeamLogo()).getImage().getScaledInstance(
                            teamLogoWidth, teamLogoHeight, Image.SCALE_SMOOTH)
            ));
            teamList.add(teamLabel);

            teamLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(final java.awt.event.MouseEvent evt) {
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

    private JLabel getNationLabel(final Nation nationOption,
                                  final JPanel parentPanel) {
        final int flagWidth = 200;
        final int flagHeight = 150;

        JLabel nationLabel = new JLabel(nationOption.getNationName());
        formatClickableLabel(nationLabel);
        nationLabel.setIcon(new ImageIcon(new ImageIcon("teamImages/"
                + nationOption.getNationFlag()).getImage().getScaledInstance(
                        flagWidth, flagHeight, Image.SCALE_SMOOTH)));
        nationLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        nationLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        nationLabel.setIconTextGap(spacing);

        nationLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(final java.awt.event.MouseEvent evt) {

                parentPanel.setVisible(false);
                SwingUtilities.getWindowAncestor(parentPanel).add(
                        selectLeagueMenu(nationOption));
            }
        });

        return nationLabel;
    }

    private JLabel getBackButtonTitle(final String titleText,
                                      final JPanel currentScreen,
                                      final JPanel backButtonDestination) {
        final int backButtonWidth = 100;

        JLabel titleLabel = new JLabel();
        titleLabel.setPreferredSize(new Dimension(defaultWidth,
                defaultTitleHeight));

        JLabel title = new JLabel(titleText);
        title.setBounds(backButtonWidth, 0, this.getWidth() - backButtonWidth,
                defaultTitleHeight);
        formatTitle(title);
        titleLabel.add(title);

        JLabel backButton = new JLabel("⬅ Back");
        backButton.setBounds(0, 0, backButtonWidth, defaultTitleHeight);
        backButton.setForeground(Color.BLUE);
        backButton.setBackground(Color.WHITE);
        backButton.setOpaque(true);
        backButton.setHorizontalAlignment(SwingConstants.CENTER);
        backButton.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.add(backButton);

        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(final java.awt.event.MouseEvent evt) {
                currentScreen.setVisible(false);
                SwingUtilities.getWindowAncestor(currentScreen).add(
                        backButtonDestination);
            }
        });

        return titleLabel;
    }

}
