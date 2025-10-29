package org.my.edy;

public class Field {
    private final char CROSS = 'X';
    private final char ZERO = '0';
    private final char EMPTY = '.';
    private final int SIZE = 3;

    char[][] field = new char[SIZE][SIZE];

    public Field() {
        initField();
    }

    private void initField() {
        for (int i = 0; i < SIZE; i ++) {
            for (int j = 0; j < SIZE; j ++) {
                this.field[i][j] = EMPTY;
            }
        }
    }

    public void printField() {
        System.out.print(" ");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(" | " + i);
        }
        System.out.println(" |");
        for (int i = 0; i < SIZE; i ++) {
            System.out.println("---------------");
            System.out.print(i + " ");
            for (int j = 0; j < SIZE; j ++) {
                System.out.print("|" + " " + this.field[i][j] + " ");
            }
            System.out.println("|");
        }
    }

    public boolean checkEmpty(int x, int y) {
        return field[x][y] == EMPTY;
    }

    public boolean createSymbol(int x, int y, boolean isCross) {
        char symbol = getSymbol(isCross);
        this.field[x][y] = symbol;
        return true;
    }

    private char getSymbol(boolean isCross) {
        if (isCross)
            return CROSS;
        return ZERO;
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

    public boolean isEmpty(int r, int c) {
        return false;
    }
}
