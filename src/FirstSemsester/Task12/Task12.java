package FirstSemsester.Task12;

import FirstSemsester.Tasks.Task1;
import FirstSemsester.Tasks.Task11;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Task12 {
    private static final String PATH = "src/FirstSemsester.Task12";
    private static final String INPUT = "/input.txt";
    private static final String OUTPUT = "/output.txt";

    public static void main(String[] args) throws IOException { // 15 61616 4185 41841664 84 8 48 9
        multiphaseSort(INPUT, 4);
    }

    public static void multiphaseSort(String inputPath, int size) throws IOException {
        int counter = 0;
        List<String> strArray;
        String path;
        String line;
        BufferedWriter outWriter = new BufferedWriter(new FileWriter(PATH + OUTPUT));
        BufferedReader reader = new BufferedReader(new FileReader(PATH + INPUT));
        List<Integer> array = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            Integer integer = Integer.parseInt(line);
            path = PATH + "/" + counter + ".txt";
            if (array.size() >= size) {
                new File(path).createNewFile();
                StringBuilder builder = new StringBuilder();
                array = Task11.quickSort(array);
                for (Integer item : array) {
                    builder.append(item).append(" ");
                }
                array.clear();
                counter++;
                writeInFile(path, builder.toString());
            }
            array.add(integer);

        }
        if (!array.isEmpty()) {
            path = PATH + "/" + counter + ".txt";
            new File(path).createNewFile();
            StringBuilder builder = new StringBuilder();
            array = Task11.quickSort(array);
            for (Integer item : array) {
                builder.append(item).append(" ");
            }
            writeInFile(path, builder.toString());
        }
        reader.close();

        boolean flag = true;
        int minItem = Integer.MAX_VALUE;
        int firstItem;
        String pathRemoveFile = PATH + "/" + "notFoundTXT" + ".txt";
        while (flag) {
            flag = false;
            List<String> tempArray = new ArrayList<>();
            for (int i = 0; i < counter + 1; i++) {
                path = PATH + "/" + (i) + ".txt";
                reader = new BufferedReader(new FileReader(path));
                String item;
                if ((item = reader.readLine()) != null)
                    strArray = new ArrayList<>(List.of(item.split(" ")));
                else
                    continue;
                reader.close();
                firstItem = Integer.parseInt(strArray.get(0));
                if (firstItem <= minItem) {
                    flag = true;
                    minItem = firstItem;
                    pathRemoveFile = path;
                    tempArray = new ArrayList<>(strArray);
                }
            }
            if (minItem != Integer.MAX_VALUE) {
                outWriter.write(String.valueOf(minItem));
                outWriter.write(" ");
                outWriter.flush();
            }
            if (!tempArray.isEmpty()) {
                tempArray.remove(0);
                writeInFile(pathRemoveFile, createString(tempArray));
            }
            minItem = Integer.MAX_VALUE;
        }
        outWriter.close();
    }

    private static void writeInFile(String path, String expression) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        writer.write(expression);
        writer.close();
    }

    private static String createString(List<String> expressions) {
        StringBuilder builder = new StringBuilder();
        for (String item : expressions)
            builder.append(item).append(" ");
        return builder.toString();
    }
}
