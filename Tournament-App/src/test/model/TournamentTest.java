package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Test Tournament class
public class TournamentTest {
    private Tournament tournament1;
    private Tournament tournament2;
    private List<Team> teams = new ArrayList<>();

    @BeforeEach
    public void runBefore() {
        ArrayList<Team> teams = new ArrayList<>();
        tournament1 = new Tournament("tournament", 0, teams);
    }

    @Test
    public void testConstructorTeam() {
        assertEquals("tournament",tournament1.getTitle());
        assertEquals(0,tournament1.getPrizePool());
    }

    @Test
    public void testGetTitle() {
        assertEquals("tournament",tournament1.getTitle());
    }

    @Test
    public void testChangeTitle() {
        tournament1.changeTitle("new team");
        assertEquals("new team", tournament1.getTitle());
    }

    @Test
    public void testGetPrizePool() {
        assertEquals(0,tournament1.getPrizePool());
    }

    @Test
    public void testChangePrizePool() {
        tournament1.changePrizePool("30");
        assertEquals(30,tournament1.getPrizePool());
        try {
            tournament1.changePrizePool("error");
        } catch (Exception e) {
            //expected
        }
    }

    @Test
    public void testGetTeamSize() {
        Team team1 = new Team("team1", 30);
        Team team2 = new Team("team2", 70);
        Team team3 = new Team("team3", -20);
        ArrayList<Team> newTeams = new ArrayList<>();
        newTeams.add(team1);
        newTeams.add(team2);
        newTeams.add(team3);
        tournament2 = new Tournament("tournament", 0, newTeams);
        assertEquals(3,tournament2.getTournamentSize());
    }

    @Test
    public void testGetTeamOdds() {
        Team team1 = new Team("team1", 30);
        ArrayList<Team> newTeams = new ArrayList<>();
        newTeams.add(team1);
        try {
            tournament2 = new Tournament("tournament", 0, newTeams);
            assertEquals(30,tournament2.getTeamOdds(0));
        } catch (Exception e) {
            fail("No Exception Expected");
        }

        try {
            tournament2 = new Tournament("tournament", 0, newTeams);
            tournament2.getTeamOdds(1);
            fail("Exception Expected");
        } catch (Exception e) {
            //expected
        }
        tournament2 = new Tournament("tournament", 0, newTeams);
        assertEquals(30,tournament2.getTeamOdds(0));
    }
    @Test
    public void testGetTeamName() {
        Team team1 = new Team("team1", 30);
        ArrayList<Team> newTeams = new ArrayList<>();
        newTeams.add(team1);
        tournament2 = new Tournament("tournament", 0, newTeams);
        try {
            String name = tournament2.getTeamName(0);
            assertEquals("team1",name);
        } catch (Exception e) {
            fail("No Exception Expected");
        }

        try {
            tournament2.getTeamName(1);
            fail("Exception Expected");
        } catch (Exception e) {
            //expected
        }

    }

    @Test
    public void testAddTeam() {
        tournament1.addTeam("team2","30");
        String name = tournament1.getTeamName(0);
        assertEquals("team2",name);
        try {
            tournament1.addTeam("team3","error");
        } catch (Exception e) {
            //expected
        }

    }

    @Test
    public void testEditTeam() {
        tournament1.addTeam("newTeamName","30");
        assertTrue(tournament1.editTeam("newTeamName"));
        assertFalse(tournament1.editTeam("newTeamName"));
        tournament1.addTeam("team1","30");
        tournament1.addTeam("Team2", "40");
        assertFalse(tournament1.editTeam("newTeamName"));
    }

    @Test
    public void testGetTeamNames() {
        List<String> newTeams = new ArrayList<>();
        newTeams.add("team1");
        newTeams.add("team2");
        newTeams.add("team3");
        tournament1.addTeam("team1","30");
        tournament1.addTeam("team2","70");
        tournament1.addTeam("team3","23");
        assertEquals(tournament1.getTeamNames(), newTeams);
    }

    @Test
    public void testGetTeams() {
        assertEquals(teams, tournament1.getTeams());
        tournament1.addTeam("NewTeam", "23");
        assertNotEquals(teams, tournament1.getTeams());
    }

}

