package SecondSemester.Lab11;

import Tools.Helper;

import java.io.IOException;
import java.util.List;

public class Lab11 {


    public static boolean automatic(List<String> textInCharsArray) {
        State state = State.STATE_1;
        boolean isFindSubString = false;
        for (String item : textInCharsArray) {  // abbab
            switch (state) {
                case STATE_1:
                    if (item.equals("a")) {
                        state = State.STATE_2;
                    }
                    continue;
                case STATE_2:
                    if (item.equals("b")) {
                        state = State.STATE_3;
                    }
                    continue;
                case STATE_3:
                    if (item.equals("b")) {
                        state = State.STATE_4;
                    } else if (item.equals("a")) {
                        state = State.STATE_4;
                    }
                    continue;
                case STATE_4:
                    if (item.equals("a") ) {
                        state = State.STATE_5;

                    } else if (item.equals("b")) {
                        state = State.STATE_1;
                    }
                    continue;
                case STATE_5:
                    if (item.equals("b")) {
                        state = State.STATE_6;
                    } else if (item.equals("a")) {
                        state = State.STATE_2;
                    }
                    continue;
                case STATE_6:
                    isFindSubString = true;  // получается, что мы уже нашли искомую подстроку
                    int i = textInCharsArray.size();
                    break;
            }
            if (isFindSubString) {
                break;
            }
        }
        return isFindSubString;
    }

    public static void main(String[] args) throws IOException {
        String path = "src/SecondSemester/Lab11/input.txt";
        List<String> lineWithText = Helper.readFileInLine(path, ""); // Здесь нужно определитьтся, в каком виде считывать файл
        boolean isFoundString = automatic(lineWithText);
        if (isFoundString) {
            System.out.println("Мы нашли искомую подстроку!!!");
        } else {
            System.out.println("Не найдена подстрока :(");
        }

    }


    enum State {
        STATE_1, STATE_2, STATE_3, STATE_4, STATE_5, STATE_6
    }
}
