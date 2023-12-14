package Task12;

import Tasks.Task1;
import Tasks.Task11;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Task12 {
    private static final String PATH = "src/Task12";
    private static final int COUNT_OF_FILES = 2;

    public static void main(String[] args) throws IOException { // 15 61616 4185 41841664 84 8 48 9
        // List<Integer> expression = Task1.scanConsole();
        multiphaseSort("/input.txt", "/1.txt", "/2.txt");
    }

    private static void createFiles() throws IOException {
        File file1 = new File(PATH + "/1.txt");
        File file2 = new File(PATH + "/2.txt");
        file1.createNewFile();
        file2.createNewFile();
    }

    public static void multiphaseSort(String inputPath, String file1Path, String file2Path) throws IOException {
        createFiles();
        List<String> strArray;
        List<Integer> result = new ArrayList<>();
        StringBuilder builder = new StringBuilder();

        BufferedReader reader = new BufferedReader(new FileReader(PATH + inputPath));
        strArray = new ArrayList<>(List.of(reader.readLine().split(" ")));  /// прочитали с файла числа
        reader.close();
        String path = PATH + file1Path;
        for (String item : strArray)
            result.add(Integer.valueOf(item));

        for (Integer item : Task11.quickSort(new ArrayList<>(result.subList(0, strArray.size() / 2)))) {
            builder.append(item).append(" ");
        }
        writeInFile(path, builder.toString());
        result.subList(0, strArray.size() / 2).clear();
        builder = new StringBuilder();
        for (Integer item : Task11.quickSort(result)) {
            builder.append(item).append(" ");
        }
        path = PATH + file2Path;
        writeInFile(path, builder.toString());

        boolean flag = true;
        int minItem = Integer.MAX_VALUE;
        int firstItem;
        int num = 0;
        BufferedWriter outWriter = new BufferedWriter(new FileWriter(PATH + "/output.txt"));
        while (flag) {
            flag = false;
            for (int i = 0; i < COUNT_OF_FILES; i++) {
                path = PATH + "/" + (i + 1) + ".txt";
                reader = new BufferedReader(new FileReader(path));
                String item;
                if ((item = reader.readLine()) != null)
                    strArray = new ArrayList<>(List.of(item.split(" ")));
                reader.close();
                firstItem = Integer.parseInt(strArray.get(0));
                if (firstItem <= minItem) {
                    flag = true;
                    minItem = firstItem;
                    path = PATH + "/" + (i + 1) + ".txt";
                }

            }
            if (minItem != Integer.MAX_VALUE) {
                outWriter.write(String.valueOf(minItem));
                outWriter.write(" ");
                outWriter.flush();
            }
            strArray.remove(0);
            writeInFile(path, createString(strArray));
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
