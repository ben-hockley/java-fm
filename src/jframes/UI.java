package jframes;

import Objects.Player;
import Objects.Team;
import Objects.dateTime;
import data.Data;
import events.Event;
import events.Game;
import JPanels.CalendarPanel;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import java.util.ArrayList;

//JAVA swing to create the user interface.
public class UI extends JFrame {

    /**
     * ArrayList events contains all events relevant to the user to be
     * displayed in the calendar panel to the NORTH of the UI.
     */
    private final ArrayList<Event> events = new ArrayList<>();

    /**
     * JLabel with a mouse listener that opens a new JFrame for the transfer
     * market when clicked.
     * Acts as a button but classified as JLabel rather than JButton for
     * design reasons.
     */
    private final Button transferMarketButton;

    /**
     * The clock object used to track date and month to update the calendar.
     */
    private final dateTime clock;

    /**
     * The panel that contains the calendar, applied NORTH in the BorderLayout.
     * Always visible.
     */
    private final JPanel topPanel; //JPanel to hold the calendar. (North)

    /**
     * The button positioned permenantly SOUTH in the UI, used to
     * progress the date.
     * Disabled when a matchday, so the user can not progress date without
     * playing their match.
     */
    private final Button progressDateButton;

    /**
     * JPanel used to hold all the objects visible on non-matchdays.
     * Hidden on matchdays.
     */
    private JPanel homeDefaultDisplay;

    /**
     * int to hold the week number (0 indexed).
     * Applied to the 2D arrayList allGameFixtures to get the week's fixtures,
     * So that the non-user fixtures can be simulated in the background.
     */
    private int weekNumber; //0 indexed (so, for example: week 20 == 19)

    private JLabel leagueTopGoalscorers;
    private JLabel teamTopGoalscorers;

    /**
     * 2D ArrayList containing all the seasons fixtures.
     * Each inner ArrayList contains a week's fixtures (10 games).
     */
    private final ArrayList<ArrayList<Game>> allGameFixtures;

