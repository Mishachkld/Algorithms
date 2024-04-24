package SecondSemester.Lab9;

import Tools.HelpClasses.Graph;
import Tools.Helper;

import java.io.IOException;

public class Lab9 {
    public static void main(String[] args) throws IOException { // сложность V*E
        String path = "src/SecondSemester/Lab9/input.txt";
        Graph graph = new Graph(Helper.readMatrixFromFile(path));
        int startPoint = 0;
        graph.bellmanFord(startPoint);
        System.out.println(graph);
    }
}
