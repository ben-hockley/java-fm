package data;

import Objects.*;

import java.awt.*;

public class Data {
    //Nations

    public static World world = new World();

    public static Nation international = new Nation("International", "International.png", 0, "World");
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
    public static Nation austria = new Nation("Austria", "Austria.png", 51, "Europe");
    public static Nation slovenia = new Nation("Slovenia", "Slovenia.png", 52, "Europe");
    public static Nation montenegro = new Nation("Montenegro", "Montenegro.png", 53, "Europe");
    public static Nation venezuela = new Nation("Venezuela", "Venezuela.png", 54, "South America");
    public static Nation finland = new Nation("Finland", "Finland.png", 55, "Europe");
    public static Nation burkinaFaso = new Nation("Burkina Faso", "BurkinaFaso.png", 56, "Africa");
    public static Nation northMacedonia = new Nation("North Macedonia", "NorthMacedonia.png", 57, "Europe");
    public static Nation iceland = new Nation("Iceland", "Iceland.png", 58, "Europe");
    public static Nation armenia = new Nation("Armenia", "Armenia.png", 58, "Europe");
    public static Nation serbia = new Nation("Serbia", "Serbia.png", 59, "Europe");
    public static Nation guinea = new Nation("Guinea", "Guinea.png", 59, "Africa");
    public static Nation georgia = new Nation("Georgia", "Georgia.png", 60, "Europe");
    public static Nation bosniaHerzegovina = new Nation("Bosnia and Herzegovina", "BosniaHerzegovina.png", 61, "Europe");
    public static Nation canada = new Nation("Canada", "Canada.png", 55, "North America");
    public static Integer[][] listOfLeagueDates = {
            {6, 8},
            {13, 8},
            {20, 8},
            {27, 8},
            {3, 9},
            {10, 9},
            {17, 9},
            {24, 9},
            {1, 10},
            {8, 10},
            {15, 10},
            {22, 10},
            {29, 10},
            {5, 11},
            {12, 11},
            {19, 11},
            {26, 11},
            {3, 12},
            {10, 12},
            {17, 12},
            {24, 12},
            {31, 12},
            {7, 1},
            {14, 1},
            {21, 1},
            {28, 1},
            {4, 2},
            {11, 2},
            {18, 2},
            {25, 2},
            {4, 3},
            {11, 3},
            {18, 3},
            {25, 3},
            {1, 4},
            {8, 4},
            {15, 4},
            {22, 4},
            {29, 4},
            {6, 5},
            {13, 5},
            {20, 5},
            {27, 5},
            {3, 6},
            {10, 6},
            {17, 6},
            {24, 6},
            {1, 7},
            {8, 7},
            {15, 7},
            {22, 7},
            {29, 7}
    };

    public static Integer[][] listOfCupDates = {
            {9, 8},
            {16, 8},
            {23, 8},
            {30, 8},
            {6, 9},
            {13, 9},
            {20, 9},
            {27, 9},
            {4, 10},
            {11, 10},
            {18, 10},
            {25, 10},
            {1, 11},
            {8, 11},
            {15, 11},
            {22, 11},
            {29, 11},
            {6, 12},
            {13, 12},
            {20, 12},
            {27, 12},
            {3, 1},
            {10, 1},
            {17, 1},
            {24, 1},
            {31, 1},
            {7, 2},
            {14, 2},
            {21, 2},
            {28, 2},
            {7, 3},
            {14, 3},
            {21, 3},
            {28, 3},
            {4, 4},
            {11, 4},
            {18, 4},
            {25, 4},
            {2, 5},
            {9, 5},
            {16, 5},
            {23, 5},
            {30, 5},
            {6, 6},
            {13, 6},
            {20, 6},
            {27, 6},
            {4, 7},
            {11, 7},
            {18, 7},
            {25, 7}
    };



    public static String[] listOfFirstNames = {
            "James",
            "John",
            "Robert",
            "Michael",
            "William",
            "David",
            "Richard",
            "Joseph",
            "Thomas",
            "Charles",
            "Daniel",
            "Matthew",
            "Anthony",
            "Mark",
            "Paul",
            "Steven",
            "Andrew",
            "Kenneth",
            "Joshua",
            "George",
            "Kevin",
            "Brian",
            "Edward",
            "Ronald",
            "Timothy",
            "Jason",
            "Jeffrey",
            "Ryan",
            "Jacob",
            "Gary",
            "Nicholas",
            "Eric",
            "Stephen",
            "Jonathan",
            "Larry",
            "Justin",
            "Scott",
            "Brandon",
            "Benjamin",
            "Samuel",
            "Gregory",
            "Frank",
            "Alexander",
            "Raymond",
            "Patrick",
            "Jack",
            "Dennis",
            "Jerry",
            "Tyler",
            "Aaron",
            "Jose",
            "Henry",
            "Adam",
            "Douglas",
            "Nathan",
            "Peter",
            "Zachary",
            "Kyle",
            "Walter",
            "Harold",
            "Jeremy",
            "Ethan",
            "Carl",
            "Keith",
            "Roger",
            "Gerald",
            "Vincent",
            "Arthur",
            "Phillip",
            "Bobby",
            "Harry",
            "Louis",
            "Sean",
            "Todd",
            "Lawrence",
            "Austin",
            "Joe",
            "Noah",
            "Jesse",
            "Jordan",
            "Bryan",
            "Billy",
            "Bruce",
            "Albert",
            "Willie",
            "Gabriel",
            "Logan",
            "Alan",
            "Juan",
            "Wayne",
            "Roy",
            "Ralph",
            "Randy",
            "Eugene",
            "Vincent",
            "Russell",
            "Elijah",
            "Louis",
            "Bobby",
            "Philip",
            "Johnny",
            "Howard",
            "Dylan",
            "Cameron",
            "Shawn",
            "Gordon",
            "Martin",
            "Jay",
            "Dean",
            "Oscar",
            "Jimmy",
            "Jared"
    };
    public static String[] listOfLastNames = {
            "Smith",
            "Johnson",
            "Williams",
            "Jones",
            "Brown",
            "Davis",
            "Miller",
            "Wilson",
            "Moore",
            "Taylor",
            "Anderson",
            "Thomas",
            "Jackson",
            "White",
            "Harris",
            "Martin",
            "Thompson",
            "Garcia",
            "Martinez",
            "Robinson",
            "Clark",
            "Rodriguez",
            "Lewis",
            "Lee",
            "Walker",
            "Hall",
            "Allen",
            "Young",
            "Hernandez",
            "King",
            "Wright",
            "Lopez",
            "Hill",
            "Scott",
            "Green",
            "Adams",
            "Baker",
            "Gonzalez",
            "Nelson",
            "Carter",
            "Mitchell",
            "Perez",
            "Roberts",
            "Turner",
            "Phillips",
            "Campbell",
            "Parker",
            "Evans",
            "Edwards",
            "Collins",
            "Stewart",
            "Sanchez",
            "Morris",
            "Rogers",
            "Reed",
            "Cook",
            "Morgan",
            "Bell",
            "Murphy",
            "Bailey",
            "Rivera",
            "Cooper",
            "Richardson",
            "Cox",
            "Howard",
            "Ward",
            "Torres",
            "Peterson",
            "Gray",
            "Ramirez",
            "James",
            "Watson",
            "Brooks",
            "Kelly",
            "Sanders",
            "Price",
            "Bennett",
            "Wood",
            "Barnes",
            "Ross",
            "Henderson",
            "Coleman",
            "Jenkins",
            "Perry",
            "Powell",
            "Long",
            "Patterson",
            "Hughes",
            "Flores",
            "Washington",
            "Butler",
            "Simmons",
            "Foster",
            "Gonzales",
            "Bryant",
            "Alexander",
            "Russell",
            "Griffin",
            "Diaz",
            "Hayes"
    };


