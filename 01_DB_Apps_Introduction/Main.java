import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        //0. Username and password
        Properties props = new Properties();
        props.setProperty("user", "....");
        props.setProperty("password", "...........");

        //1. Establish a connection to the database - connection to the base
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/diablo", props);

        //2. Username and statement - заявка
        PreparedStatement statement = connection.prepareStatement(
                "SELECT user_name, first_name, last_name, COUNT(users_games.id) AS games_played FROM users" +
                        " Join users_games ON users_games.user_id = users.id" +
                        " WHERE user_name = ?" +
                        " GROUP BY users_games.user_id");

        //3. Output - към заявката закачваме параметър
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();

        // Обхождаме резултата като взимаме данните от базата и ги показваме по удобен за нас начин
        if (resultSet.next()) {
            String dbUserName = resultSet.getString("user_name");
            String dbFirstName = resultSet.getString("first_name");
            String dbLastName = resultSet.getString("last_name");
            Integer dbGamesCount = resultSet.getInt("games_played");

            System.out.printf("User: %s\n%s %s has played %d games\n",
                    dbUserName, dbFirstName, dbLastName, dbGamesCount);
        } else {
            System.out.println("No such user exists");
        }
    }
}
