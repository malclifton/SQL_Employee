import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteEmployees {
    public static void deleteEmployee(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("Delete Employee");
        System.out.print("Enter employee: ");
        int empId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        PreparedStatement statement = connection.prepareStatement("DELETE FROM employees WHERE empid = ?");
        statement.setInt(1, empId);

        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Employee deleted successfully.");
        } else {
            System.out.println("No employee found with ID " + empId);
        }
    }
}