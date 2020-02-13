package com.linz.controller;


import com.linz.util.DbUtil;
import com.linz.util.IpAddressUtil;
import com.linz.util.Result;
import com.linz.util.ResultCode;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

@WebServlet("/api/commend")
public class commendServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long article_id = Long.parseLong(req.getParameter("article_id"));

        String ipAddress = IpAddressUtil.getIpAddress(req);

        System.out.println("ipAddress ---> " + ipAddress);

        System.out.println("article_id ---> " + article_id);
        DbUtil dbUtil = new DbUtil();
        ArrayList<Object> paraList = new ArrayList<>();
        paraList.add(ipAddress);
        paraList.add(article_id);
        String checkAddress =
                "SELECT\n" +
                        "	*\n" +
                        "FROM\n" +
                        "	commend\n" +
                        "WHERE\n" +
                        "ip_address = ?	" +
                        "AND article_id = ?;";

        PrintWriter printWriter = resp.getWriter();
        try {
            ResultSet resultSet = dbUtil.executeQuery(checkAddress, paraList);
            System.out.println("Result " + resultSet);
            if (resultSet.next()) {
                JSONObject respObject = Result.failure(ResultCode.USER_HAS_COMMEND);
                printWriter.write(respObject.toString());
            } else {
                Connection connection = dbUtil.getConnection();
                try {
                    String commendSql =
                            "UPDATE articles\n" +
                                    "SET article_like_count = article_like_count + 1\n" +
                                    "WHERE\n" +
                                    "	article_id = " + article_id + ";";
                    connection.setAutoCommit(false);
                    boolean updateBoolean = dbUtil.executeUpdate(commendSql, null);
                    String insertCommend =
                            "INSERT INTO commend (ip_address,article_id) VALUES (?,?);";

                    boolean insertBoolean = dbUtil.executeUpdate(insertCommend, paraList);
                    connection.commit();

                    if (updateBoolean && insertBoolean) {
                        JSONObject result = Result.success();
                        printWriter.write(result.toString());
                    }
                } catch (SQLException e) {
                    connection.rollback();
                    JSONObject result = Result.failure(ResultCode.FAIL_TO_ADD);
                    printWriter.write(result.toString());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbUtil.closeDbConnection();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }


}
