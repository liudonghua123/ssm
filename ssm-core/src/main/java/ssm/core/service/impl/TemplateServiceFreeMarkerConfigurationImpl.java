package ssm.core.service.impl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import ssm.core.service.TemplateService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

/**
 * @author jinqinghua@gmail.com
 * @version 2012/08/04
 */
@Slf4j
public class TemplateServiceFreeMarkerConfigurationImpl implements TemplateService {

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
            log.error("Error while processing FreeMarker template ", e);
        } catch (FileNotFoundException e) {
            log.error("Error while open template file ", e);
        } catch (IOException e) {
            log.error("IO Exception ", e);
        }
        return null;
    }
}
