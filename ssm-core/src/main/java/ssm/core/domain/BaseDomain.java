package ssm.core.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ssm.core.domain.support.RowBounds;

/**
 * 域模型(Domain)基数，提供了一个用于分页的row, 方便的map, 用于树结构的childen
 * 
 * @author jinqinghua@gmail.com
 * @version 2012/08/04
 * @param <E>
 *            域模型(Domain), children集合中元素的类型在此定义
 */
public abstract class BaseDomain<E> implements Serializable {

	private static final long serialVersionUID = 2835098506064719597L;

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

	/**
	 * 用于自省的树结构
	 */
	private List<E> _children;

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

	public List<E> get_children() {
		return _children;
	}

	public void set_children(List<E> _children) {
		this._children = _children;
	}

	@Override
	public String toString() {
		return this.toString(ToStringStyle.MULTI_LINE_STYLE);
	}

	/**
	 * 将对象格式化为一定的样式
	 * 
	 * @param style
	 *            ToStringStyle
	 * @return 格式化的样式
	 * @see org.apache.commons.lang3.builder.ToStringStyle
	 */
	public String toString(ToStringStyle style) {
		return ReflectionToStringBuilder.toString(this, style);
	}
}
