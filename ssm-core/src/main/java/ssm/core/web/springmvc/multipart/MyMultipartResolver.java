package ssm.core.web.springmvc.multipart;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;

/**
 * <bean id="multipartResolver" class=
 * "org.springframework.web.multipart.commons.CommonsMultipartResolver">
 * CommonsMultipartResolver 会让Spring
 * MVC处理request对象,所以在使用KindEditor上传图片的时候,拿到的request都是空的
 * 覆写isMultipart以放过kindeditor,需在xml里重新配置 <bean id="multipartResolver" class=
 * "ssm.web.springmvc.multipart.MyMultipartResolver">
 *
 * @author jinqinghua@gmail.com
 * @version 2013年12月18日 上午12:02:33
 */
public class MyMultipartResolver extends CommonsMultipartResolver {

    @Override
    public boolean isMultipart(HttpServletRequest request) {
        if (request.getRequestURI().contains("/kindeditor/")) {
            return false;
        } else {
            return super.isMultipart(request);
        }
    }

}
