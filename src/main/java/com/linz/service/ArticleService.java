package com.linz.service;

import com.linz.util.DbUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class ArticleService {


    /**
     * 此ip是否对此文章点赞
     *
     * @param ipAddress  ip地址
     * @param article_id 文章id
     * @return boolean
     */
    public static boolean isCommend(String ipAddress, Long article_id) {

        DbUtil dbUtil = new DbUtil();

        String userIsCommend =
                "SELECT\n" +
                        "	*\n" +
                        "FROM\n" +
                        "	commend\n" +
                        "WHERE\n" +
                        "	ip_address = ?\n" +
                        "AND article_id = ?;";

        ArrayList<Object> paras = new ArrayList<>();
        paras.add(ipAddress);
        paras.add(article_id);
        ResultSet resultSet = dbUtil.executeQuery(userIsCommend, paras);
        boolean isCommend = false;

        try {
            isCommend = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbUtil.closeDbConnection();
        }

        return isCommend;
    }

    /**
     * 查询相关标签并筛选，插入表 返回 主键
     *
     * @param labels
     * @param dbUtil
     */
    public static ArrayList<Integer> addLabels(String[] labels, DbUtil dbUtil) throws SQLException {

        ArrayList<Integer> primaryKeys = new ArrayList<>();
        StringBuilder queryLabel = new StringBuilder("SELECT\n" +
                "	*\n" +
                "FROM\n" +
                "	`label`\n" +
                "WHERE\n" +
                "	label_name IN (\n");

        for (int i = 0; i < labels.length; i++) {
            queryLabel.append("?");
            if (i != labels.length - 1) queryLabel.append(",");
        }
        queryLabel.append(")");
        System.out.println(queryLabel.toString());

        ArrayList<String> labelList = new ArrayList<>(Arrays.asList(labels));
        ResultSet resultSet = dbUtil.executeQuery(queryLabel.toString(), labelList);


        while (resultSet.next()) {
            String labelName = resultSet.getString("label_name");
            for (int i = 0; i < labelList.size(); i++) {

                if (labelList.get(i).equals(labelName)) labelList.remove(i);

            }
        }

        for (int i = 0; i < labelList.size(); i++) {
            System.out.println(labelList.get(i));

        }


        if (labelList.size() > 0) {
            StringBuilder updateLabelSql = new StringBuilder("INSERT INTO label (label_name) VALUES ");
            for (int i = 0; i < labelList.size(); i++) {
                updateLabelSql.append("(?)");
                if (i != labelList.size() - 1) updateLabelSql.append(",");
            }
            updateLabelSql.append(";");
            System.out.println("updateLabelSql --> " + updateLabelSql);
            dbUtil.executeUpdateMulti(updateLabelSql.toString(), labelList);

        }

        ResultSet labelSet = dbUtil.executeQuery(queryLabel.toString(), new ArrayList<>(Arrays.asList(labels)));

        while (labelSet.next()) {
            primaryKeys.add((int) labelSet.getLong("label_id"));
        }

        return primaryKeys;

    }


    /**
     * 链接标签和文章
     *
     * @param article_id
     * @param labels
     * @param dbUtil
     * @return
     * @throws SQLException
     */
    public static boolean articleToLabel(int article_id, ArrayList<Integer> labels, DbUtil dbUtil) throws SQLException {
        StringBuilder updateLabelSql = new StringBuilder("INSERT INTO article_set_label (article_id,label_id) VALUES ");

        for (int i = 0; i < labels.size(); i++) {
            updateLabelSql.append("(?,?)");
            if (i != labels.size() - 1) updateLabelSql.append(",");
        }
        updateLabelSql.append(";");

        System.out.println(updateLabelSql.toString());
        PreparedStatement preparedStatement = dbUtil.getPrepareStatement(updateLabelSql.toString());

        for (int i = 0; i < labels.size(); i++) {
            preparedStatement.setObject((2 * i) + 1, article_id);
            preparedStatement.setObject((2 * i) + 2, labels.get(i));
        }

        return preparedStatement.executeUpdate() > 0;

    }


    public static ArrayList<String> articleLabels(Long article_id, DbUtil dbUtil) throws SQLException {

        ArrayList<String> labels = new ArrayList<>();

        String setLabel = "SELECT\n" +
                "	*\n" +
                "FROM\n" +
                "	article_set_label\n" +
                "WHERE\n" +
                "	article_id = " + article_id + ";";

        ResultSet resultSet = dbUtil.executeQuery(setLabel, null);
        ArrayList<Object> labelIds = new ArrayList<>();
        while (resultSet.next()) {
            labelIds.add(resultSet.getLong("label_id"));
        }

        if (labelIds.size() > 0) {

            StringBuilder queryLabel = new StringBuilder("SELECT\n" +
                    "	label_name\n" +
                    "FROM\n" +
                    "	`label`\n" +
                    "WHERE\n" +
                    "	label_id IN (\n");

            for (int i = 0; i < labelIds.size(); i++) {
                queryLabel.append("?");
                if (i != labelIds.size() - 1) queryLabel.append(",");
            }
            queryLabel.append(")");

            ResultSet resultSet1 = dbUtil.executeQuery(queryLabel.toString(), labelIds);


            while (resultSet1.next()) {
                labels.add(resultSet1.getString("label_name"));
            }

        }

        return labels;


    }


}
