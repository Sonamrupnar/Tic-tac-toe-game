package first;

import java.util.Scanner;
public class tictacgame
{
    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';
    private static Scanner scanner = new Scanner(System.in);

    public static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public static void printBoard() {
        System.out.println("\n-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    public static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkForWin() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') {
                return true;
            }
        }
        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[0][j] != ' ') {
                return true;
            }
        }
        // Check diagonals
        if ((board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') ||
                (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ')) {
            return true;
        }
        return false;
    }

    public static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public static void main(String[] args) {
        initializeBoard();
        System.out.println("Welcome to Tic-Tac-Toe..!!");

        System.out.println("Select an opponent:");
        System.out.println("1. Human vs. Human");
        System.out.println("2. Human vs. computer");
        int choice = scanner.nextInt();

        if (choice == 1) {
            playAgainstHuman();
        } else if (choice == 2) {
            playAgainstComputer();
        } else {
            System.out.println("Invalid choice. Exiting...");
        }
        scanner.close();
    }

    public static void playAgainstHuman() {
        while (true) {
            printBoard();
            System.out.println("Player " + currentPlayer + ", enter your move (row col): ");
            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;

            if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != ' ') {
                System.out.println("Invalid move.  Please Try again.");
                continue;
            }

            board[row][col] = currentPlayer;

            if (checkForWin()) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            }

            if (isBoardFull()) {
                printBoard();
                System.out.println("It's a draw!");
                break;
            }

            switchPlayer();
        }
    }

    public static void playAgainstComputer() {
        while (true) {
            if (currentPlayer == 'X') {
                printBoard();
                System.out.println("Player " + currentPlayer + ", enter your move (row col): ");
                int row = scanner.nextInt() - 1;
                int col = scanner.nextInt() - 1;

                if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != ' ') {
                    System.out.println("Invalid move.  Please Try again.");
                    continue;
                }

                board[row][col] = currentPlayer;
            } else {
                // Computer's move (random)
                int row, col;
                do {
                    row = (int) (Math.random() * 3);
                    col = (int) (Math.random() * 3);
                } while (board[row][col] != ' ');

                board[row][col] = currentPlayer;
                System.out.println("Computer placed its mark at row " + (row + 1) + ", column " + (col + 1));
            }

            if (checkForWin()) {
                printBoard();
                if (currentPlayer == 'X') {
                    System.out.println("Player " + currentPlayer + " wins!");
                } else {
                    System.out.println("Heyyy Computer wins!");
                }
                break;
            }

            if (isBoardFull()) {
                printBoard();
                System.out.println("It's a draw!");
                break;
            }

            switchPlayer();
        }
    }
}