package ssm.core.util;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

/**
 * Naming File with the type gived
 *
 * @author jinqinghua@gmail.com
 * @version 2012/08/04
 */
public class FileNamingUtils {
    // private static final Logger logger =
    // LoggerFactory.getLogger(FileNamingUtils.class);
    private static final String FILE_NAME_PATTERN = "%s.%s";

    public static String getFileName(String originalFilename, FileNaming fileNaming) {
        String extension = FilenameUtils.getExtension(originalFilename).toLowerCase();
        switch (fileNaming) {
            case ORIGINAL:
                return originalFilename;
            case UUID:
                return String.format(FILE_NAME_PATTERN, UUID.randomUUID().toString().toUpperCase(), extension);
            case MD5:
                return String.format(FILE_NAME_PATTERN,
                        DigestUtils.md5DigestAsHex(originalFilename.getBytes()).toUpperCase(), extension);
            case DATE:
                return String.format(FILE_NAME_PATTERN, DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS"), extension);
            case TIME:
                return String.format(FILE_NAME_PATTERN, String.valueOf(new Date().getTime()), extension);
            case RANDOM:
                return String.format(FILE_NAME_PATTERN, RandomStringUtils.randomAlphanumeric(32).toUpperCase(), extension);
            default:
                return originalFilename;
        }
    }
}
