package com.linz.controller.personal;


import com.linz.util.DbUtil;
import com.linz.util.Result;
import com.linz.util.ResultCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/api/upLoadImg")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,   // 10 MB
        maxFileSize = 1024 * 1024 * 2,          // 2 MB
        maxRequestSize = 1024 * 1024 * 10       // 20 MB
)
public class UploadImg extends HttpServlet {

    private static final String SAVE_DIR = "E:\\upload";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long uid = Long.parseLong(req.getParameter("uid"));


        // creates the save directory if it does not exists
        File fileSaveDir = new File(SAVE_DIR);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        System.out.println("Upload File Directory=" + fileSaveDir.getAbsolutePath());
        PrintWriter printWriter = resp.getWriter();

        String imgPath = null;

        //Get all the parts from request and write it to the file on server
        for (Part part : req.getParts()) {
            if (part.getName().equals("file")) {
                imgPath = this.generateImgName(this.getFileName(part), uid);
                System.out.println(imgPath);
                part.write(SAVE_DIR + File.separator + imgPath);
                System.out.println("part--->" + part);
            }
        }

        String sql = "UPDATE manger_user SET profile_url = " + "'" + imgPath + "'" + " WHERE uid = " + uid + ";";
        DbUtil dbUtil = new DbUtil();

        try {
            boolean isUpdate = dbUtil.executeUpdate(sql, null);
            if (isUpdate) printWriter.write(Result.success(ResultCode.ADD_IS_SUCCESS).toString());
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


    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("contentDisp--->" + contentDisp);
        System.out.println("content-disposition header= " + contentDisp);
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return "";
    }


    private String generateImgName(String filename, long uid) {

        String timestamp = (new SimpleDateFormat("yyyyMMddHHmmssSSS")).format(new Date());
        System.out.println("timestamp --> " + timestamp);
        String[] s = filename.split("\\.");
        for (int i = 0; i < s.length; i++) {
            System.out.println(i + "-->" + s[i]);
        }
        return timestamp + uid + "." + s[1];
    }
}
