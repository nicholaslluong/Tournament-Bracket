package persistence;

import model.Team;
import model.Tournament;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Portions of code taken from code provided by class
// tests JsonReader Class
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Tournament tournament = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTournament.json");
        try {
            Tournament tournament = reader.read();
            assertEquals("title", tournament.getTitle());
            assertEquals(0, tournament.getTournamentSize());
            assertEquals(0,tournament.getPrizePool());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralTournament.json");
        try {
            Tournament tournament = reader.read();
            assertEquals("New Tournament", tournament.getTitle());
            List<Team> teams = tournament.getTeams();
            assertEquals(2, teams.size());
            checkTeam("team1", 23, teams.get(0));
            checkTeam("team2", 34, teams.get(1));
            assertEquals(400,tournament.getPrizePool());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
