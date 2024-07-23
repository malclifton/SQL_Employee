import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateEmployees {
    public static void updateEmployee(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("Update Employee");
        System.out.print("Enter employee ID: ");
        int empId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        // Prompt user for fields to update
        System.out.print("Enter new first name (or leave blank): ");
        String newFirstName = scanner.nextLine();
        System.out.print("Enter new last name (or leave blank): ");
        String newLastName = scanner.nextLine();
        System.out.print("Enter new email (or leave blank): ");
        String newEmail = scanner.nextLine();
        System.out.print("Enter new hire date (yyyy-MM-dd, or leave blank): ");
        String newHireDateString = scanner.nextLine();
        System.out.print("Enter new salary (or leave blank): ");
        String newSalaryString = scanner.nextLine();

        // Construct the SQL update statement based on the fields entered
        StringBuilder updateSql = new StringBuilder("UPDATE employees SET ");
        if (!newFirstName.isEmpty()) {
            updateSql.append("Fname = ?, ");
        }
        if (!newLastName.isEmpty()) {
            updateSql.append("Lname = ?, ");
        }
        if (!newEmail.isEmpty()) {
            updateSql.append("email = ?, ");
        }
        if (!newHireDateString.isEmpty()) {
            updateSql.append("HireDate = ?, ");
        }
        if (!newSalaryString.isEmpty()) {
            updateSql.append("Salary = ?, ");
        }
        // Remove the last comma and space
        updateSql.setLength(updateSql.length() - 2);
        updateSql.append(" WHERE empid = ?");

        PreparedStatement statement = connection.prepareStatement(updateSql.toString());
        int parameterIndex = 1;
        if (!newFirstName.isEmpty()) {
            statement.setString(parameterIndex++, newFirstName);
        }
        if (!newLastName.isEmpty()) {
            statement.setString(parameterIndex++, newLastName);
        }
        if (!newEmail.isEmpty()) {
            statement.setString(parameterIndex++, newEmail);
        }
        if (!newHireDateString.isEmpty()) {
            Date newHireDate = Date.valueOf(newHireDateString);
            statement.setDate(parameterIndex++, newHireDate);
        }
        if (!newSalaryString.isEmpty()) {
            statement.setString(parameterIndex++, newSalaryString);
        }
        // Set empid as the last parameter
        statement.setInt(parameterIndex, empId);

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Employee updated successfully.");
        } else {
            System.out.println("No employee found with ID " + empId);
        }
    }
}
