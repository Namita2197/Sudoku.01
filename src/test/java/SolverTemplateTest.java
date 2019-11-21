import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class SolverTemplateTest {
    @Test
    public void testConstructor() throws IOException {
        Validator validator = new Validator("input\\Puzzle-16x16-0201.txt","output.txt");
        String board[][]= validator.getGridValues();
        BackTracking backTracking = new BackTracking(board);
        backTracking.getBoard();
        String board1[][]=backTracking.getBoard();
        for (int i=0; i<backTracking.getSize()+1;i++) {
            for (int j=0; j<backTracking.getSize();j++){

                assertEquals(board[i][j], board1[i][j]);
            }}

        String []validSymbols=new String[backTracking.getSize()];
        for (int j=0; j<backTracking.getSize();j++) {
            validSymbols[j]=board1[0][j];
        }

        String [] validSymbols1= backTracking.getValidSymbols();

        for (int j=0; j<backTracking.getSize();j++){
            assertEquals(validSymbols1[j], validSymbols[j]);
        }

        backTracking.getSudokuGrid();
        backTracking.setSudokuGrid(board1);
        backTracking.setValue(1,2,"8");
        board=backTracking.getSudokuGrid();
        assertEquals(board[1][2],"8");

        backTracking.setBackTrackingCount(8);
        assertEquals(backTracking.getBackTrackingCount(),8);
        backTracking.setOnePossibleCount(2);
        assertEquals(backTracking.getOnePossibleCount(),2);
        backTracking.setHumanKindCount(10);
        assertEquals(backTracking.getHumanKindCount(),10);


        backTracking.rowValidity(1);
        backTracking.columnValidity(2);
        backTracking.gridvalidity(1,2);

        String row[]=backTracking.getRow(5);
        for (int j=0; j<backTracking.getSize();j++){
            assertEquals(row[j],board1[5][j]);
        }

        String column[]=backTracking.getColumn(8);
        for (int j=0; j<backTracking.getSize();j++){
            assertEquals(column[j],board1[j][8]);
        }

        backTracking.getgrid(2,4);

        assertEquals(backTracking.isInRange(board,1,3,"2",board.length),false);
        assertEquals(backTracking.isValid(board,1,3,"2"),false);
        assertEquals(backTracking.existsAlreadyInRow(board,1,"2"),true);
        assertEquals(backTracking.existsAlreadyInCol(board,1,"2"),true);

        backTracking.consoleDisplay();

    }

}