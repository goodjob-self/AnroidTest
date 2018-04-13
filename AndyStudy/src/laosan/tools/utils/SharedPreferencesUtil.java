package laosan.tools.utils;

import android.content.Context;
import android.content.SharedPreferences;


	/**
	 * Created by ZhangHao on 2016/6/21.
	 * SharedPreferences ������
	 */

public class SharedPreferencesUtil {

    private static SharedPreferencesUtil util;
    private static SharedPreferences sp;

    private SharedPreferencesUtil(Context context, String name) {
        sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    /**
     * ��ʼ��SharedPreferencesUtil,ֻ��Ҫ��ʼ��һ�Σ�������Application�г�ʼ��
     *
     * @param context �����Ķ���
     * @param name    SharedPreferences Name
     */
    public static void getInstance(Context context, String name) {
        if (util == null) {
            util = new SharedPreferencesUtil(context, name);
        }
    }
//
//    /**
//     * �������ݵ�SharedPreferences
//     *
//     * @param key   ��
//     * @param value ��Ҫ���������
//     * @return ������
//     */
//    public static boolean putData(String key, Object value) {
//        boolean result;
//        SharedPreferences.Editor editor = sp.edit();
//        String type = value.getClass().getSimpleName();
//        try {
//            switch (type) {
//                case "Boolean":
//                    editor.putBoolean(key, (Boolean) value);
//                    break;
//                case "Long":
//                    editor.putLong(key, (Long) value);
//                    break;
//                case "Float":
//                    editor.putFloat(key, (Float) value);
//                    break;
//                case "String":
//                    editor.putString(key, (String) value);
//                    break;
//                case "Integer":
//                    editor.putInt(key, (Integer) value);
//                    break;
//                default:
//                    Gson gson = new Gson();
//                    String json = gson.toJson(value);
//                    editor.putString(key, json);
//                    break;
//            }
//            result = true;
//        } catch (Exception e) {
//            result = false;
//            e.printStackTrace();
//        }
//        editor.apply();
//        return result;
//    }
//
//    /**
//     * ��ȡSharedPreferences�б��������
//     *
//     * @param key          ��
//     * @param defaultValue ��ȡʧ��Ĭ��ֵ
//     * @return ��SharedPreferences��ȡ������
//     */
//    public static Object getData(String key, Object defaultValue) {
//        Object result;
//        String type = defaultValue.getClass().getSimpleName();
//        try {
//            switch (type) {
//                case "Boolean":
//                    result = sp.getBoolean(key, (Boolean) defaultValue);
//                    break;
//                case "Long":
//                    result = sp.getLong(key, (Long) defaultValue);
//                    break;
//                case "Float":
//                    result = sp.getFloat(key, (Float) defaultValue);
//                    break;
//                case "String":
//                    result = sp.getString(key, (String) defaultValue);
//                    break;
//                case "Integer":
//                    result = sp.getInt(key, (Integer) defaultValue);
//                    break;
//                default:
//                    Gson gson = new Gson();
//                    String json = sp.getString(key, "");
//                    if (!json.equals("") && json.length() > 0) {
//                        result = gson.fromJson(json, defaultValue.getClass());
//                    } else {
//                        result = defaultValue;
//                    }
//                    break;
//            }
//        } catch (Exception e) {
//            result = null;
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    /**
//     * ���ڱ��漯��
//     *
//     * @param key  key
//     * @param list ��������
//     * @return ������
//     */
//    public static <T> boolean putListData(String key, List<T> list) {
//        boolean result;
//        String type = list.get(0).getClass().getSimpleName();
//        SharedPreferences.Editor editor = sp.edit();
//        JsonArray array = new JsonArray();
//        try {
//            switch (type) {
//                case "Boolean":
//                    for (int i = 0; i < list.size(); i++) {
//                        array.add((Boolean) list.get(i));
//                    }
//                    break;
//                case "Long":
//                    for (int i = 0; i < list.size(); i++) {
//                        array.add((Long) list.get(i));
//                    }
//                    break;
//                case "Float":
//                    for (int i = 0; i < list.size(); i++) {
//                        array.add((Float) list.get(i));
//                    }
//                    break;
//                case "String":
//                    for (int i = 0; i < list.size(); i++) {
//                        array.add((String) list.get(i));
//                    }
//                    break;
//                case "Integer":
//                    for (int i = 0; i < list.size(); i++) {
//                        array.add((Integer) list.get(i));
//                    }
//                    break;
//                default:
//                    Gson gson = new Gson();
//                    for (int i = 0; i < list.size(); i++) {
//                        JsonElement obj = gson.toJsonTree(list.get(i));
//                        array.add(obj);
//                    }
//                    break;
//            }
//            editor.putString(key, array.toString());
//            result = true;
//        } catch (Exception e) {
//            result = false;
//            e.printStackTrace();
//        }
//        editor.apply();
//        return result;
//    }
//
//    /**
//     * ��ȡ�����List
//     *
//     * @param key key
//     * @return ��Ӧ��Lis����
//     */
//    public static <T> List<T> getListData(String key, Class<T> cls) {
//        List<T> list = new ArrayList<>();
//        String json = sp.getString(key, "");
//        if (!json.equals("") && json.length() > 0) {
//            Gson gson = new Gson();
//            JsonArray array = new JsonParser().parse(json).getAsJsonArray();
//            for (JsonElement elem : array) {
//                list.add(gson.fromJson(elem, cls));
//            }
//        }
//        return list;
//    }
//
//    /**
//     * ���ڱ��漯��
//     *
//     * @param key key
//     * @param map map����
//     * @return ������
//     */
//    public static <K, V> boolean putHashMapData(String key, Map<K, V> map) {
//        boolean result;
//        SharedPreferences.Editor editor = sp.edit();
//        try {
//            Gson gson = new Gson();
//            String json = gson.toJson(map);
//            editor.putString(key, json);
//            result = true;
//        } catch (Exception e) {
//            result = false;
//            e.printStackTrace();
//        }
//        editor.apply();
//        return result;
//    }
//
//    /**
//     * ���ڱ��漯��
//     *
//     * @param key key
//     * @return HashMap
//     */
//    public static <V> HashMap<String, V> getHashMapData(String key, Class<V> clsV) {
//        String json = sp.getString(key, "");
//        HashMap<String, V> map = new HashMap<>();
//        Gson gson = new Gson();
//        JsonObject obj = new JsonParser().parse(json).getAsJsonObject();
//        Set<Map.Entry<String, JsonElement>> entrySet = obj.entrySet();
//        for (Map.Entry<String, JsonElement> entry : entrySet) {
//            String entryKey = entry.getKey();
//            JsonObject value = (JsonObject) entry.getValue();
//            map.put(entryKey, gson.fromJson(value, clsV));
//        }
//        Log.e("SharedPreferencesUtil", obj.toString());
//        return map;
//    }
}

