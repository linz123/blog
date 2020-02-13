package com.linz.util;

import java.sql.*;
import java.util.ArrayList;

public class DbUtil {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public Connection getConnection() {
        return this.connection;
    }

    public DbUtil() {
        this.getDbConnection();
    }

    /**
     * 链接数据库，返回连接对象
     */
    private void getDbConnection() {

        final String SERVER = "localhost";
        final String DBNAME = "manger_web";

        String URL = "jdbc:mysql://" + SERVER + ":3306/" + DBNAME + "?&serverTimezone=GMT";

        final String USERNAME = "root";
        final String PASSWORD = "hu132875";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询数据库
     *
     * @param preparedSql preparedSql 语句
     * @param params      参数列表
     * @return ResultSet 数据集
     */
    public  <T> ResultSet  executeQuery(String preparedSql, ArrayList<T> params) {

        try {
            preparedStatement = connection.prepareStatement(preparedSql);
            if (params != null && !params.isEmpty()) {
                for (int i = 0; i < params.size(); i++) {
                    preparedStatement.setObject(i + 1, params.get(i));
                }
            }
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;

    }

    /**
     * 数据库增删改
     *
     * @param preparedSql preparedSql 语句
     * @param params      参数列表
     * @return boolean 是否成功
     */
    public <E> boolean executeUpdate(String preparedSql, ArrayList<E> params) throws SQLException {

        int num = -1;

        preparedStatement = connection.prepareStatement(preparedSql);
        if (params != null) {
            for (int i = 0; i < params.size(); i++) {
                preparedStatement.setObject(i + 1, params.get(i));
            }
        }
        num = preparedStatement.executeUpdate();

        return num > 0;
    }


    public <E> ResultSet executeUpdateMulti(String preparedSql, ArrayList<E> params) throws SQLException {

        preparedStatement = connection.prepareStatement(preparedSql, Statement.RETURN_GENERATED_KEYS);
        if (params != null) {
            for (int i = 0; i < params.size(); i++) {
                preparedStatement.setObject(i + 1, params.get(i));
            }
        }
        preparedStatement.executeUpdate();

        return preparedStatement.getGeneratedKeys();
    }


    public PreparedStatement getPrepareStatement(String preparedSql) throws SQLException {
        return connection.prepareStatement(preparedSql);
    }


    //关闭数据库连接
    public void closeDbConnection() {

        try {
            if (this.resultSet != null) {
                this.resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (this.preparedStatement != null) {
                    this.preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (this.connection != null) {
                        this.connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
