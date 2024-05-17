package events;

public abstract class Event {

    /**
     * Integer[] array length 2, contains the day of the month, and the month
     * number of the event respectively.
     * Used the access modifier : protected rather than private so that the
     * subclasses can access the date.
     */
    protected Integer[] date;

    /**
     * Date of events is defined as args
     * in the constructors of event's subclasses.
     * @return the day of the month in which the event is scheduled.
     */
    public Integer getDayOfMonth() {
        return date[0];
    }

    /**
     *
     * @return the month number in which the event is scheduled.
     */
    public Integer getMonth() {
        return date[1];
    }
}
