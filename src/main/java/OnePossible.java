public class OnePossible extends SolverTemplate {

    private String sudokuGrid[][];
    private String[] validSymbols;
    private int size;
    private int onePossibleCount;

    public OnePossible(String gridValues[][]) {

        super(gridValues);
        this.size = getSize();
        this.validSymbols = getValidSymbols();
        this.sudokuGrid = getSudokuGrid();
        this.onePossibleCount=0;
    }

    @Override
    public boolean solve() {
        int initialZeroCount = 0;
        int finalZeroCount = 0;
        this.onePossibleCount++;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; ++j) {
                if (sudokuGrid[i][j].equals("0")) {
                    initialZeroCount++;
                }
            }
        }

        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (sudokuGrid[i][j].equals("0")) {
                    for (String num : validSymbols) {
                        if (isValid(this.sudokuGrid, i, j, num)) {
                            this.sudokuGrid[i][j] = num;
                            setValue(i, j, num);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (sudokuGrid[i][j].equals("0")) {
                    finalZeroCount++;
                }
            }
        }
        if (initialZeroCount != finalZeroCount) {
            solve();
        }
        setSudokuGrid(this.sudokuGrid);
        setOnePossibleCount(this.onePossibleCount);
        return true;
    }

}
