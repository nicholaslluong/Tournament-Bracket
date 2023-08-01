package ui.gui;


import model.EventLog;
import model.Event;
import model.Tournament;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// Represents a JFrame which contains all the panels
public class TournamentGUI extends JFrame {

    private TeamListPanel teamListPanel;
    private AddTeamPanel addTeamPanel;
    private MenuPanel menuPanel;
    private DisplayPanel displayPanel;
    private TeamListModel teamListModel;

    // EFFECTS: Creates a TournamentGUI JFrame
    public TournamentGUI(Tournament tournament) {
        ImageIcon image = new ImageIcon("data/Carrot.png");
        this.setTitle("Tournament App");
        this.setLayout(new GridLayout(2,2));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(800,600));
        this.setResizable(false);
        this.setIconImage(image.getImage());

        teamListModel = new TeamListModel(tournament);
        teamListPanel = new TeamListPanel(teamListModel);
        addTeamPanel = new AddTeamPanel(tournament, teamListModel);
        menuPanel = new MenuPanel(tournament);
        displayPanel = new DisplayPanel(tournament);

        this.add(teamListPanel);
        this.add(displayPanel);
        this.add(addTeamPanel);
        this.add(menuPanel);

        this.setVisible(true);
        closeWindow();
    }

    private void closeWindow() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                for (Event t : EventLog.getInstance()) {
                    System.out.println("\n" + t.getDate() + ": \n" + t.getDescription());
                }
            }
        });
    }
}
