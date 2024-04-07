package SecondSemester;

import java.io.IOException;
import java.util.*;

import static SecondSemester.Lab2.Lab2.*;

public class Lab3 {
    private static final int MAX_N = 1000;
    private static final List<Boolean> used = new ArrayList<>(Collections.nCopies(MAX_N, false));
    private static final List<Integer> components = new ArrayList<>();

    public static void bfs(int start, List<List<Integer>> graph) {  /// start - начальная вершина
        Stack<Integer> queue = new Stack<>();
        queue.add(start);
        used.set(start, true);
        components.add(start);
        int front = 0;

        while (front < queue.size()) {
            int current = queue.get(front++);
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
        /*for (int i = 0; i < n; i++) {
            used.set(i, false);
        }*/

        for (int i = 0; i < number; i++) {
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
/// Компоненты: 0 1 3 8 2 7 9 5 4 6