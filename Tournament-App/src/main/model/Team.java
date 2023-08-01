package model;

import org.json.JSONObject;
import persistence.Writable;

// Used portions of code provided by class.
// Team is a class which contains the teams name and odds of winning.
public class Team implements Writable {
    private String name;
    private int odds;

    // EFFECTS: Set String as team name and int as odds, if given negative int, set odds to 0
    public Team(String name, int odds) {
        this.name = name;
        this.odds = Math.max(odds, 0);
    }

    // MODIFIES: Bracket odds
    // EFFECTS: edit the odds of a bracket with given amount
    public void changeOdds(int newOdds) {
        this.odds = Math.max(newOdds, 0);
    }

    // MODIFIES: Bracket name
    // EFFECTS: edit the name of a bracket with given string
    public void changeName(String newName) {
        this.name = newName;
    }

    // EFFECTS: gets name of team
    public String getName() {
        return this.name;
    }

    // EFFECTS: gets odds of team
    public int getOdds() {
        return this.odds;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("odds", odds);
        return json;
    }
}