package com.linz.controller.comment;

import com.linz.service.CommentService;
import com.linz.util.DbUtil;
import com.linz.util.Result;
import com.linz.util.ResultCode;
import com.linz.util.Time;
import net.sf.json.JSONObject;

import javax.lang.model.element.Element;
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

@WebServlet("/api/addComment")
public class AddComment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long uid = Long.parseLong(req.getParameter("uid"));
        Long article_id = Long.parseLong(req.getParameter("article_id"));
        String commentContent = req.getParameter("c_content");

        Timestamp timestamp = new Timestamp(new Date().getTime());

        PrintWriter printWriter = resp.getWriter();

        DbUtil dbUtil = new DbUtil();

        String sql = "INSERT INTO comment (article_id, uid, c_content,generate_time)\n" +
                "VALUES\n" +
                "	(?,?,?,?);";
        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add(article_id);
        arrayList.add(uid);
        arrayList.add(commentContent);
        arrayList.add(timestamp);

        try {
            boolean isUpdate = dbUtil.executeUpdate(sql, arrayList);
            if (isUpdate) {
                CommentService.addComment(article_id, dbUtil); //添加评论计数
                printWriter.write(Result.success(ResultCode.ADD_IS_SUCCESS).toString());
            }
        } catch (SQLException e) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("error", e.toString());
            printWriter.write(Result.success(jsonObject).toString());
        } finally {
            dbUtil.closeDbConnection();
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
