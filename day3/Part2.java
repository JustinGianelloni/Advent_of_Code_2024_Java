package aoc.day3;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        String file = "day3.txt";
        Path path = Path.of(ClassLoader.getSystemResource(file).toURI());
        String input = Files.readString(path);
        Integer answer = parseInput(input);
        System.out.println("The answer is: " + answer);
    }

    private static Integer parseInput(String input) {
        int answer = 0;
        boolean flag = true;
        String regex = "mul\\((\\d+),\\s*(\\d+)\\)|do\\(\\)|don't\\(\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            String match = matcher.group();
            if (match.equals("do()")) {
                flag = true;
            } else if (match.equals("don't()")) {
                flag = false;
            } else if (match.startsWith("mul") && flag) {
                answer += parseMatches(matcher.group(1), matcher.group(2));
            }
        }
        return answer;
    }

    private static Integer parseMatches(String firstMatch, String secondMatch) {
        int firstInt = Integer.parseInt(firstMatch);
        int secondInt = Integer.parseInt(secondMatch);
        return firstInt * secondInt;
    }
}