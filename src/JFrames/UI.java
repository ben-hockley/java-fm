package JFrames;

import data.Data;
import events.Game;
import JPanels.CalendarPanel;
import JPanels.HomeDefaultDisplay;
import JPanels.HomeGameDisplay;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

import events.Event;
import main.fixtureGen;
import objects.Cup;
import objects.Team;
import objects.dateTime;

//JAVA swing to create the user interface.
public class UI extends JFrame {

    /**
     * All the events that involve the user's team. These events will be shown
     * on the calendar panel.
     * userEvents stores the following:
     * - user's league fixtures (Game objects)
     * - user's cup fixtures (Game objects)
     */
    private ArrayList<Event> userEvents;

    /**
     * ArrayList that stores all the games that the CPU has to play in that
     * round (for cup games) / gameweek (for league games). This is so the CPU
     * games can be simulated simultaneously as the user plays their game.
     */
    private ArrayList<Game> cpuGames;

    /**
     * The clock object used to track date and month to update the calendar.
     */
    private final dateTime clock;
    /**
     * The panel that contains the calendar, applied NORTH in the BorderLayout.
     */
    private final JPanel topPanel;

    /**
     * The button that progresses the date by one day. Moves the calendar panel
     * across one day and updates the main panel if there is an event on the
     * day.
     */
    private final Button progressDateButton;

    /**
     * Panel displayed in the center of the BorderLayout in the main UI when
     * there is no game being played by the user's team.
     * This panel displays the league standings, the league top scorers, and
     * the user team's top scorers.
     * It also displays which keys should be pressed to access different
     * JFrames such as the transfer market.
     */
    private JPanel homeDefaultDisplay;

    /**
     * 2D arrayList that stores all the league fixtures for the season.
     * Each inner arrayList represents a gameweek, containing all the games to
     * be played in that week.
     * This arrayList contains all the fixtures in the league, not just the
     * games involving the user's team.
     */
    private ArrayList<ArrayList<Game>> allGameFixtures;

