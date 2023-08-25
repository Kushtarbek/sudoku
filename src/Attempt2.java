import java.util.Arrays;
import java.util.Random;

public class Attempt2 {
    public static void main(String[] args) {
        int [][] board = new int[9][9];
        Random random = new Random();

        // Initializing the board with some values (for example, all zeros)
        for(int row = 0; row<9; row++){
            int[] availableColumns = {0, 1, 2, 3, 4, 5, 6, 7, 8};
            for(int i = 0; i<4; i++){
                 int randomIndex = random.nextInt(availableColumns.length);
                 int col = availableColumns[randomIndex];

                 availableColumns[randomIndex] = availableColumns[availableColumns.length -1];
                 availableColumns = Arrays.copyOf(availableColumns, availableColumns.length-1);

                 board[row][col] = random.nextInt(9) + 1;
            }
        }
        //Printing the board
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }
}
