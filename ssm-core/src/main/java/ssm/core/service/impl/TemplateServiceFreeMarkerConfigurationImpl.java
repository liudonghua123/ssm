package ssm.core.service.impl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import ssm.core.service.TemplateService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

/**
 * @author jinqinghua@gmail.com
 * @version 2012/08/04
 */
public class TemplateServiceFreeMarkerConfigurationImpl implements TemplateService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Configuration freeMarkerConfiguration;

    public TemplateServiceFreeMarkerConfigurationImpl() {
        // Do nothing
    }

    public void setFreeMarkerConfiguration(Configuration freeMarkerConfiguration) {
        this.freeMarkerConfiguration = freeMarkerConfiguration;
    }

    public String getContent(String templateName, Map<String, Object> model) {
        try {
            Template t = freeMarkerConfiguration.getTemplate(templateName);
            return FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
        } catch (TemplateException e) {
            logger.error("Error while processing FreeMarker template ", e);
        } catch (FileNotFoundException e) {
            logger.error("Error while open template file ", e);
        } catch (IOException e) {
            logger.error("IO Exception ", e);
        }
        return null;
    }
}
