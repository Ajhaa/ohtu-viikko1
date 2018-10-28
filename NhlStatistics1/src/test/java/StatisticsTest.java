package test.java;

import main.java.Reader;
import main.java.Player;

import java.util.ArrayList;
import java.util.List;
import main.java.Statistics;

import org.junit.*;
import org.junit.Before;
import static org.junit.Assert.*;

public class StatisticsTest {
    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void searchReturnsPlayerIfExists() {
        Player p = stats.search("Semenko");
        assertEquals("Semenko", p.getName());
    }
    
    @Test
    public void searchReturnsNullIfPlayerDoesntExist() {
        Player p = stats.search("Luke");
        assertEquals(null, p);
    }
    
    @Test
    public void teamReturnsCorrectPlayers() {
        List<Player> team = stats.team("EDM");
        String[] names = team.stream().map(a -> a.getName()).toArray(String[]::new);
        String[] expected = {"Semenko", "Kurri", "Gretzky"};
        
        assertArrayEquals(expected, names);
    }
    
    @Test
    public void teamReturnsEmptyIfNonexistentTeam() {
        List<Player> team = stats.team("KUMPULA");
        
        assertTrue(team.isEmpty());
    }
    
    @Test
    public void topScorerReturnsCorrectAmountOfPlayers() {
        List<Player> top = stats.topScorers(3);
        
        assertEquals(3, top.size());
    }
    
    @Test
    public void topScorerWithOneReturnsCorrectPlayer() {
        List<Player> top = stats.topScorers(1);
        
        assertEquals(1, top.size());
        assertEquals("Gretzky", top.get(0).getName());
    }
    
    
   
}
