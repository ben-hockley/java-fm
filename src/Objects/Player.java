package Objects;

public class Player {
    private String firstName;
    private String lastName;
    private String position;
    private Team team;
    private Nation nationality;

    private Integer rating;

    public Player(String firstName, String lastName, String position,
                  Team team, Nation nationality, Integer rating) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.team = team;
        this.nationality = nationality;
        this.rating = rating;

        team.addPlayer(this); // After creating a player, add them to their team.
        nationality.addPlayer(this); // After creating a player, add them to their nation.
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

    public Team getTeam() {
        return team;
    }
}
