package ui.gui;

import model.EventLog;
import model.Event;
import model.Tournament;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a JPanel which contains action listeners. Contains Add and Remove Team button
public class AddTeamPanel extends JPanel implements ActionListener {
    private JButton addTeam;
    private JButton removeTeam;
    private JTextField name;
    private JTextField odds;
    private JLabel displayName;
    private JLabel displayOdds;
    private Tournament tournament;
    private TeamListModel teamListModel;

    //EFFECTS: Creates AddTeamPanel.
    AddTeamPanel(Tournament tournament, TeamListModel teamListModel) {
        this.teamListModel = teamListModel;
        this.tournament = tournament;
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(new FlowLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        createButtons();
        createTextFields();
        createLabels();
        this.add(displayName);
        this.add(name);
        this.add(displayOdds);
        this.add(odds);
        this.add(addTeam);
        this.add(removeTeam);
    }

    // EFFECTS: Creates Labels to help user understand the GUI.
    private void createLabels() {
        displayName = new JLabel("Team Name:");
        displayOdds = new JLabel("Team Odds:");
        displayName.setPreferredSize(new Dimension(80,50));
        displayOdds.setPreferredSize(new Dimension(80,50));
    }

    // EFFECTS: Creates TextFields for user inputs.
    private void createTextFields() {
        name = new JTextField();
        odds = new JTextField();
        name.setPreferredSize(new Dimension(250,50));
        odds.setPreferredSize(new Dimension(250,50));
    }

    //EFFECTS: Creates the buttons used in this panel, Provides buttons with their specific traits.
    private void createButtons() {
        addTeam = new JButton("Add Team");
        removeTeam = new JButton("Remove Team");
        addTeam.setFocusable(false);
        removeTeam.setFocusable(false);
        addTeam.addActionListener(this);
        removeTeam.addActionListener(this);
    }

    // EFFECTS: Reads whether user presses a button, if button is pressed, runs the appropriate code.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addTeam) {
            try {
                Integer.parseInt(odds.getText());
                addTeamButton(name.getText(), odds.getText());
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Please use a numerical value for Odds");
            }
        } else if (e.getSource() == removeTeam) {
            removeTeamButton(name.getText());
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Entry. \n"
                    + "Please enter the name of the team you want to remove, then press the Remove Team button");
        }
    }

    private void removeTeamButton(String name) {
        if (tournament.getTeamNames().contains(name)) {
            for (int i = 0; i < tournament.getTournamentSize(); i++) {
                if (name.equals(tournament.getTeamName(i))) {
                    tournament.getTeams().remove(i);
                    teamListModel.refresh();
                    EventLog.getInstance().logEvent(new Event("Removed Team: " + name));
                    break;
                }
            }
        }
    }

    private void addTeamButton(String name, String odds) {
        this.tournament.addTeam(name, odds);
        this.teamListModel.refresh();
        EventLog.getInstance().logEvent(new Event("Added Team: " + name + ", " + odds));
    }
}
