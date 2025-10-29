package org.my.edy;

import java.util.Scanner;

public class Game {
    Field field;
    boolean isWin;
    boolean isCrossTurn;
    boolean isDraw;

    private final Scanner scanner;

    public Game() {
        this(new Scanner(System.in));
    }

    public Game(Scanner scanner) {
        this.field = new Field();
        this.isCrossTurn = false;
        this.isWin = false;
        this.isDraw = false;
        this.scanner = scanner;
    }

    public void start() {

        do {
            changeTime();
            field.printField();
            getTime();
            isDraw = checkDraw();
            isWin = checkWin();
        } while (!isWin && !isDraw);

        printResults();

    }

    private void getTime() {
        boolean isSuccess;
        do {
            System.out.println("Сделай ход");
            System.out.println("Ходят " + getSymbolicTime());
            System.out.println("Строка: ");
            int x = scanner.nextInt();
            System.out.println("Столбец: ");
            int y = scanner.nextInt();
            isSuccess = createSymbolInField(x, y);
        } while (!isSuccess);
    }

    private void printResults() {
        if (isDraw)
            System.out.println("ничья!");
        if (isWin)
            System.out.println("победили " + getSymbolicTime());
    }

    private String getSymbolicTime() {
        if (isCrossTurn) {
            return "крестики";
        }
        return "нолики";
    }

    private boolean createSymbolInField(int x, int y) {
        try {
            boolean isSuccess = field.isEmpty(x, y);
            if (!isSuccess) return false;
            isSuccess = field.createSymbol(x, y, isCrossTurn);
            return isSuccess;
        }
        catch (Exception e) {
            return false;
        }
    }

    private boolean checkDraw() {
        return field.checkFill();
    }

    private boolean checkWin() {
        return field.checkWin();
    }

    private void changeTime() {
        isCrossTurn = !isCrossTurn;
    }
}
