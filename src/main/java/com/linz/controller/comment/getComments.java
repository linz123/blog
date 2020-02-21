package com.linz.controller.comment;


import com.linz.service.CommentService;
import com.linz.util.DbUtil;
import com.linz.util.Result;
import com.linz.util.ResultCode;
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

@WebServlet("/api/getComments")
public class getComments extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long article_id = Long.parseLong(req.getParameter("article_id"));

        String sql = "SELECT\n" +
                "	c_id,\n" +
                "	article_id,\n" +
                "	`comment`.uid,\n" +
                "	c_content,\n" +
                "	`comment`.generate_time,\n" +
                "	username,\n" +
                "   `manger_user`.uid\n"+
                "FROM\n" +
                "	`comment`\n" +
                "INNER JOIN manger_user\n" +
                "WHERE\n" +
                "	article_id = " + article_id + "\n" +
                "AND `comment`.uid = manger_user.uid ORDER BY generate_time DESC;";

        PrintWriter printWriter = resp.getWriter();
        DbUtil dbUtil = new DbUtil();

        ResultSet result = dbUtil.executeQuery(sql, null);

        JSONArray data = new JSONArray();

        try {
            while (result.next()) {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("commend_id", result.getLong("c_id"));
                hashMap.put("username", result.getString("username"));
                hashMap.put("uid", result.getLong("uid"));
                hashMap.put("content", result.getString("c_content"));
                hashMap.put("generate_time", result.getTimestamp("generate_time"));
                hashMap.put("reply_counts", CommentService.getCommentCount(result.getLong("c_id"),dbUtil));
                data.add(hashMap);
            }
            printWriter.write(Result.success(data).toString());

        } catch (SQLException e) {
            System.out.println(e);
            printWriter.write(Result.failure(ResultCode.DATABASE_ERROR).toString());
        } finally {
            dbUtil.closeDbConnection();
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
