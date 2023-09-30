package Tasks;

import java.util.*;

public class Task5 {

    public static void main(String[] args) { // 1 5 69 8 2 12 15 256 1000 1 12 55           // 5 2 4 3 1

        System.out.print("Введите строку: ");
        Scanner scanner = new Scanner(System.in);
        List<String> expression = new ArrayList<>(List.of(scanner.nextLine().split(" ")));
        selectedSort(expression);
        System.out.println(expression);
    }

    public static void selectedSort(List<String> expression) {  /// сложность N^2
        for (int i = 1; i < expression.size(); i++)
            for (int j = i - 1; j >= 0; j--)
                if (Integer.parseInt(expression.get(j)) > Integer.parseInt(expression.get(j + 1)))
                    Collections.swap(expression, j, j + 1);
    }
}
