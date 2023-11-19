package Tasks;

import java.util.ArrayList;
import java.util.List;

public class Task10 {  /// 4545456 4545 55 5 58 9127 789 87 98 45 4 454 4 84 89849 49 874 98 79 7874 8 49 49 849 8
    public static void main(String[] args) {
        List<Integer> expression = Task1.scanConsole();
        System.out.println(mergeSort(expression));

    }

    private static List<Integer> merge(List<Integer> left, List<Integer> right) {
        List<Integer> array = new ArrayList<>();
        while (!left.isEmpty() && !right.isEmpty())
            if (left.get(0) < right.get(0))
                array.add(left.remove(0));

            else
                array.add(right.remove(0));
        array.addAll(left);
        array.addAll(right);
        return array;
    }

    public static List<Integer> mergeSort(List<Integer> expression) {
        int half = expression.size() / 2;
        if (expression.size() < 2) {
            return expression;
        }
        List<Integer> left = new ArrayList<>(expression.subList(0, half));
        expression.removeAll(left);
        return merge(mergeSort(left), mergeSort(expression));

    }
}
