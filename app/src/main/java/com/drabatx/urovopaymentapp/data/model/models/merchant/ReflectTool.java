package com.drabatx.urovopaymentapp.data.model.models.merchant;

import java.lang.reflect.Field;

/**反射操作的工具类
 * @author KuCoffee
 */
public class ReflectTool {
	
	/**全局查找指定字段名的Field
	 * @param cls 被查找的类的class对象
	 * @param fieldName 字段的名字
	 * @return
	 * @throws Exception
	 */
	public static Field findGlobally(Class<?> cls, String fieldName) throws Exception {
		Field field = null;
		try {
			field = cls.getDeclaredField(fieldName);
		} catch (NoSuchFieldException e) {
			Class<?> superCls = cls.getSuperclass();
			if(superCls != null) {
				field = findGlobally(superCls, fieldName);
			}
			else {
				throw e;
			}
		}
		return field;
	}
	
	/**获取指定字段的值，效果等价于obj.fieldName
	 * @param obj
	 * @param fieldName
	 * @return
	 * @throws Exception
	 */
	public static Object getFromField(Object obj, String fieldName) throws Exception {
		Field field = findGlobally(obj.getClass(), fieldName);
		field.setAccessible(true);
		return field.get(obj);
	}
	
	/**为指定字段赋值，效果等价于obj.fieldName = value
	 * @param obj
	 * @param fieldName 
	 * @param value
	 * @throws Exception
	 */
	public static void setToField(Object obj, String fieldName, Object value) throws Exception {
		Field field = findGlobally(obj.getClass(), fieldName);
		field.setAccessible(true);
		field.set(obj, value);
	}
}
