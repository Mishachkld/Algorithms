package SecondSemester.Lab13;

import Tools.Helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//алгоритм Бойера-Мура для поиска по образцу
public class Lab13 {
    // поиск строки с конца искомой строки
    public static boolean BoyerMurrrrrr(String findSubString, List<String> text) {
        List<Integer> distance = new ArrayList<>(Collections.nCopies(256, findSubString.length())); // 256 все символы в акси таблицы
        for (int i = 0; i < findSubString.length(); i++) {
            distance.set(findSubString.charAt(i), findSubString.length() - i - 1);
        }
        int lenSubString = findSubString.length() - 1;
        int j = findSubString.length() - 1;
        int i = findSubString.length() - 1;
        boolean isFindSubString = false;
        while (i < text.size()){
            if(text.get(i).charAt(0) == findSubString.charAt(j)){
                i--; j--;
                if (j < 0){
                    isFindSubString = true;
                    break;
                }
            }
            else {
                if (j == lenSubString){
                    i += distance.get(text.get(i).charAt(0));
                }else{
                    i += distance.get(text.get(j).charAt(0));
                }
            }

        }
        return isFindSubString;
    }

    public static void main(String[] args) throws IOException {
        String path = "src/SecondSemester/Lab13/input.txt";
        List<String> text = Helper.readFileInLine(path, "");
        String subString = "eeef";
        boolean isFindSubString = BoyerMurrrrrr(subString, text);
        if (isFindSubString){
            System.out.println("We found SubString ->" + subString);
        }else{
            System.out.println("SubString -> " + subString +" <- not Found!::::((((");
        }
    }
}
