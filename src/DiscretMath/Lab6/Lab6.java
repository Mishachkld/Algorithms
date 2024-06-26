package DiscretMath.Lab6;


import SecondSemester.Lab9.Lab9;
import Tools.HelpClasses.Graph;
import Tools.HelpClasses.TripleData;
import Tools.Helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static Tools.Helper.readInLinesFromFile;

public class Lab6 {

    private static final int SIZE_OF_GRAPH = 8;
    private static final int COUNT_OF_EDGES = 13;

    private static List<List<Integer>> createMatrixFromTripleData(List<TripleData<Integer, Integer, Integer>> data) {
        List<List<Integer>> matrix = new ArrayList<>(); // нужно посчитать колличество вершин
        for (int i = 0; i < SIZE_OF_GRAPH; i++) {
            matrix.add(new ArrayList<>(Collections.nCopies(SIZE_OF_GRAPH, 0))); // заполняем 0 матрицу n*n
        }
        for (TripleData<Integer, Integer, Integer> item : data) {
            matrix.get(item.first).set(item.second, item.weight);
        }
        Helper.outMatrix(matrix);
        return matrix;
    }

    private static List<List<Integer>> convertToNonOrentir(List<List<Integer>> data) {
        List<List<Integer>> nonOrentirMatrix = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            nonOrentirMatrix.add(new ArrayList<>(Collections.nCopies(data.size(), 0)));
        }

        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.get(i).size(); j++) {
                int value = 0;
                if (data.get(i).get(j) != 0) {
                    value = data.get(i).get(j);
                }
                else if (data.get(j).get(i) != 0){
                    value = data.get(j).get(i);
                }
                nonOrentirMatrix.get(i).set(j, value);
            }
        }
        return nonOrentirMatrix;
    }

    public static boolean isElerGraph(List<List<Integer>> matrix) {
        boolean isEler = true;
        List<Integer> degrees = new ArrayList<>(Collections.nCopies(SIZE_OF_GRAPH, 0));
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                if (matrix.get(i).get(j) != 0) {
                    degrees.set(i, degrees.get(i) + 1);
                }
            }
        }
        for (Integer degree : degrees) {
            if (degree % 2 != 0) {
                isEler = false;
                break;
            }
        }
        System.out.println(degrees);
        return isEler;
    }

    public static List<List<Integer>> createMatrixIncidence(List<TripleData<Integer, Integer, Integer>> data) {
        List<List<Integer>> incidentMatrix = new ArrayList<>();
        for (int i = 0; i < SIZE_OF_GRAPH; i++) {
            incidentMatrix.add(new ArrayList<>(Collections.nCopies(data.size(), 0)));
        }
        for (int i = 0; i < data.size(); i++) {
            incidentMatrix.get(data.get(i).first).set(i, 1);
            incidentMatrix.get(data.get(i).second).set(i, -1);
        }
        System.out.println("Инциндентная матрица:");
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < SIZE_OF_GRAPH; j++) {
                System.out.print(incidentMatrix.get(j).get(i) + " ");
            }
            System.out.println();
        }
        System.out.println();
        return incidentMatrix;
    }

    public static void main(String[] args) throws IOException {
        String path = "src/DiscretMath/Lab6/input.txt";
        List<TripleData<Integer, Integer, Integer>> data = readInLinesFromFile(path);
        System.out.println("Матрица смежности:");
        List<List<Integer>> matrix = createMatrixFromTripleData(data);

        createMatrixIncidence(data);
        List<List<Integer>> nonOrentirMatrix = convertToNonOrentir(matrix);

        System.out.println("Является ли граф Эйлеровым: " + isElerGraph(matrix) + "\n"); // граф не Эйлеров

        System.out.println("Кратчайшие расстояния между вершинами: ");
        Graph graph = new Graph(nonOrentirMatrix);
        for (int i = 0; i < nonOrentirMatrix.size(); i++) {
            System.out.print(i + ": ");
            graph.dekstra(i);
        }
    }

}
