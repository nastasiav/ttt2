package org.my.edy;

import java.util.Scanner;

public class Game {
    Field field;
    boolean isWin;
    boolean isCross;
    boolean isDraw;

    private final Scanner scanner = new Scanner(System.in);

    public Game() {
        this.field = new Field();
        isCross = false;
        isWin = false;
        isDraw = false;
    }

    public void start() {

        do {
            changeTime();
            this.getField().printField();
            System.out.println("Сделай ход");
            System.out.println("Ходят " + getSymbolicTime());
            System.out.println("Строка: ");
            int x = scanner.nextInt();
            System.out.println("Столбец: ");
            int y = scanner.nextInt();
            boolean isSuccess = createSymbolInField(x, y);
            if (!isSuccess)
                continue;
            isDraw = checkDraw();
            isWin = checkWin();
        } while (!isWin && !isDraw);

        printResults();

    }

    private void printResults() {
        if (isDraw)
            System.out.println("ничья!");
        if (isWin)
            System.out.println("победили " + getSymbolicTime());
    }

    public Field getField() {
        return field;
    }

    private String getSymbolicTime() {
        if (this.isCross) {
            return "крестики";
        }
        return "нолики";
    }

    private boolean createSymbolInField(int x, int y) {
        boolean isSuccess = this.field.checkEmpty(x, y);
        if (!isSuccess) return false;
        isSuccess = this.field.createSymbol(x, y, isCross);
        return isSuccess;
    }

    private boolean checkDraw() {
        return this.field.checkFill();
    }

    private boolean checkWin() {
        return this.field.checkWin();
    }

    private void changeTime() {
        this.isCross = !this.isCross;
    }
}
