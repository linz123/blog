package com.linz.controller;

import com.linz.util.DbUtil;
import com.linz.util.Result;
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

@WebServlet("/api/getAllLabels")
public class getAllLabels extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sql = "SELECT * FROM label;";
        DbUtil dbUtil = new DbUtil();

        ResultSet result = dbUtil.executeQuery(sql, null);
        JSONArray data = new JSONArray();
        PrintWriter printWriter = resp.getWriter();
        try {
            while (result.next()) {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("label_parent_name", result.getString("label_parent_name"));
                hashMap.put("label_name", result.getString("label_name"));
                hashMap.put("label_description", result.getString("label_description"));
                data.add(hashMap);
            }
            printWriter.write(Result.success(data).toString());
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