    /**
     * This 2D arrayList contains all the currently schedules champions league
     * fixtures. When new fixtures are generated, they are added to this.
     * Each inner arrayList represents a round of fixtures, containing all the
     * fixtures to be played in that round. (by any team).
     */
    private ArrayList<ArrayList<Game>> championsLeagueFixtures;
    /**
     * The months in which the transfer market is open.
     * Used to determine when the user and CPU can buy and sell players.
     * (January, June, July, August).
     */
    private final ArrayList<Integer> transferMarketMonths =
            new ArrayList<>(Arrays.asList(1, 6, 7, 8));
    /**
     * Constructor for the main UI that contains the general flow of the game
     * and the main panels.
     * @param userTeam the team the user is controlling, as selected by the
     *                 user in the game setup JFrame. (loaded by Main.java
     *                 before us.)
     */
    public UI(final Team userTeam) {
        // generates all league fixtures for the league,
        // and adds each fixture to the fixture schedule for each team.
        regenerateFixtures(userTeam);

        //set basic properties of the JFrame.
        this.setTitle("Football Manager 2024");

        final int defaultWidth = 1000;
        final int defaultHeight = 600;

        this.setSize(defaultWidth, defaultHeight);
        this.setLayout(new BorderLayout());

        topPanel = new CalendarPanel();

        final Integer[] startDate = {1, 8, 2023}; //start date of the game.
        clock = new dateTime(startDate); //pass as args to dateTime object.

        //set calendar to default date.
        updateCalendar(userTeam);
        this.add(topPanel, BorderLayout.NORTH);

        // Add a button to progress the date to the SOUTH of the BorderLayout.
        progressDateButton = new Button("Progress Date (P)");
        final int progressDateButtonHeight = 50;
        progressDateButton.setPreferredSize(new Dimension(defaultWidth,
                progressDateButtonHeight));

        progressDateButton.addActionListener(e -> {
            clock.progressDate();
            updateCalendar(userTeam);

            //dispose of all frames except the main frame.
            if (Frame.getFrames().length > 1) {

                for (Frame jframe : Frame.getFrames()) {
                    if (!jframe.getTitle().equals("Football Manager 2024")) {
                        jframe.dispose();
                    }
                }
            }
        });


        //progress date shortcut by pressing P.
        progressDateButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_P) {
                    if (progressDateButton.isEnabled()) {
                        clock.progressDate();
                        updateCalendar(userTeam);

                        //dispose of all frames except the main frame.
                        if (Frame.getFrames().length > 1) {
                            for (Frame jframe : Frame.getFrames()) {
                                if (!jframe.getTitle().equals(
                                        "Football Manager 2024")) {
                                    jframe.dispose();
                                }
                            }
                        }
                    }
                }
            }
        });

        progressDateButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_T) {

                    if (userTeam.getTeamType().equals("International")) {
                        JOptionPane.showMessageDialog(null,
                                "You are managing a national Team,"
                                + " so you cannot buy and sell players.");
                    } else if (transferMarketMonths.contains(
                            clock.getMonthNumber())) {
                            new transferMarket(userTeam);
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "Transfer window shut, you can buy"
                                    + " and sell players in January,"
                                    + " June, July and August.");
                        }
                    }
                }
            });
        progressDateButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_M) {
                    new manageTeam(userTeam);
                }
            }
        });
        progressDateButton.setFocusable(true);
        this.add(progressDateButton, BorderLayout.SOUTH);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * Boolean to confirm whether the home display has been set.
     * If the home display has not been set, the default display will be set.
     * If the home display has been set, the default display will not be set.
     */
    private Boolean homeDisplaySet;

    /**
     * Updates the calendar on the main UI:
     * - Updates the month and year displayed on the calendar, if changed.
     * - Updates the dates displayed on the calendar.
     * Also carries out any necessary updates to the game state:
     * - 0-2 random transfers per day during the transfer window.
     * - displays popup messages to user to update on the transfer window.
     * - refreshes the main UI.
     * @param userTeam the user's team. (user games are calendar events).
     */
    public void updateCalendar(final Team userTeam) {

        final Integer date = clock.getDateNumber();
        final Integer monthNumber = clock.getMonthNumber();
        final String month = clock.getMonthName();
        final Integer year = clock.getYearNumber();

        if (transferMarketMonths.contains(monthNumber)) {
            final int maxDailyTransfers = 3;
            int numberOfTransfers = (int) (Math.random() * maxDailyTransfers);

            for (int i = 0; i < numberOfTransfers; i++) {
                Data.world.doRandomTransfer(userTeam, date, monthNumber);
            }
        }
        if (progressDateButton != null) {
            progressDateButton.setEnabled(true);
        }

        topPanel.removeAll();
        topPanel.add(new JLabel(month + " " + year));
        homeDisplaySet = false;


        for (int i = date - 2; i <= date + 2; i++) {
            topPanel.add(label(i, userTeam));
        }
        topPanel.revalidate();
        if (!homeDisplaySet) {

            ArrayList<Team> standings = userTeam.getLeague().getStandings();

            homeDefaultDisplay =
                    new HomeDefaultDisplay(standings, userTeam, clock, this);
            this.add(homeDefaultDisplay, BorderLayout.CENTER);
            homeDisplaySet = true;
        }

        //popup messages for the transfer window
        if (userTeam.getTeamType().equals("Club")) {
            if (month.equals("January") && date.equals(1)) {
                JOptionPane.showMessageDialog(null,
                        "Happy New Year! It's now " + year + "!"
                                + ", the transfer market is open.");
            }
            if (month.equals("June") && date.equals(1)) {
                JOptionPane.showMessageDialog(null,
                        "It's now June, the transfer market is open.");
            }
            if ((month.equals("August") || month.equals("January"))
                    && date.equals(31)) {
                JOptionPane.showMessageDialog(null,
                        "It's deadline day, wrap up any last-minute deals "
                                + "before it's too late.");
            }
            if ((month.equals("September") || month.equals("February"))
                    && date.equals(1)) {
                JOptionPane.showMessageDialog(null,
                        "The transfer window is now closed,"
                                + " you can no longer buy or sell players.");
            }
        }
    }
    /**
     * inner method for updateCalendar.
     * Creates a JLabel with a date as text.
     * If the dateNumber is the current date, the background is blue.
     * If the dateNumber is not in the current month, the background is black.
     * @param dateNumber The date to display.
     * @param userTeam The user's team.
     * @return The JLabel with the dateNumber as text.
     */
    private JLabel label(final Integer dateNumber, final Team userTeam) {

        final Integer date = clock.getDateNumber();
        final Integer monthNumber = clock.getMonthNumber();

        JLabel label = new JLabel();
        final int fontSize = 10;
        label.setFont(new Font("Arial", Font.PLAIN, fontSize));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        label.setOpaque(true);
        label.setLayout(new GridLayout(1, 2));

        JLabel subLabel1 = new JLabel();
        JLabel subLabel2 = new JLabel();

        if (dateNumber > 0 && dateNumber <= clock.getMonthLength()) {
            //add the date to the label if in the current month.
            label.setText(dateNumber.toString());
            if (dateNumber.equals(clock.getDateNumber())) {
                //set the label to blue when the date is the current date.
                label.setBackground(Color.BLUE);
            }
        } else {
            //If the date is not in the current month,set label to black.
            label.setBackground(Color.BLACK);
        }


        //If there is an event on this date, add the event logo to the label.
        for (Event event : userEvents) {
            if (event.getDayOfMonth().equals(date)
                    && event.getMonth().equals(monthNumber)) {
                //set the label to yellow when there is an event on this date.
                label.setBackground(Color.yellow);
                if (event instanceof Game) {

                    if (((Game) event).getGameType().equals("League")) {
                        final Color premierLeagueColor = new Color(61, 25, 91);
                        label.setBackground(premierLeagueColor);
                    } else if (((Game) event).getGameType().equals("Cup")) {
                        final Color uclColor = new Color(14, 32, 80);
                        label.setBackground(uclColor);
                    }

                    Team homeTeam = ((Game) event).getHomeTeam();
                    Team awayTeam = ((Game) event).getAwayTeam();

                    final ImageIcon scaledHomeBadge
                            = new ImageIcon(new ImageIcon("teamImages/"
                            + homeTeam.getTeamLogo()).getImage()
                            .getScaledInstance(55, 72, Image.SCALE_SMOOTH));

                    final ImageIcon scaledAwayBadge
                            = new ImageIcon(new ImageIcon("teamImages/"
                            + awayTeam.getTeamLogo()).getImage()
                            .getScaledInstance(55, 72, Image.SCALE_SMOOTH));

                    //add home team's name, badge to the left of the label.
                    subLabel1.setText(homeTeam.getShortName());
                    subLabel1.setForeground(Color.WHITE);
                    subLabel1.setIcon(scaledHomeBadge);
                    subLabel1.setHorizontalAlignment(JLabel.LEFT);
                    subLabel1.setHorizontalTextPosition(JLabel.CENTER);
                    subLabel1.setVerticalTextPosition(JLabel.BOTTOM);

                    //add away team's name, badge to the right of the label.
                    subLabel2.setText(awayTeam.getShortName());
                    subLabel2.setForeground(Color.WHITE);
                    subLabel2.setIcon(scaledAwayBadge);
                    subLabel2.setHorizontalAlignment(JLabel.RIGHT);
                    subLabel2.setHorizontalTextPosition(JLabel.CENTER);
                    subLabel2.setVerticalTextPosition(JLabel.BOTTOM);

                    label.add(subLabel1);
                    label.add(subLabel2);
                }

                if (dateNumber.equals(clock.getDateNumber())) {
                    progressDateButton.setEnabled(false);
                    this.remove(homeDefaultDisplay);
                    label.setBackground(Color.BLUE);
                    homeDisplaySet = true;
                    if (event instanceof Game) {
                        //hide default display on matchdays.
                        homeDefaultDisplay.setVisible(false);
                        if (((Game) event).getGameType().equals("League")) {
                            cpuGames = allGameFixtures.get(
                                    userTeam.getLeagueMatchesPlayed());
                        } else if (((Game) event).getGameType().equals("Cup")) {
                            cpuGames = championsLeagueFixtures.get(
                                    userTeam.getCupMatchesPlayed());
                        }
                        this.add(new HomeGameDisplay((Game) event, userTeam,
                                clock, this, cpuGames), BorderLayout.CENTER);
                        this.revalidate();
                    }
                }
            }
        }
        return label;
    }

    /**
     * Adds a game to the userEvents arrayList.
     * Used to add additional fixtures mid-season based on cup results.
     * @param userGame The game to add to the userEvents arrayList.
     */
    public void addUserGame(final Event userGame) {
        userEvents.add(userGame);
    }

    /**
     * Adds a arrayList of games representing a round of champions league
     * fixtures (for all teams, not just user teams.)
     * This is so non-user games can be simulated in the background while
     * the user plays their game.
     * @param roundOfChampionsLeagueFixtures arrayList of a round of champions
     *                                       league fixtures. (e.g. QF leg 1)
     */
    public void addRoundOfChampionsLeagueFixtures(
            final ArrayList<Game> roundOfChampionsLeagueFixtures) {
        championsLeagueFixtures.add(roundOfChampionsLeagueFixtures);
    }

    /**
     * Regenerates all the fixtures for the season.
     * Wipes all team's fixture lists and regenerates fixtures for each league.
     * Each game assigns itself to both it's home and away team's fixtures,
     * giving every team a complete list of their fixtures for the season.
     * @param userTeam the user's team.
     */
    public void regenerateFixtures(final Team userTeam) {

        Cup championsLeague = Data.world.getCupByName("UEFA Champions League");
        //wipe all fixtures for all teams and reset champions league groups.
        for (Team team : Data.world.getAllTeams()) {
            team.wipeFixtures();
            team.resetChampionsLeagueGroupStage();
        }

        //update champions league teams for the season.
        championsLeague.updateChampionsLeagueTeams();

        //wipe users events to make room for new fixtures.
        userEvents = new ArrayList<>();

        //regenerate league fixtures and champions league fixtures.
        allGameFixtures = userTeam.getLeague().generateLeagueFixtures();
        championsLeagueFixtures =
                fixtureGen.generateChampionsLeagueFixtureSchedule();

        //add all UCL fixtures to the teams' arraylist of fixtures.
        for (ArrayList<Game> fixtureWeek : championsLeagueFixtures) {
            for (Game game : fixtureWeek) {
                game.getHomeTeam().addFixture(game);
                game.getAwayTeam().addFixture(game);
            }
        }

        //set all qualified UCL teams to advance by default.
        for (Team uclTeam : championsLeague.getTeams()) {
            uclTeam.setAdvancingToNextRound(true);
        }

        ArrayList<Game> fixtures = userTeam.getFixtures();
        userEvents.addAll(fixtures);
    }

    /**
     * @return the progress date button (at the bottom of the main UI). So its
     * properties can be accessed outside the UI class.
     */
    public Button getProgressDateButton() {
        return progressDateButton;
    }
}
