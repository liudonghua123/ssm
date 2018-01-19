package ssm.core.service;

/**
 * 服务门面基类
 *
 * @author jinqinghua@gmail.com
 * @version 2012/08/04
 */
public interface BaseFacade {

    MailService getMailService();

    TemplateService getTemplateService();
}
