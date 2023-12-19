package days.day19;

import util.Util;

import java.util.*;

public class Day19 {

    public static Integer part1(final List<String> input) {
        boolean inWorkFlows = true;
        final Map<String, WorkFlow> workFlowMap = new HashMap<>();
        final List<Part> partsList = new ArrayList<>();
        for (final String line : input) {
            if (inWorkFlows) {
                if (line.isEmpty()) {
                    inWorkFlows = false;
                    continue;
                }
                final WorkFlow workFlow = new WorkFlow(line);
                workFlowMap.put(workFlow.getLabel(), workFlow);
            }
            else {
                String[] numbers = line.substring(1, line.length() - 1).split(",");
                partsList.add(new Part(
                        Integer.parseInt(numbers[0].substring(2)),
                        Integer.parseInt(numbers[1].substring(2)),
                        Integer.parseInt(numbers[2].substring(2)),
                        Integer.parseInt(numbers[3].substring(2))
                ));
            }
        }

        final Set<Part> acceptedParts = new HashSet<>();
        for (final Part part : partsList) {
            if (processPart(part, workFlowMap)) {
                acceptedParts.add(part);
            }
        }

        return acceptedParts.stream()
                .map(part -> part.a() + part.m() + part.s() + part.x())
                .reduce(0, Integer::sum);
    }

    public static long part2(final List<String> input) {
        final Map<String, WorkFlow> workFlowMap = new HashMap<>();
        for (final String line : input) {
            if (line.isEmpty()) {
                break;
            }
            final WorkFlow workFlow = new WorkFlow(line);
            workFlowMap.put(workFlow.getLabel(), workFlow);
        }

        final TreeMap<String, TreeSet<Integer> > allows = new TreeMap<>();
        allows.put("x", new TreeSet<>());
        allows.put("m", new TreeSet<>());
        allows.put("a", new TreeSet<>());
        allows.put("s", new TreeSet<>());

        for(int i=1; i<=4000; i++) {
            allows.get("x").add(i);
            allows.get("m").add(i);
            allows.get("a").add(i);
            allows.get("s").add(i);
        }

        return recursive(allows, "in", 0, workFlowMap);
    }

    private static boolean processPart(final Part part, final Map<String, WorkFlow> workFlowMap) {
        WorkFlow currentWorkFlow;
        String label = "in";
        do {
            currentWorkFlow = workFlowMap.get(label);
            label = currentWorkFlow.process(part);
        } while (!label.equals("A") && !label.equals("R"));
        return label.equals("A");
    }

    public static void main(String... args) {
        var fileContent = Util.getFileContent(19);
        System.out.println(part1(fileContent));
        System.out.println(part2(fileContent));
    }

    public static long recursive(TreeMap<String, TreeSet<Integer> > allowed, String next, int step, Map<String, WorkFlow> workFlowMap) {
        if (next.equals("R")) {
            return 0L;
        }

        if (next.equals("A")) {
            long opt = 1L;
            for(TreeSet<Integer> opt_set : allowed.values()) {
                opt = opt * opt_set.size();
            }
            return opt;
        }

        for(TreeSet<Integer> opt_set : allowed.values()) {
            if (opt_set.isEmpty()) {
                return 0L;
            }
        }

        final WorkFlow workFlow = workFlowMap.get(next);
        final Step s = workFlow.steps.get(step);

        if (!s.isConditional()) {
            return recursive(allowed, s.getResult(), 0, workFlowMap);
        }

        long opt = 0L;
        {
            TreeMap<String, TreeSet<Integer> > a2 = new TreeMap<>();
            a2.putAll(allowed);
            TreeSet<Integer> opt_set = new TreeSet<>();
            opt_set.addAll(a2.get(s.getCharToCheck()));
            s.applyLimit(opt_set);
            a2.put(s.getCharToCheck(), opt_set);
            opt+= recursive(a2, s.getResult(), 0, workFlowMap);
        }

        {
            TreeMap<String, TreeSet<Integer> > a2 = new TreeMap<>();
            a2.putAll(allowed);
            TreeSet<Integer> opt_limit = new TreeSet<>();
            opt_limit.addAll(a2.get(s.getCharToCheck()));
            s.applyLimit(opt_limit);
            TreeSet<Integer> opt_set = new TreeSet<>();
            opt_set.addAll(a2.get(s.getCharToCheck()));
            opt_set.removeAll(opt_limit);
            a2.put(s.getCharToCheck(), opt_set);
            opt+= recursive(a2, next, step+1, workFlowMap);
        }
        return opt;
    }
}
