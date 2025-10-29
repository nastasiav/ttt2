package org.my.edy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FieldTest {

    @Test
    void testInitEmpty() {
        Field f = new Field();
        for (int r = 0; r < f.size(); r++) {
            for (int c = 0; c < f.size(); c++) {
                assertTrue(f.isEmpty(r, c));
            }
        }
    }

    @Test
    void testPlaceSymbolAndRejectOccupied() {
        Field f = new Field();
        assertTrue(f.createSymbol(1, 1, true)); // place X
        assertFalse(f.isEmpty(1, 1));
        assertFalse(f.createSymbol(1, 1, false)); // cannot place on occupied
    }

    @Test
    void testWinRow() {
        Field f = new Field();
        f.createSymbol(0, 0, true);
        f.createSymbol(0, 1, true);
        f.createSymbol(0, 2, true);
        assertTrue(f.checkWin());
    }

    @Test
    void testWinColumn() {
        Field f = new Field();
        f.createSymbol(0, 1, false);
        f.createSymbol(1, 1, false);
        f.createSymbol(2, 1, false);
        assertTrue(f.checkWin());
    }

    @Test
    void testWinMainDiagonal() {
        Field f = new Field();
        f.createSymbol(0, 0, true);
        f.createSymbol(1, 1, true);
        f.createSymbol(2, 2, true);
        assertTrue(f.checkWin());
    }

    @Test
    void testWinAntiDiagonal() {
        Field f = new Field();
        f.createSymbol(0, 2, false);
        f.createSymbol(1, 1, false);
        f.createSymbol(2, 0, false);
        assertTrue(f.checkWin());
    }

    @Test
    void testFullBoardNoWinner() {
        Field f = new Field();
        // X O X
        // X X O
        // O X O  -> full, no 3-in-row
        char[][] layout = {
                {Field.CROSS, Field.ZERO, Field.CROSS},
                {Field.CROSS, Field.CROSS, Field.ZERO},
                {Field.ZERO, Field.CROSS, Field.ZERO}
        };
        for (int r = 0; r < f.size(); r++) {
            for (int c = 0; c < f.size(); c++) {
                boolean isCross = layout[r][c] == Field.CROSS;
                f.createSymbol(r, c, isCross);
            }
        }
        assertTrue(f.checkFill());
        assertFalse(f.checkWin());
    }

    @Test
    void testInvalidCoordinatesThrow() {
        Field f = new Field();
        assertThrows(IndexOutOfBoundsException.class, () -> f.isEmpty(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> f.createSymbol(0, 5, true));
    }
}