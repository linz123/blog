package com.linz.util;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Result {


    /**
     * 返回成功消息及数据
     */
    public static JSONObject success(Object jsonArray) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", ResultCode.SUCCESS.getCode());
        jsonObject.put("message", ResultCode.SUCCESS.getMessage());
        jsonObject.put("data", jsonArray);
        return jsonObject;
    }

    public static JSONObject success(ResultCode resultCode, Object result) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", resultCode.getCode());
        jsonObject.put("message", resultCode.getMessage());
        jsonObject.put("data", result);
        return jsonObject;
    }

    public static JSONObject success(ResultCode resultCode) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", resultCode.getCode());
        jsonObject.put("message", resultCode.getMessage());
        return jsonObject;
    }


    /**
     * 返回成功消息
     */
    public static JSONObject success() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", ResultCode.SUCCESS.getCode());
        jsonObject.put("message", ResultCode.SUCCESS.getMessage());
        return jsonObject;
    }

    /**
     * 返回错误消息及数据
     */
    public static JSONObject failure(ResultCode resultCode) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", resultCode.getCode());
        jsonObject.put("message", resultCode.getMessage());
        return jsonObject;
    }


}
