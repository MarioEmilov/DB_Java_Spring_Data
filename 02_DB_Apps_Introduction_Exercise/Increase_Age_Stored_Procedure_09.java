import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Increase_Age_Stored_Procedure_09 {
    public static void main(String[] args) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", properties);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter minion id:");
        int minion_id = Integer.parseInt(scanner.nextLine());
/*
Please create the procedure in MySQL
DELIMITER //
CREATE PROCEDURE usp_get_older(minion_id INT)
BEGIN
    UPDATE minions
    SET age = age + 1
    WHERE id = minion_id;
end //
DELIMITER ;
*/
        CallableStatement callableStatement = connection.prepareCall("CALL usp_get_older(?);");
        callableStatement.setInt(1, minion_id);
        // int affectedRows = callableStatement.executeUpdate();
        PreparedStatement ps = connection.prepareStatement("SELECT name, age FROM minions WHERE id = ?;");
        ps.setInt(1, minion_id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            System.out.printf("%s %d %n", rs.getString("name"), rs.getInt("age"));
        }
    }
}
