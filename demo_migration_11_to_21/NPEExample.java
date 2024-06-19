/* Helpful NullPointerExceptions under JEP 358 broke this (kind of) tests*/
class Point {
    int x;
    int y;
    Point(int x1, int y1) {
        this.x = x1;
        this.y = y1;
    }
}
public class NPEExample {
    public static void main(String[] args) {
        try {
            Point p = null;
            p.x = 10;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (e.getMessage() == null) {
                System.out.println("Test Passes");
            } else {
                System.out.println("Test Failed");
            }
        }
    }
}
