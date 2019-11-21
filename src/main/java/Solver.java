import java.io.*;
import java.sql.SQLOutput;

public class Solver {

    private String[][] puzzleArray;
    private String[][] gridValues;
    private int gridSize;
    private String gridSymbols[];
    private File puzzleInputFile;
    private File puzzleOutputFile;


    public Solver(String puzzleInput, String resultFile) throws IOException {
        puzzleInputFile = new  File(puzzleInput);
        if(resultFile!=null){
            puzzleOutputFile= new File(resultFile);
        }
        FileIO();
    }

    public void FileIO() throws IOException {
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
            if(puzzleOutputFile!=null){
                writeErrorOutputFile("Grid is not of size n x n");
            }
            System.exit(1);
        }

        this.puzzleArray = new String[gridSize + 1][gridSize];
        this.gridValues = new String[gridSize+1][gridSize];

        for (int i = 0; i <=gridSize; i++) {
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

    public void validity(String[][] gridValues, int size) throws IOException {
        try {
            String errorMessage=null;
            String puzzleGrid[][] = new String[gridSize][gridSize];
            Boolean foundSymbol= false;
            int g,h;
            for ( g = 1; g < gridSize + 1; g++) {
                for ( h = 0; h < gridSize; h++) {
                    for(int k=0; k< gridSymbols.length; k++) {
                        if (gridValues[g][h].equals(gridSymbols[k]) || gridValues[g][h] == "0") {
                            foundSymbol = false;
                            break;
                        } else {
                            foundSymbol = true;
                            errorMessage="Invalid Symbol in the puzzle";
                        }
                    }
                    if(foundSymbol){
                        g=gridSize+2;
                        h=gridSize+2;
                        break;
                    }
                }
            }

            for (int i = 1; i < gridSize + 1; i++) {
                for (int j = 0; j < gridSize; j++) {
                    puzzleGrid[i - 1][j] = gridValues[i][j];
                }
            }
            if (puzzleGrid == null) {
                System.out.println("puzzle is null");
                errorMessage="puzzle is null";
                foundSymbol=true;
            }
            for (int i = 1; i < gridSize; i++) {
                if (puzzleGrid[i].length != gridSize) {
                    System.out.println("Invalid Format");
                    errorMessage="puzzle is in Invalid Format";
                    foundSymbol=true;
                    break;

                }
            }
            for (int i = 1; i < gridSize; i++) {
                if (puzzleGrid.length != gridSize) {
                    System.out.println("Puzzle size is not correct");
                    errorMessage="puzzle size i snot correct";
                    foundSymbol=true;
                    break;
                }
            }

            for (int i = 0; i < gridSize; i++) {
                String[] colBoard = new String[gridSize];
                for (int j = 0; j < gridSize; j++) {
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
                                    System.out.println("Puzzle has Invalid Column Count");
                                    errorMessage="Puzzle has Invalid Column Count";
                                    foundSymbol=true;
                                    break;

                                }
                            }
                        }
                        n++;
                    }
                    m++;
                }
            }

            for (int i = 0; i < gridSize; i++) {
                String[] rowBoard = new String[gridSize];
                for (int j = 0; j < gridSize; j++) {
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
                                    System.out.println("Puzzle has Invalid row Count");
                                    errorMessage="Puzzle has Invalid Column Count";
                                    foundSymbol=true;
                                    break;
                                }
                            }
                        }
                        n++;
                    }
                    m++;
                }
            }

            for (int i = 0; i < gridSize; i++) {
                for (int j = 0; j < gridSize; j++) {
                    String[] subGrid = getSectionOfGrid(i, j, String.valueOf(puzzleGrid[i][j]), gridSize, puzzleGrid);
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
                                        System.out.println("Subgrid has repeated symbols");
                                        errorMessage="Subgrid has repeated symbols";
                                        foundSymbol=true;
                                        break;
                                    }
                                }
                            }
                            n++;
                        }
                        m++;
                    }
                }
            }
            if(foundSymbol){
                System.out.println(errorMessage);
                if(puzzleOutputFile!=null){
                    writeErrorOutputFile(errorMessage);
                }
                System.exit(1);
            }
        }catch(Exception ex){
            String fault =ex.toString();
            if(fault.equals("java.lang.NullPointerException")){
                System.out.println("invalid puzzle -> Format is incorrect");
                writeErrorOutputFile("Invalid:not formatted correctly");
                System.exit(1);
            }
        }

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

    public void writeErrorOutputFile(String errorMessage) throws IOException {
        BufferedReader read= new BufferedReader(new FileReader(puzzleInputFile));
        BufferedWriter write= new BufferedWriter(new FileWriter(puzzleOutputFile));
        String row= null;
        while ((row=read.readLine())!=null){
            write.write(row);
            write.newLine();
        }
        write.newLine();
        write.write(errorMessage);
        write.close();
    }
}
