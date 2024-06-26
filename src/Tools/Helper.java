package Tools;

import Tools.HelpClasses.Point;
import Tools.HelpClasses.TripleData;

import java.io.*;
import java.util.*;

public class Helper {

    public static final String PATH_TO_INPUT_FILE = "src/SecondSemester/Lab2/input.txt";
    private static final int MAX_N = 1000;
    public static List<Boolean> used = new ArrayList<>(Collections.nCopies(MAX_N, false));
    public static List<Integer> components = new ArrayList<>();


    public static void writeInFile(HashMap<Integer, List<String>> expression, String Path) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(Path));
        StringBuilder builder = new StringBuilder();
        for (Integer key : expression.keySet()) {
            builder.append(key).append(" - ");
            for (String item : expression.get(key))
                builder.append(item).append(" ");
            builder.append("\n");
        }

        writer.write(builder.toString());
        writer.close();
        System.out.println("Вывод в файл " + Path + " выполнен!");

    }

    public static List<Integer> scanConsole() {
        List<Integer> expression = new ArrayList<>();
        System.out.print("Введите строку: ");
        Scanner scanner = new Scanner(System.in);
        List<String> expressionString = new ArrayList<>(List.of(scanner.nextLine().split(" ")));
        for (String item : expressionString) {
            expression.add(Integer.valueOf(item));
        }
        return expression;
    }

    public static List<String> scanConsoleString() {
        System.out.print("Введите строку: ");
        return new ArrayList<>(List.of(new Scanner(System.in).nextLine().split("")));
    }

    public static List<String> readFileInLine(String path, String splitter) throws IOException {
        return new ArrayList<>(List.of(new BufferedReader(new FileReader(path)).readLine().split(splitter)));

    }

    public static List<String> readFileInLine(String path) throws IOException {
        return readFileInLine(path, "");
    }

    public static List<List<Integer>> createMatrixFromTripleData(List<TripleData<Integer, Integer, Integer>> data) {
        List<List<Integer>> matrix = new ArrayList<>(); // нужно посчитать колличество вершин
        for (int i = 0; i < data.size(); i++) {
            matrix.add(new ArrayList<>(Collections.nCopies(data.size(), 0))); // заполняем 0 матрицу n*n
        }
        for (TripleData<Integer, Integer, Integer> item : data) {
            matrix.get(item.first).set(item.second, item.weight);
        }
        return matrix;
    }

    public static void outMatrix(List<List<Integer>> matrix) {
        for (List<Integer> line : matrix) {
            for (Integer item : line) {
                int outItem = item; // != 0 ? 1 : 0;
                System.out.print(outItem + " ");
            }
            System.out.println();
        }
        System.out.println("---------------");
    }

    public static void outGraph(List<Point> graph) {
        for (Point point : graph) {
            System.out.print(point + " ");
        }
    }

    public static List<List<Integer>> numerateMatrix(List<List<Integer>> matrix) {  /// Сделали массив из массивов, элементы которого являются точки  которые можно попасть из текущего положения
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < matrix.size(); i++) {
            graph.add(new ArrayList<>());
            for (int j = 0; j < matrix.get(i).size(); j++) {
                if (matrix.get(i).get(j) == 1) { // заполняем элементами, в которые можно попасть из текущего элмента графа
                    graph.get(i).add(j);
                }
            }
        }
        return graph;
    }

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

    public static List<TripleData<Integer, Integer, Integer>> readInLinesFromFile(String path) throws IOException {
        return convertToTripleData(readMatrixFromFile(path));
    }

    private static List<TripleData<Integer, Integer, Integer>> convertToTripleData(List<List<Integer>> data) {
        List<TripleData<Integer, Integer, Integer>> tripleDataList = new ArrayList<>();
        for (List<Integer> line : data) {
            tripleDataList.add(new TripleData<>(line.get(0), line.get(1), line.get(2)));
        }
        return tripleDataList;
    }


    public static List<Integer> fillArray(int size) {
        List<Integer> array = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            array.add(0);
        }
        return array;
    }

    public static List<Integer> generateNumeratedArray(int start, int finish) {
        List<Integer> numbersArray = new ArrayList<>();
        for (int i = start; i < finish; i++) {
            numbersArray.add(i);
        }
        return numbersArray;
    }


}
