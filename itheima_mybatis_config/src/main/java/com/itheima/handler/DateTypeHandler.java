package com.itheima.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * @ClassName DateTypeHandler
 * @Description TODO
 * @Author gxw
 * @Date 21:00 2022/11/17
 * @Version 1.0
 **/
public class DateTypeHandler extends BaseTypeHandler<Date> {
    // 将Java类型 转换成 数据库需要的类型
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Date date, JdbcType jdbcType) throws SQLException {
        long time = date.getTime();
        preparedStatement.setLong(i,time);
    }
    // 将数据库中类型 转换成java类型
    // String参数 要转换的字段名称
    // ResultSet 查询出的结果集
    @Override
    public Date getNullableResult(ResultSet resultSet, String s) throws SQLException {
        // 获得结果集中需要的数据（long）转换成date类型
        long aLong = resultSet.getLong(s);
        Date date = new Date(aLong);
        return date;
    }

    // 将数据库中类型 转换成java类型
    @Override
    public Date getNullableResult(ResultSet resultSet, int i) throws SQLException {
        long aLong = resultSet.getLong(i);
        Date date = new Date(aLong);
        return date;
    }

    // 将数据库中类型 转换成java类型
    @Override
    public Date getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        long aLong = callableStatement.getLong(i);
        Date date = new Date(aLong);
        return date;
    }
}
