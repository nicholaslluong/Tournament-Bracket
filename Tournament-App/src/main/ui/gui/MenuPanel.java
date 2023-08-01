package ui.gui;

import model.Tournament;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represents a JPanel which contains Action Listeners, contains save and load button
public class MenuPanel extends JPanel implements ActionListener {
    private JButton save;
    private JButton load;
    private Tournament tournament;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/tournament.json";

    // EFFECTS: Creates MenuPanel Panel.
    MenuPanel(Tournament tournament) {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        this.tournament = tournament;
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(new FlowLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        createButtons();
        this.add(save);
        this.add(load);
    }

    //EFFECTS: Creates the buttons used in this panel, Provides buttons with their specific traits.
    private void createButtons() {
        save = new JButton("Save");
        load = new JButton("Load");
        save.setPreferredSize(new Dimension(200,50));
        load.setPreferredSize(new Dimension(200,50));
        save.setFocusable(false);
        load.setFocusable(false);
        save.addActionListener(this);
        load.addActionListener(this);
    }

    // EFFECTS: Reads whether user presses a button, if button is pressed, runs the appropriate code.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == save) {
            try {
                jsonWriter.open();
                jsonWriter.write(tournament);
                jsonWriter.close();
                JOptionPane.showMessageDialog(null, "File Saved");
            } catch (FileNotFoundException fileNotFoundException) {
                JOptionPane.showMessageDialog(null, "Error Saving File");
            }
        } else if (e.getSource() == load) {
            try {
                tournament = jsonReader.read();
                new TournamentGUI(tournament);
                this.tournament = (new Tournament(tournament.getTitle(), tournament.getPrizePool(),
                        tournament.getTeams()));
                JOptionPane.showMessageDialog(null, "File Loaded");
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(null, "Error Loading File");
            }
        }
    }
}
