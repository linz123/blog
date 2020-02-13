package com.linz.controller;

import com.linz.service.ArticleService;
import com.linz.util.DbUtil;
import com.linz.util.IpAddressUtil;
import com.linz.util.Result;
import com.mysql.cj.xdevapi.JsonArray;
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
import java.util.ArrayList;
import java.util.HashMap;


@WebServlet("/api/articleList")
public class ArticleList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameter("pageSize"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));     // 数据条数
        int currentPage = Integer.parseInt(req.getParameter("currentPage")); // 第几页
        int start = (currentPage - 1) * pageSize;
        System.out.println("start -->" + start);
        DbUtil dbUtil = new DbUtil();
        // ip 地址
        final String ipAddress = IpAddressUtil.getIpAddress(req);


        String sql = "" +
                "SELECT\n" +
                "	user_id,\n" +
                "	articles.article_id,\n" +
                "	article_title,\n" +
                "	article_views,\n" +
                "	article_comment_count,\n" +
                "	article_date,\n" +
                "	article_like_count,\n" +
                "	username\n" +
                "FROM articles\n" +
                "INNER JOIN manger_user ON articles.user_id = manger_user.uid LIMIT " + start + "," + pageSize + ";";


        ResultSet resultSet = dbUtil.executeQuery(sql, null);

        String count =
                "SELECT\n" +
                        "	COUNT(*) as total\n" +
                        "FROM articles\n" +
                        "			INNER JOIN manger_user ON articles.user_id = manger_user.uid";

        ResultSet totalCount = dbUtil.executeQuery(count, null);


        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        JSONObject result = new JSONObject();

        try {
            while (resultSet.next()) {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("article_id", resultSet.getLong("article_id"));
                hashMap.put("user_id", resultSet.getLong("user_id"));
                hashMap.put("article_title", resultSet.getString("article_title"));
                hashMap.put("article_views", resultSet.getLong("article_views"));
                hashMap.put("article_comment_count", resultSet.getLong("article_comment_count"));
                hashMap.put("article_date", resultSet.getTimestamp("article_date"));
                hashMap.put("article_like_count", resultSet.getLong("article_like_count"));
                hashMap.put("username", resultSet.getString("username"));
                ArrayList<String> labels = ArticleService.articleLabels(resultSet.getLong("article_id"), dbUtil);
                hashMap.put("labels", labels);
                boolean commend = ArticleService.isCommend(ipAddress, resultSet.getLong("article_id"));
                hashMap.put("isCommend", commend);
                jsonArray.add(hashMap);
            }

            while (totalCount.next()) {
                result.put("total", totalCount.getLong("total"));
            }
            result.put("result", jsonArray);


            // 统一格式返回体
            jsonObject = Result.success(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("closeDbConnection");

        }

        dbUtil.closeDbConnection();
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(jsonObject.toString());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }


}
