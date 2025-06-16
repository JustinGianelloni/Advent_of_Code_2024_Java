package aoc.day4;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

public class Table {

    private final String[] table;
    private final int height;
    private final int width;
    private int rowPointer = 0;
    private int colPointer = 0;

    public Table(String[] table) {
        height = table.length;
        width = table[0].length();
        this.table = table;
    }

    public boolean next() {
        if (colPointer == width - 1) {
            if (rowPointer == height - 1) {
                return false;
            }
            colPointer = 0;
            rowPointer += 1;
        } else {
            colPointer += 1;
        }
        return true;
    }

    public boolean isWordStart(char start) {
        return table[rowPointer].charAt(colPointer) == start;
    }

    private boolean checkRight() {
        if (colPointer + 3 > width - 1) {
            return false;
        }
        return table[rowPointer].charAt(colPointer + 1) == 'M'
                && table[rowPointer].charAt(colPointer + 2) == 'A'
                && table[rowPointer].charAt(colPointer + 3) == 'S';
    }

    private boolean checkLeft() {
        if (colPointer - 3 < 0) {
            return false;
        }
        return table[rowPointer].charAt(colPointer - 1) == 'M'
                && table[rowPointer].charAt(colPointer - 2) == 'A'
                && table[rowPointer].charAt(colPointer - 3) == 'S';
    }

    private boolean checkUp() {
        if (rowPointer - 3 < 0) {
            return false;
        }
        return table[rowPointer - 1].charAt(colPointer) == 'M'
                && table[rowPointer - 2].charAt(colPointer) == 'A'
                && table[rowPointer - 3].charAt(colPointer) == 'S';
    }

    private boolean checkDown() {
        if (rowPointer + 3 > height - 1) {
            return false;
        }
        return table[rowPointer + 1].charAt(colPointer) == 'M'
                && table[rowPointer + 2].charAt(colPointer) == 'A'
                && table[rowPointer + 3].charAt(colPointer) == 'S';
    }

    private boolean checkNorthEast() {
        if (rowPointer - 3 < 0 || colPointer + 3 > width - 1) {
            return false;
        }
        return table[rowPointer - 1].charAt(colPointer + 1) == 'M'
                && table[rowPointer - 2].charAt(colPointer + 2) == 'A'
                && table[rowPointer - 3].charAt(colPointer + 3) == 'S';
    }

    private boolean checkSouthEast() {
        if (rowPointer + 3 > height - 1|| colPointer + 3 > width - 1) {
            return false;
        }
        return table[rowPointer + 1].charAt(colPointer + 1) == 'M'
                && table[rowPointer + 2].charAt(colPointer + 2) == 'A'
                && table[rowPointer + 3].charAt(colPointer + 3) == 'S';
    }

    private boolean checkSouthWest() {
        if (rowPointer + 3 > height - 1 || colPointer - 3 < 0) {
            return false;
        }
        return table[rowPointer + 1].charAt(colPointer - 1) == 'M'
                && table[rowPointer + 2].charAt(colPointer - 2) == 'A'
                && table[rowPointer + 3].charAt(colPointer - 3) == 'S';
    }

    private boolean checkNorthWest() {
        if (rowPointer - 3 < 0 || colPointer - 3 < 0) {
            return false;
        }
        return table[rowPointer - 1].charAt(colPointer - 1) == 'M'
                && table[rowPointer - 2].charAt(colPointer - 2) == 'A'
                && table[rowPointer - 3].charAt(colPointer - 3) == 'S';
    }

    private boolean leftToRight() {
        if (rowPointer + 2 > height - 1 || colPointer + 2 > width - 1) {
            return false;
        }
        return table[rowPointer + 1].charAt(colPointer + 1) == 'A'
                && table[rowPointer + 2].charAt(colPointer + 2) == 'S'
                && table[rowPointer + 2].charAt(colPointer) == 'M'
                && table[rowPointer].charAt(colPointer + 2) == 'S';
    }

    private boolean bottomToTop() {
        if (rowPointer - 2 < 0 || colPointer + 2 > width - 1) {
            return false;
        }
        return table[rowPointer - 1].charAt(colPointer + 1) == 'A'
                && table[rowPointer - 2].charAt(colPointer + 2) == 'S'
                && table[rowPointer].charAt(colPointer + 2) == 'M'
                && table[rowPointer - 2].charAt(colPointer) == 'S';
    }

    private boolean rightToLeft() {
        if (rowPointer - 2 < 0 || colPointer - 2 < 0) {
            return false;
        }
        return table[rowPointer - 1].charAt(colPointer - 1) == 'A'
                && table[rowPointer - 2].charAt(colPointer - 2) == 'S'
                && table[rowPointer - 2].charAt(colPointer) == 'M'
                && table[rowPointer].charAt(colPointer - 2) == 'S';
    }

    private boolean topToBottom() {
        if (rowPointer + 2 > height - 1 || colPointer + 2 > width - 1) {
            return false;
        }
        return table[rowPointer + 1].charAt(colPointer + 1) == 'A'
                && table[rowPointer + 2].charAt(colPointer + 2) == 'S'
                && table[rowPointer].charAt(colPointer + 2) == 'M'
                && table[rowPointer + 2].charAt(colPointer) == 'S';
    }

    private static int countDiscoveries(List<BooleanSupplier> methods) {
        int count = 0;
        for (BooleanSupplier method : methods) {
            if (method.getAsBoolean()) {
                count++;
            }
        }
        return count;
    }

    public int checkXMAS() {
        List<BooleanSupplier> tests = new ArrayList<>(8);
        tests.add(this::checkRight);
        tests.add(this::checkLeft);
        tests.add(this::checkUp);
        tests.add(this::checkDown);
        tests.add(this::checkNorthEast);
        tests.add(this::checkSouthEast);
        tests.add(this::checkSouthWest);
        tests.add(this::checkNorthWest);
        return countDiscoveries(tests);
    }

    public int checkMAS() {
        List<BooleanSupplier> tests = new ArrayList<>(4);
        tests.add(this::leftToRight);
        tests.add(this::bottomToTop);
        tests.add(this::rightToLeft);
        tests.add(this::topToBottom);
        return countDiscoveries(tests);
    }
}
