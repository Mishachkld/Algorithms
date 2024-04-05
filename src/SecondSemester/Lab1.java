package SecondSemester;

import ReadAndWriteFuncrions.Helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Lab1 {

    public static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }

    private static double rotate(Point startPoint, Point rightPoint, Point tecPoint) {
        return (rightPoint.x - startPoint.x) * (tecPoint.y - rightPoint.y) - (rightPoint.y - startPoint.y) * (tecPoint.x - rightPoint.x);
    }

    public static List<Integer> jarvisCalculate(List<Point> points) {
        List<Integer> hull = new ArrayList<>();
        List<Point> dots = new ArrayList<>(points);


        int lenDotsArray = dots.size();
        List<Integer> numbersOfPoints = new ArrayList<>();
        for (int i = 0; i < lenDotsArray; i++) {
            numbersOfPoints.add(i);
        }

        for (int i = 1; i < lenDotsArray; i++) {
            int numberOfTecPoint = numbersOfPoints.get(i);
            if (points.get(numberOfTecPoint).x < points.get(numbersOfPoints.get(0)).x) {
                Collections.swap(numbersOfPoints, i, 0);
            }
        }
        hull.add(numbersOfPoints.get(0));
        numbersOfPoints.remove(0);
        numbersOfPoints.add(hull.get(0));

        while (true) {
            int right = 0;
            for (int i = 1; i < numbersOfPoints.size(); i++) {
                double rotation = rotate(points.get(hull.get(hull.size() - 1)),
                                         points.get(numbersOfPoints.get(right)),
                                         points.get(numbersOfPoints.get(i)));
                if (rotation < 0){
                    System.out.println(rotation);
                    right = i;
                }
            }
            if (numbersOfPoints.get(right).equals(hull.get(0))){
                break;
            }
            else{
                hull.add(numbersOfPoints.get(right));
                numbersOfPoints.remove(right);
            }
        }

        return hull;
    }




    public static void main(String[] args) {
        List<Point> dots = addPoints();
        System.out.println(jarvisCalculate(dots));
    }


    private static List<Point> addPoints() {
        return new ArrayList<>(Arrays.asList(new Point(1, 1), new Point(2, -2), new Point(-1, -4), new Point(-1, 1), new Point(-2, -3),
                new Point(0, -1), new Point(0, -2), new Point(1, -1)));
    }
}
