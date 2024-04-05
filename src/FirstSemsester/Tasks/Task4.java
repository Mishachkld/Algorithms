package FirstSemsester.Tasks;

import java.util.*;

public class Task4 {

    public static final double DISTANCE = 1.247;

    public static void main(String[] args) {   /// 5 1 4 2 8 255 64 8 74 9 6 7 5 6 2 5 8 6
        ArrayList<Integer> elements = new ArrayList<>();
        System.out.println("Введите строку: ");
        Scanner scanner = new Scanner(System.in);
        List<String> elementsString = Arrays.asList(scanner.nextLine().split(" "));
        for (String integer : elementsString) {
            elements.add(Integer.valueOf(integer));
        }

        double lenOfArray = (elements.size() / DISTANCE);
        while (lenOfArray > 1) {
            for (int i = 0; i < elements.size() - lenOfArray; i++) {
                int secondElement = (int) (i + lenOfArray);
                if (elements.get(i) < elements.get(secondElement))
                    Collections.swap(elements, i, secondElement);
            }
            lenOfArray = (lenOfArray / DISTANCE);
        }
        System.out.println(elements);
    }
}
