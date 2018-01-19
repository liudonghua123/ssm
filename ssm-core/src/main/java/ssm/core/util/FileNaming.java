package ssm.core.util;

/**
 * 文件命名的枚举类型
 *
 * @author jinqinghua@gmail.com
 * @version 2012/08/04
 */
public enum FileNaming {

    /**
     * Original Filename
     */
    ORIGINAL("Original Filename"),
    /**
     * UUID
     */
    UUID("UUID"),
    /**
     * MD5 32
     */
    MD5("MD5 32"),
    /**
     * yyyyMMddHHmmssSSS
     */
    DATE("yyyyMMddHHmmssSSS"),
    /**
     * Millisecond
     */
    TIME("Millisecond"),
    /**
     * Random Alpha Numeric 32
     */
    RANDOM("Random Alpha Numeric 32");

    private String description;

    private FileNaming(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
