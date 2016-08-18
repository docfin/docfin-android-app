package com.jellsoft.mobile.docfin.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jellsoft.mobile.docfin.model.DoctorSpecialization;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by atulanand on 8/17/16.
 */
public class Json<T> {

    public static <T> T toObject(String jsonString) {
        Type type = new TypeToken<T>() {
        }.getType();
        return new Gson().fromJson(jsonString, type);
    }


    public static <T> Collection<T> toCollection(String jsonString) {
        Type collectionType = new TypeToken<Collection<T>>() {
        }.getType();
        Collection<T> collection = new Gson().fromJson(jsonString, collectionType);
        return collection;
    }

    public static <T> List<T> toList(String jsonString) {
        Collection<T> collection = Json.toCollection(jsonString);
        List<T> l = new ArrayList<>();
        l.addAll(collection);
        return l;
    }
}
