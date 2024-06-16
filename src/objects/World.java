package objects;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class World {
    /**
     * ArrayList of all the leagues in the world.
     * New leagues add themselves to this through their constructor.
     */
    private final ArrayList<League> leagues;
    /**
     * ArrayList of all the cups in the world.
     * New cups add themselves to this through their constructor.
     */
    private final ArrayList<Cup> cups;

    /**
     * ArrayList of all the recent transfers in the world.
     * New transfers are added to this through the doRandomTransfer method.
     * New transfers insert themselves at the start of the list, so the list
     * has the newest transfers at the top. (these are displayed in the UI)
     * Reset at the start of every season.
     */
    private final ArrayList<String> recentTransfers;

    /**
     * Constructor for the World class.
     * Initialises the empty leagues, cups and recentTransfers ArrayLists.
     * For this reason, the world object must be initialised before any
     * leagues or cups.
     */
    public World() {
        leagues = new ArrayList<>();
        cups = new ArrayList<>();
        recentTransfers = new ArrayList<>();
    }

    /**
     * Adds a league to the world.
     * Used in the league constructor to add the league to the world
     * upon creation.
     * @param league The league to be added (new league).
     */
    public void addLeague(final League league) {
        leagues.add(league);
    }

    /**
     * Adds a cup to the world.
     * Used in the cup constructor to add the cup to the world
     * upon creation.
     * @param cup The cup to be added (new cup).
     */
    public void addCup(final Cup cup) {
        cups.add(cup);
    }

    /**
     * Searches the arrayList cups and returns the cup with the given name
     * passed as args. If no cup is found, returns null.
     * @param cupName name of cup to search for.
     * @return cup with the given name, or null if not found.
     */
    public Cup getCupByName(final String cupName) {
        for (Cup cup : cups) {
            if (cup.getName().equals(cupName)) {
                return cup;
            }
        }
        return null;
    }

    /**
     * Returns all the players of all the teams in all the leagues in the
     * world. Does not return retired players or filler players on national
     * teams as these do not belong to any team object. (have no team).
     * @return ArrayList of all the players in the world.
     */
    public ArrayList<Player> getAllPlayers() {
        ArrayList<Player> allPlayers = new ArrayList<>();
        for (League league : leagues) {
            allPlayers.addAll(league.getAllPlayers());
        }
        return allPlayers;
    }

    /**
     * Returns all the players of all the teams in all the leagues in the
     * world (throgh getAllPlayers method). Then sorts the players by their
     * value.
     * This function is used to return all the players in order of their value
     * for the transfer market interface.
     * @return ArrayList of all the players in the world sorted by value.
     */
    public ArrayList<Player> getPlayersByValue() {
        ArrayList<Player> allPlayers = getAllPlayers();
        allPlayers.sort((p1, p2) -> (p2.getValue() - p1.getValue()));
        return allPlayers;
    }

    /**
     * Returns the highest rated player in the world by sorting all the players
     * by their rating and returning the first player in the sorted list.
     * This function is used to return the highest rated player at the end of
     * the season, so the ballon d'or can be awarded and displayed in the UI.
     * @return The highest rated player in the world.
     */
    public Player getHighestRatedPlayer() {
        ArrayList<Player> allPlayers = getAllPlayers();
        allPlayers.sort((p1, p2) -> (p2.getRating() - p1.getRating()));
        return allPlayers.get(0);
    }

    /**
     * Returs the highest rated player in the world under or equal to the age
     * of 21 by sorting all the players by their rating and returning the
     * first player under the age of 22 in the sorted list.
     * This function is used to return the highest rated player <= 21 at the
     * end of the season, so the golden boy award can be awarded and displayed
     * in the UI.
     * @return The highest rated player in the world under 22.
     */
    public Player getHighestRatedPlayerUnder21() {
        ArrayList<Player> allPlayers = getAllPlayers();
        allPlayers.sort((p1, p2) -> (p2.getRating() - p1.getRating()));

        final int maxAgeOfGoldenBoy = 21;

        for (Player player : allPlayers) {
            if (player.getAge() <= maxAgeOfGoldenBoy) {
                return player;
            }
        }
        return null;
    }

    /**
     * For every league in the leagues object, sorts through all the teams in
     * the league until it finds the team matching the given name passed as
     * args and returns that team. If no team is found across all leagues,
     * returns null.
     * @param teamName name of team to search for.
     * @return team with the given name, or null if not found.
     */
    public Team getTeamByName(final String teamName) {
        for (League league : leagues) {
            Team team = league.getTeamByName(teamName);
            if (team != null) {
                return team;
            }
        }
        return null;
    }

    /**
     * For every league in the leagues object, sorts through all the players in
     * all the teams in the league until it finds the player matching the given
     * name passed as args and returns that player. If no player is found
     * across all leagues, returns null.
     * @param playerName name of player to search for.
     * @return player with the given name, or null if not found.
     */
    public Player getPlayerByName(final String playerName) {
        for (League league : leagues) {
            Player player = league.getPlayerByName(playerName);
            if (player != null) {
                return player;
            }
        }
        return null;
    }

    /**
     * Picks a random club team from all the teams across all the leagues in
     * the world. Used to randomly select a team to buy a player in the
     * transfer market.
     * Does this by compiling all the teams from all the leagues into one
     * arraylist, and generating a random index from this arraylist, then
     * returning the team at that index.
     * Does not return national teams as these do not belong to any league.
     * @return any random team from any league in the world.
     */
    public Team getRandomTeam() {
        return getAllTeams().get((int) (Math.random() * getAllTeams().size()));
    }

    /**
     * Returns an arraylist of all the teams across all the leagues in the
     * world (leagues list).
     * @return ArrayList of all the teams in the world.
     */
    public ArrayList<Team> getAllTeams() {
        ArrayList<Team> allTeams = new ArrayList<>();
        for (League league : leagues) {
            allTeams.addAll(league.getAllTeams());
        }
        return allTeams;
    }

    /**
     * Function to perform a random transfer in the background of the game.
     * This function triggers during the transfer window.
     * The userTeam will never be involved in these transfers, as user
     * transfers are done manually by the user.
     * Random players are generated until a suitable player is found to sell,
     * and then random teams are generated until a sutiable buying team is
     * found. The player is then transferred from the selling team to the
     * buying team.
     * @param userTeam the user's team, so this can be excluded from transfers.
     * @param date the current date in the game, so this can be added to the
     *             transfer's description.
     * @param monthNumber the current month in the game, so this can be added
     *                    to the transfer's description.
     */
    public void doRandomTransfer(final Team userTeam, final Integer date,
                                 final Integer monthNumber) {
        Player playerForSale = getAllPlayers().get(
                (int) (Math.random() * getAllPlayers().size()));
        Team sellingTeam = playerForSale.getTeam();

        Integer[] sellingTeamFormation = sellingTeam.getFormation();
        final int minSquadSize = 16;
        final int minNumOfDEFs = sellingTeamFormation[0] + 1;
        final int minNumOfMIDs = sellingTeamFormation[1] + 1;
        final int minNumOfFWDs = sellingTeamFormation[2] + 1;

        while (
                sellingTeam == userTeam
                || sellingTeam.getNumberOfPlayers() <= minSquadSize
                        //make sure team has atleast one sub for each position.
                || playerForSale.getPosition().equals("GK")
                        && sellingTeam.getNumberOfGoalkeepers() <= 2
                || playerForSale.getPosition().equals("DEF")
                        && sellingTeam.getNumberOfDefenders() <= minNumOfDEFs
                || playerForSale.getPosition().equals("MID")
                        && sellingTeam.getNumberOfMidfielders() <= minNumOfMIDs
                || playerForSale.getPosition().equals("FWD")
                        && sellingTeam.getNumberOfForwards() <= minNumOfFWDs
                || playerForSale.getSellable().equals(false)
        ) {
            playerForSale = getAllPlayers().get(
                    (int) (Math.random() * getAllPlayers().size()));
            sellingTeam = playerForSale.getTeam();
        }

        Team buyingTeam = getBuyingTeam(userTeam, sellingTeam, playerForSale);

        if (buyingTeam == null) {
            return;
        }

        playerForSale.setTeam(buyingTeam);
        playerForSale.setSellable(false);

        buyingTeam.reduceTransferBudget(playerForSale.getValue());
        sellingTeam.increaseTransferBudget(playerForSale.getValue() / 2);

        String formatPlayerValue = NumberFormat.getInstance(Locale.UK).format(
                playerForSale.getValue() / 1000000) + "M";

        String recentTransferMessage = playerForSale.getPlayerName() + " : "
                + sellingTeam.getShortName() + " -> "
                + buyingTeam.getShortName() + " , " + formatPlayerValue
                + " ( " + date + "/" + monthNumber + " ) ";

        recentTransfers.add(0, recentTransferMessage);
    }

    /**
     * Inner method in the getRandomTranfer method to find a suitable buying
     * team for the player, generates random teams until a suitable buying team
     * is found.
     * @param userTeam the user's team, so this can be excluded from transfers.
     * @param sellingTeam the team selling the player, so this can be excluded
     *                    from transfers.
     * @param playerForSale the player being sold, so the buying team can be
     *                      checked for suitability.
     * @return the first sutiable buying team found, or null if no suitable
     * buying team is found.
     */
    private Team getBuyingTeam(final Team userTeam, final Team sellingTeam,
                               final Player playerForSale) {
        Team buyingTeam = getRandomTeam();
        final int maxSquadSize = 23;
        final int maxNumOfGKs = 3;

        int maxNumOfDEFs = buyingTeam.getFormation()[0] * 2;
        int maxNumOfMIDs = buyingTeam.getFormation()[1] * 2;
        int maxNumOfFWDs = buyingTeam.getFormation()[2] * 2;

        int teamsChecked = 0;
        while (
                buyingTeam == sellingTeam
                || buyingTeam == userTeam
                || buyingTeam.getTransferBudget() < playerForSale.getValue()
                || buyingTeam.getNumberOfPlayers() >= maxSquadSize
                || playerForSale.getPosition().equals("GK")
                        && buyingTeam.getNumberOfGoalkeepers() >= maxNumOfGKs
                || playerForSale.getPosition().equals("DEF")
                        && buyingTeam.getNumberOfDefenders() >= maxNumOfDEFs
                || playerForSale.getPosition().equals("MID")
                        && buyingTeam.getNumberOfMidfielders() >= maxNumOfMIDs
                || playerForSale.getPosition().equals("FWD")
                        && buyingTeam.getNumberOfForwards() >= maxNumOfFWDs
                || playerForSale.getRating() + 2
                        < buyingTeam.getLowestRatedStarterAtPosition(playerForSale.getPosition()).getRating()
        ) {
            buyingTeam = getRandomTeam();
            maxNumOfDEFs = buyingTeam.getFormation()[0] * 2;
            maxNumOfMIDs = buyingTeam.getFormation()[1] * 2;
            maxNumOfFWDs = buyingTeam.getFormation()[2] * 2;

            teamsChecked++;

            if (teamsChecked >= getAllTeams().size()) {
                return null;
            }
        }
        return buyingTeam;
    }

    /**
     * Wipes the recent transfers list at the start of every new season.
     * So the recent transfers list only contains players from this season.
     */
    public void wipeRecentTransfersList() {

        recentTransfers.clear();

        for (Player player : getAllPlayers()) {
            //set all players to sellable at the start of the new season.
            player.setSellable(true);
        }
    }

    /**
     * Getter mrthod for the recent transfers ArrayList.
     * @return all the transfers this season in the world, most recent first.
     */
    public ArrayList<String> getRecentTransfers() {
        return recentTransfers;
    }

    /**
     * Converts a digit to a date title. e.g. 1 -> 1st, 2 -> 2nd, 3 -> 3rd,
     * 4 -> 4th, 5 -> 5th, etc.
     * @param digit the digit to be converted to a date title.
     * @return the date title of the digit. e.g. 1 -> 1st, 2 -> 2nd, 3 -> 3rd.
     */
    public String convertToDateFormat(final Integer digit) {
        if (digit == 1) {
            return "1st";
        } else if (digit == 2) {
            return "2nd";
        } else if (digit == 3) {
            return "3rd";
        } else {
            return digit + "th";
        }
    }
}
