import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class ValidatorTest {
    @Test
    public void solverConstructorTest() throws IOException {
        Validator validator = new Validator("input\\Puzzle-4x4-0001.txt","output.txt");
        String gridValues[][]= validator.getGridValues();
        int backTrackingCount=0;
        int onePossibleCount=0;
        int humanKindCount=0;
        long totalTimeTaken1=0;
        long totalTimeTaken2=0;
        BackTracking backTracking=new BackTracking(gridValues);
        validator.validity(gridValues, gridValues[0].length);
        long startTime = System.nanoTime();


        long startTime3 = System.nanoTime();
        backTracking.solve();
        long endTime3 = System.nanoTime();
        long totalTimeTaken3=endTime3-startTime3;
        backTrackingCount=backTracking.getBackTrackingCount();
        gridValues = backTracking.getBoard();
        backTracking.consoleDisplay();
        long endTime = System.nanoTime();
        long totalTimeTaken=endTime-startTime;
        File output = new File("output.txt");
        validator.writeOutputFile(output,gridValues,onePossibleCount,humanKindCount,backTrackingCount,totalTimeTaken,totalTimeTaken1,totalTimeTaken2,totalTimeTaken3);

    }

    @Test
    public void testWriteErrorOutputFile() throws IOException {
         Validator validator=new Validator("invalid.txt","invalidOutput.txt");
    }

}