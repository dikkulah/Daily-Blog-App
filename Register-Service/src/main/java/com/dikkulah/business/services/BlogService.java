package com.dikkulah.business.services;

import com.dikkulah.retrofit.RetrofitCommonGenerics;
import com.dikkulah.retrofit.request.BlogServiceRequest;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogServiceRequest blogServiceRequest;


    public JsonElement blogSave(JsonElement jsonElement, String token) {
        JsonObject daily = jsonElement.getAsJsonObject();
        daily.addProperty("email", SecurityContextHolder.getContext().getAuthentication().getName());
        return RetrofitCommonGenerics.retrofitGenerics(blogServiceRequest.blogSave(jsonElement, token));
    }

    public List<JsonElement> blogList(String token) {
        return RetrofitCommonGenerics.retrofitGenerics(blogServiceRequest.blogList(token));
    }

    public JsonElement blogFind(String id, String token) {
        return RetrofitCommonGenerics.retrofitGenerics(blogServiceRequest.blogFind(id, token));
    }

    public JsonElement blogDelete(String id, String token) {
        return RetrofitCommonGenerics.retrofitGenerics(blogServiceRequest.blogDelete(id, token));
    }

    public JsonElement blogUpdate(String id, JsonElement jsonElement, String token) {
        return RetrofitCommonGenerics.retrofitGenerics(blogServiceRequest.blogUpdate(id, jsonElement, token));
    }
}
