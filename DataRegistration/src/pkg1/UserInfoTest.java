package DataRegistration.src.pkg1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserInfoTest {

        // Static method that returns test users
        public static List<UserInfo> getTestUsers() {
                List<UserInfo> users = new ArrayList<>();

                // Super Early (5K) & 65 years old
                users.add(new UserInfo(
                                "Alice",
                                "Johnson",
                                LocalDate.of(1960, 1, 1),
                                "Female",
                                "alice.johnson@gmail.com",
                                LocalDate.of(2024, 10, 15),
                                "5K"));

                // Super Early (10K) & > 65
                users.add(new UserInfo(
                                "Alice",
                                "Johnson",
                                LocalDate.of(1959, 1, 1),
                                "Female",
                                "alice.johnson@gmail.com",
                                LocalDate.of(2024, 10, 15),
                                "10K"));

                // Super Early (Half Marathon) & < 65
                users.add(new UserInfo(
                                "Alice",
                                "Johnson",
                                LocalDate.of(2000, 1, 1),
                                "Female",
                                "alice.johnson@gmail.com",
                                LocalDate.of(2024, 10, 15),
                                "Half Marathon"));

                // Super Early (Marathon) & < 65
                users.add(new UserInfo(
                                "Alice",
                                "Johnson",
                                LocalDate.of(2000, 1, 1),
                                "Female",
                                "alice.johnson@gmail.com",
                                LocalDate.of(2024, 10, 15),
                                "Marathon"));

                // Early (5K) & < 65
                users.add(new UserInfo(
                                "Alice",
                                "Johnson",
                                LocalDate.of(2000, 1, 1),
                                "Female",
                                "alice.johnson@gmail.com",
                                LocalDate.of(2024, 12, 15),
                                "5K"));

                // Early (10K) & 65 years old
                users.add(new UserInfo(
                                "Alice",
                                "Johnson",
                                LocalDate.of(1960, 1, 1),
                                "Female",
                                "alice.johnson@gmail.com",
                                LocalDate.of(2024, 12, 15),
                                "10K"));

                // Early (Half Marathon) & > 65
                users.add(new UserInfo(
                                "Alice",
                                "Johnson",
                                LocalDate.of(1959, 1, 1),
                                "Female",
                                "alice.johnson@gmail.com",
                                LocalDate.of(2024, 12, 15),
                                "Half Marathon"));

                // Early (Marathon) & < 65
                users.add(new UserInfo(
                                "Alice",
                                "Johnson",
                                LocalDate.of(2000, 1, 1),
                                "Female",
                                "alice.johnson@gmail.com",
                                LocalDate.of(2024, 12, 15),
                                "Marathon"));

                // Baseline (5K) & < 65
                users.add(new UserInfo(
                                "Alice",
                                "Johnson",
                                LocalDate.of(2000, 1, 1),
                                "Female",
                                "alice.johnson@gmail.com",
                                LocalDate.of(2025, 3, 15),
                                "5K"));

                // Baseline (10K) & < 65
                users.add(new UserInfo(
                                "Alice",
                                "Johnson",
                                LocalDate.of(1961, 1, 1),
                                "Female",
                                "alice.johnson@gmail.com",
                                LocalDate.of(2025, 3, 15),
                                "10K"));

                // Baseline (Half Marathon) & 65 years old
                users.add(new UserInfo(
                                "Alice",
                                "Johnson",
                                LocalDate.of(1960, 1, 1),
                                "Female",
                                "alice.johnson@gmail.com",
                                LocalDate.of(2025, 3, 15),
                                "Half Marathon"));

                // Baseline (Marathon) & > 65
                users.add(new UserInfo(
                                "Alice",
                                "Johnson",
                                LocalDate.of(1950, 1, 1),
                                "Female",
                                "alice.johnson@gmail.com",
                                LocalDate.of(2025, 3, 15),
                                "Marathon"));

                // Late (5K) & > 65
users.add(new UserInfo(
        "Alice",
        "Johnson",
        LocalDate.of(1950, 1, 1),
        "Female",
        "alice.johnson@gmail.com",
        LocalDate.of(2025, 4, 15),
        "5K"
));

// Late (10K) & < 65
users.add(new UserInfo(
        "Alice",
        "Johnson",
        LocalDate.of(2000, 1, 1),
        "Female",
        "alice.johnson@gmail.com",
        LocalDate.of(2025, 4, 15),
        "10K"
));

// Late (Half Marathon) & < 65
users.add(new UserInfo(
        "Alice",
        "Johnson",
        LocalDate.of(1962, 1, 1),
        "Female",
        "alice.johnson@gmail.com",
        LocalDate.of(2025, 4, 15),
        "Half Marathon"
));

// Late (Marathon) & > 65
users.add(new UserInfo(
        "Alice",
        "Johnson",
        LocalDate.of(1900, 1, 1),
        "Female",
        "alice.johnson@gmail.com",
        LocalDate.of(2025, 4, 15),
        "Marathon"
));


                return users;
        }
}
