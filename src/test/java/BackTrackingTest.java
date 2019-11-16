import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class BackTrackingTest {

    @Test
    public void  backTrackingTest() throws IOException {
        Solver sudokuContent = new Solver("input\\Puzzle-9x9-0101.txt");
        String gridValues[][]=sudokuContent.getGridValues();
        BackTracking backTracking= new BackTracking(gridValues);
        assertEquals(backTracking.getSize(),gridValues[0].length);
        backTracking.solve();
        assertEquals(backTracking.getBackTrackingCount(),206);


    }

}