package SecondSemester.Lab7;

import Tools.HelpClasses.TripleData;
import Tools.Helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static Tools.Helper.*;

public class Lab7 {
    private static List<TripleData<Integer, Integer, Integer>> prim(List<List<Integer>> matrix) {
        int n = matrix.size();
        used = new ArrayList<>(Collections.nCopies(n, false));
        List<TripleData<Integer, Integer, Integer>> mst = new ArrayList<>();
        used.set(0, true);
        for (int i = 0; i < n; i++) {
            if (matrix.get(0).get(i) != -1) {
                mst.add(new TripleData<>(0, i, matrix.get(0).get(i)));
                break;
            }
        }
        for (int iter = 0; iter < n - 1; iter++) {
            int u = -1;
            int v = -1;
            int minWeight = Integer.MAX_VALUE;
            // поиск ребра с минимальным весом, соеденящим псещенную и непосещеную вершины
            for (int i = 0; i < n; i++) {
                if (used.get(i)) {
                    for (int j = 0; j < n; j++) {
                        int item = matrix.get(i).get(j);
                        if (!used.get(j) && item != -1 && item != 0){
                            if (item < minWeight){
                                minWeight = item;
                                u = i;
                                v = j;
                            }
                        }
                    }
                }
            }
            mst.add(new TripleData<>(u, v, minWeight));
            used.set(v, true);
        }
        return mst;
    }

    public static void main(String[] args) throws IOException {
        String path = "src/SecondSemester/Lab7/input.txt";
        List<List<Integer>> matrix = Helper.readMatrixFromFile(path);
        System.out.println(prim(matrix));

    }
}
