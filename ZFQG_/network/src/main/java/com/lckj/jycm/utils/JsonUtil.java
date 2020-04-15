package com.lckj.jycm.utils;


import com.google.gson.Gson;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class JsonUtil {
    /**
     * object对象转json
     *
     * @param obj
     * @return
     */
    public static String object2json(Object obj) {
        Gson gson = new Gson();
        String json = gson.toJson(obj);
//        Log.d("对象转换", json);
        return json;
    }
	/*public static String object2json(Object obj) {
		StringBuilder json = new StringBuilder();
		if (obj == null) {
			json.append("\"\"");
		} else if (obj instanceof String
				|| obj instanceof Float || obj instanceof Boolean
				|| obj instanceof Short || obj instanceof Double
                || obj instanceof BigDecimal
				|| obj instanceof BigInteger || obj instanceof Byte) {
			json.append("\"").append(string2json(obj.toString())).append("\"");
		} else if (obj instanceof Object[]) {
			json.append(array2json((Object[]) obj));
		} else if (obj instanceof List) {
			json.append(list2json((List<?>) obj));
		} else if (obj instanceof Map) {
			json.append(map2json((Map<?, ?>) obj));
		} else if (obj instanceof Set) {
			// json.append(set2json((Set<?>) obj));
		} else if(obj instanceof Integer ||  obj instanceof Long){
            json.append(string2json(obj.toString()));
        }else {
			json.append(bean2json(obj));
		}
		return json.toString();
	}
	*//**
     * java bean转json
     * @param bean
     * @return
     *//*
	public static String bean2json(Object bean) {
		StringBuilder json = new StringBuilder();
		json.append("{");
		PropertyDescriptor[] props = null;
		try {
			props = Introspector.getBeanInfo(bean.getClass(), Object.class)
					.getPropertyDescriptors();
		} catch (IntrospectionException e) {
		}
		if (props != null) {
			for (int i = 0; i < props.length; i++) {
				try {
					String name = object2json(props[i].getName());
					String value = object2json(props[i].getReadMethod().invoke(
							bean));
					json.append(name);
					json.append(":");
					json.append(value);
					json.append(",");
				} catch (Exception e) {
				}
			}
			json.setCharAt(json.length() - 1, '}');
		} else {
			json.append("}");
		}
		return json.toString();
	}*/

    /**
     * List转json
     *
     * @param list
     * @return
     */
    public static String list2json(List<?> list) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if (list != null && list.size() > 0) {
            for (Object obj : list) {
                json.append(object2json(obj));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }
        return json.toString();
    }

    /**
     * object数组转json
     *
     * @param array
     * @return
     */
    public static String array2json(Object[] array) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if (array != null && array.length > 0) {
            for (Object obj : array) {
                json.append(object2json(obj));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }
        return json.toString();
    }

    /**
     * Map对象转json
     *
     * @param map
     * @return
     */
    public static String map2json(Map<?, ?> map) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        if (map != null && map.size() > 0) {
            for (Object key : map.keySet()) {
                json.append(object2json(key));
                json.append(":");
                json.append(object2json(map.get(key)));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, '}');
        } else {
            json.append("}");
        }
        return json.toString();
    }

    /**
     * Set集合转json
     *
     * @param set
     * @return
     */
    public static String set2json(Set<?> set) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if (set != null && set.size() > 0) {
            for (Object obj : set) {
                json.append(object2json(obj));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }
        return json.toString();
    }

    /**
     * String字符串转json
     *
     * @param s
     * @return
     */
    public static String string2json(String s) {
        if (s == null)
            return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            switch (ch) {
                case '"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                case '/':
                    sb.append("\\/");
                    break;
                default:
                    if (ch >= '\u0000' && ch <= '\u001F') {
                        String ss = Integer.toHexString(ch);
                        sb.append("\\u");
                        for (int k = 0; k < 4 - ss.length(); k++) {
                            sb.append('0');
                        }
                        sb.append(ss.toUpperCase());
                    } else {
                        sb.append(ch);
                    }
            }
        }
        return sb.toString();
    }

   /* *//**
     * 编码
     *
     * @param obj
     * @return
     *//*
    public static String Encode(Object obj) {
        if (obj == null || obj.toString().equals("null"))
            return null;
        if (obj != null && obj.getClass() == String.class) {
            return obj.toString();
        }
        JSONSerializer serializer = new JSONSerializer();
        serializer.transform(new DateTransformer("yyyy-MM-dd' 'HH:mm:ss"),
                Date.class);
        serializer.transform(new DateTransformer("yyyy-MM-dd' 'HH:mm:ss"),
                Timestamp.class);
        return serializer.deepSerialize(obj);
    }

    *//**
     * 解码
     *
     * @param json
     * @return
     *//*
    @SuppressWarnings("rawtypes")
    public static Object Decode(String json) {
        if (StringUtil.isNullOrEmpty(json))
            return "";
        JSONDeserializer deserializer = new JSONDeserializer();
        deserializer.use(String.class, new DateTransformer("yyyy-MM-dd'T'HH:mm:ss"));
        Object obj = deserializer.deserialize(json);
        if (obj != null && obj.getClass() == String.class) {
            return Decode(obj.toString());
        }
        return obj;
    }*/
}
