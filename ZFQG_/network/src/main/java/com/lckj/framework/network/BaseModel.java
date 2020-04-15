package com.lckj.framework.network;

import com.google.gson.GsonBuilder;

import java.io.Serializable;

public class BaseModel implements Serializable {

    public String toJson(){
        return new GsonBuilder().create().toJson(this);
    }
}