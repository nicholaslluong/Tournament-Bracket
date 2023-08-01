package persistence;

import model.Team;
import model.Tournament;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Tests Json Writer Class
// Portions of code taken from code provided by class
class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            ArrayList<Team> teams = new ArrayList<>();
            Tournament tournament = new Tournament("title",0, teams);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            ArrayList<Team> teams = new ArrayList<>();
            Tournament tournament = new Tournament("title",0, teams);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTournament.json");
            writer.open();
            writer.write(tournament);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTournament.json");
            tournament = reader.read();
            assertEquals("title", tournament.getTitle());
            assertEquals(0, tournament.getTournamentSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            ArrayList<Team> teams = new ArrayList<>();
            Tournament tournament = new Tournament("title",0, teams);
            tournament.addTeam("team1","23");
            tournament.addTeam("team2","34");
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralTournament.json");
            writer.open();
            writer.write(tournament);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralTournament.json");
            tournament = reader.read();
            assertEquals("title", tournament.getTitle());
            List<Team> teams2 = tournament.getTeams();
            assertEquals(2, teams2.size());
            checkTeam("team1",23, teams2.get(0));
            checkTeam("team2",34, teams2.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}