package org.my.edy;

import java.util.Arrays;

public class Field {
    public static final char CROSS = 'X';
    public static final char ZERO = '0';
    public static final char EMPTY = '.';
    private static final int SIZE = 3;

    char[][] field;

    public Field() {
        this(SIZE);
    }

    public Field(int size) {
        if (size < 1) throw new IllegalArgumentException("size must be >= 1");
        this.field = new char[size][size];
        initField();
    }


    private void initField() {
        for (char[] row : field) {
            Arrays.fill(row, EMPTY);
        }
    }

    public boolean isEmpty(int x, int y) {
        validateCoordinates(x, y);
        return field[x][y] == EMPTY;
    }

    public void printField() {
        System.out.print(this);
    }

    public boolean createSymbol(int x, int y, boolean isCross) {
        if (field[x][y] != EMPTY)
            return false;
        char symbol = getSymbol(isCross);
        this.field[x][y] = symbol;
        return true;
    }
    public boolean checkFill() {
        int countEmtpy = 0;
        for (char[] i : this.field) {
            for (char j : i) {
                if (j == EMPTY)
                    countEmtpy++;
            }
        }
        return countEmtpy == 0;
    }

    public boolean checkWin() {

        for(int i = 0; i < field.length; i++) {
            if (field[0][i] != EMPTY && field[0][i] == field[1][i] && field[0][i] == field[2][i])
                return true;
            if (field[i][0] != EMPTY && field[i][0] == field[i][1] && field[i][1] == field[i][2])
                return true;
        }

        if (field[0][0] != EMPTY && field[0][0] == field[1][1] && field[0][0] == field[2][2])
            return true;
        if (field[0][2] != EMPTY && field[0][2] == field[1][1] && field[1][1] == field[2][0])
            return true;

        return false;
    }

    public int size() {
        return SIZE;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("  ");
        for (int i = 0; i < SIZE; i++) {
            sb.append(" | ").append(i);
        }
        sb.append(" |\n");
        for (int i = 0; i < SIZE; i ++) {
            sb.append("---------------\n");
            sb.append(i).append(" ");
            for (int j = 0; j < SIZE; j ++) {
                sb.append("| ").append(field[i][j]).append(" ");
            }
            sb.append("|\n");
        }
        return sb.toString();
    }
    private void validateCoordinates(int row, int col) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            throw new IndexOutOfBoundsException("Coordinates out of bounds: (" + row + "," + col + ")");
        }
    }

    private char getSymbol(boolean isCross) {
        if (isCross)
            return CROSS;
        return ZERO;
    }

}
