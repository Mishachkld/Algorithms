package SecondSemester.Lab12;

import Tools.Helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lab12 {

    private static List<Integer> prefixFunction(List<String> stringSplit) {
        List<Integer> prefix = new ArrayList<>(Collections.nCopies(stringSplit.size(), 0));
        for (int i = 1; i < stringSplit.size(); i++) {
            int k = prefix.get(i - 1);
            while ((k > 0) && (!stringSplit.get(i).equals(stringSplit.get(k)))) {
                k = prefix.get(k - 1);
            }
            if (stringSplit.get(i).equals(stringSplit.get(k))) {
                k++;
            }
            prefix.set(i, k);
        }
        return prefix;
    }

    public List<Integer> KMP(List<Integer> prefix, List<String> strings) {
        List<Integer> answer = new ArrayList<>();
        int pl = prefix.size();
        int tl = strings.size();
        prefix = prefixFunction(strings); // здесь нужно передавать все же строку с разделителем
        for (int i = 0; i < tl; i++) {
            if (prefix.get(pl + i + 1) == pl) {
                answer.add(i - pl);
            }
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        List<List<String>> dataFromFiles = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            String path = "src/SecondSemester/Lab12/input_" + i + ".txt";
            dataFromFiles.add(Helper.readFile(path));
        }
        for (List<String> fileData: dataFromFiles){
            System.out.println(fileData);
        }
    }
}
