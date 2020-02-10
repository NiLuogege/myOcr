package com.example.ruifight_3.saolouruifight.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RuiFight-3 on 2018/4/16.
 */

public class JsonUtils {

    private static GsonBuilder gb = new GsonBuilder();

    /**
     * 将json字符串转化成实体对象
     *
     * @param json
     * @param classOfT
     * @return
     */
    public static Object stringToObject(String json, Class classOfT) throws Exception {
        gb.registerTypeAdapter(String.class, new StringConverter());
        Gson mGson = gb.create();
        return mGson.fromJson(json, classOfT);
    }

    /**
     * 将对象转换为json字符串 或者 把list 转化成json
     *
     * @param object
     * @param <T>
     * @return
     */
    public static <T> String objectToString(T object) {
        gb.registerTypeAdapter(String.class, new StringConverter());
        Gson mGson = gb.create();
        return mGson.toJson(object);
    }

    /**
     * 把json 字符串转化成list
     *
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<T> stringToList(String json, Class<T> cls) {
        Gson gson = new Gson();
        List<T> list = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (final JsonElement elem : array) {
            list.add(gson.fromJson(elem, cls));
        }
        return list;
    }

    /**
     * 防止json属性 为空
     */
    public static class StringConverter implements JsonSerializer<String>,
            JsonDeserializer<String> {
        @Override
        public JsonElement serialize(String src, Type typeOfSrc,
                                     JsonSerializationContext context) {
            if (src == null) {
                return new JsonPrimitive("");
            } else {
                return new JsonPrimitive(src.toString());
            }
        }

        @Override
        public String deserialize(JsonElement json, Type typeOfT,
                                  JsonDeserializationContext context)
                throws JsonParseException {
            return json.getAsJsonPrimitive().getAsString();
        }
    }
}