    public static void setPremierLeague() {
        //League : Premier League
        League premierLeague = new League("Premier League", "PremierLeague.png", 1, england);
        //Team : Manchester City
        Team manCity = new Team("Manchester City", "Man City", "manchesterCity.png", premierLeague, new Integer[]{4, 3, 3}, new Color(108, 171, 221), 250);
        new Player("Ederson", "", "GK", manCity, brazil, 90, 30);
        new Player("Stefan", "Ortega", "GK", manCity, germany, 80, 31);

        new Player("Kyle", "Walker", "DEF", manCity, england, 90, 33);
        new Player("Ruben", "Dias", "DEF", manCity, portugal, 90, 27);
        new Player("John", "Stones", "DEF", manCity, england, 88, 29);
        new Player("Josko", "Gvardiol", "DEF", manCity, croatia, 85, 22);
        new Player("Nathan", "Ake", "DEF", manCity, netherlands, 82, 29);
        new Player("Manuel", "Akanji", "DEF", manCity, switzerland, 79, 31);
        new Player("Rico", "Lewis", "DEF", manCity, england, 75, 19);

        new Player("Kevin", "De Bruyne", "MID", manCity, belgium, 97, 32);
        new Player("Rodri", "", "MID", manCity, spain, 95, 27);
        new Player("Bernardo", "Silva", "MID", manCity, portugal, 87, 29);
        new Player("Mateo", "Kovacic", "MID", manCity, croatia, 83, 30);
        new Player("Matheus", "Nunes", "MID", manCity, portugal, 77, 25);

        new Player("Phil", "Foden", "FWD", manCity, england, 95, 23);
        new Player("Erling", "Haaland", "FWD", manCity, norway, 95, 23);
        new Player("Jeremy", "Doku", "FWD", manCity, belgium, 88, 21);
        new Player("Julian", "Alvarez", "FWD", manCity, argentina, 84, 24);
        new Player("Jack", "Grealish", "FWD", manCity, england, 82, 28);
        new Player("Oscar", "Bobb", "FWD", manCity, norway, 78, 20);

        //Team : Arsenal
        Team arsenal = new Team("Arsenal", "Arsenal", "Arsenal.png", premierLeague, new Integer[]{4, 3, 3}, new Color(239, 1, 7), 200);
        new Player("David", "Raya", "GK", arsenal, spain, 85, 28);
        new Player("Aaron", "Ramsdale", "GK", arsenal, england, 84, 26);

        new Player("William", "Saliba", "DEF", arsenal, france, 93, 23);
        new Player("Ben", "White", "DEF", arsenal, england, 88, 26);
        new Player("Gabriel", "", "DEF", arsenal, brazil, 88, 26);
        new Player("Takehiro", "Tomiyasu", "DEF", arsenal, japan, 84, 25);
        new Player("Oleksandr", "Zinchenko", "DEF", arsenal, ukraine, 79, 27);
        new Player("Kieran", "Tierney", "DEF", arsenal, scotland, 77, 26);

        new Player("Declan", "Rice", "MID", arsenal, england, 94, 25);
        new Player("Martin", "Odegaard", "MID", arsenal, norway, 92, 25);
        new Player("Jorginho", "", "MID", arsenal, italy, 85, 32);
        new Player("Emile", "Smith-Rowe", "MID", arsenal, england, 77, 23);
        new Player("Thomas", "Partey", "MID", arsenal, ghana, 76, 30);

        new Player("Bukayo", "Saka", "FWD", arsenal, england, 94, 22);
        new Player("Kai", "Havertz", "FWD", arsenal, germany, 89, 24);
        new Player("Gabriel", "Martinelli", "FWD", arsenal, brazil, 86, 22);
        new Player("Gabriel", "Jesus", "FWD", arsenal, brazil, 83, 27);
        new Player("Leandro", "Trossard", "FWD", arsenal, belgium, 82, 29);

        //Team : Liverpool
        Team liverpool = new Team("Liverpool", "Liverpool", "Liverpool.png", premierLeague, new Integer[]{4, 3, 3}, new Color(200, 16, 46), 150);
        new Player("Alisson", "", "GK", liverpool, brazil, 92, 31);
        new Player("Caoimhin", "Kelleher", "GK", liverpool, ireland, 80, 25);

        new Player("Virgil", "Van Dijk", "DEF", liverpool, netherlands, 92, 32);
        new Player("Trent", "Alexander-Arnold", "DEF", liverpool, england, 88, 25);
        new Player("Andrew", "Robertson", "DEF", liverpool, scotland, 88, 30);
        new Player("Ibrahima", "Konate", "DEF", liverpool, france, 85, 24);
        new Player("Joe", "Gomez", "DEF", liverpool, england, 80, 26);
        new Player("Jarell", "Quansah", "DEF", liverpool, england, 74, 21);

        new Player("Dominik", "Szoboszlai", "MID", liverpool, hungary, 88, 23);
        new Player("Alexis", "Mac Allister", "MID", liverpool, argentina, 86, 25);
        new Player("Curtis", "Jones", "MID", liverpool, england, 80, 23);
        new Player("Harvey", "Elliott", "MID", liverpool, england, 78, 21);
        new Player("Ryan", "Gravenberch", "MID", liverpool, netherlands, 77, 22);

        new Player("Mohamed", "Salah", "FWD", liverpool, egypt, 94, 31);
        new Player("Luiz", "Diaz", "FWD", liverpool, colombia, 85, 27);
        new Player("Diogo", "Jota", "FWD", liverpool, portugal, 84, 27);
        new Player("Darwin", "Nunez", "FWD", liverpool, uruguay, 83, 24);
        new Player("Cody", "Gakpo", "FWD", liverpool, netherlands, 81, 25);

        //Team : Aston Villa
        Team astonVilla = new Team("Aston Villa", "Aston Villa", "AstonVilla.png", premierLeague, new Integer[]{4, 3, 3}, new Color(103, 14, 54), 100);
        new Player("Emiliano", "Martinez", "GK", astonVilla, argentina, 88, 31);
        new Player("Robin", "Olsen", "GK", astonVilla, sweden, 72, 34);

        new Player("Pau", "Torres", "DEF", astonVilla, spain, 88, 27);
        new Player("Matty", "Cash", "DEF", astonVilla, poland, 85, 26);
        new Player("Ezri", "Konsa", "DEF", astonVilla, england, 84, 26);
        new Player("Diego", "Carlos", "DEF", astonVilla, brazil, 83, 31);
        new Player("Tyrone", "Mings", "DEF", astonVilla, england, 80, 31);
        new Player("Lucas", "Digne", "DEF", astonVilla, france, 78, 30);

        new Player("Douglas", "Luiz", "MID", astonVilla, brazil, 86, 26);
        new Player("John", "McGinn", "MID", astonVilla, scotland, 85, 29);
        new Player("Nicolo", "Zaniolo", "MID", astonVilla, italy, 84, 24);
        new Player("Boubacar", "Kamara", "MID", astonVilla, france, 82, 24);
        new Player("Youri", "Tielemans", "MID", astonVilla, belgium, 80, 27);

        new Player("Ollie", "Watkins", "FWD", astonVilla, england, 86, 28);
        new Player("Moussa", "Diaby", "FWD", astonVilla, france, 85, 24);
        new Player("Jacob", "Ramsey", "FWD", astonVilla, england, 82, 22);
        new Player("Leon", "Bailey", "FWD", astonVilla, jamaica, 81, 26);
        new Player("Emi", "Buendia", "FWD", astonVilla, argentina, 80, 27);

        //Team : Spurs
        Team spurs = new Team("Tottenham Hotspur", "Spurs", "Spurs.png", premierLeague, new Integer[]{4, 3, 3}, new Color(19, 34, 87), 150);
        new Player("Guglielmo", "Vicario", "GK", spurs, italy, 85, 27);
        new Player("Fraser", "Forster", "GK", spurs, england, 70, 36);

        new Player("Christian", "Romero", "DEF", spurs, argentina, 88, 26);
        new Player("Micky", "van de Ven", "DEF", spurs, netherlands, 85, 23);
        new Player("Destiny", "Udogie", "DEF", spurs, italy, 84, 21);
        new Player("Pedro", "Porro", "DEF", spurs, spain, 83, 24);
        new Player("Radu", "Dragusin", "DEF", spurs, romania, 80, 22);
        new Player("Emerson", "Royal", "DEF", spurs, brazil, 78, 25);

        new Player("James", "Maddison", "MID", spurs, england, 88, 27);
        new Player("Pape", "Sarr", "MID", spurs, senegal, 84, 21);
        new Player("Yves", "Bissouma", "MID", spurs, mali, 84, 27);
        new Player("Sergio", "Bentancur", "MID", spurs, uruguay, 83, 26);
        new Player("Pierre", "Hojbjerg", "MID", spurs, denmark, 78, 28);

        new Player("Hueng-Min", "Son", "FWD", spurs, southKorea, 92, 31);
        new Player("Brennan", "Johnson", "FWD", spurs, wales, 82, 22);
        new Player("Richarlison", "", "FWD", spurs, brazil, 82, 27);
        new Player("Bryan", "Gil", "FWD", spurs, spain, 78, 23);
        new Player("Timo", "Werner", "FWD", spurs, germany, 77, 28);

        //Team : Chelsea
        Team chelsea = new Team("Chelsea", "Chelsea", "Chelsea.png", premierLeague, new Integer[]{4, 3, 3}, new Color(3, 70, 148), 250);
        new Player("Robert", "Sanchez", "GK", chelsea, spain, 85, 26);
        new Player("Donde", "Petrovic", "GK", chelsea, croatia, 80, 24);

        new Player("Thiago", "Silva", "DEF", chelsea, brazil, 90, 39);
        new Player("Reece", "James", "DEF", chelsea, england, 88, 24);
        new Player("Ben", "Chilwell", "DEF", chelsea, england, 87, 27);
        new Player("Trevoh", "Chalobah", "DEF", chelsea, england, 85, 24);
        new Player("Alex", "Disasi", "DEF", chelsea, france, 83, 26);
        new Player("Marc", "Cucurella", "DEF", chelsea, spain, 80, 25);

        new Player("Conor", "Gallagher", "MID", chelsea, england, 86, 24);
        new Player("Moises", "Caicedo", "MID", chelsea, ecuador, 81, 22);
        new Player("Enzo", "Fernandez", "MID", chelsea, argentina, 81, 23);
        new Player("Romeo", "Lavia", "MID", chelsea, belgium, 80, 20);
        new Player("Carney", "Chukwuemenka", "MID", chelsea, england, 79, 20);

        new Player("Cole", "Palmer", "FWD", chelsea, england, 89, 22);
        new Player("Christopher", "Nkunku", "FWD", chelsea, france, 86, 26);
        new Player("Raheem", "Sterling", "FWD", chelsea, england, 85, 29);
        new Player("Nicolas", "Jackson", "FWD", chelsea, senegal, 82, 22);
        new Player("Mykhaylo", "Mudryk", "FWD", chelsea, ukraine, 78, 23);

        //Team : Manchester United
        Team manUtd = new Team("Manchester United", "Man Utd", "manchesterUnited.png", premierLeague, new Integer[]{4, 3, 3}, new Color(218, 41, 28), 200);
        new Player("Andre", "Onana", "GK", manUtd, cameroon, 86, 28);
        new Player("Altay", "Bayindir", "GK", manUtd, turkey, 80, 26);

        new Player("Raphael", "Varane", "DEF", manUtd, france, 86, 31);
        new Player("Lisandro", "Martinez", "DEF", manUtd, argentina, 85, 26);
        new Player("Aaron", "Wan-Bissaka", "DEF", manUtd, england, 82, 26);
        new Player("Luke", "Shaw", "DEF", manUtd, england, 81, 28);
        new Player("Harry", "Maguire", "DEF", manUtd, england, 80, 31);
        new Player("Diogo", "Dalot", "DEF", manUtd, portugal, 78, 25);

        new Player("Bruno", "Fernandes", "MID", manUtd, portugal, 92, 29);
        new Player("Casemiro", "", "MID", manUtd, brazil, 90, 32);
        new Player("Scott", "McTominay", "MID", manUtd, scotland, 85, 27);
        new Player("Kobbie", "Mainoo", "MID", manUtd, england, 80, 19);
        new Player("Mason", "Mount", "MID", manUtd, england, 78, 25);

        new Player("Marcus", "Rashford", "FWD", manUtd, england, 90, 26);
        new Player("Rasmus", "Hojlund", "FWD", manUtd, denmark, 85, 21);
        new Player("Alejandro", "Garnacho", "FWD", manUtd, argentina, 84, 19);
        new Player("Antony", "", "FWD", manUtd, brazil, 77, 24);
        new Player("Anthony", "Martial", "FWD", manUtd, france, 76, 28);

        //Team : Newcastle United
        Team newcastle = new Team("Newcastle United", "Newcastle", "Newcastle.png", premierLeague, new Integer[]{4, 3, 3}, new Color(45, 41, 38), 150);
        new Player("Nick", "Pope", "GK", newcastle, england, 84, 32);
        new Player("Martin", "Dubravka", "GK", newcastle, slovakia, 80, 35);

        new Player("Kieran", "Trippier", "DEF", newcastle, england, 86, 33);
        new Player("Sven", "Botman", "DEF", newcastle, netherlands, 85, 24);
        new Player("Fabian", "Schar", "DEF", newcastle, switzerland, 80, 32);
        new Player("Dan", "Burn", "DEF", newcastle, england, 78, 32);
        new Player("Tino", "Livramento", "DEF", newcastle, england, 77, 21);
        new Player("Jamaal", "Lascelles", "DEF", newcastle, england, 76, 30);

        new Player("Bruno", "Guimaraes", "MID", newcastle, brazil, 86, 26);
        new Player("Sandro", "Tonali", "MID", newcastle, italy, 85, 24);
        new Player("Joelinton", "", "MID", newcastle, brazil, 83, 27);
        new Player("Joe", "Willock", "MID", newcastle, england, 88, 24);
        new Player("Lewis", "Miley", "MID", newcastle, england, 75, 18);

        new Player("Alexsander", "Isak", "FWD", newcastle, sweden, 86, 24);
        new Player("Miguel", "Almiron", "FWD", newcastle, paraguay, 85, 30);
        new Player("Anthony", "Gordon", "FWD", newcastle, england, 82, 23);
        new Player("Harvey", "Barnes", "FWD", newcastle, england, 80, 26);
        new Player("Callum", "Wilson", "FWD", newcastle, england, 76, 32);

        //Team : West Ham United
        Team westHam = new Team("West Ham United", "West Ham", "WestHam.png", premierLeague, new Integer[]{4, 3, 3}, new Color(122, 38, 58), 100);
        new Player("Lukasz", "Fabianski", "GK", westHam, poland, 82, 39);
        new Player("Alphonse", "Areola", "GK", westHam, france, 80, 31);

        new Player("Kurt", "Zouma", "DEF", westHam, france, 86, 29);
        new Player("Nayef", "Aguerd", "DEF", westHam, morocco, 83, 28);
        new Player("Aaron", "Cresswell", "DEF", westHam, england, 82, 34);
        new Player("Vladimir", "Coufal", "DEF", westHam, czechRepublic, 80, 31);
        new Player("Kostantinos", "Mavropanos", "DEF", westHam, greece, 78, 26);
        new Player("Ben", "Johnson", "DEF", westHam, england, 77, 24);

        new Player("Lucas", "Paqueta", "MID", westHam, brazil, 88, 26);
        new Player("Thomas", "Soucek", "MID", westHam, czechRepublic, 86, 29);
        new Player("Edson", "Alvarez", "MID", westHam, mexico, 85, 26);
        new Player("James", "Ward-Prowse", "MID", westHam, england, 84, 29);
        new Player("Kalvin", "Phillips", "MID", westHam, england, 75, 28);

        new Player("Jarrod", "Bowen", "FWD", westHam, england, 86, 27);
        new Player("Mohamed", "Kudus", "FWD", westHam, ghana, 85, 23);
        new Player("Michael", "Antonio", "FWD", westHam, jamaica, 84, 34);
        new Player("Maxwel", "Cornet", "FWD", westHam, ivoryCoast, 82, 27);
        new Player("Danny", "Ings", "FWD", westHam, england, 80, 31);

        //Team : Brighton & Hove Albion
        Team brighton = new Team("Brighton & Hove Albion", "Brighton", "Brighton.png", premierLeague, new Integer[]{4, 3, 3}, new Color(0, 87, 184), 100);
        new Player("Bart", "Verbruggen", "GK", brighton, belgium, 80, 21);
        new Player("Jason", "Steele", "GK", brighton, england, 74, 33);

        new Player("Lewis", "Dunk", "DEF", brighton, england, 85, 32);
        new Player("Adam", "Webster", "DEF", brighton, england, 82, 29);
        new Player("Joel", "Veltman", "DEF", brighton, netherlands, 81, 32);
        new Player("Igor", "", "DEF", brighton, brazil, 80, 26);
        new Player("Jan-Paul", "van Hecke", "DEF", brighton, netherlands, 78, 23);
        new Player("Valentin", "Barco", "DEF", brighton, argentina, 77, 19);

        new Player("Pascal", "Gross", "MID", brighton, germany, 86, 32);
        new Player("Billy", "Gilmour", "MID", brighton, scotland, 82, 22);
        new Player("Jack", "Hinshelwood", "MID", brighton, england, 79, 19);
        new Player("James", "Milner", "MID", brighton, england, 78, 38);
        new Player("Facundo", "Buonanotte", "MID", brighton, argentina, 77, 19);

        new Player("Kauro", "Mitoma", "FWD", brighton, japan, 85, 27);
        new Player("Solly", "March", "FWD", brighton, england, 82, 29);
        new Player("Evan", "Ferguson", "FWD", brighton, ireland, 80, 19);
        new Player("Danny", "Welbeck", "FWD", brighton, england, 78, 33);
        new Player("Ansu", "Fati", "FWD", brighton, spain, 77, 21);

        //Team : Bournemouth
        Team bournemouth = new Team("AFC Bournemouth", "Bournemouth", "Bournemouth.png", premierLeague, new Integer[]{4, 3, 3}, new Color(218, 41, 28), 60);
        new Player("Neto", "", "GK", bournemouth, brazil, 80, 34);
        new Player("Mark", "Travers", "GK", bournemouth, ireland, 74, 25);

        new Player("Ilya", "Zabarnyi", "DEF", bournemouth, ukraine, 85, 21);
        new Player("Lloyd", "Kelly", "DEF", bournemouth, england, 82, 25);
        new Player("Chris", "Mepham", "DEF", bournemouth, wales, 81, 26);
        new Player("Max", "Aarons", "DEF", bournemouth, england, 80, 24);
        new Player("Marcos", "Senesi", "DEF", bournemouth, argentina, 78, 27);
        new Player("Milos", "Kerkez", "DEF", bournemouth, hungary, 77, 20);

        new Player("Phillip", "Billing", "MID", bournemouth, denmark, 86, 27);
        new Player("Tyler", "Adams", "MID", bournemouth, usa, 84, 25);
        new Player("Ryan", "Christie", "MID", bournemouth, scotland, 82, 29);
        new Player("Lewis", "Cook", "MID", bournemouth, england, 82, 27);
        new Player("Alex", "Scott", "MID", bournemouth, england, 80, 20);

        new Player("Dominic", "Solanke", "FWD", bournemouth, england, 86, 26);
        new Player("Luis", "Sinisterra", "FWD", bournemouth, colombia, 85, 24);
        new Player("Justin", "Kluivert", "FWD", bournemouth, netherlands, 84, 25);
        new Player("Enes", "Unal", "FWD", bournemouth, turkey, 82, 27);
        new Player("Antoine", "Semenyo", "FWD", bournemouth, ghana, 80, 24);

        //Team : Crystal Palace
        Team crystalPalace = new Team("Crystal Palace", "Crystal Palace", "CrystalPalace.png", premierLeague, new Integer[]{4, 3, 3}, new Color(27, 69, 143), 100);
        new Player("Sam", "Johnstone", "GK", crystalPalace, england, 82, 31);
        new Player("Dean", "Henderson", "GK", crystalPalace, england, 80, 27);

        new Player("Marc", "Guehi", "DEF", crystalPalace, england, 85, 23);
        new Player("Joachim", "Andersen", "DEF", crystalPalace, denmark, 84, 27);
        new Player("Tyrick", "Mitchell", "DEF", crystalPalace, england, 82, 24);
        new Player("Joel", "Ward", "DEF", crystalPalace, england, 80, 34);
        new Player("Daniel", "Munoz", "DEF", crystalPalace, colombia, 78, 27);
        new Player("Nathan", "Ferguson", "DEF", crystalPalace, england, 75, 23);

        new Player("Eberechi", "Eze", "MID", crystalPalace, england, 86, 25);
        new Player("Adam", "Wharton", "MID", crystalPalace, england, 82, 20);
        new Player("Jefferson", "Lerma", "MID", crystalPalace, colombia, 81, 29);
        new Player("Will", "Hughes", "MID", crystalPalace, england, 81, 29);
        new Player("Cheick", "Doucoure", "MID", crystalPalace, mali, 80, 24);

        new Player("Michael", "Olise", "FWD", crystalPalace, france, 85, 22);
        new Player("Odsonne", "Edouard", "FWD", crystalPalace, france, 84, 26);
        new Player("Jean-Philippe", "Mateta", "FWD", crystalPalace, france, 83, 26);
        new Player("Jeffrey", "Schlupp", "FWD", crystalPalace, ghana, 82, 31);
        new Player("Jordan", "Ayew", "FWD", crystalPalace, ghana, 80, 32);

        //Team : Wolverhampton Wanderers
        Team wolves = new Team("Wolverhampton Wanderers", "Wolves", "Wolves.png", premierLeague, new Integer[]{4, 3, 3}, new Color(253, 185, 19), 60);
        new Player("Jose", "Sa", "GK", wolves, portugal, 81, 31);
        new Player("Daniel", "Bentley", "GK", wolves, england, 72, 30);

        new Player("Max", "Kilman", "DEF", wolves, england, 85, 26);
        new Player("Rayan", "Ait-Nouri", "DEF", wolves, algeria, 84, 22);
        new Player("Ki-Jana", "Hoever", "DEF", wolves, netherlands, 82, 22);
        new Player("Nelson", "Semedo", "DEF", wolves, portugal, 80, 30);
        new Player("Toti", "", "DEF", wolves, portugal, 78, 25);
        new Player("Craig", "Dawson", "DEF", wolves, england, 77, 34);

        new Player("Joao", "Gomes", "MID", wolves, brazil, 82, 23);
        new Player("Mario", "Lemina", "MID", wolves, gabon, 81, 30);
        new Player("Tommy", "Doyle", "MID", wolves, england, 80, 22);
        new Player("Bouboucar", "Traore", "MID", wolves, mali, 79, 22);
        new Player("Jean-Ricner", "Bellegarde", "MID", wolves, france, 78, 25);

        new Player("Pedro", "Neto", "FWD", wolves, portugal, 84, 24);
        new Player("Matheus", "Cunha", "FWD", wolves, brazil, 82, 24);
        new Player("Hwang", "Hee-Chan", "FWD", wolves, southKorea, 77, 28);
        new Player("Pablo", "Sarabia", "FWD", wolves, spain, 77, 32);
        new Player("Enso", "Gonzalez", "FWD", wolves, paraguay, 73, 19);

        //Team :Fulham
        Team fulham = new Team("Fulham", "Fulham", "Fulham.png", premierLeague, new Integer[]{4, 3, 3}, new Color(0, 0, 0), 60);
        new Player("Bernd", "Leno", "GK", fulham, germany, 85, 32);
        new Player("Marek", "Rodak", "GK", fulham, slovakia, 80, 27);

        new Player("Tosin", "Adarabioyo", "DEF", fulham, england, 82, 26);
        new Player("Antonee", "Robinson", "DEF", fulham, usa, 81, 26);
        new Player("Calvin", "Bassey", "DEF", fulham, nigeria, 80, 24);
        new Player("Tim", "Ream", "DEF", fulham, usa, 80, 36);
        new Player("Issa", "Diop", "DEF", fulham, france, 78, 27);
        new Player("Kenny", "Tete", "DEF", fulham, netherlands, 77, 28);

        new Player("Joao", "Palhinha", "MID", fulham, portugal, 86, 28);
        new Player("Harrison", "Reed", "MID", fulham, england, 82, 29);
        new Player("Andreas", "Pereira", "MID", fulham, brazil, 81, 28);
        new Player("Alex", "Iwobi", "MID", fulham, nigeria, 80, 28);
        new Player("Tom", "Cairney", "MID", fulham, scotland, 79, 33);

        new Player("Armando", "Broja", "FWD", fulham, albania, 78, 22);
        new Player("Willian", "", "FWD", fulham, brazil, 77, 35);
        new Player("Adama", "Traore", "FWD", fulham, spain, 77, 28);
        new Player("Raul", "Jimenez", "FWD", fulham, mexico, 75, 33);
        new Player("Harry", "Wilson", "FWD", fulham, wales, 75, 27);

        //Team : Everton
        Team everton = new Team("Everton", "Everton", "Everton.png", premierLeague, new Integer[]{4, 3, 3}, new Color(39, 68, 136), 60);
        new Player("Jordan", "Pickford", "GK", everton, england, 85, 30);
        new Player("Joao", "Virginia", "GK", everton, portugal, 80, 24);

        new Player("Jarrad", "Branthwaite", "DEF", everton, england, 82, 21);
        new Player("Ben", "Godfrey", "DEF", everton, england, 81, 26);
        new Player("Michael", "Keane", "DEF", everton, england, 80, 31);
        new Player("James", "Tarkowski", "DEF", everton, england, 80, 31);
        new Player("Vitaliy", "Mykolenko", "DEF", everton, ukraine, 78, 24);
        new Player("Nathan", "Patterson", "DEF", everton, scotland, 77, 22);

        new Player("Amadou", "Onana", "MID", everton, belgium, 86, 22);
        new Player("James", "Garner", "MID", everton, england, 82, 23);
        new Player("Abdoulaye", "Doucoure", "MID", everton, ivoryCoast, 81, 31);
        new Player("Andre", "Gomes", "MID", everton, portugal, 80, 30);
        new Player("Idrissa", "Gueye", "MID", everton, senegal, 79, 34);

        new Player("Dominic", "Calvert-Lewin", "FWD", everton, england, 86, 27);
        new Player("Dwight", "McNeil", "FWD", everton, england, 85, 24);
        new Player("Jack", "Harrison", "FWD", everton, england, 84, 27);
        new Player("Beto", "", "FWD", everton, portugal, 82, 19);
        new Player("Arnaut", "Danjuma", "FWD", everton, netherlands, 80, 27);

        //Team : Brentford
        Team brentford = new Team("Brentford", "Brentford", "Brentford.png", premierLeague, new Integer[]{4, 3, 3}, new Color(227, 6, 19), 60);
        new Player("Mark", "Flekken", "GK", brentford, netherlands, 80, 30);
        new Player("Thomas", "Strakosha", "GK", brentford, albania, 80, 29);

        new Player("Ethan", "Pinnock", "DEF", brentford, england, 82, 30);
        new Player("Nathan", "Collins", "DEF", brentford, ireland, 81, 23);
        new Player("Rico", "Henry", "DEF", brentford, england, 80, 26);
        new Player("Mads", "Roerslev", "DEF", brentford, denmark, 80, 24);
        new Player("Ben", "Mee", "DEF", brentford, england, 78, 34);
        new Player("Aaron", "Hickey", "DEF", brentford, scotland, 77, 21);

        new Player("Christian", "Norgaard", "MID", brentford, denmark, 86, 30);
        new Player("Mathias", "Jensen", "MID", brentford, denmark, 82, 28);
        new Player("Vitaly", "Janelt", "MID", brentford, germany, 81, 26);
        new Player("Josh", "Dasilva", "MID", brentford, england, 80, 25);
        new Player("Frank", "Onyeka", "MID", brentford, nigeria, 79, 26);

        new Player("Ivan", "Toney", "FWD", brentford, england, 86, 28);
        new Player("Bryan", "Mbeumo", "FWD", brentford, cameroon, 85, 24);
        new Player("Neal", "Maupay", "FWD", brentford, france, 84, 27);
        new Player("Mikel", "Damsgaard", "FWD", brentford, denmark, 82, 23);
        new Player("Kevin", "Schade", "FWD", brentford, germany, 80, 22);

        //Team: Nottingham Forest
        Team nottinghamForest = new Team("Nottingham Forest", "Notts Forest", "NottinghamForest.png", premierLeague, new Integer[]{4, 3, 3}, new Color(221, 0, 0), 60);
        new Player("Matt", "Turner", "GK", nottinghamForest, usa, 80, 29);
        new Player("Odysseas", "Vlachodimos", "GK", nottinghamForest, greece, 80, 30);

        new Player("Murillo", "", "DEF", nottinghamForest, brazil, 82, 21);
        new Player("Willy", "Boly", "DEF", nottinghamForest, ivoryCoast, 81, 33);
        new Player("Moussa", "Niakhate", "DEF", nottinghamForest, senegal, 80, 28);
        new Player("Nuno", "Tavares", "DEF", nottinghamForest, portugal, 80, 24);
        new Player("Gonzalo", "Montiel", "DEF", nottinghamForest, argentina, 78, 27);
        new Player("Ola", "Aina", "DEF", nottinghamForest, nigeria, 77, 27);

        new Player("Ibrahima", "Sangare", "MID", nottinghamForest, ivoryCoast, 82, 26);
        new Player("Ryan", "Yates", "MID", nottinghamForest, england, 77, 26);
        new Player("Morgan", "Gibbs-White", "MID", nottinghamForest, england, 81, 24);
        new Player("Danilo", "", "MID", nottinghamForest, brazil, 76, 23);
        new Player("Nicolas", "Dominguez", "MID", nottinghamForest, argentina, 76, 25);

        new Player("Chris", "Wood", "FWD", nottinghamForest, newZealand, 83, 32);
        new Player("Callum", "Hudson-Odoi", "FWD", nottinghamForest, england, 80, 23);
        new Player("Taiwo", "Awoniyi", "FWD", nottinghamForest, nigeria, 78, 26);
        new Player("Antony", "Elanga", "FWD", nottinghamForest, sweden, 77, 22);
        new Player("Giovanni", "Reyna", "FWD", nottinghamForest, usa, 75, 21);

        //Team : Luton
        Team luton = new Team("Luton Town", "Luton", "Luton.png", premierLeague, new Integer[]{4, 3, 3}, new Color(247, 143, 30), 30);
        new Player("Thomas", "Kaminski", "GK", luton, belgium, 80, 31);
        new Player("Tim", "Krul", "GK", luton, netherlands, 80, 36);

        new Player("Teden", "Mengi", "DEF", luton, england, 82, 22);
        new Player("Tom", "Lockyer", "DEF", luton, wales, 78, 29);
        new Player("Amari i", "Bell", "DEF", luton, jamaica, 75, 30);
        new Player("Mads", "Andersen", "DEF", luton, denmark, 74, 26);
        new Player("Gabriel", "Osho", "DEF", luton, nigeria, 74, 25);
        new Player("Dan", "Potts", "DEF", luton, england, 72, 30);

        new Player("Ross", "Barkley", "MID", luton, england, 82, 30);
        new Player("Marvellous", "Nakamba", "MID", luton, zimbabwe, 80, 30);
        new Player("Pelly", "Ruddock", "MID", luton, england, 78, 30);
        new Player("Albert", "Sambi-Lokonga", "MID", luton, belgium, 77, 24);
        new Player("Alfie", "Doughty", "MID", luton, england, 75, 24);

        new Player("Elijah", "Adebayo", "FWD", luton, england, 80, 26);
        new Player("Carlton", "Morris", "FWD", luton, england, 78, 28);
        new Player("Tahith", "Chong", "FWD", luton, netherlands, 77, 24);
        new Player("Andros", "Townsend", "FWD", luton, england, 76, 32);
        new Player("Chiedoze", "Ogbene", "FWD", luton, ireland, 75, 27);

        //Team: Burnley
        Team burnley = new Team("Burnley", "Burnley", "Burnley.png", premierLeague, new Integer[]{4, 3, 3}, new Color(108, 29, 69), 60);
        new Player("James", "Trafford", "GK", burnley, england, 80, 21);
        new Player("Arijanet", "Muric", "GK", burnley, kosovo, 80, 25);

        new Player("Jordan", "Beyer", "DEF", burnley, germany, 77, 24);
        new Player("Dara", "O Shea", "DEF", burnley, ireland, 76, 25);
        new Player("Ameen", "Al-Dakhil", "DEF", burnley, belgium, 75, 22);
        new Player("Charlie", "Taylor", "DEF", burnley, england, 74, 30);
        new Player("Maxime", "Esteve", "DEF", burnley, france, 74, 21);
        new Player("Vitinho", "", "DEF", burnley, brazil, 73, 24);

        new Player("Josh", "Brownhill", "MID", burnley, england, 80, 28);
        new Player("Sander", "Berge", "MID", burnley, norway, 80, 26);
        new Player("Aaron", "Ramsey", "MID", burnley, england, 78, 21);
        new Player("Josh", "Cullen", "MID", burnley, ireland, 77, 28);
        new Player("Jack", "Cork", "MID", burnley, england, 76, 34);

        new Player("Lyle", "Foster", "FWD", burnley, southAfrica, 78, 23);
        new Player("Zeki", "Amdouni", "FWD", burnley, switzerland, 77, 23);
        new Player("Mike", "Tresor", "FWD", burnley, belgium, 76, 24);
        new Player("David", "Datro-Fofana", "FWD", burnley, ivoryCoast, 75, 21);
        new Player("Nathan", "Redmond", "FWD", burnley, england, 75, 30);

        //Team : Sheffield United
        Team sheffieldUnited = new Team("Sheffield United", "Sheff Utd", "SheffieldUnited.png", premierLeague, new Integer[]{4, 3, 3}, new Color(238, 39, 55), 30);
        new Player("Wes", "Foderingham", "GK", sheffieldUnited, england, 80, 33);
        new Player("Ivo", "Grbic", "GK", sheffieldUnited, croatia, 80, 28);

        new Player("Anel", "Ahmedhodzic", "DEF", sheffieldUnited, sweden, 80, 25);
        new Player("Auston", "Trusty", "DEF", sheffieldUnited, usa, 78, 25);
        new Player("Yasser", "Larouci", "DEF", sheffieldUnited, algeria, 77, 23);
        new Player("Jaden", "Bogle", "DEF", sheffieldUnited, england, 76, 23);
        new Player("John", "Egan", "DEF", sheffieldUnited, ireland, 75, 31);
        new Player("Mason", "Holgate", "DEF", sheffieldUnited, england, 74, 27);

        new Player("Gustavo", "Hamer", "MID", sheffieldUnited, netherlands, 80, 26);
        new Player("James", "Mcatee", "MID", sheffieldUnited, england, 78, 21);
        new Player("Tom", "Davies", "MID", sheffieldUnited, england, 77, 25);
        new Player("Vini", "Souza", "MID", sheffieldUnited, brazil, 76, 24);
        new Player("Andre", "Brooks", "MID", sheffieldUnited, england, 75, 20);

        new Player("Ben", "Brereton-Diaz", "FWD", sheffieldUnited, chile, 80, 25);
        new Player("Rhian", "Brewster", "FWD", sheffieldUnited, england, 78, 24);
        new Player("Cameron", "Archer", "FWD", sheffieldUnited, england, 77, 22);
        new Player("Oli", "McBurnie", "FWD", sheffieldUnited, scotland, 76, 27);
        new Player("William", "Osula", "FWD", sheffieldUnited, denmark, 75, 20);

        world.addLeague(premierLeague);
    }

