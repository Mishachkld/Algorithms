package SecondSemester.Lab15;

import Tools.HelpClasses.TripleData;
import Tools.Helper;

import java.io.IOException;
import java.util.*;

import static Tools.Helper.readInLinesFromFile;

public class Lab15 {

    public static boolean isColoringGraph(List<List<Integer>> graph, int colorsCount, int size) {
        Set<Integer> colours = new HashSet<>();
        List<Set<Integer>> colored = new ArrayList<>();

        for (int i = 1; i < colorsCount + 1; i++) {
            colours.add(i);
        }
        for (int i = 0; i < size; i++) {
            colored.add(new HashSet<>());
        }
        colored.get(0).add(1);
        for (int i = 1; i < size; i++) {
            Set<Integer> usedColors = new HashSet<>();
            for (Integer neighbor : graph.get(i)) {
                usedColors.addAll(colored.get(neighbor));
                System.out.println( neighbor);
            }
            System.out.println(usedColors.size());
            Set<Integer> freeColors = new HashSet<>(colours);
            freeColors.removeAll(usedColors);
            System.out.println(freeColors);
            if (!freeColors.isEmpty()) {
                int curColor = freeColors.iterator().next();
                colored.get(i).add(curColor);
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        String path = "src/DiscretMath/Lab6/input.txt";
        List<TripleData<Integer, Integer, Integer>> data = readInLinesFromFile(path);
        List<List<Integer>> graph = Helper.createMatrixFromTripleData(data);

        int colours = 3; // Количество доступных цветов
        int size = data.size();

        System.out.println(isColoringGraph(graph, colours, size));

    }


}

class GraphColoring {

    private static int V; // Количество вершин в графе
    private static int[] colors; // Массив для хранения цветов вершин
    private static int[][] graph; // Матрица смежности графа

    // Функция для проверки, можно ли покрасить вершину v цветом c
    private static boolean isSafe(int v, int c) {
        for (int i = 0; i < V; i++) {
            if (graph[v][i] == 1 && c == colors[i]) {
                return false;
            }
        }
        return true;
    }

    // Рекурсивная функция для раскраски графа
    private static boolean graphColoringUtil(int v, int m) {
        if (v == V) {
            return true; // Все вершины раскрашены
        }
        for (int c = 0; c < m; c++) {
            if (isSafe(v, c)) {
                colors[v] = c;
                if (graphColoringUtil(v + 1, m)) {
                    return true;
                }
                colors[v] = -1; // Backtracking
            }
        }
        return false;
    }

    // Функция для раскраски графа с использованием жадного алгоритма
    public static void graphColoringGreedy(int[][] g, int m) {
        colors = new int[V];
        Arrays.fill(colors, -1); // Инициализируем все вершины цветом -1 (не раскрашены)
        graph = g;
        if (!graphColoringUtil(0, m)) {
            System.out.println("невозможно");
        } else {
            System.out.println("успешно раскрашен. " + m + " цвета");
            System.out.println(Arrays.toString(colors));
        }
    }

    public static void main(String[] args) {
        int[][] graph = {{0, 1, 1, 1},
                         {1, 0, 1, 0},
                         {1, 1, 0, 1},
                         {1, 0, 1, 0}};
        V = graph.length;
        int m = 3; // Количество доступных цветов
        graphColoringGreedy(graph, m);
    }
}