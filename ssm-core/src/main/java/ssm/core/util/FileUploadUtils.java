package ssm.core.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;

public class FileUploadUtils {
    public static final String UPLOAD_FILE_SEPARATOR = "/";
    private static final String UPLOAD_PATH = "files/upload/";
    private static final String UPLOAD_DATE_PATH = "yyyy/MM/dd/";
    private static final Logger logger = LoggerFactory.getLogger(FileUploadUtils.class);

    public static String getUploadFileFullSavePath(ServletContext context) throws FileNotFoundException {
        return FileUploadUtils.getServletContextPath(context) + FileUploadUtils.getUploadFileSavePath(context);
    }

    public static String getUploadFileSavePath(ServletContext context) throws FileNotFoundException {
        String uploadPath = StringUtils.replace(UPLOAD_PATH, UPLOAD_FILE_SEPARATOR, File.separator);
        String uploadDatePath = DateFormatUtils.format(new Date(), UPLOAD_DATE_PATH);
        uploadDatePath = StringUtils.replace(uploadDatePath, UPLOAD_FILE_SEPARATOR, File.separator);

        String uploadFileSavePath = uploadPath + uploadDatePath;
        File dir = new File(FileUploadUtils.getServletContextPath(context), uploadFileSavePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return uploadFileSavePath;
    }

    public static String getServletContextPath(ServletContext context) throws FileNotFoundException {
        String ctxPath = WebUtils.getRealPath(context, File.separator);
        logger.debug("...servletContext.getRealPath({}):={}", File.separator, ctxPath);
        if (!ctxPath.endsWith(File.separator)) {
            ctxPath = ctxPath + File.separator;
        }
        return ctxPath;
    }
}
