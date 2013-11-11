package ssm.core.domain.easyui;

import ssm.core.domain.BaseDomain;

/**
 * EasyUI(http://www.jeasyui.com/)TreeGrid 的一些属性
 * 
 * @author jinqinghua@gmail.com
 * @version 2012/08/04
 * @param <E>
 *            域模型(Domain), children集合中元素的类型在此定义
 */
public class TreeGrid<E> extends BaseDomain<E> {
	private static final long serialVersionUID = 3569329962019587611L;
	/**
	 * open
	 */
	public static final String EASYUI_TREEGRID_ROW_STATE_OPEN = "open";
	/**
	 * closed
	 */
	public static final String EASYUI_TREEGRID_ROW_STATE_CLOSED = "closed";

	/**
	 * state
	 */
	private String state;

	public TreeGrid() {
		state = EASYUI_TREEGRID_ROW_STATE_CLOSED;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
