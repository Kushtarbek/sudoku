import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Attempt2 {
    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings("DMI_RANDOM_USED_ONLY_ONCE")
    public static void main(String[] args) {
        int [][] board = generateRandomBoard();
        printBoard(board);

        Scanner scanner = new Scanner(System.in);

        //input numbers and validate
        while(true){
            System.out.println("Enter the row (0-8) and column (0-8) of the 3x3 grid, separated by spaces (or -1 to exit):");
            int row = scanner.nextInt();
            if(row == -1){
                break;
            }

            int col = scanner.nextInt();

            if(row < 0 || row > 6 || col < 0 || col > 6){
                System.out.println("Invalid input. Please try again.");
                continue;
            }

            System.out.println("Enter the number (1-9) to insert in the selected 3x3 grid:");
            int num = scanner.nextInt();

            if(num < 1 || num > 9){
                System.out.println("Invalid input. Please try again.");
                continue;
            }

            int subRow = row % 3;
            int subCol = col % 3;

            if(!isUniqueInSubgrid(board, row - subRow, col - subCol, num) ||
                !isUniqueInRowAndColumn(board, row, col, num) ||
                !isUniqueInSubgridRowAndColumn(board, row - subRow, col - subCol, num)) {
                System.out.println(" Conflict in subgrid, row, column, or subgrid's row/column. Please try again.");
                continue;
            }

            board[row][col] = num;
            printBoard(board);
        }

        System.out.println("Exiting the program.");
    }

    //Check if a number is unique within its subgrid
    public static boolean isUniqueInSubgrid( int [][] board, int  startRow, int startCol, int num ){
        for( int row = 0; row < 3; row++ ){
            for( int col =0; col < 3; col++){
                if(board[startRow + row][startCol + startCol] == num){
                    return false;
                }
            }
        }
        return true;
    }

    // Check if a number is unique within its row and column
    public static boolean isUniqueInSubgridRowAndColumn( int [][] board, int startRow, int startCol, int num ){
        for( int row = 0; row < 3; row++ ){
            for( int col =0; col < 3; col++){
                if((board[startRow + row][startCol + col] != 0) && (board[startRow + row][startCol + col] == num)){
                    return false;
                }
            }
        }
        return true;
    }


    //Generate a random 9x9 board
    @SuppressFBWarnings("DMI_RANDOM_USED_ONLY_ONCE")
    public static int [][] generateRandomBoard(){
        int [][] board = new int[9][9];
        Random random = new Random();

        for( int row = 0; row < 9; row++){
            int[] availableColumns = {0, 1, 2, 3, 4, 5, 6, 7, 8};
            for(int i = 0; i < 4; i++){
                    int randomIndex = random.nextInt(availableColumns.length);
                    int col = availableColumns[randomIndex];
                    availableColumns[randomIndex] = availableColumns[availableColumns.length -1];
                    availableColumns = Arrays.copyOf(availableColumns, availableColumns.length-1);
                    int num = generateUniqueNumber(board, row, col);
                    board[row][col] = num;
            }
        }
        return board;
    }


    //print the board
    public static void printBoard( int[][] board ) {
        for( int row = 0; row < 9; row++ ){
            if(row % 3 == 0 && row != 0) {
                System.out.println("-----------------------");
            }
            for( int col =0; col < 9; col++){
                if(col % 3 == 0 && col !=0) {
                    System.out.print("| ");
                }
                System.out.print(board[row][col]+ " ");
            }
            System.out.println();
        }
    }


    //Generate a unique number for a given cell, ensuring it is not present in the same row and column
    @SuppressFBWarnings("DMI_RANDOM_USED_ONLY_ONCE")
    public static int generateUniqueNumber(int[][] board, int row, int col){
        Random random = new Random();
        int num;
        do{
            num = random.nextInt(9)+1;
        }while( !isUniqueInRowAndColumn(board, row, col, num));
        return num;
    }

    //Check if a number is unique within its row and column
    public static boolean isUniqueInRowAndColumn(int [][] board, int row, int col, int num){
        for(int i=0; i < 9; i++){
            if(board[row][i] == num || board[i][col] == num){
                return false;
            }
        }
        return true;
    }
}
