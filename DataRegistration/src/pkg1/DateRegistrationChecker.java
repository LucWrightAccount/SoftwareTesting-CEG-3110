package DataRegistration.src.pkg1;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

public class DateRegistrationChecker {
    public static ArrayList<UserInfo> users = new ArrayList<>();
    public static List<UserInfo> fiveKUser = new ArrayList<>();
    public static List<UserInfo> marathonUsers = new ArrayList<>();
    public static List<UserInfo> halfMarathonUser = new ArrayList<>();
    public static List<UserInfo> tenKUsers = new ArrayList<>();
    public static List<UserInfo> testUsers = UserInfoTest.getTestUsers();
    public static List<LocalDate> dates = DateOfRegistrationTest.getDateOfRegistrationTest();

    public static void main(String[] args) {
        for (UserInfo user : testUsers) {
            System.out.println((user.toString()));
        }

    }

    // public static String dateValidations(LocalDate date) {
    //     System.out.print(date+ " : ");
    //     return categorizeDate(date);
    // }

    // public static void userInput() {
    //     String cont = "y";
    //     String registrationDate;
    //     Scanner scanner = new Scanner(System.in);
    //     while (cont.equalsIgnoreCase("y")) {
    //         System.out.println("Enter Todays Date : YYYYMMDD\n");
    //         registrationDate = scanner.nextLine().trim();

    //         if (registrationDate.length() != 8 || !registrationDate.matches("\\d{8}")) {
    //             System.out.println("Invalid");
    //             continue; // go back to the top of loop

    //         }

    //         try {
    //             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    //             LocalDate date = LocalDate.parse(registrationDate, formatter);

    //             System.out.println("\nDate entered: " + date.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")));
    //             // Register status goes here
    //             String category = categorizeDate(date);
    //             System.out.println(category);
    //             if (category.equalsIgnoreCase("Registration Not Open") ||
    //                     category.equalsIgnoreCase("Registration Closed")) {
    //                 System.out.println("Registration not available. Please try another date.\n");
    //                 continue;

    //             }
    //             UserInput(date);

    //         } catch (DateTimeParseException e) {
    //             System.out.println("Invalid Date");
    //             continue;

    //         }

    //         System.out.println("\nType Y to contine");
    //         cont = scanner.nextLine().trim();

    //     }
    //     scanner.close();
    //     System.out.println("End");
    // }

    public static boolean UserTestCases(UserInfo user) {
        boolean pass = true;
        if (!firstNameValidation(user.getfName())) {
            System.out.println("\nInvalid First Name : " + user.getfName());
            pass = false;
        } else {
            System.out.println("\nValid First Name : " + user.getfName());

        }
        if (!lastNameValidation(user.getlName())) {
            System.out.println("\nInvalid LastName : " + user.getlName());
            pass = false;
        } else {
            System.out.println("\nValid LastName : " + user.getlName());

        }
        if (!DOBValidation(user.getDOB())) {
            System.out.print("\nInvalid Date of Birth : " + user.getDOB());
            pass = false;
        } else {
            System.out.print("\nValid Date of Birth : " + user.getDOB());

        }
        if (!genderValidation(user.getGender())) {
            System.out.println("\nInvalid Gender : " + user.getGender());
            pass = false;
        } else {
            System.out.println("\nValid Gender : " + user.getGender());

        }
        if (!emailValidation(user.getEmail())) {
            System.out.println("\nInvalid email : " + user.getEmail());
            pass = false;
        } else {
            System.out.println("\nValid email : " + user.getEmail());

        }

        return pass;

    }

    public static void UserInput(LocalDate date) {
        Scanner input = new Scanner(System.in);
        System.out.println("\nYour information goes here:");
        UserInfo user = new UserInfo();
        printFirstName();
        user.setfName(input.nextLine());
        printLastName();
        user.setlName(input.nextLine());
        printDOB();
        user.setDOB(input.nextLine());
        printGender();
        user.setGender(input.nextLine());
        printEmail();
        user.setEmail(input.nextLine());
        printReg();
        user.setTimeStamp(date);
        printRace();
        user.setRaceType(input.nextLine());
        UserTestCases(user);
        users.add(user);
    }

