package org;

import java.util.Scanner;

public class TicTacToe {
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private final Board board;
    private final Scanner scanner;

    public TicTacToe() {
        player1 = new Player('X');
        player2 = new Player('O');
        currentPlayer = player1;
        board = new Board();
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("====================");
        System.out.println(" Tic-Tac-Toe Game");
        System.out.println("====================");
        System.out.println("Player 1: X | Player 2: O");
        System.out.println("Enter moves as: row column (for example: 1 3)");

        boolean playAgain = true;

        while (playAgain) {
            board.clear();
            currentPlayer = player1;
            playRound();

            System.out.print("Play again? (y/n): ");
            String answer = scanner.nextLine().trim().toLowerCase();
            playAgain = answer.equals("y") || answer.equals("yes");
        }

        System.out.println("Thanks for playing.");
        scanner.close();
    }

    private void playRound() {
        while (true) {
            board.print();
            System.out.println("Player " + currentPlayer.getMarker() + "'s turn.");

            int[] move = getValidMove();
            board.place(move[0], move[1], currentPlayer.getMarker());

            if (hasWinner()) {
                board.print();
                System.out.println("Player " + currentPlayer.getMarker() + " wins!");
                break;
            }

            if (board.isFull()) {
                board.print();
                System.out.println("It's a draw!");
                break;
            }

            switchCurrentPlayer();
        }
    }

    private int[] getValidMove() {
        while (true) {
            System.out.print("Enter row and column (1-3): ");
            String input = scanner.nextLine().trim();

            String[] parts = input.split("\\s+");
            if (parts.length != 2) {
                System.out.println("Please enter two numbers separated by a space.");
                continue;
            }

            try {
                int row = Integer.parseInt(parts[0]) - 1;
                int col = Integer.parseInt(parts[1]) - 1;

                if (row < 0 || row > 2 || col < 0 || col > 2) {
                    System.out.println("Numbers must be between 1 and 3.");
                    continue;
                }

                if (!board.isCellEmpty(row, col)) {
                    System.out.println("That cell is already taken. Choose another.");
                    continue;
                }

                return new int[]{row, col};
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numbers only.");
            }
        }
    }

    public void switchCurrentPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Board getBoard() {
        return board;
    }

    public boolean hasWinner() {
        char marker = currentPlayer.getMarker();

        for (int i = 0; i < 3; i++) {
            if (board.getCell(i, 0) == marker && board.getCell(i, 1) == marker && board.getCell(i, 2) == marker) {
                return true;
            }
            if (board.getCell(0, i) == marker && board.getCell(1, i) == marker && board.getCell(2, i) == marker) {
                return true;
            }
        }

        if (board.getCell(0, 0) == marker && board.getCell(1, 1) == marker && board.getCell(2, 2) == marker) {
            return true;
        }

        return board.getCell(0, 2) == marker && board.getCell(1, 1) == marker && board.getCell(2, 0) == marker;
    }
}
