package com.dikkulah.retrofit.request;

import com.google.gson.JsonElement;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

@Service
public interface DailyServiceRequest {

    @POST("/api/v1/daily")
    Call<JsonElement> dailySave(@Body JsonElement jsonElement, @Header("GatewayToken") String token);

    //LIST
    @GET("/api/v1/daily")
    Call<List<JsonElement>> dailyList(@Header("GatewayToken") String token);

    //FIND
    @GET("/api/v1/daily/{id}")
    Call<JsonElement> dailyFind(@Path("id") String id, @Header("GatewayToken") String token);


    //DELETE
    @DELETE("/api/v1/daily/{id}")
    Call<JsonElement> dailyDelete(@Path("id") String id, @Header("GatewayToken") String token);


    //UPDATE
    @PUT("/api/v1/daily/{id}")
    Call<JsonElement> dailyUpdate(@Path("id") String id, @Body JsonElement jsonElement, @Header("GatewayToken") String token);

}
