package objects;

import data.regenNames;

public class Player {
    /**
     * The player's first name/ commonly known name.
     */
    private final String firstName;
    /**
     * The player's last name.
     */
    private final String lastName;
    /**
     * The player's position. (e.g. "GK", "DEF", "MID", "ATT")
     */
    private final String position;
    /**
     * The team that the player is currently playing for.
     */
    private Team team;
    /**
     * The player's nationality. (The player may play for this national team)
     */
    private final Nation nationality;
    /**
     * The player's rating. (The player's overall rating)
     * Between 70 and 99 for real players, regens start at 70-84.
     * Filler players on national teams may have a lower overall rating.
     */
    private Integer rating;
    /**
     * The player's age, increases by 1 at the end of every season.
     */
    private Integer age;

    /**
     * The number of appearances the player has made in the league this season,
     * resets to 0 at the start of every season.
     */
    private Integer leagueAppearances;
    /**
     * The number of goals the player has scored in the league this season,
     * resets to 0 at the start of every season.
     */
    private Integer leagueGoals;

    /**
     * The number of appearances the player has made in the champions league
     * this season, resets to 0 at the start of every season.
     */
    private Integer cupAppearances;
    /**
     * The number of goals the player has scored in the champions league
     * this season, resets to 0 at the start of every season.
     */
    private Integer cupGoals;
    /**
     * A boolean to determine if the player is sellable in the transfer market.
     * Is set to true by default at the start of every season.
     * Set to false until the season ends if a player transfers to a new team.
     */
    private Boolean sellable;

    /**
     * Constructor for the Player class.
     * @param fName the player's first name/ commonly known name.
     * @param lName the player's last name.
     * @param pos the players position. (e.g. "GK", "DEF", "MID", "ATT")
     * @param initialTeam the player's initial team.
     * @param nation the players nationality/ national team.
     * @param initialRating the player's initial rating (regens start at 70-84)
     * @param initialAge the player's initial age. (regens start at 16-20)
     */
    public Player(final String fName, final String lName, final String pos,
                  final Team initialTeam, final Nation nation,
                  final Integer initialRating, final Integer initialAge) {

        this.firstName = fName;
        this.lastName = lName;
        this.position = pos;

        //ignore players with no team (national team filler players)
        if (initialTeam != null) {
            this.team = initialTeam;
            team.addPlayer(this);
        }
        this.nationality = nation;

        this.rating = initialRating;
        this.age = initialAge;

        nationality.addPlayer(this);

        //League stats (set to zero by default)
        this.leagueAppearances = 0;
        this.leagueGoals = 0;

        //Cup stats (set to zero by default)
        this.cupAppearances = 0;
        this.cupGoals = 0;

        this.sellable = true; //allows player to be sold in the transfer market.
    }

    /**
     * Returns the player's full name.
     * @return the player's first name followed by their last name.
     */
    public String getPlayerName() {
        return firstName + " " + lastName;
    }

    /**
     * Getter method for the player's position.
     * @return the player's position.
     */
    public String getPosition() {
        return position;
    }
    /**
     * Getter method for the player's current rating (70-99).
     * @return the player's rating.
     */
    public Integer getRating() {
        return rating;
    }

    /**
     * Increases the player's leagueAppearances stat by 1.
     * Used when a player either starts or is subbed on in a league match.
     */
    public void addLeagueAppearance() {
        this.leagueAppearances++;
    }
    /**
     * Increases the player's leagueGoals stat by 1.
     * Used when a player scores in a league match.
     */
    public void addLeagueGoal() {
        this.leagueGoals++;
    }
    /**
     * Getter method for the player's leagueAppearances stat.
     * @return the number of league games played by the player this season.
     */
    public Integer getLeagueAppearances() {
        return leagueAppearances;
    }
    /**
     * Getter method for the player's leagueGoals stat.
     * @return the number of league goals scored by the player this season.
     */
    public Integer getLeagueGoals() {
        return leagueGoals;
    }
    /**
     * Increases the player's cupAppearances stat by 1.
     * Used when a player either starts or is subbed on in a cup match.
     */
    public void addCupAppearance() {
        this.cupAppearances++;
    }
    /**
     * Increases the player's cupGoals stat by 1.
     * Used when a player scores in a cup match.
     */
    public void addCupGoal() {
        this.cupGoals++;
    }
    /**
     * Getter method for the player's cupGoals stat.
     * @return the number of cup goals scored by the player this season.
     */
    public Integer getCupGoals() {
        return cupGoals;
    }

