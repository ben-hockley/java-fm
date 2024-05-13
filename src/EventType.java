import javax.swing.*;

public class EventType {
    private String eventTypeName;
    private String eventTypeLogo;
    private JPanel display;
    //constructor
    public EventType(String name, String logo, JPanel display){
        this.eventTypeName = name;
        this.eventTypeLogo = logo;
        this.display = display;
    }
    //getters
    public String getEventTypeName(){
        return eventTypeName;
    }
    public String getEventTypeLogo(){
        return eventTypeLogo;
    }
    public JPanel getDisplay(){
        return display;
    }
}
