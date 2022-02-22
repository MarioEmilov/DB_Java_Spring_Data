import java.sql.*;
import java.util.Properties;

public class Get_Villains_Names_02 {
    public static void main(String[] args) throws SQLException {

        //0. Username and password
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");

        //1. Establish a connection to the database - connection to the base
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        //2. Username and statement - заявка
        PreparedStatement statement = connection.prepareStatement(
                "SELECT name, COUNT(distinct mv.minion_id) AS minion_count FROM villains AS v" +
                        " JOIN minions_villains AS mv ON mv.villain_id = v.id" +
                        " GROUP BY mv.villain_id" +
                        " HAVING minion_count > ?" +
                        " ORDER BY minion_count DESC;");

        statement.setInt(1, 15);

        //3. Output - към заявката закачваме параметър
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String villainName = resultSet.getString("name");
            int minionCount = resultSet.getInt("minion_count");

            System.out.println(villainName + " " + minionCount);
        }
        connection.close();
    }
}