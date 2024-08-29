import java.util.Scanner;

public class SudokoSolver {

    // Size of the grid
    private static final int GRID_SIZE = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] board = new int[GRID_SIZE][GRID_SIZE];

        // Taking input from the user
        System.out.println("Enter the 3x3 Sudoku puzzle (use 0 for empty cells):");
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                board[i][j] = scanner.nextInt();
            }
        }

        if (solveSudoku(board)) {
            System.out.println("Sudoku solved successfully:");
            printBoard(board);
        } else {
            System.out.println("Unsolvable Sudoku.");
        }

        scanner.close();
    }

    // Method to solve the Sudoku using backtracking
    private static boolean solveSudoku(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (board[row][col] == 0) { // Find an empty cell
                    for (int num = 1; num <= GRID_SIZE; num++) {
                        if (isValid(board, num, row, col)) {
                            board[row][col] = num;

                            if (solveSudoku(board)) {
                                return true;
                            } else {
                                board[row][col] = 0; // Backtrack
                            }
                        }
                    }
                    return false; // No valid number found, trigger backtracking
                }
            }
        }
        return true; // All cells are filled successfully
    }

    // Method to check if a number is valid in a particular cell
    private static boolean isValid(int[][] board, int number, int row, int col) {
        // Check the row
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == number) {
                return false;
            }
        }

        // Check the column
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][col] == number) {
                return false;
            }
        }

        return true;
    }

    // Method to print the Sudoku board
    private static void printBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }
}
