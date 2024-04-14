package SecondSemester.Lab5;

import SecondSemester.Lab2.Lab2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import static Tools.Helper.*;

public class Lab5 {
    // + функции которые преобразоывавают в numeratedGraph
    // + used; components; stack
    private static void DFS1(int v, List<List<Integer>> graph, Stack<Integer> stack) {
        used.set(v, true);
        for (Integer point : graph.get(v)) {
            if (!used.get(point)) {
                DFS1(point, graph, stack);
            }
        }
        stack.add(v);
    }

    private static void DFS2(int v, List<List<Integer>> graph) {
        used.set(v, true);
        components.add(v);
        for (Integer neighbor : graph.get(v)) {
            if (!used.get(neighbor)) {
                DFS2(neighbor, graph);
            }
        }
    }

    private static List<List<Integer>> sccComponents(List<List<Integer>> matrix) {
        List<List<Integer>> sccComponents = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        int size = matrix.size();
        List<List<Integer>> graph = numerateMatrix(matrix);
        List<List<Integer>> graphH = numerateMatrix(createTranspMatrix(matrix));  // получили транспорнированную матрицу и перевели ее в граф
        for (int i = 0; i < size; i++) {
            if (!used.get(i)){
                DFS1(i, graph, stack);
            }
        }
        used = new ArrayList<>(Collections.nCopies(size, false));
        while (!stack.empty()){
            int point = stack.pop();
            if (!used.get(point)){
                components = new ArrayList<>();
                DFS2(point, graph);
                sccComponents.add(components);
            }
        }
        return sccComponents;
    }


    private static List<List<Integer>> createTranspMatrix(List<List<Integer>> matrix) {
        int size = matrix.size();
        List<List<Integer>> matrixT = new ArrayList<>(); // Collections.nCopies(size, new ArrayList<>(Collections.nCopies(size, null))) создает несколько копий, но при этом они ссылаются на один и тотже элемент
        for (int i = 0; i < size; i++) {
            matrixT.add(new ArrayList<>());
            for (int j = 0; j < size; j++) {
                matrixT.get(i).add(null);
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Integer elementFromMatrix = matrix.get(j).get(i);
                matrixT.get(i).set(j, elementFromMatrix);
            }
        }
        return matrixT;
    }

    public static void main(String[] args) throws IOException {
        final String path = "src/SecondSemester/Lab5/input.txt";
        List<List<Integer>> numeratedGraph = readMatrixFromFile(path);
        outMatrix(sccComponents(numeratedGraph));
    }
}
