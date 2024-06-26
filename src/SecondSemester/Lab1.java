package SecondSemester;

import Tools.HelpClasses.Point;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.pow;

public class Lab1 {
    // сложность N*H - H - коллличество точек в оболочке
    // N- колличество всех точек. Худший случай, если все точки вошли в оболочку N^2

    private static double rotate(Point startPoint, Point rightPoint, Point tecPoint) {
        return (rightPoint.x - startPoint.x) * (tecPoint.y - rightPoint.y) -
                (rightPoint.y - startPoint.y) * (tecPoint.x - rightPoint.x);
    }


    private static double distance(Point one, Point two) {
        return pow(pow(one.x - two.x, 2) + pow(one.y - two.y, 2), 2);
    }


    private static int orientation(Point p, Point q, Point r) { // определяем, в каком порядке идут точки (по часовой, если 1, простив часовой, если 2, и коллиниарны, если 0)
        double value = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
        if (value == 0) {
            return 0;
        }
        return (value > 0) ? 1 : 2;
    }

    public static List<Integer> jarvisCalculateNotWorking(List<Point> points) { //
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

        while (true) {
            int right = 0;
            double maxDistance = Integer.MIN_VALUE;
            int maxDistanceIndexOfPoint = right;
            for (int i = 1; i < numbersOfPoints.size(); i++) {  // ищем самую точку из points, относительно последней вершины из hull
                // до тех пор, пока она не будет стартовой
                double rotation = rotate(points.get(hull.get(hull.size() - 1)),
                        points.get(numbersOfPoints.get(right)),
                        points.get(numbersOfPoints.get(i)));
                double tempDistance = distance(dots.get(hull.get(hull.size() - 1)), dots.get(numbersOfPoints.get(i)));
                if (rotation < 0) {
                    right = i;
                }
                /*else if ((rotation == 0) && maxDistance < tempDistance) {
                    right = i;
                    maxDistance = tempDistance;
                }*/
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

    private static List<Point> jarvisCalculate(List<Point> dots) {
        Point startPoint = dots.stream().min(Comparator.comparing(Point::getX).thenComparing(Point::getY)).get(); // получаем мнимальную (левую) точку
        dots = dots.stream().sorted(Comparator.comparing(Point::getX).thenComparing(Point::getY)).collect(Collectors.toList());
        List<Point> pointsInShell = new ArrayList<>();
        Point tempPoint = startPoint;
        Point q;
        while (true) {
            pointsInShell.add(tempPoint);
            q = null;
            double maxDistance = distance(tempPoint, pointsInShell.get(0));
            for (Point point : dots) {
                if (point == tempPoint) {
                    continue;
                }
                if ((q == null) || (orientation(tempPoint, q, point) == 2)) { // точка выберается та, у которой угол будет максимальным относительно текущей, предыдущей и следующей
                    q = point;
                } else if ((orientation(tempPoint, q, point) == 0) && distance(pointsInShell.get(pointsInShell.size() - 1), point) > maxDistance) {
                    q = point;
                    maxDistance = distance(pointsInShell.get(pointsInShell.size() - 1), point);
                }
            }
            if (q == startPoint) { // если дошли до стартовой точки, то заканчиваем
                break;
            }
            tempPoint = q;
        }
        return pointsInShell;
    }


    private static List<Point> addPoints() {
        return new ArrayList<>(Arrays.asList(new Point(0, 0), new Point(0, 100), new Point(100, 0), new Point(50, 50), new Point(10, 0),
                new Point(0, 9), new Point(40, 8), new Point(0, 6), new Point(0, 100), new Point(3, 3), new Point(5, 7)));
        /*return new ArrayList<>(Arrays.asList(new Point(1, 2), new Point(3, 5), new Point(6, 1), new Point(8, 4), new Point(10, 7),
                new Point(7, 9), new Point(4, 8), new Point(2, 6), new Point(9, 8), new Point(4, 3), new Point(5, 7)));*/
        /*return new ArrayList<>(Arrays.asList(new Point(1, 1), new Point(2, -2), new Point(-1, -4), new Point(-1, 1), new Point(-2, -3),
                new Point(0, -1), new Point(0, -2), new Point(1, -1)));*/
       /* return new ArrayList<>(Arrays.asList(new Point(0, 3), new Point(2, 2), new Point(1, 1), new Point(-10, 231), new Point(3, 0),
                new Point(-1, 0), new Point(32, 4)));*/
    }
}
