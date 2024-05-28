import java.util.*;

public class DijkstraSearch<V> implements Search<V> {
    private Map<V, Double> distTo;
    private Map<V, V> edgeTo;
    private PriorityQueue<VertexDist<V>> pq;

    public DijkstraSearch(WeightedGraph<V> graph, V source) {
        distTo = new HashMap<>();
        edgeTo = new HashMap<>();
        pq = new PriorityQueue<>(Comparator.comparingDouble(VertexDist::getDistance));

        for (V vertex : graph.getVertices().keySet()) {
            distTo.put(vertex, Double.POSITIVE_INFINITY);
        }
        distTo.put(source, 0.0);

        pq.add(new VertexDist<>(source, 0.0));

        while (!pq.isEmpty()) {
            VertexDist<V> vd = pq.poll();
            V v = vd.getVertex();
            for (Map.Entry<Vertex<V>, Double> entry : graph.getVertex(v).getAdjacentVertices().entrySet()) {
                relax(v, entry.getKey().getData(), entry.getValue());
            }
        }
    }

    private void relax(V v, V w, double weight) {
        if (distTo.get(w) > distTo.get(v) + weight) {
            distTo.put(w, distTo.get(v) + weight);
            edgeTo.put(w, v);
            pq.add(new VertexDist<>(w, distTo.get(w)));
        }
    }

    @Override
    public List<V> pathTo(V dest) {
        if (!edgeTo.containsKey(dest)) return null;
        List<V> path = new LinkedList<>();
        for (V x = dest; x != null && !x.equals(edgeTo.get(x)); x = edgeTo.get(x)) {
            path.add(x);
        }
        path.add(edgeTo.keySet().iterator().next());
        Collections.reverse(path);
        return path;
    }

    private static class VertexDist<V> {
        private final V vertex;
        private final double distance;

        public VertexDist(V vertex, double distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        public V getVertex() {
            return vertex;
        }

        public double getDistance() {
            return distance;
        }
    }
}





