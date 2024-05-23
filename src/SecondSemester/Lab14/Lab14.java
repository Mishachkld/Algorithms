package SecondSemester.Lab14;

import java.util.ArrayList;
import java.util.List;

public class Lab14 {
    private final static int q = (int) Math.pow(2, 30); // любое число
    private final static int BASE_SIMPLE_NUMBER = 31;  // число от 1 до q-1

    public static int calculateHash(int start, int end, char[] sym) {
        int result = sym[start];
        for (int i = start; i < end - 1; i++) {
            result = ((result % q) * (BASE_SIMPLE_NUMBER % q) + (int) sym[i + 1]) % q;
        }
        return result;
    }

    public static List<Integer> rabinKarp(String findString, String text) {
        List<Integer> indexesOfFindSubstring = new ArrayList<>();
        int sizeOfText = text.length();
        int sizeOfFindString = findString.length();
        int hashOfText = calculateHash(0, sizeOfFindString, text.toCharArray());
        int hashOfFindString = calculateHash(0, sizeOfFindString, findString.toCharArray());

        for (int i = 0; i <= sizeOfText - sizeOfFindString; i++) {
            if (hashOfText == hashOfFindString &&
                    text.substring(i, i + sizeOfFindString).equals(findString)) { // если хэши совпали => мы сравниваем строки,
                indexesOfFindSubstring.add(i);                                    // если все совпало, то мы нашли искомую подстроку
            }
            if (i < sizeOfText - sizeOfFindString) {
                hashOfText = ((hashOfText - (text.charAt(i) * (int) Math.pow(BASE_SIMPLE_NUMBER, sizeOfFindString - 1) % q)) * BASE_SIMPLE_NUMBER +
                        text.charAt(i + sizeOfFindString)) % q;  // пересчитываем в хэш на основе предыдущего выcчета
                if (hashOfText < 0) {
                    hashOfText += q;
                }
            }
        }
        return indexesOfFindSubstring;
    }

    public static void main(String[] args) {
        String string = "AMOGUSПППfATCAM OGUSGCAMOGUSAGAMOGU SAGAG TATACAGTA AMOGUSCG";
        String subString = "AMOGUS";
        List<Integer> findSubStringArray = rabinKarp(subString, string);
        System.out.println(findSubStringArray);
        System.out.println(string);
        for (Integer index : findSubStringArray) {
            System.out.println(index + "->" + string.substring(index, index + subString.length()));
        }
    }

}