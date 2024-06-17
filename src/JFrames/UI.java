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

import events.Event;
import main.fixtureGen;
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
     * Constructor for the main UI that contains the general flow of the game
     * and the main panels.
     * @param userTeam the team the user is controlling, as selected by the
     *                 user in the game setup JFrame. (loaded by Main.java
     *                 before us.)
     */
    public UI(final Team userTeam) {


        // generates all league fixtures for the league,
        // and adds each fixture to the fixture schedule for both the home and away teams.
        regenerateFixtures(userTeam);

        //set basic properties of the JFrame.
        this.setTitle("Football Manager 2024");
        this.setSize(1000, 600);
        this.pack();
        this.setMinimumSize(new Dimension(1000, 600));
        this.setLayout(new BorderLayout());


        // Add calendar JPanel to the NORTH of the BorderLayout.
        topPanel = new CalendarPanel();
        clock = new dateTime(new Integer[]{1, 8, 2023}); //pass starting date as args.
        updateCalendar(clock.getDateNumber(), userTeam); //set calendar to default date.
        this.add(topPanel, BorderLayout.NORTH);

        // Add a button to progress the date to the SOUTH of the BorderLayout.
        progressDateButton = new Button("Progress Date (P)"); //references shortcut to progress date (P).
        progressDateButton.setPreferredSize(new Dimension(100, 50));
        progressDateButton.addActionListener(e -> {
            clock.progressDate();
            updateCalendar(clock.getDateNumber(), userTeam);

            //dispose of all frames except the main frame.
            if (Frame.getFrames().length > 1){

                for (Frame jframe : Frame.getFrames()){
                    if (!jframe.getTitle().equals("Football Manager 2024")) {
                        jframe.dispose();
                    }
                }
            }
        });


        //progress date shortcut by pressing P.
        progressDateButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_P) {
                    if (progressDateButton.isEnabled()) {
                        clock.progressDate();
                        updateCalendar(clock.getDateNumber(), userTeam);

                        //dispose of all frames except the main frame.
                        if (Frame.getFrames().length > 1){
                            for (Frame jframe : Frame.getFrames()){
                                if (!jframe.getTitle().equals("Football Manager 2024")) {
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
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_T) {

                    if (userTeam.getTeamType().equals("International")){
                        JOptionPane.showMessageDialog(null, "You are managing a national Team, so you cannot buy and sell players.");
                    } else {
                        if (clock.getMonthNumber().equals(1) || clock.getMonthNumber().equals(6) || clock.getMonthNumber().equals(7) || clock.getMonthNumber().equals(8)){
                            new transferMarket(userTeam);
                        } else {
                            JOptionPane.showMessageDialog(null, "Transfer window shut, you can buy and sell players in January, June, July and August.");
                        }
                    }
                }
            }
        });
        progressDateButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
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

    Boolean homeDisplaySet;
    public void updateCalendar(final Integer dateNumber, Team userTeam) {

        if (clock.getMonthNumber().equals(1) || clock.getMonthNumber().equals(6) || clock.getMonthNumber().equals(7) || clock.getMonthNumber().equals(8)){
            //if the transfer window is open, do between 0 and 2 random transfers per day.
            int numberOfTransfers = (int) (Math.random() * 3);

            for (int i = 0; i < numberOfTransfers; i++){
                Data.world.doRandomTransfer(userTeam, clock.getDateNumber(), clock.getMonthNumber());
            }
        }
        if (progressDateButton!=null) progressDateButton.setEnabled(true);

        topPanel.removeAll();
        topPanel.add(new JLabel(clock.getMonthName() + " " + clock.getYearNumber())); //month name to the left of the calendar.
        homeDisplaySet = false;


        for (int i = dateNumber - 2; i <= dateNumber + 2; i++) {
            topPanel.add(label(i, userTeam));
        }
        topPanel.revalidate();
        if (!homeDisplaySet) {

            ArrayList<Team> actualStandings = userTeam.getLeague().getStandings();

            homeDefaultDisplay = new HomeDefaultDisplay(actualStandings, userTeam, clock, this);
            this.add(homeDefaultDisplay, BorderLayout.CENTER);
            homeDisplaySet = true;
        }

        //transfer market updates for club teams only (irrelevant to international teams).
        if (userTeam.getTeamType().equals("Club")){
            if (clock.getMonthNumber().equals(1) && clock.getDateNumber().equals(1)){
                JOptionPane.showMessageDialog(null, "Happy New Year! It's now " + clock.getYearNumber() + "!" + ", the transfer market is open.");
            }
            if (clock.getMonthNumber().equals(6) && clock.getDateNumber().equals(1)){
                JOptionPane.showMessageDialog(null, "It's now June, the transfer market is open.");
            }
            if ((clock.getMonthNumber().equals(8) || clock.getMonthNumber().equals(1)) && clock.getDateNumber().equals(31)){
                JOptionPane.showMessageDialog(null, "It's Transfer deadline day, wrap up any last-minute deals before it's too late.");
            }
            if ((clock.getMonthNumber().equals(9) || clock.getMonthNumber().equals(2)) && clock.getDateNumber().equals(1)){
                JOptionPane.showMessageDialog(null, "The transfer window is now closed, you can no longer buy or sell players.");
            }
        }
    }
    /**
     * inner method for updateCalendar.
     * Creates a JLabel with a date as text.
     * If the dateNumber is the current date, the background is blue.
     * If the dateNumber is not in the current month, the background is black.
     * @param dateNumber The date to display.
     * @return The JLabel with the dateNumber as text.
     */
    private JLabel label(final Integer dateNumber, Team userTeam) {
        JLabel label = new JLabel();
        label.setFont(new Font("Arial", Font.PLAIN, 10));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        label.setOpaque(true);
        label.setLayout(new GridLayout(1,2));

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
        for (Event event : userEvents) {
            if (event.getDayOfMonth().equals(dateNumber) && event.getMonth().equals(clock.getMonthNumber())) {
                label.setBackground(Color.yellow); //set the label to yellow when there is an event on this date.
                if (event instanceof Game) {

                    if (((Game) event).getGameType().equals("League")){
                        label.setBackground(new Color(61, 25, 91)); // Premier league Color
                    } else if (((Game) event).getGameType().equals("Cup")){
                        label.setBackground(new Color(14, 32, 80)); // Champions league Color
                    }


                    //add the home team's name and badge to the left of the label.
                    subLabel1.setText(((Game) event).getHomeTeam().getShortName());
                    subLabel1.setForeground(Color.WHITE);
                    subLabel1.setIcon(new ImageIcon(new ImageIcon("teamImages/" + ((Game) event).getHomeTeam().getTeamLogo()).getImage().getScaledInstance(55,72, Image.SCALE_SMOOTH)));
                    subLabel1.setHorizontalAlignment(JLabel.LEFT);
                    subLabel1.setHorizontalTextPosition(JLabel.CENTER);
                    subLabel1.setVerticalTextPosition(JLabel.BOTTOM);

                    //add the away team's name and badge to the right of the label.
                    subLabel2.setText((((Game) event).getAwayTeam().getShortName()));
                    subLabel2.setForeground(Color.WHITE);
                    subLabel2.setIcon(new ImageIcon(new ImageIcon("teamImages/" + ((Game) event).getAwayTeam().getTeamLogo()).getImage().getScaledInstance(55,72, Image.SCALE_SMOOTH)));
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
                        homeDefaultDisplay.setVisible(false); // Hide the default display
                        if (((Game) event).getGameType().equals("League")){
                            cpuGames = allGameFixtures.get(userTeam.getLeagueMatchesPlayed());
                        } else if (((Game) event).getGameType().equals("Cup")){
                            cpuGames = championsLeagueFixtures.get(userTeam.getCupMatchesPlayed());
                        }
                        this.add(new HomeGameDisplay((Game) event, userTeam, clock, this, cpuGames), BorderLayout.CENTER);
                        this.revalidate();
                    }
                }
            }
        }
        return label;
    }

    public void addUserGame(Event userGame) {
        userEvents.add(userGame);
    }

    public void addRoundOfChampionsLeagueFixtures(ArrayList<Game> roundOfChampionsLeagueFixtures) {
        championsLeagueFixtures.add(roundOfChampionsLeagueFixtures);
    }
    public void regenerateFixtures(Team userTeam){

        for (Team team : Data.world.getAllTeams()){
            team.wipeFixtures();
            team.resetChampionsLeagueGroupStage();
        }

        Data.world.getCupByName("UEFA Champions League").updateChampionsLeagueTeams();

        userEvents = new ArrayList<>();

        allGameFixtures = userTeam.getLeague().generateLeagueFixtures();

        championsLeagueFixtures = fixtureGen.generateChampionsLeagueFixtureSchedule();

        for (ArrayList<Game> fixtureWeek : championsLeagueFixtures){
            for (Game game : fixtureWeek){
                game.getHomeTeam().addFixture(game);
                game.getAwayTeam().addFixture(game);
            }
        }


        for (Team uclTeam : Data.world.getCupByName("UEFA Champions League").getTeams()){
            uclTeam.setAdvancingToNextRound(true);
        }

        ArrayList<Game> fixtures = userTeam.getFixtures();
        userEvents.addAll(fixtures);
    }

    public Button getProgressDateButton() {
        return progressDateButton;
    }
}
