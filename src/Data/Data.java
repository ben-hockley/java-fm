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
    public static Nation netherlands = new Nation("Netherlands", "Netherlands.png", 16, "Europe");
    public static Nation switzerland = new Nation("Switzerland", "Switzerland.png", 13, "Europe");
    public static Nation germany = new Nation("Germany", "Germany.png", 12, "Europe");
    public static Nation argentina = new Nation("Argentina", "Argentina.png", 1, "South America");
    public static Nation france = new Nation("France", "France.png", 2, "Europe");
    public static Nation ukraine = new Nation("Ukraine", "Ukraine.png", 24, "Europe");
    public static Nation japan = new Nation("Japan", "Japan.png", 27, "Asia");
    public static Nation scotland = new Nation("Scotland", "Scotland.png", 14, "Europe");
    public static Nation ghana = new Nation("Ghana", "Ghana.png", 15, "Africa");
    public static Nation italy = new Nation("Italy", "Italy.png", 7, "Europe");

    public static void setData() {
        League premierLeague = new League("Premier League", "PremierLeague.png", 1, england);

        Team manCity = new Team("Manchester City", "Man City", "manchesterCity.png", premierLeague, new Integer[]{4, 3, 3});
        new Player("Ederson", "", "GK", manCity, brazil, 90);
        new Player("Stefan", "Ortega", "GK", manCity, germany, 85);

        new Player("Kyle", "Walker", "DEF", manCity, england, 89);
        new Player("Ruben", "Dias", "DEF", manCity, portugal, 88);
        new Player("John", "Stones", "DEF", manCity, england, 86);
        new Player("Josko", "Gvardiol", "DEF", manCity, croatia, 83);
        new Player("Nathan", "Ake", "DEF", manCity, netherlands, 82);
        new Player("Manuel", "Akanji", "DEF", manCity, switzerland, 79);

        new Player("Kevin", "De Bruyne", "MID", manCity, belgium, 94);
        new Player("Rodri", "", "MID", manCity, spain, 93);
        new Player("Bernardo", "Silva", "MID", manCity, portugal, 87);
        new Player("Mateo", "Kovacic", "MID", manCity, croatia, 83);
        new Player("Matheus", "Nunes", "MID", manCity, portugal, 77);

        new Player("Phil", "Foden", "FWD", manCity, england, 93);
        new Player("Erling", "Haaland", "FWD", manCity, norway, 95);
        new Player("Jeremy", "Doku", "FWD", manCity, belgium, 85);
        new Player("Julian", "Alvarez", "FWD", manCity, argentina, 84);
        new Player("Jack", "Grealish", "FWD", manCity, england, 82);

        Team arsenal = new Team("Arsenal", "Arsenal", "Arsenal.png", premierLeague, new Integer[]{4, 3, 3});
        new Player("David", "Raya", "GK", arsenal, spain, 85);
        new Player("Aaron", "Ramsdale", "GK", arsenal, england, 84);

        new Player("William", "Saliba", "DEF", arsenal, france, 90);
        new Player("Ben", "White", "DEF", arsenal, england, 86);
        new Player("Gabriel", "", "DEF", arsenal, brazil, 85);
        new Player("Takehiro", "Tomiyasu", "DEF", arsenal, japan, 82);
        new Player("Oleksandr", "Zinchenko", "DEF", arsenal, ukraine, 79);
        new Player("Kieran", "Tierney", "DEF", arsenal, scotland, 77);

        new Player("Declan", "Rice", "MID", arsenal, england, 90);
        new Player("Martin", "Odegaard", "MID", arsenal, norway, 88);
        new Player("Jorginho", "", "MID", arsenal, italy, 83);
        new Player("Emile", "Smith-Rowe", "MID", arsenal, england, 82);
        new Player("Thomas", "Partey", "MID", arsenal, ghana, 76);

        new Player("Bukayo", "Saka", "FWD", arsenal, england, 90);
        new Player("Gabriel", "Martinelli", "FWD", arsenal, brazil, 86);
        new Player("Kai", "Havertz", "FWD", arsenal, germany, 85);
        new Player("Gabriel", "Jesus", "FWD", arsenal, brazil, 83);
        new Player("Leandro", "Trossard", "FWD", arsenal, belgium, 82);

    }

}
