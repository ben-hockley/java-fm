package data;

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
    public static Nation chile = new Nation("Chile", "Chile.png", 17, "South America");
    public static Nation colombia = new Nation("Colombia", "Colombia.png", 21, "South America");
    public static Nation uruguay = new Nation("Uruguay", "Uruguay.png", 22, "South America");
    public static Nation sweden = new Nation("Sweden", "Sweden.png", 23, "Europe");
    public static Nation poland = new Nation("Poland", "Poland.png", 25, "Europe");
    public static Nation jamaica = new Nation("Jamaica", "Jamaica.png", 26, "North America");
    public static Nation romania = new Nation("Romania", "Romania.png", 28, "Europe");
    public static Nation senegal = new Nation("Senegal", "Senegal.png", 29, "Africa");
    public static Nation southKorea = new Nation("South Korea", "SouthKorea.png", 30, "Asia");
    public static Nation wales = new Nation("Wales", "Wales.png", 31, "Europe");
    public static Nation ecuador = new Nation("Ecuador", "Ecuador.png", 32, "South America");
    public static Nation denmark = new Nation("Denmark", "Denmark.png", 33, "Europe");
    public static Nation mali = new Nation("Mali", "Mali.png", 34, "Africa");
    public static Nation cameroon = new Nation("Cameroon", "Cameroon.png", 35, "Africa");
    public static Nation turkey = new Nation("Turkey", "Turkey.png", 36, "Europe");
    public static Nation morocco = new Nation("Morocco", "Morocco.png", 37, "Africa");
    public static Nation slovakia = new Nation("Slovakia", "Slovakia.png", 38, "Europe");
    public static Nation greece = new Nation("Greece", "Greece.png", 39, "Europe");
    public static Nation czechRepublic = new Nation("Czech Republic", "CzechRepublic.png", 40, "Europe");
    public static Nation mexico = new Nation("Mexico", "Mexico.png", 41, "North America");
    public static Nation paraguay = new Nation("Paraguay", "Paraguay.png", 42, "South America");
    public static Nation kosovo = new Nation("Kosovo", "Kosovo.png", 44, "Europe");
    public static Nation southAfrica = new Nation("South Africa", "SouthAfrica.png", 45, "Africa");
    public static Nation usa = new Nation("United States", "USA.png", 44, "North America");
    public static Nation zimbabwe = new Nation("Zimbabwe", "Zimbabwe.png", 45, "Africa");
    public static Nation gabon = new Nation("Gabon", "Gabon.png", 45, "Africa");
    public static Nation algeria = new Nation("Algeria", "Algeria.png", 46, "Africa");
    public static Nation nigeria = new Nation("Nigeria", "Nigeria.png", 48, "Africa");
    public static Nation newZealand = new Nation("New Zealand", "NewZealand.png", 49, "Oceania");
    public static Nation albania = new Nation("Albania", "Albania.png", 50, "Europe");
    public static Nation ivoryCoast = new Nation("Ivory Coast", "IvoryCoast.png", 43, "Africa");

    public static void setPremierLeague() {
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

        //Team : Spurs
        Team spurs = new Team("Tottenham Hotspur", "Spurs", "Spurs.png", premierLeague, new Integer[]{4, 3, 3});
        new Player("Guglielmo", "Vicario", "GK", spurs, italy, 85);
        new Player("Fraser", "Forster", "GK", spurs, england, 70);

        new Player("Christian", "Romero", "DEF", spurs, argentina, 88);
        new Player("Micky", "van de Ven", "DEF", spurs, netherlands, 85);
        new Player("Destiny", "Udogie", "DEF", spurs, italy, 84);
        new Player("Pedro", "Porro", "DEF", spurs, spain, 83);
        new Player("Radu", "Dragusin", "DEF", spurs, romania, 80);
        new Player("Emerson", "Royal", "DEF", spurs, brazil, 78);

        new Player("James", "Maddison", "MID", spurs, england, 88);
        new Player("Pape", "Sarr", "MID", spurs, senegal, 84);
        new Player("Yves", "Bissouma", "MID", spurs, mali, 84);
        new Player("Sergio", "Bentancur", "MID", spurs, uruguay, 83);
        new Player("Oliver", "Skipp", "MID", spurs, england, 79);
        new Player("Pierre", "Hojbjerg", "MID", spurs, denmark, 78);

        new Player("Hueng-Min", "Son", "FWD", spurs, southKorea, 92);
        new Player("Brennan", "Johnson", "FWD", spurs, wales, 82);
        new Player("Richarlison", "", "FWD", spurs, brazil, 82);
        new Player("Bryan", "Gil", "FWD", spurs, spain, 78);
        new Player("Timo", "Werner", "FWD", spurs, germany, 77);

        //Team : Chelsea
        Team chelsea = new Team("Chelsea", "Chelsea", "Chelsea.png", premierLeague, new Integer[]{4, 3, 3});
        new Player("Robert", "Sanchez", "GK", chelsea, spain, 85);
        new Player("Donde", "Petrovic", "GK", chelsea, croatia, 80);

        new Player("Thiago", "Silva", "DEF", chelsea, brazil, 90);
        new Player("Reece", "James", "DEF", chelsea, england, 88);
        new Player("Ben", "Chilwell", "DEF", chelsea, england, 87);
        new Player("Trevoh", "Chalobah", "DEF", chelsea, england, 85);
        new Player("Alex", "Disasi", "DEF", chelsea, france, 83);
        new Player("Marc", "Cucurella", "DEF", chelsea, spain, 80);

        new Player("Conor", "Gallagher", "MID", chelsea, england, 86);
        new Player("Moises", "Caicedo", "MID", chelsea, ecuador, 81);
        new Player("Enzo", "Fernandez", "MID", chelsea, argentina, 81);
        new Player("Romeo", "Lavia", "MID", chelsea, belgium, 80);
        new Player("Carney", "Chukwuemenka", "MID", chelsea, england, 79);

        new Player("Cole", "Palmer", "FWD", chelsea, england, 89);
        new Player("Christopher", "Nkunku", "FWD", chelsea, france, 86);
        new Player("Raheem", "Sterling", "FWD", chelsea, england, 85);
        new Player("Nicolas", "Jackson", "FWD", chelsea, senegal, 82);
        new Player("Mykhaylo", "Mudryk", "FWD", chelsea, ukraine, 78);

        //Team : Manchester United
        Team manUtd = new Team("Manchester United", "Man Utd", "manchesterUnited.png", premierLeague, new Integer[]{4, 3, 3});
        new Player("Andre", "Onana", "GK", manUtd, cameroon, 86);
        new Player("Altay", "Bayindir", "GK", manUtd, turkey, 80);

        new Player("Raphael", "Varane", "DEF", manUtd, france, 86);
        new Player("Lisandro", "Martinez", "DEF", manUtd, argentina, 85);
        new Player("Aaron", "Wan-Bissaka", "DEF", manUtd, england, 82);
        new Player("Luke", "Shaw", "DEF", manUtd, england, 81);
        new Player("Harry", "Maguire", "DEF", manUtd, england, 80);
        new Player("Diogo", "Dalot", "DEF", manUtd, portugal, 78);

        new Player("Bruno", "Fernandes", "MID", manUtd, portugal, 92);
        new Player("Casemiro", "", "MID", manUtd, brazil, 90);
        new Player("Scott", "McTominay", "MID", manUtd, scotland, 85);
        new Player("Kobbie", "Mainoo", "MID", manUtd, england, 80);
        new Player("Mason", "Mount", "MID", manUtd, england, 78);

        new Player("Marcus", "Rashford", "FWD", manUtd, england, 90);
        new Player("Rasmus", "Hojlund", "FWD", manUtd, denmark, 85);
        new Player("Alejandro", "Garnacho", "FWD", manUtd, argentina, 84);
        new Player("Antony", "", "FWD", manUtd, brazil, 77);
        new Player("Anthony", "Martial", "FWD", manUtd, france, 76);

        //Team : Newcastle United
        Team newcastle = new Team("Newcastle United", "Newcastle", "Newcastle.png", premierLeague, new Integer[]{4, 3, 3});
        new Player("Nick", "Pope", "GK", newcastle, england, 84);
        new Player("Martin", "Dubravka", "GK", newcastle, slovakia, 80);

        new Player("Kieran", "Trippier", "DEF", newcastle, england, 86);
        new Player("Sven", "Botman", "DEF", newcastle, netherlands, 85);
        new Player("Fabian", "Schar", "DEF", newcastle, switzerland, 80);
        new Player("Dan", "Burn", "DEF", newcastle, england, 78);
        new Player("Tino", "Livramento", "DEF", newcastle, england, 77);
        new Player("Jamaal", "Lascelles", "DEF", newcastle, england, 76);

        new Player("Bruno", "Guimaraes", "MID", newcastle, brazil, 86);
        new Player("Sandro", "Tonali", "MID", newcastle, italy, 85);
        new Player("Joelinton", "", "MID", newcastle, brazil, 83);
        new Player("Joe", "Willock", "MID", newcastle, england, 88);
        new Player("Lewis", "Miley", "MID", newcastle, england, 75);

        new Player("Alexsander", "Isak", "FWD", newcastle, sweden, 86);
        new Player("Miguel", "Almiron", "FWD", newcastle, paraguay, 85);
        new Player("Anthony", "Gordon", "FWD", newcastle, england, 82);
        new Player("Harvey", "Barnes", "FWD", newcastle, england, 80);
        new Player("Callum", "Wilson", "FWD", newcastle, england, 76);

        //Team : West Ham United
        Team westHam = new Team("West Ham United", "West Ham", "WestHam.png", premierLeague, new Integer[]{4, 3, 3});
        new Player("Lukasz", "Fabianski", "GK", westHam, poland, 82);
        new Player("Alphonse", "Areola", "GK", westHam, france, 80);

        new Player("Kurt", "Zouma", "DEF", westHam, france, 86);
        new Player("Nayef", "Aguerd", "DEF", westHam, morocco, 83);
        new Player("Aaron", "Cresswell", "DEF", westHam, england, 82);
        new Player("Vladimir", "Coufal", "DEF", westHam, czechRepublic, 80);
        new Player("Kostantinos", "Mavropanos", "DEF", westHam, greece, 78);
        new Player("Ben", "Johnson", "DEF", westHam, england, 77);

        new Player("Lucas", "Paqueta", "MID", westHam, brazil, 88);
        new Player("Thomas", "Soucek", "MID", westHam, czechRepublic, 86);
        new Player("Edson", "Alvarez", "MID", westHam, mexico, 85);
        new Player("James", "Ward-Prowse", "MID", westHam, england, 84);
        new Player("Kalvin", "Phillips", "MID", westHam, england, 75);

        new Player("Jarrod", "Bowen", "FWD", westHam, england, 86);
        new Player("Mohamed", "Kudus", "FWD", westHam, ghana, 85);
        new Player("Michael", "Antonio", "FWD", westHam, jamaica, 84);
        new Player("Maxwel", "Cornet", "FWD", westHam, ivoryCoast, 82);
        new Player("Danny", "Ings", "FWD", westHam, england, 80);

        //Team : Brighton & Hove Albion
        Team brighton = new Team("Brighton & Hove Albion", "Brighton", "Brighton.png", premierLeague, new Integer[]{4, 3, 3});
        new Player("Bart", "Verbruggen", "GK", brighton, belgium, 80);
        new Player("Jason", "Steele", "GK", brighton, england, 74);

        new Player("Lewis", "Dunk", "DEF", brighton, england, 85);
        new Player("Adam", "Webster", "DEF", brighton, england, 82);
        new Player("Joel", "Veltman", "DEF", brighton, netherlands, 81);
        new Player("Igor", "", "DEF", brighton, brazil, 80);
        new Player("Jan-Paul", "van Hecke", "DEF", brighton, netherlands, 78);
        new Player("Valentin", "Barco", "DEF", brighton, argentina, 77);

        new Player("Pascal", "Gross", "MID", brighton, germany, 86);
        new Player("Billy", "Gilmour", "MID", brighton, scotland, 82);
        new Player("Jack", "Hinshelwood", "MID", brighton, england, 79);
        new Player("James", "Milner", "MID", brighton, england, 78);
        new Player("Facundo", "Buonanotte", "MID", brighton, argentina, 77);

        new Player("Kauro", "Mitoma", "FWD", brighton, japan, 85);
        new Player("Solly", "March", "FWD", brighton, england, 82);
        new Player("Evan", "Ferguson", "FWD", brighton, ireland, 80);
        new Player("Danny", "Welbeck", "FWD", brighton, england, 78);
        new Player("Ansu", "Fati", "FWD", brighton, spain, 77);

        //Team : Bournemouth
        Team bournemouth = new Team("AFC Bournemouth", "Bournemouth", "Bournemouth.png", premierLeague, new Integer[]{4, 3, 3});
        new Player("Neto", "", "GK", bournemouth, brazil, 80);
        new Player("Mark", "Travers", "GK", bournemouth, ireland, 74);

        new Player("Ilya", "Zabarnyi", "DEF", bournemouth, ukraine, 85);
        new Player("Lloyd", "Kelly", "DEF", bournemouth, england, 82);
        new Player("Chris", "Mepham", "DEF", bournemouth, wales, 81);
        new Player("Max", "Aarons", "DEF", bournemouth, england, 80);
        new Player("Marcos", "Senesi", "DEF", bournemouth, argentina, 78);
        new Player("Milos", "Kerkez", "DEF", bournemouth, hungary, 77);

        new Player("Phillip", "Billing", "MID", bournemouth, denmark, 86);
        new Player("Tyler", "Adams", "MID", bournemouth, usa, 84);
        new Player("Ryan", "Christie", "MID", bournemouth, scotland, 82);
        new Player("Lewis", "Cook", "MID", bournemouth, england, 82);
        new Player("Alex", "Scott", "MID", bournemouth, england, 80);

        new Player("Dominic", "Solanke", "FWD", bournemouth, england, 86);
        new Player("Luis", "Sinisterra", "FWD", bournemouth, colombia, 85);
        new Player("Justin", "Kluivert", "FWD", bournemouth, netherlands, 84);
        new Player("Enes", "Unal", "FWD", bournemouth, turkey, 82);
        new Player("Antoine", "Semenyo", "FWD", bournemouth, ghana, 80);

        //Team : Crystal Palace
        Team crystalPalace = new Team("Crystal Palace", "Crystal Palace", "CrystalPalace.png", premierLeague, new Integer[]{4, 3, 3});
        new Player("Sam", "Johnstone", "GK", crystalPalace, england, 82);
        new Player("Dean", "Henderson", "GK", crystalPalace, england, 80);

        new Player("Marc", "Guehi", "DEF", crystalPalace, england, 85);
        new Player("Joachim", "Andersen", "DEF", crystalPalace, denmark, 84);
        new Player("Tyrick", "Mitchell", "DEF", crystalPalace, england, 82);
        new Player("Joel", "Ward", "DEF", crystalPalace, england, 80);
        new Player("Daniel", "Munoz", "DEF", crystalPalace, colombia, 78);
        new Player("Nathan", "Ferguson", "DEF", crystalPalace, england, 75);

        new Player("Eberechi", "Eze", "MID", crystalPalace, england, 86);
        new Player("Adam", "Wharton", "MID", crystalPalace, england, 82);
        new Player("Jefferson", "Lerma", "MID", crystalPalace, colombia, 81);
        new Player("Will", "Hughes", "MID", crystalPalace, england, 81);
        new Player("Cheick", "Doucoure", "MID", crystalPalace, mali, 80);

        new Player("Michael", "Olise", "FWD", crystalPalace, france, 85);
        new Player("Odsonne", "Edouard", "FWD", crystalPalace, france, 84);
        new Player("Jean-Philippe", "Mateta", "FWD", crystalPalace, france, 83);
        new Player("Jeffrey", "Schlupp", "FWD", crystalPalace, ghana, 82);
        new Player("Jordan", "Ayew", "FWD", crystalPalace, ghana, 80);

        //Team : Wolverhampton Wanderers
        Team wolves = new Team("Wolverhampton Wanderers", "Wolves", "Wolves.png", premierLeague, new Integer[]{4, 3, 3});
        new Player("Jose", "Sa", "GK", wolves, portugal, 81);
        new Player("Daniel", "Bentley", "GK", wolves, england, 72);

        new Player("Max", "Kilman", "DEF", wolves, england, 85);
        new Player("Rayan", "Ait-Nouri", "DEF", wolves, algeria, 84);
        new Player("Ki-Jana", "Hoever", "DEF", wolves, netherlands, 82);
        new Player("Nelson", "Semedo", "DEF", wolves, portugal, 80);
        new Player("Toti", "", "DEF", wolves, portugal, 78);
        new Player("Craig", "Dawson", "DEF", wolves, england, 77);

        new Player("Joao", "Gomes", "MID", wolves, brazil, 82);
        new Player("Mario", "Lemina", "MID", wolves, gabon, 81);
        new Player("Tommy", "Doyle", "MID", wolves, england, 80);
        new Player("Bouboucar", "Traore", "MID", wolves, mali, 79);
        new Player("Jean-Ricner", "Bellegarde", "MID", wolves, france, 78);

        new Player("Pedro", "Neto", "FWD", wolves, portugal, 84);
        new Player("Matheus", "Cunha", "FWD", wolves, brazil, 82);
        new Player("Hwang", "Hee-Chan", "FWD", wolves, southKorea, 77);
        new Player("Pablo", "Sarabia", "FWD", wolves, spain, 77);
        new Player("Enso", "Gonzalez", "FWD", wolves, paraguay, 73);

        //Team :Fulham
        Team fulham = new Team("Fulham", "Fulham", "Fulham.png", premierLeague, new Integer[]{4, 3, 3});
        new Player("Bernd", "Leno", "GK", fulham, germany, 85);
        new Player("Marek", "Rodak", "GK", fulham, slovakia, 80);

        new Player("Tosin", "Adarabioyo", "DEF", fulham, england, 82);
        new Player("Antonee", "Robinson", "DEF", fulham, usa, 81);
        new Player("Calvin", "Bassey", "DEF", fulham, nigeria, 80);
        new Player("Tim", "Ream", "DEF", fulham, usa, 80);
        new Player("Issa", "Diop", "DEF", fulham, france, 78);
        new Player("Kenny", "Tete", "DEF", fulham, netherlands, 77);

        new Player("Joao", "Palhinha", "MID", fulham, portugal, 86);
        new Player("Harrison", "Reed", "MID", fulham, england, 82);
        new Player("Andreas", "Pereira", "MID", fulham, brazil, 81);
        new Player("Alex", "Iwobi", "MID", fulham, nigeria, 80);
        new Player("Tom", "Cairney", "MID", fulham, scotland, 79);

        new Player("Armando", "Broja", "FWD", fulham, albania, 78);
        new Player("Willian", "", "FWD", fulham, brazil, 77);
        new Player("Adama", "Traore", "FWD", fulham, spain, 77);
        new Player("Raul", "Jimenez", "FWD", fulham, mexico, 75);
        new Player("Harry", "Wilson", "FWD", fulham, wales, 75);

        //Team : Everton
        Team everton = new Team("Everton", "Everton", "Everton.png", premierLeague, new Integer[]{4, 3, 3});
        new Player("Jordan", "Pickford", "GK", everton, england, 85);
        new Player("Joao", "Virginia", "GK", everton, portugal, 80);

        new Player("Jarrad", "Branthwaite", "DEF", everton, england, 82);
        new Player("Ben", "Godfrey", "DEF", everton, england, 81);
        new Player("Michael", "Keane", "DEF", everton, england, 80);
        new Player("James", "Tarkowski", "DEF", everton, england, 80);
        new Player("Vitaliy", "Mykolenko", "DEF", everton, ukraine, 78);
        new Player("Nathan", "Patterson", "DEF", everton, scotland, 77);

        new Player("Amadou", "Onana", "MID", everton, belgium, 86);
        new Player("James", "Garner", "MID", everton, england, 82);
        new Player("Abdoulaye", "Doucoure", "MID", everton, ivoryCoast, 81);
        new Player("Andre", "Gomes", "MID", everton, portugal, 80);
        new Player("Idrissa", "Gueye", "MID", everton, senegal, 79);

        new Player("Dominic", "Calvert-Lewin", "FWD", everton, england, 86);
        new Player("Dwight", "McNeil", "FWD", everton, england, 85);
        new Player("Jack", "Harrison", "FWD", everton, england, 84);
        new Player("Beto", "", "FWD", everton, portugal, 82);
        new Player("Arnaut", "Danjuma", "FWD", everton, netherlands, 80);

        //Team : Brentford
        Team brentford = new Team("Brentford", "Brentford", "Brentford.png", premierLeague, new Integer[]{4, 3, 3});
        new Player("Mark", "Flekken", "GK", brentford, netherlands, 80);
        new Player("Thomas", "Strakosha", "GK", brentford, albania, 80);

        new Player("Ethan", "Pinnock", "DEF", brentford, england, 82);
        new Player("Nathan", "Collins", "DEF", brentford, ireland, 81);
        new Player("Rico", "Henry", "DEF", brentford, england, 80);
        new Player("Mads", "Roerslev", "DEF", brentford, denmark, 80);
        new Player("Ben", "Mee", "DEF", brentford, england, 78);
        new Player("Aaron", "Hickey", "DEF", brentford, scotland, 77);

        new Player("Christian", "Norgaard", "MID", brentford, denmark, 86);
        new Player("Mathias", "Jensen", "MID", brentford, denmark, 82);
        new Player("Vitaly", "Janelt", "MID", brentford, germany, 81);
        new Player("Josh", "Dasilva", "MID", brentford, england, 80);
        new Player("Frank", "Onyeka", "MID", brentford, nigeria, 79);

        new Player("Ivan", "Toney", "FWD", brentford, england, 86);
        new Player("Bryan", "Mbeumo", "FWD", brentford, cameroon, 85);
        new Player("Neal", "Maupay", "FWD", brentford, france, 84);
        new Player("Mikel", "Damsgaard", "FWD", brentford, denmark, 82);
        new Player("Kevin", "Schade", "FWD", brentford, germany, 80);

        //Team: Nottingham Forest
        Team nottinghamForest = new Team("Nottingham Forest", "Notts Forest", "NottinghamForest.png", premierLeague, new Integer[]{4, 3, 3});
        new Player("Matt", "Turner", "GK", nottinghamForest, usa, 80);
        new Player("Odysseas", "Vlachodimos", "GK", nottinghamForest, greece, 80);

        new Player("Murillo", "", "DEF", nottinghamForest, brazil, 82);
        new Player("Willy", "Boly", "DEF", nottinghamForest, france, 81);
        new Player("Moussa", "Niakhate", "DEF", nottinghamForest, france, 80);
        new Player("Nuno", "Tavares", "DEF", nottinghamForest, portugal, 80);
        new Player("Gonzalo", "Montiel", "DEF", nottinghamForest, argentina, 78);
        new Player("Ola", "Aina", "DEF", nottinghamForest, nigeria, 77);

        new Player("Ibrahima", "Sangare", "MID", nottinghamForest, ivoryCoast, 82);
        new Player("Ryan", "Yates", "MID", nottinghamForest, england, 77);
        new Player("Morgan", "Gibbs-White", "MID", nottinghamForest, england, 81);
        new Player("Danilo", "", "MID", nottinghamForest, brazil, 76);
        new Player("Nicolas", "Dominguez", "MID", nottinghamForest, argentina, 76);

        new Player("Chris", "Wood", "FWD", nottinghamForest, newZealand, 83);
        new Player("Callum", "Hudson-Odoi", "FWD", nottinghamForest, england, 80);
        new Player("Taiwo", "Awoniyi", "FWD", nottinghamForest, nigeria, 78);
        new Player("Antony", "Elanga", "FWD", nottinghamForest, sweden, 77);
        new Player("Giovanni", "Reyna", "FWD", nottinghamForest, usa, 75);

        //Team : Luton
        Team luton = new Team("Luton Town", "Luton", "Luton.png", premierLeague, new Integer[]{4, 3, 3});
        new Player("Thomas", "Kaminski", "GK", luton, belgium, 80);
        new Player("Tim", "Krul", "GK", luton, netherlands, 80);

        new Player("Teden", "Mengi", "DEF", luton, england, 82);
        new Player("Tom", "Lockyer", "DEF", luton, wales, 78);
        new Player("Amari'i", "Bell", "DEF", luton, jamaica, 75);
        new Player("Mads", "Andersen", "DEF", luton, denmark, 74);
        new Player("Gabriel", "Osho", "DEF", luton, nigeria, 74);
        new Player("Dan", "Potts", "DEF", luton, england, 72);

        new Player("Ross", "Barkley", "MID", luton, england, 82);
        new Player("Marvellous", "Nakamba", "MID", luton, zimbabwe, 80);
        new Player("Pelly", "Ruddock", "MID", luton, england, 78);
        new Player("Albert", "Sambi-Lokonga", "MID", luton, belgium, 77);
        new Player("Alfie", "Doughty", "MID", luton, england, 75);

        new Player("Elijah", "Adebayo", "FWD", luton, england, 80);
        new Player("Carlton", "Morris", "FWD", luton, england, 78);
        new Player("Tahith", "Chong", "FWD", luton, netherlands, 77);
        new Player("Andros", "Townsend", "FWD", luton, england, 76);
        new Player("Chiedoze", "Ogbene", "FWD", luton, ireland, 75);

        //Team: Burnley
        Team burnley = new Team("Burnley", "Burnley", "Burnley.png", premierLeague, new Integer[]{4, 3, 3});
        new Player("James", "Trafford", "GK", burnley, england, 80);
        new Player("Arijanet", "Muric", "GK", burnley, kosovo, 80);

        new Player("Jordan", "Beyer", "DEF", burnley, germany, 77);
        new Player("Dara", "O'Shea", "DEF", burnley, ireland, 76);
        new Player("Ameen", "Al-Dakhil", "DEF", burnley, belgium, 75);
        new Player("Charlie", "Taylor", "DEF", burnley, england, 74);
        new Player("Maxime", "Esteve", "DEF", burnley, france, 74);
        new Player("Vitinho", "", "DEF", burnley, brazil, 73);

        new Player("Josh", "Brownhill", "MID", burnley, england, 80);
        new Player("Sander", "Berge", "MID", burnley, norway, 80);
        new Player("Aaron", "Ramsey", "MID", burnley, england, 78);
        new Player("Josh", "Cullen", "MID", burnley, ireland, 77);
        new Player("Jack", "Cork", "MID", burnley, england, 76);

        new Player("Lyle", "Foster", "FWD", burnley, southAfrica, 78);
        new Player("Zeki", "Amdouni", "FWD", burnley, switzerland, 77);
        new Player("Mike", "Tresor", "FWD", burnley, belgium, 76);
        new Player("David", "Datro-Fofana", "FWD", burnley, ivoryCoast, 75);
        new Player("Nathan", "Redmond", "FWD", burnley, england, 75);

        //Team : Sheffield United
        Team sheffieldUnited = new Team("Sheffield United", "Sheff Utd", "SheffieldUnited.png", premierLeague, new Integer[]{4, 3, 3});
        new Player("Wes", "Foderingham", "GK", sheffieldUnited, england, 80);
        new Player("Ivo", "Grbic", "GK", sheffieldUnited, croatia, 80);

        new Player("Anel", "Ahmedhodzic", "DEF", sheffieldUnited, sweden, 80);
        new Player("Auston", "Trusty", "DEF", sheffieldUnited, usa, 78);
        new Player("Yasser", "Larouci", "DEF", sheffieldUnited, algeria, 77);
        new Player("Jaden", "Bogle", "DEF", sheffieldUnited, england, 76);
        new Player("John", "Egan", "DEF", sheffieldUnited, ireland, 75);
        new Player("Mason", "Holgate", "DEF", sheffieldUnited, england, 74);

        new Player("Gustavo", "Hamer", "MID", sheffieldUnited, netherlands, 80);
        new Player("James", "Mcatee", "MID", sheffieldUnited, england, 78);
        new Player("Tom", "Davies", "MID", sheffieldUnited, england, 77);
        new Player("Vini", "Souza", "MID", sheffieldUnited, brazil, 76);
        new Player("Andre", "Brooks", "MID", sheffieldUnited, england, 75);

        new Player("Ben", "Brereton-Diaz", "FWD", sheffieldUnited, chile, 80);
        new Player("Rhian", "Brewster", "FWD", sheffieldUnited, england, 78);
        new Player("Cameron", "Archer", "FWD", sheffieldUnited, england, 77);
        new Player("Oli", "McBurnie", "FWD", sheffieldUnited, scotland, 76);
        new Player("William", "Osula", "FWD", sheffieldUnited, denmark, 75);
    }
}
