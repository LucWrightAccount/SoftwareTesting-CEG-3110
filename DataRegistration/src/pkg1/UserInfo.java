package DataRegistration.src.pkg1;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.text.NumberFormat;
import java.util.Locale;

public class UserInfo {
    NumberFormat usCurrencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
     
    private String fName, lName, gender, email, race, raceCost, racePeriod, race2;
    private LocalDate regDate1, raceDay, raceDay1, raceDay2, DOB;
    private int ageOnRaceDay;

    // userinfo for one race
    UserInfo(String fName, String lName, LocalDate DOB, String gender, String email, LocalDate registrationDate,
            String raceType) {
        this.fName = fName; // First Name
        this.lName = lName; // Last Name
        this.DOB = DOB; // Date of Birth
        this.gender = gender; // Gender
        this.email = email; // Email
        this.regDate1 = registrationDate; // The date of which they register(i.e Super Early, Early, Baseline, Late)
        this.race = raceType;// 5k, 10k, Half Marathon
        this.raceDay = raceDay(raceType, registrationDate.getYear()); // Date of the race
        this.ageOnRaceDay = AgeOnRaceDay(); // Age on Raceday
        this.racePeriod = categorizeDate(this.regDate1); // Determiens
        this.raceCost = usCurrencyFormat.format(RaceCost());

    }

    // UserInfo for two races
    UserInfo(String fName, String lName, LocalDate DOB, String gender, String email, LocalDate registrationDate,
            String raceType1, String raceType2) {
        this.fName = fName; // First Name
        this.lName = lName; // Last Name
        this.DOB = DOB; // Date of Birth
        this.gender = gender; // Gender
        this.email = email; // Email
        this.regDate1 = registrationDate; // The date of which they register(i.e Super Early, Early, Baseline, Late)
        this.race = raceType1;// 5k, 10k, Half Marathon
        this.race2 = raceType2;// 5k, 10k, Half Marathon
        this.raceDay1 = raceDay(raceType1, registrationDate.getYear()); // Date of the race
        this.raceDay2 = raceDay(raceType2, registrationDate.getYear());
        this.ageOnRaceDay = AgeOnRaceDay(); // Age on Raceday
        this.racePeriod = categorizeDate(this.regDate1); // Determiens
        this.raceCost = usCurrencyFormat.format(RaceCost() * .8); // 20% discount for twos races being selected

    }

    public LocalDate getRaceDay1() {
        return this.raceDay1;
    }

    public LocalDate getRaceDay2() {
        return this.raceDay2;
    }

    public void setRaceDay1(LocalDate raceDay1) {
        this.raceDay1 = raceDay1;
    }

    public void setRaceDay2(LocalDate raceDay2) {
        this.raceDay2 = raceDay2;
    }

    UserInfo() {

    }

    public String getRaceCost() {
        return this.raceCost;
    }

    public String getRacePeriod() {
        return this.racePeriod;
    }

    private static String categorizeDate(LocalDate date) {
        int year = 2024; // Base registration year

        // --- Define key boundaries ---
        LocalDate jun1 = LocalDate.of(year, 6, 1);
        LocalDate sep30 = LocalDate.of(year, 9, 30);
        LocalDate oct1 = LocalDate.of(year, 10, 1);
        LocalDate oct31 = LocalDate.of(year, 10, 31);
        LocalDate nov1 = LocalDate.of(year, 11, 1);
        LocalDate febEnd = LocalDate.of(year + 1, 2, isLeapYear(year + 1) ? 29 : 28);
        LocalDate mar1 = LocalDate.of(year + 1, 3, 1);
        LocalDate apr1 = LocalDate.of(year + 1, 4, 1);
        LocalDate apr30 = LocalDate.of(year + 1, 4, 30);
        LocalDate raceDay = getRaceSaturday(year + 1); // First Saturday in May
        LocalDate lateEnd = raceDay.minusDays(1); // Late ends the day before race
        LocalDate may31 = LocalDate.of(year + 1, 5, 31);

        // --- Determine registration category ---
        if (!date.isBefore(jun1) && !date.isAfter(sep30)) {
            return "Registration Not Open";
        } else if (!date.isBefore(oct1) && !date.isAfter(oct31)) {
            return "Super Early";
        } else if (!date.isBefore(nov1) && !date.isAfter(febEnd)) {
            return "Early";
        } else if (!date.isBefore(mar1) && !date.isAfter(apr1)) {
            return "Baseline";
        } else if (date.isAfter(apr1) && !date.isAfter(lateEnd)) {
            return "Late";
        } else if (date.isAfter(lateEnd) && !date.isAfter(may31)) {
            return "Registration Closed";
        } else {
            return "Date not in any registration range.";
        }
    }

    private static boolean isLeapYear(int year) {
        return java.time.Year.of(year).isLeap();
    }

    private static LocalDate getRaceSaturday(int year) {
        LocalDate may1 = LocalDate.of(year, 5, 1);

        // Find first Saturday in May
        while (may1.getDayOfWeek() != java.time.DayOfWeek.SATURDAY) {
            may1 = may1.plusDays(2);
        }

        // If that Saturday’s weekend overlaps with April, skip to next Saturday
        LocalDate sunday = may1.plusDays(1);
        if (sunday.getMonthValue() != 5) {
            may1 = may1.plusWeeks(1);
        }

        return may1;
    }

