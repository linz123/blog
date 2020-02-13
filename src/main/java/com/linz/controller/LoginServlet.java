package com.linz.controller;

import com.linz.util.DbUtil;
import com.linz.util.Md5Util;
import com.linz.util.Result;
import com.linz.util.ResultCode;

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
import java.util.HashMap;


@WebServlet("/api/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        PrintWriter printWriter = resp.getWriter();

        if (username != null && password != null) {
            String md5Password = Md5Util.getResult(password);
            String sql =
                    "SELECT\n" +
                            "	uid,\n" +
                            "	username,\n" +
                            "	password\n" +
                            "FROM\n" +
                            "	`manger_user`\n" +
                            "WHERE\n" +
                            "	username = '" + username + "';";
            DbUtil dbUtil = new DbUtil();
            System.out.println(sql);
            ResultSet resultSet = dbUtil.executeQuery(sql, null);

            try {
                if (resultSet.next()) {
                    String dbPwd = resultSet.getString("password");
                    if (dbPwd.equals(md5Password)) {
                        HashMap<String, Object> result = new HashMap<>();
                        result.put("uid", resultSet.getString("uid"));
                        HttpSession session = req.getSession();
                        System.out.println("uid"+ resultSet.getString("uid"));
                        session.setAttribute("uid", resultSet.getString("uid"));
                        session.setMaxInactiveInterval(60 * 60 * 3);
                        printWriter.write(Result.success(ResultCode.LOGIN_SUCCESS, result).toString());
                    } else {
                        printWriter.write(Result.failure(ResultCode.USER_LOGIN_ERROR).toString());
                    }
                } else {
                    printWriter.write(Result.failure(ResultCode.USER_NOT_EXIST).toString());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                dbUtil.closeDbConnection();
            }

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
