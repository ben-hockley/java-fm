package Objects;

public class Player {
    private String firstName;
    private String lastName;
    private String position;
    private Team team;
    private Nation nationality;

    public Player(String firstName, String lastName, String position,
                  Team team, Nation nationality) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.team = team;
        this.nationality = nationality;

        team.addPlayer(this);
        nationality.addPlayer(this);
    }
}
