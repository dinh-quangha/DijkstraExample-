import java.util.*;

class DijkstraExample {
    static class Edge {
        String vertex;
        int weight;

        Edge(String vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    public static Map<String, Integer> dijkstra(Map<String, List<Edge>> graph, String start) {
        Map<String, Integer> distances = new HashMap<>();
        for (String vertex : graph.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE); // Vô cực
        }
        distances.put(start, 0);

        // PriorityQueue theo khoảng cách ngắn nhất
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
                Map.Entry.comparingByValue()
        );
        pq.add(new AbstractMap.SimpleEntry<>(start, 0));

        while (!pq.isEmpty()) {
            Map.Entry<String, Integer> current = pq.poll();
            String currentVertex = current.getKey();
            int currentDistance = current.getValue();

            for (Edge neighbor : graph.get(currentVertex)) {
                int newDist = currentDistance + neighbor.weight;
                if (newDist < distances.get(neighbor.vertex)) {
                    distances.put(neighbor.vertex, newDist);
                    pq.add(new AbstractMap.SimpleEntry<>(neighbor.vertex, newDist));
                }
            }
        }

        return distances;
    }

    public static void main(String[] args) {
        // Đồ thị ví dụ: A → B (4), A → C (1), C → B (2)
        Map<String, List<Edge>> graph = new HashMap<>();
        graph.put("A", Arrays.asList(new Edge("B", 4), new Edge("C", 1)));
        graph.put("B", new ArrayList<>());
        graph.put("C", Arrays.asList(new Edge("B", 2)));

        Map<String, Integer> result = dijkstra(graph, "A");

        for (String vertex : result.keySet()) {
            System.out.println("Shortest distance from A to " + vertex + ": " + result.get(vertex));
        }
    }
}
