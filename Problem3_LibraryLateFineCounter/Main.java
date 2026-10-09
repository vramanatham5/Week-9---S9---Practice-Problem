import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    private abstract static class LibraryItem {
        private final String title;
        private final int daysLate;

        LibraryItem(String title, int daysLate) {
            this.title = title;
            this.daysLate = daysLate;
        }

        abstract double fine();

        String title() {
            return title;
        }

        int daysLate() {
            return daysLate;
        }
    }

    private static class Book extends LibraryItem {
        Book(String title, int daysLate) {
            super(title, daysLate);
        }

        @Override
        double fine() {
            return daysLate() * 2.0;
        }
    }

    private static class Dvd extends LibraryItem {
        Dvd(String title, int daysLate) {
            super(title, daysLate);
        }

        @Override
        double fine() {
            return Math.min(daysLate() * 5.0, 50.0);
        }
    }

    private static class Magazine extends LibraryItem {
        Magazine(String title, int daysLate) {
            super(title, daysLate);
        }

        @Override
        double fine() {
            return daysLate();
        }
    }

    private static LibraryItem readItem(Scanner input) {
        String type = input.next();
        String title = input.next();
        int daysLate = input.nextInt();

        switch (type) {
            case "BOOK":
                return new Book(title, daysLate);
            case "DVD":
                return new Dvd(title, daysLate);
            case "MAGAZINE":
                return new Magazine(title, daysLate);
            default:
                throw new IllegalArgumentException("Unknown library item type: " + type);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int count = input.nextInt();
        List<LibraryItem> items = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            items.add(readItem(input));
        }

        double totalFines = 0;
        for (LibraryItem item : items) {
            double fine = item.fine();
            System.out.printf(Locale.US, "%s: %.2f%n", item.title(), fine);
            totalFines += fine;
        }
        System.out.printf(Locale.US, "Total Fines: %.2f%n", totalFines);
    }
}
