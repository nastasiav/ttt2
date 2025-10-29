package org.my.edy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ConsoleGameTest {

    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outCaptor = new ByteArrayOutputStream();

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testCrossesWin() {
        // Moves:
        // X: (0,0)
        // O: (1,0)
        // X: (0,1)
        // O: (1,1)
        // X: (0,2) -> crosses win
        String input = String.join("\n",
                "0", "0",
                "1", "0",
                "0", "1",
                "1", "1",
                "0", "2",
                "-1", "-2"
        );
        Scanner scanner = new Scanner(input);
        System.setOut(new PrintStream(outCaptor));

        ConsoleGame game = new ConsoleGame(scanner, null);
        game.start();

        String output = outCaptor.toString();
        assertTrue(game.field.isWin(), "Expected crosses to win; output:\n" + output);
        assertTrue(game.isCrossTurn, "Expected crosses to win; output:\n" + output);
    }

    @Test
    void testDrawGame() {
        // 1 X (0,0)
        // 2 O (0,1)
        // 3 X (0,2)
        // 4 O (1,2)
        // 5 X (1,0)
        // 6 O (2,0)
        // 7 X (1,1)
        // 8 O (2,2)
        // 9 X (2,1)
        String input = String.join(" ",
                "0", "0",
                "0", "1",
                "0", "2",
                "1", "2",
                "1", "0",
                "2", "0",
                "1", "1",
                "2", "2",
                "2", "1",
                "-1", "-2"
        );

        Scanner scanner = new Scanner(input);
        System.setOut(new PrintStream(outCaptor));

        ConsoleGame consoleGame = new ConsoleGame(scanner, null);
        consoleGame.start();

        String output = outCaptor.toString();
        assertTrue(consoleGame.field.isFill(), "Expected a draw; output:\n" + output);
    }
}