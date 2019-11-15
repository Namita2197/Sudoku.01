import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class BackTrackingTest {

    @Test
    public void backTrackingTest() throws IOException {
        Solver solver = new Solver("input\\Puzzle-4x4-0001.txt");
        String gridValues[][]=solver.getGridValues();
        BackTracking backTracking= new BackTracking(gridValues);
        assertEquals(backTracking.getSize(),gridValues[0].length);
        backTracking.solve();
        assertEquals(backTracking.getBackTrackingCount(),206);
    }

}