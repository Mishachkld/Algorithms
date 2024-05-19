package SecondSemester;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;


public class Lab14 {
    private final static int q = Integer.MAX_VALUE;

    private final static int BASE = 31;

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

class BM {
    public static void main(String[] args) {
        String source = "ПППfATCAM OGUSGCAGAMOGUSAGAG TATACAGTA AMOGUSCG";
        String template = "AMOGUS";
        System.out.println(getFirstEntry(source, template));
    }

    public static HashMap<Character, Integer> makeOffsetTable(String pattern) {
        HashMap<Character, Integer> offsetTable = new HashMap<Character, Integer>();
        for (int i = 0; i <= 255; i++) {
            offsetTable.put((char) i, pattern.length());
        }
        for (int i = 0; i < pattern.length() - 1; i++) {
            offsetTable.put(pattern.charAt(i), pattern.length() - i - 1);
        }
        return offsetTable;
    }

    public static int getFirstEntry(String s, String p) {
        HashMap<Character, Integer> offsetTable = makeOffsetTable(p);
        if (s.length() < p.length()) {
            return -1;
        }

        int i = p.length() - 1;
        int j = i;
        int k = i;

        while (j >= 0 && i <= s.length() - 1) {
            j = p.length() - 1;
            k = i;
            while (j >= 0 && s.charAt(k) == p.charAt(j)) {
                k--;
                j--;
            }
            i += offsetTable.get(s.charAt(i));
        }
        if (k >= s.length() - p.length()) {
            return -1;
        } else {
            return k + 1;
        }
    }
}
