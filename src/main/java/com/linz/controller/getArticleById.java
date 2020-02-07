package com.linz.controller;

import com.linz.service.ArticleService;
import com.linz.util.DbUtil;
import com.linz.util.IpAddressUtil;
import com.linz.util.Result;
import com.linz.util.ResultCode;
import com.mysql.cj.util.StringUtils;
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

@WebServlet("/getArticleById")
public class getArticleById extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String article_id = req.getParameter("article_id");
        long long_article_id;

        // ip 地址
        final String ipAddress = IpAddressUtil.getIpAddress(req);


        PrintWriter printWriter = resp.getWriter();
        DbUtil dbUtil = new DbUtil();
        if (!StringUtils.isNullOrEmpty(article_id)) {
            long_article_id = Long.parseLong(req.getParameter("article_id"));
            String sql = "" +
                    "SELECT\n" +
                    "	user_id,\n" +
                    "	articles.article_id,\n" +
                    "	article_title,\n" +
                    "	article_views,\n" +
                    "   article_content,\n" +
                    "	article_comment_count,\n" +
                    "	article_date,\n" +
                    "	article_like_count,\n" +
                    "	username,\n" +
                    "	label_parent_name,\n" +
                    "	label_name\n" +
                    "   FROM\n" +
                    "	(\n" +
                    "		(\n" +
                    "			articles \n" +
                    "			INNER JOIN manger_user ON articles.user_id = manger_user.uid AND \n" +
                    "           articles.article_id = " + long_article_id + "\n" +
                    "		)\n" +
                    "		LEFT JOIN  article_set_label ON articles.article_id = article_set_label.article_id\n" +
                    "	)\n" +
                    "LEFT JOIN label ON label.label_id = article_set_label.label_id  ;";
            ResultSet resultSet = dbUtil.executeQuery(sql, null);

            String articleViewsSql =
                    "UPDATE articles\n" +
                            "SET article_views = article_views + 1\n" +
                            "WHERE\n" +
                            "article_id = " + long_article_id + ";";
            try {
                dbUtil.executeUpdate(articleViewsSql, null);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            try {
                while (resultSet.next()) {
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("article_id", resultSet.getLong("article_id"));
                    hashMap.put("user_id", resultSet.getLong("user_id"));
                    hashMap.put("article_content", resultSet.getString("article_content"));
                    hashMap.put("article_title", resultSet.getString("article_title"));
                    hashMap.put("article_views", resultSet.getLong("article_views"));
                    hashMap.put("article_comment_count", resultSet.getLong("article_comment_count"));
                    hashMap.put("article_date", resultSet.getTimestamp("article_date"));
                    hashMap.put("article_like_count", resultSet.getLong("article_like_count"));
                    hashMap.put("username", resultSet.getString("username"));
                    hashMap.put("label_parent_name", resultSet.getString("label_parent_name"));
                    hashMap.put("label_name", resultSet.getString("label_name"));
                    boolean commend = ArticleService.isCommend(ipAddress, resultSet.getLong("article_id"));
                    hashMap.put("isCommend", commend);
                    jsonArray.add(hashMap);
                }
                // 统一格式返回体
                jsonObject = Result.success(jsonArray);
                System.out.println(jsonObject.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            dbUtil.closeDbConnection();

            printWriter.write(jsonObject.toString());
        } else {
            printWriter.write(Result.failure(ResultCode.PARAM_IS_BLANK).toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
