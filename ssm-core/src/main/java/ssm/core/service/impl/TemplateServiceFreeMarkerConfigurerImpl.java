package ssm.core.service.impl;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import ssm.core.service.TemplateService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

/**
 * @author jinqinghua@gmail.com
 * @version 2012/08/04
 */
@Slf4j
public class TemplateServiceFreeMarkerConfigurerImpl implements TemplateService {

    private FreeMarkerConfigurer freeMarkerConfigurer;

    public TemplateServiceFreeMarkerConfigurerImpl() {
        // Do nothing
    }

    public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer) {
        this.freeMarkerConfigurer = freeMarkerConfigurer;
    }

    public String getContent(String templateName, Map<String, Object> model) {
        try {
            Template t = freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
            log.debug(FreeMarkerTemplateUtils.processTemplateIntoString(t, model));
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
