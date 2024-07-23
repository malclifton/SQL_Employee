import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertEmployees {
    public static void insertEmployee(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter salary: ");
        String salary = scanner.nextLine();
        System.out.print("Enter hire date (yyyy-MM-dd): ");
        String hireDateString = scanner.nextLine();
        Date hireDate = Date.valueOf(hireDateString);

        PreparedStatement statement = connection
                .prepareStatement(
                        "INSERT INTO employees (Fname, Lname, email, HireDate, Salary) VALUES (?, ?, ?, ?, ?1)");
        statement.setString(1, firstName);
        statement.setString(2, lastName);
        statement.setString(3, email);
        statement.setDate(4, hireDate);
        statement.setString(5, salary);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Employee inserted successfully.");
        }
    }
}
