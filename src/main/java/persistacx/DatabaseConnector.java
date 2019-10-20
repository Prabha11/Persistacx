package persistacx;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import java.sql.*;
import java.util.HashMap;

public class DatabaseConnector {

    private static DatabaseConnector databaseConnector = new DatabaseConnector();
    private Connection connection;

    private DatabaseConnector() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/stacx_pos",
                    "root",
                    "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean setConnection(String url, String dataBaseName, String userName, String password) throws Exception {
        try {
            connection = DriverManager.getConnection(
                    String.format("jdbc:mysql://%s/%s", url, dataBaseName),
                    userName,
                    password
            );
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return true;
    }

    public static DatabaseConnector getInstance() {
        return databaseConnector;
    }

    public boolean executeSaveQuery(String query) throws Exception {

        boolean success = false;
        Statement statement;

        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            success = true;

        } catch (MySQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            throw e;
        }
        return success;
    }

    public ResultSet executeSelectQuery(String query) throws Exception {

        ResultSet resultSet = null;
        Statement statement;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return resultSet;
    }
}
