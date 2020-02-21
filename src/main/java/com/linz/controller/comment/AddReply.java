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
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

@WebServlet("/api/addReply")
public class AddReply extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        Long article_id = Long.parseLong(req.getParameter("article_id"));
        Long comment_id = Long.parseLong(req.getParameter("comment_id"));
        Long uid = Long.parseLong(req.getParameter("uid"));
        String replyContent = req.getParameter("reply_content");
        Long replyToUid = Long.parseLong(req.getParameter("reply_to_uid"));

        Timestamp time = new Timestamp(new Date().getTime());

        PrintWriter printWriter = resp.getWriter();
        DbUtil dbUtil = new DbUtil();

        String addSql =
                "INSERT INTO reply (\n" +
                        "	uid,\n" +
                        "	comment_id,\n" +
                        "	reply_content,\n" +
                        "	reply_to_uid,\n" +
                        "	generate_time\n" +
                        ")\n" +
                        "VALUES\n" +
                        "	(?,?,?,?,?);";

        ArrayList<Object> paraList = new ArrayList<>();
        paraList.add(uid);
        paraList.add(comment_id);
        paraList.add(replyContent);
        paraList.add(replyToUid);
        paraList.add(time);

        try {
            boolean isUpdate = dbUtil.executeUpdate(addSql, paraList);
            if (isUpdate) {
//                CommentService.addComment(article_id, dbUtil);
                printWriter.write(Result.success().toString());
            }

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
