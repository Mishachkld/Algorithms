package Tools;

import Tools.HelpClasses.Point;

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

    public static List<String> readFile(String path) throws IOException {
        return new ArrayList<>(List.of(new BufferedReader(new FileReader(path)).readLine().split(" ")));
    }

    public static void outMatrix(List<List<Integer>> matrix) {
        for (List<Integer> line : matrix) {
            for (Integer item : line) {
                System.out.print(item + " ");
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


    public static List<Integer> generateShuffleArray(int start, int finish){
        List<Integer> numbersArray = new ArrayList<>();
        for (int i = start; i < finish; i++) {
            numbersArray.add(i);
        }
        Collections.shuffle(numbersArray);
        return numbersArray;
    }
}
