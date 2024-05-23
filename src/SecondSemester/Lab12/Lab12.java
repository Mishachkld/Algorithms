package SecondSemester.Lab12;

import Tools.Helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lab12 {

    public static List<Integer> KMPSearch(String text, String subString) {
        List<Integer> findIndexes = new ArrayList<>();

        int sizeSubString = subString.length();
        int sizeText = text.length();

        int[] lps = new int[sizeSubString];
        computeLPSArray(subString, sizeSubString, lps);

        int indexText = 0;
        int indexSubString = 0;
        while (indexText < sizeText) {
            if (subString.charAt(indexSubString) == text.charAt(indexText)) {
                indexText++;
                indexSubString++;
            }
            if (indexSubString == sizeSubString) { // если длина оказалась равной длине нашей подстроки (т.е. мы нашли в тексте нашу подстроку)
                                                   // то добавляем ее в результат.
                findIndexes.add(indexText - indexSubString);
                indexSubString = lps[indexSubString - 1];
            } else if (indexText < sizeText && subString.charAt(indexSubString) != text.charAt(indexText)) {
                if (indexSubString != 0) {
                    indexSubString = lps[indexSubString - 1];
                } else {
                    indexText = indexText + 1;
                }
            }
        }

        if (findIndexes.isEmpty()) {
            System.out.println("Искому подстроку не нашли :(((");
        }
        return findIndexes;
    }

    private static void computeLPSArray(String pattern, int M, int[] lps) { // вычисляет массив длин префиксов и суффиксов для заданного подстроки
        int len = 0;
        int i = 0;
        lps[i++] = 0; ///

        while (i < M) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }

    public static void main(String[] args) {
        String text = "AABABCABABBABDABABCABABABACDABABCABABABCABABAB";
        String pattern = "ABABCABAB";
        System.out.println(KMPSearch(text, pattern));
    }
}
