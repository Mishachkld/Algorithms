package Tasks;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task11 {
    public static void main(String[] args) {  /// 1455 45 64 6548 84 45 86 465 456123 123
        /// 5 11 6 7
        List<Integer> expression = Task1.scanConsole();
        List<Integer> expression1 = new ArrayList<>(expression);
        int len = expression.size() - 1;
        quickSort(expression, 0 , len);
        System.out.println(expression);
        System.out.println(quickSort(expression1));
    }




    public static void quickSort(List<Integer> expression, int start, int end) {
        if (!expression.isEmpty()) {
            int i = start;
            int j = end;
            int midItem = expression.get((start + end) / 2);
            while (i <= j) {
                while (expression.get(i) < midItem) {
                    i++;
                }
                while (expression.get(j) > midItem) {
                    j--;
                }
                if (i <= j) {
                    Collections.swap(expression, i, j);
                    i++;
                    j--;
                }
            }
            if (i < end)
                quickSort(expression, i, end);
            if (j >= start)
                quickSort(expression, start, j);
        }
    }

    public static List<Integer> quickSort(List<Integer> expression) {
        if (expression.size() < 2)
            return expression;

        int rootItem = expression.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (Integer item : expression) {
            if (rootItem > item)
                left.add(item);
            else
                right.add(item);
        }
        //left.add(rootItem);
        expression = quickSort(left);
        expression.addAll(right);
        // System.out.println(expression);
        return expression;
    }

}
