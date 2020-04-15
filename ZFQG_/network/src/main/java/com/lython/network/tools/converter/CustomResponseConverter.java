package com.lython.network.tools.converter;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class CustomResponseConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    public CustomResponseConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        Reader reader = value.charStream();
        if(String.class.equals(type)){
            try {
                String valueStr = new String(value.bytes());
                valueStr = valueStr.replaceAll("\r\n", "");
                return (T) valueStr;
            }catch (Exception e){
                return (T)"";
            }
        }
        try {
            return gson.fromJson(reader, type);
        }catch (Exception ex){
          throw ex;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ignored) {
                }
            }
        }
    }
}
