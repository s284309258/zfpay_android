package com.lython.network.tools.converter;

import com.google.gson.Gson;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class CustomGsonConverter extends Converter.Factory {

    private final Gson gson;

    public static CustomGsonConverter create() {
        return create(new Gson());
    }

    public static CustomGsonConverter create(Gson gson) {
        return new CustomGsonConverter(gson);
    }

    private CustomGsonConverter(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        this.gson = gson;
    }

    /*@Override
    public Converter<?, RequestBody> toRequestBody(Type type, Annotation[] annotations) {
        return new CustomRequestConverter<>(gson, type);
    }

    @Override
    public Converter<ResponseBody, ?> fromResponseBody(Type type, Annotation[] annotations) {
        return new CustomResponseConverter<>(gson, type);
    }*/

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new CustomResponseConverter<>(gson, type);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new CustomRequestConverter<>(gson, type);
    }
}
