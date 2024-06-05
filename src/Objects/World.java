package Objects;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class World {
    public ArrayList<League> leagues;
    public ArrayList<Cup> cups;

    public ArrayList<String> recentTransfers;


    public World() {
        leagues = new ArrayList<>();
        cups = new ArrayList<>();
        recentTransfers = new ArrayList<>();
    }

    public void addLeague(League league) {
        leagues.add(league);
    }

    public void addCup(Cup cup) {
        cups.add(cup);
    }

    public Cup getCupByName(String cupName) {
        for (Cup cup : cups) {
            if (cup.getName().equals(cupName)) {
                return cup;
            }
        }
        return null;
    }

    public ArrayList<Player> getAllPlayers() {
        ArrayList<Player> allPlayers = new ArrayList<>();
        for (League league : leagues) {
            allPlayers.addAll(league.getAllPlayers());
        }
        return allPlayers;
    }

    public ArrayList<Player> getPlayersByValue() {
        ArrayList<Player> allPlayers = getAllPlayers();
        allPlayers.sort((p1, p2) -> (p2.getValue() - p1.getValue()));
        return allPlayers;
    }

    public Player getHighestRatedPlayer() {
        ArrayList<Player> allPlayers = getAllPlayers();
        allPlayers.sort((p1, p2) -> (p2.getRating() - p1.getRating()));
        return allPlayers.get(0);
    }

    public Player getHighestRatedPlayerUnder21() {
        ArrayList<Player> allPlayers = getAllPlayers();
        allPlayers.sort((p1, p2) -> (p2.getRating() - p1.getRating()));

        for (Player player : allPlayers){
            if (player.getAge() < 22){
                return player;
            }
        }
        return null;
    }

    public Team getTeamByName(String teamName) {
        for (League league : leagues) {
            Team team = league.getTeamByName(teamName);
            if (team != null) {
                return team;
            }
        }
        return null;
    }

    public Player getPlayerByName(String playerName) {
        for (League league : leagues) {
            Player player = league.getPlayerByName(playerName);
            if (player != null) {
                return player;
            }
        }
        return null;
    }

    public Team getRandomTeam() {
        return leagues.get((int) (Math.random() * leagues.size())).getRandomTeam();
    }

    public ArrayList<Team> getAllTeams() {
        ArrayList<Team> allTeams = new ArrayList<>();
        for (League league : leagues) {
            allTeams.addAll(league.getAllTeams());
        }
        return allTeams;
    }

    public void doRandomTransfer(Team userTeam, Integer date, Integer monthNumber){
        Player playerForSale = getAllPlayers().get((int) (Math.random() * getAllPlayers().size()));
        Team sellingTeam = playerForSale.getTeam();

        while (
                //check if the selling team has enough players in the squad and the player's position to sell the player.
                //and that the selling team is not the user team (user transfers should be done manually).
                sellingTeam == userTeam
                || sellingTeam.getNumberOfPlayers() <= 15
                || playerForSale.getPosition().equals("GK") && sellingTeam.getNumberOfGoalkeepers() <= 2
                || playerForSale.getPosition().equals("DEF") && sellingTeam.getNumberOfDefenders() <= 4
                || playerForSale.getPosition().equals("MID") && sellingTeam.getNumberOfMidfielders() <= 3
                || playerForSale.getPosition().equals("ATT") && sellingTeam.getNumberOfForwards() <= 3
        ){
            //if conditions are not met, reselect a player to sell.
            playerForSale = getAllPlayers().get((int) (Math.random() * getAllPlayers().size()));
            sellingTeam = playerForSale.getTeam();
        }


        Team buyingTeam = getRandomTeam();

        while (
                buyingTeam == sellingTeam //check that the buying team is not the same as the selling team.
                || buyingTeam == userTeam //check that the buying team is not the user team (user transfers should be done manually).
                || buyingTeam.getTransferBudget() < playerForSale.getValue() //check that the buying team has enough money to buy the player.
                || buyingTeam.getNumberOfPlayers() >= 23 //and that the buying team has enough space in the squad to buy the player.
                || playerForSale.getPosition().equals("GK") && buyingTeam.getNumberOfGoalkeepers() >= 3
                || playerForSale.getPosition().equals("DEF") && buyingTeam.getNumberOfDefenders() >= 10
                || playerForSale.getPosition().equals("MID") && buyingTeam.getNumberOfMidfielders() >= 8
                || playerForSale.getPosition().equals("ATT") && buyingTeam.getNumberOfForwards() >= 8
                || playerForSale.getRating() + 3 < buyingTeam.getLowestRatedStarterAtPosition(playerForSale.getPosition()).getRating() //check that the player is good enough to be a realistic transfer target for the buying team.
        ){
            //if conditions are not met, reselect a team to buy the player.
            buyingTeam = getRandomTeam();
        }

        //once eligible player and eligible buying team have been found, transfer the player.
        playerForSale.setTeam(buyingTeam);

        //update the transfer budgets of the buying and selling teams.
        buyingTeam.reduceTransferBudget(playerForSale.getValue());

        //add half the transfer fee to the budget of the selling team. (the other half goes towards running costs for the club/ player wages etc.)
        sellingTeam.increaseTransferBudget(playerForSale.getValue() / 2);

        //add transfer to the front of the recent transfers list which is displayed on the home screen.
        recentTransfers.add(0, playerForSale.getPlayerName() + " : " + sellingTeam.getShortName() + " -> " + buyingTeam.getShortName() + " , " + NumberFormat.getInstance(Locale.UK).format(playerForSale.getValue() / 1000000) + "M ( " + date + "/" + monthNumber + " ) ");
    }

    public void wipeRecentTransfersList(){
        recentTransfers.clear();
    }

    public ArrayList<String> getRecentTransfers(){
        return recentTransfers;
    }

    public String convertToDateFormat(Integer digit){
        if (digit == 1){
            return "1st";
        } else if (digit==2){
            return "2nd";
        } else if (digit==3){
            return "3rd";
        } else {
            return digit + "th";
        }
    }



}