    public static void setChampionsLeague() {
        League laLiga = new League("La Liga", "LaLiga.png", 1, spain);

        Team realMadrid = new Team("Real Madrid", "Real Madrid", "RealMadrid.png", laLiga, new Integer[]{4, 3, 3}, new Color(0, 82, 159), 250);
        new Player("Thibaut", "Courtois", "GK", realMadrid, belgium, 90, 32);
        new Player("Andriy", "Lunin", "GK", realMadrid, ukraine, 80, 25);

        new Player("David", "Alaba", "DEF", realMadrid, austria, 90, 31);
        new Player("Antonio", "Rudiger", "DEF", realMadrid, germany, 88, 32);
        new Player("Eder", "Militao", "DEF", realMadrid, brazil, 85, 26);
        new Player("Dani", "Carvajal", "DEF", realMadrid, spain, 80, 32);
        new Player("Ferland", "Mendy", "DEF", realMadrid, france, 78, 28);
        new Player("Fran", "Garcia", "DEF", realMadrid, spain, 77, 24);

        new Player("Jude", "Bellingham", "MID", realMadrid, england, 92, 22);
        new Player("Federico", "Valverde", "MID", realMadrid, uruguay, 90, 25);
        new Player("Aurelien", "Tchouameni", "MID", realMadrid, france, 88, 24);
        new Player("Eduardo", "Camavinga", "MID", realMadrid, france, 87, 21);
        new Player("Luka", "Modric", "MID", realMadrid, croatia, 85, 38);

        new Player("Vinicius", "Junior", "FWD", realMadrid, brazil, 94, 23);
        new Player("Rodrygo", "Goes", "FWD", realMadrid, brazil, 90, 23);
        new Player("Endrick", "", "FWD", realMadrid, brazil, 88, 17);
        new Player("Joselu", "", "FWD", realMadrid, spain, 85, 34);
        new Player("Nico", "Paz", "FWD", realMadrid, argentina, 77, 19);

        Team Barcelona = new Team("Barcelona", "Barca", "Barcelona.png", laLiga, new Integer[]{4, 3, 3}, new Color(168, 19, 62), 200);
        new Player("Marc-Andre", "ter Stegen", "GK", Barcelona, germany, 90, 32);
        new Player("Inaki", "Pena", "GK", Barcelona, spain, 80, 25);

        new Player("Ronald", "Araujo", "DEF", Barcelona, uruguay, 88, 23);
        new Player("Jules", "Kounde", "DEF", Barcelona, france, 88, 25);
        new Player("Alejandro", "Balde", "DEF", Barcelona, spain, 85, 20);
        new Player("Pau", "Cubarsi", "DEF", Barcelona, spain, 85, 17);
        new Player("Joao", "Cancelo", "DEF", Barcelona, portugal, 84, 30);
        new Player("Andreas", "Christensen", "DEF", Barcelona, denmark, 83, 28);

        new Player("Pedri", "Gonzalez", "MID", Barcelona, spain, 90, 21);
        new Player("Frenkie", "de Jong", "MID", Barcelona, netherlands, 89, 27);
        new Player("Gavi", "Hernandez", "MID", Barcelona, spain, 88, 19);
        new Player("Ilkay", "Gundogan", "MID", Barcelona, germany, 87, 33);
        new Player("Fermin", "Lopez", "MID", Barcelona, spain, 80, 21);

        new Player("Robert", "Lewandowski", "FWD", Barcelona, poland, 94, 35);
        new Player("Raphinha", "", "FWD", Barcelona, brazil, 90, 27);
        new Player("Lamine", "Yamal", "FWD", Barcelona, spain, 88, 16);
        new Player("Joao", "Felix", "FWD", Barcelona, portugal, 84, 24);
        new Player("Vitor", "Roque", "FWD", Barcelona, brazil, 80, 19);

        Team atleticoMadrid = new Team("Atletico Madrid", "Atletico Madrid", "AtleticoMadrid.png", laLiga, new Integer[]{4, 3, 3}, new Color(228, 29, 37), 150);
        new Player("Jan", "Oblak", "GK", atleticoMadrid, slovenia, 92, 31);
        new Player("Horatiu", "Moldovan", "GK", atleticoMadrid, romania, 80, 26);

        new Player("Jose", "Giminez", "DEF", atleticoMadrid, uruguay, 84, 29);
        new Player("Stefan", "Savic", "DEF", atleticoMadrid, montenegro, 83, 33);
        new Player("Naheul", "Molina", "DEF", atleticoMadrid, argentina, 82, 26);
        new Player("Mario", "Hermoso", "DEF", atleticoMadrid, spain, 81, 28);
        new Player("Gabriel", "Paulista", "DEF", atleticoMadrid, brazil, 81, 33);
        new Player("Axel", "Witsel", "DEF", atleticoMadrid, belgium, 80, 35);

        new Player("Rodrigo", "De Paul", "MID", atleticoMadrid, argentina, 88, 28);
        new Player("Marcos", "Llorente", "MID", atleticoMadrid, spain, 87, 27);
        new Player("Arthur", "Vermeeren", "MID", atleticoMadrid, belgium, 84, 19);
        new Player("Koke", "", "MID", atleticoMadrid, spain, 83, 29);
        new Player("Pablo", "Barrios", "MID", atleticoMadrid, spain, 82, 20);

        new Player("Antoine", "Griezmann", "FWD", atleticoMadrid, france, 90, 33);
        new Player("Alvaro", "Morata", "FWD", atleticoMadrid, spain, 88, 31);
        new Player("Samuel", "Lino", "FWD", atleticoMadrid, brazil, 83, 24);
        new Player("Angel", "Correa", "FWD", atleticoMadrid, argentina, 82, 29);
        new Player("Rodrigo", "Riquelme", "FWD", atleticoMadrid, spain, 80, 24);

        Team Girona = new Team("Girona", "Girona", "Girona.png", laLiga, new Integer[]{4, 3, 3}, new Color(205, 57, 32), 60);
        new Player("Paulo", "Gazzaniga", "GK", Girona, argentina, 80, 32);
        new Player("Juan", "Carlos", "GK", Girona, spain, 72, 36);

        new Player("Miguel", "Gutiérrez", "DEF", Girona, spain, 82, 22);
        new Player("Jan", "Couto", "DEF", Girona, brazil, 81, 21);
        new Player("Eric", "Garcia", "DEF", Girona, spain, 80, 23);
        new Player("Daley", "Blind", "DEF", Girona, netherlands, 80, 34);
        new Player("Arnau", "Martinez", "DEF", Girona, spain, 78, 21);
        new Player("David", "Lopez", "DEF", Girona, spain, 77, 34);

        new Player("Aleix", "Garcia", "MID", Girona, spain, 82, 24);
        new Player("Yangel", "Herrera", "MID", Girona, venezuela, 81, 26);
        new Player("Ivan", "Martin", "MID", Girona, spain, 80, 25);
        new Player("Jhon", "Solis", "MID", Girona, colombia, 79, 19);
        new Player("Pablo", "Torre", "MID", Girona, spain, 78, 21);

        new Player("Artem", "Dovbyk", "FWD", Girona, ukraine, 86, 26);
        new Player("Viktor", "Tsygankov", "FWD", Girona, ukraine, 85, 26);
        new Player("Savio", "", "FWD", Girona, brazil, 78, 20);
        new Player("Portu", "", "FWD", Girona, spain, 77, 32);
        new Player("Christian", "Stuani", "FWD", Girona, uruguay, 76, 37);

        world.addLeague(laLiga);

        League bundesliga = new League("Bundesliga", "Bundesliga.png", 1, germany);

        Team bayernMunich = new Team("Bayern Munich", "Bayern Munich", "BayernMunich.png", bundesliga, new Integer[]{4, 3, 3}, new Color(227, 6, 19), 200);
        new Player("Manuel", "Neuer", "GK", bayernMunich, germany, 92, 38);
        new Player("Sven", "Ulreich", "GK", bayernMunich, germany, 80, 35);

        new Player("Matthijs", "de Ligt", "DEF", bayernMunich, netherlands, 90, 24);
        new Player("Alphonso", "Davies", "DEF", bayernMunich, canada, 88, 23);
        new Player("Dayot", "Upamecano", "DEF", bayernMunich, france, 86, 25);
        new Player("Raphael", "Guerrero", "DEF", bayernMunich, portugal, 83, 30);
        new Player("Kim", "Min-Jae", "DEF", bayernMunich, southKorea, 82, 27);
        new Player("Noussair", "Mazraoui", "DEF", bayernMunich, morocco, 80, 26);

        new Player("Joshua", "Kimmich", "MID", bayernMunich, germany, 92, 29);
        new Player("Jamal", "Musiala", "MID", bayernMunich, germany, 88, 21);
        new Player("Konrad", "Laimer", "MID", bayernMunich, austria, 86, 29);
        new Player("Alexsandr", "Pavlović", "MID", bayernMunich, germany, 83, 20);
        new Player("Leon", "Goretzka", "MID", bayernMunich, germany, 82, 27);

        new Player("Harry", "Kane", "FWD", bayernMunich, england, 96, 30);
        new Player("Leroy", "Sane", "FWD", bayernMunich, germany, 90, 28);
        new Player("Kingsley", "Coman", "FWD", bayernMunich, france, 88, 27);
        new Player("Serge", "Gnabry", "FWD", bayernMunich, germany, 87, 28);
        new Player("Mathys", "Tel", "FWD", bayernMunich, france, 82, 19);

        Team borussiaDortmund = new Team("Borussia Dortmund", "Dortmund", "BorussiaDortmund.png", bundesliga, new Integer[]{4, 3, 3}, new Color(253, 225, 0), 100);
        new Player("Gregor", "Kobel", "GK", borussiaDortmund, switzerland, 85, 26);
        new Player("Marcel", "Lotka", "GK", borussiaDortmund, germany, 75, 23);

        new Player("Mats", "Hummels", "DEF", borussiaDortmund, germany, 88, 35);
        new Player("Nico", "Schlotterbeck", "DEF", borussiaDortmund, germany, 85, 24);
        new Player("Ian", "Maatsen", "DEF", borussiaDortmund, netherlands, 84, 22);
        new Player("Julian", "Ryerson", "DEF", borussiaDortmund, norway, 82, 26);
        new Player("Niklas", "Sule", "DEF", borussiaDortmund, germany, 80, 28);
        new Player("Marius", "Wolf", "DEF", borussiaDortmund, germany, 78, 29);

        new Player("Marcel", "Sabitzer", "MID", borussiaDortmund, austria, 88, 30);
        new Player("Julian", "Brandt", "MID", borussiaDortmund, germany, 87, 28);
        new Player("Emre", "Can", "MID", borussiaDortmund, germany, 86, 30);
        new Player("Marco", "Reus", "MID", borussiaDortmund, germany, 85, 34);
        new Player("Felix", "Nmencha", "MID", borussiaDortmund, germany, 84, 23);

        new Player("Jadon", "Sancho", "FWD", borussiaDortmund, england, 90, 24);
        new Player("Donyell", "Malen", "FWD", borussiaDortmund, netherlands, 88, 25);
        new Player("Karim", "Adeyemi", "FWD", borussiaDortmund, germany, 86, 22);
        new Player("Niclas", "Fullkrug", "FWD", borussiaDortmund, germany, 84, 31);
        new Player("Youssoufa", "Moukoko", "FWD", borussiaDortmund, germany, 82, 19);

        Team bayerLeverkusen = new Team("Bayer Leverkusen", "Leverkusen", "BayerLeverkusen.png", bundesliga, new Integer[]{4, 3, 3}, new Color(227, 34, 33), 100);
        new Player("Matej", "Kovar", "GK", bayerLeverkusen, czechRepublic, 80, 24);
        new Player("Lukas", "Hradecky", "GK", bayerLeverkusen, finland, 80, 34);

        new Player("Jonathan", "Tah", "DEF", bayerLeverkusen, germany, 88, 28);
        new Player("Edmond", "Tapsoba", "DEF", bayerLeverkusen, burkinaFaso, 86, 25);
        new Player("Odilon", "Kossounou", "DEF", bayerLeverkusen, ivoryCoast, 84, 23);
        new Player("Piero", "Hincapie", "DEF", bayerLeverkusen, ecuador, 82, 22);
        new Player("Josip", "Stanisic", "DEF", bayerLeverkusen, croatia, 80, 21);
        new Player("Arthur", "", "DEF", bayerLeverkusen, brazil, 78, 21);

        new Player("Alejandro", "Grimaldo", "MID", bayerLeverkusen, spain, 88, 28);
        new Player("Granit", "Xhaka", "MID", bayerLeverkusen, switzerland, 87, 29);
        new Player("Jeremie", "Frimpong", "MID", bayerLeverkusen, netherlands, 85, 23);
        new Player("Robert", "Andrich", "MID", bayerLeverkusen, germany, 84, 29);
        new Player("Exequiel", "Palacios", "MID", bayerLeverkusen, argentina, 83, 25);

        new Player("Florian", "Wirtz", "FWD", bayerLeverkusen, germany, 90, 21);
        new Player("Victor", "Boniface", "FWD", bayerLeverkusen, nigeria, 88, 23);
        new Player("Patrik", "Schick", "FWD", bayerLeverkusen, czechRepublic, 88, 28);
        new Player("Amine", "Adli", "FWD", bayerLeverkusen, morocco, 84, 24);
        new Player("Jonas", "Hofmann", "FWD", bayerLeverkusen, germany, 82, 31);

        Team rbLeipzig = new Team("RB Leipzig", "Leipzig", "RBLeipzig.png", bundesliga, new Integer[]{4, 3, 3}, new Color(12, 32, 67), 100);
        new Player("Peter", "Gulacsi", "GK", rbLeipzig, hungary, 88, 34);
        new Player("Janis", "Blaswich", "GK", rbLeipzig, germany, 74, 33);

        new Player("Willi", "Orban", "DEF", rbLeipzig, germany, 86, 31);
        new Player("David", "Raum", "DEF", rbLeipzig, germany, 85, 26);
        new Player("Castello", "Lukeba", "DEF", rbLeipzig, germany, 84, 21);
        new Player("Benjamin", "Henrichs", "DEF", rbLeipzig, germany, 83, 27);
        new Player("Mohamed", "Simakan", "DEF", rbLeipzig, france, 82, 24);
        new Player("El Chadaille", "Bitshiabu", "DEF", rbLeipzig, germany, 80, 19);

        new Player("Dani", "Olmo", "MID", rbLeipzig, spain, 88, 24);
        new Player("Xaver", "Schlager", "MID", rbLeipzig, austria, 84, 26);
        new Player("Amadou", "Haidara", "MID", rbLeipzig, mali, 84, 26);
        new Player("Nicolas", "Seiwald", "MID", rbLeipzig, austria, 83, 23);
        new Player("Christoph", "Baumgartner", "MID", rbLeipzig, austria, 82, 24);

        new Player("Lois", "Openda", "FWD", rbLeipzig, belgium, 89, 24);
        new Player("Xavi", "Simons", "FWD", rbLeipzig, netherlands, 89, 21);
        new Player("Benjamin", "Sesko", "FWD", rbLeipzig, austria, 85, 20);
        new Player("Eljif", "Elmas", "FWD", rbLeipzig, northMacedonia, 79, 24);
        new Player("Yussuf", "Poulsen", "FWD", rbLeipzig, denmark, 76, 29);

        world.addLeague(bundesliga);

        League serieA = new League("Serie A", "SerieA.png", 1, italy);

        Team interMilan = new Team("Inter Milan", "Inter", "InterMilan.png", serieA, new Integer[]{4, 3, 3}, new Color(1, 14, 128), 150);
        new Player("Yann", "Sommer", "GK", interMilan, switzerland, 88, 33);
        new Player("Emil", "Audero", "GK", interMilan, italy, 80, 25);

        new Player("Allessandro", "Bastoni", "DEF", interMilan, italy, 88, 25);
        new Player("Benjamin", "Pavard", "DEF", interMilan, france, 87, 28);
        new Player("Federico", "Dimarco", "DEF", interMilan, italy, 86, 26);
        new Player("Denzel", "Dumfries", "DEF", interMilan, netherlands, 85, 28);
        new Player("Matteo", "Darmian", "DEF", interMilan, italy, 84, 34);
        new Player("Stefan", "de Vrij", "DEF", interMilan, netherlands, 82, 32);

        new Player("Nicolo", "Barella", "MID", interMilan, italy, 90, 27);
        new Player("Hakan", "Calhanoglu", "MID", interMilan, turkey, 88, 30);
        new Player("Davide", "Frattesi", "MID", interMilan, italy, 86, 24);
        new Player("Carlos", "Augusto", "MID", interMilan, brazil, 80, 25);
        new Player("Kristjan", "Asllani", "MID", interMilan, albania, 79, 22);

        new Player("Lautaro", "Martinez", "FWD", interMilan, argentina, 90, 26);
        new Player("Marcus", "Thuram", "FWD", interMilan, france, 88, 26);
        new Player("Henrikh", "Mkhitaryan", "FWD", interMilan, armenia, 86, 35);
        new Player("Alexis", "Sanchez", "FWD", interMilan, chile, 80, 35);
        new Player("Marko", "Arnautovic", "FWD", interMilan, austria, 79, 35);

        Team acMilan = new Team("AC Milan", "AC Milan", "ACMilan.png", serieA, new Integer[]{4, 3, 3}, new Color(251, 9, 11), 100);
        new Player("Mike", "Maignan", "GK", acMilan, france, 88, 28);
        new Player("Marco", "Sportiello", "GK", acMilan, italy, 80, 32);

        new Player("Theo", "Hernandez", "DEF", acMilan, france, 88, 26);
        new Player("Fikayo", "Tomori", "DEF", acMilan, england, 87, 24);
        new Player("Davide", "Calabria", "DEF", acMilan, italy, 84, 27);
        new Player("Malik", "Thiaw", "DEF", acMilan, germany, 84, 22);
        new Player("Pierre", "Kalulu", "DEF", acMilan, france, 83, 23);
        new Player("Matteo", "Gabbia", "DEF", acMilan, italy, 82, 24);

        new Player("Ismael", "Bennacer", "MID", acMilan, algeria, 88, 26);
        new Player("Tijjani", "Reijnders", "MID", acMilan, netherlands, 86, 23);
        new Player("Ruben", "Loftus-Cheek", "MID", acMilan, england, 85, 28);
        new Player("Yunus", "Musah", "MID", acMilan, usa, 84, 21);
        new Player("Yacine", "Adli", "MID", acMilan, france, 83, 23);

        new Player("Rafael", "Leao", "FWD", acMilan, portugal, 88, 24);
        new Player("Christian", "Pulisic", "FWD", acMilan, usa, 84, 25);
        new Player("Olivier", "Giroud", "FWD", acMilan, france, 84, 37);
        new Player("Noah", "Okafor", "FWD", acMilan, switzerland, 82, 24);
        new Player("Samuel", "Chukwueze", "FWD", acMilan, nigeria, 80, 25);

        Team juventus = new Team("Juventus", "Juventus", "Juventus.png", serieA, new Integer[]{4, 3, 3}, new Color(0, 0, 0), 100);
        new Player("Wojciech", "Szczesny", "GK", juventus, poland, 88, 34);
        new Player("Mattia", "Perin", "GK", juventus, italy, 80, 31);

        new Player("Bremer", "", "DEF", juventus, brazil, 83, 27);
        new Player("Tiago", "Djalo", "DEF", juventus, portugal, 81, 24);
        new Player("Andrea", "Cambiasso", "DEF", juventus, italy, 80, 24);
        new Player("Alex", "Sandro", "DEF", juventus, brazil, 80, 33);
        new Player("Federico", "Gatti", "DEF", juventus, italy, 79, 25);
        new Player("Danilo", "", "DEF", juventus, brazil, 79, 32);

        new Player("Adrien", "Rabiot", "MID", juventus, france, 88, 29);
        new Player("Manuel", "Locatelli", "MID", juventus, italy, 83, 24);
        new Player("Weston", "McKennie", "MID", juventus, usa, 82, 23);
        new Player("Fabio", "Miretti", "MID", juventus, italy, 80, 20);
        new Player("Carlos", "Alcaraz", "MID", juventus, spain, 80, 21);

        new Player("Dusan", "Vlahovic", "FWD", juventus, serbia, 88, 21);
        new Player("Federico", "Chiesa", "FWD", juventus, italy, 85, 26);
        new Player("Moise", "Kean", "FWD", juventus, italy, 83, 24);
        new Player("Samuel", "Iling-Junior", "FWD", juventus, england, 80, 20);
        new Player("Timothy", "Weah", "FWD", juventus, usa, 80, 24);

        Team napoli = new Team("Napoli", "Napoli", "Napoli.png", serieA, new Integer[]{4, 3, 3}, new Color(0, 70, 147), 100);
        new Player("Alex", "Meret", "GK", napoli, italy, 85, 27);
        new Player("Pierluigi", "Gollini", "GK", napoli, italy, 80, 29);

        new Player("Amir", "Rrahmani", "DEF", napoli, kosovo, 85, 30);
        new Player("Mathias", "Olivera", "DEF", napoli, uruguay, 83, 26);
        new Player("Giovanni", "Di Lorenzo", "DEF", napoli, italy, 80, 30);
        new Player("Natan", "Silva", "DEF", napoli, brazil, 79, 23);
        new Player("Mario", "Rui", "DEF", napoli, portugal, 78, 33);
        new Player("Leo", "Ostigard", "DEF", napoli, norway, 76, 24);

        new Player("Frank", "Anguissa", "MID", napoli, cameroon, 86, 28);
        new Player("Piotr", "Zielinski", "MID", napoli, poland, 85, 30);
        new Player("Stanislav", "Lobotka", "MID", napoli, slovakia, 84, 27);
        new Player("Jesper", "Lindstrom", "MID", napoli, denmark, 79, 24);
        new Player("Leander", "Dendoncker", "MID", napoli, belgium, 78, 29);

        new Player("Victor", "Osimhen", "FWD", napoli, nigeria, 88, 25);
        new Player("Khvicha", "Kvaratskhelia", "FWD", napoli, georgia, 88, 23);
        new Player("Giacamo", "Raspadori", "FWD", napoli, italy, 83, 24);
        new Player("Matteo", "Politano", "FWD", napoli, italy, 80, 30);
        new Player("Cyril", "Ngonge", "FWD", napoli, belgium, 79, 24);

        world.addLeague(serieA);

        League ligue1 = new League("Ligue 1", "Ligue1.png", 1, france);

        Team psg = new Team("Paris Saint-Germain", "PSG", "ParisSaintGermain.png", ligue1, new Integer[]{4, 3, 3}, new Color(1, 66, 106), 250);
        new Player("Gianluigi", "Donnarumma", "GK", psg, italy, 90, 25);
        new Player("Arnau", "Tenas", "GK", psg, spain, 76, 23);

        new Player("Marquinhos", "", "DEF", psg, brazil, 90, 30);
        new Player("Milan", "Skriniar", "DEF", psg, slovakia, 88, 29);
        new Player("Achraf", "Hakimi", "DEF", psg, morocco, 86, 25);
        new Player("Nuno", "Mendes", "DEF", psg, portugal, 85, 21);
        new Player("Lucas", "Hernandez", "DEF", psg, france, 85, 28);
        new Player("Lucas", "Beraldo", "DEF", psg, brazil, 84, 20);

        new Player("Vitinha", "", "MID", psg, portugal, 88, 24);
        new Player("Manuel", "Ugarte", "MID", psg, uruguay, 86, 23);
        new Player("Warren", "Zaire-Emery", "MID", psg, france, 85, 18);
        new Player("Kang-In", "Lee", "MID", psg, southKorea, 83, 21);
        new Player("Danilo", "Pereira", "MID", psg, portugal, 80, 32);

        new Player("Kylian", "Mbappe", "FWD", psg, france, 98, 25);
        new Player("Randal", "Kolo", "FWD", psg, france, 88, 25);
        new Player("Ousmane", "Dembele", "FWD", psg, france, 87, 27);
        new Player("Goncalo", "Ramos", "FWD", psg, portugal, 85, 22);
        new Player("Bradley", "Barcola", "FWD", psg, france, 80, 21);

        Team monaco = new Team("AS Monaco", "Monaco", "ASMonaco.png", ligue1, new Integer[]{4, 3, 3}, new Color(229, 27, 34), 100);
        new Player("Philipp", "Kohn", "GK", monaco, switzerland, 77, 26);
        new Player("Radoslaw", "Majewski", "GK", monaco, poland, 75, 24);

        new Player("Caio", "Henrique", "DEF", monaco, brazil, 80, 26);
        new Player("Mohammed", "Salisu", "DEF", monaco, ghana, 78, 25);
        new Player("Thilo", "Kehrer", "DEF", monaco, germany, 77, 27);
        new Player("Wilfried", "Singo", "DEF", monaco, ivoryCoast, 76, 24);
        new Player("Vanderson", "", "DEF", monaco, brazil, 75, 22);
        new Player("Soungoutou", "Magassa", "DEF", monaco, france, 74, 20);

        new Player("Youssouf", "Fofana", "MID", monaco, france, 80, 25);
        new Player("Denis", "Zakaria", "MID", monaco, switzerland, 78, 27);
        new Player("Mohamed", "Camara", "MID", monaco, mali, 77, 24);
        new Player("Eliesse", "Ben Seghir", "MID", monaco, france, 76, 19);
        new Player("Maghnes", "Akliouche", "MID", monaco, france, 75, 19);

        new Player("Folarin", "Balogun", "FWD", monaco, usa, 85, 22);
        new Player("Wissam", "Ben Yedder", "FWD", monaco, france, 84, 31);
        new Player("Takumi", "Minamino", "FWD", monaco, japan, 80, 29);
        new Player("Breel", "Embolo", "FWD", monaco, switzerland, 79, 27);
        new Player("Krepin", "Diatta", "FWD", monaco, senegal, 78, 25);

        Team lyon = new Team("Olympique Lyon", "Lyon", "OlympiqueLyon.png", ligue1, new Integer[]{4, 3, 3}, new Color(20, 56, 127), 60);
        new Player("Anthony", "Lopes", "GK", lyon, portugal, 85, 33);
        new Player("Lucas", "Perri", "GK", lyon, brazil, 75, 26);

        new Player("Nicolas", "Tagliafico", "DEF", lyon, argentina, 86, 31);
        new Player("Duje", "Caleta-Car", "DEF", lyon, croatia, 83, 27);
        new Player("Jake", "O Brien", "DEF", lyon, ireland, 82, 23);
        new Player("Sael", "Kumbedi", "DEF", lyon, france, 77, 19);
        new Player("Dejan", "Lovren", "DEF", lyon, croatia, 76, 34);
        new Player("Sinaly", "Diomande", "DEF", lyon, ivoryCoast, 75, 23);

        new Player("Orel", "Mangala", "MID", lyon, belgium, 84, 26);
        new Player("Maxence", "Caqueret", "MID", lyon, france, 83, 24);
        new Player("Johann", "Lepenant", "MID", lyon, france, 82, 19);
        new Player("Paul", "Akouokou", "MID", lyon, ivoryCoast, 80, 20);
        new Player("Malick", "Fofana", "MID", lyon, belgium, 79, 19);

        new Player("Said", "Benrahma", "FWD", lyon, algeria, 82, 28);
        new Player("Alexandre", "Lacazette", "FWD", lyon, france, 82, 33);
        new Player("Rayan", "Cherki", "FWD", lyon, france, 80, 20);
        new Player("Gift", "Orban", "FWD", lyon, nigeria, 76, 21);
        new Player("Ernest", "Nuamah", "FWD", lyon, ghana, 75, 20);

        Team lille = new Team("LOSC Lille", "Lille", "Lille.png", ligue1, new Integer[]{4, 3, 3}, new Color(224, 30, 19), 60);
        new Player("Lucas", "Chevalier", "GK", lille, france, 80, 22);
        new Player("Vito", "Mannone", "GK", lille, italy, 75, 36);

        new Player("Lenny", "Yoro", "DEF", lille, france, 82, 18);
        new Player("Alexsandro", "", "DEF", lille, brazil, 81, 24);
        new Player("Bafode", "Diakite", "DEF", lille, france, 80, 24);
        new Player("Tiago", "Santos", "DEF", lille, portugal, 79, 21);
        new Player("Samuel", "Umtiti", "DEF", lille, france, 78, 30);
        new Player("Rafael", "Fernandes", "DEF", lille, portugal, 77, 21);

        new Player("Angel", "Gomes", "MID", lille, england, 84, 23);
        new Player("Nabil", "Bentaleb", "MID", lille, algeria, 83, 29);
        new Player("Yusuf", "Yazici", "MID", lille, turkey, 82, 27);
        new Player("Ignacio", "Miramon", "MID", lille, argentina, 80, 20);
        new Player("Ayyoub", "Bouaddi", "MID", lille, france, 79, 16);

        new Player("Jonathan", "David", "FWD", lille, canada, 86, 22);
        new Player("Edon", "Zhegrova", "FWD", lille, kosovo, 84, 25);
        new Player("Adam", "Ounas", "FWD", lille, algeria, 82, 27);
        new Player("Hakon", "Arnar Haraldsson", "FWD", lille, iceland, 80, 21);
        new Player("Tiago", "Morais", "FWD", lille, portugal, 78, 20);

        world.addLeague(ligue1);

        League eredivisie = new League("Eredivisie", "Eredivisie.png", 1, netherlands);

        Team ajax = new Team("Ajax", "Ajax", "Ajax.png", eredivisie, new Integer[]{4, 3, 3}, new Color(210, 18, 46), 60);
        new Player("Diant", "Ramaj", "GK", ajax, germany, 78, 22);
        new Player("Geronimo", "Rulli", "GK", ajax, argentina, 78, 32);

        new Player("Josip", "Sutalo", "DEF", ajax, croatia, 82, 24);
        new Player("Borna", "Sosa", "DEF", ajax, croatia, 81, 26);
        new Player("Jorrel", "Hato", "DEF", ajax, netherlands, 80, 18);
        new Player("Devyne", "Rensch", "DEF", ajax, netherlands, 79, 21);
        new Player("Ahmetcan", "Kaplan", "DEF", ajax, turkey, 75, 21);
        new Player("Anton", "Gaaei", "DEF", ajax, denmark, 74, 20);

        new Player("Branco", "van den Boomen", "MID", ajax, netherlands, 82, 28);
        new Player("Jordan", "Henderson", "MID", ajax, england, 81, 33);
        new Player("Kenneth", "Taylor", "MID", ajax, netherlands, 80, 22);
        new Player("Sivert", "Mannsverk", "MID", ajax, norway, 76, 22);
        new Player("Silvano", "Vos", "MID", ajax, netherlands, 75, 19);

        new Player("Steven", "Bergwijn", "FWD", ajax, netherlands, 86, 26);
        new Player("Brian", "Brobbey", "FWD", ajax, netherlands, 83, 20);
        new Player("Steven", "Berghuis", "FWD", ajax, netherlands, 80, 30);
        new Player("Chuba", "Akpom", "FWD", ajax, nigeria, 80, 26);
        new Player("Carlos", "Forbs", "FWD", ajax, portugal, 76, 20);

        Team psv = new Team("PSV", "PSV", "PSV.png", eredivisie, new Integer[]{4, 3, 3}, new Color(240, 0, 0), 60);
        new Player("Walter", "Benitez", "GK", psv, argentina, 85, 31);
        new Player("Joel", "Drommel", "GK", psv, netherlands, 76, 27);

        new Player("Olivier", "Boscagli", "DEF", psv, france, 82, 27);
        new Player("Armel", "Bella-Kotchap", "DEF", psv, germany, 81, 22);
        new Player("Sergino", "Dest", "DEF", psv, usa, 80, 23);
        new Player("Jordan", "Teze", "DEF", psv, netherlands, 79, 24);
        new Player("Patrick", "van Aanholt", "DEF", psv, netherlands, 74, 33);
        new Player("Armando", "Obispo", "DEF", psv, netherlands, 73, 25);

        new Player("Jerdy", "Schouten", "MID", psv, netherlands, 82, 27);
        new Player("Joey", "Veerman", "MID", psv, netherlands, 81, 25);
        new Player("Malik", "Tillman", "MID", psv, usa, 80, 22);
        new Player("Ismael", "Saibari", "MID", psv, morocco, 79, 23);
        new Player("Richard", "Ledezma", "MID", psv, usa, 74, 23);

        new Player("Johan", "Bakayoko", "FWD", psv, belgium, 84, 22);
        new Player("Hirving", "Lozano", "FWD", psv, mexico, 83, 28);
        new Player("Ricardo", "Pepi", "FWD", psv, usa, 82, 21);
        new Player("Luuk", "de Jong", "FWD", psv, netherlands, 80, 33);
        new Player("Noa", "Lang", "FWD", psv, netherlands, 79, 24);

        world.addLeague(eredivisie);

        League primeraDivision = new League("Primera Division", "PrimeraDivision.png", 1, portugal);

        Team benfica = new Team("Benfica", "Benfica", "Benfica.png", primeraDivision, new Integer[]{4, 3, 3}, new Color(232, 48, 48), 60);
        new Player("Anatoliy", "Trubin", "GK", benfica, ukraine, 84, 22);
        new Player("Samuel", "Soares", "GK", benfica, portugal, 75, 21);

        new Player("Antonio", "Silva", "DEF", benfica, portugal, 85, 20);
        new Player("Nicolas", "Otamendi", "DEF", benfica, argentina, 82, 36);
        new Player("Juan", "Bernat", "DEF", benfica, spain, 81, 31);
        new Player("Alexander", "Bah", "DEF", benfica, denmark, 78, 26);
        new Player("Morato", "", "DEF", benfica, brazil, 77, 22);
        new Player("Alvaro", "Carreras", "DEF", benfica, spain, 76, 21);

        new Player("Joao", "Neves", "MID", benfica, portugal, 84, 19);
        new Player("Florentino", "Luis", "MID", benfica, portugal, 83, 24);
        new Player("Orkun", "Kokcu", "MID", benfica, turkey, 82, 23);
        new Player("Frederik", "Aursenes", "MID", benfica, norway, 78, 28);
        new Player("Joao", "Mario", "MID", benfica, portugal, 77, 31);

        new Player("David", "Neres", "FWD", benfica, brazil, 86, 27);
        new Player("Angel", "Di Maria", "FWD", benfica, argentina, 85, 36);
        new Player("Arthur", "Cabral", "FWD", benfica, brazil, 82, 26);
        new Player("Marcos", "Leonardo", "FWD", benfica, brazil, 80, 21);
        new Player("Casper", "Tengstedt", "FWD", benfica, denmark, 76, 23);

        Team sportingCP = new Team("Sporting Lisbon", "Sporting CP", "SportingCP.png", primeraDivision, new Integer[]{4, 3, 3}, new Color(0, 128, 87), 60);
        new Player("Antonio", "Adan", "GK", sportingCP, spain, 80, 37);
        new Player("Franco", "Israel", "GK", sportingCP, uruguay, 75, 24);

        new Player("Goncalo", "Inacio", "DEF", sportingCP, portugal, 85, 22);
        new Player("Ousmane", "Diomande", "DEF", sportingCP, ivoryCoast, 83, 20);
        new Player("Ivan", "Frasneda", "DEF", sportingCP, spain, 78, 19);
        new Player("Nuno", "Santos", "DEF", sportingCP, portugal, 77, 29);
        new Player("Sebastian", "Coates", "DEF", sportingCP, uruguay, 76, 33);
        new Player("Jerry", "St Juste", "DEF", sportingCP, netherlands, 75, 27);

        new Player("Morten", "Hjulmand", "MID", sportingCP, denmark, 82, 24);
        new Player("Pedro", "Goncalves", "MID", sportingCP, portugal, 81, 25);
        new Player("Hidemasa", "Morita", "MID", sportingCP, japan, 75, 29);
        new Player("Daniel", "Braganca", "MID", sportingCP, portugal, 74, 25);
        new Player("Koba", "Koindredi", "MID", sportingCP, france, 73, 22);

        new Player("Viktor", "Gyokeres", "FWD", sportingCP, sweden, 84, 25);
        new Player("Marcus", "Edwards", "FWD", sportingCP, england, 83, 25);
        new Player("Trincao", "", "FWD", sportingCP, portugal, 80, 24);
        new Player("Paulinho", "", "FWD", sportingCP, portugal, 77, 31);
        new Player("Rafael", "Camacho", "FWD", sportingCP, portugal, 72, 24);

        world.addLeague(primeraDivision);

        League proLeague = new League("Pro League", "ProLeague.png", 1, belgium);

        Team anderlecht = new Team("RSC Anderlecht", "Anderlecht", "RSCAnderlecht.png", proLeague, new Integer[]{4, 3, 3}, new Color(81, 46, 143), 30);
        new Player("Kasper", "Schmeichel", "GK", anderlecht, denmark, 82, 37);
        new Player("Mads", "Kikkenborg", "GK", anderlecht, denmark, 73, 24);

        new Player("Zeno", "Debast", "DEF", anderlecht, belgium, 80, 20);
        new Player("Jan", "Vertonghen", "DEF", anderlecht, belgium, 80, 37);
        new Player("Ludwig", "Augustinsson", "DEF", anderlecht, sweden, 77, 30);
        new Player("Killian", "Sardella", "DEF", anderlecht, belgium, 76, 22);
        new Player("Federico", "Gattoni", "DEF", anderlecht, argentina, 75, 25);
        new Player("Moussa", "N Diaye", "DEF", anderlecht, cameroon, 74, 21);

        new Player("Yani", "Verschaeren", "MID", anderlecht, belgium, 82, 22);
        new Player("Thomas", "Delaney", "MID", anderlecht, denmark, 81, 32);
        new Player("Majeed", "Ashimeru", "MID", anderlecht, ghana, 76, 26);
        new Player("Mario", "Stroeykens", "MID", anderlecht, belgium, 75, 19);
        new Player("Kristian", "Arnstad", "MID", anderlecht, norway, 75, 20);

        new Player("Kasper", "Dolberg", "FWD", anderlecht, denmark, 84, 26);
        new Player("Anders", "Dreyer", "FWD", anderlecht, denmark, 82, 26);
        new Player("Thorgan", "Hazard", "FWD", anderlecht, belgium, 81, 31);
        new Player("Nilson", "Angulo", "FWD", anderlecht, ecuador, 75, 21);
        new Player("Francis", "Amuzu", "FWD", anderlecht, belgium, 74, 24);

        world.addLeague(proLeague);

        League superLig = new League("Super Lig", "SuperLig.png", 1, turkey);

        Team galatasaray = new Team("Galatasaray", "Galatasaray", "Galatasaray.png", superLig, new Integer[]{4, 3, 3}, new Color(253, 185, 18), 100);
        new Player("Fernando", "Muslera", "GK", galatasaray, uruguay, 77, 37);
        new Player("Gunay", "Guvenc", "GK", galatasaray, turkey, 72, 32);

        new Player("Davinson", "Sanchez", "DEF", galatasaray, colombia, 82, 27);
        new Player("Victor", "Nelsson", "DEF", galatasaray, denmark, 80, 25);
        new Player("Serge", "Aurier", "DEF", galatasaray, ivoryCoast, 79, 31);
        new Player("Abdulkerim", "Bardakci", "DEF", galatasaray, turkey, 77, 29);
        new Player("Kaan", "Ayhan", "DEF", galatasaray, turkey, 75, 29);
        new Player("Ali", "Turap Bulbul", "DEF", galatasaray, turkey, 71, 19);

        new Player("Lucas", "Torreira", "MID", galatasaray, uruguay, 82, 28);
        new Player("Tanguy", "Ndombele", "MID", galatasaray, france, 81, 27);
        new Player("Berkan", "Kutlu", "MID", galatasaray, turkey, 76, 26);
        new Player("Eyup", "Aydin", "MID", galatasaray, turkey, 74, 19);
        new Player("Efe", "Akman", "MID", galatasaray, turkey, 73, 18);

        new Player("Mauro", "Icardi", "FWD", galatasaray, argentina, 85, 31);
        new Player("Wilfried", "Zaha", "FWD", galatasaray, ivoryCoast, 84, 31);
        new Player("Hakim", "Ziyech", "FWD", galatasaray, morocco, 81, 31);
        new Player("Kerem", "Akturkoglu", "FWD", galatasaray, turkey, 81, 25);
        new Player("Baris Alper", "Yilmaz", "FWD", galatasaray, turkey, 79, 24);

        Team fenerbahce = new Team("Fenerbahce", "Fenerbahce", "Fenerbahce.png", superLig, new Integer[]{4, 3, 3}, new Color(0, 65, 127), 100);
        new Player("Dominik", "Livakovic", "GK", fenerbahce, croatia, 82, 29);
        new Player("Irfan", "Can", "GK", fenerbahce, turkey, 75, 25);

        new Player("Caglar", "Soyuncu", "DEF", fenerbahce, turkey, 84, 28);
        new Player("Leonardo", "Bonucci", "DEF", fenerbahce, italy, 81, 37);
        new Player("Bright", "Osayi Samuel", "DEF", fenerbahce, nigeria, 80, 26);
        new Player("Ferdi", "Kadioglu", "DEF", fenerbahce, turkey, 78, 24);
        new Player("Alexander", "Dijku", "DEF", fenerbahce, ghana, 75, 29);
        new Player("Mert", "Mulder", "DEF", fenerbahce, turkey, 74, 25);

        new Player("Fred", "", "MID", fenerbahce, brazil, 84, 31);
        new Player("Sebastian", "Szymanski", "MID", fenerbahce, poland, 82, 25);
        new Player("Ismail", "Yuksek", "MID", fenerbahce, turkey, 78, 25);
        new Player("Mert", "Hakan Yandas", "MID", fenerbahce, turkey, 77, 29);
        new Player("Rade", "Krunic", "MID", fenerbahce, bosniaHerzegovina, 76, 30);

        new Player("Edin", "Dzeko", "FWD", fenerbahce, bosniaHerzegovina, 85, 38);
        new Player("Dusan", "Tadic", "FWD", fenerbahce, serbia, 82, 35);
        new Player("Irfan", "Can Kahveci", "FWD", fenerbahce, turkey, 81, 28);
        new Player("Cengiz", "Under", "FWD", fenerbahce, turkey, 80, 26);
        new Player("Joshua", "King", "FWD", fenerbahce, norway, 79, 32);
        world.addLeague(superLig);

        League scottishPremiership = new League("Scottish Premiership", "ScottishPremiership.png", 1, scotland);

        Team celtic = new Team("Celtic", "Celtic", "Celtic.png", scottishPremiership, new Integer[]{4, 3, 3}, new Color(1, 135, 73), 60);
        new Player("Joe", "Hart", "GK", celtic, england, 80, 37);
        new Player("Scott", "Bain", "GK", celtic, scotland, 74, 32);

        new Player("Cameron", "Carter-Vickers", "DEF", celtic, usa, 82, 26);
        new Player("Alistair", "Johnston", "DEF", celtic, canada, 80, 25);
        new Player("Greg", "Taylor", "DEF", celtic, scotland, 79, 26);
        new Player("Liam", "Scales", "DEF", celtic, ireland, 78, 25);
        new Player("Stephen", "Welsh", "DEF", celtic, scotland, 77, 24);
        new Player("Adam", "Montgomery", "DEF", celtic, scotland, 76, 21);

        new Player("Callum", "McGregor", "MID", celtic, scotland, 82, 30);
        new Player("Matt", "O Riley", "MID", celtic, denmark, 81, 23);
        new Player("Reo", "Hatate", "MID", celtic, japan, 79, 26);
        new Player("James", "McCarthy", "MID", celtic, ireland, 75, 33);
        new Player("Odin Thiago", "Holm", "MID", celtic, norway, 74, 21);
        new Player("Mikey", "Johnston", "MID", celtic, ireland, 74, 25);

        new Player("Kyogo", "Furuhashi", "FWD", celtic, japan, 84, 29);
        new Player("Daizen", "Maeda", "FWD", celtic, japan, 82, 26);
        new Player("Adam", "Idah", "FWD", celtic, ireland, 80, 23);
        new Player("James", "Forrest", "FWD", celtic, scotland, 79, 32);
        new Player("Hyun-Jun", "Yang", "FWD", celtic, southKorea, 75, 22);

        Team rangers = new Team("Rangers", "Rangers", "Rangers.png", scottishPremiership, new Integer[]{4, 3, 3}, new Color(27, 69, 143), 60);
        new Player("Jack", "Butland", "GK", rangers, england, 80, 31);
        new Player("Robbie", "McCrorie", "GK", rangers, scotland, 74, 26);

        new Player("James", "Tavernier", "DEF", rangers, england, 85, 32);
        new Player("Connor", "Goldson", "DEF", rangers, england, 78, 31);
        new Player("Borna", "Barisic", "DEF", rangers, croatia, 77, 31);
        new Player("John", "Souttar", "DEF", rangers, scotland, 76, 27);
        new Player("Leon", "Balogun", "DEF", rangers, nigeria, 75, 35);
        new Player("Ridvan", "Yilmaz", "DEF", rangers, turkey, 74, 23);

        new Player("John", "Lundstram", "MID", rangers, england, 77, 30);
        new Player("Todd", "Cantwell", "MID", rangers, england, 77, 26);
        new Player("Nicholas", "Raskin", "MID", rangers, belgium, 76, 23);
        new Player("Mohamed", "Diomande", "MID", rangers, ivoryCoast, 76, 22);
        new Player("Ryan", "Jack", "MID", rangers, scotland, 75, 32);

        new Player("Fabio", "Silva", "FWD", rangers, portugal, 82, 21);
        new Player("Cyril", "Dessers", "FWD", rangers, nigeria, 79, 29);
        new Player("Tom", "Lawrence", "FWD", rangers, wales, 78, 30);
        new Player("Rabbi", "Motondo", "FWD", rangers, wales, 77, 23);
        new Player("Scott", "Wright", "FWD", rangers, scotland, 75, 26);

        world.addLeague(scottishPremiership);

        League austriaBundesliga = new League("Austria Bundesliga", "AustriaBundesliga.png", 1, austria);

        Team rbSalzburg = new Team("Red Bull Salzburg", "RB Salzburg", "RBSalzburg.png", austriaBundesliga, new Integer[]{4, 3, 3}, new Color(219, 0, 0), 60);
        new Player("Alexander", "Schlager", "GK", rbSalzburg, austria, 82, 28);
        new Player("Timo", "Horn", "GK", rbSalzburg, germany, 76, 31);

        new Player("Strahinja", "Pavlovic", "DEF", rbSalzburg, serbia, 82, 23);
        new Player("Samson", "Baidoo", "DEF", rbSalzburg, austria, 80, 20);
        new Player("Amar", "Dedic", "DEF", rbSalzburg, bosniaHerzegovina, 79, 21);
        new Player("Daouda", "Guindo", "DEF", rbSalzburg, mali, 78, 21);
        new Player("Flavius", "Daniliuc", "DEF", rbSalzburg, austria, 79, 23);
        new Player("Andreas", "Ulmer", "DEF", rbSalzburg, austria, 78, 38);

        new Player("Luka", "Sucic", "MID", rbSalzburg, croatia, 82, 21);
        new Player("Nicolas", "Capaldo", "MID", rbSalzburg, argentina, 81, 23);
        new Player("Maurits", "Kjaergaard", "MID", rbSalzburg, denmark, 80, 20);
        new Player("Mads", "Bidstrup", "MID", rbSalzburg, denmark, 79, 23);
        new Player("Amankwah", "Forson", "MID", rbSalzburg, ghana, 78, 21);

        new Player("Karim", "Konate", "FWD", rbSalzburg, ivoryCoast, 78, 20);
        new Player("Roko", "Simic", "FWD", rbSalzburg, croatia, 76, 20);
        new Player("Petar", "Ratkovica", "FWD", rbSalzburg, serbia, 75, 20);
        new Player("Fernando", "", "FWD", rbSalzburg, brazil, 74, 25);
        new Player("Dijon", "Kameri", "FWD", rbSalzburg, austria, 73, 20);

        world.addLeague(austriaBundesliga);

        League greekSuperLeague = new League("Greek Super League", "GreekSuperLeague.png", 1, greece);

        Team olympiacos = new Team("Olympiacos", "Olympiacos", "Olympiacos.png", greekSuperLeague, new Integer[]{4, 3, 3}, new Color(208, 6, 31), 60);
        new Player("Konstantinos", "Tzolakis", "GK", olympiacos, greece, 75, 21);
        new Player("Alexandros", "Paschalakis", "GK", olympiacos, greece, 74, 34);

        new Player("David", "Carmo", "DEF", olympiacos, portugal, 79, 24);
        new Player("Rodinei", "", "DEF", olympiacos, brazil, 78, 32);
        new Player("Francisco", "Ortega", "DEF", olympiacos, argentina, 77, 25);
        new Player("Panagiotis", "Retsos", "DEF", olympiacos, greece, 76, 25);
        new Player("Omar", "Richards", "DEF", olympiacos, jamaica, 75, 26);
        new Player("Andreas", "Ndoj", "DEF", olympiacos, greece, 74, 21);

        new Player("Santiago", "Hezze", "MID", olympiacos, argentina, 82, 22);
        new Player("Konstantinos", "Fourtonis", "MID", olympiacos, greece, 81, 31);
        new Player("Vincent", "Iborra", "MID", olympiacos, spain, 80, 34);
        new Player("Mady", "Camara", "MID", olympiacos, guinea, 74, 24);
        new Player("Sotiris", "Alexandropoulos", "MID", olympiacos, greece, 73, 22);

        new Player("Daniel", "Podence", "FWD", olympiacos, portugal, 84, 28);
        new Player("Ayoub", "El Kaabi", "FWD", olympiacos, morocco, 80, 30);
        new Player("Youssef", "El Arabi", "FWD", olympiacos, morocco, 75, 37);
        new Player("Georgios", "Masouras", "FWD", olympiacos, greece, 74, 30);
        new Player("Algassime", "Bah", "FWD", olympiacos, guinea, 73, 21);

        world.addLeague(greekSuperLeague);

        Cup championsLeague = new Cup("UEFA Champions League", 32);
        championsLeague.addTeams(england.getLeagueByTier(1).getStandings().subList(0, 5));
        championsLeague.addTeams(spain.getLeagueByTier(1).getStandings().subList(0, 4));
        championsLeague.addTeams(italy.getLeagueByTier(1).getStandings().subList(0, 4));
        championsLeague.addTeams(germany.getLeagueByTier(1).getStandings().subList(0, 4));
        championsLeague.addTeams(france.getLeagueByTier(1).getStandings().subList(0, 4));
        championsLeague.addTeams(portugal.getLeagueByTier(1).getStandings().subList(0, 2));
        championsLeague.addTeams(netherlands.getLeagueByTier(1).getStandings().subList(0, 2));
        championsLeague.addTeams(scotland.getLeagueByTier(1).getStandings().subList(0, 2));
        championsLeague.addTeams(turkey.getLeagueByTier(1).getStandings().subList(0, 2));
        championsLeague.addTeams(austria.getLeagueByTier(1).getStandings().subList(0, 1));
        championsLeague.addTeams(greece.getLeagueByTier(1).getStandings().subList(0, 1));
        championsLeague.addTeams(belgium.getLeagueByTier(1).getStandings().subList(0, 1));


        world.addCup(championsLeague);
    }
    public static void setWorldCup() {
        League worldCup = new League("World Cup", "WorldCup.png", 1, international);

        //world cup teams

        //Europe (14)
        Team englandNationalTeam = new Team(england);
        Team franceNationalTeam = new Team(france);
        Team germanyNationalTeam = new Team(germany);
        Team italyNationalTeam = new Team(italy);
        Team netherlandsNationalTeam = new Team(netherlands);
        Team portugalNationalTeam = new Team(portugal);
        Team spainNationalTeam = new Team(spain);
        Team belgiumNationalTeam = new Team(belgium);
        Team croatiaNationalTeam = new Team(croatia);
        Team denmarkNationalTeam = new Team(denmark);
        Team swedenNationalTeam = new Team(sweden);
        Team switzerlandNationalTeam = new Team(switzerland);
        Team turkeyNationalTeam = new Team(turkey);
        Team austriaNationalTeam = new Team(austria);

        //South America (5)
        Team argentinaNationalTeam = new Team(argentina);
        Team brazilNationalTeam = new Team(brazil);
        Team chileNationalTeam = new Team(chile);
        Team colombiaNationalTeam = new Team(colombia);
        Team uruguayNationalTeam = new Team(uruguay);

        //North America (3)
        Team canadaNationalTeam = new Team(canada);
        Team usaNationalTeam = new Team(usa);
        Team mexicoNationalTeam = new Team(mexico);

        //Asia (2)
        Team japanNationalTeam = new Team(japan);
        Team southKoreaNationalTeam = new Team(southKorea);

        //Africa (8)
        Team nigeriaNationalTeam = new Team(nigeria);
        Team ghanaNationalTeam = new Team(ghana);
        Team ivoryCoastNationalTeam = new Team(ivoryCoast);
        Team cameroonNationalTeam = new Team(cameroon);
        Team algeriaNationalTeam = new Team(algeria);
        Team moroccoNationalTeam = new Team(morocco);
        Team senegalNationalTeam = new Team(senegal);
        Team maliNationalTeam = new Team(mali);




    }
}