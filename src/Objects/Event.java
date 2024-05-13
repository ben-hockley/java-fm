package Objects;

public class Event {
    private EventType type;
    private Integer date;
    private Integer month;
    public Event(EventType type, Integer date, Integer month){
        this.type = type;
        this.date = date;
        this.month = month;
    }
    //getters
    public EventType getType(){
        return type;
    }
    public Integer getDate(){
        return date;
    }
    public Integer getMonth(){
        return month;
    }
}
