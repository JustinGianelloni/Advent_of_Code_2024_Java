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
        Integer part1 = part1(lines);
        Integer part2 = part2(lines);
        System.out.println("The answer to part 1 is: " + part1 + "\nThe answer to part 2 is: " + part2);
    }

    private static String[] readInput(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);
        return lines.toArray(new String[0]);
    }

    private static Integer part1(String[] lines) {
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

    private static Integer part2(String[] lines) {
        Map.Point obstacle = new Map.Point(0, 0);
        int y = lines.length;
        int x = lines[0].length();
        int loops = 0;
        boolean skip;
        Map map;
        do {
            map = new Map(lines);
            map.addObstacle(obstacle);
            while(map.hasNext()) {
                if (map.isSpaceAhead()) {
                    map.move();
                } else {
                    map.turn();
                }
                if (map.isLooping()) {
                    loops++;
                    break;
                }
            }
            if (obstacle.x == x - 1) {
                obstacle.x = 0;
                obstacle.y++;
            } else {
                obstacle.x++;
            }
        } while(obstacle.y != y-1 || obstacle.x != x-1);
        return loops;
    }
}