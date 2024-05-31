package events;

import Objects.Nation;

public class internationalGame extends Event{
    private final Nation homeNation;
    private final Nation awayNation;

    public internationalGame(final Nation homeNation, final Nation awayNation, final Integer[] date){
        this.homeNation = homeNation;
        this.awayNation = awayNation;
        this.date = date;
    }

    public Nation getHomeNation(){
        return homeNation;
    }

    public Nation getAwayNation(){
        return awayNation;
    }
}
