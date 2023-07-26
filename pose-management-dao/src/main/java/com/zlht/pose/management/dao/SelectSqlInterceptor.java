package com.zlht.pose.management.dao;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.Properties;

/**
 * MP SQL执行拦截器，用于捕获@Select执行的SQL语句。
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class SelectSqlInterceptor implements Interceptor {


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler sh = (StatementHandler) invocation.getTarget();
        MappedStatement ms = (MappedStatement) invocation.getArgs()[0];

        // 只拦截@Select注解的方法
        if (ms != null && ms.getSqlCommandType() == SqlCommandType.SELECT) {
            BoundSql boundSql = sh.getBoundSql();
            String sql = boundSql.getSql();
            System.out.println("Executing SQL: {}" + sql);
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        // empty
    }
}