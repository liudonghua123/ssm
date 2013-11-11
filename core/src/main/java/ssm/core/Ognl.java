package ssm.core;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * sqlmapper中的用法: <code>&lt;if test="@ssm.core.Ognl@isNotEmpty(id)"&gt; and ID = #{id:INTEGER}&lt;/if&gt;</code> .
 * 
 * @author jinqinghua@gmail.com
 * @version 2012/08/04
 * 
 */
public class Ognl {

	/**
	 * 判断 Map, Collection, String, Array 是否为空.
	 * 
	 * @param o
	 *            任意对象（Map, Collection, String, Array）
	 * @return true 如果为空
	 * @throws IllegalArgumentException
	 */
	public static boolean isEmpty(Object o) throws IllegalArgumentException {
		if (o == null) {
			return true;
		}

		if (o instanceof String) {
			if (((String) o).length() == 0) {
				return true;
			}
		} else if (o instanceof Collection) {
			if (((Collection<?>) o).isEmpty()) {
				return true;
			}
		} else if (o.getClass().isArray()) {
			if (Array.getLength(o) == 0) {
				return true;
			}
		} else if (o instanceof Map) {
			if (((Map<?, ?>) o).isEmpty()) {
				return true;
			}
		} else {
			return false;
		}
		return false;
	}

	/**
	 * 判断 Map, Collection, String, Array 是否不为空.
	 * 
	 * @param o
	 *            任意对象（Map, Collection, String, Array）
	 * @return true 如果不为空
	 */
	public static boolean isNotEmpty(Object o) {
		return !isEmpty(o);
	}
}
