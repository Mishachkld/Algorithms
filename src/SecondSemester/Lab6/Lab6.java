package SecondSemester.Lab6;

import Tools.HelpClasses.TripleData;
import Tools.Helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static Tools.Helper.fillArray;

public class Lab6 {

    public static class Edge {

        private final List<Integer> parent;
        private final List<Integer> rank;

        public Edge(int n) {
            this.parent = Helper.generateNumeratedArray(0, n);
            this.rank = fillArray(n);
        }

        public int find(int u) {
            if (parent.get(u) != u) {
                System.out.println(parent.get(u) + " " + u);
                int element = find(parent.get(u));
                parent.set(u, element);
            }
            System.out.println(parent.get(u));
            return parent.get(u);
        }

        public boolean union(int u, int v) {
            int uRoot = find(u);
            int vRoot = find(v);
            if (vRoot == uRoot) {
                return false;
            }
            if (rank.get(uRoot) > rank.get(vRoot)) {
                parent.set(vRoot, uRoot);
            } else if (rank.get(uRoot) < rank.get(vRoot)) {
                parent.set(uRoot, vRoot);
            } else {
                parent.set(vRoot, uRoot);
                parent.set(uRoot, parent.get(uRoot) + 1);
            }
            return true;
        }

    }


    public static List<TripleData<Integer, Integer, Integer>> kruk(List<List<Integer>> graph) {
        int n = graph.size();
        List<TripleData<Integer, Integer, Integer>> edges = new ArrayList<>();
        List<TripleData<Integer, Integer, Integer>> mst = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (graph.get(i).get(j) != 0) {
                    edges.add(new TripleData<>(i, j, graph.get(i).get(j)));
                }
            }
        }
        edges.sort(new Comparator<TripleData<Integer, Integer, Integer>>() {
            @Override
            public int compare(TripleData<Integer, Integer, Integer> o1, TripleData<Integer, Integer, Integer> o2) {
                return o1.weight.compareTo(o2.weight);
            }
        });
        Edge edgeSet = new Edge(n);
        for (TripleData<Integer, Integer, Integer> edge : edges) {
            int u = edge.first;
            int v = edge.second;
            int weight = edge.weight;
            if (edgeSet.union(u, v)) {
                mst.add(new TripleData<>(u, v, weight));
            }
        }
        return mst;
    }

    public static void main(String[] args) throws IOException {
        String path = "src/SecondSemester/Lab6/input.txt";
        List<List<Integer>> graph = Helper.readMatrixFromFile(path);
        System.out.println((kruk(graph)));
    }
}
