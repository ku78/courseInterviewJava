package hw1;

public class Triangle extends Shape{
    public Triangle(int width,int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return 1/2*width*height;
    }
}
