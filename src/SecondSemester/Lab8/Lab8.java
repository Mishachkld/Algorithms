package SecondSemester.Lab8;

import Tools.HelpClasses.Graph;
import Tools.Helper;

import java.io.IOException;
import java.util.List;

// Орентированный граф используется V - множество вершни E - ребра
public class Lab8 {
    public static void main(String[] args) throws IOException {
        String path = "src/SecondSemester/Lab8/input.txt";
        List<List<Integer>> matrix = Helper.readMatrixFromFile(path);
        Graph graph = new Graph(matrix);
        graph.dekstra(0);
        System.out.println(graph);
    }
}
