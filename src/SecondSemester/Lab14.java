package SecondSemester;

import java.util.ArrayList;
import java.util.List;

public class Lab14 {

    public static List<Integer> rabinKarp(String findString, String string) {
        List<Integer> indexesOfFindSubstring = new ArrayList<>();
        int sizeOfString = string.length();
        int sizeOfFindString = findString.length();
        String subString = string.substring(0, sizeOfString);
        int hashOfString = subString.hashCode();
        int hashOfFindString = findString.hashCode();
        for (int i = 0; i < (sizeOfString - sizeOfFindString) - 1; i++) {
            boolean one = (hashOfString == hashOfFindString);
            boolean two = string.substring(i, i + sizeOfFindString).equals(findString);
            if (one && two) { // если строки совпали, то текущий индекс - индекс начала искомой подстроки
                indexesOfFindSubstring.add(i);
            } else {
                subString = string.substring(i + 1, i + sizeOfFindString + 1);
                hashOfString = subString.hashCode();
            }
        }
        return indexesOfFindSubstring;
    }

    public static void main(String[] args) {
        String string = "ПППfATCAMOGUSGCAGAMOGUSAGAGTATACAGTAAMOGUSCG";
        String subString = "AMOGUS";
        List<Integer> findSubStringArray = rabinKarp(subString, string);
        System.out.println(findSubStringArray);
        System.out.println(string);
        for (Integer index : findSubStringArray) {
            System.out.println(index + "->" + string.substring(index, index + subString.length()));
        }
    }
}
