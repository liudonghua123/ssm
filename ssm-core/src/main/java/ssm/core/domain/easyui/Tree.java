package ssm.core.domain.easyui;

import ssm.core.domain.BaseDomain;

/**
 * EasyUI(http://www.jeasyui.com/)Tree的一些属性
 * 
 * @author jinqinghua@gmail.com
 * @version 2012/08/04
 * @param <E>
 *            域模型(Domain), children集合中元素的类型在此定义
 */
public class Tree<E> extends BaseDomain<E> {
	private static final long serialVersionUID = -4277373245511952879L;

	private Integer id;
	private String text;
	private String iconCls;
	private String state;
	private Boolean checked;
	private String attibutes;
	private String target;

	public Tree() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getAttibutes() {
		return attibutes;
	}

	public void setAttibutes(String attibutes) {
		this.attibutes = attibutes;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

}
