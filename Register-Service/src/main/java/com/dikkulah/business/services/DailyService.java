package com.dikkulah.business.services;

import com.dikkulah.retrofit.RetrofitCommonGenerics;
import com.dikkulah.retrofit.request.DailyServiceRequest;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

//Lombok
@RequiredArgsConstructor
@Log4j2

@Service
public class DailyService {
    private final DailyServiceRequest dailyServiceRequest;

    public JsonElement dailySave(JsonElement jsonElement, String token) {
        JsonObject daily = jsonElement.getAsJsonObject();
        daily.addProperty("email", SecurityContextHolder.getContext().getAuthentication().getName());
        return RetrofitCommonGenerics.retrofitGenerics(dailyServiceRequest.dailySave(jsonElement, token));
    }

    public List<JsonElement> dailyList(String token) {
        return RetrofitCommonGenerics.retrofitGenerics(dailyServiceRequest.dailyList(token));
    }

    public JsonElement dailyFind(String id, String token) {
        return RetrofitCommonGenerics.retrofitGenerics(dailyServiceRequest.dailyFind(id, token));
    }

    public JsonElement dailyDelete(String id, String token) {
        return RetrofitCommonGenerics.retrofitGenerics(dailyServiceRequest.dailyDelete(id,token));
    }

    public JsonElement dailyUpdate(String id, JsonElement jsonElement, String token) {
        return RetrofitCommonGenerics.retrofitGenerics(dailyServiceRequest.dailyUpdate(id, jsonElement,token));
    }

}
