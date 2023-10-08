package Tasks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task7 {
    public static void main(String[] args) { // 1 43 12 78 11 93 5 256 27 555 1 43 12 78 11 93 5 256 27 555 1 43 12 78 11 93 5 256 27 5551 43 12 78 11 93 5 256 27 555 1 43 12 78 11 93 5 256 27 555 43 12 78 11 93 5 256 27 555 1 43 12 78 11 93 5 256 27 55 43 12 78 11 93 5 256 27 5 43 12 78 11 93 5 256 27 555 1 43 12 78 11 93 5 256 27 5555 1 43 12 78 11 93 5 256 27 55
        List<Integer> expression = Task1.scanConsole();
        shallSort(expression);
        System.out.println(expression);
    }

    public static void shallSort(List<Integer> elements) {
        int step = elements.size() / 2;
        int counter = 0;
        while (step >= 1) {
            for (int i = 0; i < elements.size() - step; i += step)
                for (int j = i; j < elements.size(); j += step) {
                    if (elements.get(i) > elements.get(j))
                        Collections.swap(elements, i, j);
                //    System.out.println(++counter + ". " + elements);
                }
            step /= 2;
        }
    }

}
