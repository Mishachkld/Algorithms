package SecondSemester;

import java.util.ArrayList;
import java.util.List;

public class Lab14 {
    public final static int q = Integer.MAX_VALUE;

    public final static int BASE = 31;

    public static int gornerScheme(char[] sym, int start, int end) {
        int result = (int) (sym[start]);
        for (int i = start; i < end - 1; i++) {
            result = result * BASE + (int) sym[i + 1];
        }
        return result;
    }

    public static int calculateHash(char[] sym, int start, int end) {
        return gornerScheme(sym, start, end) % q;
    }


    public static List<Integer> rabinKarp(String findString, String string) {
        List<Integer> indexesOfFindSubstring = new ArrayList<>();
        // длина строк
        int sizeOfString = string.length();
        int sizeOfFindString = findString.length();
        // вычисляем хэш строк
        int hashOfString = calculateHash(string.toCharArray(), 0, sizeOfString);
        //string.substring(0, sizeOfString).hashCode();  // нужно использовать кольцевую хэшфункцию
        int hashOfFindString = calculateHash(findString.toCharArray(), 0, sizeOfFindString);
        for (int i = 0; i < (sizeOfString - sizeOfFindString) - 1; i++) {
            boolean one = (hashOfString == hashOfFindString);
            boolean two = string.substring(i, i + sizeOfFindString).equals(findString);
            if (one && two) { // если строки совпали, то текущий индекс - индекс начала искомой подстроки
                indexesOfFindSubstring.add(i);
            } else {
                hashOfString = calculateHash(string.toCharArray(), i + 1, i + sizeOfFindString + 1);
            }
        }
        return indexesOfFindSubstring;
    }

    public static void main(String[] args) {
        String string = "ПППfATCAM OGUSGCAGAMOGUSAGAG TATACAGTA AMOGUSCG";
        String subString = "AMOGUS";
        List<Integer> findSubStringArray = rabinKarp(subString, string);
        System.out.println(findSubStringArray);
        System.out.println(string);
        for (Integer index : findSubStringArray) {
            System.out.println(index + "->" + string.substring(index, index + subString.length()));
        }
    }
}
