package aoc.day6;

public class Map {

    private final String[] map;
    private final int width;
    private final int height;
    private int rowPointer = 0;
    private int colPointer = 0;
    private char direction = 'N';
    private int steps = 1;

    public Map(String[] map) {
        this.map = map;
        width = map[0].length();
        height = map.length;
        setStartPointer();
    }

    private void setStartPointer() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (map[y].charAt(x) == '^') {
                    rowPointer = y;
                    colPointer = x;
                    map[y] = map[y].replace('^', 'X');
                    return;
                }
            }
        }
    }

    public Boolean hasNext() {
        return switch (direction) {
            case 'N' -> rowPointer > 0;
            case 'S' -> rowPointer < height;
            case 'E' -> colPointer < width;
            case 'W' -> colPointer > 0;
            default -> null;
        };
    }

    public Boolean isSpaceAhead() {
        return switch (direction) {
            case 'N' -> map[rowPointer - 1].charAt(colPointer) != '#';
            case 'S' -> map[rowPointer + 1].charAt(colPointer) != '#';
            case 'E' -> map[rowPointer].charAt(colPointer + 1) != '#';
            case 'W' -> map[rowPointer].charAt(colPointer - 1) != '#';
            default -> null;
        };
    }

    public void move() {
        switch (direction) {
            case 'N' -> rowPointer--;
            case 'S' -> rowPointer++;
            case 'E' -> colPointer++;
            case 'W' -> colPointer--;
        };
        if (map[rowPointer].charAt(colPointer) == '.') {
            char[] line = map[rowPointer].toCharArray();
            line[colPointer] = 'X';
            map[rowPointer] = new String(line);
            steps++;
        }
    }

    public void turn() {
        switch (direction) {
            case 'N' -> direction = 'E';
            case 'S' -> direction = 'W';
            case 'E' -> direction = 'S';
            case 'W' -> direction = 'N';
        }
    }

    public Integer getSteps() {
        return steps;
    }
}
