import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    private abstract static class Connection {
        private final double units;

        Connection(double units) {
            this.units = units;
        }

        abstract double bill();

        abstract String type();

        double units() {
            return units;
        }
    }

    private static class Home extends Connection {
        Home(double units) {
            super(units);
        }

        @Override
        double bill() {
            return Math.min(units(), 100) * 5
                    + Math.max(units() - 100, 0) * 7;
        }

        @Override
        String type() {
            return "HOME";
        }
    }

    private static class Shop extends Connection {
        Shop(double units) {
            super(units);
        }

        @Override
        double bill() {
            return units() * 8 + 100;
        }

        @Override
        String type() {
            return "SHOP";
        }
    }

    private static class Factory extends Connection {
        Factory(double units) {
            super(units);
        }

        @Override
        double bill() {
            return Math.max(units() * 6, 1000);
        }

        @Override
        String type() {
            return "FACTORY";
        }
    }

    private static Connection readConnection(Scanner input) {
        String type = input.next();
        double units = input.nextDouble();

        switch (type) {
            case "HOME":
                return new Home(units);
            case "SHOP":
                return new Shop(units);
            case "FACTORY":
                return new Factory(units);
            default:
                throw new IllegalArgumentException("Unknown connection type: " + type);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int count = input.nextInt();
        List<Connection> connections = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            connections.add(readConnection(input));
        }

        double total = 0;
        for (Connection connection : connections) {
            double bill = connection.bill();
            System.out.printf(Locale.US, "%s: %.2f%n", connection.type(), bill);
            total += bill;
        }
        System.out.printf(Locale.US, "Total: %.2f%n", total);
    }
}
