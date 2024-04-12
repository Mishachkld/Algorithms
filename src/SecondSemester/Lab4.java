package SecondSemester;

import SecondSemester.Lab2.Lab2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static SecondSemester.Lab2.Lab2.readMatrixFromFile;
import static SecondSemester.Lab3.*;

public class Lab4 {

    private static List<List<Integer>> numeratedGraph;

    public static void DFS(int positionOfPoint) {
        used.set(positionOfPoint, true);
        components.add(positionOfPoint);
        for (Integer element : numeratedGraph.get(positionOfPoint)) {
            if (!used.get(element)) {
                DFS(element);
            }
        }
    }

    public static void findComponents(int numbers, List<List<Integer>> numeratedGraph) {
        used = new ArrayList<>(Collections.nCopies(numbers, false));
        for (int i = 0; i < numbers; i++) {
            if (!used.get(i)) {
                components.clear();
                DFS(i);
                System.out.print("Компомненты: ");
                for (Integer elements : components) {
                    System.out.print(elements + " ");
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        numeratedGraph = numerateMatrix(readMatrixFromFile(Lab2.PATH_TO_INPUT_FILE));
        findComponents(numeratedGraph.size(), numeratedGraph);
    }
}
