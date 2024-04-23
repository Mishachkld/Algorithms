package SecondSemester.Lab10;

import Tools.Helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lab10 {

    private static void findEulorianCycleRecursive(List<List<Integer>> graph, int v, List<Integer> circuit) {
        for (int u = 0; u < graph.get(v).size(); u++) {
            if (graph.get(v).get(u) != 0) {
                graph.get(u).set(v, 0);
                graph.get(v).set(u, 0);
                findEulorianCycleRecursive(graph, u, circuit);
            }
        }
        circuit.add(v);
    }

    public static void outEulorianCycle(List<Integer> circuit) {
        for (Integer item : circuit) {
            System.out.print(item + " -> ");
        }
        System.out.println(circuit.get(circuit.size() - 1));
    }

    public static List<Integer> findEulorianCycle(int startPoint, List<List<Integer>> graph) {
        List<Integer> circuit = new ArrayList<>();
        findEulorianCycleRecursive(graph, startPoint, circuit);
        Collections.reverse(circuit);
        return circuit;
    }

    public static void main(String[] args) throws IOException {
        String path = "src/SecondSemester/Lab10/input.txt";
        outEulorianCycle(findEulorianCycle(0, Helper.readMatrixFromFile(path)));
    }
}
