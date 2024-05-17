package Events;

public abstract class Event {
    protected Integer[] date; //protected so that subclasses can access it.
    //getters
    public Integer getDayOfMonth(){
        return date[0];
    }
    public Integer getMonth(){
        return date[1];
    }
}
