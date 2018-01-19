package ssm.core.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import ssm.core.domain.support.RowBounds;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 域模型(Domain)基数，提供了一个用于分页的_rowBounds, 方便的_map
 *
 * @param <E> 域模型(Domain), children集合中元素的类型在此定义
 * @author jinqinghua@gmail.com
 * @version 2012/08/04
 */
public abstract class BaseDomain implements Serializable {

    private static final long serialVersionUID = 2835098506064719597L;

    /**
     * 删除标记（假删）字段
     */
    private String _deleteFlagField;

    /**
     * 删除标记（假删）值
     */
    private int _deleteFlagValue = 1;
    /**
     * 行对象，用于分页或取部分数据
     *
     * @see ssm.core.domain.support.RowBounds
     */
    private RowBounds _rowBounds;

    /**
     * Map<String, Object> 对象
     */
    private Map<String, Object> _map;

    public BaseDomain() {
        _rowBounds = new RowBounds();
        _map = new HashMap<String, Object>();
    }

    public RowBounds get_rowBounds() {
        return _rowBounds;
    }

    public void set_rowBounds(RowBounds _rowBounds) {
        this._rowBounds = _rowBounds;
    }

    public Map<String, Object> get_map() {
        return _map;
    }

    public void set_map(Map<String, Object> _map) {
        this._map = _map;
    }

    public String get_deleteFlagField() {
        return _deleteFlagField;
    }

    public void set_deleteFlagField(String _deleteFlagField) {
        this._deleteFlagField = _deleteFlagField;
    }

    public int get_deleteFlagValue() {
        return _deleteFlagValue;
    }

    public void set_deleteFlagValue(int _deleteFlagValue) {
        this._deleteFlagValue = _deleteFlagValue;
    }

    @Override
    public String toString() {
        return this.toString(ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * 将对象格式化为一定的样式
     *
     * @param style ToStringStyle
     * @return 格式化的样式
     * @see org.apache.commons.lang3.builder.ToStringStyle
     */
    public String toString(ToStringStyle style) {
        return ReflectionToStringBuilder.toString(this, style);
    }
}
