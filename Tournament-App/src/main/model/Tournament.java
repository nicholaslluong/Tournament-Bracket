package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Tournament class is a class which contains the title, prize pool, and a list of teams.
// Used portions of code provided by class.
public class Tournament implements Writable {
    private String title;
    private int prizePool;
    private List<Team> teams;

    public Tournament(String title, int prizePool, List<Team> teams) {
        this.title = title;
        this.prizePool = prizePool;
        this.teams = teams;
    }

    // MODIFIES: this
    // EFFECTS: changes tournament title
    public void changeTitle(String newTitle) {
        this.title = newTitle;
    }

    // EFFECTS: gets tournament title
    public String getTitle() {
        return this.title;
    }

    // MODIFIES: this
    // EFFECTS: changes tournament prize pool
    public void changePrizePool(String prize) {
        try {
            Integer.parseInt(prize);
            this.prizePool = Integer.parseInt(prize);
        } catch (Exception e) {
            System.out.println("invalid input, please try again");
        }
    }

    // EFFECTS: gets tournament prize pool
    public int getPrizePool() {
        return this.prizePool;
    }

    // MODIFIES: this
    // EFFECTS: adds team to the tournament
    public void addTeam(String name, String odds) {
        try {
            Integer.parseInt(odds);
            Team team = new Team(name, Integer.parseInt(odds));
            teams.add(team);
            System.out.println("Team " + name + " added to tournament.");
        } catch (Exception e) {
            System.out.println("invalid input, please try again");
        }
    }

    // MODIFIES: this
    // EFFECTS: removes team, then adds a new team to replace it
    public boolean editTeam(String teamName) {
        int i;
        for (i = 0; i < teams.size(); i++) {
            if (teams.get(i).getName().equals(teamName)) {
                teams.remove(i);
                return true;
            }
        }
        return false;
    }

    // EFFECTS: gets a teams name at index i
    public String getTeamName(int i) {
        return teams.get(i).getName();
    }

    // EFFECTS: returns the name of all teams participating in tournament
    public List<String> getTeamNames() {
        List<String> teamNames = new ArrayList<>();
        for (int i = 0; i < getTournamentSize(); i++) {
            teamNames.add(teams.get(i).getName());
        }
        return teamNames;
    }

    // EFFECTS: gets the odds of the given team
    public int getTeamOdds(int i) {
        return teams.get(i).getOdds();
    }

    // EFFECTS: gets the tournament size
    public int getTournamentSize() {
        return teams.size();
    }

    // EFFECTS: returns list of teams
    public List<Team> getTeams() {
        return teams;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("teams", teamsToJson());
        json.put("prizePool", getPrizePool());
        return json;
    }

    // EFFECTS: stores list of teams to JSON file, returns jsonArray
    private JSONArray teamsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Team t : teams) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
}
