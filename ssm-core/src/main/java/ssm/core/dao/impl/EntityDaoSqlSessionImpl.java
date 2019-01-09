package ssm.core.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import ssm.core.dao.EntityDao;

import java.util.List;

/**
 * 实体 DAO 的接口的 sqlSession 实现
 *
 * @param <T> 实体对象(Domain)
 * @author jinqinghua@gmail.com
 * @version 2012/08/04
 */
public class EntityDaoSqlSessionImpl<T> extends SqlSessionDaoSupport implements EntityDao<T> {

    /**
     * SQL语句
     */
    private String statement;

    public EntityDaoSqlSessionImpl() {
        // Do nothing
    }

    public int insertEntity(T t) {
        statement = String.format(INSERT_PATTERN, t.getClass().getSimpleName());
        return super.getSqlSession().insert(statement, t);
    }

    public int updateEntity(T t) {
        statement = String.format(UPDATE_PATTERN, t.getClass().getSimpleName());
        return super.getSqlSession().update(statement, t);
    }

    public int deleteEntity(T t) {
        statement = String.format(DELETE_PATTERN, t.getClass().getSimpleName());
        return super.getSqlSession().delete(statement, t);
    }

    public T selectEntity(T t) {
        statement = String.format(SELECT_PATTERN, t.getClass().getSimpleName());
        return super.getSqlSession().selectOne(statement, t);
    }

    public Long selectEntityCount(T t) {
        statement = String.format(SELECT_COUNT_PATTERN, t.getClass().getSimpleName());
        return super.getSqlSession().selectOne(statement, t);
    }

    public List<T> selectEntityList(T t) {
        statement = String.format(SELECT_LIST_PATTERN, t.getClass().getSimpleName());
        return super.getSqlSession().selectList(statement, t);
    }
}
