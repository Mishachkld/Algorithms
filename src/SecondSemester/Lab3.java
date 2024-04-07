package SecondSemester;

import java.io.IOException;
import java.util.*;

import static SecondSemester.Lab2.Lab2.*;

public class Lab3 {
    private static final int MAX_N = 1000;
    private static List<Boolean> used = new ArrayList<>(Collections.nCopies(MAX_N, false));
    private static List<Integer> components = new ArrayList<>();

    public static void bfs(int size, List<List<Integer>> graph) {
        Stack<Integer> queue = new Stack<>();
        queue.add(size);
        used.set(size, true);
        components.add(size);
        int front = 0;

        while (front < queue.size()) {
            int current = queue.get(front);
            front++;
            for (Integer item : graph.get(current)) {
                if (!used.get(item)) {
                    used.set(item, true);
                    queue.add(item);
                    components.add(item);
                }
            }
        }
    }

    private static void findComponents(int number, List<List<Integer>> graph) {
        int n = number;
        for (int i = 0; i < n; i++) {
            used.set(i, false);
        }

        for (int i = 0; i < n; i++) {
            if (!used.get(i)) {
                components.clear();
                bfs(i, graph);

                System.out.print("Компонент: ");
                for (Integer item: components){
                    System.out.print(item + " ");
                }
                System.out.println();
            }
        }

    }

    private static List<List<Integer>> numerateMatrix(List<List<Integer>> matrix) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < matrix.size(); i++) {
            graph.add(new ArrayList<>());
            for (int j = 0; j < matrix.get(i).size(); j++) {
                if (matrix.get(i).get(j) == 1) {
                    graph.get(i).add(j);
                }
            }
        }
        return graph;
    }

    public static void main(String[] args) throws IOException {
        List<List<Integer>> matrix = readMatrixFromFile(PATH_TO_INPUT_FILE);
        List<List<Integer>> graph = numerateMatrix(matrix);
        findComponents(graph.size(), graph);
    }
}
