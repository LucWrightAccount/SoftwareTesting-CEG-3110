package DataRegistration.src.pkg1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DateOfRegistrationTest {
    
    public static List<LocalDate> getDateOfRegistrationTest(){
        List<LocalDate> dates = new ArrayList<>();

        // Registration Not Open
        dates.add(LocalDate.of(2026, 9, 30));   // Registration Before Oct 1
        // Registration Closed
        dates.add(LocalDate.of(2027, 5, 2));    // Registration After Close Date

        //Supper Early
         dates.add(LocalDate.of(2026, 10, 5));   // In-Bound Testing Super Early
        dates.add(LocalDate.of(2026, 10, 1));   // Boundary Testing Min - Super Early
        dates.add(LocalDate.of(2026, 10, 31));  // Boundary Testing Max - Super Early

        // Early
        dates.add(LocalDate.of(2026, 11, 1));   // Boundary Testing Min - Early
        dates.add(LocalDate.of(2026, 11, 2));   // In-Bound Testing Early
        dates.add(LocalDate.of(2027, 2, 28));   // Boundary Testing Max - Early

        // Baseline
        dates.add(LocalDate.of(2027, 3, 1));    // Boundary Testing Min - Baseline
        dates.add(LocalDate.of(2027, 3, 15));   // In-Bound Testing Baseline
        dates.add(LocalDate.of(2027, 4, 1));    // Boundary Testing Max - Baseline

        // Late
        dates.add(LocalDate.of(2027, 4, 2));    // In-Bound Testing Late
        dates.add(LocalDate.of(2027, 4, 24));   // Boundary Testing Min - Late
        dates.add(LocalDate.of(2027, 4, 29));    // Boundary Testing Max - Late

        return dates;
    }

    
    
}
