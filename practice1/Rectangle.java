public class Rectangle extends Shape
{
    public Rectangle (double x, double y)
    {
        super (x, y);
    }


    public double getArea ()
    {
        return width * height;
    }

    public double getPerimeter ()
    {
        return 2 * (width + height);
    }
}
