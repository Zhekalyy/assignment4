import java.util.*;

public class BreadthFirstSearch<V> implements Search<V> {
    private Map<V, V> edgeTo;
    private Set<V> marked;
    private final V source;

    public BreadthFirstSearch(MyGraph<V> graph, V source) {
        this.source = source;
        this.edgeTo = new HashMap<>();
        this.marked = new HashSet<>();
        bfs(graph, source);
    }

    private void bfs(MyGraph<V> graph, V source) {
        Queue<V> queue = new LinkedList<>();
        queue.add(source);
        marked.add(source);

        while (!queue.isEmpty()) {
            V v = queue.poll();
            for (V w : graph.getAdjVertices(v)) {
                if (!marked.contains(w)) {
                    queue.add(w);
                    marked.add(w);
                    edgeTo.put(w, v);
                }
            }
        }
    }

    @Override
    public List<V> pathTo(V dest) {
        if (!marked.contains(dest)) return null;
        List<V> path = new LinkedList<>();
        for (V x = dest; x != null && !x.equals(source); x = edgeTo.get(x)) {
            path.add(x);
        }
        path.add(source);
        Collections.reverse(path);
        return path;
    }
}





