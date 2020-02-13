package com.linz.controller;

import com.linz.service.ArticleService;
import com.linz.util.DbUtil;
import com.linz.util.Result;
import com.linz.util.ResultCode;
import com.mysql.cj.Session;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

@WebServlet("/api/addArticle")
public class AddArticle extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String title = req.getParameter("title");
        String content = req.getParameter("content");

        String[] labels = req.getParameterValues("labels");
        String publishType = req.getParameter("publishType");
        String sketch = req.getParameter("sketch");


        PrintWriter printWriter = resp.getWriter();
        HttpSession session = req.getSession();

        String user_session_id = (String)session.getAttribute("uid");
        System.out.println("user_session_id -->"+user_session_id);

        if (user_session_id == null) {
            printWriter.write(Result.failure(ResultCode.USER_NOT_LOGGED).toString());
            return;
        }

        Long user_id = Long.parseLong(user_session_id);
        System.out.println("user_id -->"+user_id);

        DbUtil dbUtil = new DbUtil();

        try {
            ArrayList<Integer> primaryKeys = ArticleService.addLabels(labels, dbUtil);

            System.out.println("primaryKeys-->" + primaryKeys);
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

            ResultSet resultSet = dbUtil.executeUpdateMulti(sql, paraList);

            int article_id = -1;
            while (resultSet.next()) {
                article_id = resultSet.getInt(1);
            }

            if (article_id > -1) {
                boolean isSuccess = ArticleService.articleToLabel(article_id, primaryKeys, dbUtil);
                JSONObject data = new JSONObject();
                data.put("article_id", article_id);
                if (isSuccess) {
                    printWriter.write(Result.success(ResultCode.ARTICLE_PUBLISH_SUCCESS, data).toString());
                } else {
                    printWriter.write(Result.failure(ResultCode.FAIL_TO_ADD).toString());
                }
            }


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
