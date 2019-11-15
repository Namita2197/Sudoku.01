import java.io.*;
import java.sql.SQLOutput;

public class Solver {

    private String[][] puzzleArray;
    private String[][] gridValues;
    private int gridSize;
    private String gridSymbols[];

    public Solver(String puzzleInputFile) throws IOException {

            FileReader fileReader = new FileReader(puzzleInputFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            this.gridSize = Integer.valueOf(bufferedReader.readLine());

            BufferedReader bufferedReader1 = new BufferedReader(new FileReader(puzzleInputFile));
            int rows=0;
            while(bufferedReader1.readLine()!=null) {
                rows++;
            }
            bufferedReader1.close();
            if(gridSize!=rows-2){
                System.out.println("Grid is not of size n x n");
                System.exit(1);
            }

            puzzleArray = new String[gridSize + 1][gridSize];
            this.gridValues = new String[gridSize+1][gridSize];

             for (int i = 0; i < gridSize+1; i++) {
                 String allValues = bufferedReader.readLine();
                 String temp[] = allValues.split(" ");

                 for (int k = 0; k < temp.length; k++) {
                     if (temp[k].equals("-")) {
                         puzzleArray[i][k] = "0";
                     }else {
                         puzzleArray[i][k] = temp[k];
                     }
                 }
             }

        gridSymbols=new String[gridSize];
        for(int x=0; x< puzzleArray[0].length; x++){
            this.gridSymbols[x]= puzzleArray[0][x];
        }

        for (int y = 0; y < gridSize + 1; y++) {
            for (int z = 0; z < gridSize; z++) {
                this.gridValues[y ][z] = puzzleArray[y][z];
            }
        }
    }

    public String[][] getGridValues(){
        return gridValues;
    }

    public boolean validity(String[][] gridValues, int size){
        try {
            String puzzleGrid[][] = new String[size][size];
            for (int i = 1; i < size + 1; i++) {
                for (int j = 0; j < size; j++) {
                    puzzleGrid[i - 1][j] = gridValues[i][j];
                }
            }
            if (puzzleGrid == null) {
                System.out.println("Sudoku is null");
                return false;
            }
            for (int i = 1; i < size; i++) {
                if (puzzleGrid[i].length != size) {
                    System.out.println("Invalid Format");
                    return false;
                }
            }
            for (int i = 1; i < size; i++) {
                if (puzzleGrid.length != size) {
                    System.out.println("Invalid Format");
                    return false;
                }
            }

            for (int i = 0; i < size; i++) {
                String[] colBoard = new String[size];
                for (int j = 0; j < size; j++) {
                    colBoard[j] = puzzleGrid[j][i];
                }
                int m = 0;
                while (m < colBoard.length) {
                    int n = 0;
                    while (n < colBoard.length) {

                        if (colBoard[m].equals(colBoard[n])) {
                            if (m == n) {

                            } else {
                                if (Integer.parseInt(colBoard[m]) == 0 && Integer.parseInt(colBoard[m]) == 0) {

                                } else {
                                    System.out.println("Invalid Format");
                                    return false;
                                }
                            }
                        }
                        n++;
                    }
                    m++;
                }
            }

            for (int i = 0; i < size; i++) {
                String[] rowBoard = new String[size];
                for (int j = 0; j < size; j++) {
                    rowBoard[j] = puzzleGrid[i][j];
                }
                int m = 0;
                while (m < rowBoard.length) {
                    int n = 0;
                    while (n < rowBoard.length) {

                        if (rowBoard[m].equals(rowBoard[n])) {
                            if (m == n) {

                            } else {
                                if (Integer.parseInt(rowBoard[m]) == 0 && Integer.parseInt(rowBoard[m]) == 0) {

                                } else {
                                    System.out.println("Invalid Format");
                                    return false;
                                }
                            }
                        }
                        n++;
                    }
                    m++;
                }
            }

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    String[] subGrid = getSectionOfGrid(i, j, String.valueOf(puzzleGrid[i][j]), size, puzzleGrid);
                    for (int p = 0; p < subGrid.length; p++) {

                    }
                    int m = 0;
                    while (m < subGrid.length) {
                        int n = 0;
                        while (n < subGrid.length) {

                            if (subGrid[m].equals(subGrid[n])) {
                                if (m == n) {

                                } else {
                                    if (Integer.parseInt(subGrid[m]) == 0 && Integer.parseInt(subGrid[m]) == 0) {

                                    } else {
                                        System.out.println("Invalid Format");
                                        return false;
                                    }
                                }
                            }
                            n++;
                        }
                        m++;
                    }
                }
            }
        }catch(Exception ex){
            String fault =ex.toString();
            if(fault.equals("java.lang.NullPointerException")){
                System.out.println("invalid puzzle -> Format is incorrect");
                System.exit(1);
            }
        }
        return true;
    }

    public String[] getSectionOfGrid(int row, int column, String values, int size, String gridValues[][]){
        String [] sectionOfGridValues = new String[size];
        Double sqrt = Math.sqrt(size);
        int squareRoot = sqrt.intValue();
        int r = row - row%squareRoot;
        int c = column - column%squareRoot;
        int k = 0;
        for(int i = r; i<r+squareRoot; i++){

            for(int j = c; j<c+squareRoot; j++){
                sectionOfGridValues[k] = gridValues[i][j];
                k++;
            }
        }

        return sectionOfGridValues;
    }

    public  void writeOutputFile(File outputFile, String [][]puzzleBoard, int backTrackingCount, int OnePossibleCount, int hmanKindCount, long timeUtilised, long time1, long time2, long time3) throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream(outputFile);

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));

        bufferedWriter.write(String.valueOf(puzzleBoard[0].length));
        bufferedWriter.newLine();
        for (int i = 0; i < this.gridSize + 1; i++) {
            for (int j = 0; j < this.gridSize; j++) {
                if (this.puzzleArray[i][j].equals("0")) {
                    bufferedWriter.write("-");
                    bufferedWriter.write(" ");
                } else {
                    bufferedWriter.write(this.puzzleArray[i][j]);
                    bufferedWriter.write(" ");
                }
            }
            bufferedWriter.newLine();

        }

        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.write("Solution:");
        bufferedWriter.newLine();
        bufferedWriter.newLine();

        for (int i = 1; i < this.gridSize + 1; i++) {
            for (int j = 0; j < this.gridSize; j++) {
                bufferedWriter.write(puzzleBoard[i][j]);
                bufferedWriter.write(" ");
            }
            bufferedWriter.newLine();
        }

        bufferedWriter.newLine();
        bufferedWriter.newLine();

        bufferedWriter.write("Total Time:   ");
        bufferedWriter.write(String.valueOf(timeUtilised)+"   ns");
        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.write("Strategy               ");
        bufferedWriter.write("Uses          ");
        bufferedWriter.write("Time   ");
        bufferedWriter.newLine();
        bufferedWriter.write("One Possible Solution  ");
        bufferedWriter.write(OnePossibleCount+"      ");
        bufferedWriter.write(String.valueOf(time1)+"  ns");
        bufferedWriter.newLine();
        bufferedWriter.write("Human Kind Strategy    ");
        bufferedWriter.write(hmanKindCount+"      ");
        bufferedWriter.write(String.valueOf(time2)+"  ns");
        bufferedWriter.newLine();
        bufferedWriter.write("BackTracking           ");
        bufferedWriter.write(backTrackingCount+"      ");
        bufferedWriter.write(String.valueOf(time3)+"  ns");
        bufferedWriter.close();
    }

}
