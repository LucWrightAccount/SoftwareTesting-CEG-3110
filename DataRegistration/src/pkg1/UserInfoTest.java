package DataRegistration.src.pkg1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserInfoTest {

    // Static method that returns test users
    public static List<UserInfo> getTestUsers() {
        List<UserInfo> users = new ArrayList<>();

        // Test Case 1 - 5K registration
        users.add(new UserInfo(
                "Alice",
                "Johnson",
                LocalDate.of(1999, 1, 15),
                "Female",
                "alice.johnson@example.com",
                LocalDate.of(2025, 3, 1),
                "5K"
        ));

        // Test Case 2 - 10K registration
        users.add(new UserInfo(
                "Bob",
                "Smith",
                LocalDate.of(1988, 6, 10),
                "Male",
                "bob.smith@example.com",
                LocalDate.of(2025, 3, 5),
                "10K"
        ));

        // Test Case 3 - Half Marathon registration
        users.add(new UserInfo(
                "Carol",
                "Martinez",
                LocalDate.of(1995, 9, 22),
                "Female",
                "carol.martinez@example.com",
                LocalDate.of(2025, 3, 10),
                "Half Marathon"
        ));

        // Test Case 4 - Marathon registration
        users.add(new UserInfo(
                "David",
                "Lee",
                LocalDate.of(2000, 11, 30),
                "Male",
                "david.lee@example.com",
                LocalDate.of(2025, 4, 2),
                "Marathon"
        ));

        // Test Case 5 - User registered for both Half and Marathon (valid)
        users.add(new UserInfo(
                "Ella",
                "Brown",
                LocalDate.of(1992, 12, 12),
                "Female",
                "ella.brown@example.com",
                LocalDate.of(2025, 4, 15),
                "Half Marathon"
        ));
        users.add(new UserInfo(
                "Ella",
                "Brown",
                LocalDate.of(1992, 12, 12),
                "Female",
                "ella.brown@example.com",
                LocalDate.of(2025, 4, 15),
                "Marathon"
        ));

        // Test Case 6 - Invalid: both 5K and 10K for same person
        users.add(new UserInfo(
                "Frank",
                "Davis",
                LocalDate.of(1990, 8, 25),
                "Male",
                "frank.davis@example.com",
                LocalDate.of(2025, 4, 20),
                "5K"
        ));
        users.add(new UserInfo(
                "Frank",
                "Davis",
                LocalDate.of(1990, 8, 25),
                "Male",
                "frank.davis@example.com",
                LocalDate.of(2025, 4, 20),
                "10K"
        ));

        return users;
    }
}
