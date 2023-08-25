import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.Arrays;
import java.util.Random;

public class Attempt2 {
    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings("DMI_RANDOM_USED_ONLY_ONCE")
    public static void main(String[] args) {
        int [][] board = generateRandomBoard();

        //Printing the board
        for (int row = 0; row < 9; row++) {
            if(row % 3 == 0 && row != 0){
                System.out.println("---------------------");
            }
            for (int col = 0; col < 9; col++) {
                if(col % 3 == 0 && col != 0){
                    System.out.print("| ");
                }
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }



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
