package engine.models;

import engine.models.exceptions.AbsentNotInitializePlayerException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TableTest {

    @Test(expected = AbsentNotInitializePlayerException.class)
    public void testGetIndexFirstPlayerNotNull() throws Exception {
        final int maxTablePlayers = 6;
        final Player player = new Player();
        final Table table = new Table(null, maxTablePlayers, 0, null, null);
        for (int i = 0; i < table.getPlayers().length; i++) {
            table.getPlayers()[i] = player;
        }
        table.getIndexFirstPlayerNotNull();
    }

    @Test
    public void testEqualsWhenEqual() throws Exception {
        final Table table1 = new Table("0006098E", 6, 1000, null, null);
        final Table table2 = new Table("0006098E", 6, 1000, null, null);
        final boolean expectedAnswer = true;

        final boolean actualAnswer1 = table1.equals(table2);
        assertEquals(expectedAnswer, actualAnswer1);

        final boolean actualAnswer2 = table2.equals(table1);
        assertEquals(expectedAnswer, actualAnswer2);

        final boolean actualAnswer3 = table1.equals(table1);
        assertEquals(expectedAnswer, actualAnswer3);

        final boolean actualAnswer4 = table2.equals(table2);
        assertEquals(expectedAnswer, actualAnswer4);
    }

    @Test
    public void testEqualsWhenNotEqual() throws Exception {
        final Table table1 = new Table("00400A2A", 6, 1000, null, null);
        final Table table2 = new Table("0006098E", 6, 1000, null, null);
        final Table table3 = new Table("00400A2A", 9, 1000, null, null);
        final Table table4 = new Table("00400A2A", 6, 2000, null, null);
        final boolean expectedAnswer = false;

        final boolean actualAnswer1 = table1.equals(table2);
        assertEquals(expectedAnswer, actualAnswer1);

        final boolean actualAnswer2 = table2.equals(table1);
        assertEquals(expectedAnswer, actualAnswer2);

        final boolean actualAnswer3 = table1.equals(table3);
        assertEquals(expectedAnswer, actualAnswer3);

        final boolean actualAnswer4 = table3.equals(table1);
        assertEquals(expectedAnswer, actualAnswer4);

        final boolean actualAnswer5 = table1.equals(table4);
        assertEquals(expectedAnswer, actualAnswer5);

        final boolean actualAnswer6 = table4.equals(table1);
        assertEquals(expectedAnswer, actualAnswer6);
    }

}