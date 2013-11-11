package ssm.core.service;

import java.util.Map;

/**
 * 模板服务类
 * 
 * @author jinqinghua@gmail.com
 * @version 2012/08/04
 */
public interface TemplateService {

	/**
	 * 根据模板和数据模型取得渲染后的内容
	 * 
	 * @param templateName
	 *            模板文件名
	 * @param model
	 *            数据模型
	 * @return 渲染后的内容
	 */
	String getContent(String templateName, Map<String, Object> model);

}
