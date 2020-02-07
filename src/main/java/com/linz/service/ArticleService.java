package com.linz.service;

import com.linz.util.DbUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ArticleService {


    /**
     * 此ip是否对此文章点赞
     *
     * @param ipAddress  ip地址
     * @param article_id 文章id
     * @return boolean
     */
    public static boolean isCommend(String ipAddress, Long article_id) {

        DbUtil dbUtil = new DbUtil();

        String userIsCommend =
                "SELECT\n" +
                        "	*\n" +
                        "FROM\n" +
                        "	commend\n" +
                        "WHERE\n" +
                        "	ip_address = ?\n" +
                        "AND article_id = ?;";

        ArrayList<Object> paras = new ArrayList<>();
        paras.add(ipAddress);
        paras.add(article_id);
        ResultSet resultSet = dbUtil.executeQuery(userIsCommend, paras);
        boolean isCommend = false;

        try {
            isCommend = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbUtil.closeDbConnection();
        }

        return isCommend;
    }
}
