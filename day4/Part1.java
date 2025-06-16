package aoc.day4;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Part1 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        String file = "day4.txt";
        Path path = Path.of(ClassLoader.getSystemResource(file).toURI());
        String[] lines = readInput(path);
        Integer answer = parseInput(lines);
        System.out.println("The answer is: " + answer);
    }

    private static String[] readInput(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);
        return lines.toArray(new String[0]);
    }

    private static Integer parseInput(String[] input) {
        int answer = 0;
        Table table = new Table(input);
        do {
            if (table.isWordStart('X')) {
                answer += table.checkXMAS();
            }
        } while (table.next());
        return answer;
    }
}