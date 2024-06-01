package JFrames;

import events.Game;
import JPanels.CalendarPanel;
import JPanels.HomeDefaultDisplay;
import JPanels.HomeGameDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Objects.*;
import events.Event;

//JAVA swing to create the user interface.
public class UI extends JFrame {

    //ArrayList to store all events. Events can be added and removed.
    ArrayList<Event> events = new ArrayList<>();

    ArrayList<Game> cpuGames;

    /**
     * The clock object used to track date and month to update the calendar.
     */
    private final dateTime clock;
    /**
     * The panel that contains the calendar, applied NORTH in the BorderLayout.
     */
    private final JPanel topPanel; //JPanel to hold the calendar. (North)
    private final Button progressDateButton; //Button to progress the date. (South)
    private JPanel homeDefaultDisplay;

    ArrayList<ArrayList<Game>> allGameFixtures;
    //constructor to initialize homepage.
    public UI(Team userTeam) {

        allGameFixtures = userTeam.getLeague().generateFixtures();

        ArrayList<Game> fixtures = userTeam.getFixtures();
        events.addAll(fixtures); //add the fixtures to the events list.

        //set basic properties of the JFrame.
        this.setSize(1000, 600);
        this.pack();
        this.setMinimumSize(new Dimension(1000, 600));
        this.setLayout(new BorderLayout());
        //this.setResizable(false);


        // Add calendar JPanel to the North of the BorderLayout.
        topPanel = new CalendarPanel();
        clock = new dateTime(new Integer[]{1, 8, 2023}); //pass starting date as args.
        updateCalendar(clock.getDateNumber(), userTeam); //set calendar to default date.
        this.add(topPanel, BorderLayout.NORTH);

        // Add a button to progress the date to the South of the BorderLayout.
        //Button to progress the date. (South)
        progressDateButton = new Button("Progress Date");
        progressDateButton.setPreferredSize(new Dimension(100, 50));
        progressDateButton.addActionListener(e -> {
            clock.progressDate();
            updateCalendar(clock.getDateNumber(), userTeam);
        });
        progressDateButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_T) {

                    if (clock.getMonthNumber().equals(1) || clock.getMonthNumber().equals(6) || clock.getMonthNumber().equals(7) || clock.getMonthNumber().equals(8)){
                        new transferMarket(userTeam);
                    } else {
                        JOptionPane.showMessageDialog(null, "Transfer window shut, you can buy and sell players in January, June, July and August.");
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

            homeDefaultDisplay = new HomeDefaultDisplay(actualStandings, userTeam, clock);
            this.add(homeDefaultDisplay, BorderLayout.CENTER);
            homeDisplaySet = true;
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
    public JLabel label(final Integer dateNumber, Team userTeam) {
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
        for (Event event : events) {
            if (event.getDayOfMonth().equals(dateNumber) && event.getMonth().equals(clock.getMonthNumber())) {
                label.setBackground(Color.yellow); //set the label to yellow when there is an event on this date.
                if (event instanceof Game) {

                    //add the home team's name and badge to the left of the label.
                    subLabel1.setText(((Game) event).getHomeTeam().getShortName());
                    subLabel1.setIcon(new ImageIcon(new ImageIcon("teamImages/" + ((Game) event).getHomeTeam().getTeamLogo()).getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH)));
                    subLabel1.setHorizontalTextPosition(JLabel.CENTER);
                    subLabel1.setVerticalTextPosition(JLabel.BOTTOM);

                    //add the away team's name and badge to the right of the label.
                    subLabel2.setText((((Game) event).getAwayTeam().getShortName()));
                    subLabel2.setIcon(new ImageIcon(new ImageIcon("teamImages/" + ((Game) event).getAwayTeam().getTeamLogo()).getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH)));
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
                    if (event instanceof Game) {
                        homeDefaultDisplay.setVisible(false); // Hide the default display
                        cpuGames = allGameFixtures.get(userTeam.getMatchesPlayed());
                        this.add(new HomeGameDisplay(((Game) event).getHomeTeam(),((Game) event).getAwayTeam(), userTeam, clock, this, cpuGames), BorderLayout.CENTER);
                        this.revalidate();
                    }
                }
            }
        }
        return label;
    }
}
