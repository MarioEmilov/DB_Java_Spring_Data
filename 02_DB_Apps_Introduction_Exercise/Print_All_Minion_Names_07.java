import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Print_All_Minion_Names_07 {
    public static void main(String[] args) throws SQLException {

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://127.0.0.1:3306/minions_db", properties);


        PreparedStatement minionsName = connection.prepareStatement(
                "SELECT name FROM minions;");

        ResultSet result = minionsName.executeQuery();
        List<String> minions = new ArrayList<>();
        while (result.next()){
            minions.add(result.getString(1)); // or "name"
        }
        int start = 0;
        int end = minions.size() - 1;
        for (int i = 0; i < minions.size(); i++) {
            System.out.println(i % 2 == 0 ? minions.get(start++) : minions.get(end--));
        }
    }
}
