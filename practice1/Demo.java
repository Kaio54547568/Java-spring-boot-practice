public class Demo {
    public static void main (String[] args)
    {
        Shape x = new Shape(3, 4);
        System.out.println("Shape's width, height: " + x.width + ", " + x.height);

        Rectangle y = new Rectangle(3, 4);
        System.out.println("Rectangle's area: " + y.getArea());
        System.out.println("Rectangle's perimeter: " + y.getPerimeter());

        Circle z = new Circle(5);
        System.out.println("Circle's area: " + z.getArea());
        System.out.println("Circle's circumference: " + z.getCircumference());
    }
}
