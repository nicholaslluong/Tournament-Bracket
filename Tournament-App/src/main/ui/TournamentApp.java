package ui;

import model.Team;
import model.Tournament;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// runTournament, init, process Command, Display Menu
// Template of code from TellerApp

// Used portions of code provided by class.
// TournamentApp is an ui which allows the user to create a tournament
public class TournamentApp {
    private static final String JSON_STORE = "./data/tournament.json";
    private Tournament tournament;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // Runs tournament and initializes json Writer/Reader
    public TournamentApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runTournament();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runTournament() {
        boolean keepGoing = true;
        String command;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("quit")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nProgram Ended");
    }

    // MODIFIES: this
    // EFFECTS: processes user commands
    private void processCommand(String command) {
        if (command.equals("a")) {
            addTeam();
        } else if (command.equals("b")) {
            editor();
        } else if (command.equals("c")) {
            prizePool();
        } else if (command.equals("d")) {
            title();
        } else if (command.equals("e")) {
            getTeams();
        } else if (command.equals("s")) {
            saveTournament();
        } else if (command.equals("l")) {
            loadTournament();
        } else {
            System.out.println("Invalid Input");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes tournament
    private void init() {
        ArrayList<Team> teams = new ArrayList<>();
        tournament = new Tournament("title",0,teams);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays options menu
    private void displayMenu() {
        System.out.println("menu:");
        System.out.println("\t(a) add team");
        System.out.println("\t(b) edit team");
        System.out.println("\t(c) edit prize pool");
        System.out.println("\t(d) edit title");
        System.out.println("\t(e) view current teams");
        System.out.println("\t(s) -> save tournament to file");
        System.out.println("\t(l) -> load tournament from file");
        System.out.println("\tquit");
    }

    // MODIFIES: this
    // EFFECTS: adds a team to the tournament
    private void addTeam() {
        System.out.println("Enter Team Name");
        String name = input.next();
        System.out.println("Enter Team's odds");
        String odds = input.next();
        tournament.addTeam(name,odds);
    }

    // MODIFIES: this
    // EFFECTS: edits a team given its old name, if no team found, returns to display menu.
    private void editor() {
        System.out.println("please enter the name of the team you wish to edit");
        String teamName = input.next();
        if (tournament.editTeam(teamName)) {
            addTeam();
        } else {
            System.out.println("Team not found");
        }
    }

    // MODIFIES: this
    // EFFECTS: edits the prize pool
    private void prizePool() {
        System.out.println("current prize pool is " + tournament.getPrizePool());
        System.out.println("Please enter the prize pool amount");
        String prize = input.next();
        tournament.changePrizePool(prize);
    }

    // MODIFIES: this
    // EFFECTS: edits the tournament title
    public void title() {
        System.out.println("Current Title is " + tournament.getTitle());
        System.out.println("What is the new name of the tournament?");
        String tournamentName = input.next();
        tournament.changeTitle(tournamentName);
    }

    // EFFECTS: lists all teams currently in tournament
    public void getTeams() {
        System.out.println("Current teams in tournament:");
        int j = tournament.getTournamentSize();
        for (int i = 0; i < j; i++) {
            System.out.println((i + 1) + ": " + tournament.getTeamName(i)
                    + ", odds of winning: " + tournament.getTeamOdds(i));
        }
    }

    // EFFECTS: saves the tournament to file
    private void saveTournament() {
        try {
            jsonWriter.open();
            jsonWriter.write(tournament);
            jsonWriter.close();
            System.out.println("Saved " + tournament.getTitle() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads tournament from file
    private void loadTournament() {
        try {
            tournament = jsonReader.read();
            System.out.println("Loaded " + tournament.getTitle() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
