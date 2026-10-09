import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    private abstract static class StaffMember {
        private final String name;

        StaffMember(String name) {
            this.name = name;
        }

        abstract double pay();

        String name() {
            return name;
        }
    }

    private static class FullTimeStaff extends StaffMember {
        private final double weeklySalary;

        FullTimeStaff(String name, double weeklySalary) {
            super(name);
            this.weeklySalary = weeklySalary;
        }

        @Override
        double pay() {
            return weeklySalary;
        }
    }

    private static class HourlyStaff extends StaffMember {
        private final double hours;
        private final double rate;

        HourlyStaff(String name, double hours, double rate) {
            super(name);
            this.hours = hours;
            this.rate = rate;
        }

        @Override
        double pay() {
            double regularHours = Math.min(hours, 40);
            double overtimeHours = Math.max(hours - 40, 0);
            return regularHours * rate + overtimeHours * rate * 1.5;
        }
    }

    private static class Intern extends StaffMember {
        private final double stipend;

        Intern(String name, double stipend) {
            super(name);
            this.stipend = stipend;
        }

        @Override
        double pay() {
            return stipend;
        }
    }

    private static StaffMember readStaffMember(Scanner input) {
        String type = input.next();
        String name = input.next();

        switch (type) {
            case "FULLTIME":
                return new FullTimeStaff(name, input.nextDouble());
            case "HOURLY":
                return new HourlyStaff(name, input.nextDouble(), input.nextDouble());
            case "INTERN":
                return new Intern(name, input.nextDouble());
            default:
                throw new IllegalArgumentException("Unknown staff type: " + type);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int count = input.nextInt();
        List<StaffMember> staffMembers = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            staffMembers.add(readStaffMember(input));
        }

        double totalPayroll = 0;
        for (StaffMember staffMember : staffMembers) {
            double pay = staffMember.pay();
            System.out.printf(Locale.US, "%s: %.2f%n", staffMember.name(), pay);
            totalPayroll += pay;
        }
        System.out.printf(Locale.US, "Total Payroll: %.2f%n", totalPayroll);
    }
}
