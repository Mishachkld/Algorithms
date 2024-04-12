package SecondSemester;

import SecondSemester.Lab2.Lab2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static Tools.Helper.numerateMatrix;
import static Tools.Helper.readMatrixFromFile;

public class Lab5 {
    // + функции которые преобразоывавают в numeratedGraph
    // + used; components; stack

    private static void DFS1(List<List<Integer>> graph, Stack<Integer> stack){

    }
    private static void DFS2(){

    }

    private static List<List<Integer>> sccComponents(List<List<Integer>> matrix){
        List<List<Integer>> sccComponents = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        return sccComponents;
    }

    public static void main(String[] args) throws IOException {
        List<List<Integer>> numeratedGraph = numerateMatrix(readMatrixFromFile(Lab2.PATH_TO_INPUT_FILE));


    }
}
