import java.util.*;

public class DepthFirstSearch<V> implements Search<V> {
    private Map<V, V> edgeTo;
    private Set<V> marked;
    private final V source;

    public DepthFirstSearch(MyGraph<V> graph, V source) {
        this.source = source;
        this.edgeTo = new HashMap<>();
        this.marked = new HashSet<>();
        dfs(graph, source);
    }

    private void dfs(MyGraph<V> graph, V v) {
        marked.add(v);
        for (V w : graph.getAdjVertices(v)) {
            if (!marked.contains(w)) {
                edgeTo.put(w, v);
                dfs(graph, w);
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


