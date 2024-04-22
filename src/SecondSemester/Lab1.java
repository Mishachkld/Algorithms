package SecondSemester;

import Tools.HelpClasses.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Lab1 {
    // сложность N*H - H - коллличество точек в оболочке
    // N- колличество всех точек. Худший случай, если все точки вошли в оболочку N^2

    private static double rotate(Point startPoint, Point rightPoint, Point tecPoint) {
        return (rightPoint.x - startPoint.x) * (tecPoint.y - rightPoint.y) -
                (rightPoint.y - startPoint.y) * (tecPoint.x - rightPoint.x);
    }

    public static List<Integer> jarvisCalculate(List<Point> points) { //
        List<Integer> hull = new ArrayList<>(); // в правильном порядке хранятся вершины
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
            } /// Ещем самую левую точку
        }
        hull.add(numbersOfPoints.get(0));   // делаем стартовую вершину текущей
        int p = numbersOfPoints.remove(0);
        numbersOfPoints.add(hull.get(0));

        Point pointStart = points.get(numbersOfPoints.get(p));

        /*while(true){
            Point point = null;
            for (Point value: points){
                if ((point == null) || (rotate(pointStart, point, value) < 0)){
                    point = value;
                }
            }
            if (point == pointStart){
                break;
            }
            point = ;
            hull.add(points.indexOf(points.get(p)));
        }*/

        while (true) {
            int right = 0;
            for (int i = 1; i < numbersOfPoints.size(); i++) {  // ищем самую точку из points, относительно последней вершины из hull
                                                                // до тех пор, пока она не будет стартовой
                double rotation = rotate(points.get(hull.get(hull.size() - 1)),
                        points.get(numbersOfPoints.get(right)),
                        points.get(numbersOfPoints.get(i)));
                if (rotation < 0) {
                    right = i;
                }
            }
            if (numbersOfPoints.get(right).equals(hull.get(0))) {
                break;
            } else {
                hull.add(numbersOfPoints.get(right));
                numbersOfPoints.remove(right);
            }
        }

        return hull;
    }


    public static void main(String[] args) {
        List<Point> dots = addPoints();
        System.out.println(dots);
        System.out.println(jarvisCalculate(dots));
    }


    private static List<Point> addPoints() {
        return new ArrayList<>(Arrays.asList(new Point(0, 0), new Point(0, 100), new Point(100, 0), new Point(50, 50), new Point(10, 0),
                new Point(0, 9), new Point(40, 8), new Point(0, 6), new Point(0, 15), new Point(3, 3), new Point(5, 7)));
        /*return new ArrayList<>(Arrays.asList(new Point(1, 2), new Point(3, 5), new Point(6, 1), new Point(8, 4), new Point(10, 7),
                new Point(7, 9), new Point(4, 8), new Point(2, 6), new Point(9, 8), new Point(4, 3), new Point(5, 7)));*/
        /*return new ArrayList<>(Arrays.asList(new Point(1, 1), new Point(2, -2), new Point(-1, -4), new Point(-1, 1), new Point(-2, -3),
                new Point(0, -1), new Point(0, -2), new Point(1, -1)));*/
    }
}
