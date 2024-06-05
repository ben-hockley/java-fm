package Objects;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class World {
    public ArrayList<League> leagues;
    public ArrayList<Cup> cups;


    public World() {
        leagues = new ArrayList<>();
        cups = new ArrayList<>();
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

    public void doRandomTransfer(Team userTeam){
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
        ){
            //if conditions are not met, reselect a team to buy the player.
            buyingTeam = getRandomTeam();
        }

        //once eligible player and eligible buying team have been found, transfer the player.
        playerForSale.setTeam(buyingTeam);

        //update the transfer budgets of the buying and selling teams.
        buyingTeam.reduceTransferBudget(playerForSale.getValue());
        sellingTeam.increaseTransferBudget(playerForSale.getValue());

        //print transfer confirmation message to the console
        System.out.println(playerForSale.getPlayerName() + " has been transferred from " + sellingTeam.getTeamName() + " to " + buyingTeam.getTeamName() + " for " + NumberFormat.getInstance(Locale.US).format(playerForSale.getValue()));

        System.out.println("Transfer budgets: " + sellingTeam.getTeamName() + ": " + NumberFormat.getInstance(Locale.UK).format(sellingTeam.getTransferBudget()) + ", " + buyingTeam.getTeamName() + ": " + NumberFormat.getInstance(Locale.UK).format(buyingTeam.getTransferBudget()));
    }



}
