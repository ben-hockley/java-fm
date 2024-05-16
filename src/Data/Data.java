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
    public static Nation ireland = new Nation("Ireland", "Ireland.png", 18, "Europe");
    public static Nation hungary = new Nation("Hungary", "Hungary.png", 19, "Europe");
    public static Nation egypt = new Nation("Egypt", "Egypt.png", 20, "Africa");
    public static Nation colombia = new Nation("Colombia", "Colombia.png", 21, "South America");
    public static Nation uruguay = new Nation("Uruguay", "Uruguay.png", 22, "South America");
    public static Nation sweden = new Nation("Sweden", "Sweden.png", 23, "Europe");
    public static Nation poland = new Nation("Poland", "Poland.png", 25, "Europe");
    public static Nation jamaica = new Nation("Jamaica", "Jamaica.png", 26, "North America");


    public static void setData() {
        //League : Premier League
        League premierLeague = new League("Premier League", "PremierLeague.png", 1, england);

        //Team : Manchester City
        Team manCity = new Team("Manchester City", "Man City", "manchesterCity.png", premierLeague, new Integer[]{4, 3, 3});
        new Player("Ederson", "", "GK", manCity, brazil, 90);
        new Player("Stefan", "Ortega", "GK", manCity, germany, 83);

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

        //Team : Arsenal
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

        //Team : Liverpool
        Team liverpool = new Team("Liverpool", "Liverpool", "Liverpool.png", premierLeague, new Integer[]{4, 3, 3});
        new Player("Alisson", "", "GK", liverpool, brazil, 92);
        new Player("Caoimhin", "Kelleher", "GK", liverpool, ireland, 80);

        new Player("Virgil", "Van Dijk", "DEF", liverpool, netherlands, 92);
        new Player("Trent", "Alexander-Arnold", "DEF", liverpool, england, 89);
        new Player("Andrew", "Robertson", "DEF", liverpool, scotland, 88);
        new Player("Ibrahima", "Konate", "DEF", liverpool, france, 85);
        new Player("Joe", "Gomez", "DEF", liverpool, england, 80);
        new Player("Jarell", "Quansah", "DEF", liverpool, england, 74);

        new Player("Dominik", "Szoboszlai", "MID", liverpool, hungary, 88);
        new Player("Alexis", "Mac Allister", "MID", liverpool, argentina, 86);
        new Player("Curtis", "Jones", "MID", liverpool, england, 80);
        new Player("Harvey", "Elliott", "MID", liverpool, england, 78);
        new Player("Ryan", "Gravenberch", "MID", liverpool, netherlands, 77);

        new Player("Mohamed", "Salah", "FWD", liverpool, egypt, 94);
        new Player("Luiz", "Diaz", "FWD", liverpool, colombia, 85);
        new Player("Diogo", "Jota", "FWD", liverpool, portugal, 84);
        new Player("Darwin", "Nunez", "FWD", liverpool, uruguay, 83);
        new Player("Cody", "Gakpo", "FWD", liverpool, netherlands, 81);

        //Team : Aston Villa
        Team astonVilla = new Team("Aston Villa", "Aston Villa", "AstonVilla.png", premierLeague, new Integer[]{4, 3, 3});
        new Player("Emiliano", "Martinez", "GK", astonVilla, argentina, 88);
        new Player("Robin", "Olsen", "GK", astonVilla, sweden, 72);

        new Player("Pau", "Torres", "DEF", astonVilla, spain, 88);
        new Player("Matty", "Cash", "DEF", astonVilla, poland, 85);
        new Player("Ezri", "Konsa", "DEF", astonVilla, england, 84);
        new Player("Diego", "Carlos", "DEF", astonVilla, brazil, 83);
        new Player("Tyrone", "Mings", "DEF", astonVilla, england, 80);
        new Player("Lucas", "Digne", "DEF", astonVilla, france, 78);

        new Player("Douglas", "Luiz", "MID", astonVilla, brazil, 86);
        new Player("John", "McGinn", "MID", astonVilla, scotland, 85);
        new Player("Nicolo", "Zaniolo", "MID", astonVilla, italy, 84);
        new Player("Boubacar", "Kamara", "MID", astonVilla, france, 82);
        new Player("Youri", "Tielemans", "MID", astonVilla, belgium, 80);

        new Player("Ollie", "Watkins", "FWD", astonVilla, england, 86);
        new Player("Moussa", "Diaby", "FWD", astonVilla, france, 85);
        new Player("Jacob", "Ramsey", "FWD", astonVilla, england, 82);
        new Player("Leon", "Bailey", "FWD", astonVilla, jamaica, 81);
        new Player("Emi", "Buendia", "FWD", astonVilla, argentina, 80);
    }

}