    private static void UserOutput(LocalDate registrationDate) {

        for (UserInfo user : testUsers) {

            if (UserTestCases(user)) {

                if (user.getRaceType().equalsIgnoreCase("5k")) {
                    fiveKUser.add(user);
                } else if (user.getRaceType().equalsIgnoreCase("10k")) {
                    tenKUsers.add(user);
                } else if (user.getRaceType().equalsIgnoreCase("Half Marathon")) {
                    halfMarathonUser.add(user);
                } else if (user.getRaceType().equalsIgnoreCase("Marathon")) {
                    marathonUsers.add(user);
                }
            } else {
                System.out.println("Invalid Entry");
            }

        }

        System.out.println("10k Array");
        System.out.println(tenKUsers);
        System.out.println("5k Array");
        System.out.println(fiveKUser);
        System.out.println("Marathon Array");
        System.out.println(marathonUsers);
        System.out.println("Half Matathon Array");
        System.out.println(halfMarathonUser);

    }

    public static boolean firstNameValidation(String first) {
        // Check if null or empty
        if (first == null || first.trim().isEmpty()) {
            return false;
        }

        // Only letters allowed, minimum 2 characters, max 30
        return first.matches("^[A-Za-z]{2,30}$");
    }

    public static boolean lastNameValidation(String last) {
        // Check if null or empty
        if (last == null || last.trim().isEmpty()) {
            return false;
        }

        // Allow letters, hyphens, or apostrophes (e.g., O'Neill, Smith-Jones)
        return last.matches("^[A-Za-z'-]{2,30}$");
    }

    public static boolean DOBValidation(LocalDate DOB) {
        LocalDate today = LocalDate.now();
        return !DOB.isAfter(today);

    }

    public static boolean genderValidation(String gender) {
        if (gender.equalsIgnoreCase("Female") || gender.equalsIgnoreCase("Male")) {
            return true;
        }
        return false;

    }

    public static boolean emailValidation(String email) {

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern p = Pattern.compile(emailRegex);
        return email != null && p.matcher(email).matches();

    }

    public static void printRace() {
        System.out.println("Enter Race Type");
    }

    public static void printFirstName() {
        System.out.println("Enter First Name:");
    }

    public static void printLastName() {
        System.out.println("Enter Last Name");
    }

    public static void printDOB() {
        System.out.println("Enter Date of Birth(YYYYMMDD)");
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

    // private static String categorizeDate(LocalDate date) {
    //     int year = 2026; // Base registration year

    //     // --- Define key boundaries ---
    //     LocalDate jun1 = LocalDate.of(year, 6, 1);
    //     LocalDate sep30 = LocalDate.of(year, 9, 30);
    //     LocalDate oct1 = LocalDate.of(year, 10, 1);
    //     LocalDate oct31 = LocalDate.of(year, 10, 31);
    //     LocalDate nov1 = LocalDate.of(year, 11, 1);
    //     LocalDate febEnd = LocalDate.of(year + 1, 2, isLeapYear(year + 1) ? 29 : 28);
    //     LocalDate mar1 = LocalDate.of(year + 1, 3, 1);
    //     LocalDate apr1 = LocalDate.of(year + 1, 4, 1);
    //     LocalDate apr30 = LocalDate.of(year + 1, 4, 30);
    //     LocalDate raceDay = getRaceSaturday(year + 1); // First Saturday in May
    //     LocalDate lateEnd = raceDay.minusDays(1); // Late ends the day before race
    //     LocalDate may31 = LocalDate.of(year + 1, 5, 31);

//         // --- Determine registration category ---
//         if (!date.isBefore(jun1) && !date.isAfter(sep30)) {
//             return "Registration Not Open";
//         } else if (!date.isBefore(oct1) && !date.isAfter(oct31)) {
//             return "Super Early";
//         } else if (!date.isBefore(nov1) && !date.isAfter(febEnd)) {
//             return "Early";
//         } else if (!date.isBefore(mar1) && !date.isAfter(apr1)) {
//             return "Baseline";
//         } else if (date.isAfter(apr1) && !date.isAfter(lateEnd)) {
//             return "Late";
//         } else if (date.isAfter(lateEnd) && !date.isAfter(may31)) {
//             return "Registration Closed";
//         } else {
//             return "Date not in any registration range.";
//         }
//     }

//     private static boolean isLeapYear(int year) {
//         return java.time.Year.of(year).isLeap();
//     }

//     private static LocalDate getRaceSaturday(int year) {
//     LocalDate may1 = LocalDate.of(year, 5, 1);

//     // Find first Saturday in May
//     while (may1.getDayOfWeek() != java.time.DayOfWeek.SATURDAY) {
//         may1 = may1.plusDays(2);
//     }

//     // If that Saturday’s weekend overlaps with April, skip to next Saturday
//     LocalDate sunday = may1.plusDays(1);
//     if (sunday.getMonthValue() != 5) {
//         may1 = may1.plusWeeks(1);
//     }

//     return may1;
// }


}
