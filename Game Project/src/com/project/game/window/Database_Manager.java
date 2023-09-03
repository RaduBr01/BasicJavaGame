package com.project.game.window;
import java.sql.*;

// conexiunea cu baza de date

public class Database_Manager {
    public Connection connection;

    public Database_Manager() {
        try {
            Class.forName("org.sqlite.JDBC");
            String databaseName = "game_database.db";
            connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
            createTable();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTable() {
        String query = "CREATE TABLE IF NOT EXISTS game_data (" +
                "Killcount INTEGER, " +
                "Player_Pos_X INTEGER, " +
                "Player_Pos_Y INTEGER " +
                ")";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertData(int killcount, int posX, int posY) {
        String query = "INSERT INTO game_data (Killcount, Player_Pos_X, Player_Pos_Y) " +
                "VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, killcount);
            preparedStatement.setInt(2, posX);
            preparedStatement.setInt(3, posY);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printData() {
        String query = "SELECT * FROM game_data";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int killcount = resultSet.getInt("Killcount");
                int posX = resultSet.getInt("Player_Pos_X");
                int posY = resultSet.getInt("Player_Pos_Y");
                System.out.println("Killcount: " + killcount +
                        ", Player Pos X: " + posX +
                        ", Player Pos Y: " + posY );
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeDatabase() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
