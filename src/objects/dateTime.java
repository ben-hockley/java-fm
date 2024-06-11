package objects;

public class dateTime {
    /**
     * The lengths of each month in days.
     */
    private final Integer[] monthLengths =
            new Integer[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    /**
     * The names of each month.
     */
    private final String[] monthNames =
            new String[]{"January", "February", "March", "April", "May", "June",
                    "July", "August", "September", "October", "November", "December"};
    /**
     * The day of the month, increments by 1 each day, and resets to one
     * at the end of the month.
     */
    private Integer dateNumber;
    /**
     * The month of the year, increments by 1 each month, and resets to one
     * at the end of the year.
     */
    private Integer monthNumber;
    /**
     * The year of the simulation, increments by 1 each year.
     */
    private Integer yearNumber;
    /**
     * Creates a new dateTime object with the given starting date.
     * Used to feed the calendarPanel to the north of the main UI.
     * @param startingDate The starting date of the simulation.
     */
    public dateTime(final Integer[] startingDate) {
        this.dateNumber = startingDate[0];
        this.monthNumber = startingDate[1];
        this.yearNumber = startingDate[2];
    }

    /**
     * Increments the date by one day.
     * Resets month/ increments year if necessary.
     */
    public void progressDate() {
        this.dateNumber += 1;

        int monthLength = monthLengths[monthNumber - 1];
        if (dateNumber > monthLength) {
            this.dateNumber = 1;
            this.monthNumber += 1;
        }
        if (monthNumber > 12) {
            this.monthNumber = 1;
            this.yearNumber += 1;
        }
    }

    /**
     * returns the date of the month.
     * @return the date of the month (e.g. 5)
     */
    public Integer getDateNumber() {
        return dateNumber;
    }
    /**
     * returns the month of the year.
     * @return the month of the year (e.g. 3)
     */
    public Integer getMonthNumber() {
        return monthNumber;
    }

    /**
     * returns the year of the simulation.
     * @return the year of the simulation (e.g. 2021)
     */
    public Integer getYearNumber() {
        return yearNumber;
    }
    /**
     * returns the length of the current month, from the list of month lengths.
     * @return the length of the current month (e.g. 31)
     */
    public Integer getMonthLength() {
        return monthLengths[monthNumber - 1];
    }
    /**
     * returns the name of the current month, from the list of month names.
     * @return the name of the current month (e.g. "March")
     */
    public String getMonthName() {
        return monthNames[monthNumber - 1];
    }
}
