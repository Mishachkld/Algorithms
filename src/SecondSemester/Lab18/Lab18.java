package SecondSemester.Lab18;

import java.util.Arrays;

public class Lab18 {

    // Дано множество целых чисел S и целевое число t.
    // Требуется определить, существует ли подмножество S' множества S, сумма элементов которого равна целевому числу t.

    // Задача о суммах подмножеств является классической комбинаторной задачей.
    // Для данного множества целых чисел S мы хотим найти подмножество S', сумма элементов которого равна заданному числу t.
    // Эта задача решена перебором всех возможных подмножеств множества S.

    // O(n*S), где n - длина массива nums, а S - целевая сумма.
    public static boolean dinamicSubsetSum(int[] nums, int sum) {
        int sizeNums = nums.length;
        boolean[][] dp = new boolean[sizeNums + 1][sum + 1];
        for (int i = 0; i <= sizeNums; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i <= sizeNums; i++) {
            for (int j = 1; j <= sum; j++) {
                if (nums[i - 1] > j) {                      // если текущий элемент больше текущей суммы, то мы берем значение предыдущего элемента
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j - nums[i - 1]] || dp[i - 1][j];  //можно ли получить сумму, если тек. элемент будет ключен
                                                                            // или можно ли получить сумму без текущего элемента
                }
            }
        }
        return dp[sizeNums][sum]; /// итоговый реультат
    }

    // O(n log n), где n - количество элементов в массиве.
    public static boolean fatCalculateSum(int[] nums, int S) {
        Arrays.sort(nums); // Сортируем массив

        int sum = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (sum + nums[i] <= S) {
                sum += nums[i];
            }
        }

        return sum == S;
    }

    public static void main(String[] args) {
        int[] nums = {3, 34, 3, 12, 5, 2};
        int sum = 3;
        System.out.println((dinamicSubsetSum(nums, sum)) ? "Существует" : "Не существует");
    }

}
