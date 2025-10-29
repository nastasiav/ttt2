package org.my.edy;

import java.util.Scanner;

public class ConsoleGame implements GameView{
    Field field;
    boolean isRunning;
    boolean isCrossTurn;

    private final Scanner scanner;

    public ConsoleGame() {
        this(new Scanner(System.in), null);
    }

    public ConsoleGame(FieldListener listener) {
        this(new Scanner(System.in), listener);
    }

    public ConsoleGame(Scanner scanner, FieldListener listener) {
        this.field = new Field(listener);
        this.isCrossTurn = false;
        this.isRunning = true;
        this.scanner = scanner;
    }


    @Override
    public void start() {

        do {
            changeTime();
            printField();
            getTime();
        } while (isRunning);
    }

    @Override
    public void stop() {
        printField();
        isRunning = false;
    }

    private void printField() {
        field.printField();
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
            isSuccess = validateXY(x, y);
            if (!isSuccess)  {
                this.stop();
                changeTime();
                break;
            }
            isSuccess = createSymbolInField(x, y);
        } while (!isSuccess);
    }

    @Override
    public String getSymbolicTime() {
        if (isCrossTurn) {
            return "крестики";
        }
        return "нолики";
    }

    private boolean createSymbolInField(int x, int y) {
        boolean isSuccess = field.isEmpty(x, y);
        if (!isSuccess) return false;
        isSuccess = field.createSymbol(x, y, isCrossTurn);
        return isSuccess;
    }

    private void changeTime() {
        isCrossTurn = !isCrossTurn;
    }

    private boolean validateXY(int x, int y) {
        return field.validateCoordinates(x, y);
    }
}
