package persistence;

import model.Team;
import model.Tournament;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.json.*;

// Used portions of code provided by class.
// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads tournament from file and returns it
    //          throws IOException if an error occurs during reading from file
    public Tournament read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTournament(jsonObject);
    }

    // EFFECTS: reads source file as String, returns String
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses tournament from JSON object and returns it
    private Tournament parseTournament(JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        int prizePool = jsonObject.getInt("prizePool");
        ArrayList<Team> teams = new ArrayList<>();
        Tournament tournament = new Tournament(title, prizePool, teams);
        addTeams(tournament, jsonObject);
        return tournament;
    }

    // MODIFIES: tournament
    // EFFECTS: parses teams from JSON object and adds them to tournament
    private void addTeams(Tournament tournament, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("teams");
        for (Object json : jsonArray) {
            JSONObject nextTeam = (JSONObject) json;
            addTeam(tournament, nextTeam);
        }
    }

    // MODIFIES: tournament
    // EFFECTS: parses team from JSON object and adds it to tournament
    private void addTeam(Tournament tournament, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String odds = String.valueOf(jsonObject.getInt("odds"));
        tournament.addTeam(name, odds);
    }
}