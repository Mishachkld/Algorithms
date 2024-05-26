package SecondSemester.Lab16;

import Tools.Helper;

import java.io.IOException;
import java.util.*;

public class Lab16 {

    private static List<List<Integer>> A;
    private static final List<Integer> thingsInBag = new ArrayList<>();
    private static List<Integer> w;

    public static void findAns(int k, int s) {
        if (A.get(k).get(s) == 0) {
            return;
        }
        if (Objects.equals(A.get(k - 1).get(s), A.get(k).get(s))) {
            findAns(k - 1, s);
        } else {   ///Если стоимость предмета k - 1 не равна стоимости предмета k для текущего s, то вызывается рекурсивно findAns() для предыдущего предмета k - 1 с уменьшенной вместимостью
            findAns(k - 1, s - w.get(k - 1)); // на вес предмета, который добавляется в рюкзак.
            thingsInBag.add(k - 1);                //  номер предмета k - 1 добавляется в список thingsInBag.
        }
    }

    public static void main(String[] args) throws IOException {
        String path = "src/SecondSemester/Lab16/input.txt";
        List<List<Integer>> dataFromFile = Helper.readMatrixFromFile(path);
        List<Integer> sizesArray = dataFromFile.get(0);
        w = dataFromFile.get(2); // набор весов
        List<Integer> p = dataFromFile.get(1);  // стоимость для соотвествующей вещи
        A = new ArrayList<>(); // матрица для динамического поиска вещей с масимальной стоимости

        int N = sizesArray.get(0);
        int W = sizesArray.get(1);
        for (int i = 0; i < N + 1; i++) {
            A.add(new ArrayList<>(Collections.nCopies(W + 1, 0))); // часть алогритма, заполняем нулями
        }
        for (int k = 1; k <= N; k++) {
            for (int s = 1; s <= W; s++) { // Перебираем все вместимости для каждого k
                if (s >= w.get(k - 1)) {  // если вмещается , то выбераем - класть его в рюкзак или нет
                    A.get(k).set(s, Math.max(A.get(k - 1).get(s), A.get(k - 1).get(s - w.get(k - 1)) + p.get(k - 1)));
                } else {
                    A.get(k).set(s, A.get(k - 1).get(s));
                }
            }
        }
        findAns(N, W);
        System.out.println(thingsInBag);
        System.out.println("Cost : Weight");
        for (Integer index : thingsInBag) {
            System.out.println(p.get(index) + " : " + w.get(index));
        }
    }
}