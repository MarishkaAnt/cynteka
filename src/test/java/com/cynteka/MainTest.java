package com.cynteka;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    void whenRightPath_thenRightOutput() {
        //given
        final String outputPath = "src/test/resources/output.txt";
        final Map<String, String> rightPaths = Map.of(
                "src/test/resources/input1.txt", "src/test/resources/expected1.txt",
                "src/test/resources/input2.txt", "src/test/resources/expected2.txt",
                "src/test/resources/input3.txt", "src/test/resources/expected3.txt"
        );
        //when
        rightPaths.forEach((key, value) -> {
            List<String> result = null;
            List<String> expected = null;
            try {
                Main.main(new String[]{key, outputPath});
                result = TxtFileReader.readNotBlankLinesFromFile(outputPath);
                expected = TxtFileReader.readNotBlankLinesFromFile(value);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //then
            assertEquals(expected, result);
        });
    }

    @Test
    void whenEmptyArgsToMainMethod_thenNothingThrown() {
        assertDoesNotThrow(() -> Main.main(new String[0]));
    }
}
