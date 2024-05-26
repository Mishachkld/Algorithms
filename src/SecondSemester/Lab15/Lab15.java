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
                System.out.println(neighbor);
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
    private int V; // Количество вершин в графе
    private final LinkedList<Integer>[] adj; // Список смежности

    public GraphColoring(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    // Функция для раскраски графа
    void greedyColoring() {
        int result[] = new int[V]; // Результат раскраски
        Arrays.fill(result, -1); // Инициализация всех вершин как нераскрашенных
        result[0] = 0; // Раскрашиваем первую вершину в цвет 0

        boolean available[] = new boolean[V];         // Доступные цвета. true, если цвет доступен, false, если занят
        Arrays.fill(available, true);

        // Раскраска остальных (V-1) вершин
        for (int u = 1; u < V; u++) {
            for (int i : adj[u]) {             // Перебираем все смежные вершины и отмечаем их цвета как недоступные
                if (result[i] != -1)
                    available[result[i]] = false;
            }
            int cr;
            for (cr = 0; cr < V; cr++)
                if (available[cr]) // Находим первый доступный цвет
                    break;
            result[u] = cr; // Раскрашиваем вершину в найденный цвет
            Arrays.fill(available, true); // Сбрасываем доступность цветов для следующей итерации
        }
        System.out.println("Хроматическое число графа: " + Arrays.stream(result).max().getAsInt());
        System.out.println("Раскраска вершин:");
        for (int u = 0; u < V; u++) {
            System.out.println("Вершина " + u + " -> Цвет " + result[u]);
        }
    }

    public static void main(String[] args) {
        GraphColoring graph = new GraphColoring(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.greedyColoring();
    }
}
