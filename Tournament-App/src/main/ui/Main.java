package ui;

import model.Team;
import model.Tournament;
import ui.gui.TournamentGUI;

import java.util.ArrayList;

// Runs the program
// Used portions of code provided by class.
public class Main {
    public static void main(String[] args) {
        new TournamentGUI(new Tournament("Default Tournament", 0, new ArrayList<>()));
    }
}