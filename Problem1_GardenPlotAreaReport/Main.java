import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    private abstract static class Plot {
        private final String owner;

        Plot(String owner) {
            this.owner = owner;
        }

        abstract double area();

        abstract String shape();

        String owner() {
            return owner;
        }
    }

    private static class Circle extends Plot {
        private final double radius;

        Circle(String owner, double radius) {
            super(owner);
            this.radius = radius;
        }

        @Override
        double area() {
            return Math.PI * radius * radius;
        }

        @Override
        String shape() {
            return "CIRCLE";
        }
    }

    private static class Rectangle extends Plot {
        private final double length;
        private final double width;

        Rectangle(String owner, double length, double width) {
            super(owner);
            this.length = length;
            this.width = width;
        }

        @Override
        double area() {
            return length * width;
        }

        @Override
        String shape() {
            return "RECTANGLE";
        }
    }

    private static class Triangle extends Plot {
        private final double base;
        private final double height;

        Triangle(String owner, double base, double height) {
            super(owner);
            this.base = base;
            this.height = height;
        }

        @Override
        double area() {
            return 0.5 * base * height;
        }

        @Override
        String shape() {
            return "TRIANGLE";
        }
    }

    private static Plot readPlot(Scanner input) {
        String shape = input.next();
        String owner = input.next();

        switch (shape) {
            case "CIRCLE":
                return new Circle(owner, input.nextDouble());
            case "RECTANGLE":
                return new Rectangle(owner, input.nextDouble(), input.nextDouble());
            case "TRIANGLE":
                return new Triangle(owner, input.nextDouble(), input.nextDouble());
            default:
                throw new IllegalArgumentException("Unknown plot shape: " + shape);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int count = input.nextInt();
        List<Plot> plots = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            plots.add(readPlot(input));
        }

        double totalArea = 0;
        for (Plot plot : plots) {
            double area = plot.area();
            System.out.printf(Locale.US, "%s (%s): %.2f%n",
                    plot.owner(), plot.shape(), area);
            totalArea += area;
        }
        System.out.printf(Locale.US, "Total Area: %.2f%n", totalArea);
    }
}
