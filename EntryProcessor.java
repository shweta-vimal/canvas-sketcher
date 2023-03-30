package org.canvas.sample;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;

/**
 * Entry Processor to draw shapes based on the input commands
 */
@Slf4j
public class EntryProcessor {

    private char[][] canvas;

    public void process(Command cmd, int[] entries) {
        switch (cmd) {
            case CANVAS -> drawCanvas(entries[0], entries[1]);
            case RECTANGLE -> drawRectangle(entries[0], entries[1], entries[2], entries[3]);
            case LINE -> drawLine(entries[0], entries[1], entries[2], entries[3], false);
        }
    }

    private void drawCanvas(int w, int h) {
        canvas = new char[h + 2][w + 2];
        for (int i = 0; i < canvas.length; i++) {
            for (int j = 0; j < canvas[i].length; j++) {
                if (i == 0 || i == canvas.length - 1) {
                    canvas[i][j] = '-';
                } else if (j == 0 || j == canvas[i].length - 1) {
                    canvas[i][j] = '|';
                } else {
                    canvas[i][j] = ' ';
                }
            }
        }
        printCanvas();
    }

    private void drawLine(int x1, int y1, int x2, int y2, boolean partial) {
        if (isOutsideCanvas(x1, y1, x2, y2)) return;

        if (y1 == y2) { // Horizontal Line
            IntStream.rangeClosed(x1, x2).forEach(i -> canvas[y1][i] = 'x');
        } else if (x1 == x2) { // Vertical Line
            IntStream.rangeClosed(y1, y2).forEach(i -> canvas[i][x1] = 'x');
        } else {
            log.error("Only horizontal and vertical lines can be drawn.");
            return;
        }

        if (!partial) {
            printCanvas();
        }
    }

    private void drawRectangle(int x1, int y1, int x2, int y2) {
        if (isOutsideCanvas(x1, y1, x2, y2)) return;

        drawLine(x1, y1, x2, y1, true); // top
        drawLine(x1, y1, x1, y2, true); // left
        drawLine(x1, y2, x2, y2, true); // bottom
        drawLine(x2, y1, x2, y2, true); // right
        printCanvas();
    }

    private boolean isOutsideCanvas(int x1, int y1, int x2, int y2) {
        if (canvas == null) {
            log.error("Please draw canvas first.");
            return true;
        } else if (x1 > canvas[0].length - 2 || x2 > canvas[0].length - 2
                || y1 > canvas.length - 2 || y2 > canvas.length - 2) {
            log.error("Line exceeding canvas size. Please try again.");
            return true;
        }
        return false;
    }

    private void printCanvas() {
        for (char[] row : canvas) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
}
