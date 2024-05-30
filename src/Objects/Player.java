package Objects;

import data.Data;

public class Player {
    private final String firstName;
    private final String lastName;
    private final String position;
    private Team team;
    private Nation nationality;
    private Integer rating;
    private Integer age;

    //in season stats.
    private Integer appearances;
    private Integer goals;

    public Player(String firstName, String lastName, String position,
                  Team team, Nation nationality, Integer rating, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.team = team;
        this.nationality = nationality;

        this.rating = rating;
        this.age = age;

        team.addPlayer(this); // After creating a player, add them to their team.
        nationality.addPlayer(this); // After creating a player, add them to their nation.

        //in season stats. (set to zero by default)
        this.appearances = 0;
        this.goals = 0;
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

    public void addAppearance() {
        this.appearances++;
    }
    public void addGoal() {
        this.goals++;
    }

    public Integer getAppearances() {
        return appearances;
    }

    public Integer getGoals() {
        return goals;
    }

    public Team getTeam() {
        return team;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getValue() {
        int actualValue = ((rating - 70) * (35 - age)) * 500000;

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

        Player regen = new Player(regenFirstName, regenLastName, position, team, nationality, 75, 18);


        this.team.removePlayer(this);
        this.team.setDefaultStartingElevenandSubs();
        this.nationality.removePlayer(this);
    }

}
