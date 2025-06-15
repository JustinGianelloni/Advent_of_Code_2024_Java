package aoc.day1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Part2 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        String file = "day1.txt";
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
        ArrayList<Integer> left = new ArrayList<>(lines.length);
        ArrayList<Integer> right = new ArrayList<>(lines.length);
        for (String line : lines) {
            String[] parts = line.split(" {3}");
            left.add(Integer.valueOf(parts[0]));
            right.add(Integer.valueOf(parts[1]));
        }
        return compareLists(left.toArray(new Integer[0]), right.toArray(new Integer[0]));
    }

    private static Integer compareLists(Integer[] left, Integer[] right) {
        int answer = 0;
        int count;
        for (int key : left) {
            count = 0;
            for (int value : right) {
                if (key == value) {
                    count++;
                }
            }
            answer += key * count;
        }
        return answer;
    }
}
