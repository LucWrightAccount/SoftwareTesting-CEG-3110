package DataRegistration.src.pkg1;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class DateRegistrationChecker {
    public static ArrayList<UserInfo> users = new ArrayList<>();

    public static void main(String[] args) {
        String cont = "y";
        String registrationDate;
        Scanner scanner = new Scanner(System.in);
        while (cont.equalsIgnoreCase("y")) {
            System.out.println("Enter Todays Date : YYYYMMDD\n");
            registrationDate = scanner.nextLine().trim();

            if (registrationDate.length() != 8 || !registrationDate.matches("\\d{8}")) {
                System.out.println("Invalid");
                continue; // go back to the top of loop

            }

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                LocalDate date = LocalDate.parse(registrationDate, formatter);

                System.out.println("\nDate entered: " + date.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")));
                // Register status goes here
                String category = categorizeDate(date);
                System.out.println(category);
                if (category.equalsIgnoreCase("Registration Not Open") ||
                        category.equalsIgnoreCase("Registration Closed")) {
                    System.out.println("Registration not available. Please try another date.\n");
                    continue;

                }
                UserInfo(date);

            } catch (DateTimeParseException e) {
                System.out.println("Invalid Date");
                continue;

            }

            for (UserInfo user : users) {
                System.out.println(user.getfName());

            }

            System.out.println("\nType Y to contine");
            cont = scanner.nextLine().trim();

        }
        scanner.close();
        System.out.println("End");
    }

    private static void UserInfo(LocalDate date) {
        Scanner input = new Scanner(System.in);
        System.out.println("\nYour information goes here:");
        UserInfo user = new UserInfo();
        printFirstName();
        user.setfName(input.nextLine());
        printLastName();
        user.setlName(input.nextLine());
        printDOB();
        // user.setDOB();
        printGender();
        user.setGender(input.nextLine());
        printEmail();
        user.setEmail(input.nextLine());
        printReg();
        user.setTimeStamp(date);
        users.add(user);

    }

    public static void printFirstName() {
        System.out.println("Enter First Name:");
    }

    public static void printLastName() {
        System.out.println("Enter Last Name");
    }

    public static void printDOB() {
        System.out.println("Enter Date of Birth");
    }

    public static void printGender() {
        System.out.println("Enter Gender:");
    }

    public static void printEmail() {
        System.out.println("Enter email");
    }

    public static void printReg() {
        System.out.println("Registration Timestamp");
    }

    private static String categorizeDate(LocalDate date) {
        int year = date.getYear();

        // Registration cycle boundaries
        LocalDate jun1 = LocalDate.of(year, 6, 1);
        LocalDate sep30 = LocalDate.of(year, 9, 30);
        LocalDate oct1 = LocalDate.of(year, 10, 1);
        LocalDate oct31 = LocalDate.of(year, 10, 31);
        LocalDate nov1 = LocalDate.of(year, 11, 1);

        // Handle leap year for February
        boolean leap = isLeapYear(year + 1);
        LocalDate febEnd = LocalDate.of(year + 1, 2, leap ? 29 : 28);

        LocalDate mar1 = LocalDate.of(year + 1, 3, 1);
        LocalDate apr1 = LocalDate.of(year + 1, 4, 1);
        LocalDate apr2 = LocalDate.of(year + 1, 4, 2);

        // TDay (Thursday before 1st Saturday in May)
        LocalDate tDay = getTDay(year + 1);
        LocalDate may31 = LocalDate.of(year + 1, 5, 31);

        // Adjust if date is after Sep (i.e., registration cycle crosses year)
        if (date.isAfter(sep30)) {
            febEnd = LocalDate.of(year + 1, 2, isLeapYear(year + 1) ? 29 : 28);
            mar1 = LocalDate.of(year + 1, 3, 1);
            apr1 = LocalDate.of(year + 1, 4, 1);
            apr2 = LocalDate.of(year + 1, 4, 2);
            tDay = getTDay(year + 1);
            may31 = LocalDate.of(year + 1, 5, 31);
        }

        // Now test all date ranges
        if (!date.isBefore(jun1) && !date.isAfter(sep30)) {
            return "Registration Not Open";
        } else if (!date.isBefore(oct1) && !date.isAfter(oct31)) {
            return "Super Early";
        } else if (!date.isBefore(nov1) && !date.isAfter(febEnd)) {
            return "Early";
        } else if (!date.isBefore(mar1) && !date.isAfter(apr1)) {
            return "Baseline";
        } else if (!date.isBefore(apr2) && date.isBefore(tDay)) {
            return "Late";
        } else if (!date.isBefore(tDay) && !date.isAfter(may31)) {
            return "Registration Closed";
        } else {
            return "Date not in any registration range.";
        }
    }

    private static LocalDate getTDay(int year) {
        LocalDate mayFirst = LocalDate.of(year, 5, 1);

        LocalDate firstSaturday = mayFirst;
        while (firstSaturday.getDayOfWeek() != java.time.DayOfWeek.SATURDAY) {
            firstSaturday = firstSaturday.plusDays(1);
        }
        return firstSaturday.minusDays(2);
    }

    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

}
