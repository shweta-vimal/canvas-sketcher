package org.canvas.sample;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;
import java.util.stream.Stream;

@Slf4j
public class Solution {
    public static void main(String[] args) {
        log.info("Please enter following commands to draw:\n" +
                "Canvas: C w h\n" +
                "Rectangle: R x1 y1 x2 y2\n" +
                "Line: L x1 y1 x2 y2\n" +
                "Quit: Q");

        EntryProcessor entryProcessor = new EntryProcessor();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.isBlank() || Command.QUIT.getCmd() == input.charAt(0)) {
                break;
            }
            log.info("Entered command: {}", input);

            String[] s = input.replaceAll("\\s+$", "").split(" ");

            // Validate user input
            Command.fromText(s[0].charAt(0))
                    .filter(e -> e.getArgsLength() == (s.length - 1))
                    .ifPresentOrElse(cmd -> entryProcessor.process(cmd, Stream.of(s).skip(1).mapToInt(Integer::parseInt).toArray()),
                            () -> log.error("Invalid input. Please try again"));
        }
    }
}
