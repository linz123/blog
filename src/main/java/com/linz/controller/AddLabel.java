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
import java.util.ArrayList;

@WebServlet("/api/addLabel")
public class AddLabel extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String labelParentName = req.getParameter("labelParentName");
        String labelName = req.getParameter("labelName");
        String labelDescription = req.getParameter("labelDescription");

        String sql =
                "INSERT INTO label (\n" +
                        "	label_parent_name,\n" +
                        "	label_name,\n" +
                        "	label_description\n" +
                        ")\n" +
                        "VALUES\n" +
                        "	(?,?,?);";
        ArrayList<String> parameters = new ArrayList<>();
        parameters.add(labelParentName);
        parameters.add(labelName);
        parameters.add(labelDescription);

        DbUtil dbUtil = new DbUtil();
        PrintWriter printWriter = resp.getWriter();
        try {
            boolean isSuccess = dbUtil.executeUpdate(sql, parameters);

            if (isSuccess) {
                printWriter.write(Result.success(ResultCode.ADD_IS_SUCCESS).toString());
            }

        } catch (SQLException e) {
            printWriter.write(Result.failure(ResultCode.FAIL_TO_ADD).toString());
        }finally {
            dbUtil.closeDbConnection();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
