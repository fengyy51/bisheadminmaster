package com.binwang.util;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by owen on 17/4/28.
 */
public class JsonTypeHandler implements TypeHandler<Object> {
    private static final char SEPARATOR = ';';

    @Override
    public void setParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        if (parameter == null) {
            ps.setString(i, null);
            return;
        }
        String json = JsonUtil.toJson(parameter);
        json = json + SEPARATOR + parameter.getClass().getName();
        ps.setString(i, json);
    }

    @Override
    public Object getResult(ResultSet rs, String columnName) throws SQLException {
        String json = rs.getString(columnName);
        return jsonToObject(json);
    }

    @Override
    public Object getResult(ResultSet rs, int columnIndex) throws SQLException {
        String json = rs.getString(columnIndex);
        return jsonToObject(json);
    }

    @Override
    public Object getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String json = cs.getString(columnIndex);
        return jsonToObject(json);
    }

    private Object jsonToObject(String json) {
        if (json == null || json.equals("")) {
            return null;
        }
        int index = json.lastIndexOf(SEPARATOR);
        if (index < 0) {
            return null;
        }

        String className = json.substring(index + 1, json.length());
        json = json.substring(0, index);
        Class<?> classType = null;
        try {
            classType = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("反序列化json为对象时找不到指定的类:" + className, e);
        }
        Object obj = JsonUtil.fromJson(json, classType);
        return obj;
    }
}

