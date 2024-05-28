import java.util.*;

public class MyGraph<V> {
    private Map<V, List<V>> adjVertices;
    private boolean directed;

    public MyGraph(boolean directed) {
        this.directed = directed;
        this.adjVertices = new HashMap<>();
    }

    public void addEdge(V source, V dest) {
        adjVertices.computeIfAbsent(source, k -> new ArrayList<>()).add(dest);
        if (!directed) {
            adjVertices.computeIfAbsent(dest, k -> new ArrayList<>()).add(source);
        }
    }

    public List<V> getAdjVertices(V vertex) {
        return adjVertices.getOrDefault(vertex, Collections.emptyList());
    }
}




