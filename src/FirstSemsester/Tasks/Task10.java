package FirstSemsester.Tasks;

import java.util.ArrayList;
import java.util.List;
// Сложность O(n^2)
public class Task10 {  /// 4545456 4545 55 5 58 9127 789 87 98 45 4 454 4 84 89849 49 874 98 79 7874 8 49 49 849 8
    public static void main(String[] args) {
        List<Integer> expression = Task1.scanConsole();
        System.out.println(expression.size());
        System.out.println(mergeSort(expression).size());

    }

    private static List<Integer> merge(List<Integer> left, List<Integer> right) {
        List<Integer> array = new ArrayList<>();
        while (!left.isEmpty() && !right.isEmpty())
            if (left.get(0) < right.get(0))
                // если в левом массиве элемент меньше, то перемещаем его в массив с результатом
                array.add(left.remove(0));
            else
                // иначе делаем наоборот: перемещаем элемент из правой части в результирующий
                array.add(right.remove(0));
        array.addAll(left);
        array.addAll(right);
        return array; // возвращаем реузультирующий + остатки из левой и правых частей
    }

    public static List<Integer> mergeSort(List<Integer> expression) {
        if (expression.size() < 2) { // если размер массива <2 то он уже отсортирован и возвращаем его
            return expression;
        }
        int half = expression.size() / 2; // ищем индекс середины
        List<Integer> left = new ArrayList<>(expression.subList(0, half)); /// берем левую часть из исходного массива до середины
        expression.subList(0, half).clear(); // и удалем эту левую часть из исходного, чтобы использовать ее как правую
        return merge(mergeSort(left), mergeSort(expression)); //запускаем рекурсию, передавая л.ч. и п.ч.

    }
}
