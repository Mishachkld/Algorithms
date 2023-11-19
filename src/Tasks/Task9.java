package Tasks;

import java.util.Collections;
import java.util.List;

public class Task9 { // heapSort Пирамидальная

    public static void main(String[] args) {   /// 14 61 85 24 74 26 17 50 40 45 21 32 59 58 13
        List<Integer> expression = Task1.scanConsole();
        heapSort(expression);
        System.out.println(expression);
    }

    private static void heapSort(List<Integer> expression) {
        buildMaxHeap(expression);  /// делаем правильное дерево (потомок меньше родителя)
        int size = expression.size() - 1;
        for (int i = size; i > 0; i--) {
            Collections.swap(expression, 0, i);  // самый большой элемент перемещаем из начала массива в конец и далее его уже не рассматриваем
            heapify(expression, 0, --size); // опять выполняем процедуру построения правильного дерева
        }
    }

    private static void heapify(List<Integer> expression, int i, int size) {  /// в начало массива засовываем самый большой элемент
        // и делаем так, что бы каждый птомок был меньше чем его родитель
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int max = i;
        /// сравниваем и кладем самый большой элемент в корень тек. Дерева
        if (left < size && expression.get(left) > expression.get(i))
            max = left;
        if (right < size && expression.get(right) > expression.get(max))
            max = right;
        if (max != i){
            Collections.swap(expression, max, i);
            heapify(expression, max, size);
        }

    }

    private static void buildMaxHeap(List<Integer> expression) {
        for (int i = expression.size() / 2; i >= 0; i--) {
            heapify(expression, i, expression.size());
        }
    }

    public static void sort(List<Integer> elements) {  /// 1 3 23 3243 4 5 9 1 56 43 45 78
        for (int size = elements.size() - 1; size > 0; size--)
            Collections.swap(elements, size, elements.indexOf(Collections.max(elements.subList(0, size + 1)))); // получаем максимальный элимент в начало массива


    }
}
