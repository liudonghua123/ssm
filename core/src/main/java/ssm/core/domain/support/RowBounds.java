package ssm.core.domain.support;

import java.io.Serializable;

/**
 * RowBounds(offset, limit)
 * 
 * @author jinqinghua@gmail.com
 * @version 2013/10/22
 * @since 1.2.4
 */
public class RowBounds implements Serializable {

	private static final long serialVersionUID = 4503548102626896410L;

	private Integer offset;

	private Integer limit;

	public RowBounds() {
	}

	public RowBounds(Integer limit) {
		this(0, limit);
	}

	public RowBounds(Integer offset, Integer limit) {
		this.offset = offset;
		this.limit = limit;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

}
