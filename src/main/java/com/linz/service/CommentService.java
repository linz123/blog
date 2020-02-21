package com.linz.service;


import com.linz.util.DbUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentService {

    public static void addComment(Long articleId, DbUtil dbUtil) throws SQLException {

        String sql =
                "UPDATE articles\n" +
                        "SET article_comment_count = article_comment_count + 1\n" +
                        "WHERE\n" +
                        "	article_id = " + articleId + ";";
        dbUtil.executeUpdate(sql, null);

    }

    public static String getUserNameById(Long uid, DbUtil dbUtil) throws SQLException {

        String username = null;

        String sql = "SELECT\n" +
                "	username\n" +
                "FROM\n" +
                "	manger_user\n" +
                "WHERE\n" +
                "	uid = " + uid + ";";

        ResultSet resultSet = dbUtil.executeQuery(sql, null);

        if (resultSet.next()) {
            username = resultSet.getString("username");
        }
        return username;
    }

    public static long getCommentCount(long comment_id, DbUtil dbUtil) throws SQLException {

        long count =  0;

        String sql = "SELECT\n" +
                "	COUNT(*) AS count\n" +
                "FROM\n" +
                "	reply WHERE comment_id =" + comment_id + ";";

        ResultSet resultSet = dbUtil.executeQuery(sql, null);

        if (resultSet.next()) {
            count = resultSet.getLong("count");
        }

        return count;
    }

}
