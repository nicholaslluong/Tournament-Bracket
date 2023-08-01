package ui.gui;

import model.Team;
import model.Tournament;

import javax.swing.*;
// TeamListModel Class used to help display Team Information on TeamListPanel

public class TeamListModel extends DefaultListModel<String> {

    private Tournament tournament;
    // TeamListModel, uses given tournament and elements to JList.

    public TeamListModel(Tournament tournament) {
        this.tournament = tournament;

        for (Team t : tournament.getTeams()) {
            String label = ("Team: " + t.getName() + ", Odds: " + t.getOdds() + "\n");
            this.addElement(label);
        }
    }

    // Returns assigned tournament
    public Tournament getTournament() {
        return this.tournament;
    }

    // When called, removes all elements from JList, then adds them back.
    public void refresh() {
        this.removeAllElements();
        for (Team t : tournament.getTeams()) {
            String label = ("Team: " + t.getName() + ", Odds: " + t.getOdds() + "\n");
            this.addElement(label);
        }
    }
}
