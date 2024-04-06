package SecondSemester.Lab2;

import SecondSemester.Lab1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Lab2 {

    private static final String PATH_TO_INPUT_FILE = "src/SecondSemester/Lab2/input.txt";

    private static List<List<Integer>> readMatrixFromFile() throws IOException {
        List<List<String>> matrixString = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(PATH_TO_INPUT_FILE));
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

    private static List<Lab1.Point> convertMatrixToGraph(List<List<Integer>> matrix) {
        List<Lab1.Point> graph = new ArrayList<>();
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                if (matrix.get(i).get(j) == 1) {
                    graph.add(new Lab1.Point(i, j));
                }
            }
        }

        return graph;
    }

    public static int bfs(List<Lab1.Point> graph, int source, int destination) {
        List<Integer> dist = new ArrayList<>();

        for (int i = 0; i < graph.size(); i++) {
            dist.add(null);
        }
        dist.set(source, 0);
        List<Integer> queue = new ArrayList<>(List.of(0));

        while (!queue.isEmpty()) {
            int u = queue.remove(0);
            for (Lab1.Point edge : graph) {
                if (edge.x == u) {
                    int v = edge.y;
                    if (dist.get(v) == null) {
                        dist.set(v, dist.get(u) + 1);
                        queue.add(v);
                    }
                }
            }
        }
        return dist.get(destination);
    }

    public static void main(String[] args) throws IOException {
        List<List<Integer>> matrix = readMatrixFromFile();
        System.out.println(bfs(convertMatrixToGraph(matrix), 0, 5));
    }

    public static void outMatrix(List<List<Integer>> matrix) {
        for (List<Integer> line : matrix) {
            for (Integer item : line) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
    }

    public static void outGraph(List<Lab1.Point> graph) {
        for (Lab1.Point point : graph) {
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
