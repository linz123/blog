package com.linz.controller;

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
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

@WebServlet("/addArticle")
public class AddArticle extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        Long user_id = Long.parseLong(req.getParameter("user_id"));
        Timestamp timestamp = new Timestamp(new Date().getTime());
        String sql = "INSERT INTO articles (\n" +
                "	user_id,\n" +
                "	article_title,\n" +
                "	article_content,\n" +
                "	article_date\n" +
                ")\n" +
                "VALUES\n" +
                "	(?,?,?,?);";
        ArrayList<Object> paraList = new ArrayList<>();
        paraList.add(user_id);
        paraList.add(title);
        paraList.add(content);
        paraList.add(timestamp);

        DbUtil dbUtil = new DbUtil();
        PrintWriter printWriter = resp.getWriter();
        boolean isSuccess;
        try {
            isSuccess = dbUtil.executeUpdate(sql, paraList);

            if (isSuccess) {
                printWriter.write(Result.success().toString());
            } else {
                printWriter.write(Result.failure(ResultCode.FAIL_TO_ADD).toString());
            }
        } catch (SQLException e) {
            printWriter.write(Result.failure(ResultCode.DATABASE_ERROR).toString());
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