    /**
     * Getter method for the player's current team.
     * @return the player's current team.
     */
    public Team getTeam() {
        return team;
    }
    /**
     * Getter method for the player's current age.
     * @return the player's current age.
     */
    public Integer getAge() {
        return age;
    }
    /**
     * Calculates and returns the player's value.
     * The player's current value is calculated based on their:
     * -rating (higher rating = higher value)
     * -age (younger players are worth more)
     * @return the player's value.
     */
    public Integer getValue() {

        final int minRating = 70; //miniumum rating for a player.
        final int retirementAge = 35; //age at which a player loses all value.

        final int valueMultiplier = 500000; // value of each rating point/
                                            // year under retirement age.

        int actualValue = (rating - minRating)
                * (retirementAge - age)
                * valueMultiplier;

        // Ensure that the player's value is at least 1.
        return Math.max(actualValue, 1);
    }

    /**
     * Transfers the player to a new team,
     * removes the player from their current team's player list and adds them
     * to their new team's player list.
     * Recalculates the starting eleven and substitutes for both teams to
     * account for the transfer.
     * @param newTeam the player's new team.
     */
    public void setTeam(final Team newTeam) {
        this.team.removePlayer(this);
        this.team.setDefaultStartingElevenandSubs();

        this.team = newTeam;

        newTeam.addPlayer(this);
        newTeam.setDefaultStartingElevenandSubs();
    }

    /**
     * Increases the player's age by a specified number of years.
     * @param years number of years to increase the player's age by.
     */
    public void increaseAge(final Integer years) {
        this.age += years;
    }

    /**
     * Function to retire the player, and create a regen to replace them.
     * The regen will have the same nationality, position, and team as the
     * retiring player.
     * This is useful to keep the number of each position,
     * nationality and club fairly steady.
     * The regen gets a new random first name and last name from the lists of
     * names in the class data.regenNames.
     * The regen's age is randomized between 16 and 20.
     * The players rating is randomized between 70 and 80 + the player's
     * age - 16 (e.g. a 16-year-old regen will have a rating between 70 and 80)
     */
    public void retirePlayer() {
        //create regen to replace retired player
        String regenFirstName = regenNames.listOfFirstNames[
                (int) (Math.random() * regenNames.listOfFirstNames.length)];
        String regenLastName = regenNames.listOfLastNames[
                (int) (Math.random() * regenNames.listOfLastNames.length)];

        final int regenMinRating = 70;
        final int regenMaxRating = 80;

        final int regenMinAge = 16;
        final int regenMaxAge = 20;

        int regenAge = regenMinAge
                + (int) (Math.random() * (regenMaxAge - regenMinAge));
        int regenRating = regenMinRating
                + (int) (Math.random() * (regenMaxRating - regenMinRating))
                + (int) (Math.random() * (regenAge - regenMinAge));

        //create regen with same position, team, nationality as retired player.
        new Player(regenFirstName, regenLastName,
                position, team, nationality,
                regenRating, regenAge);


        this.team.removePlayer(this);
        this.team.setDefaultStartingElevenandSubs();
        this.nationality.removePlayer(this);
    }

    /**
     * Increases the player's rating by a specified number of points.
     * @param ratingIncrease the number of points to increase the player's
     *                       rating by.
     */
    public void increaseRating(final Integer ratingIncrease) {
        this.rating += ratingIncrease;
    }

    /**
     * Decreases the player's rating by a specified number of points.
     * @param ratingDecrease the number of points to decrease the player's
     *                       rating by.
     */
    public void decreaseRating(final Integer ratingDecrease) {
        this.rating -= ratingDecrease;
    }

    /**
     * Sets the player's rating to a specified value.
     * Can be used to limit the player's rating if it leaves the desired range.
     * @param newRating the value to set the player's rating to.
     */
    public void setRating(final Integer newRating) {
        this.rating = newRating;
    }
    /**
     * Resets the player's league and cup stats to 0.
     * Used at the start of every new season.
     */
    public void resetSeasonStats() {
        this.leagueAppearances = 0;
        this.leagueGoals = 0;
        this.cupAppearances = 0;
        this.cupGoals = 0;
    }

    /**
     * Getter method for the player's nationality.
     * @return the player's nationality/ national team.
     */
    public Nation getNationality() {
        return nationality;
    }

    /**
     * Alters the players transfermarket status.
     * @param isSellable Boolean - whether the player is sellable.
     */
    public void setSellable(final Boolean isSellable) {
        this.sellable = isSellable;
    }

    /**
     * Getter method for the player's transfer market status.
     * @return whether the player can be sold or not.
     */
    public Boolean getSellable() {
        return sellable;
    }
}
