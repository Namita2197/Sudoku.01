public class HumanKind extends Strategy{

    private String sudokuGrid[][];
    private String[] validSymbols;
    private int size;
    private int humanKindCount;

    public HumanKind(String gridValues[][]) {

        super(gridValues);
        this.size = getSize();
        this.validSymbols = getValidSymbols();
        this.sudokuGrid = getSudokuGrid();
        this.humanKindCount=0;

    }

    @Override
    public boolean solve() {
        int initialZeroCount = 0;
        int finalZeroCount = 0;
        humanKindCount++;

        for (int i = 0; i < size; i++) {
            for (int j=0; j < size; ++j) {
                if (this.sudokuGrid[i][j].equals("0")) {
                    initialZeroCount++;
                }
            }
        }

        for(int i =0; i<size; ++i){
            for (int j = 0 ; j<size ; ++j){
                if(this.sudokuGrid[i][j].equals("0")){
                    if(rowValidity(i)){
                        String [] checkRow = getRow(i);
                        for(int k =0; k<size; ++k){
                            for(int l = 0; l<size; ++l){
                                if (validSymbols[k].equals(checkRow[l])){
                                    break;
                                }
                                if(l == size-1){
                                    this.sudokuGrid[i][j]=validSymbols[k];
                                }
                            }
                        }
                    }
                    if(columnValidity(j)){
                        String [] checkColumn = getColumn(j);
                        for(int k =0; k<size; ++k){
                            for(int l = 0; l<size; ++l){
                                if (validSymbols[k].equals(checkColumn[l])){
                                    break;
                                }
                                if(l == size-1){
                                    this.sudokuGrid[i][j]=validSymbols[k];
                                }
                            }
                        }
                    }
                    if(gridvalidity(i,j)){
                        String [] checkGrid = getgrid(i,j);
                        for(int k =0; k<size; ++k){
                            for(int l = 0; l<size; ++l){
                                if (validSymbols[k].equals(checkGrid[l])){
                                    break;
                                }
                                if(l == size-1){
                                    this.sudokuGrid[i][j]=validSymbols[k];
                                }
                            }
                        }
                    }
                }
            }
        }
        for(int i = 0; i<size; ++i){
            for(int j = 0; j<size; ++j){
                if (this.sudokuGrid[i][j].equals("0")){
                    finalZeroCount++;
                }
            }
        }
        if(initialZeroCount != finalZeroCount){
            solve();
        }
        setSudokuGrid(this.sudokuGrid);
        setHumanKindCount(this.humanKindCount);
        return true;
    }
}
