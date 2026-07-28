public class Circle extends Shape
{
    final private double PI = 3.14;
    private double r;
    public Circle (double r)
    {
        super(2 * r, 2 * r);
        this.r = r;
    }

    public double getArea ()
    {
        return PI * r*r;
    }

    public double getCircumference ()
    {
        return 2 * r * PI;
    }
}
