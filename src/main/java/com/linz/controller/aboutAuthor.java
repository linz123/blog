package com.linz.controller;

import com.linz.util.DbUtil;
import com.linz.util.Result;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;


@WebServlet("/aboutAuthor")
public class aboutAuthor extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  用户id
        long uid = Long.parseLong(req.getParameter("uid"));

        String sql =
                "SELECT\n" +
                        "	articles.user_id,\n" +
                        "	COUNT(*) AS total,\n" +
                        "	SUM(articles.article_views) AS article_views,\n" +
                        "	SUM(\n" +
                        "		articles.article_like_count\n" +
                        "	) AS article_like_counts,\n" +
                        "	SUM(\n" +
                        "		articles.article_comment_count\n" +
                        "	) AS article_comment_counts,\n" +
                        "	manger_user.username,\n" +
                        "	manger_user.email,\n" +
                        "	manger_user.describe\n" +
                        "FROM\n" +
                        "	articles\n" +
                        "LEFT JOIN manger_user ON articles.user_id = manger_user.uid\n" +
                        "WHERE\n" +
                        "	articles.user_id = " + uid + "\n" +
                        "GROUP BY\n" +
                        "	articles.user_id;";

        DbUtil dbUtil = new DbUtil();

        ResultSet resultSet = dbUtil.executeQuery(sql, null);

        JSONArray data = new JSONArray();
        try {
            if (resultSet.next()) {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("user_id", resultSet.getLong("user_id"));
                hashMap.put("total", resultSet.getLong("total"));
                hashMap.put("article_views", resultSet.getLong("article_views"));
                hashMap.put("article_like_counts", resultSet.getLong("article_like_counts"));
                hashMap.put("article_comment_counts", resultSet.getLong("article_comment_counts"));
                hashMap.put("username", resultSet.getString("username"));
                hashMap.put("email", resultSet.getString("email"));
                hashMap.put("describe", resultSet.getString("describe"));
                data.add(hashMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbUtil.closeDbConnection();
        }
        System.out.println(data);
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(Result.success(data).toString());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
