package persistence;

import org.json.JSONObject;

// Used portions of code provided by class.
//
public interface Writable {
    // EFFECTS: returns this as JSON Object
    JSONObject toJson();
}