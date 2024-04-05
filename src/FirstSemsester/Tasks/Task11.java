package FirstSemsester.Tasks;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Task11 {  /// Сложность алгоритма: Худшее время О(n^2) Лучшее время: O(n*logn)
    public static void main(String[] args) {  /// 1455 45 64 6548 84 45 86 465 456123 123
        /// 5 11 6 7
        List<Integer> expression = Task1.scanConsole();
        List<Integer> expression1 = new ArrayList<>(expression);
        int len = expression.size() - 1;
        quickSort(expression, 0, len);
        System.out.println(expression);
        System.out.println(quickSort(expression1));
    }

    public static List<Integer> quickSort(List<Integer> expression) {
        if (expression.size() < 2)  /// если длина массива меньше двух, то массив отсортирован
            return expression;

        int rootItem = expression.get(0); // выбираем опорный элемент
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int i = 1; i < expression.size(); i++) {
            int item = expression.get(i);
            if (rootItem > item) // если опопрный элемент больше текущего, то добавляем его в л/ч
                left.add(item);
            else // если меньше - в л.ч.
                right.add(item);
        }
        expression = quickSort(left); /// делаем все тоже самое с л.ч. и п.ч. наших массивов
        expression.add(rootItem);
        expression.addAll(quickSort(right)); // склеваем все в один массив и возвращаем

        return expression;
    }


    public static void quickSort(List<Integer> expression, int start, int end) {
        if (!expression.isEmpty()) {
            int i = start;
            int j = end;
            int midItem = expression.get((start + end) / 2); /// выбираем элемент, от которого будем отталкиваться
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
}
