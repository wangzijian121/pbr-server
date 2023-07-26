package com.zlht.pose.management.dao;

import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

@Slf4j
public class LizzMybatisIntercepts implements InnerInterceptor {
    private static final Logger logger = LogManager.getLogger(LizzMybatisIntercepts.class);

    @Override
    public boolean willDoQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        logger.info("#####willDoQuery");
        logger.info(boundSql.getSql());
        return true;
    }

    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        logger.info("#####beforeQuery");
        logger.info(boundSql.getSql());
    }

    @Override
    public boolean willDoUpdate(Executor executor, MappedStatement ms, Object parameter) throws SQLException {
        logger.info("#####willDoUpdate");
        // 一堆sql处理仅供参考
        BoundSql boundSql = ms.getBoundSql(parameter);
        ms.getSqlSource().getClass();
        String sql = boundSql.getSql();
        Statement statement = null;
        try {
            statement = CCJSqlParserUtil.parse(sql);
        } catch (JSQLParserException e) {
            e.printStackTrace();
        }
        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
        List<String> tableList = tablesNamesFinder.getTableList(statement);
        logger.info("sql:{}"+ sql);
        return false;
    }

    @Override
    public void beforeUpdate(Executor executor, MappedStatement ms, Object parameter) throws SQLException {
        logger.info("#####beforeUpdate");

    }

    @Override
    public void beforePrepare(StatementHandler sh, Connection connection, Integer transactionTimeout) {
        logger.info("#####beforePrepare");
    }

    @Override
    public void beforeGetBoundSql(StatementHandler sh) {
        logger.info("#####beforeGetBoundSql");
    }

    @Override
    public void setProperties(Properties properties) {
        logger.info("#####setProperties");
    }
}