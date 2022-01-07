package com.bqj.mall.library.commonlib.net;

import com.google.gson.stream.JsonReader;
import com.lzy.okgo.convert.Converter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * <pre>
 *     author : tangyang
 *     e-mail : ty766998873@163.com
 *     time   : 2020/06/03
 *     desc   :
 *     version: 2.6.0
 * </pre>
 */
public class JsonConvert<T> implements Converter<T> {


    private Type type;
    private Class<T> clazz;

    public JsonConvert() {
    }

    public JsonConvert(Type type) {
        this.type = type;
    }

    public JsonConvert(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * 该方法是子线程处理，不能做ui相关的工作
     * 主要作用是解析网络返回的 response 对象，生成onSuccess回调中需要的数据对象
     */
    @Override
    public T convertResponse(Response response) throws Throwable {
        if (type == null) {
            if (clazz == null) {
                // 如果没有通过构造函数传进来，就自动解析父类泛型的真实类型（有局限性，继承后就无法解析到）
                Type genType = getClass().getGenericSuperclass();
                type = ((ParameterizedType) genType).getActualTypeArguments()[0];
            } else {
                return parseClass(response, clazz);
            }
        }

        if (type instanceof ParameterizedType) {
            return parseParameterizedType(response, (ParameterizedType) type);
        } else if (type instanceof Class) {
            return parseClass(response, (Class<?>) type);
        } else {
            return parseType(response, type);
        }
    }

    private T parseClass(Response response, Class<?> rawType) throws Exception {
        if (rawType == null) return null;
        ResponseBody body = response.body();
        if (body == null) return null;
        JsonReader jsonReader = new JsonReader(body.charStream());
        if (rawType == String.class) {
            //noinspection unchecked
            return (T) body.string();
        } else if (rawType == JSONObject.class) {
            //noinspection unchecked
            return (T) new JSONObject(body.string());
        } else if (rawType == JSONArray.class) {
            //noinspection unchecked
            return (T) new JSONArray(body.string());
        } else {
            T t = Convert.fromJson(jsonReader, rawType);
            response.close();
            return t;
        }
    }

    private T parseType(Response response, Type type) throws Exception {
        if (type == null) return null;
        ResponseBody body = response.body();
        if (body == null) return null;
        JsonReader jsonReader = new JsonReader(body.charStream());
        T t = Convert.fromJson(jsonReader, type);
        response.close();
        return t;
    }

    private T parseParameterizedType(Response response, ParameterizedType type) throws Exception {
        if (type == null) return null;
        ResponseBody body = response.body();
        if (body == null) return null;
        JsonReader jsonReader = new JsonReader(body.charStream());
        Type rawType = type.getRawType();                     // 泛型的实际类型
        Type typeArgument = type.getActualTypeArguments()[0]; // 泛型的参数
        if (response.code() == 401) {
            InvalidTokenBean invalidTokenBean = Convert.fromJson(response.body().string(), InvalidTokenBean.class);
            if (invalidTokenBean != null && "invalid_token".equals(invalidTokenBean.getError())) {
                throw new BQJException(401, "token过期,请重新登录");
            } else if (invalidTokenBean != null && "unauthorized".equals(invalidTokenBean.getError())) {
                throw new BQJException(402, "未登录,请先登录");
            } else {
                throw new BQJException(403, "401错误");
            }
        } else {
            if (rawType != BQJResponse.class) {
                T t = Convert.fromJson(jsonReader, type);
                response.close();
                return t;
            } else {
                if (typeArgument == Void.class) {
                    BQJSimpleResponse simpleResponse = Convert.fromJson(jsonReader, BQJSimpleResponse.class);
                    response.close();
                    return (T) simpleResponse.toBQJResponse();
                } else {
                    BQJResponse lzyResponse = Convert.fromJson(jsonReader, type);
                    response.close();
                    int statusCode = lzyResponse.getStatus();
                    if (statusCode == 200) {
                        return (T) lzyResponse;
                    } else if (statusCode == 500) {
                        throw new IllegalStateException(lzyResponse.getMessage());
                    } else {
                        //直接将服务端的错误信息抛出，onError中可以获取
                        throw new IllegalStateException(lzyResponse.getMessage());
                    }
                }
            }
        }
    }

}
