package Tools.HelpClasses;

import Tools.Helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Graph {

    private List<List<Integer>> graph;
    private List<Boolean> sptSet;
    private List<Integer> dist;
    private final int vertices;

    public Graph(List<List<Integer>> vertices) {
        this.vertices = vertices.size();
        this.graph = vertices;
    }

    public void dijkstra(int startIndex) {
        dist = new ArrayList<>(Collections.nCopies(vertices, Integer.MAX_VALUE));
        dist.set(startIndex, 0);
        sptSet = new ArrayList<>(Collections.nCopies(vertices, false));
        for (int i = 0; i < vertices; i++) {
            int u = this.findMinDistanceIndex();
            sptSet.set(u, true);
            for (int v = 0; v < vertices; v++) {
                if ((graph.get(u).get(v) > 0) && (!sptSet.get(v)) && (dist.get(v) > dist.get(u) + graph.get(u).get(v))) {
                    dist.set(v, dist.get(u) + graph.get(u).get(v));
                }
            }
        }
//      Vertex <-> Distance from Source
        System.out.println(dist);
    }

    public int findMinDistanceIndex() {
        int minDistance = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < vertices; i++) {
            if ((dist.get(i) < minDistance) && !(sptSet.get(i))) {
                minDistance = dist.get(i);
                minIndex = i;
            }
        }
        return minIndex;
    }

    @Override
    public String toString() {
        if (dist == null) {
            return "Call method dijkstra after sout solution!!!";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < vertices; i++) {
            stringBuilder.append(i).append(" <-> ")
                    .append(dist.get(i)).append("\n");
        }
        return stringBuilder.toString();
    }
}
