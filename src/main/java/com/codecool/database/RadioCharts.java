package com.codecool.database;


import java.sql.*;

public class RadioCharts {

    private final String DB_URL;
    private final String USER;
    private final String PASS;


    public RadioCharts(String db_url, String user, String password) {
        DB_URL = db_url;
        USER = user;
        PASS = password;
    }

    public String getMostPlayedSong() {
        String result = "";
        String SQL = "select song, sum(times_aired) as sum, artist from music_broadcast group by song order by sum desc, artist asc";

        try {

            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                result = resultSet.getString(1);
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return result;
    }

    public String getMostActiveArtist() {
        String result = "";
        String SQL = "select artist, count(distinct song) as song_count from music_broadcast group by artist order by song_count desc";

        try {

            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                result = resultSet.getString(1);
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return result;
    }
}
