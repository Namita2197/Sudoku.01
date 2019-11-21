import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class HumanKindTest {
    @Test
    public void humanKindTest() throws IOException {
        Validator validator = new Validator("input\\Puzzle-4x4-0001.txt","output.txt");
        String gridValues[][]= validator.getGridValues();
        HumanKind humanKind= new HumanKind(gridValues);
        assertEquals(humanKind.getSize(),gridValues[0].length);
        humanKind.solve();
        assertEquals(humanKind.getHumanKindCount(),2);
    }
}