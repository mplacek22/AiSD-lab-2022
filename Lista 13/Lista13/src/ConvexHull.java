import java.awt.*;
import java.util.*;
import java.util.List;

public class ConvexHull {

    public static List<Point> solve(List<Point> points) throws IllegalArgumentException {
        // TODO
        int N = points.size();

        if (N < 3)
            throw new IllegalArgumentException();

        Point[] pointsArr = points.toArray(new Point[0]);

        //find p0 (smallest y, if equal, then smallest x)
        Point p0 = pointsArr[0];
        int idxMinY = 0;
        for (int i = 1; i < N; i++) {
            Point tmp = pointsArr[i];
            if (tmp.y < p0.y || (tmp.y == p0.y && tmp.x <  p0.x)){
                p0 = tmp;
                idxMinY = i;
            }
        }

        Point temp = pointsArr[0];
        pointsArr[0] = p0;
        pointsArr[idxMinY] = temp;

        //sort by polar coordinates
        Arrays.sort(pointsArr, 1, N, new PolarCMP(p0));


        //handle collinear points
        int m = 1;
        for (int i=1; i<N; i++)
        {
            while (i < N-1 && orientation(p0, pointsArr[i], pointsArr[i+1]) == 0)
                i++;

            pointsArr[m] = pointsArr[i];
            m++;
        }

        //only collinear points
        if (m < 3)
            throw new IllegalArgumentException();


        Stack<Point> stack = new Stack<>();
        stack.push(p0);
        stack.push(pointsArr[1]);
        stack.push(pointsArr[2]);


        for (int i = 3; i < m; i++)
        {
            while (stack.size()>1 && orientation(nextToTop(stack), stack.peek(), pointsArr[i]) != 2)
                stack.pop();
            stack.push(pointsArr[i]);
        }

        stack.push(p0);


        return stack;
    }

    private static int orientation(Point p, Point q, Point r)
    {
        int det = p.x*q.y + r.x*p.y + q.x*r.y - r.x*q.y - p.x*r.y - q.x*p.y;

        if (det == 0) return 0;  // collinear
        return (det < 0) ? 1 : 2; // counterclockwise (right turn)  or clockwise (left turn)
    }

    private static int distance(Point p1, Point p2)
    {
        return Math.abs(p1.x - p2.x) + Math.abs((p1.y - p2.y));
    }

    static Point nextToTop(Stack<Point> stack) {
        Point p = stack.peek();
        stack.pop();
        Point result = stack.peek();
        stack.push(p);
        return result;
    }

    private static class PolarCMP implements Comparator<Point>{
        Point p0;

        public PolarCMP(Point p0) {
            this.p0 = p0;
        }

        @Override
        public int compare(Point o1, Point o2) {
            int o = orientation(p0, o1, o2);
            if (o == 0)
                return (distance(p0, o2) >= distance(p0, o1)) ? -1 : 1;

            return (o == 2) ? -1: 1;
        }
    }

}





