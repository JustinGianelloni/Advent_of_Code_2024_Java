package aoc.day6;

public class OldMap {

    private final String[] map;
    private final int width;
    private final int height;
    private int xPointer = 0;
    private int yPointer = 0;
    private char direction = 'N';
    private int steps = 1;
    private int xObstacle = 0;
    private int yObstacle = 0;

    public OldMap(String[] map) {
        this.map = map;
        width = map[0].length();
        height = map.length;
        setStartPointer();
    }

    private void setStartPointer() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (map[y].charAt(x) == '^') {
                    xPointer = x;
                    yPointer = y;
                    map[y] = map[y].replace('^', 'X');
                    return;
                }
            }
        }
    }

    public Boolean hasNext() {
        return switch (direction) {
            case 'N' -> xPointer > 0;
            case 'S' -> xPointer < height;
            case 'E' -> yPointer < width;
            case 'W' -> yPointer > 0;
            default -> null;
        };
    }

    public Boolean isSpaceAhead() {
        return switch (direction) {
            case 'N' -> map[xPointer - 1].charAt(yPointer) != '#';
            case 'S' -> map[xPointer + 1].charAt(yPointer) != '#';
            case 'E' -> map[xPointer].charAt(yPointer + 1) != '#';
            case 'W' -> map[xPointer].charAt(yPointer - 1) != '#';
            default -> null;
        };
    }

    public void move() {
        switch (direction) {
            case 'N' -> xPointer--;
            case 'S' -> xPointer++;
            case 'E' -> yPointer++;
            case 'W' -> yPointer--;
        };
        if (map[xPointer].charAt(yPointer) != '*') {
            char[] line = map[xPointer].toCharArray();
            if (line[yPointer] == '.') {
                line[yPointer] = 'X';
                steps++;
            } else if (line[yPointer] == 'X') {
                line[yPointer] = '*';
            }
            map[xPointer] = new String(line);
        }
    }

    public Boolean isLooping() {
        return switch (direction) {
            case 'N' -> map[xPointer - 1].charAt(yPointer) == '*';
            case 'S' -> map[xPointer + 1].charAt(yPointer) == '*';
            case 'E' -> map[xPointer].charAt(yPointer + 1) == '*';
            case 'W' -> map[xPointer].charAt(yPointer - 1) == '*';
            default -> null;
        };
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

    public void placeObstacle(int x, int y) {
        char[] line = map[y].toCharArray();
        line[x] = 'O';
        map[y] = new String(line);
    }

    public boolean moveObstacle() {
        char[] line = map[yObstacle].toCharArray();
        line[xObstacle] = '.';
        map[yObstacle] = new String(line);
        if (xObstacle == width - 1) {
            if (yObstacle == height - 1) {
                return false;
            }
            yObstacle++;
            xObstacle = 0;
        } else {
            xObstacle++;
        }
        System.out.println(map[yObstacle].charAt(xObstacle) + " " + xObstacle + " " + yObstacle);
        if (map[yObstacle].charAt(xObstacle) != '.') {
            return moveObstacle();
        } else {
            char[] newline = map[yObstacle].toCharArray();
            newline[xObstacle] = 'O';
            map[yObstacle] = new String(newline);
            return true;
        }
    }

    public Integer getXObstacle() {
        return xObstacle;
    }

    public Integer getYObstacle() {
        return yObstacle;
    }
}
