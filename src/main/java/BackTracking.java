public class BackTracking extends Strategy{

    private String sudokuGrid[][];
    String [] validSymbols;
    private int size;
    private int backTrackingCount=0;

    public BackTracking(String gridValues[][]){
        super(gridValues);
        this.size = getSize();
        this.sudokuGrid = getSudokuGrid();
        this.validSymbols = getValidSymbols();
        this.backTrackingCount=0;

    }

    @Override
    public boolean solve() {
        int row = -1;
        int col = -1;

        setBackTrackingCount(this.backTrackingCount);
        boolean isEmpty = true;

        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                if (this.sudokuGrid[i][j].equals("0"))
                {
                    row = i;
                    col = j;

                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty)
            {
                break;
            }
        }

        if (isEmpty)
        {
            return true;
        }

        for (String i : validSymbols)
        {

            if (isValid(this.sudokuGrid, row, col, i ))
            {   this.backTrackingCount++;
                this.sudokuGrid[row][col]=i;
                setValue(row,col,i);
                if (solve())
                {
                    return true;
                }
                else
                {
                    this.sudokuGrid[row][col]="0";
                    setValue(row,col,"0");
                }
            }
        }
        this.setSudokuGrid(this.sudokuGrid);

        return false;
    }
}
