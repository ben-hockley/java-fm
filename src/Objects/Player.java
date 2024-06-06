package Objects;

import data.Data;

public class Player {
    private final String firstName;
    private final String lastName;
    private final String position;
    private Team team;
    private final Nation nationality;
    private Integer rating;
    private Integer age;

    //League stats.
    private Integer leagueAppearances;
    private Integer leagueGoals;

    //Cup stats
    private Integer cupAppearances;
    private Integer cupGoals;

    public Player(String firstName, String lastName, String position,
                  Team team, Nation nationality, Integer rating, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;

        //added players with team = null to fill national teams, these are not available on the transfer market.
        if (team != null){
            this.team = team;
            team.addPlayer(this); // After creating a player, add them to their team.
        }
        this.nationality = nationality;

        this.rating = rating;
        this.age = age;

        nationality.addPlayer(this); // After creating a player, add them to their nation.

        //League stats (set to zero by default)
        this.leagueAppearances = 0;
        this.leagueGoals = 0;

        //Cup stats (set to zero by default)
        this.cupAppearances = 0;
        this.cupGoals = 0;
    }

    public String getPlayerName() {
        return firstName + " " + lastName;
    }

    public String getPosition() {
        return position;
    }

    public Integer getRating() {
        return rating;
    }

    public void addLeagueAppearance() {
        this.leagueAppearances++;
    }
    public void addLeagueGoal() {
        this.leagueGoals++;
    }

    public Integer getLeagueAppearances() {
        return leagueAppearances;
    }

    public Integer getLeagueGoals() {
        return leagueGoals;
    }

    public void addCupAppearance() {
        this.cupAppearances++;
    }
    public void addCupGoal() {
        this.cupGoals++;
    }
    public Integer getCupGoals() {
        return cupGoals;
    }

    public Team getTeam() {
        return team;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getValue() {
        int actualValue = ((Math.max(rating, 70) - 70) * (35 - age)) * 500000;

        // Ensure that the player's value is at least 1.
        return Math.max(actualValue, 1);
    }

    public void setTeam(Team team) {
        this.team.removePlayer(this); //remove player from current team
        this.team.setDefaultStartingElevenandSubs(); //update the current team's lineup to reflect the player leaving.

        this.team = team; //set player's team to new team

        team.addPlayer(this); //add player to new team
        team.setDefaultStartingElevenandSubs(); //update the new team's lineup to reflect the new signing.
    }

    public void increaseAge(Integer years){
        this.age += years;
    }

    public void retirePlayer(){
        //create regen to replace retired player
        String regenFirstName = Data.listOfFirstNames[(int) (Math.random() * Data.listOfFirstNames.length)];
        String regenLastName = Data.listOfLastNames[(int) (Math.random() * Data.listOfLastNames.length)];
        Integer regenRating = 70 + (int) (Math.random() * 15);
        Integer regenAge = 16 + (int) (Math.random() * 5);

        //create regen with same position, team, nationality as retired player.
        new Player(regenFirstName, regenLastName, position, team, nationality, regenRating, regenAge);


        this.team.removePlayer(this);
        this.team.setDefaultStartingElevenandSubs();
        this.nationality.removePlayer(this);
    }

    public void increaseRating(Integer rating){
        this.rating += rating;
    }

    public void decreaseRating(Integer rating){
        this.rating -= rating;
    }

    public void setRating(Integer rating){
        this.rating = rating;
    }

    public void resetLeagueStats(){
        this.leagueAppearances = 0;
        this.leagueGoals = 0;
    }
    public void resetCupStats(){
        this.cupAppearances = 0;
        this.cupGoals = 0;
    }

    public Nation getNationality() {
        return nationality;
    }
}
