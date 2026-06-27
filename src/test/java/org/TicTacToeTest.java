package org;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TicTacToeTest {

    private Board board;
    private Player playerX;
    private Player playerO;

    @BeforeEach
    void setUp() {
        board = new Board();
        playerX = new Player('X');
        playerO = new Player('O');
    }

    @Test
    void testPlayerMarkerX() {
        assertEquals('X', playerX.getMarker());
    }

    @Test
    void testPlayerMarkerO() {
        assertEquals('O', playerO.getMarker());
    }

    @Test
    void testBoardInitiallyEmpty() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertTrue(board.isCellEmpty(i, j), "Cell (" + i + "," + j + ") should be empty");
            }
        }
    }

    @Test
    void testCellNotEmptyAfterPlace() {
        board.place(0, 0, 'X');
        assertFalse(board.isCellEmpty(0, 0));
    }

    @Test
    void testPlaceMarkerX() {
        board.place(1, 1, 'X');
        assertFalse(board.isCellEmpty(1, 1));
    }

    @Test
    void testPlaceMarkerO() {
        board.place(2, 2, 'O');
        assertFalse(board.isCellEmpty(2, 2));
    }

    @Test
    void testBoardNotFullInitially() {
        assertFalse(board.isFull());
    }

    @Test
    void testBoardFullAfterAllCellsFilled() {
        char[] markers = {'X', 'O', 'X', 'O', 'X', 'O', 'O', 'X', 'O'};
        int k = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board.place(i, j, markers[k++]);
            }
        }

        assertTrue(board.isFull());
    }

    @Test
    void testBoardNotFullWithOneEmptyCell() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!(i == 2 && j == 2)) {
                    board.place(i, j, 'X');
                }
            }
        }

        assertFalse(board.isFull());
    }

    @Test
    void testClearResetsBoard() {
        board.place(0, 0, 'X');
        board.place(1, 1, 'O');

        board.clear();

        assertTrue(board.isCellEmpty(0, 0));
        assertTrue(board.isCellEmpty(1, 1));
    }

    @Test
    void testWinnerTopRow() {
        TicTacToe game = new TicTacToe();
        game.getBoard().place(0, 0, 'X');
        game.getBoard().place(0, 1, 'X');
        game.getBoard().place(0, 2, 'X');
        game.setCurrentPlayer(game.getPlayer1());

        assertTrue(game.hasWinner());
    }

    @Test
    void testWinnerMiddleRow() {
        TicTacToe game = new TicTacToe();
        game.getBoard().place(1, 0, 'O');
        game.getBoard().place(1, 1, 'O');
        game.getBoard().place(1, 2, 'O');
        game.setCurrentPlayer(game.getPlayer2());

        assertTrue(game.hasWinner());
    }

    @Test
    void testWinnerBottomRow() {
        TicTacToe game = new TicTacToe();
        game.getBoard().place(2, 0, 'X');
        game.getBoard().place(2, 1, 'X');
        game.getBoard().place(2, 2, 'X');
        game.setCurrentPlayer(game.getPlayer1());

        assertTrue(game.hasWinner());
    }

    @Test
    void testWinnerLeftColumn() {
        TicTacToe game = new TicTacToe();
        game.getBoard().place(0, 0, 'X');
        game.getBoard().place(1, 0, 'X');
        game.getBoard().place(2, 0, 'X');
        game.setCurrentPlayer(game.getPlayer1());

        assertTrue(game.hasWinner());
    }

    @Test
    void testWinnerMiddleColumn() {
        TicTacToe game = new TicTacToe();
        game.getBoard().place(0, 1, 'O');
        game.getBoard().place(1, 1, 'O');
        game.getBoard().place(2, 1, 'O');
        game.setCurrentPlayer(game.getPlayer2());

        assertTrue(game.hasWinner());
    }

    @Test
    void testWinnerRightColumn() {
        TicTacToe game = new TicTacToe();
        game.getBoard().place(0, 2, 'X');
        game.getBoard().place(1, 2, 'X');
        game.getBoard().place(2, 2, 'X');
        game.setCurrentPlayer(game.getPlayer1());

        assertTrue(game.hasWinner());
    }

    @Test
    void testWinnerDiagonalTopLeftToBottomRight() {
        TicTacToe game = new TicTacToe();
        game.getBoard().place(0, 0, 'X');
        game.getBoard().place(1, 1, 'X');
        game.getBoard().place(2, 2, 'X');
        game.setCurrentPlayer(game.getPlayer1());

        assertTrue(game.hasWinner());
    }

    @Test
    void testWinnerDiagonalTopRightToBottomLeft() {
        TicTacToe game = new TicTacToe();
        game.getBoard().place(0, 2, 'O');
        game.getBoard().place(1, 1, 'O');
        game.getBoard().place(2, 0, 'O');
        game.setCurrentPlayer(game.getPlayer2());

        assertTrue(game.hasWinner());
    }

    @Test
    void testNoWinnerOnEmptyBoard() {
        TicTacToe game = new TicTacToe();
        game.setCurrentPlayer(game.getPlayer1());

        assertFalse(game.hasWinner());
    }

    @Test
    void testNoWinnerOnDraw() {
        TicTacToe game = new TicTacToe();

        game.getBoard().place(0, 0, 'X');
        game.getBoard().place(0, 1, 'O');
        game.getBoard().place(0, 2, 'X');
        game.getBoard().place(1, 0, 'X');
        game.getBoard().place(1, 1, 'X');
        game.getBoard().place(1, 2, 'O');
        game.getBoard().place(2, 0, 'O');
        game.getBoard().place(2, 1, 'X');
        game.getBoard().place(2, 2, 'O');
        game.setCurrentPlayer(game.getPlayer1());

        assertFalse(game.hasWinner());
    }

    @Test
    void testSwitchFromPlayer1ToPlayer2() {
        TicTacToe game = new TicTacToe();
        game.setCurrentPlayer(game.getPlayer1());

        game.switchCurrentPlayer();

        assertEquals(game.getPlayer2(), game.getCurrentPlayer());
    }

    @Test
    void testSwitchFromPlayer2ToPlayer1() {
        TicTacToe game = new TicTacToe();
        game.setCurrentPlayer(game.getPlayer2());

        game.switchCurrentPlayer();

        assertEquals(game.getPlayer1(), game.getCurrentPlayer());
    }
}
