package aoc.day5;

import java.util.ArrayList;
import java.util.List;

public class RuleBook {

    private final Rule[] rules;

    public RuleBook(List<String> rules) {
        this.rules = new Rule[rules.size()];
        for (int i = 0; i < rules.size(); i++) {
            this.rules[i] = new Rule(rules.get(i));
        }
    }

    public Integer validateManual(String pageString, boolean repair) {
        String[] split = pageString.split(",");
        List<Integer> pages = new ArrayList<>(split.length);
        for (String page : split) {
            pages.add(Integer.parseInt(page));
        }
        for (Rule rule : rules) {
            if (!pages.contains(rule.getFirstPage()) || !pages.contains(rule.getSecondPage())) {
                continue;
            }
            if (pages.indexOf(rule.getFirstPage()) > pages.indexOf(rule.getSecondPage())) {
                if (repair) {
                    return repairManual(pages);
                }
                return 0;
            }
        }
        if (repair) {
            return 0;
        }
        return pages.get(pages.size() / 2);
    }

    public Integer repairManual(List<Integer> pages) {
       for (Rule rule : rules) {
           if (!pages.contains(rule.getFirstPage()) || !pages.contains(rule.getSecondPage())) {
               continue;
           }
           if (pages.indexOf(rule.getFirstPage()) > pages.indexOf(rule.getSecondPage())) {
               Integer move = pages.remove(pages.indexOf(rule.getFirstPage()));
               int index = pages.indexOf(rule.getSecondPage());
               pages.add(index, move);
               return repairManual(pages);
           }
       }
       return pages.get(pages.size() / 2);
    }
}
