package JPanels;

import javax.swing.*;
import java.awt.*;

public class CalendarPanel extends JPanel {
    public CalendarPanel() {
        this.setLayout(new GridLayout(1, 6));
        this.setPreferredSize(new Dimension(1000, 100));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setAlignmentY(Component.CENTER_ALIGNMENT);
    }
}
