import java.util.HashMap;

public class Main {



    public static boolean solveSudoku(int [][] grid){
        int emptyCell = findEmptyCell(grid);
        if(emptyCell == null){
            return true;
        };

        int row = emptyCell[0];
        int col = emptyCell[1];

        for (int i=0; i<=9; num++) {
            if(isSafe(grid, row, col, num)){
                grid[row][col] = num;

                if(solveSudoku(grid)) {
                    return true;
                }

                grid[row][col]=0;
            }
        }
        return false; // No valid number found, need to backtrack
    }


    public static int[] findEmptyCell(int [][] grid){
        int [] emptyCell = new int[2];
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(grid[i][j] == 0){
                    emptyCell[0] = i;
                    emptyCell[1] = j;
                    return emptyCell;
                }
            }
        }
        return null;
    }


    public static boolean isSafe(int [][] grid, int row, int col, int num){
        //check row
        HashMap<Integer, Integer>  rowMap = new HashMap<>();
        for(int i= 0; i<9; i++) {
            if(grid[row][i] != 0 && rowMap.containsKey(grid[row][i])) {
                return false;           //duplicate number found in the row
            }
            rowMap.put(grid[row][i], 1);
        }

        //check column
        HashMap<Integer, Integer> colMap = new HashMap<>();
        for( int i= 0; i<9; i++){
            if( grid[i][col] != 0 && colMap.containsKey(grid[i][col])) {
                return false;           //duplicate number found in the column
            }
            colMap.put(grid[i][col], 1);
        }

        //check 3x3 box
        int boxStartRow = row - row % 3;
        int boxStartCol = col - col % 3;
        HashMap<Integer, Integer> boxMap = new HashMap<>();
        for( int i = boxStartRow; i < boxStartRow+3; i++){
            for(int j = boxStartCol; j < boxStartCol+3; j++){
                if(grid[i][j] != 0 && boxMap.containsKey(grid[i][j])){
                    return false; //Duplicate number found in the box
                }
                boxMap.put(grid[i][j], 1);
            }
        }
        return true; //no duplicate number found in row, column, or box;
    }

    public static void printGrid(int [][] grid){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.println(grid[i][j] + " ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        int[][] sudokuGrid = {  {7, 0, 0, 0, 0, 0, 2, 0, 0},
                            {4, 0, 2, 0, 0, 0, 0, 0, 3},
                            {0, 0, 0, 2, 0, 1, 0, 0, 0},
                            {3, 0, 0, 1, 8, 0, 0, 9, 7},
                            {0, 0, 9, 0, 7, 0, 6, 0, 0},
                            {6, 5, 0, 0, 3, 2, 0, 0, 1},
                            {0, 0, 0, 4, 0, 9, 0, 0, 0},
                            {5, 0, 0, 0, 0, 0, 1, 0, 6},
                            {0, 0, 6, 0, 0, 0, 0, 0, 8}
        };

        boolean safe = isSafe(sudokuGrid, 0, 2, 4);
        System.out.println("Is the number safe? " + safe);
        if (solveSudoku(sudokuGrid)) {
            System.out.println("Sudoku solved:");
            printGrid(sudokuGrid);
        } else {
            System.out.println("No solution exists.");
        }
    }
}
