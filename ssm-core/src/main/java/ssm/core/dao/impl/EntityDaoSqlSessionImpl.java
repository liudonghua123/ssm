package ssm.core.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.dao.DataAccessException;
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
    }

    @Override
    public int insertEntity(T t) throws DataAccessException {
        statement = String.format(INSERT_PATTERN, t.getClass().getSimpleName());
        return super.getSqlSession().insert(statement, t);
    }

    @Override
    public int updateEntity(T t) throws DataAccessException {
        statement = String.format(UPDATE_PATTERN, t.getClass().getSimpleName());
        return super.getSqlSession().update(statement, t);
    }

    @Override
    public int deleteEntity(T t) throws DataAccessException {
        statement = String.format(DELETE_PATTERN, t.getClass().getSimpleName());
        return super.getSqlSession().delete(statement, t);
    }

    @Override
    public T selectEntity(T t) throws DataAccessException {
        statement = String.format(SELECT_PATTERN, t.getClass().getSimpleName());
        return super.getSqlSession().selectOne(statement, t);
    }

    @Override
    public Long selectEntityCount(T t) throws DataAccessException {
        statement = String.format(SELECT_COUNT_PATTERN, t.getClass().getSimpleName());
        return (Long) super.getSqlSession().selectOne(statement, t);
    }

    @Override
    public List<T> selectEntityList(T t) throws DataAccessException {
        statement = String.format(SELECT_LIST_PATTERN, t.getClass().getSimpleName());
        return super.getSqlSession().selectList(statement, t);
    }
}
