package Data;

import Objects.Player;
import Objects.Team;
import Objects.League;
import Objects.Nation;
public class Data {
    //Nations
    public static Nation england = new Nation("England", "England.png", 4, "Europe");
    public static Nation brazil = new Nation("Brazil", "Brazil.png", 5, "South America");
    public static Nation croatia = new Nation("Croatia", "Croatia.png", 10, "Europe");
    public static Nation portugal = new Nation("Portugal", "Portugal.png", 6, "Europe");
    public static Nation spain = new Nation("Spain", "Spain.png", 8, "Europe");
    public static Nation belgium = new Nation("Belgium", "Belgium.png", 3, "Europe");
    public static Nation norway = new Nation("Norway", "Norway.png", 47, "Europe");

    //Leagues
    public static League premierLeague = new League("Premier League", "PremierLeague.png", 1, england);

    //Teams
    public static Team manCity = new Team("Manchester City", "Man City", "ManCity.png", premierLeague);

    //Players

    public static void setManCity() {
        new Player("Ederson", "", "GK", manCity, brazil, 90);
        new Player("Kyle", "Walker", "DEF", manCity, england, 89);
        new Player("Ruben", "Dias", "DEF", manCity, portugal, 88);
        new Player("John", "Stones", "DEF", manCity, england, 86);
        new Player("Josko", "Gvardiol", "DEF", manCity, croatia, 97);
        new Player("Kevin", "De Bruyne", "MID", manCity, belgium,99);
        new Player("Rodri", "", "MID", manCity, spain,98);
        new Player("Bernardo", "Silva", "MID", manCity, portugal,92);
        new Player("Phil", "Foden", "FWD", manCity, england,85);
        new Player("Jack", "Grealish", "FWD", manCity, england, 80);
        new Player("Erling", "Haaland", "FWD", manCity, norway, 99);
    }

}
