package com.yhkhgl.top.utils;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * created by: darren at 2019/1/21
 * desc:
 */
public class GsonFaultCreator {
    /**
     * @return
     */
    public static GsonBuilder createFaultGsonObject() {
        return new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
//                .serializeNulls() // 取消这行代码，让String字符串即便为null的时候，解析完成的Bean里面也有返回  ???
                .registerTypeHierarchyAdapter(List.class, new JsonDeserializer<List>() {
                    @Override
                    public List<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        if (json.isJsonArray()) {
                            JsonArray array = json.getAsJsonArray();
                            Type itemType = ((ParameterizedType) typeOfT).getActualTypeArguments()[0];
                            List list = new ArrayList<>();
                            for (int i = 0; i < array.size(); i++) {
                                JsonElement element = array.get(i);
                                Object item = context.deserialize(element, itemType);
                                list.add(item);
                            }
                            return list;
                        } else {
                            //和接口类型不符，返回空List
                            return Collections.EMPTY_LIST;
                        }
                    }
                })
                .registerTypeHierarchyAdapter(Integer.class, new JsonDeserializer<Integer>() {
                    @Override
                    public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        try {
                            return json.getAsInt();
                        } catch (Exception e) {
                            return -1;
                        }
                    }
                })
                .registerTypeHierarchyAdapter(int.class, new JsonDeserializer<Integer>() {
                    @Override
                    public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        try {
                            return json.getAsInt();
                        } catch (Exception e) {
                            return -1;
                        }
                    }
                })
                .registerTypeHierarchyAdapter(Long.class, new JsonDeserializer<Long>() {
                    @Override
                    public Long deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        try {
                            return json.getAsLong();
                        } catch (Exception e) {
                            return -1L;
                        }
                    }
                })
                .registerTypeHierarchyAdapter(long.class, new JsonDeserializer<Long>() {
                    @Override
                    public Long deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        try {
                            return json.getAsLong();
                        } catch (Exception e) {
                            return -1L;
                        }
                    }
                })
                .registerTypeHierarchyAdapter(Float.class, new JsonDeserializer<Float>() {
                    @Override
                    public Float deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        try {
                            return json.getAsFloat();
                        } catch (Exception e) {
                            return -1.0f;
                        }
                    }
                })
                .registerTypeHierarchyAdapter(float.class, new JsonDeserializer<Float>() {
                    @Override
                    public Float deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        try {
                            return json.getAsFloat();
                        } catch (Exception e) {
                            return -1.0f;
                        }
                    }
                })
                .registerTypeHierarchyAdapter(Double.class, new JsonDeserializer<Double>() {
                    @Override
                    public Double deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        try {
                            return json.getAsDouble();
                        } catch (Exception e) {
                            return -1.0;
                        }
                    }
                })
                .registerTypeHierarchyAdapter(double.class, new JsonDeserializer<Double>() {
                    @Override
                    public Double deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        try {
                            return json.getAsDouble();
                        } catch (Exception e) {
                            return -1.0;
                        }
                    }
                })
                .registerTypeHierarchyAdapter(Byte.class, new JsonDeserializer<Byte>() {
                    @Override
                    public Byte deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        try {
                            return json.getAsByte();
                        } catch (Exception e) {
                            return -1;
                        }
                    }
                })
                .registerTypeHierarchyAdapter(byte.class, new JsonDeserializer<Byte>() {
                    @Override
                    public Byte deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        try {
                            return json.getAsByte();
                        } catch (Exception e) {
                            return -1;
                        }
                    }
                })
                .registerTypeHierarchyAdapter(Boolean.class, new JsonDeserializer<Boolean>() {
                    @Override
                    public Boolean deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        try {
                            return json.getAsBoolean();
                        } catch (Exception e) {
                            return false;
                        }
                    }
                })
                .registerTypeHierarchyAdapter(boolean.class, new JsonDeserializer<Boolean>() {
                    @Override
                    public Boolean deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        try {
                            return json.getAsBoolean();
                        } catch (Exception e) {
                            return false;
                        }
                    }
                })
                .registerTypeHierarchyAdapter(String.class, new JsonDeserializer<String>() {
                    @Override
                    public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        try {
                            return json.getAsString();
                        } catch (Exception e) {
                            return "";
                        }
                    }
                });
    }
}
