import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class ManageEmployees {
    private static final String url = "jdbc:mysql://localhost:3306/employeedata";
    private static final String user = "root";
    private static final String password = "Ch00seG00se";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("1. Insert Employee");
                System.out.println("2. Update Employee");
                System.out.println("3. Delete Employee");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (choice) {
                    case 1:
                        InsertEmployees.insertEmployee(connection, scanner);
                        break;
                    case 2:
                        UpdateEmployees.updateEmployee(connection, scanner);
                        break;
                    case 3:
                        DeleteEmployees.deleteEmployee(connection, scanner);
                        break;
                    case 4:
                        connection.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
