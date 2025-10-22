package DataRegistration.src.pkg1;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;

public class UserInfo {
    private String fName, lName, gender, email, race;
    private LocalDate regDate, raceDay, DOB;
    private int ageOnRaceDay;

    UserInfo(String fName, String lName, LocalDate DOB, String gender, String email, LocalDate registrationDate,
            String raceType) {
        this.fName = fName;
        this.lName = lName;
        this.DOB = DOB;
        this.gender = gender;
        this.email = email;
        this.regDate = registrationDate;
        this.race = raceType;
        this.raceDay = raceDay(raceType, registrationDate.getYear());

        this.ageOnRaceDay = AgeOnRaceDay();

    }

    UserInfo() {

    }

    // TODO: Age of Race(AKA: How old they will be when the race starts)
    public int AgeOnRaceDay() {
        return Period.between(this.DOB, this.raceDay).getYears();

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
        return regDate;
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
        this.regDate = regDate;
    }

    public int getAgeOnRaceDay() {
        return ageOnRaceDay;
    }

    public LocalDate getRaceDay() {
        return raceDay;
    }

    @Override
    public String toString() {
        return String.format(
                "[Register Timestamps: %s, FirstName: %s, LastName: %s, Email Address: %s, " +
                        "Gender: %s, DOB: %s, Type of Race: %s, Age of Race: %s, RaceDate: %s]",
                getRegDate(), getfName(), getlName(), getEmail(),
                getGender(), getDOB(), getRaceType(), getAgeOnRaceDay(), getRaceDay());
    }

}
