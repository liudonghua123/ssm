package ssm.core.service;

import java.util.Map;

import org.springframework.mail.SimpleMailMessage;

/**
 * 邮件服务接口.
 * 
 * @author jinqinghua@gmail.com
 * @version 2012/08/04
 */
public interface MailService {

	/**
	 * 发送邮件
	 * 
	 * @param mailMessage
	 *            mailMessage对象
	 */
	void send(SimpleMailMessage mailMessage);

	/**
	 * 发送邮件
	 * 
	 * @param mailMessage
	 *            mailMessage对象
	 * @param templateName
	 *            模板文件
	 * @param model
	 *            数据模型
	 */
	void send(SimpleMailMessage mailMessage, String templateName, Map<String, Object> model);

	/**
	 * 发送邮件
	 * 
	 * @param mimeMessage
	 *            mimeMessage对象
	 * @param templateName
	 *            模板文件
	 * @param model
	 *            数据模型变量
	 * @param encoding
	 *            编码，默认是UTF-8
	 */
	void send(SimpleMailMessage mimeMessage, String templateName, Map<String, Object> model, String encoding);

}
