package org.canvas.sample;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Represents the supported Input Command type values
 */
@RequiredArgsConstructor
@Getter
public enum Command {
    CANVAS('C', 2),
    LINE('L', 4),
    RECTANGLE('R', 4),
    QUIT('Q', 0);

    private final char cmd;
    private final int argsLength;

    public static Optional<Command> fromText(char text) {
        return Stream.of(values())
                .filter(e -> e.getCmd() == text)
                .findFirst();
    }
}
