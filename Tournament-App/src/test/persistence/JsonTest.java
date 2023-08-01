package persistence;

import model.Team;
import model.Tournament;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Tests Json
public class JsonTest {
    protected void checkTeam(String name, int odds, Team team) {
        assertEquals(name, team.getName());
        assertEquals(odds, team.getOdds());
    }
}