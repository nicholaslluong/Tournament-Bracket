package ui.gui;

import model.EventLog;
import model.Event;
import model.Tournament;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a JPanel which contains action listeners. User can edit Title and Prize Pool.
public class DisplayPanel extends JPanel implements ActionListener {
    private JButton changeTitle;
    private JButton changePrize;
    private JTextArea newTitle;
    private JLabel newPrize;
    private JTextField changed;
    private Tournament tournament;

    // EFFECTS: Creates the DisplayPanel for Tournament GUI.
    DisplayPanel(Tournament tournament) {
        this.tournament = tournament;
        this.setBackground(Color.LIGHT_GRAY);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        createLabels();
        createTextFields();
        createButtons();
        this.add(newTitle);
        this.add(newPrize);
        this.add(changed);
        this.add(changeTitle);
        this.add(changePrize);
    }

    // EFFECTS: Creates TextFields for user inputs.
    private void createTextFields() {
        changed = new JTextField();
        changed.setPreferredSize(new Dimension(200,50));
    }

    // EFFECTS: Creates Labels to help user understand the GUI.
    private void createLabels() {
        newTitle = new JTextArea("Tournament Name:\n" + tournament.getTitle());
        newTitle.setLineWrap(true);
        newTitle.setEditable(false);
        newTitle.setBackground(Color.LIGHT_GRAY);
        newPrize = new JLabel("Tournament Prize: " + tournament.getPrizePool());
        newTitle.setPreferredSize(new Dimension(200,50));
        newPrize.setPreferredSize(new Dimension(200,50));
    }

    //EFFECTS: Creates the buttons used in this panel, Provides buttons with their specific traits.
    private void createButtons() {
        changeTitle = new JButton("Change Tournament Name");
        changePrize = new JButton("Change Tournament Prize");
        changeTitle.setPreferredSize(new Dimension(350,50));
        changePrize.setPreferredSize(new Dimension(350,50));
        changeTitle.setFocusable(false);
        changePrize.setFocusable(false);
        changeTitle.addActionListener(this);
        changePrize.addActionListener(this);
    }

    // EFFECTS: Reads whether user presses a button, if button is pressed, runs the appropriate code.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == changeTitle) {
            tournament.changeTitle(changed.getText());
            newTitle.setText("Tournament Name:\n" + tournament.getTitle());
            EventLog.getInstance().logEvent(new Event("Tournament name changed to: "
                    + changed.getText()));
        } else if (e.getSource() == changePrize) {
            try {
                Integer.parseInt(changed.getText());
                tournament.changePrizePool(changed.getText());
                newPrize.setText("Tournament Prize: " + tournament.getPrizePool());
                EventLog.getInstance().logEvent(new Event("Tournament prize changed to: "
                        + changed.getText()));
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null,
                        "Invalid Entry, please use numerical values");
            }
        }
    }
}
