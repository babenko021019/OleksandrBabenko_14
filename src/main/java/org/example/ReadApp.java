package org.example;

import java.sql.*;
import java.util.LinkedHashSet;

public class ReadApp {

    private static Statement stmt;
    private static ResultSet results;

    public static void main(String[] args) {

        String query = "SELECT FileName FROM san_content_999_calculated";
//			String queryFromTif = "SELECT FileName FROM san_content_999_calculated WHERE FileName LIKE '%.tif'";
        try (Connection connection = ConnectionToDB.createNewConnection()){
            stmt = connection.createStatement();
            results = stmt.executeQuery(query);

            LinkedHashSet<String> codingList = new LinkedHashSet<>();
            LinkedHashSet<String> copyingList = new LinkedHashSet<>();

            while (results.next()){
                String fileName = results.getString("FileName");
                if (fileName.endsWith(".tif")) {
                    codingList.add(fileName.substring((fileName.indexOf('\\') + 1), fileName.lastIndexOf('\\')));
                } else if ((fileName.endsWith(".mov")) || (fileName.endsWith(".avi"))){
                    codingList.add(fileName.substring((fileName.indexOf('\\') + 1)));
                } else if (fileName.contains(".")) {
                    copyingList.add(fileName.substring((fileName.indexOf('\\') + 1)));
                }
            }
            System.out.println("Список файлов\\директорий на кодирование:");
            for (String list: codingList) {
                System.out.println(list);
            }
            System.out.println("\nСписок файлов\\директорий на копирование:");
            for (String list: copyingList) {
                System.out.println(list);
            }
        }
        catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }
}
