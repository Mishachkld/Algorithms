package Tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Task4 {

    public static final double DISTANCE = 1.247;

    public static void main(String[] args) {   /// 5 1 4 2 8
        ArrayList<Integer> elements = new ArrayList<>();
        System.out.println("Введите строку: ");
        Scanner scanner = new Scanner(System.in);
        /*while (scanner.hasNext())
            elements.add(scanner.nextInt());*/
        List<String> elementsString = Arrays.asList(scanner.nextLine().split(" "));
        for (String integer : elementsString) {
            elements.add(Integer.valueOf(integer));
        }


        double lenOfArray = (elements.size() / DISTANCE);
        while (lenOfArray > 1) {
            for (int i = 0; i < lenOfArray; i++) {
                for (int j = 0; j < i; j++) {
                    if (elements.get(i) > elements.get((j))) {
                        int changedElement = elements.get(i);
                        elements.set(i, elements.get(j));
                        elements.set(j, changedElement);
                    }
                }
            }
            lenOfArray = (lenOfArray / DISTANCE);
        }
        System.out.println(elements);

    }
}
