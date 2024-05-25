package SecondSemester.Lab17;

import java.util.Arrays;

public class Lab17 {

    //Дано множество предметов с заданными весами w_1, w_2, ..., w_n и n пустых ящиков.
    // Требуется распределить все предметы по ящикам таким образом, чтобы минимизировать количество использованных ящиков.
    // Каждый ящик имеет ограниченную вместимость C, и суммарный вес предметов в каждом ящике не должен превышать эту вместимость

    // функция для подсчета колличества использованных контейнеров
    public static int firstFit(int[] weights, int n, int containerSize) {  /// с - вместимость контейнера
        int quantityOfContainers = 0; // колличество использованных контейнеров
        int[] bin_rem = new int[n]; // массив хранит информацию о том, сколько свободного места осталось в каждом контейнере после размещения предметов.
        for (int i = 0; i < n; i++) { // проходимся по всем предметам
            int j;
            for (j = 0; j < quantityOfContainers; j++) {
                if (bin_rem[j] >= weights[i]) {
                    bin_rem[j] = bin_rem[j] - weights[i]; // пытаемся положить в текущий контейнер предмет (обновляем колличество места свободного в текущем контейнере)
                    break;
                }
            }
            if (j == quantityOfContainers) {
                bin_rem[quantityOfContainers] = containerSize - weights[i]; // если у нас появился не задействованный контейнер,
                quantityOfContainers++;                                     // то мы кладем предмет в текущую ячейку и делаем пересчет свободного места
            }
        }
        System.out.println(Arrays.toString(Arrays.stream(bin_rem).toArray()));
        return quantityOfContainers;
    }
    // худшая сложность - O(n^2), где n - количество предметов
    // Лучшая - O(n), где n - количество предметов.
    public static void main(String[] args) {
        int[] weights = {3, 5, 7, 4, 2};
        int containerSize = 10; // вместимость контейнера
        int countOfElements = weights.length;
        System.out.println("Необходимо " + firstFit(weights, countOfElements, containerSize) + " контейнера(ов)");

    }
}
