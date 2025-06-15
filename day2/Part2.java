package aoc.day2;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part2 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        String file = "day2.txt";
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
        int answer = 0;
        for (String line : lines) {
            String[] chars = line.split(" ");
            Integer[] numbers = new Integer[chars.length];
            for (int i = 0; i < chars.length; i++) {
                numbers[i] = Integer.parseInt(chars[i]);
            }
            if (isArraySafe(numbers)) {
                answer += 1;
            }
        }
        return answer;
    }

    private static Boolean isArraySafe(Integer[] numbers) {
        if (isArrayMonotonic(numbers) && isArrayClose(numbers)) {
            return true;
        }
        ArrayList<Integer> numberList = new ArrayList<>(Arrays.asList(numbers));
        for (int i = 0; i < numberList.size(); i++) {
            ArrayList<Integer> variant = new ArrayList<>(numberList);
            variant.remove(i);
            Integer[] newNumbers = variant.toArray(new Integer[0]);
            if (isArrayMonotonic(newNumbers) && isArrayClose(newNumbers)) {
                return true;
            }
        }
        return false;
    }

    private static Boolean isArrayMonotonic(Integer[] numbers) {
        boolean ascending = true;
        boolean descending = true;
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > numbers[i-1]) {
                descending = false;
            }
            if (numbers[i] < numbers[i-1]) {
                ascending = false;
            }
        }
        return ascending || descending;
    }

    private static Boolean isArrayClose(Integer[] numbers) {
        for (int i = 1; i < numbers.length; i++) {
            if (Math.abs(numbers[i] - numbers[i-1]) < 1) {
                return false;
            }
            if (Math.abs(numbers[i] - numbers[i-1]) > 3) {
                return false;
            }
        }
        return true;
    }
}