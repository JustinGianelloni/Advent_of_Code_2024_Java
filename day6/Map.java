package aoc.day6;

public class Map {

    private final char[][] map;
    private Point guard;
    private final int width;
    private final int height;
    private char direction = 'N';
    private int steps = 1;
    private int loopCount = 0;

    public Map(String[] lines) {
        height = lines.length;
        width = lines[0].length();
        map = new char[height][width];
        for (int i = 0; i < height; i++) {
            map[i] = lines[i].toCharArray();
        }
        initGuard();
    }

    private void initGuard() {
        for (int y = 0; y < height-1; y++) {
            for (int x = 0; x < width-1; x++) {
                if (map[y][x] == '^') {
                    guard = new Point(x, y);
                    map[y][x] = 'X';
                    return;
                }
            }
        }
    }

    public Boolean hasNext() {
        return switch (direction) {
            case 'N' -> guard.y > 0;
            case 'S' -> guard.y < height-1;
            case 'E' -> guard.x < width-1;
            case 'W' -> guard.x > 0;
            default -> null;
        };
    }

    public Boolean isSpaceAhead() {
        return switch (direction) {
            case 'N' -> map[guard.y-1][guard.x] != '#';
            case 'S' -> map[guard.y+1][guard.x] != '#';
            case 'E' -> map[guard.y][guard.x+1] != '#';
            case 'W' -> map[guard.y][guard.x-1] != '#';
            default -> null;
        };
    }

    public void move() {
        switch (direction) {
            case 'N' -> guard.y--;
            case 'S' -> guard.y++;
            case 'E' -> guard.x++;
            case 'W' -> guard.x--;
        };
        if (map[guard.y][guard.x] == '.') {
            map[guard.y][guard.x] = 'X';
            steps++;
            loopCount = 0;
        } else {
            loopCount++;
        }
    }

    public void turn() {
        switch (direction) {
            case 'N' -> direction = 'E';
            case 'S' -> direction = 'W';
            case 'E' -> direction = 'S';
            case 'W' -> direction = 'N';
        };
    }

    public Integer getSteps() {
        return steps;
    }

    public void addObstacle(Point obstacle) {
        map[obstacle.y][obstacle.x] = '#';
    }

    public Boolean isLooping() {
        return loopCount > 1000;
    }

    public static class Point {

        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
