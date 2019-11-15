public abstract class Strategy {

    private String sudokuGrid[][];
    private String[] validSymbols;
    private int size;
    private int backTrackingCount=0;
    private int humanKindCount;
    private int onePossibleCount;


    public Strategy(String gridValues[][]){
        this.sudokuGrid=new String[gridValues[0].length][gridValues[0].length];
        this.size=gridValues[0].length;
        this.validSymbols=new String[this.size];
        for(int i=1;i<gridValues[0].length+1;i++){
            for (int j=0; j<gridValues[0].length;j++) {
                this.sudokuGrid[i-1][j] = gridValues[i][j];
            }
        }

        for (int i = 0; i < gridValues[0].length; i++) {

            this.validSymbols[i] = gridValues[0][i];
        }

    }

    public int getSize() {
        return size;
    }

    public String[][] getBoard(){
        String[][] tempState=new String[size+1][size];
        for(int i=0;i<size+1;i++){
            for (int j=0; j<size;j++) {
                if(i==0)
                {
                    for (int k=0; k<size;k++)
                        tempState[0][k]=validSymbols[k];
                }
                else
                    tempState[i][j]=this.sudokuGrid[i-1][j];
            }
        }
        return tempState;
    }

    public String[][] getSudokuGrid() {
        return sudokuGrid;
    }

    public void setSudokuGrid(String[][] sudokuBoard) {
        this.sudokuGrid = sudokuBoard;
    }

    public String[] getValidSymbols() {
        return validSymbols;
    }

    public void setValue(int row, int col, String number)
    {
        this.sudokuGrid[row][col]=number;
    }

    public void setBackTrackingCount(int backTrackingCount) {
        this.backTrackingCount = backTrackingCount;
    }

    public void setHumanKindCount(int humanKindCount) {
        this.humanKindCount = humanKindCount;
    }

    public int getBackTrackingCount() {
        return backTrackingCount;
    }

    public int getHumanKindCount() {
        return humanKindCount;
    }

    public int getOnePossibleCount() {
        return onePossibleCount;
    }

    public void setOnePossibleCount(int onePossibleCount) {
        this.onePossibleCount = onePossibleCount;
    }

    public boolean rowValidity(int row) {
        int count = 0;
        for (int i = 0; i < this.sudokuGrid[0].length; i++) {
            if (this.sudokuGrid[row][i].equals("0")) {
                count++;
            }
        }
        if (count == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean columnValidity(int col) {
        int count = 0;
        for (int i = 0; i < this.sudokuGrid[0].length; i++) {
            if (this.sudokuGrid[i][col].equals("0")) {
                count++;
            }
        }
        if (count == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean gridvalidity(int row, int col) {
        int count = 0;
        String [] grid = getgrid(row,col);
        for(int i =0; i<this.sudokuGrid[0].length ; ++i){
            if(grid[i].equals("0")){
                count++;
            }
        }
        if (count == 1) {
            return true;
        } else {
            return false;
        }
    }

    public String[] getRow(int row){
        String [] currentRow = new String[size];
        for(int i = 0; i<size; ++i){
            currentRow[i]= this.sudokuGrid[row][i];
        }
        return currentRow;
    }

    public String[] getColumn(int col){
        String [] currentColumn = new String[size];
        for(int i = 0; i<size; ++i){
            currentColumn[i]= this.sudokuGrid[i][col];
        }
        return currentColumn;
    }

    public String[] getgrid(int row, int col){
        String [] grid = new String[size];
        int l = 0;
        Double sqrt = Math.sqrt(size);
        int squareRoot = sqrt.intValue();
        int r = row - row % squareRoot;
        int c = col - col % squareRoot;
        for (int i = r; i < r + squareRoot; i++) {

            for (int j = c; j < c + squareRoot; j++) {
                grid[l] =this.sudokuGrid[i][j];
                l++;
            }
        }
        return grid;
    }

    public boolean existsAlreadyInRow(String board[][],int row, String number) {
        for (int i = 0; i < board[0].length; i++)
            if (board[row][i].equals(number))
                return false;

        return true;
    }

    public boolean existsAlreadyInCol(String board[][], int col, String number) {
        for (int i = 1; i < board[0].length; i++)
            if (board[i][col].equals(number))
                return false;

        return true;
    }

    public boolean isInRange(String board[][], int row, int col, String number, int size) {
        Double sqrt = Math.sqrt(size);
        int squareRoot = sqrt.intValue();
        int r = row - row % squareRoot;
        int c = col - col % squareRoot;
        for (int i = r; i < r + squareRoot; i++) {

            for (int j = c; j < c + squareRoot; j++) {
                if(board[i][j].equals(number))
                    return false;
            }
        }
        return true;
    }

    public boolean isValid(String board[][], int row, int col, String number) {
        return existsAlreadyInRow(board, row, number)  &&  existsAlreadyInCol(board, col, number)  &&  isInRange(board, row, col, number, board[0].length);
    }

    public abstract boolean solve();

    public void consoleDisplay() {
        for (int i = 0; i < sudokuGrid[0].length; i++) {
            for (int j = 0; j < sudokuGrid[0].length; j++) {
                System.out.print(" " + sudokuGrid[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

}
