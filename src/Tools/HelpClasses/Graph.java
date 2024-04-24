package Tools.HelpClasses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Graph {

    private List<List<Integer>> graph;
    private List<Boolean> sptSet;
    private List<Integer> dist;
    private final int vertices;
    private int startIndex;

    public Graph(List<List<Integer>> vertices) {
        this.vertices = vertices.size();
        this.graph = vertices;
    }

    public void dekstra(int startIndex) {
        this.startIndex = startIndex;
        dist = new ArrayList<>(Collections.nCopies(vertices, Integer.MAX_VALUE)); // массив из максимальных значений
        dist.set(startIndex, 0);
        sptSet = new ArrayList<>(Collections.nCopies(vertices, false));
        for (int i = 0; i < vertices; i++) {
            int u = this.findMinDistanceIndex();  // получаем миниальную длину, за которую можем попасть в точку
            sptSet.set(u, true);
            for (int v = 0; v < vertices; v++) {
                if ((graph.get(u).get(v) > 0) && (!sptSet.get(v)) && (dist.get(v) > dist.get(u) + graph.get(u).get(v))) { // (либо оставляем предыдущее значение если оно мнимально, либо вот эта формула)
                    dist.set(v, dist.get(u) + graph.get(u).get(v));         // перещитываем длинны кратчайших особых путей если нашли более короткий путь из текущей вершины
                }
            }
        }
//      Vertex <-> Distance from Source
        System.out.println(dist);
    }

    private int findMinDistanceIndex() {
        int minDistance = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < vertices; i++) {
            if ((dist.get(i) < minDistance) && !(sptSet.get(i))) { // + если элемент в вассиве sptSet имеет false
                // то текущая дистанция будет минимальной и мы сохраняем индекс элемента с этим значением
                minDistance = dist.get(i);
                minIndex = i;
            }
        }
        return minIndex;
    }

    public void bellmanFord(int src) {
        dist = new ArrayList<>(Collections.nCopies(vertices, Integer.MAX_VALUE));
        dist.set(src, 0);
        // V - 1 интераций достаточно, чтобы получить верные расстояния для всех вершин
        int loop = vertices - 1;
        while (loop != 0){
            for (int u = 0; u < vertices; u++) {
                for (int v = 0; v < vertices; v++) {
                    if (graph.get(u).get(v) != 0) {
                        if (dist.get(u) + graph.get(u).get(v) < dist.get(v)) {
                            dist.set(v, dist.get(u) + graph.get(u).get(v));
                        }
                    }
                }
            }
            loop--;
        }
        System.out.println(dist);
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (dist == null) {
            stringBuilder.append("Call method dijkstra or belmanFord after sout solution!!!");
        } else {
            stringBuilder.append("Длина кротчайшего пути от начальной точки до других элементов графа").append("\n");
            for (int i = 0; i < vertices; i++) {
                stringBuilder.append(startIndex).append(":")
                        .append(i).append(" <-> ")
                        .append(dist.get(i)).append("\n");
            }
        }
        return stringBuilder.toString();
    }
}
