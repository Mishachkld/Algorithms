package SecondSemester.Lab13;

import Tools.Helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// алгоритм Бойера-Мура для поиска по образцу
public class Lab13 {
    // поиск строки с конца искомой строки
    public static boolean BoyerMurrrrrr(String findSubString, List<String> text) {
        List<Integer> distance = new ArrayList<>(Collections.nCopies(256, findSubString.length())); // 256 все символы в аcки таблицы
        for (int i = 0; i < findSubString.length() - 1; i++) { // устанавливаем расстояние
            distance.set(findSubString.charAt(i), findSubString.length() - i - 1);
        }
        int lenSubString = findSubString.length() - 1;
        int j = findSubString.length() - 1;
        int i = findSubString.length() - 1;
        boolean isFindSubString = false;
        // поиск строки с конца искомой строки
        while (i < text.size()) {
            if (text.get(i).charAt(0) == findSubString.charAt(j)) {
                i--;
                j--;
                if (j < 0) {
                    System.out.println(i + 1);
                    isFindSubString = true;
                    break;
                }
            } else {  // происходит смещение на нужное число индексов
                if (j == lenSubString) {
                    i += distance.get(text.get(i).charAt(0));
                } else {
                    i += distance.get(text.get(j).charAt(0));
                }
            }
        }
        return isFindSubString;
    }

    public static void main(String[] args) throws IOException { //
        String path = "src/SecondSemester/Lab13/input.txt";
        List<String> text = Helper.readFileInLine(path, "");
        String subString = "Mishach send hello";
        boolean isFindSubString = BoyerMurrrrrr(subString, text);
        if (isFindSubString) {
            System.out.println("We found SubString ->" + subString);
        } else {
            System.out.println("SubString -> " + subString + " <- not Found!::::((((");
        }
        // сложность - O(m + K) где K - длина алфавита, m - размер искомой строки(шалблона)
    }
}
