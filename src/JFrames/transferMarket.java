package JFrames;

import Objects.Player;
import Objects.Team;
import data.Data;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Locale;
public class transferMarket extends JFrame {
    public transferMarket(Team userTeam) {
        setTitle("Transfer Market");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setVisible(true);

        JPanel topPanel = new JPanel(new GridLayout(1, 2));

        String heading = "Transfer Market";
        JLabel headingLabel = new JLabel(heading);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headingLabel.setVerticalAlignment(SwingConstants.CENTER);
        topPanel.add(headingLabel);

        String transferBudget = "Transfer Budget: £" + NumberFormat.getInstance(Locale.US).format(userTeam.getTransferBudget());
        JLabel transferBudgetLabel = new JLabel(transferBudget);
        transferBudgetLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        transferBudgetLabel.setHorizontalAlignment(SwingConstants.CENTER);
        transferBudgetLabel.setVerticalAlignment(SwingConstants.CENTER);
        topPanel.add(transferBudgetLabel);

        this.add(topPanel, BorderLayout.NORTH);

        //JTable
        //column names for the table.
        String[] columnNames = {"Name", "Team", "Position", "Rating", "Age", "Value"};

        //2D array to store player data for the table.
        String[][] playerDataList = new String[Data.world.getAllPlayers().size()][6];

        //add all players from the league to playerDataList, which stores player data for the table.
        for (int i=0; i< Data.world.getAllPlayers().size(); i++) {
            String[] playerData = new String[6];

            Player player = Data.world.getPlayersByValue().get(i);
            playerData[0] = player.getPlayerName();
            playerData[1] = player.getTeam().getTeamName();

            playerData[2] = player.getPosition();

            playerData[3] = String.valueOf(player.getRating());
            playerData[4] = String.valueOf(player.getAge());
            playerData[5] = "£" + NumberFormat.getInstance(Locale.US).format(player.getValue());

            playerDataList[i] = playerData;
        }

        //make the table not editable, instead show a confirmation dialog when a cell is clicked.
        DefaultTableModel tableModel = new DefaultTableModel(playerDataList, columnNames) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //clicking on a cell offers the option to buy the player.

                //if the player is not in the user team, offer to BUY the player.
                if (getValueAt(row, 1) != userTeam.getTeamName()) {

                    Team sellingTeam = Data.world.getTeamByName((String) getValueAt(row, 1));
                    Player transferTarget = Data.world.getPlayerByName((String) getValueAt(row, 0));


                    if (userTeam.getTransferBudget() < transferTarget.getValue()) {
                        //block transfer if the user team does not have enough money.
                        JOptionPane.showMessageDialog(null, "You do not have enough money to buy this player.");
                    } else if (sellingTeam.getNumberOfPlayers() <= 16) {
                        //block transfer if the selling team has too few players.
                        JOptionPane.showMessageDialog(null, "This player is not for sale, their team has too few players.");
                    } else if (userTeam.getNumberOfPlayers() == 23){
                        //block transfer if the user team has too many players.
                        JOptionPane.showMessageDialog(null, "You have too many players in your squad. Maximum squad size is 23. You must sell a player before you can buy another.");
                    } else if (transferTarget.getPosition().equals("GK") && sellingTeam.getNumberOfGoalkeepers() <= 2){
                        //block transfer if the target is a goalkeeper, and selling team has too few goalkeepers
                        JOptionPane.showMessageDialog(null, "This player is not for sale, their team has too few goalkeepers.");
                    } else if (transferTarget.getPosition().equals("DEF") && sellingTeam.getNumberOfDefenders() <= 4){
                        //block transfer if the target is a defender, and selling team has too few defenders
                        JOptionPane.showMessageDialog(null, "This player is not for sale, their team has too few defenders.");
                    } else if (transferTarget.getPosition().equals("MID") && sellingTeam.getNumberOfMidfielders() <= 3){
                        //block transfer if the target is a midfielder, and the selling team has too few midfielders
                        JOptionPane.showMessageDialog(null, "This player is not for sale, their team has too few midfielders.");
                    } else if (transferTarget.getPosition().equals("FWD") && sellingTeam.getNumberOfForwards() <= 3){
                        //block transfer if selling team has too few forwards
                        JOptionPane.showMessageDialog(null, "This player is not for sale, their team has too few forwards.");
                    }

                    //if all conditions are met, ask the user to confirm the purchase.
                    else {
                        int confirmPlayerPurchase = JOptionPane.showConfirmDialog(null, "Buy " + transferTarget.getPlayerName() + " for  £" + NumberFormat.getInstance(Locale.US).format(transferTarget.getValue()) + "?",
                                "Confirm player purchase", JOptionPane.YES_NO_CANCEL_OPTION);

                        //sign player for user team
                        if (confirmPlayerPurchase == JOptionPane.YES_OPTION) {

                            //increase transfer budget of selling team.
                            transferTarget.getTeam().increaseTransferBudget(transferTarget.getValue());

                            //reduce transfer budget of buying team (user).
                            userTeam.reduceTransferBudget(transferTarget.getValue());

                            transferTarget.setTeam(userTeam);
                            JOptionPane.showConfirmDialog(null,transferTarget.getPlayerName() + " to " + userTeam.getTeamName() + " from " + sellingTeam.getTeamName() + " for  £" + NumberFormat.getInstance(Locale.US).format(transferTarget.getValue()) + ", Here we go!" , "New Signing", JOptionPane.DEFAULT_OPTION);

                            //refresh the transfer market window.
                            transferMarket.this.dispose();
                            new transferMarket(userTeam);
                        }
                    }
                }

                //if the player is from the user team, offer to sell the player.
                else {
                    int confirmPlayerTransferList = JOptionPane.showConfirmDialog(null, "Place " + getValueAt(row, 0) + " on the transfer list?" ,
                            "Confirm player transfer list", JOptionPane.YES_NO_CANCEL_OPTION);

                    //sell the player
                    if (confirmPlayerTransferList == JOptionPane.YES_OPTION) {
                        //generate random team to buy the player.
                        Team buyingTeam = Data.world.getRandomTeam();
                        Player playerForSale = Data.world.getPlayerByName((String) getValueAt(row, 0));

                        //generate new buying team if random team generated is the user team, or if the buying team has too many players, or if buying team doesn't have enough money in their transfer budget.
                        while (buyingTeam == userTeam || buyingTeam.getNumberOfPlayers() >= 23 || buyingTeam.getTransferBudget() < playerForSale.getValue()) {
                            buyingTeam = Data.world.getRandomTeam();
                        }

                        int confirmPlayerSale = JOptionPane.showConfirmDialog(null, "Sell " + getValueAt(row, 0) + " to " + buyingTeam.getTeamName() + " for " + getValueAt(row, 5) + "?",
                                "Confirm player sale", JOptionPane.YES_NO_CANCEL_OPTION);

                        //sell player
                        if (confirmPlayerSale == JOptionPane.YES_OPTION) {

                            if (userTeam.getNumberOfPlayers() <= 16) {
                                //block sale if player squad is too small.
                                JOptionPane.showMessageDialog(null, "You must have at least 17 players in your squad to sell a player.");
                            } else if (playerForSale.getPosition().equals("GK") && userTeam.getNumberOfGoalkeepers() <= 2){
                                //block sale if player is a goalkeeper and user team has too few goalkeepers.
                                JOptionPane.showMessageDialog(null, "Transfer blocked, not enough Goalkeepers, you must have at least 2 goalkeepers in your squad.");
                            } else if (playerForSale.getPosition().equals("DEF") && userTeam.getNumberOfDefenders() <= 4){
                                //block sale if player is a defender and user team has too few defenders.
                                JOptionPane.showMessageDialog(null, "Transfer blocked, not enough Defenders, you must have at least 4 defenders in your squad.");
                            } else if (playerForSale.getPosition().equals("MID") && userTeam.getNumberOfMidfielders() <= 3){
                                //block sale if player is a midfielder and user team has too few midfielders.
                                JOptionPane.showMessageDialog(null, "Transfer blocked, not enough Midfielders, you must have at least 3 midfielders in your squad.");
                            } else if (playerForSale.getPosition().equals("FWD") && userTeam.getNumberOfForwards() <= 3){
                                //block sale if player is a forward and user team has too few forwards.
                                JOptionPane.showMessageDialog(null, "Transfer blocked, not enough Forwards, you must have at least 3 forwards in your squad.");
                            } else {
                                //transfer player to the buying team if all conditions are met.

                                //increase transfer budget of the selling team (user).
                                playerForSale.getTeam().increaseTransferBudget(playerForSale.getValue());

                                //reduce transfer budget of the buying team.
                                buyingTeam.reduceTransferBudget(playerForSale.getValue());

                                playerForSale.setTeam(buyingTeam);
                                JOptionPane.showConfirmDialog(null,playerForSale.getPlayerName() + " to " + buyingTeam.getTeamName() + " from " + userTeam.getTeamName() + " for " + getValueAt(row,5) + ", Here we go!" , "Player Sold", JOptionPane.DEFAULT_OPTION);

                                //refresh the transfer market window.
                                transferMarket.this.dispose();
                                new transferMarket(userTeam);
                            }
                        }

                    }
                }

                return false; //makes all cells uneditable.
            }

        };

        JTable allPlayers = new JTable(tableModel);
        JScrollPane sp = new JScrollPane(allPlayers);
        this.add(sp, BorderLayout.CENTER);
    }
}
