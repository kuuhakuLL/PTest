package com.kuuhakull;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class TestLoader {
    static private String userName = "kuuhakull";
    static private String password = "an123ton22LL";
    static private String url = "jdbc:mysql://localhost:3306/PTests";

    public static HashMap<Integer,String> selectTests(int id) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        HashMap<Integer,String> tests = new HashMap<>();
        try (Connection connection = DriverManager.getConnection(url, userName, password)) {
            PreparedStatement preparedTest = connection.prepareStatement(
                    "Select IdTest, Title From Tests" +
                            " Where idDirection = ?;");
            preparedTest.setInt(1, id);
            ResultSet resultTest = preparedTest.executeQuery();
            while (resultTest.next()) {
                tests.put(resultTest.getInt(1),resultTest.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tests;
    }

    public static ArrayList<Qwest> selectQwests( int Id) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        ArrayList<Qwest> qwests= new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, userName, password)) {
            PreparedStatement preparedQwests = connection.prepareStatement(
                    "Select IdQwest, Title, TypeQwest From Qwests " +
                            "Where idTest = ?;");
            preparedQwests.setInt(1, Id);
            ResultSet resultQwests = preparedQwests.executeQuery();
            while (resultQwests.next()) {
                int id = resultQwests.getInt(1);
                String qwest = resultQwests.getString(2);
                byte typeQwest = resultQwests.getByte(3);
                ArrayList<String> answer = selectAnswers(id);
                ArrayList<String> dstractor = selectDstractor(id);
                qwests.add(new Qwest(id, qwest, answer, dstractor, typeQwest));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return qwests;
    }

    static public ArrayList<String> selectAnswers(int idQwests) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        ArrayList<String> answer = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, userName, password)) {
            PreparedStatement preparedAnswer = connection.prepareStatement(
                    "Select Answer From Answers " +
                            "Where idQwest = ?;");
            preparedAnswer.setInt(1, idQwests);
            ResultSet resultAnswer = preparedAnswer.executeQuery();
            while (resultAnswer.next()) {
                answer.add(resultAnswer.getString(1));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return answer;
    }

    static public ArrayList<String> selectDstractor(int idQwests) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        ArrayList<String> dstractor = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, userName, password)) {
            PreparedStatement preparedDstractor = connection.prepareStatement(
                    "Select Dstractor From Dstractors " +
                            "Where idQwest = ?;");
            preparedDstractor.setInt(1, idQwests);
            ResultSet resultDstractor = preparedDstractor.executeQuery();
            while (resultDstractor.next()) {
                dstractor.add(resultDstractor.getString(1));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return dstractor;
    }
}