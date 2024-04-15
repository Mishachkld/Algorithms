package SecondSemester;

import java.util.Collections;
import java.util.List;

import static Tools.Helper.generateShuffleArray;

public class Lab6 {

    public static void main(String[] args) {
        List<Integer> numbers = generateShuffleArray(1, 5);
        System.out.println(numbers);
        int index = 0;
        for (int i = 0; i < numbers.size() - 1; i++) {
            int min = numbers.get(i + 1);
            for (int j = i + 1; j < numbers.size(); j++) {
                if (numbers.get(j) < min) {
                    min = numbers.get(j);
                    index = j;
                }
            }
            if (numbers.get(i) > min) {
                Collections.swap(numbers, i, index);
            }
        }
        System.out.println(numbers);
    }
}