    private int RaceCost() {
        int raceCost = 0;
        if (getRacePeriod().equalsIgnoreCase("Super Early")) {
            if (race.equalsIgnoreCase("5k")) {
                raceCost = 30;
            } else if (race.equalsIgnoreCase("10k")) {
                raceCost = 50;

            } else if (race.equalsIgnoreCase("Half Marathon")) {
                raceCost = 65;

            } else if (race.equalsIgnoreCase("Marathon")) {
                raceCost = 75;
            }
        } else if (racePeriod.equalsIgnoreCase("Early")) {
            if (race.equalsIgnoreCase("5k")) {
                raceCost = 40;

            } else if (race.equalsIgnoreCase("10k")) {
                raceCost = 55;

            } else if (race.equalsIgnoreCase("Half Marathon")) {
                raceCost = 70;
            } else if (race.equalsIgnoreCase("Marathon")) {
                raceCost = 85;
            }
        } else if (racePeriod.equalsIgnoreCase("Baseline")) {
            if (race.equalsIgnoreCase("5k")) {
                raceCost = 50;
            } else if (race.equalsIgnoreCase("10k")) {
                raceCost = 70;
            } else if (race.equalsIgnoreCase("Half Marathon")) {
                raceCost = 85;
            } else if (race.equalsIgnoreCase("Marathon")) {
                raceCost = 85;
            }
        } else if (racePeriod.equalsIgnoreCase("Late")) {
            if (race.equalsIgnoreCase("5k")) {
                raceCost = 64;
            } else if (race.equalsIgnoreCase("10k")) {
                raceCost = 89;

            } else if (race.equalsIgnoreCase("Half Marathon")) {
                raceCost = 99;
            } else if (race.equalsIgnoreCase("Marathon")) {
                raceCost = 109;

            }
        } else {
            return 0;
        }

        if (getAgeOnRaceDay() >= 65) {
            raceCost -= 5;
            System.out.println("Discount Applied!!");
        }

        return raceCost;

    }

    // TODO: Age of Race(AKA: How old they will be when the race starts)
    public int AgeOnRaceDay() {
        LocalDate run =  LocalDate.of(2025,5,1);
        //LocalDate actualRaceDay = (raceDay != null) ? raceDay : raceDay1;
        return Period.between(this.DOB, run).getYears();
    }

    public LocalDate raceDay(String raceType, int year) {
        // Find the first Saturday of May for that year
        LocalDate mayFirst = LocalDate.of(year, 5, 1);
        LocalDate firstSaturday = mayFirst;

        while (firstSaturday.getDayOfWeek() != DayOfWeek.SATURDAY) {
            firstSaturday = firstSaturday.plusDays(1);
        }

        // TDay = Thursday before first Saturday
        LocalDate tDay = firstSaturday.minusDays(2);

        // Race day logic
        if (raceType.equalsIgnoreCase("5k") || raceType.equalsIgnoreCase("10k")) {
            return tDay.plusDays(2); // Saturday
        } else if (raceType.equalsIgnoreCase("Marathon") || raceType.equalsIgnoreCase("Half Marathon")) {
            return tDay.plusDays(3); // Sunday
        } else {
            return null;
        }
    }

    // Time of race - Date of birth = Age of Race

    public String getRaceType() {
        return race;
    }

    public void setRaceType(String race) {
        this.race = race;

    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public LocalDate getDOB() {

        return DOB;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getRegDate() {
        return regDate1;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setDOB(String dobString) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            this.DOB = LocalDate.parse(dobString, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format for DOB. Please use YYYY-MM-DD.");
            this.DOB = null;
        }
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTimeStamp(LocalDate regDate) {
        this.regDate1 = regDate;
    }

    public int getAgeOnRaceDay() {
        return ageOnRaceDay;
    }

    public LocalDate getRaceDay() {
        return raceDay;
    }

    @Override
    public String toString() {
        if(this.race2 == null){
            return String.format(
                    "\n{\n" +
                            "  Register Timestamp:  %s\n" +
                            "  First Name:          %s\n" +
                            "  Last Name:           %s\n" +
                            "  Email Address:       %s\n" +
                            "  Gender:              %s\n" +
                            "  DOB:                 %s\n" +
                            "  Race:                %s\n" +
                            "  Age on Race Day:     %s\n" +
                            "  Race Date:           %s\n" +
                            "  Registration Period: %s\n" +
                            "  Race Cost:           %s\n" +
                            "}",
                    regDate1, fName, lName, email, gender, DOB,
                    race, ageOnRaceDay, raceDay, racePeriod, raceCost);
        }

        return String.format(
            "\n{\n" +
                            "  Register Timestamp:  %s\n" +
                            "  First Name:          %s\n" +
                            "  Last Name:           %s\n" +
                            "  Email Address:       %s\n" +
                            "  Gender:              %s\n" +
                            "  DOB:                 %s\n" +
                            "  Race1:               %s\n"+
                            "  Race2:               %s\n"+
                            "  Age on Race Day:     %s\n" +
                            "  Race Date1:           %s\n" +
                            "  Race Date2:           %s\n" +
                            "  Registration Period: %s\n" +
                            "  Race Cost:           %s\n" +
                            "}",
                    regDate1, fName, lName, email, gender, DOB,
                    race, race2, ageOnRaceDay, raceDay1, raceDay2, racePeriod, raceCost);
        

        // Two race case
        // return String.format(
        //         "\n{\n" +
        //                 "  Register Timestamp:  %s\n" +
        //                 "  First Name:          %s\n" +
        //                 "  Last Name:           %s\n" +
        //                 "  Email Address:       %s\n" +
        //                 "  Gender:              %s\n" +
        //                 "  DOB:                 %s\n" +
        //                 "  Race 1:              %s on %s\n" +
        //                 "  Race 2:              %s on %s\n" +
        //                 "  Age on Race Day:     %s\n" +
        //                 "  Registration Period: %s\n" +
        //                 "  Total Cost (20% off):%s\n" +
        //                 "}",
        //         regDate1, fName, lName, email, gender, DOB,
        //         race, raceDay1,
        //         race2, raceDay2,
        //         ageOnRaceDay, racePeriod, raceCost);
    }

}
