package aoc.day5;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day5 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        String file = "day5.txt";
        Path path = Path.of(ClassLoader.getSystemResource(file).toURI());
        String[] lines = readInput(path);
        Integer part1 = parseInput(lines, false);
        Integer part2 = parseInput(lines, true);
        System.out.println("The answer for Part 1 is: " + part1 +"\nThe answer for Part 2 is: " + part2);
    }

    private static String[] readInput(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);
        return lines.toArray(new String[0]);
    }

    private static Integer parseInput(String[] lines, boolean repair) {
        List<String> lineList = new ArrayList<>(Arrays.asList(lines));
        int divider = -1;
        int answer = 0;
        for (int i = 0; i < lineList.size(); i++) {
            if (lineList.get(i).isEmpty()) {
                divider = i;
                break;
            }
        }
        List<String> rules = lineList.subList(0, divider);
        RuleBook ruleBook = new RuleBook(rules);
        List<String> pageList = lineList.subList(divider + 1, lineList.size());
        for (String line : pageList) {
            answer += ruleBook.validateManual(line, repair);
        }
        return answer;
    }
}