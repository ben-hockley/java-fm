package JFrames;

import Objects.Player;
import Objects.Team;
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

        String heading = "Transfer Market";
        JLabel headingLabel = new JLabel(heading);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headingLabel.setPreferredSize(new Dimension(800, 50));
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headingLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.add(headingLabel, BorderLayout.NORTH);

        //JTable
        //column names for the table.
        String[] columnNames = {"Name", "Team", "Position", "Rating", "Age", "Value"};

        //2D array to store player data for the table.
        String[][] playerDataList = new String[userTeam.getLeague().getAllPlayers().size()][6];

        //add all players from the league to playerDataList, which stores player data for the table.
        for (int i=0; i< userTeam.getLeague().getAllPlayers().size(); i++) {
            String[] playerData = new String[6];

            Player player = userTeam.getLeague().getPlayersByValue().get(i);
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

                //blocks user from buying their own player.
                if (getValueAt(row, 1) != userTeam.getTeamName()) {
                    int confirmPlayerPurchase = JOptionPane.showConfirmDialog(null, "Buy " + getValueAt(row, 0) + " for " + getValueAt(row, 5) + "?",
                            "Confirm player purchase", JOptionPane.YES_NO_CANCEL_OPTION);

                    //sign player for user team
                    if (confirmPlayerPurchase == JOptionPane.YES_OPTION) {
                        if (userTeam.getAllPlayers().size() < 23 && userTeam.getLeague().getPlayerByName((String) getValueAt(row, 0)).getTeam().getAllPlayers().size() > 16) {
                            userTeam.getLeague().getPlayerByName((String) getValueAt(row, 0)).setTeam(userTeam);
                            JOptionPane.showConfirmDialog(null,getValueAt(row, 0) + " to " + userTeam.getTeamName() + " from " + getValueAt(row,1) + " for " + getValueAt(row,5) + ", Here we go!" , "New Signing", JOptionPane.DEFAULT_OPTION);
                        } else if (userTeam.getAllPlayers().size() == 23){
                            JOptionPane.showMessageDialog(null, "You have too many players in your squad. Maximum squad size is 23. You must sell a player before you can buy another.");
                        } else if (userTeam.getLeague().getPlayerByName((String) getValueAt(row, 0)).getTeam().getAllPlayers().size() <= 16) {
                            JOptionPane.showMessageDialog(null, "This player is not for sale, their team has too few players.");
                        }
                    }
                } else {
                    int confirmPlayerTransferList = JOptionPane.showConfirmDialog(null, "Place " + getValueAt(row, 0) + " on the transfer list?" ,
                            "Confirm player transfer list", JOptionPane.YES_NO_CANCEL_OPTION);

                    //sell the player
                    if (confirmPlayerTransferList == JOptionPane.YES_OPTION) {
                        //generate random team to buy the player.
                        Team buyingTeam = userTeam.getLeague().getRandomTeam();

                        //get new buying team if random team generated is the user team, or if the buying team has too many players.
                        while (buyingTeam == userTeam || buyingTeam.getAllPlayers().size() >= 23) {
                            buyingTeam = userTeam.getLeague().getRandomTeam();
                        }

                        int confirmPlayerSale = JOptionPane.showConfirmDialog(null, "Sell " + getValueAt(row, 0) + " to " + buyingTeam.getTeamName() + " for " + getValueAt(row, 5) + "?",
                                "Confirm player sale", JOptionPane.YES_NO_CANCEL_OPTION);

                        //sell player
                        if (confirmPlayerSale == JOptionPane.YES_OPTION) {
                            if (userTeam.getAllPlayers().size() <= 16) {
                                //block sale if player squad is too small.
                                JOptionPane.showMessageDialog(null, "You must have at least 17 players in your squad to sell a player.");
                            } else {
                                //transfer player to the buying team.
                                userTeam.getLeague().getPlayerByName((String) getValueAt(row, 0)).setTeam(buyingTeam);
                                JOptionPane.showConfirmDialog(null,getValueAt(row, 0) + " to " + buyingTeam.getTeamName() + " from " + userTeam.getTeamName() + " for " + getValueAt(row,5) + ", Here we go!" , "Player Sold", JOptionPane.DEFAULT_OPTION);
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
