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
    public static int [][] generateRandomBoard(){
        int [][] board = new int[9][9];
        Random random = new Random();

        for( int row = 0; row < 9; row++){
            int[] availableColumns = {0, 1, 2, 3, 4, 5, 6, 7, 8};
            for(int i = 0; i<4; i++){
                int randomIndex = random.nextInt(availableColumns.length);
                int col = availableColumns[randomIndex];

                availableColumns[randomIndex] = availableColumns[availableColumns.length -1];
                availableColumns = Arrays.copyOf(availableColumns, availableColumns.length-1);
                board[row][col] = random.nextInt(9) + 1;
            }
        }
        return board;
    }
}
