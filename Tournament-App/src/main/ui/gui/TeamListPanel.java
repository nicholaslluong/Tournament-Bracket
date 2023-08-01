package ui.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

// Represents JPanel which displays the teams participating in the Tournament
public class TeamListPanel extends JPanel {
    private JScrollPane jscrollPane;
    private JList<String> jlist;

    // EFFECTS: Creates TeamListPanel Panel.
    TeamListPanel(TeamListModel teamListModel) {
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        Border displayTitle = BorderFactory.createTitledBorder(blackLine, "Teams in Tournament");
        setBorder(displayTitle);

        this.jscrollPane = new JScrollPane();
        this.jlist = new JList<>(teamListModel);
        jscrollPane.setViewportView(jlist);
        jscrollPane.setPreferredSize(new Dimension(350,250));

        this.add(jscrollPane);

    }
}
