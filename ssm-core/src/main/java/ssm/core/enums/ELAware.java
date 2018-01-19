package ssm.core.enums;

/**
 * 枚举类型实现此接口，在EL表达式中可调用name和ordinal，还加了label和value
 *
 * @author jinqinghua@gmail.com
 * @version 2013年12月13日 下午10:38:49
 */
public interface ELAware {

    /**
     * 对应 enum.name，一般为大写字母
     */
    String getName();

    /**
     * 对应 enum.ordinal，从0开始递增的数字
     */
    int getOrdinal();

    /**
     * 用于显示的名称
     */
    String getLabel();

    /**
     * 一般用ordinal就足够了，如果不想受限制，可以用这个
     */
    int getValue();
}
