package days.day19;

import java.util.*;

public class WorkFlow {

    private final String label;
    private final List<Rule> rules = new ArrayList<>();
    ArrayList<Step> steps = new ArrayList<>();

    public WorkFlow(final String input) {
        String step_list = input.split("\\{")[1].replace("}","");
        for(String ss : en(step_list, ",")) {
            steps.add(new Step(ss));
        }

        this.label = input.split("\\{")[0];
        final String[] rules = input.split("\\{")[1].substring(0, input.split("\\{")[1].length() - 1).split(",");
        for (final String rule : rules) {
            this.rules.add(new Rule(rule));
        }
    }

    public static List<String> en(String input, String delim) {
        ArrayList<String> lst = new ArrayList<>();
        StringTokenizer stok = new StringTokenizer(input, delim);

        while(stok. hasMoreTokens()) {
            lst.add(stok. nextToken());
        }

        return lst;
    }

    public String getLabel() {
        return this.label;
    }

    public String process(final Part part) {
        for (int i = 0; i < rules.size() - 1; i++) {
            char charToCheck = rules.get(i).getCharToCheck();
            int partValue = 0;
            if (charToCheck == 'x') {
                partValue = part.x();
            }
            else if (charToCheck == 's') {
                partValue = part.s();
            }
            else if (charToCheck == 'm') {
                partValue = part.m();
            }
            else {
                partValue = part.a();
            }
            final Boolean positive = rules.get(i).getCheckFunction().apply(partValue, rules.get(i).getCheckValue());
            if (positive) {
                return rules.get(i).getNextLabel();
            }
        }
        return this.rules.get(this.rules.size() - 1).getOriginal();
    }
}
