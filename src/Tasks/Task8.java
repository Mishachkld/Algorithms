package Tasks;

import java.util.ArrayList;
import java.util.List;

public class Task8 {
    public static void main(String[] args) { // 1371375157 243959293 479561035 5 276 42 3 0 1 5
        List<String> expression = Task1.scanConsoleString();
        System.out.println(radixSort(expression));
    }

    public static List<Integer> radixSort(List<String> elements) {
        List<List<Integer>> intArray = new ArrayList<>();
        List<Integer> resultArray = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            intArray.add(new ArrayList<>());
        int maxSizeOfItem = 0;
        for (String item : elements) {  // узнаем длину самого большого по разрядам числа
            resultArray.add(Integer.valueOf(item));
            if (maxSizeOfItem < item.length()) {
                maxSizeOfItem = item.length();
            }
        }


        for (int i = 0; i < maxSizeOfItem; i++) {
            for (Integer item : resultArray) {
                int itemIndex = (int) ((item / Math.pow(10, i)) % 10);
                intArray.get(itemIndex).add(item);
            }
            resetArrays(intArray, resultArray);
        }
        return resultArray;
    }

    private static void resetArrays(List<List<Integer>> array, List<Integer> resultArray) {
        resultArray.clear();
        for (List<Integer> indexArray : array)
            if (!indexArray.isEmpty()) {
                resultArray.addAll(indexArray);
            }
        array.clear();
        for (int i = 0; i < 10; i++)
            array.add(new ArrayList<>());
    }

}
