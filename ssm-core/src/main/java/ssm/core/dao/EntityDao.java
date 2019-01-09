package ssm.core.dao;

import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * 实体 DAO 的接口
 *
 * @param <T> 实体对象(Domain)
 * @author jinqinghua@gmail.com
 * @version 2012/08/04
 */
public interface EntityDao<T> {

    /**
     * insert%s
     */
    String INSERT_PATTERN = "insert%s";

    /**
     * update%s
     */
    String UPDATE_PATTERN = "update%s";

    /**
     * delete%s
     */
    String DELETE_PATTERN = "delete%s";

    /**
     * select%s
     */
    String SELECT_PATTERN = "select%s";

    /**
     * select%sCount
     */
    String SELECT_COUNT_PATTERN = "select%sCount";

    /**
     * select%sList
     */
    String SELECT_LIST_PATTERN = "select%sList";

    /**
     * @param t 实体对象(Domain)
     * @return 受影响的行数，注意，这个和ibatis2有重大差别
     * @throws DataAccessException
     */
    int insertEntity(T t);

    /**
     * @param t 实体对象(Domain)
     * @return 受影响的行数
     * @throws DataAccessException
     */
    int updateEntity(T t);

    /**
     * @param t 实体对象(Domain)
     * @return 受影响的行数
     * @throws DataAccessException
     */
    int deleteEntity(T t);

    /**
     * @param t 实体对象(Domain)
     * @return 空或一个实体对象(Domain)
     * @throws DataAccessException
     */
    T selectEntity(T t);

    /**
     * @param t 实体对象(Domain)
     * @return 查询得到的数量
     * @throws DataAccessException
     */
    Long selectEntityCount(T t);

    /**
     * @param t 实体对象(Domain)
     * @return 空或一个实体对象(Domain)的集合(List)
     * @throws DataAccessException
     */
    List<T> selectEntityList(T t);

}