    /**
     * Constructor to initialize the homepage.
     * @param userTeam Team passed from Main, gets the user's team from
     *                 static class Data.
     */
    public UI(final Team userTeam) {
        weekNumber = -1;
        allGameFixtures = Data.england.getLeagueByTier(1).generateFixtures();

        ArrayList<Game> userTeamFixtures = userTeam.getFixtures();
        events.addAll(userTeamFixtures); //add user fixtures to the events list.

        // set basic properties of the JFrame.
        this.setSize(1000, 600);
        this.setLayout(new BorderLayout());

        // Add calendar JPanel to the NORTH of the BorderLayout.
        topPanel = new CalendarPanel();
        clock = new dateTime(new Integer[]{1, 8});
        updateCalendar(clock.getDateNumber(), userTeam);
        this.add(topPanel, BorderLayout.NORTH);

        // Add progressDate Button to the SOUTH of the BorderLayout.
        progressDateButton = new Button("Progress Date");
        progressDateButton.setPreferredSize(new Dimension(100, 50));
        progressDateButton.addActionListener(e -> {
            clock.progressDate();
            updateCalendar(clock.getDateNumber(), userTeam);
        });
        this.add(progressDateButton, BorderLayout.SOUTH);

        transferMarketButton = new Button("Transfer Market");
        transferMarketButton.setPreferredSize(new Dimension(400, 500));
        transferMarketButton.setFont(new Font("Arial", Font.PLAIN, 20));
        transferMarketButton.addActionListener(e -> {
            new transferMarket();
        });
        this.add(transferMarketButton, BorderLayout.EAST);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private Boolean homeDisplaySet;

    public void updateCalendar(final Integer dateNumber, Team userTeam) {
        if(progressDateButton != null) progressDateButton.setEnabled(true);

        //if (leagueTopGoalscorers!= null) leagueTopGoalscorers.setVisible(true);

        if(transferMarketButton != null) {
            transferMarketButton.setEnabled(true);
            transferMarketButton.setVisible(true);
        }

        topPanel.removeAll();
        topPanel.add(new JLabel(clock.getMonthName())); //month name to the left of the calendar.
        homeDisplaySet = false;


        for (int i = dateNumber - 2; i <= dateNumber + 2; i++) {
            topPanel.add(label(i, userTeam));
        }
        topPanel.revalidate();
        if (!homeDisplaySet) {

            ArrayList<Team> actualStandings = userTeam.getLeague().getStandings();

            homeDefaultDisplay = HomeDefaultDisplay(actualStandings, userTeam);
            this.add(homeDefaultDisplay, BorderLayout.CENTER);
            homeDisplaySet = true;
        }
    }

    /**
     * inner method for updateCalendar.
     * Creates a JLabel with a date as text.
     * If the dateNumber is the current date, the background is blue.
     * If the dateNumber is not in the current month, the background is black.
     *
     * @param dateNumber The date to display.
     * @return The JLabel with the dateNumber as text.
     */
    public JLabel label(final Integer dateNumber, Team userTeam) {
        JLabel label = new JLabel();
        label.setFont(new Font("Arial", Font.PLAIN, 10));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        label.setOpaque(true);
        label.setLayout(new GridLayout(1, 2));

        JLabel subLabel1 = new JLabel();
        JLabel subLabel2 = new JLabel();

        if (dateNumber > 0 && dateNumber <= clock.getMonthLength()) {
            label.setText(dateNumber.toString()); //add the date to the label if in the current month.
            if (dateNumber.equals(clock.getDateNumber())) {
                label.setBackground(Color.BLUE); //set the label to blue when the date is the current date.
            }
        } else {
            label.setBackground(Color.BLACK); // If the date is not in the current month, set the background to black.
        }


        // Check if there is an event on this date, add the event logo to the label if there is.
        for (Event event : events) {
            if (event.getDayOfMonth().equals(dateNumber) && event.getMonth().equals(clock.getMonthNumber())) {
                label.setBackground(Color.yellow); //set the label to yellow when there is an event on this date.
                if (event instanceof Game) {

                    //add the home team's name and badge to the left of the label.
                    subLabel1.setText(((Game) event).getHomeTeam().getShortName());
                    subLabel1.setIcon(new ImageIcon(new ImageIcon("teamImages/" + ((Game) event).getHomeTeam().getTeamLogo()).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                    subLabel1.setHorizontalTextPosition(JLabel.CENTER);
                    subLabel1.setVerticalTextPosition(JLabel.BOTTOM);

                    //add the away team's name and badge to the right of the label.
                    subLabel2.setText((((Game) event).getAwayTeam().getShortName()));
                    subLabel2.setIcon(new ImageIcon(new ImageIcon("teamImages/" + ((Game) event).getAwayTeam().getTeamLogo()).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                    subLabel2.setHorizontalTextPosition(JLabel.CENTER);
                    subLabel2.setVerticalTextPosition(JLabel.BOTTOM);

                    label.add(subLabel1);
                    label.add(subLabel2);


                }

                if (dateNumber.equals(clock.getDateNumber())) {
                    this.remove(homeDefaultDisplay);
                    label.setBackground(Color.BLUE);
                    homeDisplaySet = true;
                    progressDateButton.setEnabled(false);
                    transferMarketButton.setEnabled(false);
                    transferMarketButton.setVisible(false);

                    leagueTopGoalscorers.setVisible(false);

                    //transferMarketButton.removeMouseListener(transferMarketButton.getMouseListeners()[0]);

                    if (event instanceof Game) {
                        homeDefaultDisplay.setVisible(false); // Hide the default display
                        weekNumber += 1;

                        JPanel matchdayPanel = HomeGameDisplay(((Game) event).getHomeTeam(), ((Game) event).getAwayTeam(), userTeam, clock, allGameFixtures.get(weekNumber));

                        this.add(matchdayPanel, BorderLayout.CENTER);
                        this.setComponentZOrder(matchdayPanel, 0);
                        this.revalidate();
                    }
                }
            }
        }
        return label;
    }


    public JPanel HomeDefaultDisplay(ArrayList<Team> leagueStandings, Team userTeam) {
        JPanel homeDefaultDisplay = new JPanel();
        homeDefaultDisplay.setPreferredSize(new Dimension(1000, 350));
        homeDefaultDisplay.setLayout(new BorderLayout());
        homeDefaultDisplay.removeAll();

        //Title of the team (NORTH)
        homeDefaultDisplay.add(teamTitle(userTeam), BorderLayout.NORTH);
        homeDefaultDisplay.add(leagueTable(leagueStandings), BorderLayout.WEST);

        //Game Options (CENTER)
        JLabel centerLabel = new JLabel();
        centerLabel.setPreferredSize(new Dimension(300, 600));
        centerLabel.setBackground(Color.BLUE);
        centerLabel.setLayout(new GridLayout(2, 1));

        teamTopGoalscorers = getTeamTopGoalscorers(userTeam);

        leagueTopGoalscorers = getLeagueTopGoalscorers(userTeam);

        JLabel RedLabel = new JLabel();
        RedLabel.setBackground(Color.RED);
        RedLabel.setOpaque(true);

        //transferMarketButton = new Button("Transfer Market");
        //transferMarketButton.setHorizontalAlignment(SwingConstants.CENTER);
        //transferMarketButton.setVerticalAlignment(SwingConstants.CENTER);
        //transferMarketButton.setFont(new Font("Arial", Font.PLAIN, 20));
        //transferMarketButton.setOpaque(true);

        /*
        transferMarketButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
              new transferMarket();
            }
        });



        transferMarketButton.addActionListener(e -> {
            new transferMarket();
        });

         */

        centerLabel.add(teamTopGoalscorers);
        centerLabel.add(leagueTopGoalscorers);

        homeDefaultDisplay.add(centerLabel, BorderLayout.CENTER);

        return homeDefaultDisplay;
    }

    private JLabel teamTitle(Team userTeam) {
        JLabel teamTitle = new JLabel(userTeam.getTeamName());
        teamTitle.setBackground(userTeam.getTeamColor());

        return teamTitle;
    }


    //method to generate a title banner for the North of the home display.
    //static so it can be used in homeGameDisplay aswell.
    public static JLabel getTitleBanner(JLabel teamTitle) {
        teamTitle.setHorizontalAlignment(SwingConstants.CENTER);
        teamTitle.setVerticalAlignment(SwingConstants.CENTER);
        teamTitle.setFont(new Font("Arial", Font.PLAIN, 20));
        teamTitle.setPreferredSize(new Dimension(1000, 50));
        teamTitle.setForeground(Color.WHITE);
        teamTitle.setBackground(Color.BLUE);
        teamTitle.setOpaque(true);
        return teamTitle;
    }


    //League Standings (WEST)
    //static variable , so it can be shown in the homeGameDisplay aswell.
    public static JScrollPane leagueTable(ArrayList<Team> leagueStandings) {
        JLabel westLabel = new JLabel();
        westLabel.setPreferredSize(new Dimension(250, 300));
        westLabel.setBackground(Color.YELLOW);
        westLabel.setForeground(Color.WHITE);

        westLabel.setLayout(new GridLayout(1, 2));

        JLabel teamNames = new JLabel();
        JLabel teamStats = new JLabel();

        teamNames.setLayout(new GridLayout(leagueStandings.size() + 1, 1));

        JLabel tableTitle = new JLabel("League Standings");
        tableTitle.setHorizontalAlignment(SwingConstants.CENTER);
        tableTitle.setVerticalAlignment(SwingConstants.CENTER);
        tableTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        tableTitle.setOpaque(true);
        tableTitle.setBackground(Color.BLUE);
        tableTitle.setForeground(Color.WHITE);
        teamNames.add(tableTitle);

        int tablePosition = 1;
        for (Team leagueStanding : leagueStandings) {
            JLabel team = getTeam(leagueStanding, tablePosition);
            teamNames.add(team);
            tablePosition++;
        }

        teamStats.setLayout(new GridLayout(leagueStandings.size() + 1, 5));

        teamStats.add(new JLabel("MP"));
        teamStats.add(new JLabel("W"));
        teamStats.add(new JLabel("D"));
        teamStats.add(new JLabel("L"));
        teamStats.add(new JLabel("PTS"));

        for (Team leagueStanding : leagueStandings) {
            teamStats.add(new JLabel(String.valueOf(leagueStanding.getMatchesPlayed())));
            teamStats.add(new JLabel(String.valueOf(leagueStanding.getWins())));
            teamStats.add(new JLabel(String.valueOf(leagueStanding.getDraws())));
            teamStats.add(new JLabel(String.valueOf(leagueStanding.getLosses())));
            teamStats.add(new JLabel(String.valueOf(leagueStanding.getPoints())));
        }

        westLabel.add(teamNames);
        westLabel.add(teamStats);

        westLabel.setOpaque(true);
        JScrollPane westScrollPane = new JScrollPane(westLabel);
        westScrollPane.setPreferredSize(new Dimension(300, 300));
        westScrollPane.setFocusable(false);
        return westScrollPane;
    }

    private static JLabel getTeam(Team leagueStanding, int tablePosition) {
        JLabel team = new JLabel(tablePosition + "." + leagueStanding.getShortName());
        team.setHorizontalAlignment(SwingConstants.CENTER);
        team.setVerticalAlignment(SwingConstants.CENTER);
        team.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        if (tablePosition <= 4) {
            team.setBackground(Color.BLUE);
        } else if (tablePosition <= 6) {
            team.setBackground(Color.GREEN);
        } else if (tablePosition <= 17) {
            team.setBackground(Color.ORANGE);
        } else {
            team.setBackground(Color.RED);
        }
        team.setOpaque(true);
        team.setForeground(Color.WHITE);
        return team;
    }

    private JLabel getTeamTopGoalscorers(Team userTeam) {
        JLabel mainLable = new JLabel();
        mainLable.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Top Goalscorers : " + userTeam.getTeamName());
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        titleLabel.setPreferredSize(new Dimension(1000, 25));

        JLabel topGoalScorersLabel = new JLabel();
        topGoalScorersLabel.setLayout(new GridLayout(1, 2));

        JLabel nameLabel = new JLabel();
        nameLabel.setLayout(new GridLayout(6, 1));
        nameLabel.setBackground(userTeam.getTeamColor());
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setOpaque(true);

        JLabel name = new JLabel("Name");
        name.setForeground(Color.WHITE);
        nameLabel.add(name);
        for (int i = 0; i < 5; i++) {
            JLabel player = new JLabel(userTeam.getTopGoalscorers().get(i).getPlayerName());
            player.setHorizontalAlignment(SwingConstants.CENTER);
            player.setVerticalAlignment(SwingConstants.CENTER);
            player.setBorder(BorderFactory.createLineBorder(Color.WHITE));
            player.setFont(new Font("Arial", Font.PLAIN, 10));
            player.setForeground(Color.WHITE);
            nameLabel.add(player);
        }


        JLabel teamStatsLabel = new JLabel();
        teamStatsLabel.setLayout(new GridLayout(6, 3));
        teamStatsLabel.setBackground(userTeam.getTeamColor());
        teamStatsLabel.setForeground(Color.WHITE);
        teamStatsLabel.setOpaque(true);

        JLabel pos = new JLabel("Pos");
        pos.setForeground(Color.WHITE);
        teamStatsLabel.add(pos);

        JLabel apps = new JLabel("Apps");
        apps.setForeground(Color.WHITE);
        teamStatsLabel.add(apps);

        JLabel goalsHeading = new JLabel("Goals");
        goalsHeading.setForeground(Color.WHITE);
        teamStatsLabel.add(goalsHeading);

        for (int i = 0; i < 5; i++) {
            JLabel position = new JLabel(userTeam.getTopGoalscorers().get(i).getPosition());
            position.setHorizontalAlignment(SwingConstants.CENTER);
            position.setVerticalAlignment(SwingConstants.CENTER);
            position.setBorder(BorderFactory.createLineBorder(Color.WHITE));
            position.setFont(new Font("Arial", Font.PLAIN, 10));
            position.setForeground(Color.WHITE);

            JLabel appearances = new JLabel(String.valueOf(userTeam.getTopGoalscorers().get(i).getAppearances()));
            appearances.setHorizontalAlignment(SwingConstants.CENTER);
            appearances.setVerticalAlignment(SwingConstants.CENTER);
            appearances.setBorder(BorderFactory.createLineBorder(Color.WHITE));
            appearances.setFont(new Font("Arial", Font.PLAIN, 10));
            appearances.setForeground(Color.WHITE);

            JLabel goals = new JLabel(String.valueOf(userTeam.getTopGoalscorers().get(i).getGoals()));
            goals.setHorizontalAlignment(SwingConstants.CENTER);
            goals.setVerticalAlignment(SwingConstants.CENTER);
            goals.setBorder(BorderFactory.createLineBorder(Color.WHITE));
            goals.setFont(new Font("Arial", Font.PLAIN, 10));
            goals.setForeground(Color.WHITE);

            teamStatsLabel.add(position);
            teamStatsLabel.add(appearances);
            teamStatsLabel.add(goals);
        }

        topGoalScorersLabel.add(nameLabel);
        topGoalScorersLabel.add(teamStatsLabel);

        mainLable.add(titleLabel, BorderLayout.NORTH);
        mainLable.add(topGoalScorersLabel, BorderLayout.CENTER);

        return mainLable;
    }

    private JLabel getLeagueTopGoalscorers(Team userTeam) {
        JLabel mainLable = new JLabel();
        mainLable.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Top Goalscorers : " + userTeam.getLeague().getName());
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        titleLabel.setPreferredSize(new Dimension(1000, 25));

        JLabel topGoalScorersLabel = new JLabel();
        topGoalScorersLabel.setLayout(new GridLayout(1, 2));

        JLabel nameLabel = new JLabel();
        nameLabel.setLayout(new GridLayout(6, 1));
        nameLabel.setBackground(Color.WHITE);
        nameLabel.setOpaque(true);

        nameLabel.add(new JLabel("Name"));
        for (int i = 0; i < 5; i++) {
            JLabel player = new JLabel(userTeam.getLeague().getTopGoalscorers().get(i).getPlayerName());
            player.setHorizontalAlignment(SwingConstants.CENTER);
            player.setVerticalAlignment(SwingConstants.CENTER);
            player.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            player.setFont(new Font("Arial", Font.PLAIN, 10));
            nameLabel.add(player);
        }

        JLabel teamStatsLabel = new JLabel();
        teamStatsLabel.setLayout(new GridLayout(6, 3));
        teamStatsLabel.setOpaque(true);

        teamStatsLabel.add(new JLabel("Team"));
        teamStatsLabel.add(new JLabel("Apps"));
        teamStatsLabel.add(new JLabel("Goals"));

        for (int i = 0; i < 5; i++) {
            JLabel team = getLabel(userTeam, i);

            JLabel appearances = new JLabel(String.valueOf(userTeam.getLeague().getTopGoalscorers().get(i).getAppearances()));
            appearances.setHorizontalAlignment(SwingConstants.CENTER);
            appearances.setVerticalAlignment(SwingConstants.CENTER);
            appearances.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            appearances.setFont(new Font("Arial", Font.PLAIN, 10));

            JLabel goals = new JLabel(String.valueOf(userTeam.getLeague().getTopGoalscorers().get(i).getGoals()));
            goals.setHorizontalAlignment(SwingConstants.CENTER);
            goals.setVerticalAlignment(SwingConstants.CENTER);
            goals.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            goals.setFont(new Font("Arial", Font.PLAIN, 10));

            teamStatsLabel.add(team);
            teamStatsLabel.add(appearances);
            teamStatsLabel.add(goals);
        }

        topGoalScorersLabel.add(nameLabel);
        topGoalScorersLabel.add(teamStatsLabel);

        mainLable.add(titleLabel, BorderLayout.NORTH);
        mainLable.add(topGoalScorersLabel, BorderLayout.CENTER);

        return mainLable;
    }

    private static JLabel getLabel(Team userTeam, int i) {
        JLabel team = new JLabel(userTeam.getLeague().getTopGoalscorers().get(i).getTeam().getShortName());
        team.setHorizontalAlignment(SwingConstants.CENTER);
        team.setVerticalAlignment(SwingConstants.CENTER);

        team.setBackground(userTeam.getLeague().getTopGoalscorers().get(i).getTeam().getTeamColor());
        team.setForeground(Color.WHITE);
        team.setOpaque(true);
        team.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        team.setFont(new Font("Arial", Font.PLAIN, 10));
        return team;
    }

    //home game display

    public JPanel HomeGameDisplay(Team homeTeam, Team awayTeam, Team userTeam, dateTime clock, ArrayList<Game> allGames) {

        JPanel homeGameDisplayPanel = new JPanel();
        homeGameDisplayPanel.setPreferredSize(new Dimension(1000, 350));
        homeGameDisplayPanel.setLayout(new BorderLayout());

        JLabel teamTitle = new JLabel(homeTeam.getTeamName() + " vs " + awayTeam.getTeamName());
        homeGameDisplayPanel.add(getTitleBanner(teamTitle), BorderLayout.NORTH);
        homeGameDisplayPanel.add(leagueTable(userTeam.getLeague().getStandings()), BorderLayout.WEST);

        Team opponent;
        if (homeTeam == userTeam) {
            opponent = awayTeam;
        } else {
            opponent = homeTeam;
        }

        JLabel opponentsLineup = new JLabel();
        opponentsLineup.setLayout(new GridLayout(13, 1));
        opponentsLineup.setPreferredSize(new Dimension(400, 1000));
        opponentsLineup.setBackground(opponent.getTeamColor());
        opponentsLineup.setForeground(Color.WHITE);
        opponentsLineup.setOpaque(true);

        JLabel teamInfo = new JLabel(opponent.getTeamName() + " , " + (opponent.getLeague().getStandings().indexOf(opponent) + 1) + " in " + opponent.getLeague().getName());
        teamInfo.setVerticalAlignment(SwingConstants.CENTER);
        teamInfo.setForeground(Color.WHITE);

        JLabel teamFormation = new JLabel("Formation : " + opponent.getFormationInText());
        teamFormation.setVerticalAlignment(SwingConstants.CENTER);
        teamFormation.setForeground(Color.WHITE);

        opponentsLineup.add(teamInfo);
        opponentsLineup.add(teamFormation);

        Player[] opponentStartingEleven = opponent.getStartingEleven();

        for (int i = 0; i < 11; i++) {
            JLabel player = getPlayer(opponentStartingEleven, i);

            opponentsLineup.add(player);
        }

        homeGameDisplayPanel.add(opponentsLineup, BorderLayout.EAST);

        JLabel matchdayCenterPanel = new JLabel();
        matchdayCenterPanel.setLayout(new GridLayout(2, 1));
        matchdayCenterPanel.setPreferredSize(new Dimension(600, 1000));

        JLabel playGameButton = new JLabel("Play Game");
        playGameButton.setHorizontalAlignment(SwingConstants.CENTER);
        playGameButton.setVerticalAlignment(SwingConstants.CENTER);
        playGameButton.setFont(new Font("Arial", Font.PLAIN, 20));
        playGameButton.setOpaque(true);
        playGameButton.setBackground(Color.BLUE);
        playGameButton.setForeground(Color.WHITE);

        JLabel simulateGameButton = new JLabel("Simulate Game");
        simulateGameButton.setHorizontalAlignment(SwingConstants.CENTER);
        simulateGameButton.setVerticalAlignment(SwingConstants.CENTER);
        simulateGameButton.setFont(new Font("Arial", Font.PLAIN, 20));
        simulateGameButton.setOpaque(true);
        simulateGameButton.setBackground(Color.RED);
        simulateGameButton.setForeground(Color.WHITE);
        simulateGameButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                //simulate the game
                new manageTeam(userTeam);
            }

        });
        playGameButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                //simulate the game
                playGameButton.setEnabled(false);
                simulateGameButton.setEnabled(false);

                playGameButton.setVisible(false);
                simulateGameButton.setVisible(false);

                homeGameDisplayPanel.setVisible(false);

                new gameSimulator(homeTeam, awayTeam);
                homeGameDisplayPanel.setVisible(false);
                clock.progressDate();
                updateCalendar(clock.getDateNumber(), userTeam);
            }

        });

