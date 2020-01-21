package com.linz.controller;

import com.linz.util.DbUtil;
import com.linz.util.Result;
import net.sf.json.JSONArray;

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


@WebServlet("/hotArticleList")
public class ArticleHot extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sql =
                "SELECT\n" +
                        "	article_title,\n" +
                        "	article_id,\n" +
                        "	article_like_count\n" +
                        "FROM\n" +
                        "	articles\n" +
                        "ORDER BY\n" +
                        "	article_like_count DESC\n" +
                        "LIMIT 6;";

        DbUtil dbUtil = new DbUtil();
        ResultSet resultSet = dbUtil.executeQuery(sql, null);

        JSONArray jsonArray = new JSONArray();

        try {
            while (resultSet.next()) {
                HashMap<String, Object> hashMap = new HashMap<String, Object>();
                hashMap.put("article_title", resultSet.getString("article_title"));
                hashMap.put("article_id", resultSet.getLong("article_id"));
                hashMap.put("article_like_count", resultSet.getLong("article_like_count"));

                jsonArray.add(hashMap);
            }

            PrintWriter printWriter = resp.getWriter();
            printWriter.write(Result.success(jsonArray).toString());
            dbUtil.closeDbConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
