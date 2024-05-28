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
        String[] columnNames = {"Name", "Team", "Position", "Rating", "Age", "Value"};

        String[][] playerDataList = new String[userTeam.getLeague().getAllPlayers().size()][6];
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

        //make the table not editable
        DefaultTableModel tableModel = new DefaultTableModel(playerDataList, columnNames) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                System.out.println(getValueAt(row, 0));
                int choice = JOptionPane.showConfirmDialog(null, "Buy " + getValueAt(row, 0) + " for " + getValueAt(row, 5) + "?",
                        "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION);

                if (choice == JOptionPane.YES_OPTION) {
                    userTeam.getLeague().getPlayerByName((String) getValueAt(row, 0)).setTeam(userTeam);
                }


                return false; //makes cell uneditable
            }

        };

        JTable allPlayers = new JTable(tableModel);
        JScrollPane sp = new JScrollPane(allPlayers);
        this.add(sp, BorderLayout.CENTER);
    }
}
