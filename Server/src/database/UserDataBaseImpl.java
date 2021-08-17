package database;

import exceptions.SQLNoDataException;
import exceptions.SQLUniqueException;
import general.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDataBaseImpl implements UserDataBase {
    private final static String URL = "jdbc:postgresql://localhost:5432/postgres";
    private final static String login = "postgres";
    private final static String password = "makarov";

    @Override
    public User getUser(User user) throws SQLNoDataException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELCET * FROM users WHERE username = ?")) {
            statement.setString(1, user.getUserName());
            ResultSet res = statement.executeQuery();
            res.next();
            return new User(res.getString(1), res.getString(2));
        } catch (SQLException e) {
            throw new SQLNoDataException();
        }
    }

    @Override
    public void insertUser(User user) throws SQLUniqueException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO users (login, password) VALUES(?,?)")) {
            System.out.println("abas");
            statement.setString(1, user.getUserName());
            System.out.println("abas2");
            statement.setString(2, user.getPassword());
            System.out.println("abas3");
            statement.execute();
            System.out.println("abas4");
            statement.close();
            System.out.println("abas final");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLUniqueException();
        }
    }

    @Override
    public void deleteUser(User user) throws SQLNoDataException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE username = ?")) {
            statement.setString(1, user.getUserName());
            statement.execute();
        } catch (SQLException e) {
            throw new SQLNoDataException();
        }
    }

    @Override
    public ArrayList<User> getUsers() throws SQLNoDataException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users")) {
            ArrayList<User> arrayList = new ArrayList<>();
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                String log = res.getString(1);
                String password = res.getString(2);
                User user = new User(log);
                user.setPassword(password);
                arrayList.add(user);
            }
            return arrayList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLNoDataException();
        }
    }

    private Connection getConnection() throws SQLException {
        // DataBaseConnector dataBaseConnector = new DataBaseConnector();
        // dataBaseConnector.connect();
        // return dataBaseConnector.getCon();
        return DriverManager.getConnection(URL, login, password);

    }
}