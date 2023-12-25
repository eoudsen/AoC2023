package days.day25;

import util.Util;

import java.util.*;

public class Day25 {

    public static long part1(final List<String> input) {
        final Map<String, Set<String>> graph = new HashMap<>();
        input.forEach(l -> processLine(l, graph));
        final Map<Set<String>, Integer> edgeFrequency = new HashMap<>();
        final List<String> vertexes = new ArrayList<>(graph.keySet());
        for(int i = 0; i < vertexes.size(); i++) {
            final String start = vertexes.get(i);
            for(int j = i + 1; j < vertexes.size(); j++) {
                final String target = vertexes.get(j);
                markEdges(start, target, graph, edgeFrequency);
            }
        }
        edgeFrequency.entrySet().stream().sorted(Comparator.<Map.Entry<Set<String>, Integer>>comparingInt(Map.Entry::getValue).reversed()).limit(3).forEach(e -> cutEdge(graph, e.getKey()));
        int part1Size = findConnectedSize(graph.keySet().iterator().next(), graph);
        int part2Size = graph.size()-part1Size;
        return (long) part1Size * part2Size;
    }

    public static Integer part2(final List<String> input) {
        return 0;
    }

    private static void markEdges(final String start, final String target, final Map<String, Set<String>> graph, final Map<Set<String>, Integer> edgeFrequency) {
        final Queue<Step> queue = new LinkedList<>();
        final Set<String> visited = new HashSet<>();
        queue.add(new Step(start, List.of()));
        visited.add(start);
        while (!queue.isEmpty()) {
            final Step curr = queue.poll();
            if(target.equals(curr.vertex())) {
                curr.edges().forEach(e -> {
                    int v = edgeFrequency.getOrDefault(e, 0);
                    edgeFrequency.put(e, v+1);
                });
                return;
            }
            graph.get(curr.vertex()).stream().filter(n -> !visited.contains(n)).forEach(n -> {
                final List<Set<String>> nextEdges = new ArrayList<>(curr.edges());
                nextEdges.add(new HashSet<>(Set.of(curr.vertex(), n)));
                final Step nextStep = new Step(n, nextEdges);
                queue.add(nextStep);
                visited.add(n);
            });
        }
    }

    private static void cutEdge(final Map<String, Set<String>> graph, final Set<String> edge) {
        final Iterator<String> it = edge.iterator();
        final String a = it.next();
        final String b = it.next();
        graph.get(a).remove(b);
        graph.get(b).remove(a);
    }

    private static int findConnectedSize(final String start, final Map<String, Set<String>> graph) {
        final Queue<String> queue = new LinkedList<>();
        final Set<String> visited = new HashSet<>();
        queue.add(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            final String curr = queue.poll();
            graph.get(curr).stream().filter(n -> !visited.contains(n)).forEach(n -> {
                queue.add(n);
                visited.add(n);
            });
        }
        return visited.size();
    }

    private static void processLine(final String line, final Map<String, Set<String>> graph) {
        final String[] parts = line.split(": ");
        final String name = parts[0];
        final String[] cons = parts[1].split(" ");
        for(final String con: cons) {
            markConnection(graph, name, con);
        }
    }

    private static void markConnection(final Map<String, Set<String>> graph, final String from, final String to) {
        graph.computeIfAbsent(from, (k)->new HashSet<>()).add(to);
        graph.computeIfAbsent(to, (k)->new HashSet<>()).add(from);
    }

    public static void main(String... args) {
        var fileContent = Util.getFileContent(25);
        System.out.println(part1(fileContent));
        System.out.println(part2(fileContent));
    }
}
