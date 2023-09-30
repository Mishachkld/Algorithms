package Tasks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Task6 {
    public static void main(String[] args) {   /// 1 5 69 8 2 12 15 256 1000 1 12 55
        System.out.print("Введите строку: ");
        Scanner scanner = new Scanner(System.in);
        List<String> expression = new ArrayList<>(List.of(scanner.nextLine().split(" ")));
        chooseSort(expression);
        System.out.println(expression);

    }

    public static void chooseSort(List<String> expression){
        for (int i = 0; i < expression.size() - 1; i++) {
            int min = i;
            for (int j = i + 1; j < expression.size(); j++)
                if (Integer.parseInt(expression.get(j) ) < Integer.parseInt(expression.get(min)))
                    min = j;
            Collections.swap(expression, min, i);
        }
    } 
}