        matchdayCenterPanel.add(playGameButton);
        matchdayCenterPanel.add(simulateGameButton);
        homeGameDisplayPanel.add(matchdayCenterPanel,BorderLayout.CENTER);

        //simulate CPU matches
        for (Game game : allGames) {
            //i.e: only simulates games that do not involve the user's team.
            if (game.getHomeTeam() != userTeam && game.getAwayTeam() != userTeam) {

                //gets the ratings of the teams to determine who has a better chance of winning.
                Integer homeRating = game.getHomeTeam().getRating();
                Integer awayRating = game.getAwayTeam().getRating();

                //home advantage, increases the home team's chance of winning.
                homeRating += 40;

                //generate random values between 0 and 1 for each team to simulate form/other factors.
                double homeRandomValue = Math.random();
                double awayRandomValue = Math.random();

                //generate number of goals scored by each team.
                int homeGoals = (int) Math.round(homeRating * homeRandomValue / 75);
                int awayGoals = (int) Math.round(awayRating * awayRandomValue / 75);

                //compare number of goals scored by each team to determine result.
                if (homeGoals > awayGoals) {
                    //home win
                    game.getHomeTeam().addWin();
                    game.getAwayTeam().addLoss();
                } else if (awayGoals > homeGoals) {
                    //away win
                    game.getHomeTeam().addLoss();
                    game.getAwayTeam().addWin();
                } else {
                    //draw
                    game.getHomeTeam().addDraw();
                    game.getAwayTeam().addDraw();
                }


                //add appearances to all players in the game.
                Player[] homeStartingEleven = game.getHomeTeam().getStartingEleven();

                for (Player player : homeStartingEleven) {
                    player.addAppearance();
                }
                Player[] awayStartingEleven = game.getAwayTeam().getStartingEleven();

                for (Player player : awayStartingEleven) {
                    player.addAppearance();
                }

                //add goals to the goalscorers.
                getAwayGoalscorers(homeGoals, homeStartingEleven);

                getAwayGoalscorers(awayGoals, awayStartingEleven);
            }
        }
        return homeGameDisplayPanel;
    }

    private void getAwayGoalscorers(int awayGoals, Player[] awayStartingEleven) {
        for (int i = 0; i < awayGoals; i++) {
            Player scorer;

            double scorerGenerator = Math.random();
            // 60% chance the scorer is a forward (20% chance for each forward)
            if (scorerGenerator >= 0.8) {
                scorer = awayStartingEleven[10]; //FWD 1
            } else if (scorerGenerator >= 0.6) {
                scorer = awayStartingEleven[9]; //FWD 2
            } else if (scorerGenerator >= 0.4) {
                scorer = awayStartingEleven[8]; //FWD 3
            } else if (scorerGenerator >= 0.3) {
                // 30% chance the scorer is a midfielder (10% chance for each midfielder)
                scorer = awayStartingEleven[7]; //MID 1
            } else if (scorerGenerator >= 0.2) {
                scorer = awayStartingEleven[6]; //MID 2
            } else if (scorerGenerator >= 0.1) {
                scorer = awayStartingEleven[5]; //MID 3
            } else {
                // 10% chance the scorer is a defender (2.5% chance for each defender)
                int randomIntBetween1And4 = (int) Math.floor(Math.random() * 4) + 1;
                scorer = awayStartingEleven[randomIntBetween1And4]; //DEFENDER
            }

            //add a goal to the goalscorer's tally
            scorer.addGoal();
        }
    }

    private static JLabel getPlayer(Player[] opponentStartingEleven, int i) {
        JLabel player = new JLabel(opponentStartingEleven[i].getPosition() + " " + opponentStartingEleven[i].getPlayerName());
        player.setHorizontalAlignment(SwingConstants.CENTER);
        player.setVerticalAlignment(SwingConstants.CENTER);
        player.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        player.setFont(new Font("Arial", Font.PLAIN, 10));
        player.setForeground(Color.WHITE);
        player.setOpaque(true);

        switch (opponentStartingEleven[i].getPosition()) {
            case "GK" -> player.setBackground(Color.ORANGE);
            case "DEF" -> player.setBackground(Color.RED);
            case "MID" -> player.setBackground(Color.GREEN);
            case "FWD" -> player.setBackground(Color.BLUE);
        }
        return player;
    }
}
