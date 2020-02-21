package com.linz.controller.comment;

import com.linz.service.CommentService;
import com.linz.util.DbUtil;
import com.linz.util.Result;
import com.linz.util.ResultCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet("/api/getAllReply")
public class getAllReply extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long comment_id = Long.parseLong(req.getParameter("comment_id"));

        System.out.println("comment_id -->" + comment_id);

        PrintWriter printWriter = resp.getWriter();
        DbUtil dbUtil = new DbUtil();
        ArrayList<Object> dataList = new ArrayList<>();


        String sql = "SELECT\n" +
                "	*\n" +
                "FROM\n" +
                "	reply\n" +
                "WHERE\n" +
                "	comment_id = " + comment_id + " ORDER BY generate_time DESC;";


        ResultSet resultSet = dbUtil.executeQuery(sql, null);
        try {
            while (resultSet.next()) {

                long uid = resultSet.getLong("uid");
                long reply_to_uid = resultSet.getLong("reply_to_uid");

                HashMap<String, Object> item = new HashMap<>();
                item.put("uid", uid);
                item.put("reply_to_uid", reply_to_uid);
                item.put("username", CommentService.getUserNameById(uid, dbUtil));
                item.put("replyToName", CommentService.getUserNameById(reply_to_uid, dbUtil));
                item.put("reply_content", resultSet.getString("reply_content"));
                item.put("comment_id", resultSet.getLong("comment_id"));
                item.put("generate_time", resultSet.getTimestamp("generate_time"));
                dataList.add(item);
            }
            printWriter.write(Result.success(dataList).toString());
        } catch (SQLException e) {
            System.out.println(e);
            printWriter.write(Result.failure(ResultCode.FAIL_TO_ADD).toString());
        } finally {
            dbUtil.closeDbConnection();
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
