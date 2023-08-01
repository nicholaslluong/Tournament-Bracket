package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

// Tests Team class
public class TeamTest {
    private Team team1;
    private Team team2;

    @BeforeEach
    public void runBefore() {
        team1 = new Team("team1", 30);
        team2 = new Team("team2", -40);
    }

    @Test
    public void testConstructorTeam() {
        assertEquals("team1",team1.getName());
        assertEquals(30,team1.getOdds());
        assertEquals("team2", team2.getName());
        assertEquals(0,team2.getOdds());
    }

    @Test
    public void testGetName() {
        assertEquals("team1",team1.getName());
        assertEquals("team2",team2.getName());
    }

    @Test
    public void testChangeName() {
        team1.changeName("New Team");
        assertEquals("New Team", team1.getName());
    }

    @Test
    public void testGetOdds() {
        assertEquals(30,team1.getOdds());
        assertEquals(0,team2.getOdds());
    }

    @Test
    public void testChangeOdds() {
        team1.changeOdds(50);
        assertEquals(50,team1.getOdds());
        team1.changeOdds(-30);
        assertEquals(0,team1.getOdds());
    }
}
