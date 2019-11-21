import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class OnePossibleTest {
    @Test
    public void onepossibleSolutionSolve() throws IOException {
        Validator validator = new Validator("input\\Puzzle-9x9-0101.txt","output.txt");
        String gridValues[][]= validator.getGridValues();
        OnePossible onePossible= new OnePossible(gridValues);
        assertEquals(onePossible.getSize(),gridValues[0].length);
        onePossible.solve();
        assertEquals(2,onePossible.getOnePossibleCount());
    }
}