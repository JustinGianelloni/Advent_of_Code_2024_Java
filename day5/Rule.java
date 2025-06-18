package aoc.day5;

public class Rule {

    private final int first;
    private final int second;

    public Rule(String rule) {
        String[] pages = rule.split("\\|");
        first = Integer.parseInt(pages[0]);
        second = Integer.parseInt(pages[1]);
    }

    public Integer getFirstPage() {
        return first;
    }

    public Integer getSecondPage() {
        return second;
    }

    public String toString() {
        return first + "|" + second;
    }
}
