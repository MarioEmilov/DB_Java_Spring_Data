import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Increase_Minions_Age_08 {
    public static void main(String[] args) throws SQLException {

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", properties);

        Scanner scanner = new Scanner(System.in);
        String[] minionIds = scanner.nextLine().split("\\s+");

        for (String minionId : minionIds) {
            PreparedStatement updateMinions = connection.prepareStatement(
                    """
                            UPDATE minions
                            SET `name` = LOWER(`name`),
                            `age` = `age` + 1
                            WHERE id = ?;""");
            updateMinions.setInt(1, Integer.parseInt(minionId));
            updateMinions.executeUpdate();
        }

        PreparedStatement selectMinions = connection.prepareStatement(
                "SELECT `name`, `age` FROM minions ORDER BY `id`;");

        ResultSet minionsResultSet = selectMinions.executeQuery();

        while (minionsResultSet.next()) {
            String minionName = minionsResultSet.getString("name");
            int minionAge = minionsResultSet.getInt("age");
            System.out.printf("%s %s\n", minionName, minionAge);
        }
        connection.close();
    }
}
