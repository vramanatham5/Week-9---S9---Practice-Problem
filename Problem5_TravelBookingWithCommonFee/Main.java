import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    private abstract static class Booking {
        private static final double BOOKING_FEE = 50.0;
        private final double distanceKm;

        Booking(double distanceKm) {
            this.distanceKm = distanceKm;
        }

        abstract double baseFare();

        abstract String mode();

        double distanceKm() {
            return distanceKm;
        }

        final double totalFare() {
            return baseFare() + BOOKING_FEE;
        }
    }

    private static class Bus extends Booking {
        Bus(double distanceKm) {
            super(distanceKm);
        }

        @Override
        double baseFare() {
            return distanceKm() * 2;
        }

        @Override
        String mode() {
            return "BUS";
        }
    }

    private static class Train extends Booking {
        Train(double distanceKm) {
            super(distanceKm);
        }

        @Override
        double baseFare() {
            return distanceKm() * 1.5;
        }

        @Override
        String mode() {
            return "TRAIN";
        }
    }

    private static class Flight extends Booking {
        Flight(double distanceKm) {
            super(distanceKm);
        }

        @Override
        double baseFare() {
            return 2500 + distanceKm() * 4;
        }

        @Override
        String mode() {
            return "FLIGHT";
        }
    }

    private static Booking readBooking(Scanner input) {
        String mode = input.next();
        double distanceKm = input.nextDouble();

        switch (mode) {
            case "BUS":
                return new Bus(distanceKm);
            case "TRAIN":
                return new Train(distanceKm);
            case "FLIGHT":
                return new Flight(distanceKm);
            default:
                throw new IllegalArgumentException("Unknown travel mode: " + mode);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int count = input.nextInt();
        List<Booking> bookings = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            bookings.add(readBooking(input));
        }

        for (Booking booking : bookings) {
            System.out.printf(Locale.US, "%s: %.2f%n",
                    booking.mode(), booking.totalFare());
        }
    }
}
