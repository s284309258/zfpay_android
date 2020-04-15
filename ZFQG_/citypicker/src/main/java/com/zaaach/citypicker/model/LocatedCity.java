package com.zaaach.citypicker.model;

public class LocatedCity extends City {

    public LocatedCity(String name, String id, String code, String lng, String lat) {
        super(name, id, "定位城市", code, lng, lat);
    }
}
