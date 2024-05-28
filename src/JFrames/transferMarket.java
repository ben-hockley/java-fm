package JFrames;

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
        String[] columnNames = {"Name", "Position", "Rating", "Age", "Value"};

        String[][] playerDataList = new String[userTeam.getLeague().getAllPlayers().size()][5];
        for (int i=0; i< userTeam.getLeague().getAllPlayers().size(); i++) {
            String[] playerData = new String[5];
            playerData[0] = userTeam.getLeague().getPlayersByValue().get(i).getPlayerName();
            playerData[1] = userTeam.getLeague().getPlayersByValue().get(i).getPosition();
            playerData[2] = String.valueOf(userTeam.getLeague().getPlayersByValue().get(i).getRating());
            playerData[3] = String.valueOf(userTeam.getLeague().getPlayersByValue().get(i).getAge());
            playerData[4] = "£" + NumberFormat.getInstance(Locale.US).format(userTeam.getLeague().getPlayersByValue().get(i).getValue());

            playerDataList[i] = playerData;
        }

        JTable allPlayers = new JTable();

        //make the table not editable
        DefaultTableModel tableModel = new DefaultTableModel(playerDataList, columnNames) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        allPlayers.setModel(tableModel);
        JScrollPane sp = new JScrollPane(allPlayers);
        this.add(sp, BorderLayout.CENTER);
    }
}
