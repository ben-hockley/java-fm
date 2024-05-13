public class EventType {
    private String eventTypeName;
    private String eventTypeLogo;
    //constructor
    public EventType(String name, String logo){
        this.eventTypeName = name;
        this.eventTypeLogo = logo;
    }
    //getters
    public String getEventTypeName(){
        return eventTypeName;
    }
    public String getEventTypeLogo(){
        return eventTypeLogo;
    }
}
