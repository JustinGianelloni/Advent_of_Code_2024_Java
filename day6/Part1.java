package aoc.day6;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Part1 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        String file = "day6.txt";
        Path path = Path.of(ClassLoader.getSystemResource(file).toURI());
        String[] lines = readInput(path);
        Integer answer = parseInput(lines);
        System.out.println("The answer is: " + answer);
    }

    private static String[] readInput(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);
        return lines.toArray(new String[0]);
    }

    private static Integer parseInput(String[] lines) {
        Map map = new Map(lines);
        while (map.hasNext()) {
            if (map.isSpaceAhead()) {
                map.move();
            } else {
                map.turn();
            }
        }
        return map.getSteps();
    }
}