package Objects;

public class Player {
    private final String firstName;
    private final String lastName;
    private final String position;
    private final Team team;
    private final Nation nationality;
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
}
