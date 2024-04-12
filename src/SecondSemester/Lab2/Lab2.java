package SecondSemester.Lab2;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import Tools.HelpClasses.Point;


public class Lab2 {

    public static final String PATH_TO_INPUT_FILE = "src/SecondSemester/Lab2/input.txt";

    public static List<List<Integer>> readMatrixFromFile(String path) throws IOException {
        List<List<String>> matrixString = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String lineFromFile;
        while ((lineFromFile = reader.readLine()) != null) {
            matrixString.add(new ArrayList<>(List.of(lineFromFile.split(" "))));
        }
        return convertToIntegerArray(matrixString);
    }

    private static List<List<Integer>> convertToIntegerArray(List<List<String>> matrixString) {
        List<List<Integer>> matrix = new ArrayList<>();
        for (List<String> line : matrixString) {
            matrix.add(new ArrayList<>());
            for (String item : line) {
                matrix.get(matrix.size() - 1).add(Integer.parseInt(item));
            }
        }
        return matrix;
    }

    public static List<Point> convertMatrixToGraph(List<List<Integer>> matrix) {
        List<Point> graph = new ArrayList<>();
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                if (matrix.get(i).get(j) == 1) {
                    graph.add(new Point(i, j));
                }
            }
        }

        return graph;
    }

    public static int bfs(List<Point> graph, int start, int finish) {
        List<Integer> dist = new ArrayList<>(Collections.nCopies(graph.size(), null)); // массив расстояний
        dist.set(start, 0);
        List<Integer> queue = new ArrayList<>(List.of(0)); // очередь вершин, требующих обработку

        while (!queue.isEmpty()) {              // в X хранится текущая вершина, в Y та, с которой граф связан
            int u = queue.remove(0);      // получаем вершину, которую будем в первую очередь обрабатывать
            for (Point edge : graph) {     // Просматриваем все соседнии вершины
                if (edge.x == u) {              // т.е. если мы нашли вершину, которую мы сейчас доставли из очереди то ее рассматриваем
                    int v = edge.y;             // получили соседа текущей вершины
                    if (dist.get(v) == null) {  // Если сосед еще не посещен
                        dist.set(v, dist.get(u) + 1);  // то отмечаем как посещеным и записываем расстояние на 1 больше текущей
                        queue.add(v);           // добваляем в очредь новую вершину для рассмотрения
                    }
                }
            }
        }
        return dist.get(finish);
    }

    public static void main(String[] args) throws IOException {
        List<List<Integer>> matrix = readMatrixFromFile(PATH_TO_INPUT_FILE);
        System.out.println(bfs(convertMatrixToGraph(matrix), 0, 3));
        outGraph(convertMatrixToGraph(matrix));
    }

    public static void outMatrix(List<List<Integer>> matrix) {
        for (List<Integer> line : matrix) {
            for (Integer item : line) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
    }

    public static void outGraph(List<Point> graph) {
        for (Point point : graph) {
            System.out.print(point + " ");
        }
    }


//0 1 0 1 0 0 0 0 0 0
//1 0 0 0 0 0 0 0 1 0
//0 0 0 1 1 0 1 0 1 0
//1 0 1 0 0 0 0 1 0 1
//0 0 1 0 0 0 0 0 0 0
//0 0 0 0 0 0 1 0 1 0
//0 0 1 0 0 1 0 0 0 0
//0 0 0 1 0 0 0 0 0 1
//0 1 1 0 0 1 0 0 0 0
//0 0 1 1 0 0 0 1 0 0

}
