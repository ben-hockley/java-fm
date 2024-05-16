package JFrames;

import Events.Game;
import JPanels.CalendarPanel;
import JPanels.HomeDefaultDisplay;
import JPanels.HomeGameDisplay;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import Objects.*;
import Events.Event;

import Data.Data;

//JAVA swing to create the user interface.
public class UI extends JFrame {

    //ArrayList to store all events. Events can be added and removed.
    ArrayList<Event> events = new ArrayList<Event>();

    /**
     * The clock object used to track date and month to update the calendar.
     */
    private final dateTime clock;
    /**
     * The panel that contains the calendar, applied NORTH in the BorderLayout.
     */
    private final JPanel topPanel;

    private final String myTeamName;

    private JPanel homeDefaultDisplay;

    String[] testStandings = {"1. Sheffield Wednesday", "2. Manchester United", "3. Liverpool", "4. Chelsea", "5. Arsenal"};
    //constructor to initialize homepage.
    public UI(Team userTeam) {
        this.myTeamName = userTeam.getTeamName();


        // Just testing data here, will be removed later.
        Game game1 =new Game(Data.england.getLeagueByTier(1).getTeamByName("Manchester City"), Data.england.getLeagueByTier(1).getTeamByName("Arsenal"), 4, 1);
        events.add(game1);


        this.setTitle("JFrames.UI");
        this.setSize(1000, 500);
        this.setLayout(new BorderLayout());
        this.setResizable(false);


        // Create a new JPanel (North)
        topPanel = new CalendarPanel();
        clock = new dateTime();

        //set calendar to default date (1st January)
        updateCalendar(clock.getDateNumber());

        this.add(topPanel, BorderLayout.NORTH);

        Button progressDateButton = new Button("Progress Date");
        progressDateButton.setPreferredSize(new Dimension(100, 50));
        progressDateButton.addActionListener(e -> {
            clock.progressDate();
            updateCalendar(clock.getDateNumber());
        });
        this.add(progressDateButton, BorderLayout.SOUTH);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    Boolean homeDisplaySet;
    public void updateCalendar(final Integer dateNumber) {
        topPanel.removeAll();
        topPanel.add(new JLabel(clock.getMonthName()));
        homeDisplaySet = false;


        for (int i = dateNumber - 2; i <= dateNumber + 2; i++) {
            topPanel.add(label(i));
        }
        topPanel.revalidate();
        if (!homeDisplaySet) {
            homeDefaultDisplay = new HomeDefaultDisplay(myTeamName, testStandings);
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
    public JLabel label(final Integer dateNumber) {
        JLabel label = new JLabel();
        label.setFont(new Font("Arial", Font.PLAIN, 10));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        label.setOpaque(true);
        label.setLayout(new GridLayout(1,2));

        JLabel subLabel1 = new JLabel();
        JLabel subLabel2 = new JLabel();

        if (dateNumber > 0 && dateNumber <= clock.getMonthLength()) {
            label.setText(dateNumber.toString());
            if (dateNumber.equals(clock.getDateNumber())) {
                label.setBackground(Color.BLUE);
            }
        }
        // If the date is not in the current month, set the background to black.
        else {
            label.setBackground(Color.BLACK);
        }
        // Check if there is an event on this date, add the event logo to the label if there is.
        for (Event event : events) {
            if (event.getDate().equals(dateNumber) && event.getMonth().equals(clock.getMonthNumber())) {
                label.setBackground(Color.yellow);
                if (event instanceof Game) {
                    subLabel1.setText(((Game) event).homeTeam.getShortName());
                    subLabel1.setIcon(new ImageIcon(new ImageIcon("teamImages/" + ((Game) event).homeTeam.getTeamLogo()).getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH)));
                    subLabel1.setHorizontalTextPosition(JLabel.CENTER);
                    subLabel1.setVerticalTextPosition(JLabel.BOTTOM);

                    subLabel2.setText((((Game) event).awayTeam.getShortName()));
                    subLabel2.setIcon(new ImageIcon(new ImageIcon("teamImages/" + ((Game) event).awayTeam.getTeamLogo()).getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH)));
                    subLabel2.setHorizontalTextPosition(JLabel.CENTER);
                    subLabel2.setVerticalTextPosition(JLabel.BOTTOM);

                    label.add(subLabel1);
                    label.add(subLabel2);
                }

                if (dateNumber.equals(clock.getDateNumber())) {
                    this.remove(homeDefaultDisplay);
                    label.setBackground(Color.BLUE);
                    homeDisplaySet = true;
                    if (event instanceof Game) {
                        homeDefaultDisplay.setVisible(false); // Hide the default display
                        this.add(new HomeGameDisplay(((Game) event).homeTeam,((Game) event).awayTeam, testStandings), BorderLayout.CENTER);
                        this.revalidate();
                    }
                }
            }
        }
        return label;
    }
}
